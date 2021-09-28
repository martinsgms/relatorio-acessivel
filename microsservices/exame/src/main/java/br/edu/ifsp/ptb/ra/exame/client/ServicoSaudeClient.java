package br.edu.ifsp.ptb.ra.exame.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("app-servicoSaude")
public interface ServicoSaudeClient
{
    @GetMapping("servico-saude/{idServicoSaude}/existe")
    public ResponseEntity<Boolean> verificaSeServicoSaudeExiste(@PathVariable Long idServicoSaude);
}
