package br.edu.ifsp.ptb.ra.servicoSaude.model;

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

    @GetMapping("/{idServicoSaude}")
    public ResponseEntity<ServicoSaudeDTO> getServicoSaudePorId(@PathVariable Long idServicoSaude)
    {
        return ResponseEntity.ok(servicoSaudeService.getServicoSaudePorId(idServicoSaude));
    }
}
