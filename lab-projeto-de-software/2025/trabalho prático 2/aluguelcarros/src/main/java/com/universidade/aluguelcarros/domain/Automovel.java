package com.universidade.aluguelcarros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "automoveis")
public class Automovel extends Entidade {

    @NotNull
    @Column(nullable = false, unique = true)
    private Integer matricula;

    @NotNull
    @Column(nullable = false)
    private Integer ano;

    @NotBlank(message = "Marca é obrigatória")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String marca;

    @NotBlank(message = "Modelo é obrigatório")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String modelo;

    @NotBlank(message = "Placa é obrigatória")
    @Size(max = 10)
    @Column(nullable = false, unique = true, length = 10)
    private String placa;

    public Integer getMatricula() { return matricula; }
    public void setMatricula(Integer matricula) { this.matricula = matricula; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
}
