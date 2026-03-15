package com.universidade.moedaestudantil.dto;

import com.universidade.moedaestudantil.domain.TipoUsuario;

public record AuthResponse(
    String token,
    Long id,
    TipoUsuario tipo,
    String nome,
    String email
) {
    public String getTipo() {
        return tipo != null ? tipo.name() : TipoUsuario.USER.name();
    }
}
