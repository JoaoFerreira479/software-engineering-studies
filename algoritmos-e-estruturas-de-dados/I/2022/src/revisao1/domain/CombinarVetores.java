package revisao1.domain;

public final class CombinarVetores {

    private CombinarVetores() {
    }

    public static void validarTamanho(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("O tamanho dos vetores deve ser maior que zero.");
        }
    }

    public static void validarTamanhos(final int m, final int n) {
        if (m <= 0 || n <= 0) {
            throw new IllegalArgumentException("Os tamanhos dos vetores devem ser maiores que zero.");
        }
    }

    public static int[] combinar(final int[] v1, final int[] v2) {
        int[] r = new int[v1.length + v2.length];
        System.arraycopy(v1, 0, r, 0, v1.length);
        System.arraycopy(v2, 0, r, v1.length, v2.length);
        return r;
    }
}
