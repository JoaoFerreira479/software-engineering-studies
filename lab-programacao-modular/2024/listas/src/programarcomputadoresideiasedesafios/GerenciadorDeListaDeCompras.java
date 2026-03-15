package programarcomputadoresideiasedesafios;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import infrastructure.io.Console;

import java.util.Scanner;

public final class GerenciadorDeListaDeCompras {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final ListaDeCompras lista = new ListaDeCompras();
            Console.println("Bem-vindo ao programa de Lista de Compras!");

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
                        Console.println("Encerrando o programa. Obrigado por usar a Lista de Compras!");
                    }
                    default -> Console.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }

    private static void exibirMenu() {
        Console.println("\nEscolha uma opção:");
        Console.println("1. Ver lista de compras");
        Console.println("2. Adicionar item à lista");
        Console.println("3. Remover item da lista");
        Console.println("4. Imprimir lista de compras");
        Console.println("5. Sair");
    }

    private static void adicionarItem(final ListaDeCompras lista, final Scanner scanner) {
        final String nome = EntradaUtil.lerLinha(scanner, "\nDigite o nome do item: ");
        if (nome.isEmpty()) {
            Console.println("Nome não pode ser vazio.");
            return;
        }
        final int quantidade = EntradaUtil.lerInteiro(scanner, "Digite a quantidade: ");
        if (quantidade < 1) {
            Console.println("Quantidade deve ser positiva.");
            return;
        }
        lista.adicionarItem(nome, quantidade);
        Console.println("Item \"" + nome + "\" adicionado à lista.");
    }

    private static void removerItem(final ListaDeCompras lista, final Scanner scanner) {
        if (lista.isVazia()) {
            Console.println("\nA lista de compras está vazia. Não há itens para remover.");
            return;
        }
        lista.exibirLista();
        final int indice = EntradaUtil.lerInteiro(scanner, "\nDigite o número do item que deseja remover: ");
        if (lista.removerItem(indice)) {
            Console.println("Item removido com sucesso.");
        } else {
            Console.println("Número inválido! Tente novamente.");
        }
    }
}
