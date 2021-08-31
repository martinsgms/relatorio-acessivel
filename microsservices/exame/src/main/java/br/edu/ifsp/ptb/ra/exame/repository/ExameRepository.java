package br.edu.ifsp.ptb.ra.exame.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.ptb.ra.exame.model.ExameModel;

@Repository
public interface ExameRepository extends JpaRepository<ExameModel, Long> {

    @Query(value = "INSERT INTO radb.tra_exame (ID_USUARIO, ID_EXTERNO, NU_INTERVALO_AFERICAO, CD_STATUS, DH_EXAME) "
            + " VALUES (:usuario, :idExterno, :intervaloAfericaoPA, 'AGE', :data) ", nativeQuery = true)
    ExameModel agendaNovoExame(Long usuario, String idExterno, Integer intervaloAfericaoPA, LocalDateTime data);

}
