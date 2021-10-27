package br.edu.ifsp.ptb.ra.exame.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;

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
    private LocalDateTime timestampEvento;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EXAME")
    private ExameModel exame;

    public EventoModel()
    {
    }

    public EventoModel(EventoDTO dto)
    {
        exame = new ExameModel(dto.getIdExame());
        BeanUtils.copyProperties(dto, this);
    }

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

    public LocalDateTime getTimestampEvento()
    {
        return timestampEvento;
    }

    public void setTimestampEvento(LocalDateTime timestampEvento)
    {
        this.timestampEvento = timestampEvento;
    }

    public ExameModel getExame()
    {
        return exame;
    }

    public void setExame(ExameModel exame) {
        this.exame = exame;
    }

    public String getMedicamento()
    {
        return medicamento;
    }

    public void setMedicamento(String medicamento)
    {
        this.medicamento = medicamento;
    }
}