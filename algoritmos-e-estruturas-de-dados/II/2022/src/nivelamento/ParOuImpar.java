package nivelamento;

import java.util.Random;

public final class ParOuImpar {

    private static final int QUANTIDADE_NUMEROS = 10;
    private static final int LIMITE_SUPERIOR = 100;

    private ParOuImpar() {}

    public static void main(String[] args) {
        Random gerador = new Random();
        System.out.println("Gerando números aleatórios e verificando se são PAR ou ÍMPAR:");

        for (int i = 0; i < QUANTIDADE_NUMEROS; i++) {
            int numero = gerarNumeroAleatorio(gerador, LIMITE_SUPERIOR);
            verificarParOuImpar(numero);
        }
    }

    private static int gerarNumeroAleatorio(Random gerador, int limiteSuperior) {
        return gerador.nextInt(limiteSuperior);
    }

    public static void verificarParOuImpar(int numero) {
        if (numero % 2 == 0) {
            System.out.println("Número " + numero + " é PAR.");
        } else {
            System.out.println("Número " + numero + " é ÍMPAR.");
        }
    }
}
