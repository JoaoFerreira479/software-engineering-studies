package programarcomputadoresalternativasdecisao;

public final class ContaTelefonica {

    private static final int IMPULSOS_INCLUSOS = 90;

    private final Tarifas tarifas;
    private final int impulsosTotais;
    private final int impulsosCelular;
    private final double valorInterurbanos;

    public ContaTelefonica(final Tarifas tarifas, final int impulsosTotais, final int impulsosCelular, final double valorInterurbanos) {
        if (tarifas == null) {
            throw new NullPointerException("Tarifas não podem ser nulas.");
        }
        if (impulsosTotais < 0 || impulsosCelular < 0 || valorInterurbanos < 0) {
            throw new IllegalArgumentException("Os valores de impulsos e interurbanos devem ser não negativos.");
        }
        if (impulsosCelular > impulsosTotais) {
            throw new IllegalArgumentException("O número de impulsos para celular não pode ser maior que o total de impulsos.");
        }
        this.tarifas = tarifas;
        this.impulsosTotais = impulsosTotais;
        this.impulsosCelular = impulsosCelular;
        this.valorInterurbanos = valorInterurbanos;
    }

    public double calcularCustoImpulsosExtras() {
        final int extras = Math.max(impulsosTotais - IMPULSOS_INCLUSOS, 0);
        return extras * tarifas.custoImpulsoExtra();
    }

    public double calcularCustoChamadaCelular() {
        return impulsosCelular * tarifas.custoChamadaCelular();
    }

    public double calcularValorTotal() {
        return tarifas.valorAssinatura() + calcularCustoImpulsosExtras() + calcularCustoChamadaCelular() + valorInterurbanos;
    }

    public Tarifas getTarifas() {
        return tarifas;
    }
}
