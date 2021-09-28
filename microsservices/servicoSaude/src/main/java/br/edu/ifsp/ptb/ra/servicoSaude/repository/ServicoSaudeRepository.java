package br.edu.ifsp.ptb.ra.servicoSaude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.ptb.ra.servicoSaude.model.ServicoSaudeModel;

@Repository
public interface ServicoSaudeRepository extends JpaRepository<ServicoSaudeModel, Long>
{

}
