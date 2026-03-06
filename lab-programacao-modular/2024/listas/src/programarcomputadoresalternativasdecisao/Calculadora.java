package programarcomputadoresalternativasdecisao;

public final class Calculadora {

    private Calculadora() {
    }

    public static double calcular(final int num1, final int num2, final Operador operador) {
        if (operador == null) {
            throw new NullPointerException("Operador não pode ser nulo.");
        }
        return switch (operador) {
            case SOMA -> num1 + num2;
            case SUBTRACAO -> num1 - num2;
            case MULTIPLICACAO -> num1 * num2;
            case DIVISAO -> {
                if (num2 == 0) {
                    throw new IllegalArgumentException("Divisão por zero não é permitida.");
                }
                yield (double) num1 / num2;
            }
        };
    }
}
