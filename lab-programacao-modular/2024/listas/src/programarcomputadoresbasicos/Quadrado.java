package programarcomputadoresbasicos;

public final class Quadrado {

    private static final int TAMANHO_PADRAO = 5;
    private static final int TAMANHO_MINIMO = 2;

    public static void main(final String[] args) {
        desenharQuadrado(TAMANHO_PADRAO);
    }

    /**
     * Desenha um quadrado de lado {@code tamanho} (borda "X", interior vazio).
     * Não faz nada se tamanho &lt; 2.
     */
    public static void desenharQuadrado(final int tamanho) {
        if (tamanho < TAMANHO_MINIMO) {
            System.out.println("O tamanho do quadrado deve ser maior ou igual a " + TAMANHO_MINIMO + ".");
            return;
        }
        imprimirLinhaSuperiorOuInferior(tamanho);
        for (int i = 1; i < tamanho - 1; i++) {
            imprimirLinhaDoMeio(tamanho);
        }
        imprimirLinhaSuperiorOuInferior(tamanho);
    }

    private static void imprimirLinhaSuperiorOuInferior(final int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            System.out.print("X");
        }
        System.out.println();
    }

    private static void imprimirLinhaDoMeio(final int tamanho) {
        System.out.print("X");
        for (int i = 1; i < tamanho - 1; i++) {
            System.out.print(" ");
        }
        System.out.println("X");
    }
}
