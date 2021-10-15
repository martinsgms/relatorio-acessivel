package br.edu.ifsp.ptb.ra.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifsp.ptb.ra.usuario.dto.UsuarioDTO;
import br.edu.ifsp.ptb.ra.usuario.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscaUsuario(@RequestParam Object identificador)
    {
        try
        {
            return ResponseEntity.ok(usuarioService.findById(Long.parseLong(identificador.toString())));

        } catch (Exception e)
        {
            return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail((String) identificador));
        }
    }
}
