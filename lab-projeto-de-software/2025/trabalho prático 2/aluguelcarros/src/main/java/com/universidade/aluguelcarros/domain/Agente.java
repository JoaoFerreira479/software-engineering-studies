package com.universidade.aluguelcarros.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "agentes")
public class Agente extends Entidade {

    @NotBlank(message = "Razão social é obrigatória")
    @Size(max = 255)
    @Column(nullable = false, name = "razao_social")
    private String razaoSocial;

    @NotBlank(message = "CNPJ é obrigatório")
    @Size(max = 18)
    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}
