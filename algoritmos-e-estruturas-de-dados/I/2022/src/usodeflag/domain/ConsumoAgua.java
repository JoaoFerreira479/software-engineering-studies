package usodeflag.domain;

public final class ConsumoAgua {

    private ConsumoAgua() {
    }

    public static ResultadoConsumo calcular(final double[] consumos, final int quantidade) {
        if (consumos == null || quantidade < 0 || quantidade > consumos.length) {
            throw new IllegalArgumentException("Argumentos inválidos.");
        }
        if (quantidade == 0) {
            return new ResultadoConsumo(false, 0, 0, 0, 0);
        }
        double soma = 0;
        double maior = Double.NEGATIVE_INFINITY;
        double menor = Double.POSITIVE_INFINITY;
        for (int i = 0; i < quantidade; i++) {
            double v = consumos[i];
            soma += v;
            if (v > maior) maior = v;
            if (v < menor) menor = v;
        }
        double media = soma / quantidade;
        return new ResultadoConsumo(true, quantidade, media, maior, menor);
    }

    public record ResultadoConsumo(
            boolean temDados,
            int quantidade,
            double media,
            double maior,
            double menor
    ) {
    }
}
