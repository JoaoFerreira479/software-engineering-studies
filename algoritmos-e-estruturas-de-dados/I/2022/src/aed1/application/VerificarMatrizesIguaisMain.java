package aed1.application;

import aed1.domain.matrizes.VerificarMatrizesIguais;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;
import aed1.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class VerificarMatrizesIguaisMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int linhas = entrada.lerInteiro("Digite o número de linhas das matrizes: ");
            int colunas = entrada.lerInteiro("Digite o número de colunas das matrizes: ");
            VerificarMatrizesIguais.validarDimensoes(linhas, colunas);
            Console.println("Digite os valores da primeira matriz:");
            int[][] m1 = LeituraDados.lerMatriz(entrada, linhas, colunas);
            Console.println("Digite os valores da segunda matriz:");
            int[][] m2 = LeituraDados.lerMatriz(entrada, linhas, colunas);
            boolean iguais = VerificarMatrizesIguais.iguais(m1, m2);
            Console.println(iguais ? "As matrizes são iguais." : "As matrizes são diferentes.");
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
