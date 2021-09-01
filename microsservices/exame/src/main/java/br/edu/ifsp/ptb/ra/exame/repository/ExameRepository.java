package br.edu.ifsp.ptb.ra.exame.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.ptb.ra.exame.model.ExameModel;

@Repository
public interface ExameRepository extends JpaRepository<ExameModel, Long> {

    @Query(value = "INSERT INTO RADB.TRA_EXAME (ID_USUARIO, ID_EXTERNO, NU_INTERVALO_AFERICAO, CD_STATUS, DH_EXAME) "
            + " VALUES (:usuario, :idExterno, :intervaloAfericaoPA, 'AGE', :data) ", nativeQuery = true)
    ExameModel agendaNovoExame(Long usuario, String idExterno, Integer intervaloAfericaoPA, LocalDateTime data);

    @Query(value = "SELECT * FROM RADB.TRA_EXAME WHERE ID_USUARIO = :idUsuario", nativeQuery = true)
    List<ExameModel> consultaExames(Long idUsuario);

}
