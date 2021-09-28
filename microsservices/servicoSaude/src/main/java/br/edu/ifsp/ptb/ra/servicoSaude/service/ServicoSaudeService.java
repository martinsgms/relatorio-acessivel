package br.edu.ifsp.ptb.ra.servicoSaude.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.servicoSaude.dto.ServicoSaudeDTO;
import br.edu.ifsp.ptb.ra.servicoSaude.repository.ServicoSaudeRepository;

@Service
public class ServicoSaudeService
{

    @Autowired
    private ServicoSaudeRepository servicoSaudeRepository;

    public ServicoSaudeDTO getServicoSaudePorId(Long idServicoSaude)
    {
        return new ServicoSaudeDTO(servicoSaudeRepository.getOne(idServicoSaude));
    }

}
