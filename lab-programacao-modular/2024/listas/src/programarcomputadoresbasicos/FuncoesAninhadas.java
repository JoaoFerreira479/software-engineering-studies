package programarcomputadoresbasicos;

public final class FuncoesAninhadas {

    public static void main(final String[] args) {
        final double numero = 3.0;
        final double raiz = calcularRaiz(numero);
        final double arredondado = arredondar(raiz);
        final double exponencial = calcularExponencial(arredondado);
        exibirResultados(numero, raiz, arredondado, exponencial);
    }

    public static double calcularRaiz(final double numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Número não pode ser negativo para raiz quadrada: " + numero);
        }
        return Math.sqrt(numero);
    }

    public static double arredondar(final double valor) {
        return Math.round(valor);
    }

    public static double calcularExponencial(final double valor) {
        return Math.exp(valor);
    }

    private static void exibirResultados(final double numero, final double raiz, final double arredondado, final double exponencial) {
        System.out.printf("Número inicial: %.2f%n", numero);
        System.out.printf("Raiz quadrada: %.6f%n", raiz);
        System.out.printf("Valor arredondado: %.0f%n", arredondado);
        System.out.printf("Exponencial do valor arredondado: %.6f%n", exponencial);
    }
}
