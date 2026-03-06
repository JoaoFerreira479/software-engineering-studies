package com.universidade.matriculas.ui;

import com.universidade.matriculas.application.ResultadoFinalizacaoPeriodo;
import com.universidade.matriculas.model.*;
import com.universidade.matriculas.service.SecretariaService;

import java.util.Scanner;

final class MenuSecretariaHandler {

	private final Scanner scanner;
	private final SecretariaService secretariaService;

	MenuSecretariaHandler(Scanner scanner, SecretariaService secretariaService) {
		this.scanner = scanner;
		this.secretariaService = secretariaService;
	}

	ResultadoMenu executar(Secretaria secretaria) {
		System.out.println("\n--- MENU SECRETARIA ---");
		System.out.println("[1] Cadastrar Novo Aluno");
		System.out.println("[2] Cadastrar Novo Professor");
		System.out.println("[3] Cadastrar Novo Curso");
		System.out.println("[4] Cadastrar Nova Disciplina");
		System.out.println("[5] Listar Cursos");
		System.out.println("[6] Finalizar Período de Matrícula");
		System.out.println("[9] Logout");
		System.out.print("Escolha uma opção: ");
		String opcao = scanner.nextLine();

		return switch (opcao) {
			case "1" -> cadastrarAluno();
			case "2" -> cadastrarProfessor();
			case "3" -> cadastrarCurso();
			case "4" -> cadastrarDisciplina();
			case "5" -> listarCursos();
			case "6" -> finalizarPeriodoMatricula();
			case "9" -> logout(secretaria);
			default -> {
				System.out.println("Opção inválida.");
				yield ResultadoMenu.CONTINUAR;
			}
		};
	}

	private ResultadoMenu cadastrarAluno() {
		System.out.print("Nome do aluno: ");
		String nomeAluno = scanner.nextLine();
		System.out.print("Email do aluno: ");
		String emailAluno = scanner.nextLine();
		System.out.print("Senha do aluno: ");
		String senhaAluno = scanner.nextLine();
		Aluno novoAluno = secretariaService.cadastrarAluno(nomeAluno, emailAluno, senhaAluno);
		System.out.println("Aluno " + novoAluno.getNome() + " cadastrado com sucesso!");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu cadastrarProfessor() {
		System.out.print("Nome do professor: ");
		String nomeProf = scanner.nextLine();
		System.out.print("Email do professor: ");
		String emailProf = scanner.nextLine();
		System.out.print("Senha do professor: ");
		String senhaProf = scanner.nextLine();
		Professor novoProfessor = secretariaService.cadastrarProfessor(nomeProf, emailProf, senhaProf);
		System.out.println("Professor " + novoProfessor.getNome() + " cadastrado com sucesso!");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu cadastrarCurso() {
		System.out.print("Nome do curso: ");
		String nomeCurso = scanner.nextLine();
		System.out.print("Duração em semestres: ");
		int duracao = Integer.parseInt(scanner.nextLine());
		Curso novoCurso = secretariaService.cadastrarCurso(nomeCurso, duracao);
		System.out.println("Curso " + novoCurso.getNome() + " cadastrado com sucesso!");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu cadastrarDisciplina() {
		System.out.print("Nome da disciplina: ");
		String nomeDis = scanner.nextLine();
		System.out.print("Créditos: ");
		int creditos = Integer.parseInt(scanner.nextLine());
		System.out.print("Tipo (OBRIGATORIA/OPTATIVA): ");
		TipoDisciplina tipo = TipoDisciplina.valueOf(scanner.nextLine().toUpperCase());
		System.out.print("ID do Curso: ");
		Long cursoId = Long.valueOf(scanner.nextLine());
		Disciplina novaDisciplina = secretariaService.cadastrarDisciplina(nomeDis, creditos, tipo, cursoId);
		System.out.println("Disciplina " + novaDisciplina.getNome() + " cadastrada com sucesso!");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu listarCursos() {
		System.out.println("\n--- Cursos Cadastrados ---");
		secretariaService.listarCursos()
				.forEach(c -> System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome()));
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu finalizarPeriodoMatricula() {
		System.out.println("Iniciando processo de finalização do período de matrícula...");
		ResultadoFinalizacaoPeriodo resultado = secretariaService.finalizarPeriodoMatricula();
		for (ResultadoFinalizacaoPeriodo.DisciplinaAtualizada item : resultado.getDisciplinasAtualizadas()) {
			String acao = item.disciplina().getStatus() == StatusDisciplina.ATIVA ? "ATIVADA" : "CANCELADA";
			System.out.println("Disciplina '" + item.disciplina().getNome() + "' " + acao
					+ " com " + item.alunosInscritos() + " alunos.");
		}
		System.out.println("Processo de finalização concluído.");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu logout(Secretaria secretaria) {
		System.out.println("\n" + secretaria.getNome() + " deslogado com sucesso.");
		return ResultadoMenu.LOGOUT;
	}
}
