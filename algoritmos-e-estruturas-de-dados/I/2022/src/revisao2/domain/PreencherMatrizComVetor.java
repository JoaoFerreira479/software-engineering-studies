package revisao2.domain;

public final class PreencherMatrizComVetor {

    private static final int MULTIPLICADOR_LINHA_0 = 2;
    private static final int SOMA_LINHA_1 = 3;

    private PreencherMatrizComVetor() {
    }

    public static int[][] construirMatriz(final int[] vetor) {
        if (vetor == null || vetor.length == 0) {
            throw new IllegalArgumentException("Vetor deve ter pelo menos um elemento.");
        }
        int n = vetor.length;
        int[][] matriz = new int[3][n];

        for (int i = 0; i < n; i++) {
            matriz[0][i] = vetor[i] * MULTIPLICADOR_LINHA_0;
        }
        for (int i = 0; i < n; i++) {
            matriz[1][i] = vetor[i] + SOMA_LINHA_1;
        }
        for (int i = 0; i < n; i++) {
            int divisor = matriz[1][i];
            matriz[2][i] = divisor != 0 ? matriz[0][i] % divisor : 0;
        }
        return matriz;
    }
}
