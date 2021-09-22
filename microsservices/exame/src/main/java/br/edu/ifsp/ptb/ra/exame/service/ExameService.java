package br.edu.ifsp.ptb.ra.exame.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ExameService.class);

    public ExameModel novoExame(ExameDTO dto) 
    {
        LOGGER.info("criando novo exame para o usário {}", dto.getEmail());

        UsuarioDTO usuario = usuarioService.buscaUsuarioPorEmail(dto.getEmail());

        var agendaNovoExame = agendaNovoExame(new ExameModel(usuario, dto));

        LOGGER.info("exame agendado com sucesso: {}", agendaNovoExame.getId());

        return agendaNovoExame;
    }

    public ExameModel agendaNovoExame(ExameModel model)
    {
        return exameRepository.save(model);
    }

    public List<ExameModel> listaExamesUsuario(Long idUsuario)
    {
        return exameRepository.listExamesUsuario(idUsuario);
    }

    public ExameModel detalheExame(Long idExame)
    {
        return exameRepository.getExamePorId(idExame);
    }

    public EventoDTO novoEvento(EventoDTO eventoDto)
    {
        var eventoModel = new EventoModel();

        BeanUtils.copyProperties(eventoDto, eventoModel);

        EventoModel eventoSalvo = eventoRepository.save(eventoModel);
        BeanUtils.copyProperties(eventoSalvo, eventoDto);

        return eventoDto;
    }

    public ExameModel consultaExameMaisRecenteDoUsuario(Long idUsuario)
    {
        return exameRepository.getExameMaisRecenteDoUsuario(idUsuario);
    }
}
