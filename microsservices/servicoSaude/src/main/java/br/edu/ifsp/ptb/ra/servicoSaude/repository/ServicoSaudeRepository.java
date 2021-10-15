package br.edu.ifsp.ptb.ra.servicoSaude.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.ptb.ra.servicoSaude.model.ServicoSaudeModel;
import br.edu.ifsp.ptb.ra.servicoSaude.projection.IdentificacaoServicoSaudeProjection;

@Repository
public interface ServicoSaudeRepository extends JpaRepository<ServicoSaudeModel, Long>
{
    @Query(value = "select ss.id, ss.nm_curto nome, ss.nm_completo nomeCompleto from radb.tra_servico_saude ss where ss.id = :idServicoSaude", nativeQuery = true)
    IdentificacaoServicoSaudeProjection getIdentificacao(Long idServicoSaude);
}
