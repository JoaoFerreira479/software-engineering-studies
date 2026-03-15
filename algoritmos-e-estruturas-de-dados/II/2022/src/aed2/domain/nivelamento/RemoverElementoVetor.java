package aed2.domain.nivelamento;

public final class RemoverElementoVetor {

    private RemoverElementoVetor() {}

    public static int[] removerElemento(int[] vetor, int indice) {
        if (vetor == null || indice < 0 || indice >= vetor.length) {
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
        }
        int[] novo = new int[vetor.length - 1];
        for (int i = 0, j = 0; i < vetor.length; i++) {
            if (i != indice) novo[j++] = vetor[i];
        }
        return novo;
    }
}
