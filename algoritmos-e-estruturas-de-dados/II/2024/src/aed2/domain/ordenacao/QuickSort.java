package aed2.domain.ordenacao;

import java.util.Comparator;
import java.util.Objects;

public class QuickSort<T> implements IOrdenator<T> {

    private final T[] array;
    private Comparator<T> comparador;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    public QuickSort(T[] array) {
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
        quicksort(0, array.length - 1);
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return array;
    }

    private void quicksort(int esq, int dir) {
        if (esq < dir) {
            int pivo = particionar(esq, dir);
            quicksort(esq, pivo - 1);
            quicksort(pivo + 1, dir);
        }
    }

    private int particionar(int esq, int dir) {
        T pivo = array[dir];
        int i = esq - 1;
        for (int j = esq; j < dir; j++) {
            comparacoes++;
            if (comparador.compare(array[j], pivo) > 0) {
                i++;
                trocar(i, j);
            }
        }
        trocar(i + 1, dir);
        return i + 1;
    }

    private void trocar(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        movimentacoes++;
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
