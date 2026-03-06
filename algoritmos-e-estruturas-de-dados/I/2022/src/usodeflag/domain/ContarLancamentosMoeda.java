package usodeflag.domain;

public final class ContarLancamentosMoeda {

    public static final int CARA = 1;
    public static final int COROA = 2;
    public static final int ENCERRAR = 0;

    private ContarLancamentosMoeda() {
    }

    public static ResultadoMoeda contar(final int[] lancamentos, final int n) {
        if (lancamentos == null || n < 0 || n > lancamentos.length) {
            throw new IllegalArgumentException("Argumentos inválidos.");
        }
        int cara = 0;
        int coroa = 0;
        for (int i = 0; i < n; i++) {
            int v = lancamentos[i];
            if (v == CARA) cara++;
            else if (v == COROA) coroa++;
        }
        return new ResultadoMoeda(cara, coroa);
    }

    public record ResultadoMoeda(int cara, int coroa) {
    }
}
