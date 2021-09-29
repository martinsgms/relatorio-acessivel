package br.edu.ifsp.ptb.ra.exame.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifsp.ptb.ra.exame.dto.ServicoSaudeDTO;

@FeignClient("app-servicoSaude")
public interface ServicoSaudeClient
{
    @GetMapping("servico-saude/{idServicoSaude}/existe")
    public ResponseEntity<Boolean> verificaSeServicoSaudeExiste(@PathVariable Long idServicoSaude);

    @GetMapping("servico-saude/{idServicoSaude}")
    public ResponseEntity<ServicoSaudeDTO> getServicoSaudePorId(@PathVariable Long idServicoSaude);

    @GetMapping("servico-saude/{idServicoSaude}/identificacao")
    public ResponseEntity<ServicoSaudeDTO> getIdentificacao(@PathVariable Long idServicoSaude);
}
