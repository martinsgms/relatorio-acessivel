package br.edu.ifsp.ptb.ra.servicoSaude.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRA_SERVICO_SAUDE")
public class ServicoSaudeModel
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NM_COMPLETO")
    private String nomeCompleto;

    @Column(name = "NM_CURTO")
    private String nome;

    @Column(name = "NU_CNPJ")
    private String cnpj;

    @Column(name = "TE_CEP")
    private String cep;

    @Column(name = "NM_LOGRADOURO")
    private String logradouro;

    @Column(name = "NM_BAIRRO")
    private String bairro;

    @Column(name = "NM_CIDADE")
    private String cidade;

    @Column(name = "NM_ESTADO")
    private String estado;

    @Column(name = "NU_NUMERO_LOTE")
    private String lote;

    @Column(name = "TE_LINK_MAPS")
    private String linkMaps;

    @Column(name = "TE_LINK_WHATS")
    private String linkWhats;

    @Column(name = "DH_CADASTRO")
    private String dtCadastro;

    public ServicoSaudeModel()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNomeCompleto()
    {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto)
    {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public String getLogradouro()
    {
        return logradouro;
    }

    public void setLogradouro(String logradouro)
    {
        this.logradouro = logradouro;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public String getLote()
    {
        return lote;
    }

    public void setLote(String lote)
    {
        this.lote = lote;
    }

    public String getLinkMaps()
    {
        return linkMaps;
    }

    public void setLinkMaps(String linkMaps)
    {
        this.linkMaps = linkMaps;
    }

    public String getLinkWhats()
    {
        return linkWhats;
    }

    public void setLinkWhats(String linkWhats)
    {
        this.linkWhats = linkWhats;
    }

    public String getDtCadastro()
    {
        return dtCadastro;
    }

    public void setDtCadastro(String dtCadastro)
    {
        this.dtCadastro = dtCadastro;
    }

}
