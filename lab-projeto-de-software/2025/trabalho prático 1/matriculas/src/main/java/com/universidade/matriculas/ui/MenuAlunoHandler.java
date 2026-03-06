package com.universidade.matriculas.ui;

import com.universidade.matriculas.dto.MatriculaRequestDto;
import com.universidade.matriculas.model.Aluno;
import com.universidade.matriculas.model.Disciplina;
import com.universidade.matriculas.model.Matricula;
import com.universidade.matriculas.service.AlunoService;
import com.universidade.matriculas.service.MatriculaService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

final class MenuAlunoHandler {

	private final Scanner scanner;
	private final AlunoService alunoService;
	private final MatriculaService matriculaService;

	MenuAlunoHandler(Scanner scanner, AlunoService alunoService, MatriculaService matriculaService) {
		this.scanner = scanner;
		this.alunoService = alunoService;
		this.matriculaService = matriculaService;
	}

	ResultadoMenu executar(Aluno aluno) {
		System.out.println("\n--- MENU ALUNO ---");
		System.out.println("[1] Realizar Matrícula");
		System.out.println("[2] Consultar Minha Matrícula");
		System.out.println("[3] Consultar Disciplinas Disponíveis");
		System.out.println("[4] Cancelar Matrícula");
		System.out.println("[9] Logout");
		System.out.print("Escolha uma opção: ");
		String opcao = scanner.nextLine();

		return switch (opcao) {
			case "1" -> realizarMatricula(aluno);
			case "2" -> consultarMinhaMatricula(aluno);
			case "3" -> consultarDisciplinasDisponiveis();
			case "4" -> cancelarMatricula(aluno);
			case "9" -> logout(aluno);
			default -> {
				System.out.println("Opção inválida.");
				yield ResultadoMenu.CONTINUAR;
			}
		};
	}

	private ResultadoMenu realizarMatricula(Aluno aluno) {
		System.out.println("\n--- Realizar Matrícula ---");
		System.out.print("IDs das disciplinas OBRIGATÓRIAS (separados por vírgula): ");
		List<Long> idsObrigatorias = parseIds(scanner.nextLine());
		System.out.print("IDs das disciplinas OPTATIVAS (separados por vírgula): ");
		List<Long> idsOptativas = parseIds(scanner.nextLine());

		MatriculaRequestDto dto = new MatriculaRequestDto();
		dto.setAlunoId(aluno.getId());
		dto.setIdsDisciplinasObrigatorias(idsObrigatorias);
		dto.setIdsDisciplinasOptativas(idsOptativas);

		matriculaService.realizarMatricula(dto);
		System.out.println("Matrícula realizada com sucesso!");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu consultarMinhaMatricula(Aluno aluno) {
		System.out.println("\n--- Minha Matrícula ---");
		Matricula m = alunoService.consultarMinhaMatricula(aluno.getId());
		System.out.println("Aluno: " + m.getAluno().getNome());
		System.out.println("Período: " + m.getPeriodoMatricula());
		System.out.println("Obrigatórias: " + m.getDisciplinasObrigatorias().stream()
				.map(Disciplina::getNome)
				.collect(Collectors.joining(", ")));
		System.out.println("Optativas: " + m.getDisciplinasOptativas().stream()
				.map(Disciplina::getNome)
				.collect(Collectors.joining(", ")));
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu consultarDisciplinasDisponiveis() {
		System.out.println("\n--- Disciplinas Disponíveis ---");
		alunoService.consultarDisciplinasDisponiveis().forEach(d ->
				System.out.println("ID: " + d.getId() + ", Nome: " + d.getNome()
						+ ", Créditos: " + d.getCreditos() + ", Status: " + d.getStatus()));
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu cancelarMatricula(Aluno aluno) {
		matriculaService.cancelarMatricula(aluno.getId());
		System.out.println("Matrícula cancelada com sucesso.");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu logout(Aluno aluno) {
		System.out.println("\n" + aluno.getNome() + " deslogado com sucesso.");
		return ResultadoMenu.LOGOUT;
	}

	private static List<Long> parseIds(String idsString) {
		if (idsString == null || idsString.trim().isEmpty()) {
			return List.of();
		}
		return Arrays.stream(idsString.split(","))
				.map(String::trim)
				.map(Long::parseLong)
				.collect(Collectors.toList());
	}
}
