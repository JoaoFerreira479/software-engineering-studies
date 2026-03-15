package aed1.domain.condicional;

public final class MenorNumero {

    private MenorNumero() {
    }

    public static int menor(final int a, final int b, final int c) {
        return Math.min(a, Math.min(b, c));
    }
}
