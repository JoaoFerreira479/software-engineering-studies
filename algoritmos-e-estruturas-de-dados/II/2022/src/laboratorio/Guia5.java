package laboratorio;

import java.util.Scanner;

public final class Guia5 {

    private Guia5() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            executarExercicio1();
            executarExercicio2();
            executarExercicio3(scanner);
            executarExercicio4(scanner);
        }
    }

    private static void executarExercicio1() {
        System.out.println("Exercício 1: Manipulação de Ponteiros");
        int inteiro = 10;
        double real = 20.5;
        char caractere = 'A';

        System.out.println("Antes da modificação:");
        System.out.println("Inteiro: " + inteiro);
        System.out.println("Real: " + real);
        System.out.println("Caractere: " + caractere);

        inteiro = 15;
        real = 25.75;
        caractere = 'B';

        System.out.println("\nApós a modificação:");
        System.out.println("Inteiro: " + inteiro);
        System.out.println("Real: " + real);
        System.out.println("Caractere: " + caractere);
    }

    private static void executarExercicio2() {
        System.out.println("\nExercício 2: Comparação de Endereços");
        Integer num1 = 10;
        Integer num2 = 20;
        System.out.println("Endereço de num1: " + System.identityHashCode(num1));
        System.out.println("Endereço de num2: " + System.identityHashCode(num2));
        System.out.println("Conteúdo do maior valor: " + Math.max(num1, num2));
    }

    private static void executarExercicio3(Scanner scanner) {
        System.out.println("\nExercício 3: Alocação Dinâmica e Vetor");
        System.out.print("Digite a quantidade de elementos no vetor: ");
        if (!scanner.hasNextInt()) {
            System.err.println("Entrada inválida. Esperado número inteiro.");
            return;
        }
        int n = scanner.nextInt();
        if (n <= 0) {
            System.err.println("Tamanho do vetor inválido. Deve ser positivo.");
            return;
        }

        int[] vetor = new int[n];
        System.out.println("Digite os elementos do vetor:");
        for (int i = 0; i < n; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            if (!scanner.hasNextInt()) {
                System.err.println("Valor inválido. Use inteiros.");
                return;
            }
            vetor[i] = scanner.nextInt();
        }

        System.out.println("\nValores do vetor:");
        for (int valor : vetor) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }

    private static void executarExercicio4(Scanner scanner) {
        System.out.println("\nExercício 4: Implementação de Matriz");
        System.out.print("Digite o número de linhas da matriz: ");
        if (!scanner.hasNextInt()) {
            System.err.println("Entrada inválida.");
            return;
        }
        int linhas = scanner.nextInt();
        System.out.print("Digite o número de colunas da matriz: ");
        if (!scanner.hasNextInt()) {
            System.err.println("Entrada inválida.");
            return;
        }
        int colunas = scanner.nextInt();

        if (linhas <= 0 || colunas <= 0) {
            System.err.println("Dimensões da matriz inválidas. Devem ser positivas.");
            return;
        }

        int[][] matriz = new int[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = i + j;
            }
        }

        System.out.println("Matriz preenchida:");
        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.print(valor + "\t");
            }
            System.out.println();
        }
    }
}
