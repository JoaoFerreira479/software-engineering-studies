package programarcomputadoresbasicos;

import java.util.List;

public final class CalculoDeNotas {

    public static void main(final String[] args) {
        final List<Nota> notas = List.of(
            new Nota(8.0, 1),
            new Nota(7.5, 2),
            new Nota(10.0, 3),
            new Nota(9.0, 4)
        );
        final double media = calcularMediaPonderada(notas);
        exibirNotas(notas);
        System.out.printf("%nMédia Ponderada: %.1f%n", media);
    }

    public static double calcularMediaPonderada(final List<Nota> notas) {
        if (notas == null || notas.isEmpty()) {
            return 0.0;
        }
        double somaPonderada = 0.0;
        int somaPesos = 0;
        for (final Nota n : notas) {
            somaPonderada += n.valor() * n.peso();
            somaPesos += n.peso();
        }
        return somaPesos == 0 ? 0.0 : somaPonderada / somaPesos;
    }

    private static void exibirNotas(final List<Nota> notas) {
        System.out.println("Notas do aluno:");
        int i = 1;
        for (final Nota n : notas) {
            System.out.printf("Nota %d: %.1f (Peso: %d)%n", i++, n.valor(), n.peso());
        }
    }
}
