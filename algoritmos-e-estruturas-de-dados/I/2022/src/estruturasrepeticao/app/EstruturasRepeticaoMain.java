package estruturasrepeticao.app;

import estruturasrepeticao.domain.EstruturasRepeticao;
import estruturasrepeticao.io.Console;

public final class EstruturasRepeticaoMain {

    private static final int INICIO = 5;
    private static final int FIM = 15;

    public static void main(final String[] args) {
        int[] sequencia = EstruturasRepeticao.sequenciaInclusiva(INICIO, FIM);

        Console.println("Números de 5 a 15 usando diferentes estruturas de repetição:");

        Console.println("\nUsando while:");
        imprimirComWhile(sequencia);

        Console.println("\nUsando do-while:");
        imprimirComDoWhile(sequencia);

        Console.println("\nUsando for:");
        imprimirComFor(sequencia);

        Console.println("");
    }

    private static void imprimirComWhile(final int[] seq) {
        int i = 0;
        while (i < seq.length) {
            Console.print(seq[i] + " ");
            i++;
        }
    }

    private static void imprimirComDoWhile(final int[] seq) {
        if (seq.length == 0) return;
        int i = 0;
        do {
            Console.print(seq[i] + " ");
            i++;
        } while (i < seq.length);
    }

    private static void imprimirComFor(final int[] seq) {
        for (int i = 0; i < seq.length; i++) {
            Console.print(seq[i] + " ");
        }
    }
}
