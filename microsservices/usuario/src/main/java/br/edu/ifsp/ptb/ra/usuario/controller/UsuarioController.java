package br.edu.ifsp.ptb.ra.usuario.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifsp.ptb.ra.usuario.dto.UsuarioDTO;
import br.edu.ifsp.ptb.ra.usuario.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

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

    @PostMapping
    public ResponseEntity<String> cadastraUsuario(@RequestBody UsuarioDTO usuario)
    {
        usuarioService.cadastraUsuario(usuario);

        return ResponseEntity.ok(usuario.getNome() + " cadastrado(a) com sucesso!");
    }

    @PutMapping("/senha")
    public ResponseEntity<String> atualizaSenha(@RequestBody UsuarioDTO usuario)
    {
        try
        {
            usuarioService.atualizaSenha(usuario);
        }
        catch (Exception e)
        {
            LOGGER.error("Erro ao atualizar senha: {0}", e);
            return new ResponseEntity<>("Erro ao atualizar senha: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Senha de " + usuario.getNome() + " atualizada com sucesso!");
    }
}
