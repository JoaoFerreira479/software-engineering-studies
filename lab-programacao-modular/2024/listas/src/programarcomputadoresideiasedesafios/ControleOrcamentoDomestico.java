package programarcomputadoresideiasedesafios;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public final class ControleOrcamentoDomestico {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final OrcamentoDomestico orcamento = new OrcamentoDomestico();
            Console.println("Bem-vindo ao programa de Orçamento Doméstico!");

            boolean continuar = true;
            while (continuar) {
                exibirMenu();
                final int opcao = EntradaUtil.lerInteiro(scanner, "Digite sua escolha: ");

                switch (opcao) {
                    case 1 -> adicionarTransacao(orcamento, scanner, TipoTransacao.RECEITA);
                    case 2 -> adicionarTransacao(orcamento, scanner, TipoTransacao.DESPESA);
                    case 3 -> Console.printf("Saldo atual: %.2f%n", orcamento.calcularSaldo());
                    case 4 -> exibirResumoPorCategoria(orcamento);
                    case 5 -> exibirResumoMensal(orcamento, scanner);
                    case 6 -> listarTransacoes(orcamento);
                    case 7 -> {
                        continuar = false;
                        Console.println("Encerrando o programa. Obrigado por usar o Orçamento Doméstico!");
                    }
                    default -> Console.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }

    private static void exibirMenu() {
        Console.println("\nEscolha uma opção:");
        Console.println("1. Adicionar Receita");
        Console.println("2. Adicionar Despesa");
        Console.println("3. Exibir Saldo Atual");
        Console.println("4. Exibir Resumo por Categoria");
        Console.println("5. Exibir Resumo Mensal");
        Console.println("6. Listar todas as Transações");
        Console.println("7. Sair");
    }

    private static void adicionarTransacao(final OrcamentoDomestico orcamento, final Scanner scanner, final TipoTransacao tipo) {
        final String categoria = EntradaUtil.lerLinha(scanner, "Digite a categoria: ");
        if (categoria.isEmpty()) {
            Console.println("Categoria não pode ser vazia.");
            return;
        }
        final double valor = EntradaUtil.lerDouble(scanner, "Digite o valor: ");
        final String dataStr = EntradaUtil.lerLinha(scanner, "Digite a data (yyyy-mm-dd): ");
        try {
            final LocalDate data = LocalDate.parse(dataStr);
            orcamento.adicionarTransacao(tipo, categoria, valor, data);
            Console.println(tipo + " adicionada com sucesso!");
        } catch (DateTimeParseException e) {
            Console.println("Data inválida. Use o formato yyyy-mm-dd.");
        }
    }

    private static void exibirResumoPorCategoria(final OrcamentoDomestico orcamento) {
        final Map<String, Double> resumo = orcamento.calcularResumoPorCategoria();
        Console.println("Resumo por categoria:");
        resumo.forEach((cat, saldo) -> Console.printf("Categoria: %s, Saldo: %.2f%n", cat, saldo));
    }

    private static void exibirResumoMensal(final OrcamentoDomestico orcamento, final Scanner scanner) {
        final int ano = EntradaUtil.lerInteiro(scanner, "Digite o ano (yyyy): ");
        final Map<Integer, Double> resumo = orcamento.calcularResumoMensal(ano);
        Console.println("Resumo mensal:");
        resumo.forEach((mes, saldo) -> Console.printf("Mês: %d, Saldo: %.2f%n", mes, saldo));
    }

    private static void listarTransacoes(final OrcamentoDomestico orcamento) {
        final var transacoes = orcamento.listarTransacoes();
        if (transacoes.isEmpty()) {
            Console.println("Nenhuma transação registrada.");
        } else {
            Console.println("Lista de transações:");
            transacoes.forEach(System.out::println);
        }
    }
}
