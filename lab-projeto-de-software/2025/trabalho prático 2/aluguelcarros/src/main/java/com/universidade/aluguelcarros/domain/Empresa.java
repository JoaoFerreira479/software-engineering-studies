package com.universidade.aluguelcarros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "empresas")
public class Empresa extends Entidade {

    @Size(max = 100)
    @Column(length = 100)
    private String segmento;

    public String getSegmento() { return segmento; }
    public void setSegmento(String segmento) { this.segmento = segmento; }
}
