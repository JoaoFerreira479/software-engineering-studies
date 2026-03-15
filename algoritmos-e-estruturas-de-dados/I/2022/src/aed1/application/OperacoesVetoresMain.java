package aed1.application;

import aed1.domain.vetores.OperacoesVetores;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;
import aed1.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class OperacoesVetoresMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int n = entrada.lerInteiro("Digite o tamanho dos vetores: ");
            if (n <= 0) {
                Console.erro("Tamanho deve ser positivo.");
                return;
            }
            Console.println("Vetor 1:");
            int[] v1 = LeituraDados.lerVetor(entrada, n);
            Console.println("Vetor 2:");
            int[] v2 = LeituraDados.lerVetor(entrada, n);
            int[] soma = OperacoesVetores.somar(v1, v2);
            int[] mult = OperacoesVetores.multiplicar(v1, v2);
            Console.println("Soma dos vetores:");
            exibirVetor(soma);
            Console.println("Produto elemento a elemento:");
            exibirVetor(mult);
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
