package br.edu.ifsp.ptb.ra.exame.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.edu.ifsp.ptb.ra.exame.dto.EventoDTO;
import br.edu.ifsp.ptb.ra.exame.dto.ExameDTO;
import br.edu.ifsp.ptb.ra.exame.dto.QuadroPaDTO;

@Component
public class RelacionadorEventoAfericaoService
{
    public QuadroPaDTO relaciona(QuadroPaDTO quadroPa, ExameDTO exame)
    {
        List<EventoDTO> eventos = exame.getEventos();
        Integer margemEncaixeEvento = configuraMargemEncaixeEvento(exame.getIntervaloAfericoes());

        quadroPa.getAfericoes().forEach(q ->

            eventos.forEach(e ->
            {
                Long diff = e.getDiffBetween(q.getTimestamp());

                if (diff >= -margemEncaixeEvento && diff <= margemEncaixeEvento)
                {
                    q.getEventoAssociado().add(e);
                }

            })

        );

        return quadroPa;
    }

    private Integer configuraMargemEncaixeEvento(Integer intervaloAfericoes)
    {
        return Map.of(
                15, 450,
                30, 900
        ).get(intervaloAfericoes);
    }
}
