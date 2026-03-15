package aed2.application;

import aed2.domain.nivelamento.CompararMatrizes;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;
import aed2.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class CompararMatrizesMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            Console.println("=== Primeira Matriz ===");
            int linhas1 = lerPositivo(entrada, "Digite o número de linhas: ");
            int colunas1 = lerPositivo(entrada, "Digite o número de colunas: ");
            Console.println("Preenchendo a matriz:");
            int[][] matriz1 = LeituraDados.lerMatriz(entrada, linhas1, colunas1);

            Console.println("=== Segunda Matriz ===");
            int linhas2 = lerPositivo(entrada, "Digite o número de linhas: ");
            int colunas2 = lerPositivo(entrada, "Digite o número de colunas: ");
            Console.println("Preenchendo a matriz:");
            int[][] matriz2 = LeituraDados.lerMatriz(entrada, linhas2, colunas2);

            if (!CompararMatrizes.mesmasDimensoes(matriz1, matriz2)) {
                Console.println("As matrizes têm tamanhos diferentes e, portanto, não são iguais.");
            } else {
                boolean iguais = CompararMatrizes.saoIguais(matriz1, matriz2);
                Console.println(iguais ? "As matrizes são iguais." : "As matrizes não são iguais.");
            }
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }

    private static int lerPositivo(EntradaUsuario entrada, String msg) {
        while (true) {
            int v = entrada.lerInteiro(msg);
            if (v >= 1) return v;
            Console.erro("O valor deve ser maior ou igual a 1.");
        }
    }
}
