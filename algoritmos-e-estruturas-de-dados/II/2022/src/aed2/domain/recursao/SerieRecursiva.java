package aed2.domain.recursao;

public final class SerieRecursiva {

    private SerieRecursiva() {}

    public static double calcularSerie(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N deve ser positivo: " + n);
        }
        return calcularSerieAuxiliar(n, 1);
    }

    private static double calcularSerieAuxiliar(int n, int atual) {
        if (atual > n) return 0;
        double termo = (atual % 2 == 0) ? -1.0 / atual : 1.0 / atual;
        return termo + calcularSerieAuxiliar(n, atual + 1);
    }
}
