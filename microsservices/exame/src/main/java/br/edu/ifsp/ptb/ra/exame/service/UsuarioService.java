package br.edu.ifsp.ptb.ra.exame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;

@Service
public class UsuarioService
{
    @Autowired
    private RestTemplate client;

    public UsuarioDTO buscaUsuarioPorEmail(String email)
    {
        ResponseEntity<UsuarioDTO> response = client.exchange("http://app-usuario/usuario/" + email, HttpMethod.GET, null, UsuarioDTO.class);

        return response.getBody();
    }
}
