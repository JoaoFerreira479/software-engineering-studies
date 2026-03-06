package estruturasrepeticao.domain;

import java.util.Random;

public final class NumerosAleatorios {

    private static final int LIMITE_PADRAO = 100;

    private NumerosAleatorios() {
    }

    public static String classificar(final int numero) {
        return numero % 2 == 0 ? "PAR" : "ÍMPAR";
    }

    public static int[] gerarNumeros(final Random gerador, final int quantidade, final int limite) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade deve ser não negativa.");
        }
        if (limite <= 0) {
            throw new IllegalArgumentException("Limite deve ser positivo.");
        }
        int[] resultado = new int[quantidade];
        for (int i = 0; i < quantidade; i++) {
            resultado[i] = gerador.nextInt(limite);
        }
        return resultado;
    }

    public static int[] gerarNumeros(final Random gerador, final int quantidade) {
        return gerarNumeros(gerador, quantidade, LIMITE_PADRAO);
    }
}
