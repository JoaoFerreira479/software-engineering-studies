package revisao1.domain;

public final class VerificarMatrizesIguais {

    private VerificarMatrizesIguais() {
    }

    public static void validarDimensoes(final int linhas, final int colunas) {
        if (linhas <= 0 || colunas <= 0) {
            throw new IllegalArgumentException("As matrizes devem ter dimensões positivas.");
        }
    }

    public static boolean iguais(final int[][] a, final int[][] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length) return false;
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }
}
