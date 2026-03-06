package conceitosbasicosprogramacao.domain;

public final class CalculoDistancia {

    private CalculoDistancia() {
    }

    public static double hipotenusa(final double cateto1, final double cateto2) {
        if (cateto1 < 0 || cateto2 < 0) {
            throw new IllegalArgumentException("Distâncias não podem ser negativas.");
        }
        return Math.sqrt(cateto1 * cateto1 + cateto2 * cateto2);
    }
}
