package com.universidade.matriculas.service;

import com.universidade.matriculas.application.NotificadorCobranca;
import com.universidade.matriculas.application.RegrasMatricula;
import com.universidade.matriculas.dto.MatriculaRequestDto;
import com.universidade.matriculas.exception.RecursoNaoEncontradoException;
import com.universidade.matriculas.exception.RegraDeNegocioException;
import com.universidade.matriculas.model.Aluno;
import com.universidade.matriculas.model.Disciplina;
import com.universidade.matriculas.model.Matricula;
import com.universidade.matriculas.repository.AlunoRepository;
import com.universidade.matriculas.repository.DisciplinaRepository;
import com.universidade.matriculas.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaService {

	private final MatriculaRepository matriculaRepository;
	private final AlunoRepository alunoRepository;
	private final DisciplinaRepository disciplinaRepository;
	private final NotificadorCobranca notificadorCobranca;

	public MatriculaService(MatriculaRepository matriculaRepository, AlunoRepository alunoRepository,
			DisciplinaRepository disciplinaRepository, NotificadorCobranca notificadorCobranca) {
		this.matriculaRepository = matriculaRepository;
		this.alunoRepository = alunoRepository;
		this.disciplinaRepository = disciplinaRepository;
		this.notificadorCobranca = notificadorCobranca;
	}

	public Matricula realizarMatricula(MatriculaRequestDto dto) {
		Aluno aluno = alunoRepository.buscarPorId(dto.getAlunoId()).orElseThrow(
				() -> new RecursoNaoEncontradoException("Aluno não encontrado com o ID: " + dto.getAlunoId()));

		matriculaRepository.findByAlunoId(aluno.getId()).ifPresent(m -> {
			throw new RegraDeNegocioException("Este aluno já possui uma matrícula ativa.");
		});

		List<Long> idsObrigatorias = dto.getIdsDisciplinasObrigatorias();
		List<Long> idsOptativas = dto.getIdsDisciplinasOptativas();

		if (idsObrigatorias.size() > RegrasMatricula.MAX_DISCIPLINAS_OBRIGATORIAS) {
			throw new RegraDeNegocioException("Não é permitido se matricular em mais de "
					+ RegrasMatricula.MAX_DISCIPLINAS_OBRIGATORIAS + " disciplinas obrigatórias.");
		}
		if (idsOptativas.size() > RegrasMatricula.MAX_DISCIPLINAS_OPTATIVAS) {
			throw new RegraDeNegocioException("Não é permitido se matricular em mais de "
					+ RegrasMatricula.MAX_DISCIPLINAS_OPTATIVAS + " disciplinas optativas.");
		}

		List<Disciplina> disciplinasObrigatorias = buscarDisciplinasPorIds(idsObrigatorias, "obrigatória");
		List<Disciplina> disciplinasOptativas = buscarDisciplinasPorIds(idsOptativas, "optativa");

		validarVagas(disciplinasObrigatorias);
		validarVagas(disciplinasOptativas);

		Matricula novaMatricula = new Matricula(null, LocalDate.now(), aluno);
		novaMatricula.setDisciplinasObrigatorias(disciplinasObrigatorias);
		novaMatricula.setDisciplinasOptativas(disciplinasOptativas);

		notificadorCobranca.notificarMatriculaRealizada(aluno);

		return matriculaRepository.salvar(novaMatricula);
	}

	private List<Disciplina> buscarDisciplinasPorIds(List<Long> ids, String tipoParaMensagem) {
		return ids.stream()
				.map(id -> disciplinaRepository.buscarPorId(id).orElseThrow(
						() -> new RecursoNaoEncontradoException("Disciplina " + tipoParaMensagem + " não encontrada com ID: " + id)))
				.collect(Collectors.toList());
	}

	public void cancelarMatricula(Long alunoId) {
		Matricula matricula = matriculaRepository.findByAlunoId(alunoId)
				.orElseThrow(() -> new RecursoNaoEncontradoException(
						"Nenhuma matrícula encontrada para o aluno com ID: " + alunoId));

		matriculaRepository.deletarPorId(matricula.getId());
	}

	private void validarVagas(List<Disciplina> disciplinas) {
		for (Disciplina disciplina : disciplinas) {
			long inscritos = matriculaRepository.countAlunosByDisciplina(disciplina);
			if (inscritos >= disciplina.getVagas()) {
				throw new RegraDeNegocioException("A disciplina '" + disciplina.getNome() + "' não possui mais vagas.");
			}
		}
	}
}