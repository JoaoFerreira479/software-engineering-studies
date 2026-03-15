package aed1.application;

import aed1.domain.vetores.PosicoesParesRecursivo;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;
import aed1.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class PosicoesParesRecursivoMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int n = entrada.lerInteiro("Digite o tamanho do vetor: ");
            PosicoesParesRecursivo.validarTamanho(n);
            Console.println("Digite os valores do vetor:");
            int[] vetor = LeituraDados.lerVetor(entrada, n);
            int[] pares = PosicoesParesRecursivo.elementosNasPosicoesPares(vetor);
            Console.println("Elementos nas posições pares (0, 2, 4, ...):");
            exibirVetor(pares);
        } catch (IllegalArgumentException e) {
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
