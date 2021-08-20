package br.edu.ifsp.ptb.ra.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsp.ptb.ra.usuario.model.UsuarioModel;
import br.edu.ifsp.ptb.ra.usuario.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{email}")
    public ResponseEntity<UsuarioModel> resgataUsuarioPorEmail(@PathVariable String email)
    {
        return ResponseEntity.ok(usuarioService.resgataUsuarioPorEmail(email));
    }

}
