package br.edu.ifsp.ptb.ra.exame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<ExameDTO> novoExame(@RequestBody ExameDTO exame)
    {
        var novoExame = exameService.novoExame(exame);

        return ResponseEntity.ok(novoExame);
    }

    @GetMapping("/{idExame}")
    public ResponseEntity<ExameDTO> detalheExame(@PathVariable Long idExame)
    {
        var exame = exameService.detalheExame(idExame);

        return ResponseEntity.ok(exame);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> examesDoUsuario(@PathVariable Long idUsuario, @RequestParam(defaultValue = "false") boolean apenasMaisRecente)
    {
        if (apenasMaisRecente)
            return ResponseEntity.ok(exameService.consultaExameMaisRecenteDoUsuario(idUsuario));

        List<ExameModel> exames = exameService.listaExamesUsuario(idUsuario);

        return ResponseEntity.ok(exames);
    }
}
