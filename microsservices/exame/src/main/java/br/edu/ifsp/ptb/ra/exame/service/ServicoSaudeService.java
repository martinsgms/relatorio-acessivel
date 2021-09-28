package br.edu.ifsp.ptb.ra.exame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.client.ServicoSaudeClient;

@Service
public class ServicoSaudeService
{
    @Autowired
    private ServicoSaudeClient servicoSaudeClient;

    public boolean verificaSeServicoSaudeExiste(Long idServicoSaude)
    {
        ResponseEntity<Boolean> response = servicoSaudeClient.verificaSeServicoSaudeExiste(idServicoSaude);

        return response.getBody();
    }

}
