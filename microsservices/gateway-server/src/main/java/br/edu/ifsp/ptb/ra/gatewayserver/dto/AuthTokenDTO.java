package br.edu.ifsp.ptb.ra.gatewayserver.dto;

public class AuthTokenDTO
{
    private Long userId;
    private String token;
    private String tipo;

    public AuthTokenDTO(String token, Long userId)
    {
        this.token = token;
        this.userId = userId;
        this.tipo = "Bearer ";
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
}
