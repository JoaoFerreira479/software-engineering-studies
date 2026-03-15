package aed2.application;

import aed2.domain.estruturas.ListaEncadeadaSentinela;
import aed2.infrastructure.io.Console;

public final class Guia10Main {

    private Guia10Main() {}

    public static void main(String[] args) {
        ListaEncadeadaSentinela<String> listaStrings = new ListaEncadeadaSentinela<>();
        listaStrings.inserirInicio("Alice");
        listaStrings.inserirInicio("Bob");
        listaStrings.inserirFim("Charlie");
        mostrar(listaStrings);
        listaStrings.removerInicio();
        mostrar(listaStrings);

        ListaEncadeadaSentinela<Integer> listaNumeros = new ListaEncadeadaSentinela<>();
        listaNumeros.inserirInicio(10);
        listaNumeros.inserirFim(20);
        listaNumeros.inserirFim(30);
        mostrar(listaNumeros);
        Console.println("Média iterativa: " + calcularMediaIterativa(listaNumeros));
        Console.println("Média recursiva: " + calcularMediaRecursiva(listaNumeros));
    }

    private static double calcularMediaIterativa(ListaEncadeadaSentinela<Integer> lista) {
        if (lista.estaVazia()) return 0;
        final double[] soma = { 0 };
        final int[] count = { 0 };
        lista.percorrer(el -> {
            soma[0] += el.doubleValue();
            count[0]++;
        });
        return soma[0] / count[0];
    }

    private static double calcularMediaRecursiva(ListaEncadeadaSentinela<Integer> lista) {
        if (lista.estaVazia()) return 0;
        return calcularMediaRecursivaAux(lista, 0, 0, 0);
    }

    private static double calcularMediaRecursivaAux(ListaEncadeadaSentinela<Integer> lista, int indice, double soma, int count) {
        if (indice >= lista.tamanho()) return count == 0 ? 0 : soma / count;
        Integer el = lista.obter(indice);
        return calcularMediaRecursivaAux(lista, indice + 1, soma + el.doubleValue(), count + 1);
    }

    private static void mostrar(ListaEncadeadaSentinela<?> lista) {
        if (lista.estaVazia()) {
            Console.println("Lista vazia.");
            return;
        }
        Console.println("Elementos da lista:");
        lista.percorrer(el -> Console.println(el.toString()));
    }
}
