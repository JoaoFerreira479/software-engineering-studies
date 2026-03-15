package aed2.application;

import aed2.domain.nivelamento.ParOuImpar;
import aed2.infrastructure.io.Console;

import java.util.Random;

public final class ParOuImparMain {

    private static final int QUANTIDADE_NUMEROS = 10;
    private static final int LIMITE_SUPERIOR = 100;

    public static void main(String[] args) {
        Random gerador = new Random();
        Console.println("Gerando números aleatórios e verificando se são PAR ou ÍMPAR:");
        for (int i = 0; i < QUANTIDADE_NUMEROS; i++) {
            int numero = gerador.nextInt(LIMITE_SUPERIOR);
            String classificacao = ParOuImpar.classificar(numero);
            Console.println("Número " + numero + " é " + classificacao + ".");
        }
    }
}
