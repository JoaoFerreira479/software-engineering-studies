package conceitosbasicosprogramacao.domain;

public final class OperacoesDecimais {

    private OperacoesDecimais() {
    }

    public static double valorAbsoluto(final double numero) {
        return Math.abs(numero);
    }

    public static double teto(final double numero) {
        return Math.ceil(numero);
    }

    public static double piso(final double numero) {
        return Math.floor(numero);
    }
}
