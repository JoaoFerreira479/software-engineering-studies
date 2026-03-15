package introcomp.domain;

import java.util.Objects;


public final class ContadorParesImpares {

    private ContadorParesImpares() {
    }

    public static ResultadoContagem contar(int[] numeros) {
        Objects.requireNonNull(numeros, "numeros");
        int pares = 0, impares = 0;
        for (int n : numeros) {
            if (n % 2 == 0) {
                pares++;
            } else {
                impares++;
            }
        }
        return new ResultadoContagem(pares, impares);
    }

    public static String formatarResultado(ResultadoContagem r) {
        Objects.requireNonNull(r, "resultado");
        return """
                ===== Resultado =====
                Quantidade de números pares: %d
                Quantidade de números ímpares: %d""".formatted(r.pares(), r.impares());
    }
}
