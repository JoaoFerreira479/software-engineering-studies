package programarcomputadoresideiasedesafios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Prova {

    private final char[] gabarito;
    private final List<Aluno> alunos;

    public Prova(final int numQuestoes) {
        if (numQuestoes < 1) {
            throw new IllegalArgumentException("Número de questões deve ser positivo.");
        }
        this.gabarito = new char[numQuestoes];
        this.alunos = new ArrayList<>();
    }

    public void cadastrarGabarito(final Scanner scanner) {
        System.out.println("Digite o gabarito das questões (V para Verdadeiro, F para Falso):");
        for (int i = 0; i < gabarito.length; i++) {
            gabarito[i] = EntradaUtil.lerRespostaVF(scanner, "Questão " + (i + 1) + ": ");
        }
    }

    public void cadastrarRespostasAluno(final Scanner scanner, final String nomeAluno) {
        final char[] respostas = new char[gabarito.length];
        System.out.println("Digite as respostas do aluno " + nomeAluno + " (V para Verdadeiro, F para Falso):");
        for (int i = 0; i < respostas.length; i++) {
            respostas[i] = EntradaUtil.lerRespostaVF(scanner, "Questão " + (i + 1) + ": ");
        }
        alunos.add(new Aluno(nomeAluno, respostas));
    }

    public void corrigirProvas(final int fatorCorrecao) {
        for (final Aluno aluno : alunos) {
            int acertos = 0;
            int erros = 0;
            final char[] resp = aluno.getRespostas();
            for (int i = 0; i < gabarito.length; i++) {
                if (resp[i] == gabarito[i]) {
                    acertos++;
                } else {
                    erros++;
                }
            }
            if (fatorCorrecao > 0) {
                acertos -= (erros / fatorCorrecao);
                if (acertos < 0) acertos = 0;
            }
            aluno.setNota(acertos);
        }
    }

    public void exibirResultados() {
        System.out.println("\nResultados:");
        for (final Aluno aluno : alunos) {
            System.out.printf("Aluno: %s - Nota: %d/%d%n", aluno.getNome(), aluno.getNota(), gabarito.length);
        }
    }
}
