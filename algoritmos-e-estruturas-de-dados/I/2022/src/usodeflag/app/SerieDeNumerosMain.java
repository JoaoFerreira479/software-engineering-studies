package usodeflag.app;

import usodeflag.domain.SerieDeNumeros;
import usodeflag.io.Console;
import usodeflag.io.EntradaUsuario;

import java.util.Scanner;

public final class SerieDeNumerosMain {

    private static final int MAX_NUMEROS = 10_000;

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            Console.println("Digite uma série de números.");
            Console.println("Digite um número negativo para encerrar.");

            double[] buffer = new double[MAX_NUMEROS];
            int n = 0;
            while (n < MAX_NUMEROS) {
                double numero = entrada.lerDouble("Número: ");
                if (numero < 0) break;
                buffer[n++] = numero;
            }

            var res = SerieDeNumeros.calcular(buffer, n);
            Console.println("Resultados:");
            if (res.temDados()) {
                Console.printf("Média verificada: %.2f%n", res.media());
                Console.println("Maior número lido: " + res.maior());
                Console.println("Menor número lido: " + res.menor());
            } else {
                Console.println("Nenhum número foi inserido.");
            }
        } catch (Exception e) {
            Console.erro("Erro inesperado: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
