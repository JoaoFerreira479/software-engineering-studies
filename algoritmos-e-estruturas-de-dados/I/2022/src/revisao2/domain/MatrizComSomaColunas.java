package revisao2.domain;

public final class MatrizComSomaColunas {

    private MatrizComSomaColunas() {
    }

    public static void preencherUltimaLinhaComSomaColunas(final int[][] matriz, final int numLinhasComDados) {
        if (matriz == null || matriz.length == 0) {
            throw new IllegalArgumentException("Matriz inválida.");
        }
        int linhasTotal = matriz.length;
        if (numLinhasComDados < 1 || numLinhasComDados >= linhasTotal) {
            throw new IllegalArgumentException("numLinhasComDados deve ser entre 1 e " + (linhasTotal - 1) + ".");
        }
        int colunas = matriz[0].length;
        for (int j = 0; j < colunas; j++) {
            int soma = 0;
            for (int i = 0; i < numLinhasComDados; i++) {
                soma += matriz[i][j];
            }
            matriz[numLinhasComDados][j] = soma;
        }
    }
}
