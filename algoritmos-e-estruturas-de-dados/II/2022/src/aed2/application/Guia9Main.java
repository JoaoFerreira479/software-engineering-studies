package aed2.application;

import aed2.domain.estruturas.ListaEncadeada;
import aed2.infrastructure.io.Console;

public final class Guia9Main {

    private Guia9Main() {}

    public static void main(String[] args) {
        ListaEncadeada<Integer> lista = new ListaEncadeada<>();
        lista.inserirInicio(10);
        lista.inserirInicio(20);
        lista.inserirFim(30);
        lista.inserirFim(40);
        lista.inserirNaPosicao(15, 1);
        lista.inserirNaPosicao(50, lista.tamanho());
        lista.inserirNaPosicao(25, 4);
        mostrar(lista);
        lista.removerInicio();
        mostrar(lista);
        lista.removerFim();
        mostrar(lista);
        lista.removerNaPosicao(3);
        mostrar(lista);
        Console.println("Soma iterativa: " + somaIterativa(lista));
        Console.println("Soma recursiva: " + somaRecursiva(lista));
    }

    private static double somaIterativa(ListaEncadeada<Integer> lista) {
        final double[] soma = { 0 };
        lista.percorrer(el -> {
            if (el instanceof Number n) {
                soma[0] += n.doubleValue();
            }
        });
        return soma[0];
    }

    private static double somaRecursiva(ListaEncadeada<Integer> lista) {
        return somaRecursivaAux(lista, 0);
    }

    private static double somaRecursivaAux(ListaEncadeada<Integer> lista, int indice) {
        if (indice >= lista.tamanho()) return 0;
        Integer el = lista.obter(indice);
        double valor = el != null ? el.doubleValue() : 0;
        return valor + somaRecursivaAux(lista, indice + 1);
    }

    private static void mostrar(ListaEncadeada<Integer> lista) {
        Console.print("Lista: ");
        lista.percorrer(el -> Console.print(el + " "));
        Console.println("");
    }
}
