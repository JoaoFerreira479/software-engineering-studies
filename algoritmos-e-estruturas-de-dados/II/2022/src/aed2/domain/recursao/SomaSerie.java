package aed2.domain.recursao;

public final class SomaSerie {

    private SomaSerie() {}

    public static double calcularSomaSerie(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n deve ser positivo: " + n);
        }
        double soma = 0.0;
        for (int i = 1; i <= n; i++) {
            soma += 1.0 / i;
        }
        return soma;
    }
}
