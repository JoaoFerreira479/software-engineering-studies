package laboratorio;

import laboratorio.estruturas.Fila;
import laboratorio.estruturas.ListaSequencial;
import laboratorio.estruturas.Pilha;

public final class Guia7 {

    private static final int CAPACIDADE = 10;

    private Guia7() {}

    public static void main(String[] args) {
        testarLista();
        testarPilha();
        testarFila();
    }

    private static void testarLista() {
        ListaSequencial<Integer> lista = new ListaSequencial<>(CAPACIDADE);
        System.out.println("Testando Lista:");
        for (int i = 1; i <= 5; i++) {
            lista.inserirUltimaPosicao(i);
            imprimirLista(lista);
        }
        for (int i = 0; i < 5; i++) {
            lista.removerPrimeiraPosicao();
            imprimirLista(lista);
        }
    }

    private static void imprimirLista(ListaSequencial<Integer> lista) {
        StringBuilder sb = new StringBuilder("Elementos válidos: [");
        for (int i = 0; i < lista.tamanho(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(lista.obter(i));
        }
        sb.append("]");
        System.out.println(sb);
    }

    private static void testarPilha() {
        Pilha<Integer> pilha = new Pilha<>(CAPACIDADE);
        System.out.println("\nTestando Pilha:");
        for (int i = 1; i <= 5; i++) {
            pilha.push(i);
            imprimirPilha(pilha);
        }
        for (int i = 0; i < 5; i++) {
            pilha.pop();
            imprimirPilha(pilha);
        }
    }

    private static void imprimirPilha(Pilha<Integer> pilha) {
        StringBuilder sb = new StringBuilder("Pilha (base->topo): [");
        ListaSequencial<Integer> lista = new ListaSequencial<>(CAPACIDADE);
        while (!pilha.estaVazia()) {
            int x = pilha.pop();
            lista.inserirPrimeiraPosicao(x);
        }
        for (int i = 0; i < lista.tamanho(); i++) {
            if (i > 0) sb.append(", ");
            int x = lista.obter(i);
            sb.append(x);
            pilha.push(x);
        }
        sb.append("]");
        System.out.println(sb);
    }

    private static void testarFila() {
        Fila<Integer> fila = new Fila<>(CAPACIDADE);
        System.out.println("\nTestando Fila:");
        for (int i = 1; i <= 5; i++) {
            fila.enqueue(i);
            imprimirFila(fila);
        }
        for (int i = 0; i < 5; i++) {
            fila.dequeue();
            imprimirFila(fila);
        }
    }

    private static void imprimirFila(Fila<Integer> fila) {
        StringBuilder sb = new StringBuilder("Fila (frente->trás): [");
        Fila<Integer> temp = new Fila<>(CAPACIDADE);
        while (!fila.estaVazia()) {
            int x = fila.dequeue();
            sb.append(x);
            temp.enqueue(x);
            if (!fila.estaVazia()) sb.append(", ");
        }
        while (!temp.estaVazia()) {
            fila.enqueue(temp.dequeue());
        }
        sb.append("]");
        System.out.println(sb);
    }
}
