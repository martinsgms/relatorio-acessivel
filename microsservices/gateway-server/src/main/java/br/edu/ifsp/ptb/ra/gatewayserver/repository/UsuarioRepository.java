package br.edu.ifsp.ptb.ra.gatewayserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.ptb.ra.gatewayserver.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>
{
    Optional<UsuarioModel> findByEmail(String email);
}
