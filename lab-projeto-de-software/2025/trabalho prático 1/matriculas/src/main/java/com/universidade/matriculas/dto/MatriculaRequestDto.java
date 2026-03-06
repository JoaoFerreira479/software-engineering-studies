package com.universidade.matriculas.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatriculaRequestDto {

	private Long alunoId;
	private List<Long> idsDisciplinasObrigatorias = new ArrayList<>();
	private List<Long> idsDisciplinasOptativas = new ArrayList<>();

	public Long getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Long alunoId) {
		this.alunoId = alunoId;
	}

	public List<Long> getIdsDisciplinasObrigatorias() {
		return idsDisciplinasObrigatorias == null || idsDisciplinasObrigatorias.isEmpty()
				? List.of()
				: Collections.unmodifiableList(new ArrayList<>(idsDisciplinasObrigatorias));
	}

	public void setIdsDisciplinasObrigatorias(List<Long> idsDisciplinasObrigatorias) {
		this.idsDisciplinasObrigatorias = idsDisciplinasObrigatorias != null
				? new ArrayList<>(idsDisciplinasObrigatorias)
				: new ArrayList<>();
	}

	public List<Long> getIdsDisciplinasOptativas() {
		return idsDisciplinasOptativas == null || idsDisciplinasOptativas.isEmpty()
				? List.of()
				: Collections.unmodifiableList(new ArrayList<>(idsDisciplinasOptativas));
	}

	public void setIdsDisciplinasOptativas(List<Long> idsDisciplinasOptativas) {
		this.idsDisciplinasOptativas = idsDisciplinasOptativas != null
				? new ArrayList<>(idsDisciplinasOptativas)
				: new ArrayList<>();
	}
}
