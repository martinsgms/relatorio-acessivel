package br.edu.ifsp.ptb.ra.exame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRA_STATUS_EXAME")
public class StatusExameModel
{

    public StatusExameModel() {
    }

    public StatusExameModel(String codigo) {
        this.codigo = codigo;
    }

    public StatusExameModel(String codigo, String descricao)
    {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    @Id
    @Column(name = "CD_STATUS")
    private String codigo;

    @Column(name = "DS_DESCRICAO")
    private String descricao;

    @Column(name = "IN_PERMITE_ESCRITA")
    private boolean permiteEscrita;
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isPermiteEscrita() {
        return permiteEscrita;
    }

    public void setPermiteEscrita(boolean permiteEscrita) {
        this.permiteEscrita = permiteEscrita;
    }
}
