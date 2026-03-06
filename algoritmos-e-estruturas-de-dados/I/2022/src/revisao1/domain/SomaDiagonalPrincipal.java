package revisao1.domain;

public final class SomaDiagonalPrincipal {

    private SomaDiagonalPrincipal() {
    }

    public static int soma(final int[][] matriz) {
        if (matriz == null || matriz.length == 0) {
            throw new IllegalArgumentException("Matriz inválida.");
        }
        int n = matriz.length;
        int soma = 0;
        for (int i = 0; i < n; i++) {
            soma += matriz[i][i];
        }
        return soma;
    }

    public static void validarTamanho(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("O tamanho da matriz deve ser maior que zero.");
        }
    }
}
