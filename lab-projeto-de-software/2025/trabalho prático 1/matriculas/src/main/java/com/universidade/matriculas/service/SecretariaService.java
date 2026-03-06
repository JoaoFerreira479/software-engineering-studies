package com.universidade.matriculas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.universidade.matriculas.application.ResultadoFinalizacaoPeriodo;
import com.universidade.matriculas.exception.RecursoNaoEncontradoException;
import com.universidade.matriculas.exception.RegraDeNegocioException;
import com.universidade.matriculas.model.Aluno;
import com.universidade.matriculas.model.Curso;
import com.universidade.matriculas.model.Disciplina;
import com.universidade.matriculas.model.Professor;
import com.universidade.matriculas.model.Secretaria;
import com.universidade.matriculas.model.TipoDisciplina;
import com.universidade.matriculas.repository.AlunoRepository;
import com.universidade.matriculas.repository.CursoRepository;
import com.universidade.matriculas.repository.DisciplinaRepository;
import com.universidade.matriculas.repository.ProfessorRepository;
import com.universidade.matriculas.repository.SecretariaRepository;

@Service
public class SecretariaService {

	private final DisciplinaRepository disciplinaRepository;
	private final AlunoRepository alunoRepository;
	private final ProfessorRepository professorRepository;
	private final CursoRepository cursoRepository;
	private final SecretariaRepository secretariaRepository;
	private final FinalizacaoPeriodoService finalizacaoPeriodoService;

	public SecretariaService(DisciplinaRepository disciplinaRepository, AlunoRepository alunoRepository,
			ProfessorRepository professorRepository, CursoRepository cursoRepository,
			SecretariaRepository secretariaRepository, FinalizacaoPeriodoService finalizacaoPeriodoService) {
		this.disciplinaRepository = disciplinaRepository;
		this.alunoRepository = alunoRepository;
		this.professorRepository = professorRepository;
		this.cursoRepository = cursoRepository;
		this.secretariaRepository = secretariaRepository;
		this.finalizacaoPeriodoService = finalizacaoPeriodoService;
	}

	public Secretaria cadastrarSecretaria(String nome, String email, String senha) {
		if (secretariaRepository.findByEmail(email).isPresent()) {
			throw new RegraDeNegocioException("Já existe uma secretaria com o e-mail: " + email);
		}
		Secretaria nova = new Secretaria(null, nome, email, senha);
		return secretariaRepository.salvar(nova);
	}

	public Aluno cadastrarAluno(String nome, String email, String senha) {
		if (alunoRepository.findByEmail(email).isPresent()) {
			throw new RegraDeNegocioException("Já existe um aluno com o e-mail: " + email);
		}
		Aluno novoAluno = new Aluno(null, nome, email, senha, null);
		return alunoRepository.salvar(novoAluno);
	}

	public Professor cadastrarProfessor(String nome, String email, String senha) {
		if (professorRepository.findByEmail(email).isPresent()) {
			throw new RegraDeNegocioException("Já existe um professor com o e-mail: " + email);
		}
		Professor novoProfessor = new Professor(null, nome, email, senha);
		return professorRepository.salvar(novoProfessor);
	}

	public Curso cadastrarCurso(String nome, int duracao) {
		Curso novoCurso = new Curso(null, nome, duracao);
		return cursoRepository.salvar(novoCurso);
	}

	public Disciplina cadastrarDisciplina(String nome, int creditos, TipoDisciplina tipo, Long cursoId) {
		Curso curso = cursoRepository.buscarPorId(cursoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Curso não encontrado com o ID: " + cursoId));

		Disciplina novaDisciplina = new Disciplina(null, nome, creditos, tipo, curso);
		return disciplinaRepository.salvar(novaDisciplina);
	}

	public List<Curso> listarCursos() {
		return cursoRepository.listarTodos();
	}

	public Optional<Disciplina> buscarDisciplinaPorId(Long id) {
		return disciplinaRepository.buscarPorId(id);
	}

	public ResultadoFinalizacaoPeriodo finalizarPeriodoMatricula() {
		return finalizacaoPeriodoService.finalizarPeriodoMatricula();
	}

	public boolean possuiAlgumaSecretaria() {
		return !secretariaRepository.listarTodos().isEmpty();
	}
}
