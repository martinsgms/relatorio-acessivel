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
    private LocalDateTime timestampEvento;
    private FormatosDataHoraDTO formatosDataHora;

    public EventoDTO()
    {
    }

    public EventoDTO(EventoModel model)
    {
        this.idExame = model.getExame().getId();
        formatosDataHora = new FormatosDataHoraDTO(model.getTimestampEvento());
        BeanUtils.copyProperties(model, this);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public LocalDateTime getTimestampEvento()
    {
        return timestampEvento;
    }

    public void setTimestampEvento(LocalDateTime timestampEvento)
    {
        this.timestampEvento = timestampEvento;
    }

    public FormatosDataHoraDTO getFormatosDataHora()
    {
        return formatosDataHora;
    }

    public void setFormatosDataHora(FormatosDataHoraDTO formatosDataHora)
    {
        this.formatosDataHora = formatosDataHora;
    }
}
