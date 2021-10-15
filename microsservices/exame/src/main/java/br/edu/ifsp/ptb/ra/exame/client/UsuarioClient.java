package br.edu.ifsp.ptb.ra.exame.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;

@FeignClient("app-usuario")
public interface UsuarioClient
{
    @GetMapping("/usuario")
    ResponseEntity<UsuarioDTO> buscaUsuario(@RequestParam Object identificador);
}
