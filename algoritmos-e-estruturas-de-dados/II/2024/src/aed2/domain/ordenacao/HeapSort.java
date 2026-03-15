package aed2.domain.ordenacao;

import java.util.Comparator;
import java.util.Objects;

public class HeapSort<T> implements IOrdenator<T> {

    private final T[] array;
    private Comparator<T> comparador;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    public HeapSort(T[] array) {
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
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            trocar(0, i);
            movimentacoes++;
            heapify(i, 0);
        }
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return array;
    }

    private void heapify(int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;
        if (esquerda < n) {
            comparacoes++;
            if (comparador.compare(array[esquerda], array[maior]) > 0) {
                maior = esquerda;
            }
        }
        if (direita < n) {
            comparacoes++;
            if (comparador.compare(array[direita], array[maior]) > 0) {
                maior = direita;
            }
        }
        if (maior != i) {
            trocar(i, maior);
            movimentacoes++;
            heapify(n, maior);
        }
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
