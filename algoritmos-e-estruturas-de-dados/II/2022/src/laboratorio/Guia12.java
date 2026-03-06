package laboratorio;

import laboratorio.estruturas.ListaOrdenada;

import java.util.OptionalInt;
import java.util.Scanner;

public final class Guia12 {

    private static final String COMANDO_PARAR = "STOP";

    private Guia12() {}

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ListaOrdenada lista = new ListaOrdenada();
            adicionarNomes(lista, scanner);
            imprimirLista(lista);

            System.out.println("\nDigite um nome para buscar na lista (sequencial):");
            String buscaSeqNome = scanner.nextLine().trim();
            System.out.println("Nome encontrado (sequencial)? " + lista.buscaSequencial(buscaSeqNome));

            System.out.println("\nDigite um nome para buscar na lista (binária):");
            String buscaBinNome = scanner.nextLine().trim();
            System.out.println("Nome encontrado (binária)? " + lista.buscaBinaria(buscaBinNome));

            System.out.println("\nDigite um nome para remover da lista:");
            String nomeRemover = scanner.nextLine().trim();
            removerNome(lista, nomeRemover);
            imprimirLista(lista);

            System.out.println("\nDigite um nome para adicionar:");
            String nomeAdicionar = scanner.nextLine().trim();
            System.out.println("Digite a posição onde deseja adicionar:");
            String linhaPos = scanner.nextLine().trim();
            OptionalInt posOpt = parseInt(linhaPos);
            if (posOpt.isPresent()) {
                int posicao = posOpt.getAsInt();
                if (posicao >= 0 && posicao <= lista.tamanho()) {
                    lista.adicionarNaPosicao(nomeAdicionar, posicao);
                    System.out.println("Nome adicionado e lista reordenada.");
                } else {
                    System.out.println("Posição inválida. Nenhuma alteração feita.");
                }
            } else {
                System.out.println("Posição inválida! Digite um número inteiro.");
            }
            imprimirLista(lista);
        }
    }

    private static void adicionarNomes(ListaOrdenada lista, Scanner scanner) {
        System.out.println("Digite os nomes para adicionar na lista (ou '" + COMANDO_PARAR + "' para encerrar):");
        while (true) {
            String nome = scanner.nextLine().trim();
            if (nome.equalsIgnoreCase(COMANDO_PARAR)) break;
            if (!nome.isEmpty()) {
                lista.adicionar(nome);
                System.out.println("Nome adicionado: " + nome);
            }
        }
        System.out.println("Lista atualizada e ordenada.");
    }

    private static void imprimirLista(ListaOrdenada lista) {
        if (lista.estaVazia()) {
            System.out.println("A lista está vazia.");
            return;
        }
        System.out.println("Elementos da lista:");
        for (int i = 0; i < lista.tamanho(); i++) {
            System.out.println("Posição " + i + ": " + lista.obter(i));
        }
    }

    private static void removerNome(ListaOrdenada lista, String nome) {
        int antes = lista.tamanho();
        lista.remover(nome);
        if (lista.tamanho() < antes) {
            System.out.println("Nome \"" + nome + "\" removido da lista.");
        } else {
            System.out.println("Nome \"" + nome + "\" não encontrado na lista.");
        }
    }

    private static OptionalInt parseInt(String s) {
        try {
            return OptionalInt.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }
}
