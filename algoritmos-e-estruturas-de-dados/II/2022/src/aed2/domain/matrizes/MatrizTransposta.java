package aed2.domain.matrizes;

import aed2.domain.algoritmos.VetorUtil;

public final class MatrizTransposta {

    private MatrizTransposta() {}

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
}
