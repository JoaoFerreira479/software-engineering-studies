package revisao1.domain;

public final class SomaMatrizes {

    private SomaMatrizes() {
    }

    public static void validarDimensoes(final int linhas, final int colunas) {
        if (linhas <= 0 || colunas <= 0) {
            throw new IllegalArgumentException("As matrizes devem ter dimensões positivas.");
        }
    }

    public static int[][] somar(final int[][] a, final int[][] b) {
        int linhas = a.length;
        int colunas = a[0].length;
        int[][] c = new int[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }
}
