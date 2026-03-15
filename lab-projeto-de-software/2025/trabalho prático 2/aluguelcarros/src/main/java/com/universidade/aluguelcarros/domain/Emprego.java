package com.universidade.aluguelcarros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "empregos")
public class Emprego extends Entidade {

    @Size(max = 255)
    @Column(name = "entidade_empregadora", length = 255)
    private String entidadeEmpregadora;

    @Size(max = 100)
    @Column(length = 100)
    private String cargo;

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    public String getEntidadeEmpregadora() { return entidadeEmpregadora; }
    public void setEntidadeEmpregadora(String entidadeEmpregadora) { this.entidadeEmpregadora = entidadeEmpregadora; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(LocalDate dataAdmissao) { this.dataAdmissao = dataAdmissao; }
}
