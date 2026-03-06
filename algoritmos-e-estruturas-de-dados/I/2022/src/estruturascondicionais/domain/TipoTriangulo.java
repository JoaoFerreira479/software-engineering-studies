package estruturascondicionais.domain;

public final class TipoTriangulo {

    private TipoTriangulo() {
    }

    public static String classificar(final double lado1, final double lado2, final double lado3) {
        validar(lado1, lado2, lado3);
        if (lado1 == lado2 && lado2 == lado3) {
            return "Equilátero (todos os lados iguais)";
        }
        if (lado1 == lado2 || lado1 == lado3 || lado2 == lado3) {
            return "Isósceles (dois lados iguais)";
        }
        return "Escaleno (nenhum lado igual)";
    }

    private static void validar(final double lado1, final double lado2, final double lado3) {
        if (!(lado1 > 0 && lado2 > 0 && lado3 > 0)) {
            throw new IllegalArgumentException("Os lados devem ser maiores que zero.");
        }
        if (!(lado1 + lado2 > lado3 && lado1 + lado3 > lado2 && lado2 + lado3 > lado1)) {
            throw new IllegalArgumentException(
                    "Os lados não satisfazem a desigualdade triangular.");
        }
    }
}
