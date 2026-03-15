package com.universidade.matriculas.application;

import com.universidade.matriculas.model.Disciplina;
import com.universidade.matriculas.model.StatusDisciplina;

import java.util.List;

public final class ResultadoFinalizacaoPeriodo {

    private final List<DisciplinaAtualizada> disciplinasAtualizadas;

    public ResultadoFinalizacaoPeriodo(List<DisciplinaAtualizada> disciplinasAtualizadas) {
        this.disciplinasAtualizadas = disciplinasAtualizadas != null
                ? List.copyOf(disciplinasAtualizadas)
                : List.of();
    }

    public List<DisciplinaAtualizada> getDisciplinasAtualizadas() {
        return disciplinasAtualizadas;
    }

    public record DisciplinaAtualizada(Disciplina disciplina, StatusDisciplina statusAnterior, int alunosInscritos) {
    }
}
