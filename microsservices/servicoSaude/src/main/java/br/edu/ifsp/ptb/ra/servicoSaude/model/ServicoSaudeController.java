package br.edu.ifsp.ptb.ra.servicoSaude.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.ptb.ra.servicoSaude.dto.ServicoSaudeDTO;
import br.edu.ifsp.ptb.ra.servicoSaude.service.ServicoSaudeService;

@RestController
@RequestMapping("/servico-saude")
public class ServicoSaudeController
{
    @Autowired
    private ServicoSaudeService servicoSaudeService;

    @GetMapping
    public ResponseEntity<List<ServicoSaudeDTO>> findAll()
    {
        return ResponseEntity.ok(servicoSaudeService.findAll());
    }

    @GetMapping("/{idServicoSaude}")
    public ResponseEntity<ServicoSaudeDTO> getServicoSaudePorId(@PathVariable Long idServicoSaude)
    {
        return ResponseEntity.ok(servicoSaudeService.getServicoSaudePorId(idServicoSaude));
    }

    @GetMapping("/{idServicoSaude}/identificacao")
    public ResponseEntity<ServicoSaudeDTO> getIdentificacao(@PathVariable Long idServicoSaude)
    {
        return ResponseEntity.ok(servicoSaudeService.getIdentificacao(idServicoSaude));
    }

    @GetMapping("/{idServicoSaude}/existe")
    public ResponseEntity<Boolean> verificaSeServicoSaudeExiste(@PathVariable Long idServicoSaude)
    {
        return ResponseEntity.ok(servicoSaudeService.verificaSeServicoSaudeExiste(idServicoSaude));
    }
}
