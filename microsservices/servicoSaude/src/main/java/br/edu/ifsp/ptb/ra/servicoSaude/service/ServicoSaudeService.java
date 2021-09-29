package br.edu.ifsp.ptb.ra.servicoSaude.service;

import java.util.List;
import java.util.stream.Collectors;

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

    public boolean verificaSeServicoSaudeExiste(Long idServicoSaude)
    {
        return servicoSaudeRepository.existsById(idServicoSaude);
    }

    public ServicoSaudeDTO getIdentificacao(Long idServicoSaude)
    {
        return new ServicoSaudeDTO(servicoSaudeRepository.getIdentificacao(idServicoSaude));
    }

    public List<ServicoSaudeDTO> findAll()
    {
        return servicoSaudeRepository.findAll().stream().map(ServicoSaudeDTO::new).collect(Collectors.toList());
    }

}
