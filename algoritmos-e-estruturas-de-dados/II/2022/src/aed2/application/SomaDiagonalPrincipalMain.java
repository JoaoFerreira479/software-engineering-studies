package aed2.application;

import aed2.domain.recursao.SomaDiagonalPrincipal;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;
import aed2.infrastructure.io.LeituraDados;

import java.util.Scanner;

public final class SomaDiagonalPrincipalMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            int n = entrada.lerInteiro("Digite a dimensão da matriz quadrada (N x N): ");
            if (n <= 0) {
                Console.erro("O valor deve ser maior que zero.");
                return;
            }
            Console.println("Digite os elementos da matriz:");
            int[][] matriz = LeituraDados.lerMatriz(entrada, n, n);
            int soma = SomaDiagonalPrincipal.calcularSomaDiagonalPrincipal(matriz);
            Console.println("A soma da diagonal principal é: " + soma);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }
}
