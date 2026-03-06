package programarcomputadoresideiasedesafios;

import java.util.Arrays;

public final class Aluno {

    private final String nome;
    private final char[] respostas;
    private int nota;

    public Aluno(final String nome, final char[] respostas) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do aluno não pode ser vazio.");
        }
        if (respostas == null) {
            throw new NullPointerException("Respostas não podem ser nulas.");
        }
        this.nome = nome.trim();
        this.respostas = Arrays.copyOf(respostas, respostas.length);
    }

    public String getNome() {
        return nome;
    }

    /** Cópia defensiva das respostas. */
    public char[] getRespostas() {
        return Arrays.copyOf(respostas, respostas.length);
    }

    public int getNota() {
        return nota;
    }

    public void setNota(final int nota) {
        this.nota = nota;
    }
}
