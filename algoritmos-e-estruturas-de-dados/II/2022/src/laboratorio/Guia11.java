package laboratorio;

import laboratorio.estruturas.ListaDuplamenteEncadeada;
import laboratorio.estruturas.PilhaEncadeada;

public final class Guia11 {

    private Guia11() {}

    public static void main(String[] args) {
        System.out.println("=== Testando Pilha ===");
        PilhaEncadeada<Integer> pilha = new PilhaEncadeada<>();
        pilha.empilhar(10);
        pilha.empilhar(20);
        pilha.empilhar(30);
        pilha.empilhar(40);
        System.out.println("Tamanho da pilha: " + pilha.tamanho());
        mostrarPilha(pilha);
        System.out.println("Soma dos elementos (pilha intacta): " + pilha.somarElementosComoDouble());
        System.out.println("Tamanho após soma: " + pilha.tamanho());

        System.out.println("\n=== Testando Lista Duplamente Encadeada ===");
        ListaDuplamenteEncadeada<Integer> lista = new ListaDuplamenteEncadeada<>();
        lista.inserirInicio(50);
        lista.inserirInicio(60);
        lista.inserirFim(70);
        lista.inserirFim(80);
        System.out.println("Tamanho da lista: " + lista.tamanho());
        lista.percorrerDoInicio(x -> System.out.print(x + " "));
        System.out.println();
        lista.percorrerDoFim(x -> System.out.print(x + " "));
        System.out.println();
        lista.removerInicio();
        lista.removerFim();
        System.out.println("Tamanho: " + lista.tamanho());
        lista.percorrerDoInicio(x -> System.out.print(x + " "));
        System.out.println();
        lista.percorrerDoFim(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("Lista está vazia? " + lista.estaVazia());
    }

    private static void mostrarPilha(PilhaEncadeada<Integer> pilha) {
        System.out.print("Pilha (topo->base): ");
        pilha.percorrer(x -> System.out.print(x + " "));
        System.out.println();
    }
}
