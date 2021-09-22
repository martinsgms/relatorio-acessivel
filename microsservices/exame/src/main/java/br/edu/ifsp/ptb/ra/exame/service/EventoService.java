package br.edu.ifsp.ptb.ra.exame.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.exception.EventoNaoEncontradoException;
import br.edu.ifsp.ptb.ra.exame.model.EventoModel;
import br.edu.ifsp.ptb.ra.exame.repository.EventoRepository;

@Service
public class EventoService
{
    @Autowired
    private EventoRepository eventoRepository;

    public EventoDTO getEventoById(Long idEvento) throws EventoNaoEncontradoException
    {
        verificaSeEventoExiste(idEvento);

        return new EventoDTO(eventoRepository.getOne(idEvento));
    }

    public EventoDTO salvaEvento(EventoDTO eventoDto)
    {
        var eventoModel = new EventoModel(eventoDto);

        EventoModel eventoSalvo = eventoRepository.save(eventoModel);
        BeanUtils.copyProperties(eventoSalvo, eventoDto);

        return eventoDto;
    }

    public void removeEvento(Long idEvento) throws EventoNaoEncontradoException
    {
        verificaSeEventoExiste(idEvento);

        eventoRepository.deleteById(idEvento);
    }

    public void verificaSeEventoExiste(Long idEvento) throws EventoNaoEncontradoException
    {
        if (!eventoRepository.existsById(idEvento))
        {
            throw new EventoNaoEncontradoException(idEvento);
        }
    }
}
