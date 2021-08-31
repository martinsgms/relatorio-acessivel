package br.edu.ifsp.ptb.ra.exame.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_SERVICO_SAUDE")
//    private ServicoSaudeModel servicoSaude;

//    @ManyToOne
//    @JoinColumn(name = "CD_STATUS")
//    private StatusExameModel status;

    @Column(name = "DH_EXAME")
    private LocalDateTime dataHoraExame;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_PACIENTE")
//    private PacienteModel paciente;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "exame")
//    private List<EventoModel> eventos;

    @Column(name = "NU_INTERVALO_AFERICAO")
    private Integer intervaloAfericoes;

}