package conceitosbasicosprogramacao.domain;

public final class SomaPA {

    private SomaPA() {
    }

    public static long soma(final int a1, final int r, final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Número de termos (n) deve ser maior que zero.");
        }
        long termo = 2L * a1 + (long) (n - 1) * r;
        return (long) n * termo / 2;
    }
}
