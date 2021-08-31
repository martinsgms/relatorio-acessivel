package br.edu.ifsp.ptb.ra.exame.dto;

import java.time.LocalDateTime;

public class ExameDTO 
{
    private String email;
    private LocalDateTime data;
    private String idExterno;
    private Integer intervaloAfericaoPA;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public Integer getIntervaloAfericaoPA() {
        return intervaloAfericaoPA;
    }

    public void setIntervaloAfericaoPA(Integer intervaloAfericaoPA) {
        this.intervaloAfericaoPA = intervaloAfericaoPA;
    }
}
