package programarcomputadoresalternativasdecisao;

import java.util.NavigableMap;
import java.util.TreeMap;

public final class CalculadoraImposto {

    private final NavigableMap<Double, Double> faixas;

    public CalculadoraImposto() {
        faixas = new TreeMap<>();
        faixas.put(1200.00, 0.0);
        faixas.put(2500.00, 0.10);
        faixas.put(5000.00, 0.15);
        faixas.put(Double.MAX_VALUE, 0.20);
    }

    /**
     * Calcula o imposto para o valor base (>= 0).
     * Faixa: menor limite de faixa >= valorBase.
     */
    public double calcularImposto(final double valorBase) {
        if (valorBase < 0) {
            throw new IllegalArgumentException("O valor base deve ser maior ou igual a zero.");
        }
        final double taxa = faixas.ceilingEntry(valorBase).getValue();
        return valorBase * taxa;
    }
}
