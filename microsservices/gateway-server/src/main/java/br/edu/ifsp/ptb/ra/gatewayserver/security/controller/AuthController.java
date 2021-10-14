package br.edu.ifsp.ptb.ra.gatewayserver.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.ptb.ra.gatewayserver.dto.AuthRequestDTO;
import br.edu.ifsp.ptb.ra.gatewayserver.dto.AuthTokenDTO;
import br.edu.ifsp.ptb.ra.gatewayserver.security.service.AuthService;
import reactor.core.publisher.Mono;

@RestController
public class AuthController
{
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthTokenDTO>> authenticate(@RequestBody AuthRequestDTO authRequestDTO)
    {
        return authService.authenticate(authRequestDTO);
    }
}
