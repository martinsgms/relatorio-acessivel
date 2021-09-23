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
    private LocalDateTime data;
    private String idExterno;
    private LocalDateTime dataHoraExame;
    private String dataHoraFormatada;
    private Integer intervaloAfericoes;
    private StatusExameDTO status;

    public ExameDTO()
    {
    }

    public ExameDTO(ExameModel model)
    {
        status = new StatusExameDTO(model.getStatus());
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

    public StatusExameDTO getStatus()
    {
        return status;
    }

    public void setStatus(StatusExameDTO status)
    {
        this.status = status;
    }

    public LocalDateTime getDataHoraExame()
    {
        return dataHoraExame;
    }

    public void setDataHoraExame(LocalDateTime dataHoraExame)
    {
        this.dataHoraExame = dataHoraExame;
    }

    public Integer getIntervaloAfericoes()
    {
        return intervaloAfericoes;
    }

    public void setIntervaloAfericoes(Integer intervaloAfericoes)
    {
        this.intervaloAfericoes = intervaloAfericoes;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public LocalDateTime getData()
    {
        return data;
    }

    public void setData(LocalDateTime data)
    {
        this.data = data;
    }

    public String getIdExterno()
    {
        return idExterno;
    }

    public void setIdExterno(String idExterno)
    {
        this.idExterno = idExterno;
    }

    public Long getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Long usuario)
    {
        this.usuario = usuario;
    }

    public String getDataHoraFormatada()
    {
        return dataHoraFormatada;
    }

    public void setDataHoraFormatada(String dataHoraFormatada)
    {
        this.dataHoraFormatada = dataHoraFormatada;
    }
}
