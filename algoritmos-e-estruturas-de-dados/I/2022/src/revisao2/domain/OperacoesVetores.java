package revisao2.domain;

public final class OperacoesVetores {

    private OperacoesVetores() {
    }

    public static int[] somar(final int[] v1, final int[] v2) {
        validarMesmoTamanho(v1, v2);
        int[] r = new int[v1.length];
        for (int i = 0; i < v1.length; i++) {
            r[i] = v1[i] + v2[i];
        }
        return r;
    }

    public static int[] multiplicar(final int[] v1, final int[] v2) {
        validarMesmoTamanho(v1, v2);
        int[] r = new int[v1.length];
        for (int i = 0; i < v1.length; i++) {
            r[i] = v1[i] * v2[i];
        }
        return r;
    }

    private static void validarMesmoTamanho(final int[] v1, final int[] v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vetores não podem ser null.");
        }
        if (v1.length != v2.length) {
            throw new IllegalArgumentException("Vetores devem ter o mesmo tamanho.");
        }
    }
}
