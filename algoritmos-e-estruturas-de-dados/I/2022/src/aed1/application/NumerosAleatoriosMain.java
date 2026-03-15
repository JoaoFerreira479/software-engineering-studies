package aed1.application;

import aed1.domain.repeticao.NumerosAleatorios;
import aed1.infrastructure.io.Console;

import java.util.Random;

public final class NumerosAleatoriosMain {

    private static final int QUANTIDADE = 10;

    public static void main(final String[] args) {
        Random gerador = new Random();
        int[] numeros = NumerosAleatorios.gerarNumeros(gerador, QUANTIDADE);

        Console.println("Gerando 10 números aleatórios e classificando como PAR ou ÍMPAR:");
        for (int i = 0; i < numeros.length; i++) {
            int num = numeros[i];
            String classificacao = NumerosAleatorios.classificar(num);
            Console.printf("Número gerado: %d -> %s%n", num, classificacao);
        }
    }
}
