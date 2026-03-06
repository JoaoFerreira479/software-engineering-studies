package aeds2.estruturas;

import java.util.Objects;

public class TabelaHash<K extends Comparable<? super K>, V> {

    private final ABB<Entrada<K, V>>[] buckets;
    private final int capacidade;

    @SuppressWarnings("unchecked")
    public TabelaHash(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("capacidade deve ser positiva");
        }
        this.capacidade = capacidade;
        this.buckets = new ABB[capacidade];
        for (int i = 0; i < capacidade; i++) {
            buckets[i] = new ABB<>();
        }
    }

    private int indice(K chave) {
        return Math.abs(chave.hashCode() % capacidade);
    }

    public void inserir(K chave, V valor) {
        Objects.requireNonNull(chave, "chave");
        int i = indice(chave);
        Entrada<K, V> entrada = new Entrada<>(chave, valor);
        if (!buckets[i].contem(entrada)) {
            buckets[i].adicionar(entrada);
        } else {
            remover(chave);
            buckets[i].adicionar(entrada);
        }
    }

    private void remover(K chave) {
        int i = indice(chave);
        try {
            buckets[i].remover(new Entrada<>(chave, null));
        } catch (Exception ignored) {
        }
    }

    public V pesquisar(K chave) {
        Objects.requireNonNull(chave, "chave");
        int i = indice(chave);
        ABB<Entrada<K, V>> abb = buckets[i];
        try {
            Entrada<K, V> entrada = abb.pesquisar(new Entrada<>(chave, null));
            return entrada.getValor();
        } catch (Exception e) {
            return null;
        }
    }

}
