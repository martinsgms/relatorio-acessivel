package br.edu.ifsp.ptb.ra.exame.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.ptb.ra.exame.model.EventoModel;

@JsonInclude(Include.NON_NULL)
public class EventoDTO 
{
    private Long id;
    private Long idExame;
    private String descricao;
    private String sintoma;
    private String medicamento;
    private LocalDateTime dataHora;
    private String horaFormatada;

    public EventoDTO()
    {
    }

    public EventoDTO(EventoModel model)
    {
        this.idExame = model.getExame().getId();
        BeanUtils.copyProperties(model, this);
    }

    public Long getIdExame()
    {
        return idExame;
    }

    public void setIdExame(Long idExame)
    {
        this.idExame = idExame;
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

    public String getMedicamento()
    {
        return medicamento;
    }

    public void setMedicamento(String medicamento)
    {
        this.medicamento = medicamento;
    }

    public LocalDateTime getDataHora()
    {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora)
    {
        this.dataHora = dataHora;
    }

    public String getHoraFormatada()
    {
        return horaFormatada;
    }

    public void setHoraFormatada(String horaFormatada)
    {
        this.horaFormatada = horaFormatada;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
