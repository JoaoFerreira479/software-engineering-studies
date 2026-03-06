package conceitosbasicosprogramacao.domain;

public final class ConversaoMoedas {

    private final double taxaEuro;
    private final double taxaDolar;

    public ConversaoMoedas(final double taxaEuro, final double taxaDolar) {
        if (taxaEuro <= 0 || taxaDolar <= 0) {
            throw new IllegalArgumentException("Taxas devem ser positivas.");
        }
        this.taxaEuro = taxaEuro;
        this.taxaDolar = taxaDolar;
    }

    public static ConversaoMoedas padrao() {
        return new ConversaoMoedas(5.21, 4.74);
    }

    public double reaisParaEuros(final double valorReais) {
        if (valorReais < 0) {
            throw new IllegalArgumentException("Valor em reais não pode ser negativo.");
        }
        return valorReais / taxaEuro;
    }

    public double reaisParaDolares(final double valorReais) {
        if (valorReais < 0) {
            throw new IllegalArgumentException("Valor em reais não pode ser negativo.");
        }
        return valorReais / taxaDolar;
    }
}
