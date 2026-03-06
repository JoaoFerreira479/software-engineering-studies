package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public final class ProgramaContaTelefonica {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final Tarifas tarifas = Tarifas.padrao();

            final int impulsosTotais = EntradaUtil.lerInteiro(scanner, "Digite o número total de impulsos: ");
            final int impulsosCelular = EntradaUtil.lerInteiro(scanner, "Digite o número de impulsos para celular: ");
            final double valorInterurbanos = EntradaUtil.lerDoubleNaoNegativo(scanner, "Digite o valor das chamadas de interurbanos: R$ ");

            final ContaTelefonica conta = new ContaTelefonica(tarifas, impulsosTotais, impulsosCelular, valorInterurbanos);
            exibirDetalhamento(conta);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void exibirDetalhamento(final ContaTelefonica conta) {
        final Tarifas t = conta.getTarifas();
        System.out.println("\n=== Detalhamento da Conta Telefônica ===");
        System.out.printf("Valor da assinatura: R$ %.2f%n", t.valorAssinatura());
        System.out.printf("Custo de impulsos extras: R$ %.2f%n", conta.calcularCustoImpulsosExtras());
        System.out.printf("Custo de chamadas para celular: R$ %.2f%n", conta.calcularCustoChamadaCelular());
        final double interurbanos = conta.calcularValorTotal() - t.valorAssinatura() - conta.calcularCustoImpulsosExtras() - conta.calcularCustoChamadaCelular();
        System.out.printf("Custo de interurbanos: R$ %.2f%n", interurbanos);
        System.out.printf("Valor total da conta: R$ %.2f%n", conta.calcularValorTotal());
    }
}
