package br.edu.ifsp.ptb.ra.exame.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.ptb.ra.exame.model.StatusExameModel;

@JsonInclude(Include.NON_NULL)
public class StatusExameDTO
{
    private String codigo;
    private String descricao;
    private boolean permiteEscrita;

    public StatusExameDTO(StatusExameModel model)
    {
        codigo = model.getCodigo();
        descricao = model.getDescricao();
        permiteEscrita = model.isPermiteEscrita();
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public boolean isPermiteEscrita()
    {
        return permiteEscrita;
    }

    public void setPermiteEscrita(boolean permiteEscrita)
    {
        this.permiteEscrita = permiteEscrita;
    }
}
