package br.edu.ifsp.ptb.ra.exame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.dto.ExameDTO;
import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;
import br.edu.ifsp.ptb.ra.exame.model.EventoModel;
import br.edu.ifsp.ptb.ra.exame.model.ExameModel;
import br.edu.ifsp.ptb.ra.exame.repository.EventoRepository;
import br.edu.ifsp.ptb.ra.exame.repository.ExameRepository;

/**
 * @see br.edu.ifsp.ptb.ra.exame.controller.ExameController
 * 
 * @author martinsgms
 */
@Service
public class ExameService 
{
    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public ExameModel novoExame(ExameDTO dto) 
    {
        UsuarioDTO usuario = usuarioService.buscaUsuarioPorEmail(dto.getEmail());

        return agendaNovoExame(new ExameModel(usuario, dto));
    }


    public ExameModel agendaNovoExame(ExameModel model)
    {
        return exameRepository.save(model);
    }

    public List<ExameModel> consultaExames(Long idUsuario) {
        return exameRepository.consultaExames(idUsuario);
    }

    public ExameModel consultaExame(Long idExame) {
        return exameRepository.consultaExame(idExame);
    }

    public EventoModel novoEvento(EventoDTO evento) {
        return eventoRepository.save(new EventoModel(evento));
    }

}
