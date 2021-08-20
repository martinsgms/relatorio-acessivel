package br.edu.ifsp.ptb.ra.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.usuario.model.UsuarioModel;
import br.edu.ifsp.ptb.ra.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioModel resgataUsuarioPorEmail(String chave) {
        return usuarioRepository.findByEmail(chave);
    }

}
