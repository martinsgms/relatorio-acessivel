package br.edu.ifsp.ptb.ra.exame.service;

import static br.edu.ifsp.ptb.ra.exame.common.ErrorMessageBuilder.RECURSO_EVENTO;
import static br.edu.ifsp.ptb.ra.exame.common.ErrorMessageBuilder.recursoInexistente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.exception.ServiceException;
import br.edu.ifsp.ptb.ra.exame.model.EventoModel;
import br.edu.ifsp.ptb.ra.exame.repository.EventoRepository;

@Service
public class EventoService
{
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ExameService exameService;

    public EventoDTO getEventoById(Long idEvento) throws ServiceException
    {
        verificaSeEventoExiste(idEvento);

        return new EventoDTO(eventoRepository.getOne(idEvento));
    }

    public EventoDTO salvaEvento(EventoDTO eventoDto) throws ServiceException
    {
        exameService.verificaSeExameExiste(eventoDto.getIdExame());

        var eventoModel = new EventoModel(eventoDto);

        EventoModel eventoSalvo = eventoRepository.save(eventoModel);
        BeanUtils.copyProperties(eventoSalvo, eventoDto);

        return eventoDto;
    }

    public void removeEvento(Long idEvento) throws ServiceException
    {
        verificaSeEventoExiste(idEvento);

        eventoRepository.deleteById(idEvento);
    }

    public void verificaSeEventoExiste(Long idEvento) throws ServiceException
    {
        if (!eventoRepository.existsById(idEvento))
        {
            throw new ServiceException(recursoInexistente(RECURSO_EVENTO, idEvento));
        }
    }

    public List<EventoDTO> getEventosDoExame(Long idExame)
    {
        return eventoRepository.getEventosDoExame(idExame).stream().map(EventoDTO::new).collect(Collectors.toList());
    }
}
