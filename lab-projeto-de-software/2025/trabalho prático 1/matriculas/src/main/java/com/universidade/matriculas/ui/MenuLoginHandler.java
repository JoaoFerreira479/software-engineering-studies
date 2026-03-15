package com.universidade.matriculas.ui;

import com.universidade.matriculas.infrastructure.io.Console;
import com.universidade.matriculas.infrastructure.io.EntradaUsuario;
import com.universidade.matriculas.service.UsuarioService;

final class MenuLoginHandler {

	private final EntradaUsuario entrada;
	private final UsuarioService usuarioService;

	MenuLoginHandler(EntradaUsuario entrada, UsuarioService usuarioService) {
		this.entrada = entrada;
		this.usuarioService = usuarioService;
	}

	ResultadoLogin executar() {
		Console.println("\n--- MENU DE ACESSO ---");
		Console.println("[1] Fazer Login");
		Console.println("[2] Sair do Sistema");
		String opcao = entrada.lerLinha("Escolha uma opção: ");

		return switch (opcao) {
			case "1" -> fazerLogin();
			case "2" -> {
				Console.println("Até logo!");
				yield ResultadoLogin.sair();
			}
			default -> {
				Console.println("Opção inválida.");
				yield ResultadoLogin.continuar();
			}
		};
	}

	private ResultadoLogin fazerLogin() {
		String email = entrada.lerLinha("Email: ");
		String senha = entrada.lerLinha("Senha: ");

		return usuarioService.login(email, senha)
				.map(u -> {
					Console.println("\nLogin bem-sucedido! Bem-vindo(a), " + u.getNome());
					return ResultadoLogin.logado(u);
				})
				.orElseGet(() -> {
					Console.println("Email ou senha inválidos.");
					return ResultadoLogin.continuar();
				});
	}
}
