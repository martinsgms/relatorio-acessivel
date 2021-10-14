package br.edu.ifsp.ptb.ra.gatewayserver.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.ptb.ra.gatewayserver.dto.LoginDTO;
import br.edu.ifsp.ptb.ra.gatewayserver.dto.TokenDTO;

@RestController
@RequestMapping("/login")
public class AuthController
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> doAuth(@RequestBody @Valid LoginDTO form)
    {
        UsernamePasswordAuthenticationToken authData = form.covert();

        try
        {
            Authentication authenticate = authenticationManager.authenticate(authData);
            String token = tokenService.generate(authenticate);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));

        } catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
