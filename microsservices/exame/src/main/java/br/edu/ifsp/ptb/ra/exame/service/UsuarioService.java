package br.edu.ifsp.ptb.ra.exame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.exame.client.UsuarioClient;
import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;

@Service
public class UsuarioService
{
    @Autowired
    private UsuarioClient usuarioClient;

    public UsuarioDTO buscaUsuario(Object identificador)
    {
        ResponseEntity<UsuarioDTO> response = usuarioClient.buscaUsuario(identificador);

        return response.getBody();
    }
}
