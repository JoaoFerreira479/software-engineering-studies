package revisao2.app;

import revisao2.domain.MatrizComSomaColunas;
import revisao2.io.Console;
import revisao2.io.EntradaUsuario;

import java.util.Scanner;

public final class MatrizComSomaColunasMain {

    private static final int LINHAS = 4;
    private static final int COLUNAS = 4;
    private static final int LINHAS_PREENCHIDAS_PELO_USUARIO = 3;

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int[][] matriz = new int[LINHAS][COLUNAS];
            Console.println("Preenchendo a matriz (as 3 primeiras linhas):");
            for (int i = 0; i < LINHAS_PREENCHIDAS_PELO_USUARIO; i++) {
                for (int j = 0; j < COLUNAS; j++) {
                    matriz[i][j] = entrada.lerInteiro(String.format("Digite o valor na posição [%d][%d]: ", i, j));
                }
            }

            MatrizComSomaColunas.preencherUltimaLinhaComSomaColunas(matriz, LINHAS_PREENCHIDAS_PELO_USUARIO);

            Console.println("Matriz final com a soma das colunas na última linha:");
            exibirMatriz(matriz);
        } catch (Exception e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }

    private static void exibirMatriz(final int[][] m) {
        for (int[] linha : m) {
            for (int v : linha) Console.print(v + "\t");
            Console.println("");
        }
    }
}
