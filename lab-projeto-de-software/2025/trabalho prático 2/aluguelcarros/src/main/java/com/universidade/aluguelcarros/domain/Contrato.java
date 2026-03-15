package com.universidade.aluguelcarros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "contratos")
public class Contrato extends Entidade {

    @Size(max = 50)
    @Column(length = 50)
    private String status;

    @NotNull
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @NotNull
    @Column(name = "valor_mensal", nullable = false)
    private Double valorMensal;

    @Size(max = 50)
    @Column(name = "propriedade_final", length = 50)
    private String propriedadeFinal;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public Double getValorMensal() { return valorMensal; }
    public void setValorMensal(Double valorMensal) { this.valorMensal = valorMensal; }

    public String getPropriedadeFinal() { return propriedadeFinal; }
    public void setPropriedadeFinal(String propriedadeFinal) { this.propriedadeFinal = propriedadeFinal; }
}
