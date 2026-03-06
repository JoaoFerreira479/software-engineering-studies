package estruturascondicionais.domain;

public final class EquacaoSegundoGrau {

    private EquacaoSegundoGrau() {
    }

    public static ResultadoEquacao resolver(final double a, final double b, final double c) {
        if (a == 0) {
            throw new IllegalArgumentException("O coeficiente 'a' deve ser diferente de zero.");
        }
        double delta = b * b - 4 * a * c;
        if (delta < 0) {
            return new ResultadoEquacao(TipoRaizes.NENHUMA, 0, 0);
        }
        if (delta == 0) {
            double raiz = -b / (2 * a);
            return new ResultadoEquacao(TipoRaizes.UMA, raiz, 0);
        }
        double raiz1 = (-b + Math.sqrt(delta)) / (2 * a);
        double raiz2 = (-b - Math.sqrt(delta)) / (2 * a);
        return new ResultadoEquacao(TipoRaizes.DUAS, raiz1, raiz2);
    }

    public enum TipoRaizes {
        NENHUMA, UMA, DUAS
    }

    public record ResultadoEquacao(TipoRaizes tipo, double raiz1, double raiz2) {
    }
}
