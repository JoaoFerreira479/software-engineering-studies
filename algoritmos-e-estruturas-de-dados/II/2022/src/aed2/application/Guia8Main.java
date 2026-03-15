package aed2.application;

import aed2.domain.estruturas.FilaEncadeada;
import aed2.infrastructure.io.Console;

public final class Guia8Main {

    private Guia8Main() {}

    public static void main(String[] args) {
        FilaEncadeada<Integer> fila = new FilaEncadeada<>();
        fila.enfileirar(10);
        fila.enfileirar(20);
        fila.enfileirar(30);
        Console.println("Elementos adicionados à fila.");
        mostrarFila(fila);
        Console.println("Primeiro elemento: " + fila.frente());
        Console.println("Último elemento: " + fila.traseira());
        fila.desenfileirar();
        mostrarFila(fila);
        Console.println("A fila está vazia? " + fila.estaVazia());
        Console.println("Tamanho da fila: " + fila.tamanho());
        fila.desenfileirar();
        fila.desenfileirar();
        mostrarFila(fila);
        Console.println("A fila está vazia? " + fila.estaVazia());
        fila.enfileirar(40);
        fila.enfileirar(50);
        mostrarFila(fila);
        fila.limpar();
        mostrarFila(fila);
    }

    private static void mostrarFila(FilaEncadeada<Integer> fila) {
        if (fila.estaVazia()) {
            Console.println("A fila está vazia.");
            return;
        }
        FilaEncadeada<Integer> copia = new FilaEncadeada<>();
        Console.print("Fila: ");
        while (!fila.estaVazia()) {
            int x = fila.desenfileirar();
            Console.print(x + " ");
            copia.enfileirar(x);
        }
        while (!copia.estaVazia()) {
            fila.enfileirar(copia.desenfileirar());
        }
        Console.println("");
    }
}
