package br.edu.ifsp.ptb.ra.exame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.ptb.ra.exame.model.ExameModel;

@Repository
public interface ExameRepository extends JpaRepository<ExameModel, Long>
{
    @Query(value = "select * from radb.tra_exame where id_usuario = :idUsuario order by dh_exame desc", nativeQuery = true)
    List<ExameModel> listExamesUsuario(Long idUsuario);

    @Query(value = "select * from radb.tra_exame where id_usuario = :idUsuario order by dh_exame desc limit 1", nativeQuery = true)
    ExameModel getExameMaisRecenteDoUsuario(Long idUsuario);

    ExameModel findByIdExterno(String idExterno);
}
