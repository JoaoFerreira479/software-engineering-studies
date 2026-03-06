package com.universidade.matriculas.service;

import com.universidade.matriculas.model.Aluno;
import com.universidade.matriculas.model.Usuario;
import com.universidade.matriculas.repository.AlunoRepository;
import com.universidade.matriculas.repository.ProfessorRepository;
import com.universidade.matriculas.repository.SecretariaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("UsuarioService")
class UsuarioServiceTest {

	private AlunoRepository alunoRepository;
	private ProfessorRepository professorRepository;
	private SecretariaRepository secretariaRepository;
	private UsuarioService usuarioService;

	@BeforeEach
	void setUp() {
		alunoRepository = new AlunoRepository();
		professorRepository = new ProfessorRepository();
		secretariaRepository = new SecretariaRepository();
		usuarioService = new UsuarioService(alunoRepository, professorRepository, secretariaRepository);
	}

	@Test
	void login_retornaEmpty_quandoEmailNull() {
		Optional<Usuario> result = usuarioService.login(null, "qualquer");
		assertThat(result).isEmpty();
	}

	@Test
	void login_retornaEmpty_quandoSenhaNull() {
		Optional<Usuario> result = usuarioService.login("alguem@email.com", null);
		assertThat(result).isEmpty();
	}

	@Test
	void login_retornaUsuario_quandoAlunoCadastradoESenhaCorreta() {
		Aluno aluno = new Aluno(null, "Teste", "teste@uni.com", "senha123", null);
		alunoRepository.salvar(aluno);

		Optional<Usuario> result = usuarioService.login("teste@uni.com", "senha123");

		assertThat(result).isPresent();
		assertThat(result.get().getNome()).isEqualTo("Teste");
		assertThat(result.get()).isInstanceOf(Aluno.class);
	}

	@Test
	void login_retornaEmpty_quandoSenhaIncorreta() {
		Aluno aluno = new Aluno(null, "Teste", "teste@uni.com", "senha123", null);
		alunoRepository.salvar(aluno);

		Optional<Usuario> result = usuarioService.login("teste@uni.com", "errada");

		assertThat(result).isEmpty();
	}
}
