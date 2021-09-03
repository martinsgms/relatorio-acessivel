package br.edu.ifsp.ptb.ra.exame.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;

@FeignClient("app-usuario")
public interface UsuarioClient
{
    @GetMapping("/usuario/{email}")
    ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@PathVariable String email);
}
