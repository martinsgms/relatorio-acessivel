package br.edu.ifsp.ptb.ra.exame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.ptb.ra.exame.model.EventoModel;

@Repository
public interface EventoRepository extends JpaRepository<EventoModel, Long>
{

}
