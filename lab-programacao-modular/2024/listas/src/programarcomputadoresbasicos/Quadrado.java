package programarcomputadoresbasicos;

import lab.listas.infrastructure.io.Console;

public final class Quadrado {

    private static final int TAMANHO_PADRAO = 5;
    private static final int TAMANHO_MINIMO = 2;

    public static void main(final String[] args) {
        desenharQuadrado(TAMANHO_PADRAO);
    }

    
    public static void desenharQuadrado(final int tamanho) {
        if (tamanho < TAMANHO_MINIMO) {
            Console.println("O tamanho do quadrado deve ser maior ou igual a " + TAMANHO_MINIMO + ".");
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
            Console.print("X");
        }
        Console.println();
    }

    private static void imprimirLinhaDoMeio(final int tamanho) {
        Console.print("X");
        for (int i = 1; i < tamanho - 1; i++) {
            Console.print(" ");
        }
        Console.println("X");
    }
}
