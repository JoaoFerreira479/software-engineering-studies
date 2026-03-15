package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaSerie {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Console.println("=== Cálculo da Série ===");
            Console.println("Digite um valor inteiro para N (0 para sair):");

            while (true) {
                final int n = EntradaUtil.lerInteiro(scanner, "\nInforme o valor de N: ");
                if (n == 0) {
                    Console.println("Encerrando o programa. Obrigado!");
                    break;
                }
                try {
                    final double resultado = calcularSerie(n);
                    Console.printf("Resultado da série para N = %d: %.2f%n", n, resultado);
                } catch (IllegalArgumentException e) {
                    Console.println("Erro: " + e.getMessage());
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
