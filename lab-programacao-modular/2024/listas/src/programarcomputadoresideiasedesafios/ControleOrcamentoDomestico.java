package programarcomputadoresideiasedesafios;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public final class ControleOrcamentoDomestico {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final OrcamentoDomestico orcamento = new OrcamentoDomestico();
            System.out.println("Bem-vindo ao programa de Orçamento Doméstico!");

            boolean continuar = true;
            while (continuar) {
                exibirMenu();
                final int opcao = EntradaUtil.lerInteiro(scanner, "Digite sua escolha: ");

                switch (opcao) {
                    case 1 -> adicionarTransacao(orcamento, scanner, TipoTransacao.RECEITA);
                    case 2 -> adicionarTransacao(orcamento, scanner, TipoTransacao.DESPESA);
                    case 3 -> System.out.printf("Saldo atual: %.2f%n", orcamento.calcularSaldo());
                    case 4 -> exibirResumoPorCategoria(orcamento);
                    case 5 -> exibirResumoMensal(orcamento, scanner);
                    case 6 -> listarTransacoes(orcamento);
                    case 7 -> {
                        continuar = false;
                        System.out.println("Encerrando o programa. Obrigado por usar o Orçamento Doméstico!");
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Adicionar Receita");
        System.out.println("2. Adicionar Despesa");
        System.out.println("3. Exibir Saldo Atual");
        System.out.println("4. Exibir Resumo por Categoria");
        System.out.println("5. Exibir Resumo Mensal");
        System.out.println("6. Listar todas as Transações");
        System.out.println("7. Sair");
    }

    private static void adicionarTransacao(final OrcamentoDomestico orcamento, final Scanner scanner, final TipoTransacao tipo) {
        final String categoria = EntradaUtil.lerLinha(scanner, "Digite a categoria: ");
        if (categoria.isEmpty()) {
            System.out.println("Categoria não pode ser vazia.");
            return;
        }
        final double valor = EntradaUtil.lerDouble(scanner, "Digite o valor: ");
        final String dataStr = EntradaUtil.lerLinha(scanner, "Digite a data (yyyy-mm-dd): ");
        try {
            final LocalDate data = LocalDate.parse(dataStr);
            orcamento.adicionarTransacao(tipo, categoria, valor, data);
            System.out.println(tipo + " adicionada com sucesso!");
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Use o formato yyyy-mm-dd.");
        }
    }

    private static void exibirResumoPorCategoria(final OrcamentoDomestico orcamento) {
        final Map<String, Double> resumo = orcamento.calcularResumoPorCategoria();
        System.out.println("Resumo por categoria:");
        resumo.forEach((cat, saldo) -> System.out.printf("Categoria: %s, Saldo: %.2f%n", cat, saldo));
    }

    private static void exibirResumoMensal(final OrcamentoDomestico orcamento, final Scanner scanner) {
        final int ano = EntradaUtil.lerInteiro(scanner, "Digite o ano (yyyy): ");
        final Map<Integer, Double> resumo = orcamento.calcularResumoMensal(ano);
        System.out.println("Resumo mensal:");
        resumo.forEach((mes, saldo) -> System.out.printf("Mês: %d, Saldo: %.2f%n", mes, saldo));
    }

    private static void listarTransacoes(final OrcamentoDomestico orcamento) {
        final var transacoes = orcamento.listarTransacoes();
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
        } else {
            System.out.println("Lista de transações:");
            transacoes.forEach(System.out::println);
        }
    }
}
