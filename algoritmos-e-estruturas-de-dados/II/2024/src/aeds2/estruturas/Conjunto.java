package aeds2.estruturas;

import java.util.Objects;

public class Conjunto<E extends Comparable<E>> {

    private final ABB<E> abb = new ABB<>();

    public boolean contem(E item) {
        return abb.contem(item);
    }

    public boolean adicionar(E item) {
        Objects.requireNonNull(item, "item");
        if (abb.contem(item)) {
            return false;
        }
        abb.adicionar(item);
        return true;
    }

    public void emOrdem(ABB.Consumer<E> visitante) {
        abb.emOrdem(visitante);
    }
}
