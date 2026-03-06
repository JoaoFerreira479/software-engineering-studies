package programarcomputadoresalternativasdecisao;

public final class JogoDeFichas {

    private JogoDeFichas() {
    }

    /**
     * Retorna o multiplicador de rateio: 0 (BB), 0.5 (BP ou PB), 2.0 (PP).
     */
    public static double calcularRateio(final CorFicha primeira, final CorFicha segunda) {
        if (primeira == null || segunda == null) {
            throw new NullPointerException("As cores não podem ser nulas.");
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
}
