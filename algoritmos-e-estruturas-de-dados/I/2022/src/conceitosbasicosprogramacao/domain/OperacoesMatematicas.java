package conceitosbasicosprogramacao.domain;

public final class OperacoesMatematicas {

    private OperacoesMatematicas() {
    }

    public static double raizQuadradaDosQuadrados(final int x, final int y) {
        double soma = (double) x * x + (double) y * y;
        if (soma < 0) {
            throw new ArithmeticException("Raiz quadrada de número negativo não definida.");
        }
        return Math.sqrt(soma);
    }

    public static int restoDivisao(final int x, final int y) {
        if (y == 0) {
            throw new ArithmeticException("Divisão por zero não permitida.");
        }
        return x % y;
    }

    public static double potencia(final int x, final int y) {
        if (x == 0 && y < 0) {
            throw new ArithmeticException("0 elevado a expoente negativo não definido.");
        }
        return Math.pow(x, y);
    }

    public static int soma(final int x, final int y) {
        return x + y;
    }

    public static int subtracao(final int x, final int y) {
        return x - y;
    }

    public static int multiplicacao(final int x, final int y) {
        return x * y;
    }

    public static double divisao(final int x, final int y) {
        if (y == 0) {
            throw new ArithmeticException("Divisão por zero não permitida.");
        }
        return (double) x / y;
    }
}
