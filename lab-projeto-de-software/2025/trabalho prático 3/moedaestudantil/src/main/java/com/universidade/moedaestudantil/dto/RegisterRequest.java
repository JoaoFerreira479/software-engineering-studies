package com.universidade.moedaestudantil.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "Tipo é obrigatório (ALUNO, PROFESSOR ou EMPRESA)") String tipo,
    @NotBlank(message = "Nome é obrigatório") @Size(max = 255) String nome,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres") String senha,
    String cnpj,
    String cpf,
    String rg,
    String endereco,
    String curso,
    String departamento,
    Long instituicaoId
) {
    public String tipoNormalizado() {
        return tipo == null ? "" : tipo.toUpperCase().trim();
    }
}
