package br.edu.ifsp.ptb.ra.exame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.exception.EventoNaoEncontradoException;
import br.edu.ifsp.ptb.ra.exame.exception.ExameNaoEncontradoException;
import br.edu.ifsp.ptb.ra.exame.service.EventoService;

@Controller
@RequestMapping("/exame/evento")
public class EventoController
{
    @Autowired
    private EventoService eventoService;

    @GetMapping("/{idEvento}")
    public ResponseEntity<EventoDTO> getEventoPorid(@PathVariable Long idEvento) throws EventoNaoEncontradoException
    {
        var evento = eventoService.getEventoById(idEvento);

        return ResponseEntity.ok(evento);
    }

    @PostMapping
    public ResponseEntity<EventoDTO> salvarEvento(@RequestBody EventoDTO evento) throws ExameNaoEncontradoException
    {
        return ResponseEntity.ok(eventoService.salvaEvento(evento));
    }

    @PutMapping
    public ResponseEntity<EventoDTO> atualizaEvento(@RequestBody EventoDTO evento) throws EventoNaoEncontradoException, ExameNaoEncontradoException
    {
        eventoService.verificaSeEventoExiste(evento.getId());

        return ResponseEntity.ok(eventoService.salvaEvento(evento));
    }

    @DeleteMapping("/{idEvento}")
    public ResponseEntity<Void> removeEvento(@PathVariable Long idEvento) throws EventoNaoEncontradoException
    {
        eventoService.removeEvento(idEvento);

        return ResponseEntity.noContent().build();
    }
}
