package com.universidade.matriculas.ui;

import com.universidade.matriculas.dto.MatriculaRequestDto;
import com.universidade.matriculas.infrastructure.io.Console;
import com.universidade.matriculas.infrastructure.io.EntradaUsuario;
import com.universidade.matriculas.model.Aluno;
import com.universidade.matriculas.model.Disciplina;
import com.universidade.matriculas.model.Matricula;
import com.universidade.matriculas.service.AlunoService;
import com.universidade.matriculas.service.MatriculaService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class MenuAlunoHandler {

	private final EntradaUsuario entrada;
	private final AlunoService alunoService;
	private final MatriculaService matriculaService;

	MenuAlunoHandler(EntradaUsuario entrada, AlunoService alunoService, MatriculaService matriculaService) {
		this.entrada = entrada;
		this.alunoService = alunoService;
		this.matriculaService = matriculaService;
	}

	ResultadoMenu executar(Aluno aluno) {
		Console.println("\n--- MENU ALUNO ---");
		Console.println("[1] Realizar Matrícula");
		Console.println("[2] Consultar Minha Matrícula");
		Console.println("[3] Consultar Disciplinas Disponíveis");
		Console.println("[4] Cancelar Matrícula");
		Console.println("[9] Logout");
		String opcao = entrada.lerLinha("Escolha uma opção: ");

		return switch (opcao) {
			case "1" -> realizarMatricula(aluno);
			case "2" -> consultarMinhaMatricula(aluno);
			case "3" -> consultarDisciplinasDisponiveis();
			case "4" -> cancelarMatricula(aluno);
			case "9" -> logout(aluno);
			default -> {
				Console.println("Opção inválida.");
				yield ResultadoMenu.CONTINUAR;
			}
		};
	}

	private ResultadoMenu realizarMatricula(Aluno aluno) {
		Console.println("\n--- Realizar Matrícula ---");
		List<Long> idsObrigatorias = parseIds(entrada.lerLinha("IDs das disciplinas OBRIGATÓRIAS (separados por vírgula): "));
		List<Long> idsOptativas = parseIds(entrada.lerLinha("IDs das disciplinas OPTATIVAS (separados por vírgula): "));

		MatriculaRequestDto dto = new MatriculaRequestDto();
		dto.setAlunoId(aluno.getId());
		dto.setIdsDisciplinasObrigatorias(idsObrigatorias);
		dto.setIdsDisciplinasOptativas(idsOptativas);

		matriculaService.realizarMatricula(dto);
		Console.println("Matrícula realizada com sucesso!");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu consultarMinhaMatricula(Aluno aluno) {
		Console.println("\n--- Minha Matrícula ---");
		Matricula m = alunoService.consultarMinhaMatricula(aluno.getId());
		Console.println("Aluno: " + m.getAluno().getNome());
		Console.println("Período: " + m.getPeriodoMatricula());
		Console.println("Obrigatórias: " + m.getDisciplinasObrigatorias().stream()
				.map(Disciplina::getNome)
				.collect(Collectors.joining(", ")));
		Console.println("Optativas: " + m.getDisciplinasOptativas().stream()
				.map(Disciplina::getNome)
				.collect(Collectors.joining(", ")));
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu consultarDisciplinasDisponiveis() {
		Console.println("\n--- Disciplinas Disponíveis ---");
		alunoService.consultarDisciplinasDisponiveis().forEach(d ->
				Console.println("ID: " + d.getId() + ", Nome: " + d.getNome()
						+ ", Créditos: " + d.getCreditos() + ", Status: " + d.getStatus()));
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu cancelarMatricula(Aluno aluno) {
		matriculaService.cancelarMatricula(aluno.getId());
		Console.println("Matrícula cancelada com sucesso.");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu logout(Aluno aluno) {
		Console.println("\n" + aluno.getNome() + " deslogado com sucesso.");
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
