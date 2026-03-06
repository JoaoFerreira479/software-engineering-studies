package com.universidade.aluguelcarros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "bancos")
public class Banco extends Entidade {

    @NotBlank(message = "Código do banco é obrigatório")
    @Size(max = 10)
    @Column(nullable = false, unique = true, name = "codigo_banco", length = 10)
    private String codigoBanco;

    public String getCodigoBanco() { return codigoBanco; }
    public void setCodigoBanco(String codigoBanco) { this.codigoBanco = codigoBanco; }
}
