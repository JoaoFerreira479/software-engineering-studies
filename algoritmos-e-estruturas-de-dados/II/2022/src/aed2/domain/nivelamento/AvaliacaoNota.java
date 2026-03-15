package aed2.domain.nivelamento;

public final class AvaliacaoNota {

    private static final double NOTA_MAXIMA = 10.0;
    private static final double NOTA_MINIMA = 0.0;

    private AvaliacaoNota() {}

    public static String classificarNota(double nota) {
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException("Nota fora do intervalo [0, 10]: " + nota);
        }
        if (nota >= 8.0) return "Ótimo";
        if (nota >= 7.0) return "Bom";
        if (nota >= 5.0) return "Regular";
        return "Insatisfatório";
    }
}
