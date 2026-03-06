package conceitosbasicosprogramacao.domain;

public final class CompraPaes {

    private static final int CENTAVOS_POR_REAL = 100;

    private CompraPaes() {
    }

    public static int quantidadePaes(final double dinheiroReais, final int precoPaoCentavos) {
        validar(dinheiroReais, precoPaoCentavos);
        int centavos = (int) Math.round(dinheiroReais * CENTAVOS_POR_REAL);
        return centavos / precoPaoCentavos;
    }

    public static double trocoReais(final double dinheiroReais, final int precoPaoCentavos) {
        validar(dinheiroReais, precoPaoCentavos);
        int centavos = (int) Math.round(dinheiroReais * CENTAVOS_POR_REAL);
        int trocoCentavos = centavos % precoPaoCentavos;
        return trocoCentavos / (double) CENTAVOS_POR_REAL;
    }

    private static void validar(final double dinheiroReais, final int precoPaoCentavos) {
        if (dinheiroReais < 0) {
            throw new IllegalArgumentException("Dinheiro não pode ser negativo.");
        }
        if (precoPaoCentavos <= 0) {
            throw new IllegalArgumentException("Preço do pão deve ser maior que zero.");
        }
    }
}
