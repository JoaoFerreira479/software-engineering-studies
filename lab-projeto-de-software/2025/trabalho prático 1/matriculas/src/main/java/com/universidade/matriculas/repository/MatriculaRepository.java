package com.universidade.matriculas.repository;

import com.universidade.matriculas.model.Aluno;
import com.universidade.matriculas.model.Disciplina;
import com.universidade.matriculas.model.Matricula;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MatriculaRepository extends ArquivoRepository<Matricula> {

	public MatriculaRepository() {
		super("matriculas.dat");
	}

	public Optional<Matricula> findByAlunoId(Long alunoId) {
		if (alunoId == null) {
			return Optional.empty();
		}
		return listarTodos().stream()
				.filter(m -> m.getAluno() != null && alunoId.equals(m.getAluno().getId()))
				.findFirst();
	}

	public long countAlunosByDisciplina(Disciplina disciplina) {
		return countAlunosByDisciplinaId(disciplina.getId());
	}

	public long countAlunosByDisciplinaId(Long disciplinaId) {
		if (disciplinaId == null) return 0;
		return listarTodos().stream()
				.filter(m -> contemDisciplina(m, disciplinaId))
				.count();
	}

	public List<Aluno> findAlunosByDisciplina(Disciplina disciplina) {
		if (disciplina == null || disciplina.getId() == null) return List.of();
		return listarTodos().stream()
				.filter(m -> contemDisciplina(m, disciplina.getId()))
				.map(Matricula::getAluno)
				.collect(Collectors.toList());
	}

	private static boolean contemDisciplina(Matricula matricula, Long disciplinaId) {
		boolean emObrigatorias = matricula.getDisciplinasObrigatorias().stream()
				.anyMatch(d -> disciplinaId.equals(d.getId()));
		boolean emOptativas = matricula.getDisciplinasOptativas().stream()
				.anyMatch(d -> disciplinaId.equals(d.getId()));
		return emObrigatorias || emOptativas;
	}
}