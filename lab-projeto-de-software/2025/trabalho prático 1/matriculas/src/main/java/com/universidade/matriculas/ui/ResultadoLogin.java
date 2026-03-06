package com.universidade.matriculas.ui;

import java.util.Optional;

import com.universidade.matriculas.model.Usuario;

public record ResultadoLogin(AcaoLogin acao, Optional<Usuario> usuario) {

	public enum AcaoLogin {
		CONTINUAR,
		SAIR
	}

	public static ResultadoLogin continuar() {
		return new ResultadoLogin(AcaoLogin.CONTINUAR, Optional.empty());
	}

	public static ResultadoLogin sair() {
		return new ResultadoLogin(AcaoLogin.SAIR, Optional.empty());
	}

	public static ResultadoLogin logado(Usuario usuario) {
		return new ResultadoLogin(AcaoLogin.CONTINUAR, Optional.of(usuario));
	}
}
