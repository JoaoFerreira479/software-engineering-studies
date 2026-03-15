package com.universidade.matriculas.ui;

import com.universidade.matriculas.application.ResultadoFinalizacaoPeriodo;
import com.universidade.matriculas.infrastructure.io.Console;
import com.universidade.matriculas.infrastructure.io.EntradaUsuario;
import com.universidade.matriculas.model.*;
import com.universidade.matriculas.service.SecretariaService;

final class MenuSecretariaHandler {

	private final EntradaUsuario entrada;
	private final SecretariaService secretariaService;

	MenuSecretariaHandler(EntradaUsuario entrada, SecretariaService secretariaService) {
		this.entrada = entrada;
		this.secretariaService = secretariaService;
	}

	ResultadoMenu executar(Secretaria secretaria) {
		Console.println("\n--- MENU SECRETARIA ---");
		Console.println("[1] Cadastrar Novo Aluno");
		Console.println("[2] Cadastrar Novo Professor");
		Console.println("[3] Cadastrar Novo Curso");
		Console.println("[4] Cadastrar Nova Disciplina");
		Console.println("[5] Listar Cursos");
		Console.println("[6] Finalizar Período de Matrícula");
		Console.println("[9] Logout");
		String opcao = entrada.lerLinha("Escolha uma opção: ");

		return switch (opcao) {
			case "1" -> cadastrarAluno();
			case "2" -> cadastrarProfessor();
			case "3" -> cadastrarCurso();
			case "4" -> cadastrarDisciplina();
			case "5" -> listarCursos();
			case "6" -> finalizarPeriodoMatricula();
			case "9" -> logout(secretaria);
			default -> {
				Console.println("Opção inválida.");
				yield ResultadoMenu.CONTINUAR;
			}
		};
	}

	private ResultadoMenu cadastrarAluno() {
		String nomeAluno = entrada.lerLinha("Nome do aluno: ");
		String emailAluno = entrada.lerLinha("Email do aluno: ");
		String senhaAluno = entrada.lerLinha("Senha do aluno: ");
		Aluno novoAluno = secretariaService.cadastrarAluno(nomeAluno, emailAluno, senhaAluno);
		Console.println("Aluno " + novoAluno.getNome() + " cadastrado com sucesso!");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu cadastrarProfessor() {
		String nomeProf = entrada.lerLinha("Nome do professor: ");
		String emailProf = entrada.lerLinha("Email do professor: ");
		String senhaProf = entrada.lerLinha("Senha do professor: ");
		Professor novoProfessor = secretariaService.cadastrarProfessor(nomeProf, emailProf, senhaProf);
		Console.println("Professor " + novoProfessor.getNome() + " cadastrado com sucesso!");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu cadastrarCurso() {
		String nomeCurso = entrada.lerLinha("Nome do curso: ");
		int duracao = Integer.parseInt(entrada.lerLinha("Duração em semestres: ").trim());
		Curso novoCurso = secretariaService.cadastrarCurso(nomeCurso, duracao);
		Console.println("Curso " + novoCurso.getNome() + " cadastrado com sucesso!");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu cadastrarDisciplina() {
		String nomeDis = entrada.lerLinha("Nome da disciplina: ");
		int creditos = Integer.parseInt(entrada.lerLinha("Créditos: ").trim());
		TipoDisciplina tipo = TipoDisciplina.valueOf(entrada.lerLinha("Tipo (OBRIGATORIA/OPTATIVA): ").toUpperCase().trim());
		Long cursoId = Long.valueOf(entrada.lerLinha("ID do Curso: ").trim());
		Disciplina novaDisciplina = secretariaService.cadastrarDisciplina(nomeDis, creditos, tipo, cursoId);
		Console.println("Disciplina " + novaDisciplina.getNome() + " cadastrada com sucesso!");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu listarCursos() {
		Console.println("\n--- Cursos Cadastrados ---");
		secretariaService.listarCursos()
				.forEach(c -> Console.println("ID: " + c.getId() + ", Nome: " + c.getNome()));
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu finalizarPeriodoMatricula() {
		Console.println("Iniciando processo de finalização do período de matrícula...");
		ResultadoFinalizacaoPeriodo resultado = secretariaService.finalizarPeriodoMatricula();
		for (ResultadoFinalizacaoPeriodo.DisciplinaAtualizada item : resultado.getDisciplinasAtualizadas()) {
			String acao = item.disciplina().getStatus() == StatusDisciplina.ATIVA ? "ATIVADA" : "CANCELADA";
			Console.println("Disciplina '" + item.disciplina().getNome() + "' " + acao
					+ " com " + item.alunosInscritos() + " alunos.");
		}
		Console.println("Processo de finalização concluído.");
		return ResultadoMenu.CONTINUAR;
	}

	private ResultadoMenu logout(Secretaria secretaria) {
		Console.println("\n" + secretaria.getNome() + " deslogado com sucesso.");
		return ResultadoMenu.LOGOUT;
	}
}
