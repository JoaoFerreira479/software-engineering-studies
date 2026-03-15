package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class JogoDeFichasNovo {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            double acumulado = 0.0;
            boolean continuar = true;

            while (continuar) {
                final CorFicha primeira = CorFicha.fromString(EntradaUtil.lerLinha(scanner, "Digite a cor da primeira ficha (Branca ou Preta): "));
                final CorFicha segunda = CorFicha.fromString(EntradaUtil.lerLinha(scanner, "Digite a cor da segunda ficha (Branca ou Preta): "));
                final double valorAposta = EntradaUtil.lerDouble(scanner, "Digite o valor da aposta (limite de R$100): ");

                JogoDeFichas.validarAposta(valorAposta);
                final double rateio = JogoDeFichas.calcularRateio(primeira, segunda);
                final double ganho = valorAposta * rateio;
                acumulado += ganho;

                Console.printf("Rateio: %.2f%n", rateio);
                Console.printf("Ganho nesta rodada: R$%.2f%n", ganho);
                Console.printf("Total acumulado: R$%.2f%n", acumulado);
                continuar = EntradaUtil.perguntarSimNao(scanner, "Deseja jogar novamente? (S/N): ");
            }
            Console.printf("Valor total acumulado: R$%.2f%n", acumulado);
            Console.println("Obrigado por jogar!");
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }
}
