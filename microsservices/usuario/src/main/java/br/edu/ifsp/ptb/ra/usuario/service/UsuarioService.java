package br.edu.ifsp.ptb.ra.usuario.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.usuario.model.UsuarioModel;
import br.edu.ifsp.ptb.ra.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioModel buscaUsuarioPorEmail(String chave)
    {
        LOGGER.info("buscando email: {}", chave);

        return usuarioRepository.findByEmail(chave);
    }

}
