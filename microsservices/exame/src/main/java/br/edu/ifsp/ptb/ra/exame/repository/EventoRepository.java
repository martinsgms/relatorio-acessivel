package br.edu.ifsp.ptb.ra.exame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.ptb.ra.exame.model.EventoModel;

@Repository
public interface EventoRepository extends JpaRepository<EventoModel, Long>
{
    @Query(value = "select * from radb.tra_evento where id_exame = :idExame order by dh_evento asc", nativeQuery = true)
    List<EventoModel> getEventosDoExame(Long idExame);
}
