package laboratorio;

import laboratorio.estruturas.ListaEncadeadaSentinela;

public final class Guia10 {

    private Guia10() {}

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
        System.out.println("Média iterativa: " + calcularMediaIterativa(listaNumeros));
        System.out.println("Média recursiva: " + calcularMediaRecursiva(listaNumeros));
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
            System.out.println("Lista vazia.");
            return;
        }
        System.out.println("Elementos da lista:");
        lista.percorrer(el -> System.out.println(el));
    }
}
