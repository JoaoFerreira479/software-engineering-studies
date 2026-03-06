package programarcomputadoresalternativasdecisao;

public final class Salario {

    private static final double LIMITE_INSS = 150.00;
    private static final double ACRESCIMO_HORAS_EXTRAS = 1.5;
    private static final int HORAS_MENSAL = 176;

    private final double valorNominal;
    private final int horasExtras;

    public Salario(final double valorNominal, final int horasExtras) {
        if (valorNominal <= 0) {
            throw new IllegalArgumentException("O valor nominal do salário deve ser maior que zero.");
        }
        if (horasExtras < 0) {
            throw new IllegalArgumentException("O número de horas extras não pode ser negativo.");
        }
        this.valorNominal = valorNominal;
        this.horasExtras = horasExtras;
    }

    public double calcularValorHorasExtras() {
        final double valorHora = valorNominal / HORAS_MENSAL;
        return valorHora * horasExtras * ACRESCIMO_HORAS_EXTRAS;
    }

    public double calcularDescontoINSS() {
        final double salarioBruto = valorNominal + calcularValorHorasExtras();
        return Math.min(salarioBruto * 0.10, LIMITE_INSS);
    }

    public double calcularSalarioLiquido() {
        return valorNominal + calcularValorHorasExtras() - calcularDescontoINSS();
    }

    @Override
    public String toString() {
        return String.format(
            "Salário Nominal: R$ %.2f%nHoras Extras: %d horas%nValor Horas Extras: R$ %.2f%nDesconto INSS: R$ %.2f%nSalário Líquido: R$ %.2f",
            valorNominal, horasExtras, calcularValorHorasExtras(), calcularDescontoINSS(), calcularSalarioLiquido());
    }
}
