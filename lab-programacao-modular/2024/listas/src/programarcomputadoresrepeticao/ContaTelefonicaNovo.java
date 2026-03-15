package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ContaTelefonicaNovo {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final Tarifas tarifas = Tarifas.padrao();
            boolean continuar;
            do {
                Console.println("\n=== Cálculo da Conta Telefônica ===");
                final int impulsosTotais = EntradaUtil.lerInteiro(scanner, "Digite o número total de impulsos: ");
                final int impulsosCelular = EntradaUtil.lerInteiro(scanner, "Digite o número de impulsos para celular: ");
                final double valorInterurbanos = EntradaUtil.lerDouble(scanner, "Digite o valor das chamadas de interurbanos: R$ ");
                if (impulsosTotais < 0 || impulsosCelular < 0 || valorInterurbanos < 0) {
                    Console.println("Erro: Valores não podem ser negativos.");
                } else if (impulsosCelular > impulsosTotais) {
                    Console.println("Erro: Impulsos para celular não podem ser maiores que o total.");
                } else {
                    final ContaTelefonica conta = new ContaTelefonica(tarifas, impulsosTotais, impulsosCelular, valorInterurbanos);
                    exibirDetalhamento(conta);
                }
                continuar = EntradaUtil.perguntarSimNao(scanner, "\nDeseja calcular outra conta? (S/N): ");
            } while (continuar);
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }

    private static void exibirDetalhamento(final ContaTelefonica conta) {
        final Tarifas t = conta.getTarifas();
        Console.println("\n=== Detalhamento da Conta Telefônica ===");
        Console.printf("Valor da assinatura: R$ %.2f%n", t.valorAssinatura());
        Console.printf("Custo de impulsos extras: R$ %.2f%n", conta.calcularCustoImpulsosExtras());
        Console.printf("Custo de chamadas para celular: R$ %.2f%n", conta.calcularCustoChamadaCelular());
        final double interurbanos = conta.calcularValorTotal() - t.valorAssinatura() - conta.calcularCustoImpulsosExtras() - conta.calcularCustoChamadaCelular();
        Console.printf("Custo de interurbanos: R$ %.2f%n", interurbanos);
        Console.printf("Valor total da conta: R$ %.2f%n", conta.calcularValorTotal());
    }
}
