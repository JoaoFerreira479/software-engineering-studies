package conceitosbasicosprogramacao.domain;

public final class ConversaoMedidas {

    private static final int FATOR_DECIMETROS = 10;
    private static final int FATOR_CENTIMETROS = 100;
    private static final int FATOR_MILIMETROS = 1000;

    private ConversaoMedidas() {
    }

    public static double metrosParaDecimetros(final double metros) {
        validar(metros);
        return metros * FATOR_DECIMETROS;
    }

    public static double metrosParaCentimetros(final double metros) {
        validar(metros);
        return metros * FATOR_CENTIMETROS;
    }

    public static double metrosParaMilimetros(final double metros) {
        validar(metros);
        return metros * FATOR_MILIMETROS;
    }

    private static void validar(final double metros) {
        if (metros < 0) {
            throw new IllegalArgumentException("Medida em metros não pode ser negativa.");
        }
    }
}
