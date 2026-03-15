package aed2.domain.ordenacao;

import java.util.Comparator;
import java.util.Objects;

public class InsertionSort<T> implements IOrdenator<T> {

    private final T[] array;
    private Comparator<T> comparador;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    public InsertionSort(T[] array) {
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
        int n = array.length;
        for (int i = 1; i < n; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= 0 && comparador.compare(array[j], key) > 0) {
                comparacoes++;
                array[j + 1] = array[j];
                j--;
                movimentacoes++;
            }
            if (j >= 0) comparacoes++;
            array[j + 1] = key;
        }
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return array;
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
