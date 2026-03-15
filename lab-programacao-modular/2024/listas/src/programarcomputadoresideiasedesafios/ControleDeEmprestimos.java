package programarcomputadoresideiasedesafios;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ControleDeEmprestimos {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final GerenciadorDeEmprestimos gerenciador = new GerenciadorDeEmprestimos();
            Console.println("Bem-vindo ao programa de Controle de Empréstimos!");

            boolean continuar = true;
            while (continuar) {
                exibirMenu();
                final int opcao = EntradaUtil.lerInteiro(scanner, "Digite sua escolha: ");

                switch (opcao) {
                    case 1 -> {
                        final String tipo = EntradaUtil.lerLinha(scanner, "Digite o tipo do objeto: ");
                        final String nome = EntradaUtil.lerLinha(scanner, "Digite o nome do objeto: ");
                        final String pessoa = EntradaUtil.lerLinha(scanner, "Digite o nome da pessoa: ");
                        gerenciador.registrarEmprestimo(tipo, nome, pessoa);
                    }
                    case 2 -> {
                        gerenciador.exibirEmprestimos();
                        final int indice = EntradaUtil.lerInteiro(scanner, "Digite o número do empréstimo a ser devolvido: ");
                        if (gerenciador.registrarDevolucao(indice)) {
                            Console.println("Devolução registrada com sucesso!");
                        } else {
                            Console.println("Número inválido!");
                        }
                    }
                    case 3 -> gerenciador.exibirEmprestimos();
                    case 4 -> {
                        final int dias = EntradaUtil.lerInteiro(scanner, "Digite o número de dias para o relatório: ");
                        gerenciador.exibirRelatorio(dias);
                    }
                    case 5 -> {
                        continuar = false;
                        Console.println("Encerrando o programa.");
                    }
                    default -> Console.println("Opção inválida.");
                }
            }
        }
    }

    private static void exibirMenu() {
        Console.println("\nEscolha uma opção:");
        Console.println("1. Registrar novo empréstimo");
        Console.println("2. Registrar devolução");
        Console.println("3. Exibir lista de empréstimos");
        Console.println("4. Exibir relatório de objetos emprestados há mais de X dias");
        Console.println("5. Sair");
    }
}
