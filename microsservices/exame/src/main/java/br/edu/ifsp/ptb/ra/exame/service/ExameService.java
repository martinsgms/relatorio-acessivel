package br.edu.ifsp.ptb.ra.exame.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public ExameDTO novoExame(ExameDTO dto)
    {
        LOGGER.info("criando novo exame para o usário {}", dto.getEmail());

        UsuarioDTO usuario = usuarioService.buscaUsuarioPorEmail(dto.getEmail());

        var agendaNovoExame = agendaNovoExame(new ExameModel(usuario, dto));

        LOGGER.info("exame agendado com sucesso: {}", agendaNovoExame.getId());

        return agendaNovoExame;
    }

    public ExameDTO agendaNovoExame(ExameModel model)
    {
        return new ExameDTO(exameRepository.save(model));
    }

    public List<ExameModel> listaExamesUsuario(Long idUsuario)
    {
        return exameRepository.listExamesUsuario(idUsuario);
    }

    public ExameDTO detalheExame(Long idExame)
    {
        return new ExameDTO(exameRepository.getExamePorId(idExame));
    }

    public List<EventoDTO> getEventoList(Long idExame)
    {
        var exame = exameRepository.getExamePorId(idExame);

        if (exame == null)
            return Collections.emptyList();

        return exame.getEventos().stream().map(EventoDTO::new).collect(Collectors.toList());
    }

    public EventoDTO novoEvento(EventoDTO eventoDto)
    {
        var eventoModel = new EventoModel(eventoDto);

        EventoModel eventoSalvo = eventoRepository.save(eventoModel);
        BeanUtils.copyProperties(eventoSalvo, eventoDto);

        return eventoDto;
    }

    public ExameModel consultaExameMaisRecenteDoUsuario(Long idUsuario)
    {
        return exameRepository.getExameMaisRecenteDoUsuario(idUsuario);
    }
}
