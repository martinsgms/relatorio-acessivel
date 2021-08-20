package br.edu.ifsp.ptb.ra.exame.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;

@Service
public class UsuarioService
{

    public void resgataUsuarioPorEmail(String email)
    {
        RestTemplate client = new RestTemplate();
        ResponseEntity<UsuarioDTO> response = client.exchange("http://localhost:8081/usuario/" + email, HttpMethod.GET, null, UsuarioDTO.class);

        System.out.println(response.getBody().getNome());
    }
}
