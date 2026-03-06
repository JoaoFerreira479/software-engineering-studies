package com.universidade.matriculas.ui;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.universidade.matriculas.model.Curso;
import com.universidade.matriculas.model.TipoDisciplina;
import com.universidade.matriculas.service.SecretariaService;

@Component
@Order(1)
public class DadosIniciaisLoader implements CommandLineRunner {

	private final SecretariaService secretariaService;

	public DadosIniciaisLoader(SecretariaService secretariaService) {
		this.secretariaService = secretariaService;
	}

	@Override
	public void run(String... args) {
		if (secretariaService.possuiAlgumaSecretaria()) {
			return;
		}
		System.out.println("Primeira execução: criando dados iniciais...");

		secretariaService.cadastrarSecretaria("Admin", "admin@uni.com", "123");

		Curso cco = secretariaService.cadastrarCurso("Ciência da Computação", 8);
		Curso eng = secretariaService.cadastrarCurso("Engenharia de Software", 10);

		secretariaService.cadastrarDisciplina("Cálculo I", 4, TipoDisciplina.OBRIGATORIA, cco.getId());
		secretariaService.cadastrarDisciplina("Algoritmos", 4, TipoDisciplina.OBRIGATORIA, cco.getId());
		secretariaService.cadastrarDisciplina("Qualidade de Software", 4, TipoDisciplina.OPTATIVA, eng.getId());

		secretariaService.cadastrarProfessor("Dr. Alan Turing", "turing@uni.com", "abc");
		secretariaService.cadastrarAluno("Ada Lovelace", "ada@uni.com", "abc");

		System.out.println("Dados iniciais criados.");
	}
}
