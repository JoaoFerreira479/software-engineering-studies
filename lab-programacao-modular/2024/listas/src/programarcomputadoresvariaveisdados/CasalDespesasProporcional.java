package programarcomputadoresvariaveisdados;

public class CasalDespesasProporcional {

    private final double despesasMarido;
    private final double despesasEsposa;
    private final double rendaMarido;
    private final double rendaEsposa;

    public CasalDespesasProporcional(double despesasMarido, double despesasEsposa, double rendaMarido,
            double rendaEsposa) {
        if (despesasMarido < 0 || despesasEsposa < 0 || rendaMarido <= 0 || rendaEsposa <= 0) {
            throw new IllegalArgumentException("Despesas e rendas devem ser valores positivos.");
        }
        this.despesasMarido = despesasMarido;
        this.despesasEsposa = despesasEsposa;
        this.rendaMarido = rendaMarido;
        this.rendaEsposa = rendaEsposa;
    }

    public double calcularTotalDespesas() {
        return despesasMarido + despesasEsposa;
    }

    public double calcularTotalRendas() {
        return rendaMarido + rendaEsposa;
    }

    public double calcularValorDevidoProporcional(double rendaIndividual) {
        double totalRendas = calcularTotalRendas();
        if (totalRendas == 0) return 0;
        return (rendaIndividual / totalRendas) * calcularTotalDespesas();
    }

    public double calcularSaldo(double valorPago, double valorDevidoPessoa) {
        return valorPago - valorDevidoPessoa;
    }

    public double getDespesasMarido() {
        return despesasMarido;
    }

    public double getDespesasEsposa() {
        return despesasEsposa;
    }

    public double getRendaMarido() {
        return rendaMarido;
    }

    public double getRendaEsposa() {
        return rendaEsposa;
    }
}
