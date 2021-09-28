package br.edu.ifsp.ptb.ra.exame.service;

import static br.edu.ifsp.ptb.ra.exame.common.ErrorMessageBuilder.RECURSO_EXAME;
import static br.edu.ifsp.ptb.ra.exame.common.ErrorMessageBuilder.RECURSO_SERVICO_SAUDE;
import static br.edu.ifsp.ptb.ra.exame.common.ErrorMessageBuilder.RECURSO_USUARIO;
import static br.edu.ifsp.ptb.ra.exame.common.ErrorMessageBuilder.recursoInexistente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.dto.ExameDTO;
import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;
import br.edu.ifsp.ptb.ra.exame.exception.ServiceException;
import br.edu.ifsp.ptb.ra.exame.model.ExameModel;
import br.edu.ifsp.ptb.ra.exame.repository.ExameRepository;

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

    public ExameDTO novoExame(ExameDTO dto) throws ServiceException
    {
        UsuarioDTO usuario = usuarioService.buscaUsuarioPorEmail(dto.getEmail());

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
        return exameRepository.listExamesUsuario(idUsuario).stream().map(ExameDTO::new).collect(Collectors.toList());
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

        return new ExameDTO(exame);
    }

    public void verificaSeExameExiste(Long idExame) throws ServiceException
    {
        if (!exameRepository.existsById(idExame))
        {
            throw new ServiceException(recursoInexistente(RECURSO_EXAME, idExame));
        }
    }
}
