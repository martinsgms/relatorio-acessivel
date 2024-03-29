package br.edu.ifsp.ptb.ra.exame.service;

import static br.edu.ifsp.ptb.ra.exame.common.ErrorMessageBuilder.RECURSO_EXAME;
import static br.edu.ifsp.ptb.ra.exame.common.ErrorMessageBuilder.RECURSO_SERVICO_SAUDE;
import static br.edu.ifsp.ptb.ra.exame.common.ErrorMessageBuilder.RECURSO_USUARIO;
import static br.edu.ifsp.ptb.ra.exame.common.ErrorMessageBuilder.recursoInexistente;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.dto.ExameDTO;
import br.edu.ifsp.ptb.ra.exame.dto.QuadroPaDTO;
import br.edu.ifsp.ptb.ra.exame.dto.ServicoSaudeDTO;
import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;
import br.edu.ifsp.ptb.ra.exame.exception.ServiceException;
import br.edu.ifsp.ptb.ra.exame.model.ExameModel;
import br.edu.ifsp.ptb.ra.exame.model.StatusExameModel;
import br.edu.ifsp.ptb.ra.exame.repository.ExameRepository;
import br.edu.ifsp.ptb.ra.exame.util.DateTimeUtils;

@Service
public class ExameService 
{
    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ServicoSaudeService servicoSaudeService;

    @Autowired
    private AfericaoPaService afericaoPaService;

    @Autowired
    private RelacionadorEventoAfericaoService relacionadorService;

    public ExameDTO novoExame(ExameDTO dto) throws ServiceException
    {
        UsuarioDTO usuario = usuarioService.buscaUsuario(dto.getEmail());

        if (usuario == null)
        {
            throw new ServiceException(recursoInexistente(RECURSO_USUARIO, dto.getEmail()));
        }

        if (!servicoSaudeService.verificaSeServicoSaudeExiste(dto.getIdServicoSaude()))
        {
            throw new ServiceException(recursoInexistente(RECURSO_SERVICO_SAUDE, dto.getIdServicoSaude()));
        }

        return agendaNovoExame(new ExameModel(usuario, dto));
    }

    public ExameDTO agendaNovoExame(ExameModel model)
    {
        return new ExameDTO(exameRepository.save(model));
    }

    public List<ExameDTO> listaExamesUsuario(Long idUsuario)
    {
        List<ExameDTO> exames = exameRepository.listExamesUsuario(idUsuario).stream().map(ExameDTO::new).collect(Collectors.toList());

        exames.forEach(e ->
        {
            ServicoSaudeDTO servicoSaude = servicoSaudeService.getIdentificacao(e.getIdServicoSaude());
            e.setServicoSaude(servicoSaude);
        });

        return exames;
    }

    public List<EventoDTO> getEventoList(Long idExame) throws ServiceException
    {
        verificaSeExameExiste(idExame);

        return eventoService.getEventosDoExame(idExame);
    }

    public ExameDTO detalheExame(Long idExame) throws ServiceException
    {
        verificaSeExameExiste(idExame);

        var exame = exameRepository.getOne(idExame);

        var exameDTO = new ExameDTO(alteraStatusExame(exame));

        ServicoSaudeDTO servicoSaude = servicoSaudeService.getIdentificacao(exame.getIdServicoSaude());
        exameDTO.setServicoSaude(servicoSaude);

        return exameDTO;
    }

    private ExameModel alteraStatusExame(ExameModel exame)
    {
        var hoje = LocalDateTime.now();
        var dhInicioExame = exame.getTimestampExame();
        var dhFimExame = dhInicioExame.plus(Period.ofDays(1));

        if (exame.getStatus().getCodigo().equals("AGE") && (hoje.isEqual(dhInicioExame) || hoje.isAfter(dhInicioExame)))
        {
            exame.setStatus(new StatusExameModel("AND", "EM ANDAMENTO"));
        }

        if (exame.getStatus().getCodigo().equals("AND") && hoje.isAfter(dhFimExame))
        {
            exame.setStatus(new StatusExameModel("COL", "COLETADO"));
        }

        if (StringUtils.isBlank(exame.getStatus().getDescricao()))
        {
            return exameRepository.save(exame);
        }

        return exame;
    }

    public void verificaSeExameExiste(Long idExame) throws ServiceException
    {
        if (!exameRepository.existsById(idExame))
        {
            throw new ServiceException(recursoInexistente(RECURSO_EXAME, idExame));
        }
    }

    public QuadroPaDTO getDiarioAtividades(Long idServicoSaude, String idExternoExame) throws ServiceException
    {
        ExameDTO exameDTO = getExamePorIdExternoEServicoDeSaude(idServicoSaude, idExternoExame);
        UsuarioDTO usuarioDTO = usuarioService.buscaUsuario(exameDTO.getUsuario());
        ServicoSaudeDTO servicoSaudeDTO = servicoSaudeService.getIdentificacao(exameDTO.getIdServicoSaude());

        QuadroPaDTO quadroPA = afericaoPaService.getQuadroAfericoesPA(idExternoExame);

        quadroPA.setIdExternoExame(exameDTO.getIdExterno());
        quadroPA.setNomePaciente(usuarioDTO.getNome());
        quadroPA.setNomeServicoSaude(servicoSaudeDTO.getNome());
        quadroPA.setNomeCompletoServicoSaude(servicoSaudeDTO.getNomeCompleto());
        quadroPA.setDataHoraInstalacao(DateTimeUtils.getDataHora(quadroPA.getAfericoes().get(0).getTimestamp()));

        return relacionadorService.relaciona(quadroPA, exameDTO);
    }

    private ExameDTO getExamePorIdExternoEServicoDeSaude(Long idServicoSaude, String idExternoExame)
            throws ServiceException
    {
        ExameModel exameModel = exameRepository.findByIdExternoAndIdServicoSaude(idExternoExame, idServicoSaude);

        if (exameModel == null)
        {
            throw new ServiceException(recursoInexistente(RECURSO_EXAME, idExternoExame));
        }
        ExameDTO exameDTO = new ExameDTO(exameModel);

        List<EventoDTO> eventoList = getEventoList(exameModel.getId());
        exameDTO.setEventos(eventoList);

        return exameDTO;
    }

    public void alteraStatusParaProcessado(String idExternoExame)
    {
        ExameModel exameModel = exameRepository.findByIdExterno(idExternoExame);

        exameModel.setStatus(new StatusExameModel("PRO", "PROCESSADO"));

        exameRepository.save(exameModel);
    }
}
