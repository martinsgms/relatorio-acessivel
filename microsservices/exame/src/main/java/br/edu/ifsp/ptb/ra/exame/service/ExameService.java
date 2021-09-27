package br.edu.ifsp.ptb.ra.exame.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.dto.ExameDTO;
import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;
import br.edu.ifsp.ptb.ra.exame.exception.ExameNaoEncontradoException;
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

    public ExameDTO novoExame(ExameDTO dto)
    {
        UsuarioDTO usuario = usuarioService.buscaUsuarioPorEmail(dto.getEmail());

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

    public List<EventoDTO> getEventoList(Long idExame) throws ExameNaoEncontradoException
    {
        verificaSeExameExiste(idExame);

        return eventoService.getEventosDoExame(idExame);
    }

    public ExameDTO detalheExame(Long idExame) throws ExameNaoEncontradoException
    {
        verificaSeExameExiste(idExame);

        var exame = exameRepository.getOne(idExame);

        return new ExameDTO(exame);
    }

    public void verificaSeExameExiste(Long idExame) throws ExameNaoEncontradoException
    {
        if (!exameRepository.existsById(idExame))
        {
            throw new ExameNaoEncontradoException(idExame);
        }
    }
}
