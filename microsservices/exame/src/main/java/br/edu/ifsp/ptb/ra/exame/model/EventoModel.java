package br.edu.ifsp.ptb.ra.exame.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TRA_EVENTO")
public class EventoModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DS_EVENTO")
    private String descricao;

    @Column(name = "DS_SINTOMA")
    private String sintoma;

    @Column(name = "DS_MEDICAMENTO")
    private String medicamento;

    @Column(name = "DH_EVENTO")
    private LocalDateTime dataHora;

    @Column(name = "NU_PA_SISTOLICA")
    private Integer paSistolica;

    @Column(name = "NU_PA_DIASTOLICA")
    private Integer paDiastolica;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EXAME")
    private ExameModel exame;

    @Transient
    private String horaFormatada;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getSintoma()
    {
        return sintoma;
    }

    public void setSintoma(String sintoma)
    {
        this.sintoma = sintoma;
    }

    public LocalDateTime getDataHora()
    {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora)
    {
        this.dataHora = dataHora;
        setHoraFormatada();
    }

    public Integer getPaSistolica()
    {
        return paSistolica;
    }

    public void setPaSistolica(Integer paSistolica)
    {
        this.paSistolica = paSistolica;
    }

    public Integer getPaDiastolica()
    {
        return paDiastolica;
    }

    public void setPaDiastolica(Integer paDiastolica)
    {
        this.paDiastolica = paDiastolica;
    }

    public ExameModel getExame()
    {
        return exame;
    }

    public void setExame(ExameModel exame) {
        this.exame = exame;
    }

    public String getHoraFormatada()
    {
        return horaFormatada;
    }

    public void setHoraFormatada()
    {
        this.horaFormatada = DateTimeFormatter.ofPattern("HH:mm").format(dataHora);
    }
}