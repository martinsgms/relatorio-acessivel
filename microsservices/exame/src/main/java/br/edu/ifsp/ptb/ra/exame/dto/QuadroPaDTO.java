package br.edu.ifsp.ptb.ra.exame.dto;

import java.util.ArrayList;
import java.util.List;

public class QuadroPaDTO
{
    private List<AfericaoPaDTO> afericoes = new ArrayList<>();

    public List<AfericaoPaDTO> getAfericoes()
    {
        return afericoes;
    }

    public void setAfericoes(List<AfericaoPaDTO> afericoes)
    {
        this.afericoes = afericoes;
    }
}
