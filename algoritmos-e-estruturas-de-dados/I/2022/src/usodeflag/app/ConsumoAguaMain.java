package usodeflag.app;

import java.util.Scanner;
import usodeflag.domain.ConsumoAgua;
import usodeflag.io.Console;
import usodeflag.io.EntradaUsuario;

public final class ConsumoAguaMain {

    private static final int MAX_CONSUMIDORES = 10_000;

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            Console.println("Digite o consumo de água em m³ das residências.");
            Console.println("Digite um valor negativo para encerrar.");

            double[] buffer = new double[MAX_CONSUMIDORES];
            int n = 0;

            while (n < MAX_CONSUMIDORES) {
                double consumo = entrada.lerDouble("Consumo de água (m³): ");
                if (consumo < 0) break;
                buffer[n++] = consumo;
            }

            var res = ConsumoAgua.calcular(buffer, n);
            Console.println("Resultados:");
            if (res.temDados()) {
                Console.printf("Média de consumo: %.2f m³%n", res.media());
                Console.println("Número de consumidores analisados: " + res.quantidade());
                Console.printf("Maior consumo: %.2f m³%n", res.maior());
                Console.printf("Menor consumo: %.2f m³%n", res.menor());
            } else {
                Console.println("Nenhum consumidor foi analisado.");
            }
        } catch (Exception e) {
            Console.erro("Erro inesperado: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
