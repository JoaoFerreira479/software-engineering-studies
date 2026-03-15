package aed2.domain.ordenacao;

import java.util.Comparator;
import java.util.Objects;

public class SelectionSort<T> implements IOrdenator<T> {

    private final T[] array;
    private Comparator<T> comparador;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    public SelectionSort(T[] array) {
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
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparacoes++;
                if (comparador.compare(array[j], array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                trocar(i, minIndex);
                movimentacoes++;
            }
        }
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return array;
    }

    private void trocar(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
