package introcomp.domain;

import java.util.Objects;


public final class ResultadoContagem {

    private final int pares;
    private final int impares;

    public ResultadoContagem(int pares, int impares) {
        if (pares < 0 || impares < 0) {
            throw new IllegalArgumentException("pares e impares devem ser não negativos");
        }
        this.pares = pares;
        this.impares = impares;
    }

    public int pares() {
        return pares;
    }

    public int impares() {
        return impares;
    }
}
