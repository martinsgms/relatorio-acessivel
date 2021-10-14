package br.edu.ifsp.ptb.ra.gatewayserver.security.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.ifsp.ptb.ra.gatewayserver.model.UsuarioModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService
{
    @Value("${ra.jwt.expiration}")
    private String expiration;

    @Value("${ra.jwt.secret}")
    private String secret;

    public String generateToken(UsuarioModel principal)
    {
        return Jwts.builder()
                .setIssuer("RA Server")
                .setSubject(principal.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + Long.valueOf(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValidToken(String token)
    {
        return !isTokenExpired(token);
    }

    public Long getUserIdFromToken(String token)
    {
        return Long.valueOf(getClaims(token).getSubject());
    }

    public Date getExpirationDate(String token)
    {
        return getClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token)
    {
        return getExpirationDate(token).before(new Date());
    }

    private Claims getClaims(String token)
    {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }
}