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

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.dto.ExameDTO;
import br.edu.ifsp.ptb.ra.exame.dto.QuadroPaDTO;
import br.edu.ifsp.ptb.ra.exame.exception.ServiceException;
import br.edu.ifsp.ptb.ra.exame.service.ExameService;

@Controller
@RequestMapping("/exame")
public class ExameController
{
    @Autowired
    private ExameService exameService;

    @PostMapping
    public ResponseEntity<ExameDTO> novoExame(@RequestBody ExameDTO exame) throws ServiceException
    {
        var novoExame = exameService.novoExame(exame);

        return ResponseEntity.ok(novoExame);
    }

    @GetMapping("/{idExame}")
    public ResponseEntity<ExameDTO> detalheExame(@PathVariable Long idExame) throws ServiceException
    {
        var exame = exameService.detalheExame(idExame);

        return ResponseEntity.ok(exame);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> examesDoUsuario(@PathVariable Long idUsuario, @RequestParam(defaultValue = "false") boolean apenasMaisRecente)
    {
        List<ExameDTO> exames = exameService.listaExamesUsuario(idUsuario);

        if (apenasMaisRecente)
            return ResponseEntity.ok(exames.get(0));

        return ResponseEntity.ok(exames);
    }

    @GetMapping("/{idExame}/eventos")
    public ResponseEntity<List<EventoDTO>> eventosDoExame(@PathVariable Long idExame) throws ServiceException
    {
        var eventos = exameService.getEventoList(idExame);

        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{idExternoExame}/diario")
    public ResponseEntity<QuadroPaDTO> diarioAtividades(@PathVariable String idExternoExame) throws ServiceException
    {
        return ResponseEntity.ok(exameService.getDiarioAtividades(idExternoExame));
    }
}
