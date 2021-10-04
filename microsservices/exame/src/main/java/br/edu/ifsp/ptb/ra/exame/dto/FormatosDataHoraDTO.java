package br.edu.ifsp.ptb.ra.exame.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FormatosDataHoraDTO
{
    @JsonIgnore
    private LocalDateTime timestamp;

    private String dataHora;
    private String data;
    private String hora;
    private String semanaDiaMesAnoExtenso;
    private String semanaDiaMesAnoHoraExtenso;

    public FormatosDataHoraDTO(LocalDateTime timestamp)
    {
        setTimestamp(timestamp);
        setData();
        setHora();
        setDataHora();
        setSemanaDiaMesAnoExtenso();
        setSemanaDiaMesAnoHoraExtenso();
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getDataHora()
    {
        return dataHora;
    }

    public void setDataHora()
    {
        this.dataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy à's' HH:mm").format(timestamp);
    }

    public String getData()
    {
        return data;
    }

    public void setData()
    {
        this.data = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(timestamp);
    }

    public String getSemanaDiaMesAnoExtenso()
    {
        return semanaDiaMesAnoExtenso;
    }

    public void setSemanaDiaMesAnoExtenso()
    {
        this.semanaDiaMesAnoExtenso = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy", new Locale ("pt", "BR")).format(timestamp);
    }

    public String getSemanaDiaMesAnoHoraExtenso()
    {
        return semanaDiaMesAnoHoraExtenso;
    }

    public void setSemanaDiaMesAnoHoraExtenso()
    {
        this.semanaDiaMesAnoHoraExtenso = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy 'às' HH:mm", new Locale ("pt", "BR")).format(timestamp);
    }

    public String getHora()
    {
        return hora;
    }

    public void setHora()
    {
        this.hora = DateTimeFormatter.ofPattern("HH:mm").format(timestamp);
    }
}
