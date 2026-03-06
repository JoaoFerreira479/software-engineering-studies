package programarcomputadoresalternativasdecisao;

import java.util.Set;

public final class Numeros {

    private final int numero1;
    private final int numero2;
    private final int numero3;

    public Numeros(final int numero1, final int numero2, final int numero3) {
        if (!saoDiferentes(numero1, numero2, numero3)) {
            throw new IllegalArgumentException("Os números devem ser diferentes.");
        }
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.numero3 = numero3;
    }

    public int maior() {
        return Math.max(numero1, Math.max(numero2, numero3));
    }

    private static boolean saoDiferentes(final int a, final int b, final int c) {
        return Set.of(a, b, c).size() == 3;
    }
}
