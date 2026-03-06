package revisao1.domain;

public final class SegundoMaiorElemento {

    private SegundoMaiorElemento() {
    }

    public static void validarTamanho(final int tamanho) {
        if (tamanho < 2) {
            throw new IllegalArgumentException("O vetor deve ter pelo menos 2 elementos.");
        }
    }

    public static int segundoMaior(final int[] vetor) {
        int maior = Integer.MIN_VALUE;
        int segundoMaior = Integer.MIN_VALUE;
        for (int valor : vetor) {
            if (valor > maior) {
                segundoMaior = maior;
                maior = valor;
            } else if (valor > segundoMaior && valor != maior) {
                segundoMaior = valor;
            }
        }
        if (segundoMaior == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Não foi possível encontrar um segundo maior elemento.");
        }
        return segundoMaior;
    }
}
