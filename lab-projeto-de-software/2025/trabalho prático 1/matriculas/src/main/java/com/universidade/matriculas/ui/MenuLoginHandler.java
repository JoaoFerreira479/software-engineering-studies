package com.universidade.matriculas.ui;

import com.universidade.matriculas.service.UsuarioService;

import java.util.Scanner;

final class MenuLoginHandler {

	private final Scanner scanner;
	private final UsuarioService usuarioService;

	MenuLoginHandler(Scanner scanner, UsuarioService usuarioService) {
		this.scanner = scanner;
		this.usuarioService = usuarioService;
	}

	ResultadoLogin executar() {
		System.out.println("\n--- MENU DE ACESSO ---");
		System.out.println("[1] Fazer Login");
		System.out.println("[2] Sair do Sistema");
		System.out.print("Escolha uma opção: ");
		String opcao = scanner.nextLine();

		return switch (opcao) {
			case "1" -> fazerLogin();
			case "2" -> {
				System.out.println("Até logo!");
				yield ResultadoLogin.sair();
			}
			default -> {
				System.out.println("Opção inválida.");
				yield ResultadoLogin.continuar();
			}
		};
	}

	private ResultadoLogin fazerLogin() {
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Senha: ");
		String senha = scanner.nextLine();

		return usuarioService.login(email, senha)
				.map(u -> {
					System.out.println("\nLogin bem-sucedido! Bem-vindo(a), " + u.getNome());
					return ResultadoLogin.logado(u);
				})
				.orElseGet(() -> {
					System.out.println("Email ou senha inválidos.");
					return ResultadoLogin.continuar();
				});
	}
}
