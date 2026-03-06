package trabalhopratico.estruturas;

import java.util.Objects;

public class Mapa<K extends Comparable<? super K>, V> {

    private final ABB<Entrada<K, V>> abb = new ABB<>();

    public boolean contem(K chave) {
        Objects.requireNonNull(chave, "chave");
        return abb.contem(new Entrada<>(chave, null));
    }

    public V pesquisar(K chave) {
        Entrada<K, V> e = abb.pesquisar(new Entrada<>(chave, null));
        return e.getValor();
    }

    public void inserir(K chave, V valor) {
        Objects.requireNonNull(chave, "chave");
        if (abb.contem(new Entrada<>(chave, null))) {
            remover(chave);
        }
        abb.adicionar(new Entrada<>(chave, valor));
    }

    public void remover(K chave) {
        Objects.requireNonNull(chave, "chave");
        try {
            abb.remover(new Entrada<>(chave, null));
        } catch (Exception ignored) {
        }
    }

    public V get(K chave) {
        if (chave == null || !contem(chave)) {
            return null;
        }
        return pesquisar(chave);
    }

    public V putIfAbsent(K chave, V valor) {
        Objects.requireNonNull(chave, "chave");
        if (contem(chave)) {
            return pesquisar(chave);
        }
        abb.adicionar(new Entrada<>(chave, valor));
        return valor;
    }
}
