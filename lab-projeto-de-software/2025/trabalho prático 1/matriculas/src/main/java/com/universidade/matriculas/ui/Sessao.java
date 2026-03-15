package com.universidade.matriculas.ui;

import java.util.Optional;

import com.universidade.matriculas.model.Usuario;

public final class Sessao {

	private static final Sessao VAZIA = new Sessao(null);

	private final Usuario usuario;

	private Sessao(Usuario usuario) {
		this.usuario = usuario;
	}

	public static Sessao vazia() {
		return VAZIA;
	}

	public static Sessao de(Usuario usuario) {
		if (usuario == null) {
			return VAZIA;
		}
		return new Sessao(usuario);
	}

	public Optional<Usuario> getUsuario() {
		return Optional.ofNullable(usuario);
	}

	public boolean estaLogado() {
		return usuario != null;
	}
}
