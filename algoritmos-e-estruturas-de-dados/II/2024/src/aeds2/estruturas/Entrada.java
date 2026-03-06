package aeds2.estruturas;

import java.util.Objects;

public final class Entrada<K extends Comparable<? super K>, V> implements Comparable<Entrada<K, V>> {

    private final K chave;
    private final V valor;

    public Entrada(K chave, V valor) {
        this.chave = Objects.requireNonNull(chave, "chave");
        this.valor = valor;
    }

    public K getChave() {
        return chave;
    }

    public V getValor() {
        return valor;
    }

    @Override
    public int compareTo(Entrada<K, V> outra) {
        return chave.compareTo(outra.getChave());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrada<?, ?> entrada = (Entrada<?, ?>) o;
        return chave.equals(entrada.chave);
    }

    @Override
    public int hashCode() {
        return chave.hashCode();
    }
}
