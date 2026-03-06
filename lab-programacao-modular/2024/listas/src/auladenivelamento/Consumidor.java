package auladenivelamento;

public record Consumidor(
    int numero,
    double consumoKWh,
    TipoConsumidor tipo,
    double totalPagar
) {

    public Consumidor {
        if (numero < 1) {
            throw new IllegalArgumentException("Número do consumidor deve ser positivo: " + numero);
        }
        if (consumoKWh < 0) {
            throw new IllegalArgumentException("Consumo em kWh não pode ser negativo: " + consumoKWh);
        }
        if (tipo == null) {
            throw new NullPointerException("Tipo do consumidor não pode ser nulo");
        }
        if (totalPagar < 0) {
            throw new IllegalArgumentException("Total a pagar não pode ser negativo: " + totalPagar);
        }
    }

    public static Consumidor of(final int numero, final double consumoKWh, final TipoConsumidor tipo, final double precoKWh) {
        if (precoKWh <= 0) {
            throw new IllegalArgumentException("Preço do kWh deve ser positivo: " + precoKWh);
        }
        final double totalPagar = consumoKWh * precoKWh;
        return new Consumidor(numero, consumoKWh, tipo, totalPagar);
    }
}
