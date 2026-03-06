package revisao2.app;

import revisao2.domain.PreencherMatrizComVetor;
import revisao2.io.Console;
import revisao2.io.EntradaUsuario;

import java.util.Scanner;

public final class PreencherMatrizComVetorMain {

    private static final int TAMANHO_VETOR = 5;

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            Console.printf("Preenchendo o vetor de %d posições:%n", TAMANHO_VETOR);
            int[] vetor = new int[TAMANHO_VETOR];
            for (int i = 0; i < TAMANHO_VETOR; i++) {
                vetor[i] = entrada.lerInteiro(String.format("Digite o valor na posição %d: ", i));
            }

            Console.println("Vetor preenchido:");
            exibirVetor(vetor);

            int[][] matriz = PreencherMatrizComVetor.construirMatriz(vetor);
            Console.println("Matriz resultante:");
            exibirMatriz(matriz);
        } catch (Exception e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }

    private static void exibirVetor(final int[] v) {
        for (int x : v) Console.print(x + " ");
        Console.println("");
    }

    private static void exibirMatriz(final int[][] m) {
        for (int[] linha : m) {
            for (int v : linha) Console.print(v + "\t");
            Console.println("");
        }
    }
}
