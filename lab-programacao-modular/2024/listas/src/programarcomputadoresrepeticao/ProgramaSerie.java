package programarcomputadoresrepeticao;

import java.util.Scanner;

public final class ProgramaSerie {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Cálculo da Série ===");
            System.out.println("Digite um valor inteiro para N (0 para sair):");

            while (true) {
                final int n = EntradaUtil.lerInteiro(scanner, "\nInforme o valor de N: ");
                if (n == 0) {
                    System.out.println("Encerrando o programa. Obrigado!");
                    break;
                }
                try {
                    final double resultado = calcularSerie(n);
                    System.out.printf("Resultado da série para N = %d: %.2f%n", n, resultado);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }
        }
    }

    public static double calcularSerie(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("O valor de N deve ser maior que zero.");
        }
        double soma = 0.0;
        for (int i = 1; i <= n; i++) {
            soma += (double) i / (n - i + 1);
        }
        return soma;
    }
}
