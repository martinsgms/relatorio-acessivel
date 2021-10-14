package br.edu.ifsp.ptb.ra.gatewayserver.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.gatewayserver.model.UsuarioModel;
import br.edu.ifsp.ptb.ra.gatewayserver.service.UsuarioService;

@Service
public class AuthService implements UserDetailsService
{
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Optional<UsuarioModel> user = usuarioService.buscaUsuarioPorEmail(email);

        if (!user.isPresent())
            throw new UsernameNotFoundException("Usuário/Senha inválido");

        return user.get();
    }
}
