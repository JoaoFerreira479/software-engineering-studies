package revisao1.domain;

public final class PosicoesParesRecursivo {

    private PosicoesParesRecursivo() {
    }

    public static void validarTamanho(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("O tamanho do vetor deve ser maior que zero.");
        }
    }

    public static int[] elementosNasPosicoesPares(final int[] vetor) {
        return coletarParesRecursivo(vetor, 0);
    }

    private static int[] coletarParesRecursivo(final int[] v, final int indice) {
        if (indice >= v.length) {
            return new int[0];
        }
        int[] resto = coletarParesRecursivo(v, indice + 2);
        int[] resultado = new int[resto.length + 1];
        resultado[0] = v[indice];
        System.arraycopy(resto, 0, resultado, 1, resto.length);
        return resultado;
    }
}
