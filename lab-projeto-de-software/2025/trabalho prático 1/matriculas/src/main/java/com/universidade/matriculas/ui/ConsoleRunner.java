package com.universidade.matriculas.ui;

import com.universidade.matriculas.exception.PersistenciaException;
import com.universidade.matriculas.exception.RecursoNaoEncontradoException;
import com.universidade.matriculas.exception.RegraDeNegocioException;
import com.universidade.matriculas.infrastructure.io.Console;
import com.universidade.matriculas.infrastructure.io.EntradaUsuario;
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
	private final EntradaUsuario entrada = new EntradaUsuario(scanner);
	private Sessao sessao = Sessao.vazia();

	private final MenuLoginHandler menuLoginHandler;
	private final MenuAlunoHandler menuAlunoHandler;
	private final MenuSecretariaHandler menuSecretariaHandler;
	private final MenuProfessorHandler menuProfessorHandler;

	public ConsoleRunner(UsuarioService usuarioService, AlunoService alunoService,
			ProfessorService professorService, SecretariaService secretariaService,
			MatriculaService matriculaService) {
		this.menuLoginHandler = new MenuLoginHandler(entrada, usuarioService);
		this.menuAlunoHandler = new MenuAlunoHandler(entrada, alunoService, matriculaService);
		this.menuSecretariaHandler = new MenuSecretariaHandler(entrada, secretariaService);
		this.menuProfessorHandler = new MenuProfessorHandler(entrada, professorService, secretariaService);
	}

	@Override
	public void run(String... args) {
		Console.println("=== Bem-vindo ao Sistema de Matrículas da Universidade (Spring Boot) ===");

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
				Console.erro("\n[ERRO DE REGRA] " + e.getMessage());
			} catch (RecursoNaoEncontradoException e) {
				Console.erro("\n[RECURSO NÃO ENCONTRADO] " + e.getMessage());
			} catch (PersistenciaException e) {
				Console.erro("\n[ERRO DE PERSISTÊNCIA] " + e.getMessage());
			} catch (NumberFormatException e) {
				Console.erro("\n[ERRO] Entrada inválida. Por favor, digite um número.");
			} catch (Exception e) {
				Console.erro("\n[ERRO INESPERADO] Ocorreu um problema: " + e.getMessage());
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
