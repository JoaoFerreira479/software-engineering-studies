package com.universidade.matriculas.ui;

import com.universidade.matriculas.infrastructure.io.Console;
import com.universidade.matriculas.infrastructure.io.EntradaUsuario;
import com.universidade.matriculas.model.Aluno;
import com.universidade.matriculas.model.Disciplina;
import com.universidade.matriculas.model.Professor;
import com.universidade.matriculas.service.ProfessorService;
import com.universidade.matriculas.service.SecretariaService;

import java.util.List;

final class MenuProfessorHandler {

	private final EntradaUsuario entrada;
	private final ProfessorService professorService;
	private final SecretariaService secretariaService;

	MenuProfessorHandler(EntradaUsuario entrada, ProfessorService professorService, SecretariaService secretariaService) {
		this.entrada = entrada;
		this.professorService = professorService;
		this.secretariaService = secretariaService;
	}

	ResultadoMenu executar(Professor professor) {
		Console.println("\n--- MENU PROFESSOR ---");
		Console.println("[1] Consultar Alunos por Disciplina");
		Console.println("[9] Logout");
		String opcao = entrada.lerLinha("Escolha uma opção: ");

		return switch (opcao) {
			case "1" -> consultarAlunosPorDisciplina(professor);
			case "9" -> logout(professor);
			default -> {
				Console.println("Opção inválida.");
				yield ResultadoMenu.CONTINUAR;
			}
		};
	}

	private ResultadoMenu consultarAlunosPorDisciplina(Professor professor) {
		String linha = entrada.lerLinha("ID da disciplina para consulta: ");
		Long disciplinaId = Long.valueOf(linha.trim());
		List<Aluno> alunos = professorService.listarAlunosPorDisciplina(professor.getId(), disciplinaId);
		String nomeDisciplina = secretariaService.buscarDisciplinaPorId(disciplinaId)
				.map(Disciplina::getNome)
				.orElse("ID " + disciplinaId);
		Console.println("\n--- Alunos em " + nomeDisciplina + " ---");
		alunos.forEach(a -> Console.println("ID: " + a.getId() + ", Nome: " + a.getNome()));
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu logout(Professor professor) {
		Console.println("\n" + professor.getNome() + " deslogado com sucesso.");
		return ResultadoMenu.LOGOUT;
	}
}
