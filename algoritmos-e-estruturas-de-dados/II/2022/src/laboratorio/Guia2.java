package laboratorio;

import laboratorio.algoritmos.VetorUtil;

public final class Guia2 {

    private Guia2() {}

    public static int produtoRecursivo(int a, int b) {
        if (b < 0) {
            throw new IllegalArgumentException("b não pode ser negativo: " + b);
        }
        if (b == 0) return 0;
        return a + produtoRecursivo(a, b - 1);
    }

    public static int somatorioRecursivo(int[] vetor, int tamanho) {
        VetorUtil.validarVetor(vetor);
        if (tamanho < 0 || tamanho > vetor.length) {
            throw new IllegalArgumentException("Tamanho inválido: " + tamanho);
        }
        if (tamanho == 0) return 0;
        return vetor[tamanho - 1] + somatorioRecursivo(vetor, tamanho - 1);
    }

    public static int[][] matrizTransposta(int[][] matriz) {
        VetorUtil.validarMatriz(matriz);
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        int[][] transposta = new int[colunas][linhas];
        for (int i = 0; i < linhas; i++) {
            if (matriz[i].length != colunas) {
                throw new IllegalArgumentException("Matriz jagged não suportada");
            }
            for (int j = 0; j < colunas; j++) {
                transposta[j][i] = matriz[i][j];
            }
        }
        return transposta;
    }

    public static void exibirMatrizTransposta(int[][] matriz) {
        int[][] transposta = matrizTransposta(matriz);
        System.out.println("Matriz transposta:");
        for (int[] linha : transposta) {
            System.out.println(VetorUtil.formatar(linha));
        }
    }

    public static void main(String[] args) {
        try {
            executarExemploProdutoRecursivo();
            executarExemploSomatorioRecursivo();
            executarExemploMatrizTransposta();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    private static void executarExemploProdutoRecursivo() {
        int a = 5, b = 3;
        System.out.println("Produto de " + a + " e " + b + ": " + produtoRecursivo(a, b));
    }

    private static void executarExemploSomatorioRecursivo() {
        int[] vetor = { 1, 2, 3, 4, 5 };
        System.out.println("Somatório do vetor: " + somatorioRecursivo(vetor, vetor.length));
    }

    private static void executarExemploMatrizTransposta() {
        int[][] matriz = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        exibirMatrizTransposta(matriz);
    }
}
