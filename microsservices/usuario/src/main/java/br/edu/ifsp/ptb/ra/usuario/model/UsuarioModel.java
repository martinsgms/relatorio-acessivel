package br.edu.ifsp.ptb.ra.usuario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifsp.ptb.ra.usuario.dto.UsuarioDTO;

@Entity
@Table(name = "TRA_USUARIO")
public class UsuarioModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NM_NOME")
    private String nome;

    @Column(name = "TE_EMAIL")
    private String email;

    @Column(name = "TE_SENHA")
    private String senha;

    public UsuarioModel()
    {
    }

    public UsuarioModel(UsuarioDTO dto)
    {
        BeanUtils.copyProperties(dto, this);
        senha = new BCryptPasswordEncoder().encode(dto.getSenha());
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
}
