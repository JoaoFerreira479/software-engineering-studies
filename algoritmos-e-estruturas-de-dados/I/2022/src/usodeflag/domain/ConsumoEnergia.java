package usodeflag.domain;

public final class ConsumoEnergia {

    public static final int TIPO_RESIDENCIA = 1;
    public static final int TIPO_COMERCIO = 2;
    public static final int ENCERRAR = 0;

    private ConsumoEnergia() {
    }

    public static ResultadoConsumoEnergia calcular(final int[] tipos, final double[] consumos, final int n) {
        if (tipos == null || consumos == null || n < 0 || n > tipos.length || n > consumos.length) {
            throw new IllegalArgumentException("Argumentos inválidos.");
        }
        int countResidencia = 0;
        int countComercio = 0;
        double somaResidencia = 0;
        double somaComercio = 0;
        double somaTotal = 0;

        for (int i = 0; i < n; i++) {
            int tipo = tipos[i];
            double consumo = consumos[i];
            somaTotal += consumo;
            if (tipo == TIPO_RESIDENCIA) {
                countResidencia++;
                somaResidencia += consumo;
            } else if (tipo == TIPO_COMERCIO) {
                countComercio++;
                somaComercio += consumo;
            }
        }

        double mediaResidencia = countResidencia > 0 ? somaResidencia / countResidencia : 0;
        double mediaComercio = countComercio > 0 ? somaComercio / countComercio : 0;
        double mediaBairro = n > 0 ? somaTotal / n : 0;

        return new ResultadoConsumoEnergia(
                countResidencia,
                somaResidencia,
                mediaResidencia,
                countComercio,
                somaComercio,
                mediaComercio,
                somaTotal,
                n,
                mediaBairro
        );
    }

    public record ResultadoConsumoEnergia(
            int contadorResidencias,
            double somaResidencias,
            double mediaResidencias,
            int contadorComercio,
            double somaComercio,
            double mediaComercio,
            double somaTotal,
            int totalConsumidores,
            double mediaBairro
    ) {
    }
}
