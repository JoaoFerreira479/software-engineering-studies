package laboratorio;

import laboratorio.estruturas.FilaEncadeada;

public final class Guia8 {

    private Guia8() {}

    public static void main(String[] args) {
        FilaEncadeada<Integer> fila = new FilaEncadeada<>();

        fila.enfileirar(10);
        fila.enfileirar(20);
        fila.enfileirar(30);
        System.out.println("Elementos adicionados à fila.");
        mostrarFila(fila);

        System.out.println("Primeiro elemento: " + fila.frente());
        System.out.println("Último elemento: " + fila.traseira());

        fila.desenfileirar();
        mostrarFila(fila);

        System.out.println("A fila está vazia? " + fila.estaVazia());
        System.out.println("Tamanho da fila: " + fila.tamanho());

        fila.desenfileirar();
        fila.desenfileirar();
        mostrarFila(fila);
        System.out.println("A fila está vazia? " + fila.estaVazia());

        fila.enfileirar(40);
        fila.enfileirar(50);
        mostrarFila(fila);
        fila.limpar();
        mostrarFila(fila);
    }

    private static void mostrarFila(FilaEncadeada<Integer> fila) {
        if (fila.estaVazia()) {
            System.out.println("A fila está vazia.");
            return;
        }
        FilaEncadeada<Integer> copia = new FilaEncadeada<>();
        System.out.print("Fila: ");
        while (!fila.estaVazia()) {
            int x = fila.desenfileirar();
            System.out.print(x + " ");
            copia.enfileirar(x);
        }
        while (!copia.estaVazia()) {
            fila.enfileirar(copia.desenfileirar());
        }
        System.out.println();
    }
}
