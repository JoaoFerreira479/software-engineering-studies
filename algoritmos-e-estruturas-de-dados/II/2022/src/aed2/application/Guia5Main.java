package aed2.application;

import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class Guia5Main {

    private Guia5Main() {}

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            executarExercicio1();
            executarExercicio2();
            executarExercicio3(entrada);
            executarExercicio4(entrada);
        }
    }

    private static void executarExercicio1() {
        Console.println("Exercício 1: Manipulação de Ponteiros");
        int inteiro = 10;
        double real = 20.5;
        char caractere = 'A';
        Console.println("Antes da modificação:");
        Console.println("Inteiro: " + inteiro);
        Console.println("Real: " + real);
        Console.println("Caractere: " + caractere);
        inteiro = 15;
        real = 25.75;
        caractere = 'B';
        Console.println("\nApós a modificação:");
        Console.println("Inteiro: " + inteiro);
        Console.println("Real: " + real);
        Console.println("Caractere: " + caractere);
    }

    private static void executarExercicio2() {
        Console.println("\nExercício 2: Comparação de Endereços");
        Integer num1 = 10;
        Integer num2 = 20;
        Console.println("Endereço de num1: " + System.identityHashCode(num1));
        Console.println("Endereço de num2: " + System.identityHashCode(num2));
        Console.println("Conteúdo do maior valor: " + Math.max(num1, num2));
    }

    private static void executarExercicio3(EntradaUsuario entrada) {
        Console.println("\nExercício 3: Alocação Dinâmica e Vetor");
        int n = entrada.lerInteiro("Digite a quantidade de elementos no vetor: ");
        if (n <= 0) {
            Console.erro("Tamanho do vetor inválido. Deve ser positivo.");
            return;
        }
        int[] vetor = new int[n];
        Console.println("Digite os elementos do vetor:");
        for (int i = 0; i < n; i++) {
            vetor[i] = entrada.lerInteiro("Elemento " + (i + 1) + ": ");
        }
        Console.println("\nValores do vetor:");
        for (int valor : vetor) {
            Console.print(valor + " ");
        }
        Console.println("");
    }

    private static void executarExercicio4(EntradaUsuario entrada) {
        Console.println("\nExercício 4: Implementação de Matriz");
        int linhas = entrada.lerInteiro("Digite o número de linhas da matriz: ");
        int colunas = entrada.lerInteiro("Digite o número de colunas da matriz: ");
        if (linhas <= 0 || colunas <= 0) {
            Console.erro("Dimensões da matriz inválidas. Devem ser positivas.");
            return;
        }
        int[][] matriz = new int[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = i + j;
            }
        }
        Console.println("Matriz preenchida:");
        for (int[] linha : matriz) {
            for (int valor : linha) {
                Console.print(valor + "\t");
            }
            Console.println("");
        }
    }
}
