package br.edu.ifsp.ptb.ra.exame.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import br.edu.ifsp.ptb.ra.exame.dto.ExameDTO;
import br.edu.ifsp.ptb.ra.exame.dto.UsuarioDTO;

@Entity
@Table(name = "TRA_EXAME")
public class ExameModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_EXTERNO")
    private String idExterno;

    @ManyToOne
    @JoinColumn(name = "CD_STATUS")
    private StatusExameModel status;

    @Column(name = "DH_EXAME")
    private LocalDateTime timestampExame;

    @Column(name = "ID_USUARIO")
    private Long usuario;

    @Column(name = "ID_SERVICO_SAUDE")
    private Long idServicoSaude;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exame")
    private List<EventoModel> eventos;

    @Column(name = "NU_INTERVALO_AFERICAO")
    private Integer intervaloAfericoes;

    public ExameModel()
    {
    }

    public ExameModel(Long idExame)
    {
        this.id = idExame;
    }

    public ExameModel(UsuarioDTO usuario, ExameDTO dto)
    {
        BeanUtils.copyProperties(dto, this);
        this.usuario = usuario.getId();
        this.status = new StatusExameModel("AGE");
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getIdExterno()
    {
        return idExterno;
    }

    public void setIdExterno(String idExterno)
    {
        this.idExterno = idExterno;
    }

    public StatusExameModel getStatus()
    {
        return status;
    }

    public void setStatus(StatusExameModel status)
    {
        this.status = status;
    }

    public LocalDateTime getTimestampExame()
    {
        return timestampExame;
    }

    public void setTimestampExame(LocalDateTime dataHoraExame)
    {
        this.timestampExame = dataHoraExame;
    }

    public Long getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Long usuario)
    {
        this.usuario = usuario;
    }

    public List<EventoModel> getEventos()
    {
        return eventos;
    }

    public void setEventos(List<EventoModel> eventos)
    {
        this.eventos = eventos;
    }

    public Integer getIntervaloAfericoes()
    {
        return intervaloAfericoes;
    }

    public void setIntervaloAfericoes(Integer intervaloAfericoes)
    {
        this.intervaloAfericoes = intervaloAfericoes;
    }

    public Long getIdServicoSaude()
    {
        return idServicoSaude;
    }

    public void setIdServicoSaude(Long idServicoSaude)
    {
        this.idServicoSaude = idServicoSaude;
    }
}