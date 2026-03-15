package aed2.application;

import aed2.domain.algoritmos.VetorUtil;
import aed2.domain.matrizes.MatrizTransposta;
import aed2.domain.recursao.ProdutoRecursivo;
import aed2.infrastructure.io.Console;

public final class Guia2Main {

    private Guia2Main() {}

    public static void main(String[] args) {
        try {
            executarExemploProdutoRecursivo();
            executarExemploSomatorioRecursivo();
            executarExemploMatrizTransposta();
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }

    private static void executarExemploProdutoRecursivo() {
        int a = 5, b = 3;
        Console.println("Produto de " + a + " e " + b + ": " + ProdutoRecursivo.produtoRecursivo(a, b));
    }

    private static void executarExemploSomatorioRecursivo() {
        int[] vetor = { 1, 2, 3, 4, 5 };
        Console.println("Somatório do vetor: " + ProdutoRecursivo.somatorioRecursivo(vetor, vetor.length));
    }

    private static void executarExemploMatrizTransposta() {
        int[][] matriz = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int[][] transposta = MatrizTransposta.matrizTransposta(matriz);
        Console.println("Matriz transposta:");
        for (int[] linha : transposta) {
            Console.println(VetorUtil.formatar(linha));
        }
    }
}
