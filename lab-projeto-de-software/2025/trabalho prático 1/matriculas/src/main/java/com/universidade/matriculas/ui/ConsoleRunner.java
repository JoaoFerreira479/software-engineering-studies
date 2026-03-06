package com.universidade.matriculas.ui;

import com.universidade.matriculas.exception.PersistenciaException;
import com.universidade.matriculas.exception.RecursoNaoEncontradoException;
import com.universidade.matriculas.exception.RegraDeNegocioException;
import com.universidade.matriculas.model.Aluno;
import com.universidade.matriculas.model.Professor;
import com.universidade.matriculas.model.Secretaria;
import com.universidade.matriculas.model.Usuario;
import com.universidade.matriculas.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(2)
public class ConsoleRunner implements CommandLineRunner {

	private final Scanner scanner = new Scanner(System.in);
	private Sessao sessao = Sessao.vazia();

	private final MenuLoginHandler menuLoginHandler;
	private final MenuAlunoHandler menuAlunoHandler;
	private final MenuSecretariaHandler menuSecretariaHandler;
	private final MenuProfessorHandler menuProfessorHandler;

	public ConsoleRunner(UsuarioService usuarioService, AlunoService alunoService,
			ProfessorService professorService, SecretariaService secretariaService,
			MatriculaService matriculaService) {
		this.menuLoginHandler = new MenuLoginHandler(scanner, usuarioService);
		this.menuAlunoHandler = new MenuAlunoHandler(scanner, alunoService, matriculaService);
		this.menuSecretariaHandler = new MenuSecretariaHandler(scanner, secretariaService);
		this.menuProfessorHandler = new MenuProfessorHandler(scanner, professorService, secretariaService);
	}

	@Override
	public void run(String... args) {
		System.out.println("=== Bem-vindo ao Sistema de Matrículas da Universidade (Spring Boot) ===");

		while (true) {
			try {
				if (!sessao.estaLogado()) {
					ResultadoLogin resultadoLogin = menuLoginHandler.executar();
					if (resultadoLogin.acao() == ResultadoLogin.AcaoLogin.SAIR) {
						System.exit(0);
					}
					resultadoLogin.usuario().ifPresent(u -> sessao = Sessao.de(u));
				} else {
					ResultadoMenu resultado = executarMenuLogado();
					if (resultado == ResultadoMenu.LOGOUT) {
						sessao = Sessao.vazia();
					}
				}
			} catch (RegraDeNegocioException e) {
				System.err.println("\n[ERRO DE REGRA] " + e.getMessage());
			} catch (RecursoNaoEncontradoException e) {
				System.err.println("\n[RECURSO NÃO ENCONTRADO] " + e.getMessage());
			} catch (PersistenciaException e) {
				System.err.println("\n[ERRO DE PERSISTÊNCIA] " + e.getMessage());
			} catch (NumberFormatException e) {
				System.err.println("\n[ERRO] Entrada inválida. Por favor, digite um número.");
			} catch (Exception e) {
				System.err.println("\n[ERRO INESPERADO] Ocorreu um problema: " + e.getMessage());
			}
		}
	}

	private ResultadoMenu executarMenuLogado() {
		Usuario usuario = sessao.getUsuario().orElseThrow();

		if (usuario instanceof Aluno aluno) {
			return menuAlunoHandler.executar(aluno);
		}
		if (usuario instanceof Secretaria secretaria) {
			return menuSecretariaHandler.executar(secretaria);
		}
		if (usuario instanceof Professor professor) {
			return menuProfessorHandler.executar(professor);
		}

		return ResultadoMenu.CONTINUAR;
	}
}
