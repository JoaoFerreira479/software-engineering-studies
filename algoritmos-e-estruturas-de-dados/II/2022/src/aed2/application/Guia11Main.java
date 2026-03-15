package aed2.application;

import aed2.domain.estruturas.ListaDuplamenteEncadeada;
import aed2.domain.estruturas.PilhaEncadeada;
import aed2.infrastructure.io.Console;

public final class Guia11Main {

    private Guia11Main() {}

    public static void main(String[] args) {
        Console.println("=== Testando Pilha ===");
        PilhaEncadeada<Integer> pilha = new PilhaEncadeada<>();
        pilha.empilhar(10);
        pilha.empilhar(20);
        pilha.empilhar(30);
        pilha.empilhar(40);
        Console.println("Tamanho da pilha: " + pilha.tamanho());
        mostrarPilha(pilha);
        Console.println("Soma dos elementos (pilha intacta): " + pilha.somarElementosComoDouble());
        Console.println("Tamanho após soma: " + pilha.tamanho());

        Console.println("\n=== Testando Lista Duplamente Encadeada ===");
        ListaDuplamenteEncadeada<Integer> lista = new ListaDuplamenteEncadeada<>();
        lista.inserirInicio(50);
        lista.inserirInicio(60);
        lista.inserirFim(70);
        lista.inserirFim(80);
        Console.println("Tamanho da lista: " + lista.tamanho());
        lista.percorrerDoInicio(x -> Console.print(x + " "));
        Console.println("");
        lista.percorrerDoFim(x -> Console.print(x + " "));
        Console.println("");
        lista.removerInicio();
        lista.removerFim();
        Console.println("Tamanho: " + lista.tamanho());
        lista.percorrerDoInicio(x -> Console.print(x + " "));
        Console.println("");
        lista.percorrerDoFim(x -> Console.print(x + " "));
        Console.println("");
        Console.println("Lista está vazia? " + lista.estaVazia());
    }

    private static void mostrarPilha(PilhaEncadeada<Integer> pilha) {
        Console.print("Pilha (topo->base): ");
        pilha.percorrer(x -> Console.print(x + " "));
        Console.println("");
    }
}
