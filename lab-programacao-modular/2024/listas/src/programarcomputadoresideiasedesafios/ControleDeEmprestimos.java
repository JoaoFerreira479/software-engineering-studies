package programarcomputadoresideiasedesafios;

import java.util.Scanner;

public final class ControleDeEmprestimos {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final GerenciadorDeEmprestimos gerenciador = new GerenciadorDeEmprestimos();
            System.out.println("Bem-vindo ao programa de Controle de Empréstimos!");

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
                            System.out.println("Devolução registrada com sucesso!");
                        } else {
                            System.out.println("Número inválido!");
                        }
                    }
                    case 3 -> gerenciador.exibirEmprestimos();
                    case 4 -> {
                        final int dias = EntradaUtil.lerInteiro(scanner, "Digite o número de dias para o relatório: ");
                        gerenciador.exibirRelatorio(dias);
                    }
                    case 5 -> {
                        continuar = false;
                        System.out.println("Encerrando o programa.");
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Registrar novo empréstimo");
        System.out.println("2. Registrar devolução");
        System.out.println("3. Exibir lista de empréstimos");
        System.out.println("4. Exibir relatório de objetos emprestados há mais de X dias");
        System.out.println("5. Sair");
    }
}
