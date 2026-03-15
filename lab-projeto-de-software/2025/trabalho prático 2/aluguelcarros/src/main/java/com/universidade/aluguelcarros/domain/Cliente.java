package com.universidade.aluguelcarros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente extends Entidade {

    @NotBlank(message = "CPF é obrigatório")
    @Size(max = 14)
    @Column(nullable = false, length = 14)
    private String cpf;

    @Size(max = 20)
    @Column(length = 20)
    private String rg;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255)
    @Column(nullable = false)
    private String nome;

    @Size(max = 500)
    @Column(length = 500)
    private String endereco;

    @Size(max = 100)
    @Column(length = 100)
    private String profissao;

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getProfissao() { return profissao; }
    public void setProfissao(String profissao) { this.profissao = profissao; }
}
