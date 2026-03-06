package programarcomputadoresideiasedesafios;

import java.util.Scanner;

public final class ProvasVF {

    private static final int NUM_QUESTOES = 20;

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bem-vindo ao programa de correção de Provas Verdadeiro ou Falso!");
            final Prova prova = new Prova(NUM_QUESTOES);

            prova.cadastrarGabarito(scanner);

            while (true) {
                final String nomeAluno = EntradaUtil.lerLinha(scanner, "\nDigite o nome do aluno (ou 'fim' para encerrar): ");
                if (nomeAluno.equalsIgnoreCase("fim")) break;
                prova.cadastrarRespostasAluno(scanner, nomeAluno);
            }

            final int fatorCorrecao = EntradaUtil.lerInteiro(scanner, "\nDigite o fator de correção (0 para desativar, 2 ou 3 para ativar): ");
            prova.corrigirProvas(fatorCorrecao);
            prova.exibirResultados();
        }
    }
}
