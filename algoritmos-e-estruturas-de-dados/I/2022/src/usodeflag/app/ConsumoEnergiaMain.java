package usodeflag.app;

import usodeflag.domain.ConsumoEnergia;
import usodeflag.io.Console;
import usodeflag.io.EntradaUsuario;

import java.util.Scanner;

public final class ConsumoEnergiaMain {

    private static final int MAX_CONSUMIDORES = 10_000;

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            Console.println("Digite o tipo de consumidor (1 para Residência, 2 para Comércio).");
            Console.println("Digite 0 para encerrar.");

            int[] tipos = new int[MAX_CONSUMIDORES];
            double[] consumos = new double[MAX_CONSUMIDORES];
            int n = 0;

            while (n < MAX_CONSUMIDORES) {
                int tipo = entrada.lerInteiro("Tipo de consumidor: ");
                if (tipo == ConsumoEnergia.ENCERRAR) break;

                if (tipo != ConsumoEnergia.TIPO_RESIDENCIA && tipo != ConsumoEnergia.TIPO_COMERCIO) {
                    Console.println("Tipo inválido! Digite 1 (Residência), 2 (Comércio) ou 0 para encerrar.");
                    continue;
                }

                double consumo = entrada.lerDouble("Digite o consumo (kWh): ");
                if (consumo < 0) {
                    Console.println("Consumo inválido! O valor deve ser maior ou igual a zero.");
                    continue;
                }

                tipos[n] = tipo;
                consumos[n] = consumo;
                n++;
            }

            var res = ConsumoEnergia.calcular(tipos, consumos, n);
            Console.println("Resultados:");
            Console.println("Quantidade de consumidores do tipo Residência: " + res.contadorResidencias());
            Console.println("Quantidade de consumidores do tipo Comércio: " + res.contadorComercio());
            Console.printf("Média de consumo das Residências: %.2f kWh%n", res.mediaResidencias());
            Console.printf("Média de consumo do Comércio: %.2f kWh%n", res.mediaComercio());
            Console.printf("Média de consumo do Bairro: %.2f kWh%n", res.mediaBairro());
        } catch (Exception e) {
            Console.erro("Erro inesperado: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
