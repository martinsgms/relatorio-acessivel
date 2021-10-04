package br.edu.ifsp.ptb.ra.exame.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AfericaoPaDTO
{

    private Integer pas;
    private Integer pad;
    private LocalDateTime timestamp;
    private List<EventoDTO> eventoAssociado = new ArrayList<>();

    public Integer getPas()
    {
        return pas;
    }

    public void setPas(Integer pas)
    {
        this.pas = pas;
    }

    public Integer getPad()
    {
        return pad;
    }

    public void setPad(Integer pad)
    {
        this.pad = pad;
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    public List<EventoDTO> getEventoAssociado()
    {
        return eventoAssociado;
    }

    public void setEventoAssociado(List<EventoDTO> eventoAssociado)
    {
        this.eventoAssociado = eventoAssociado;
    }
}
