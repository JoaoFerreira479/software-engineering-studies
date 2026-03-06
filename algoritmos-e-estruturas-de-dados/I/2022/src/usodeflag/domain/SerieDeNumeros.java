package usodeflag.domain;

public final class SerieDeNumeros {

    private SerieDeNumeros() {
    }

    public static ResultadoSerie calcular(final double[] valores, final int quantidade) {
        if (valores == null || quantidade < 0 || quantidade > valores.length) {
            throw new IllegalArgumentException("Argumentos inválidos.");
        }
        if (quantidade == 0) {
            return new ResultadoSerie(false, 0, 0, 0, 0, 0);
        }
        double soma = 0;
        double maior = Double.NEGATIVE_INFINITY;
        double menor = Double.POSITIVE_INFINITY;
        for (int i = 0; i < quantidade; i++) {
            double v = valores[i];
            soma += v;
            if (v > maior) maior = v;
            if (v < menor) menor = v;
        }
        double media = soma / quantidade;
        return new ResultadoSerie(true, soma, quantidade, media, maior, menor);
    }

    public record ResultadoSerie(
            boolean temDados,
            double soma,
            int quantidade,
            double media,
            double maior,
            double menor
    ) {
    }
}
