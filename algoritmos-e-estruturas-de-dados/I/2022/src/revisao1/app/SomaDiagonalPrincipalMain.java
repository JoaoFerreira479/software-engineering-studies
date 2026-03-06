package revisao1.app;

import revisao1.domain.SomaDiagonalPrincipal;
import revisao1.io.Console;
import revisao1.io.EntradaUsuario;

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
        } catch (Exception e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
