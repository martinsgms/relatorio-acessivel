package br.edu.ifsp.ptb.ra.exame.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;

@FeignClient("app-usuario")
public interface UsuarioClient
{
    @RequestMapping("/usuario/{email}")
    ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@PathVariable String email);
}
