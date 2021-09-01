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
import br.edu.ifsp.ptb.ra.exame.dto.ExameDTO;
import br.edu.ifsp.ptb.ra.exame.model.ExameModel;
import br.edu.ifsp.ptb.ra.exame.service.ExameService;

@Controller
@RequestMapping("/exame")
public class ExameController
{

    @Autowired
    private ExameService exameService;

    @PostMapping
    public ResponseEntity<ExameModel> novoExame(@RequestBody ExameDTO exame)
    {
        ExameModel novoExame = exameService.novoExame(exame);

        return ResponseEntity.ok(novoExame);
    }

    @PostMapping("/{idExame}/evento")
    public ResponseEntity<?> novoEvento(@RequestBody EventoDTO evento, @PathVariable Long idExame)
    {
        ExameModel exame = exameService.consultaExame(idExame);

        if (exame == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exame não encontrado!");

        var novoEvento = exameService.novoEvento(evento);

        return ResponseEntity.ok(novoEvento);
    }

    @GetMapping("/{idExame}")
    public ResponseEntity<ExameModel> consultaExame(@PathVariable Long idExame)
    {
        ExameModel exame = exameService.consultaExame(idExame);

        return ResponseEntity.ok(exame);
    }
}
