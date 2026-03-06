package programarcomputadoresrepeticao;

public final class Tabuada {

    private static final int MIN = 1;
    private static final int MAX = 9;
    private static final int LIMITE = 10;

    private Tabuada() {
    }

    public static void exibirTabuada(final int numero) {
        if (numero < MIN || numero > MAX) {
            throw new IllegalArgumentException("O número deve estar entre " + MIN + " e " + MAX + ".");
        }
        System.out.println("Tabuada do número " + numero + ":");
        for (int i = 1; i <= LIMITE; i++) {
            System.out.println(numero + " x " + i + " = " + (numero * i));
        }
    }
}
