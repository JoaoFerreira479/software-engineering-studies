package aed1.application;

import aed1.domain.matrizes.SomaDiagonalPrincipal;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;
import aed1.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class SomaDiagonalPrincipalMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int n = entrada.lerInteiro("Digite o tamanho da matriz quadrada (N x N): ");
            SomaDiagonalPrincipal.validarTamanho(n);
            int[][] matriz = LeituraDados.lerMatrizQuadrada(entrada, n);
            int soma = SomaDiagonalPrincipal.soma(matriz);
            Console.printf("A soma da diagonal principal é: %d%n", soma);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
