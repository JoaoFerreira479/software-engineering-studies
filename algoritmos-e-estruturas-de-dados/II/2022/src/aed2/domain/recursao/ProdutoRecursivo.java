package aed2.domain.recursao;

import aed2.domain.algoritmos.VetorUtil;

public final class ProdutoRecursivo {

    private ProdutoRecursivo() {}

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
}
