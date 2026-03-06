package programarcomputadoresideiasedesafios;

import java.util.Scanner;

public final class GerenciadorDeListaDeCompras {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final ListaDeCompras lista = new ListaDeCompras();
            System.out.println("Bem-vindo ao programa de Lista de Compras!");

            boolean continuar = true;
            while (continuar) {
                exibirMenu();
                final int opcao = EntradaUtil.lerInteiro(scanner, "Digite sua escolha: ");

                switch (opcao) {
                    case 1 -> lista.exibirLista();
                    case 2 -> adicionarItem(lista, scanner);
                    case 3 -> removerItem(lista, scanner);
                    case 4 -> lista.imprimirLista();
                    case 5 -> {
                        continuar = false;
                        System.out.println("Encerrando o programa. Obrigado por usar a Lista de Compras!");
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Ver lista de compras");
        System.out.println("2. Adicionar item à lista");
        System.out.println("3. Remover item da lista");
        System.out.println("4. Imprimir lista de compras");
        System.out.println("5. Sair");
    }

    private static void adicionarItem(final ListaDeCompras lista, final Scanner scanner) {
        final String nome = EntradaUtil.lerLinha(scanner, "\nDigite o nome do item: ");
        if (nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio.");
            return;
        }
        final int quantidade = EntradaUtil.lerInteiro(scanner, "Digite a quantidade: ");
        if (quantidade < 1) {
            System.out.println("Quantidade deve ser positiva.");
            return;
        }
        lista.adicionarItem(nome, quantidade);
        System.out.println("Item \"" + nome + "\" adicionado à lista.");
    }

    private static void removerItem(final ListaDeCompras lista, final Scanner scanner) {
        if (lista.isVazia()) {
            System.out.println("\nA lista de compras está vazia. Não há itens para remover.");
            return;
        }
        lista.exibirLista();
        final int indice = EntradaUtil.lerInteiro(scanner, "\nDigite o número do item que deseja remover: ");
        if (lista.removerItem(indice)) {
            System.out.println("Item removido com sucesso.");
        } else {
            System.out.println("Número inválido! Tente novamente.");
        }
    }
}
