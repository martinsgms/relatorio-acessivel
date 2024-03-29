package br.edu.ifsp.ptb.ra.usuario.dto;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.ptb.ra.usuario.model.UsuarioModel;

@JsonInclude(Include.NON_NULL)
public class UsuarioDTO
{
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String novaSenha;

    public UsuarioDTO()
    {
    }

    public UsuarioDTO(UsuarioModel model)
    {
        BeanUtils.copyProperties(model, this);
        senha = null;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
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

    public String getNovaSenha()
    {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha)
    {
        this.novaSenha = novaSenha;
    }
}

