package revisao1.app;

import revisao1.domain.PosicoesParesRecursivo;
import revisao1.io.Console;
import revisao1.io.EntradaUsuario;

import java.util.Scanner;

public final class PosicoesParesRecursivoMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int n = entrada.lerInteiro("Digite o tamanho do vetor (N): ");
            PosicoesParesRecursivo.validarTamanho(n);
            Console.println("Digite os valores do vetor:");
            int[] vetor = LeituraDados.lerVetor(entrada, n);
            Console.println("Vetor completo:");
            exibirVetor(vetor);
            int[] pares = PosicoesParesRecursivo.elementosNasPosicoesPares(vetor);
            Console.println("Elementos nas posições pares:");
            for (int i = 0; i < pares.length; i++) {
                Console.printf("Posição %d: %d%n", i * 2, pares[i]);
            }
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
}
