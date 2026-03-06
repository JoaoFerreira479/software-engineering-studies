package revisao1.app;

import revisao1.domain.TerceiroMenorElemento;
import revisao1.io.Console;
import revisao1.io.EntradaUsuario;

import java.util.Scanner;

public final class TerceiroMenorElementoMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int linhas = entrada.lerInteiro("Digite o número de linhas da matriz: ");
            int colunas = entrada.lerInteiro("Digite o número de colunas da matriz: ");
            TerceiroMenorElemento.validarDimensoes(linhas, colunas);
            Console.println("Digite os valores da matriz:");
            int[][] matriz = LeituraDados.lerMatriz(entrada, linhas, colunas);
            int terceiroMenor = TerceiroMenorElemento.terceiroMenor(matriz);
            Console.printf("O terceiro menor elemento da matriz é: %d%n", terceiroMenor);
        } catch (Exception e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
