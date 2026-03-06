package revisao1.domain;

public final class RemoverElementoVetor {

    private RemoverElementoVetor() {
    }

    public static void validarTamanho(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("O tamanho do array deve ser maior que zero.");
        }
    }

    public static void validarIndice(final int indice, final int tamanho) {
        if (indice < 0 || indice >= tamanho) {
            throw new IllegalArgumentException("Índice inválido. Deve ser entre 0 e " + (tamanho - 1) + ".");
        }
    }

    public static void remover(final int[] array, final int indice) {
        validarIndice(indice, array.length);
        for (int i = indice; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = 0;
    }
}
