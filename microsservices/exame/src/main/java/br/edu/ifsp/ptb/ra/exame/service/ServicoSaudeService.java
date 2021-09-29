package br.edu.ifsp.ptb.ra.exame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.client.ServicoSaudeClient;
import br.edu.ifsp.ptb.ra.exame.dto.ServicoSaudeDTO;

@Service
public class ServicoSaudeService
{
    @Autowired
    private ServicoSaudeClient servicoSaudeClient;

    public boolean verificaSeServicoSaudeExiste(Long idServicoSaude)
    {
        var response = servicoSaudeClient.verificaSeServicoSaudeExiste(idServicoSaude);

        return response.getBody();
    }

    public ServicoSaudeDTO getIdentificacao(Long idServicoSaude)
    {
        var response = servicoSaudeClient.getIdentificacao(idServicoSaude);

        return response.getBody();
    }

}
