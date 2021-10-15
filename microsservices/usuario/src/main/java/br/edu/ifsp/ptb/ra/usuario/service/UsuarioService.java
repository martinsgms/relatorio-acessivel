package br.edu.ifsp.ptb.ra.usuario.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.usuario.dto.UsuarioDTO;
import br.edu.ifsp.ptb.ra.usuario.model.UsuarioModel;
import br.edu.ifsp.ptb.ra.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioDTO buscaUsuarioPorEmail(String chave)
    {
        LOGGER.info("buscando email: {}", chave);

        return new UsuarioDTO(usuarioRepository.findByEmail(chave).orElse(new UsuarioModel()));
    }

    public UsuarioDTO findById(Long id)
    {
        return new UsuarioDTO(usuarioRepository.findById(id).orElse(new UsuarioModel()));
    }

    public void cadastraUsuario(UsuarioDTO usuario)
    {
        usuarioRepository.save(new UsuarioModel(usuario));
    }

    public void atualizaSenha(UsuarioDTO dto) throws Exception
    {
        Optional<UsuarioModel> optUsuarioModel = usuarioRepository.findById(dto.getId());

        if (!optUsuarioModel.isPresent())
        {
            throw new Exception("Usuário inexistente");
        }

        UsuarioModel usuarioModel = optUsuarioModel.get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(dto.getSenha(), usuarioModel.getSenha()))
        {
            throw new Exception("As senhas não coincidem");
        }

        usuarioModel.setSenha(encoder.encode(dto.getNovaSenha()));
        usuarioRepository.save(usuarioModel);
    }
}