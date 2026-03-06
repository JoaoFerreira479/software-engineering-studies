package conceitosbasicosprogramacao.domain;

public final class AreaTrapezio {

    private AreaTrapezio() {
    }

    public static double area(final double baseMaior, final double baseMenor, final double altura) {
        if (baseMaior <= 0 || baseMenor <= 0 || altura <= 0) {
            throw new IllegalArgumentException(
                    "Base maior, base menor e altura devem ser positivos.");
        }
        return ((baseMaior + baseMenor) * altura) / 2.0;
    }
}
