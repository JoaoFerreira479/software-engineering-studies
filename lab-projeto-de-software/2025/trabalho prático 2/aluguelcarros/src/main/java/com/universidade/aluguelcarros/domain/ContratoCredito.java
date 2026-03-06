package com.universidade.aluguelcarros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contratos_credito")
public class ContratoCredito extends Entidade {

    @NotNull(message = "Valor total é obrigatório")
    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Column(name = "taxa_juros_anual")
    private Double taxaJurosAnual;

    @NotNull(message = "Número de parcelas é obrigatório")
    @Column(nullable = false)
    private Integer parcelas;

    @Size(max = 50)
    @Column(length = 50)
    private String status;

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public Double getTaxaJurosAnual() { return taxaJurosAnual; }
    public void setTaxaJurosAnual(Double taxaJurosAnual) { this.taxaJurosAnual = taxaJurosAnual; }

    public Integer getParcelas() { return parcelas; }
    public void setParcelas(Integer parcelas) { this.parcelas = parcelas; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
