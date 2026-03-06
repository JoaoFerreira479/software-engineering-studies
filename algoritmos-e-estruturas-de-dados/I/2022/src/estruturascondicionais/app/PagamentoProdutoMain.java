package estruturascondicionais.app;

import estruturascondicionais.domain.PagamentoProduto;
import estruturascondicionais.io.Console;
import estruturascondicionais.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class PagamentoProdutoMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double valor = entrada.lerDouble("Digite o valor do produto (R$): ");
            PagamentoProduto.validarValorProduto(valor);
            exibirMenu();
            int opcao = entrada.lerInteiro("Selecione uma opção (1 a 4): ");
            PagamentoProduto.validarOpcao(opcao);
            processar(opcao, valor, entrada);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use valores numéricos.");
        } finally {
            entrada.fechar();
        }
    }

    private static void exibirMenu() {
        Console.println("Opções de pagamento:");
        Console.println("1 - À vista dinheiro/PIX com 10% de desconto");
        Console.println("2 - À vista cartão sem desconto");
        Console.println("3 - Em até 3x sem juros");
        Console.println("4 - Em até 10x com juros de 15%");
    }

    private static void processar(final int opcao, final double valor, final EntradaUsuario entrada) {
        switch (opcao) {
            case 1 -> Console.printf("Pagamento à vista (dinheiro/PIX). Valor final: R$ %.2f%n",
                    PagamentoProduto.valorAVistaComDesconto(valor));
            case 2 -> Console.printf("Pagamento à vista (cartão). Valor final: R$ %.2f%n",
                    PagamentoProduto.valorAVistaSemDesconto(valor));
            case 3 -> {
                int parcelas = entrada.lerInteiro("Número de parcelas (até 3): ");
                var res = PagamentoProduto.parceladoSemJuros(valor, parcelas);
                Console.printf("Pagamento em %d parcelas sem juros. Valor de cada parcela: R$ %.2f%n",
                        res.parcelas(), res.valorParcela());
            }
            case 4 -> {
                int parcelas = entrada.lerInteiro("Número de parcelas (até 10): ");
                var res = PagamentoProduto.parceladoComJuros(valor, parcelas);
                Console.printf("Pagamento em %d parcelas com 15%% de juros. Valor final: R$ %.2f. Parcela: R$ %.2f%n",
                        res.parcelas(), res.valorTotal(), res.valorParcela());
            }
            default -> Console.erro("Opção inválida.");
        }
    }
}
