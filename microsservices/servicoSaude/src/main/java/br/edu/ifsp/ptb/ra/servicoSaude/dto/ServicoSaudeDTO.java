package br.edu.ifsp.ptb.ra.servicoSaude.dto;

import org.springframework.beans.BeanUtils;

import br.edu.ifsp.ptb.ra.servicoSaude.model.ServicoSaudeModel;

public class ServicoSaudeDTO
{

    private Long id;
    private String nomeCompleto;
    private String nome;
    private String cnpj;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String lote;
    private String linkMaps;

    public ServicoSaudeDTO(ServicoSaudeModel model)
    {
        BeanUtils.copyProperties(model, this);
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
}
