package aed2.domain.recursao;

public final class SomaRecursivaVetor {

    private SomaRecursivaVetor() {}

    public static int calcularSoma(int[] vetor, int indice) {
        if (vetor == null) return 0;
        if (indice < 0) return 0;
        return vetor[indice] + calcularSoma(vetor, indice - 1);
    }
}
