package programarcomputadoresrepeticao;

import java.util.Arrays;
import java.util.Scanner;

public final class PesquisaNotas {

    private static final int TOTAL_NOTAS = 10;
    private static final double EPSILON = 1e-6;

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final double[] notas = new double[TOTAL_NOTAS];
            boolean continuar = true;
            do {
                exibirMenu();
                final int opcao = EntradaUtil.lerInteiro(scanner, "Escolha uma opção: ");

                switch (opcao) {
                    case 1 -> lerNotas(scanner, notas);
                    case 2 -> pesquisarNota(scanner, notas);
                    case 3 -> mostrarNotas(notas);
                    case 0 -> {
                        continuar = false;
                        System.out.println("Encerrando o programa...");
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } while (continuar);
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Ler notas");
        System.out.println("2. Pesquisar nota");
        System.out.println("3. Mostrar notas");
        System.out.println("0. Sair");
    }

    private static void lerNotas(final Scanner scanner, final double[] notas) {
        System.out.println("Digite as 10 notas (valores entre 0 e 10):");
        for (int i = 0; i < TOTAL_NOTAS; i++) {
            notas[i] = lerNotaValida(scanner, i + 1);
        }
        System.out.println("Notas registradas com sucesso!");
    }

    private static double lerNotaValida(final Scanner scanner, final int indice) {
        while (true) {
            final double nota = EntradaUtil.lerDouble(scanner, "Nota " + indice + ": ");
            if (nota >= 0 && nota <= 10) return nota;
            System.out.println("A nota deve estar entre 0 e 10. Tente novamente.");
        }
    }

    private static void pesquisarNota(final Scanner scanner, final double[] notas) {
        final double notaPesquisa = EntradaUtil.lerDouble(scanner, "Digite a nota que deseja pesquisar: ");
        if (notaPesquisa < 0 || notaPesquisa > 10) {
            System.out.println("A nota deve estar entre 0 e 10.");
            return;
        }
        final boolean encontrada = Arrays.stream(notas).anyMatch(n -> Math.abs(n - notaPesquisa) < EPSILON);
        System.out.printf(encontrada ? "A nota %.1f foi encontrada no vetor.%n" : "A nota %.1f não foi encontrada no vetor.%n", notaPesquisa);
    }

    private static void mostrarNotas(final double[] notas) {
        System.out.println("\n=== Notas cadastradas ===");
        for (int i = 0; i < TOTAL_NOTAS; i++) {
            System.out.printf("Nota %d: %.1f%n", i + 1, notas[i]);
        }
    }
}
