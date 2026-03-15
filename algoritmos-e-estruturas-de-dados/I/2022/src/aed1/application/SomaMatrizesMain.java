package aed1.application;

import aed1.domain.matrizes.SomaMatrizes;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;
import aed1.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class SomaMatrizesMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int linhas = entrada.lerInteiro("Digite o número de linhas das matrizes: ");
            int colunas = entrada.lerInteiro("Digite o número de colunas das matrizes: ");
            SomaMatrizes.validarDimensoes(linhas, colunas);
            Console.println("Digite os valores da primeira matriz:");
            int[][] m1 = LeituraDados.lerMatriz(entrada, linhas, colunas);
            Console.println("Digite os valores da segunda matriz:");
            int[][] m2 = LeituraDados.lerMatriz(entrada, linhas, colunas);
            int[][] soma = SomaMatrizes.somar(m1, m2);
            Console.println("Matriz resultante (soma):");
            exibirMatriz(soma);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }

    private static void exibirMatriz(final int[][] m) {
        for (int[] linha : m) {
            for (int v : linha) Console.print(v + " ");
            Console.println("");
        }
    }
}
