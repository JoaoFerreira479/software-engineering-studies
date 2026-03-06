package revisao1.domain;

public final class IntercalarVetores {

    private IntercalarVetores() {
    }

    public static void validarTamanhos(final int m, final int n) {
        if (m <= 0 || n <= 0) {
            throw new IllegalArgumentException("Os tamanhos dos vetores devem ser maiores que zero.");
        }
    }

    public static int[] intercalar(final int[] v1, final int[] v2) {
        int[] r = new int[v1.length + v2.length];
        int i = 0, j = 0, k = 0;
        while (i < v1.length && j < v2.length) {
            r[k++] = v1[i++];
            r[k++] = v2[j++];
        }
        while (i < v1.length) r[k++] = v1[i++];
        while (j < v2.length) r[k++] = v2[j++];
        return r;
    }
}
