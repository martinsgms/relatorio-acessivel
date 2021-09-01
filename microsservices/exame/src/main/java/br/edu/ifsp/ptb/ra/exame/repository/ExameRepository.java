package br.edu.ifsp.ptb.ra.exame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.ptb.ra.exame.model.ExameModel;

@Repository
public interface ExameRepository extends JpaRepository<ExameModel, Long> {

    @Query(value = "SELECT * FROM RADB.TRA_EXAME WHERE ID_USUARIO = :idUsuario", nativeQuery = true)
    List<ExameModel> consultaExames(Long idUsuario);

    @Query(value = "SELECT * FROM RADB.TRA_EXAME WHERE ID = :idExame", nativeQuery = true)
    ExameModel consultaExame(Long idExame);
}
