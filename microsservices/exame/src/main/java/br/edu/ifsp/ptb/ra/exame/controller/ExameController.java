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

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<ExameModel>> consultaExames(@PathVariable Long idUsuario) 
    {
        List<ExameModel> exames = exameService.consultaExames(idUsuario);

        return ResponseEntity.ok(exames);
    }
}
