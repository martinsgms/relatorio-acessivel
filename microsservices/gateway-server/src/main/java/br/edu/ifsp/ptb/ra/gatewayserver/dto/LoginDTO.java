package br.edu.ifsp.ptb.ra.gatewayserver.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDTO
{
    private String email;
    private String senha;

    public UsernamePasswordAuthenticationToken covert()
    {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }
}
