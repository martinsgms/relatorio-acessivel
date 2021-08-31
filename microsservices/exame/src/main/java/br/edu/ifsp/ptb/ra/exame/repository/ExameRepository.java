package br.edu.ifsp.ptb.ra.exame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.ptb.ra.exame.model.ExameModel;

@Repository
public interface ExameRepository extends JpaRepository<ExameModel, Long> {

//    public void agendaNovoExame(UsuarioDTO usuario, LocalDateTime data);

}
