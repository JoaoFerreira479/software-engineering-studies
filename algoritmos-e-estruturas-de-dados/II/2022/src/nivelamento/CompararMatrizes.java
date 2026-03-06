package nivelamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class CompararMatrizes {

    private CompararMatrizes() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Primeira Matriz ===");
            int[][] matriz1 = lerMatriz(scanner);
            System.out.println("=== Segunda Matriz ===");
            int[][] matriz2 = lerMatriz(scanner);

            if (!mesmasDimensoes(matriz1, matriz2)) {
                System.out.println("As matrizes têm tamanhos diferentes e, portanto, não são iguais.");
            } else {
                boolean iguais = saoIguais(matriz1, matriz2);
                System.out.println(iguais ? "As matrizes são iguais." : "As matrizes não são iguais.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erro inesperado: Entrada inválida.");
        }
    }

    private static int[][] lerMatriz(Scanner scanner) {
        int linhas = lerValorInteiro(scanner, "Digite o número de linhas: ", 1);
        int colunas = lerValorInteiro(scanner, "Digite o número de colunas: ", 1);

        int[][] matriz = new int[linhas][colunas];
        System.out.println("Preenchendo a matriz:");
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = lerValorInteiro(scanner, String.format("Elemento [%d][%d]: ", i, j), Integer.MIN_VALUE);
            }
        }
        return matriz;
    }

    private static boolean mesmasDimensoes(int[][] a, int[][] b) {
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        if (a.length == 0) return true;
        return a[0] != null && b[0] != null && a[0].length == b[0].length;
    }

    private static boolean saoIguais(int[][] a, int[][] b) {
        if (a == null || b == null || a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null || b[i] == null || a[i].length != b[i].length) return false;
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }

    private static int lerValorInteiro(Scanner scanner, String mensagem, int minimo) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                if (valor < minimo) {
                    throw new IllegalArgumentException("O valor deve ser maior ou igual a " + minimo + ".");
                }
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro válido.");
                scanner.nextLine();
            }
        }
    }
}
