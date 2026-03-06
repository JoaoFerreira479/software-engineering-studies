package nivelamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class SomaDiagonalPrincipal {

    private SomaDiagonalPrincipal() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = lerInteiroPositivo(scanner, "Digite a dimensão da matriz quadrada (N x N): ");
            int[][] matriz = preencherMatriz(scanner, n);
            int somaDiagonal = calcularSomaDiagonalPrincipal(matriz);
            System.out.println("A soma da diagonal principal é: " + somaDiagonal);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida.");
        }
    }

    public static int calcularSomaDiagonalPrincipal(int[][] matriz) {
        if (matriz == null || matriz.length == 0) {
            throw new IllegalArgumentException("Matriz inválida.");
        }
        int soma = 0;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i] == null || matriz[i].length != matriz.length) {
                throw new IllegalArgumentException("Matriz deve ser quadrada.");
            }
            soma += matriz[i][i];
        }
        return soma;
    }

    private static int[][] preencherMatriz(Scanner scanner, int tamanho) {
        int[][] matriz = new int[tamanho][tamanho];
        System.out.println("Digite os elementos da matriz:");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                matriz[i][j] = lerInteiro(scanner, String.format("Elemento [%d][%d]: ", i, j));
            }
        }
        return matriz;
    }

    private static int lerInteiroPositivo(Scanner scanner, String mensagem) {
        while (true) {
            int v = lerInteiro(scanner, mensagem);
            if (v > 0) return v;
            System.out.println("Erro: O valor deve ser maior que zero.");
        }
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro.");
                scanner.nextLine();
            }
        }
    }
}
