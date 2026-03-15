package aed2.domain.ordenacao;

import java.util.Comparator;

public interface IOrdenator<T> {

    T[] ordenar();

    void setComparador(Comparator<T> comparador);

    int getComparacoes();

    int getMovimentacoes();

    double getTempoOrdenacao();
}
