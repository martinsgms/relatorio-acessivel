package br.edu.ifsp.ptb.ra.gatewayserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.gatewayserver.model.UsuarioModel;
import br.edu.ifsp.ptb.ra.gatewayserver.repository.UsuarioRepository;

@Service
public class UsuarioService
{
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<UsuarioModel> buscaUsuarioPorEmail(String email)
    {
        Optional<UsuarioModel> optionalUsuarioModel = usuarioRepository.findByEmail(email);

        return optionalUsuarioModel;
    }

}
