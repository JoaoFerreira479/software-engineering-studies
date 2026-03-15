package aed2.domain.recursao;

import java.util.Objects;

public final class ContaOcorrencias {

    private ContaOcorrencias() {
    }

    public static int contaOcorrencias(int[] vetor, int x) {
        Objects.requireNonNull(vetor, "vetor");
        return contaOcorrenciasRec(vetor, x, vetor.length - 1);
    }

    private static int contaOcorrenciasRec(int[] vetor, int x, int index) {
        if (index < 0) return 0;
        return (vetor[index] == x ? 1 : 0) + contaOcorrenciasRec(vetor, x, index - 1);
    }
}
