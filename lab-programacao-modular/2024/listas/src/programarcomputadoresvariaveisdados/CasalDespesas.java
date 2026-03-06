package programarcomputadoresvariaveisdados;

public class CasalDespesas {

    private static final int DIVISAO_IGUAL = 2;

    private final double despesasMarido;
    private final double despesasEsposa;

    public CasalDespesas(double despesasMarido, double despesasEsposa) {
        if (despesasMarido < 0 || despesasEsposa < 0) {
            throw new IllegalArgumentException("Despesas não podem ser negativas.");
        }
        this.despesasMarido = despesasMarido;
        this.despesasEsposa = despesasEsposa;
    }

    public double calcularTotalDespesas() {
        return despesasMarido + despesasEsposa;
    }

    public double calcularPercentual(double valorPago) {
        double total = calcularTotalDespesas();
        if (total == 0) return 0;
        return (valorPago / total) * 100;
    }

    public double calcularValorDevido() {
        return calcularTotalDespesas() / DIVISAO_IGUAL;
    }

    public double calcularSaldo(double valorPago) {
        return valorPago - calcularValorDevido();
    }

    public double getDespesasMarido() {
        return despesasMarido;
    }

    public double getDespesasEsposa() {
        return despesasEsposa;
    }
}
