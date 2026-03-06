package com.universidade.aluguelcarros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "rendimentos")
public class Rendimento extends Entidade {

    @NotNull(message = "Valor é obrigatório")
    @Column(nullable = false)
    private Double valor;

    @Size(max = 100)
    @Column(length = 100)
    private String fonte;

    @Size(max = 20)
    @Column(length = 20)
    private String competencia;

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public String getFonte() { return fonte; }
    public void setFonte(String fonte) { this.fonte = fonte; }

    public String getCompetencia() { return competencia; }
    public void setCompetencia(String competencia) { this.competencia = competencia; }
}
