package br.edu.ifsp.ptb.ra.exame.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.ptb.ra.exame.util.DateTimeUtils;

@JsonInclude(Include.NON_NULL)
public class QuadroPaDTO
{
    private String idExternoExame;
    private String nomeServicoSaude;
    private String nomeCompletoServicoSaude;
    private String nomePaciente;
    private String dataHoraInstalacao;
    private List<AfericaoPaDTO> afericoes = new ArrayList<>();

    public String getIdExternoExame()
    {
        return idExternoExame;
    }

    public void setIdExternoExame(String idExternoExame)
    {
        this.idExternoExame = idExternoExame;
    }

    public String getNomeServicoSaude()
    {
        return nomeServicoSaude;
    }

    public void setNomeServicoSaude(String nomeServicoSaude)
    {
        this.nomeServicoSaude = nomeServicoSaude;
    }

    public String getNomeCompletoServicoSaude()
    {
        return nomeCompletoServicoSaude;
    }

    public void setNomeCompletoServicoSaude(String nomeCompletoServicoSaude)
    {
        this.nomeCompletoServicoSaude = nomeCompletoServicoSaude;
    }

    public String getNomePaciente()
    {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente)
    {
        this.nomePaciente = nomePaciente;
    }

    public List<AfericaoPaDTO> getAfericoes()
    {
        return afericoes;
    }

    public void setAfericoes(List<AfericaoPaDTO> afericoes)
    {
        this.afericoes = afericoes;
    }

    public String getDataHoraInstalacao()
    {
        return dataHoraInstalacao;
    }

    public void setDataHoraInstalacao(String dataHoraInstalacao)
    {
        this.dataHoraInstalacao = dataHoraInstalacao;
    }

    @JsonIgnore
    private LocalDateTime getDataHoraInstalacaoAsTimestamp()
    {
        return getAfericoes().get(0).getTimestamp();
    }

    @JsonIgnore
    public String getFileName()
    {
        return DateTimeUtils.getAnoMesDiaFilename(getDataHoraInstalacaoAsTimestamp())
                + "-" + nomeServicoSaude.toUpperCase()
                + "-MAPA-"
                + idExternoExame
                + "-DIARIO_ATIVIDADES";
    }
}
