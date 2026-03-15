package aed2.application;

import aed2.domain.lista2.Fracao;
import aed2.infrastructure.io.Console;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public final class SomatoriosNovoMain {

    private SomatoriosNovoMain() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                exibirMenu();
                opcao = lerInteiro(scanner, "Opção");

                switch (opcao) {
                    case 1 -> calcularSomatorioPotencia(1, 5, 3);
                    case 2 -> calcularSomatorioBasePotencia(1, 6, 3);
                    case 3 -> calcularSomatorioXPotencia(scanner);
                    case 4 -> calcularSomatorioAlternado(1, 6, 3);
                    case 5 -> calcularSomatorioFracoesAlternado(1, 3);
                    case 0 -> Console.println("Programa encerrado.");
                    default -> Console.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);
        }
    }

    private static void exibirMenu() {
        Console.println("\n=== Calculadora de Somatórios ===");
        Console.println("1) ∑ n=1 até 5 de n^3");
        Console.println("2) ∑ n=1 até 6 de 3^n");
        Console.println("3) ∑ i=1 até N de x_i^3");
        Console.println("4) ∑ n=1 até 6 de (-1)^n * n^3");
        Console.println("5) ∑ i=1 até 3 de (-1)^(i+1) / (2i+1)");
        Console.println("0) Sair");
        Console.print("\nOpção: ");
    }

    private static int lerInteiro(Scanner scanner, String rotulo) {
        Objects.requireNonNull(scanner, "scanner");
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                Console.println("Erro: Entrada inválida. Digite um número inteiro.");
                scanner.nextLine();
                if (rotulo != null && !rotulo.isEmpty()) {
                    Console.print(rotulo + ": ");
                }
            }
        }
    }

    private static void calcularSomatorioPotencia(int inicio, int fim, int potencia) {
        int soma = 0;
        StringBuilder passos = new StringBuilder();
        for (int n = inicio; n <= fim; n++) {
            int termo = (int) Math.pow(n, potencia);
            passos.append(termo).append(n < fim ? " + " : "");
            soma += termo;
        }
        exibirResultado("∑ n=" + inicio + " até " + fim + " de n^" + potencia, passos.toString(), String.valueOf(soma));
    }

    private static void calcularSomatorioBasePotencia(int inicio, int fim, int base) {
        int soma = 0;
        StringBuilder passos = new StringBuilder();
        for (int n = inicio; n <= fim; n++) {
            int termo = (int) Math.pow(base, n);
            passos.append(termo).append(n < fim ? " + " : "");
            soma += termo;
        }
        exibirResultado("∑ n=" + inicio + " até " + fim + " de " + base + "^n", passos.toString(), String.valueOf(soma));
    }

    private static void calcularSomatorioXPotencia(Scanner scanner) {
        Console.print("\nDigite o valor de N: ");
        int n = lerInteiro(scanner, "N");
        if (n <= 0) {
            Console.println("N deve ser positivo.");
            return;
        }
        int soma = 0;
        StringBuilder passos = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            Console.print("Digite o valor de x_" + i + ": ");
            int x = lerInteiro(scanner, "x_" + i);
            int termo = (int) Math.pow(x, 3);
            passos.append(termo).append(i < n ? " + " : "");
            soma += termo;
        }
        exibirResultado("∑ i=1 até " + n + " de x_i^3", passos.toString(), String.valueOf(soma));
    }

    private static void calcularSomatorioAlternado(int inicio, int fim, int potencia) {
        int soma = 0;
        StringBuilder passos = new StringBuilder();
        for (int n = inicio; n <= fim; n++) {
            int sinal = (n % 2 == 0) ? 1 : -1;
            int termo = sinal * (int) Math.pow(n, potencia);
            passos.append(termo).append(n < fim ? " + " : "");
            soma += termo;
        }
        exibirResultado("∑ n=" + inicio + " até " + fim + " de (-1)^n * n^" + potencia, passos.toString(), String.valueOf(soma));
    }

    private static void calcularSomatorioFracoesAlternado(int inicio, int fim) {
        Fracao soma = new Fracao(0, 1);
        StringBuilder passos = new StringBuilder();
        for (int i = inicio; i <= fim; i++) {
            int sinal = (i % 2 == 0) ? -1 : 1;
            Fracao termo = new Fracao(sinal, 2 * i + 1);
            passos.append(termo).append(i < fim ? " + " : "");
            soma = soma.adicionar(termo);
        }
        exibirResultado("∑ i=" + inicio + " até " + fim + " de (-1)^(i+1) / (2i+1)", passos.toString(), soma.toString());
    }

    private static void exibirResultado(String descricao, String passos, String soma) {
        Console.println("\nResultado de " + descricao + ":");
        Console.println(passos + " = " + soma);
    }
}
