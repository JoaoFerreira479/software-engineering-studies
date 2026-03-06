package com.universidade.matriculas.ui;

import com.universidade.matriculas.model.Aluno;
import com.universidade.matriculas.model.Disciplina;
import com.universidade.matriculas.model.Professor;
import com.universidade.matriculas.service.ProfessorService;
import com.universidade.matriculas.service.SecretariaService;

import java.util.List;
import java.util.Scanner;

final class MenuProfessorHandler {

	private final Scanner scanner;
	private final ProfessorService professorService;
	private final SecretariaService secretariaService;

	MenuProfessorHandler(Scanner scanner, ProfessorService professorService, SecretariaService secretariaService) {
		this.scanner = scanner;
		this.professorService = professorService;
		this.secretariaService = secretariaService;
	}

	ResultadoMenu executar(Professor professor) {
		System.out.println("\n--- MENU PROFESSOR ---");
		System.out.println("[1] Consultar Alunos por Disciplina");
		System.out.println("[9] Logout");
		System.out.print("Escolha uma opção: ");
		String opcao = scanner.nextLine();

		return switch (opcao) {
			case "1" -> consultarAlunosPorDisciplina(professor);
			case "9" -> logout(professor);
			default -> {
				System.out.println("Opção inválida.");
				yield ResultadoMenu.CONTINUAR;
			}
		};
	}

	private ResultadoMenu consultarAlunosPorDisciplina(Professor professor) {
		System.out.print("ID da disciplina para consulta: ");
		Long disciplinaId = Long.valueOf(scanner.nextLine());
		List<Aluno> alunos = professorService.listarAlunosPorDisciplina(professor.getId(), disciplinaId);
		String nomeDisciplina = secretariaService.buscarDisciplinaPorId(disciplinaId)
				.map(Disciplina::getNome)
				.orElse("ID " + disciplinaId);
		System.out.println("\n--- Alunos em " + nomeDisciplina + " ---");
		alunos.forEach(a -> System.out.println("ID: " + a.getId() + ", Nome: " + a.getNome()));
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu logout(Professor professor) {
		System.out.println("\n" + professor.getNome() + " deslogado com sucesso.");
		return ResultadoMenu.LOGOUT;
	}
}
