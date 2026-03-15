package com.universidade.matriculas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.universidade.matriculas.application.RegrasMatricula;
import com.universidade.matriculas.application.ResultadoFinalizacaoPeriodo;
import com.universidade.matriculas.model.Disciplina;
import com.universidade.matriculas.model.StatusDisciplina;
import com.universidade.matriculas.repository.DisciplinaRepository;
import com.universidade.matriculas.repository.MatriculaRepository;

@Service
public class FinalizacaoPeriodoService {

	private final DisciplinaRepository disciplinaRepository;
	private final MatriculaRepository matriculaRepository;

	public FinalizacaoPeriodoService(DisciplinaRepository disciplinaRepository,
			MatriculaRepository matriculaRepository) {
		this.disciplinaRepository = disciplinaRepository;
		this.matriculaRepository = matriculaRepository;
	}

	public ResultadoFinalizacaoPeriodo finalizarPeriodoMatricula() {
		List<ResultadoFinalizacaoPeriodo.DisciplinaAtualizada> resultados = new ArrayList<>();
		List<Disciplina> todasAsDisciplinas = disciplinaRepository.listarTodos();
		int minimoParaAtivar = RegrasMatricula.MIN_ALUNOS_PARA_ATIVAR_DISCIPLINA;

		for (Disciplina disciplina : todasAsDisciplinas) {
			resultados.add(atualizarStatusDisciplina(disciplina, minimoParaAtivar));
		}
		return new ResultadoFinalizacaoPeriodo(resultados);
	}

	private ResultadoFinalizacaoPeriodo.DisciplinaAtualizada atualizarStatusDisciplina(
			Disciplina disciplina, int minimoParaAtivar) {
		long numeroDeInscritos = matriculaRepository.countAlunosByDisciplina(disciplina);
		StatusDisciplina statusAnterior = disciplina.getStatus();

		if (numeroDeInscritos >= minimoParaAtivar) {
			disciplina.setStatus(StatusDisciplina.ATIVA);
		} else {
			disciplina.setStatus(StatusDisciplina.CANCELADA);
		}
		disciplinaRepository.salvar(disciplina);
		return new ResultadoFinalizacaoPeriodo.DisciplinaAtualizada(
				disciplina, statusAnterior, (int) numeroDeInscritos);
	}
}
