package aed2.application;

import aed2.domain.estruturas.ListaOrdenada;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.OptionalInt;
import java.util.Scanner;

public final class Guia12Main {

    private static final String COMANDO_PARAR = "STOP";

    private Guia12Main() {}

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            ListaOrdenada lista = new ListaOrdenada();
            adicionarNomes(lista, entrada);
            imprimirLista(lista);

            Console.println("\nDigite um nome para buscar na lista (sequencial):");
            String buscaSeqNome = entrada.lerLinha("Nome: ").trim();
            Console.println("Nome encontrado (sequencial)? " + lista.buscaSequencial(buscaSeqNome));

            Console.println("\nDigite um nome para buscar na lista (binária):");
            String buscaBinNome = entrada.lerLinha("Nome: ").trim();
            Console.println("Nome encontrado (binária)? " + lista.buscaBinaria(buscaBinNome));

            Console.println("\nDigite um nome para remover da lista:");
            String nomeRemover = entrada.lerLinha("Nome: ").trim();
            removerNome(lista, nomeRemover);
            imprimirLista(lista);

            Console.println("\nDigite um nome para adicionar:");
            String nomeAdicionar = entrada.lerLinha("Nome: ").trim();
            Console.println("Digite a posição onde deseja adicionar:");
            String linhaPos = entrada.lerLinha("Posição: ").trim();
            OptionalInt posOpt = parseInt(linhaPos);
            if (posOpt.isPresent()) {
                int posicao = posOpt.getAsInt();
                if (posicao >= 0 && posicao <= lista.tamanho()) {
                    lista.adicionarNaPosicao(nomeAdicionar, posicao);
                    Console.println("Nome adicionado e lista reordenada.");
                } else {
                    Console.println("Posição inválida. Nenhuma alteração feita.");
                }
            } else {
                Console.println("Posição inválida! Digite um número inteiro.");
            }
            imprimirLista(lista);
        }
    }

    private static void adicionarNomes(ListaOrdenada lista, EntradaUsuario entrada) {
        Console.println("Digite os nomes para adicionar na lista (ou '" + COMANDO_PARAR + "' para encerrar):");
        while (true) {
            String nome = entrada.lerLinha("").trim();
            if (nome.equalsIgnoreCase(COMANDO_PARAR)) break;
            if (!nome.isEmpty()) {
                lista.adicionar(nome);
                Console.println("Nome adicionado: " + nome);
            }
        }
        Console.println("Lista atualizada e ordenada.");
    }

    private static void imprimirLista(ListaOrdenada lista) {
        if (lista.estaVazia()) {
            Console.println("A lista está vazia.");
            return;
        }
        Console.println("Elementos da lista:");
        for (int i = 0; i < lista.tamanho(); i++) {
            Console.println("Posição " + i + ": " + lista.obter(i));
        }
    }

    private static void removerNome(ListaOrdenada lista, String nome) {
        int antes = lista.tamanho();
        lista.remover(nome);
        if (lista.tamanho() < antes) {
            Console.println("Nome \"" + nome + "\" removido da lista.");
        } else {
            Console.println("Nome \"" + nome + "\" não encontrado na lista.");
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
