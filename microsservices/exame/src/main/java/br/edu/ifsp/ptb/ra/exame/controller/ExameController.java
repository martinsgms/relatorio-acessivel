package br.edu.ifsp.ptb.ra.exame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsp.ptb.ra.exame.service.UsuarioService;

@Controller
@RequestMapping("/exame")
public class ExameController
{
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/{email}")
    public void novoExame(@PathVariable String email)
    {
        usuarioService.resgataUsuarioPorEmail(email);
    }
}
