package com.universidade.matriculas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Matricula implements Serializable, Entidade {

	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDate periodoMatricula;
	private Aluno aluno;
	private List<Disciplina> disciplinasObrigatorias = new ArrayList<>();
	private List<Disciplina> disciplinasOptativas = new ArrayList<>();

	public Matricula() {
	}

	public Matricula(Long id, LocalDate periodoMatricula, Aluno aluno) {
		this.id = id;
		this.periodoMatricula = periodoMatricula;
		this.aluno = aluno;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getPeriodoMatricula() {
		return periodoMatricula;
	}

	public void setPeriodoMatricula(LocalDate periodoMatricula) {
		this.periodoMatricula = periodoMatricula;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Disciplina> getDisciplinasObrigatorias() {
		return Collections.unmodifiableList(disciplinasObrigatorias);
	}

	public void setDisciplinasObrigatorias(List<Disciplina> disciplinasObrigatorias) {
		this.disciplinasObrigatorias = disciplinasObrigatorias != null
				? new ArrayList<>(disciplinasObrigatorias)
				: new ArrayList<>();
	}

	public List<Disciplina> getDisciplinasOptativas() {
		return Collections.unmodifiableList(disciplinasOptativas);
	}

	public void setDisciplinasOptativas(List<Disciplina> disciplinasOptativas) {
		this.disciplinasOptativas = disciplinasOptativas != null
				? new ArrayList<>(disciplinasOptativas)
				: new ArrayList<>();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Matricula matricula = (Matricula) o;
		return Objects.equals(id, matricula.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}