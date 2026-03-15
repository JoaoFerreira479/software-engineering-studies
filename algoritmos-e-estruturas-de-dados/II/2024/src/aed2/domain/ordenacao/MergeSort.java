package aed2.domain.ordenacao;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Objects;

public class MergeSort<T> implements IOrdenator<T> {

    private final T[] array;
    private Comparator<T> comparador;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    public MergeSort(T[] array) {
        this.array = Objects.requireNonNull(array, "array");
        this.comparacoes = 0;
        this.movimentacoes = 0;
        this.tempoOrdenacao = 0;
    }

    @Override
    public T[] ordenar() {
        if (comparador == null) {
            throw new IllegalStateException("Comparador nao definido. Chame setComparador antes de ordenar.");
        }
        long inicio = System.currentTimeMillis();
        mergesort(0, array.length - 1);
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return array;
    }

    private void mergesort(int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(esq, meio);
            mergesort(meio + 1, dir);
            merge(esq, meio, dir);
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(int esq, int meio, int dir) {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        T[] esquerda = (T[]) Array.newInstance(array.getClass().getComponentType(), n1);
        T[] direita = (T[]) Array.newInstance(array.getClass().getComponentType(), n2);
        for (int i = 0; i < n1; i++) esquerda[i] = array[esq + i];
        for (int j = 0; j < n2; j++) direita[j] = array[meio + 1 + j];
        int i = 0, j = 0, k = esq;
        while (i < n1 && j < n2) {
            comparacoes++;
            if (comparador.compare(esquerda[i], direita[j]) <= 0) {
                array[k++] = esquerda[i++];
            } else {
                array[k++] = direita[j++];
            }
            movimentacoes++;
        }
        while (i < n1) array[k++] = esquerda[i++];
        while (j < n2) array[k++] = direita[j++];
    }

    @Override
    public void setComparador(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    @Override
    public int getComparacoes() {
        return comparacoes;
    }

    @Override
    public int getMovimentacoes() {
        return movimentacoes;
    }

    @Override
    public double getTempoOrdenacao() {
        return tempoOrdenacao;
    }
}
