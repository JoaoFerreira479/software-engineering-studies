package revisao2.app;

import revisao2.domain.OperacoesVetores;
import revisao2.io.Console;
import revisao2.io.EntradaUsuario;

import java.util.Scanner;

public final class OperacoesVetoresMain {

    private static final int TAMANHO_VETOR = 10;

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            Console.println("Preenchendo o vetor X:");
            int[] vetorX = lerVetor(entrada, TAMANHO_VETOR);
            Console.println("Preenchendo o vetor Y:");
            int[] vetorY = lerVetor(entrada, TAMANHO_VETOR);

            Console.println("Resultado da soma dos vetores:");
            exibirVetor(OperacoesVetores.somar(vetorX, vetorY));
            Console.println("Resultado do produto dos vetores:");
            exibirVetor(OperacoesVetores.multiplicar(vetorX, vetorY));
        } catch (Exception e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }

    private static int[] lerVetor(final EntradaUsuario entrada, final int tamanho) {
        int[] v = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            v[i] = entrada.lerInteiro(String.format("Digite o valor na posição %d: ", i));
        }
        return v;
    }

    private static void exibirVetor(final int[] v) {
        for (int x : v) Console.print(x + " ");
        Console.println("");
    }
}
