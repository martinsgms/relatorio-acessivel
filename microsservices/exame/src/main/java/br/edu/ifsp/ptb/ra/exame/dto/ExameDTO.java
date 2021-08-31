package br.edu.ifsp.ptb.ra.exame.dto;

import java.time.LocalDateTime;

public class ExameDTO 
{
    private String email;
    private LocalDateTime data;

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
}
