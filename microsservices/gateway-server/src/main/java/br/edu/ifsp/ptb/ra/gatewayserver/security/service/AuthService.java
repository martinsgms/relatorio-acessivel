package br.edu.ifsp.ptb.ra.gatewayserver.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.gatewayserver.dto.AuthRequestDTO;
import br.edu.ifsp.ptb.ra.gatewayserver.dto.AuthTokenDTO;
import br.edu.ifsp.ptb.ra.gatewayserver.service.UsuarioService;
import reactor.core.publisher.Mono;

@Service
public class AuthService
{
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Mono<ResponseEntity<AuthTokenDTO>> authenticate(AuthRequestDTO dto)
    {
        return usuarioService.findByEmail(dto.getEmail())
                .filter(userDetails -> passwordEncoder.matches(dto.getSenha(), userDetails.getPassword()))
                .map(userDetails ->
                {
                    String token = jwtService.generateToken(userDetails);
                    Long userId = jwtService.getUserIdFromToken(token);

                    return ResponseEntity.ok(new AuthTokenDTO(token, userId));
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
