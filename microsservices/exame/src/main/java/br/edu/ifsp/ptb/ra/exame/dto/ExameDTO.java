package br.edu.ifsp.ptb.ra.exame.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.ptb.ra.exame.model.ExameModel;

@JsonInclude(Include.NON_NULL)
public class ExameDTO 
{
    private Long id;
    private String email;
    private Long usuario;
    private LocalDateTime timestampExame;
    private String idExterno;
    private Integer intervaloAfericoes;
    private FormatosDataHoraDTO formatosDataHora;
    private StatusExameDTO status;

    public ExameDTO()
    {
    }

    public ExameDTO(ExameModel model)
    {
        status = new StatusExameDTO(model.getStatus());
        formatosDataHora = new FormatosDataHoraDTO(model.getTimestampExame());
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Long getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Long usuario)
    {
        this.usuario = usuario;
    }

    public LocalDateTime getTimestampExame()
    {
        return timestampExame;
    }

    public void setTimestampExame(LocalDateTime timestampExame)
    {
        this.timestampExame = timestampExame;
    }

    public String getIdExterno()
    {
        return idExterno;
    }

    public void setIdExterno(String idExterno)
    {
        this.idExterno = idExterno;
    }

    public FormatosDataHoraDTO getFormatosDataHora()
    {
        return formatosDataHora;
    }

    public void setFormatosDataHora(FormatosDataHoraDTO formatosDataHora)
    {
        this.formatosDataHora = formatosDataHora;
    }

    public Integer getIntervaloAfericoes()
    {
        return intervaloAfericoes;
    }

    public void setIntervaloAfericoes(Integer intervaloAfericoes)
    {
        this.intervaloAfericoes = intervaloAfericoes;
    }

    public StatusExameDTO getStatus()
    {
        return status;
    }

    public void setStatus(StatusExameDTO status)
    {
        this.status = status;
    }
}
