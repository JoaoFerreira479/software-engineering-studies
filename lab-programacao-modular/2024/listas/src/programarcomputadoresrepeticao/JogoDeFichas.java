package programarcomputadoresrepeticao;

public final class JogoDeFichas {

    private static final double LIMITE_APOSTA = 100.0;

    private JogoDeFichas() {
    }

    public static double calcularRateio(final CorFicha primeira, final CorFicha segunda) {
        if (primeira == null || segunda == null) {
            throw new NullPointerException("Cores não podem ser nulas.");
        }
        return switch (primeira) {
            case BRANCA -> switch (segunda) {
                case BRANCA -> 0.0;
                case PRETA -> 0.5;
            };
            case PRETA -> switch (segunda) {
                case BRANCA -> 0.5;
                case PRETA -> 2.0;
            };
        };
    }

    public static void validarAposta(final double valorAposta) {
        if (valorAposta < 0 || valorAposta > LIMITE_APOSTA) {
            throw new IllegalArgumentException("Valor da aposta deve estar entre 0 e " + LIMITE_APOSTA + ".");
        }
    }
}
