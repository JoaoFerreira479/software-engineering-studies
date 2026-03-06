package programarcomputadoresrepeticao;

import java.util.Optional;

public final class Caixa {

    private static final int NOTA_50 = 50;
    private static final int NOTA_10 = 10;
    private static final int MULTIPLO = 10;

    private int notasDe50;
    private int notasDe10;
    private int totalRetiradas;

    public void adicionarNotas(final int qtd50, final int qtd10) {
        if (qtd50 < 0 || qtd10 < 0) {
            throw new IllegalArgumentException("A quantidade de notas não pode ser negativa.");
        }
        notasDe50 += qtd50;
        notasDe10 += qtd10;
    }

    public Optional<RetiradaResultado> realizarRetirada(final int valor) {
        if (valor % MULTIPLO != 0) return Optional.empty();
        final int q50 = Math.min(valor / NOTA_50, notasDe50);
        final int restante = valor - (q50 * NOTA_50);
        final int q10 = Math.min(restante / NOTA_10, notasDe10);
        if (q50 * NOTA_50 + q10 * NOTA_10 < valor) return Optional.empty();
        notasDe50 -= q50;
        notasDe10 -= q10;
        totalRetiradas += valor;
        return Optional.of(new RetiradaResultado(q50, q10));
    }

    public int getNotasDe50() { return notasDe50; }
    public int getNotasDe10() { return notasDe10; }
    public int getTotalRetiradas() { return totalRetiradas; }
    public int getValorTotalDisponivel() { return notasDe50 * NOTA_50 + notasDe10 * NOTA_10; }

    public record RetiradaResultado(int qtd50, int qtd10) {}
}
