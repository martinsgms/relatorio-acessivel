package br.edu.ifsp.ptb.ra.exame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.service.ExameService;

@Controller
@RequestMapping("/exame/{idExame}/evento")
public class EventoController
{
    @Autowired
    private ExameService exameService;

    @GetMapping
    public ResponseEntity<?> eventosDoExame(@PathVariable Long idExame)
    {
        var eventos = exameService.getEventoList(idExame);

        return ResponseEntity.ok(eventos);
    }

    @PostMapping
    public ResponseEntity<?> novoEvento(@RequestBody EventoDTO evento, @PathVariable Long idExame)
    {
        var exame = exameService.detalheExame(idExame);

        if (exame == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exame não encontrado!");

        evento.setIdExame(idExame);
        var novoEvento = exameService.novoEvento(evento);

        return ResponseEntity.ok(novoEvento);
    }
}
