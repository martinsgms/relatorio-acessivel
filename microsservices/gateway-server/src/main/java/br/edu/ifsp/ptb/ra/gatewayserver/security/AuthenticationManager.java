package br.edu.ifsp.ptb.ra.gatewayserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.edu.ifsp.ptb.ra.gatewayserver.security.service.JwtService;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager
{
    @Autowired
    private JwtService jwtService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication)
    {
        String authToken = authentication.getCredentials().toString();
        Long userId = jwtService.getUserIdFromToken(authToken);

        return Mono.just(jwtService.isValidToken(authToken))
                .filter(valid -> valid)
                .switchIfEmpty(Mono.empty())
                .map(valid -> new UsernamePasswordAuthenticationToken(userId, null, null));
    }
}