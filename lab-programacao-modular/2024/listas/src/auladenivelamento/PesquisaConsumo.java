package auladenivelamento;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PesquisaConsumo {

    private final List<Consumidor> consumidores;
    private final double precoKWh;

    public PesquisaConsumo(final double precoKWh) {
        if (precoKWh <= 0) {
            throw new IllegalArgumentException("Preço do kWh deve ser positivo: " + precoKWh);
        }
        this.precoKWh = precoKWh;
        this.consumidores = new java.util.ArrayList<>();
    }

    public void adicionarConsumidor(final int numero, final double consumoKWh, final TipoConsumidor tipo) {
        consumidores.add(Consumidor.of(numero, consumoKWh, tipo, precoKWh));
    }

    public double getPrecoKWh() {
        return precoKWh;
    }

    public List<Consumidor> getConsumidores() {
        return List.copyOf(consumidores);
    }

    public int getQuantidadeConsumidores() {
        return consumidores.size();
    }

    public boolean isEmpty() {
        return consumidores.isEmpty();
    }

    public double calcularMaiorConsumo() {
        return consumidores.stream()
            .mapToDouble(Consumidor::consumoKWh)
            .max()
            .orElse(0.0);
    }

    public double calcularMenorConsumo() {
        return consumidores.stream()
            .mapToDouble(Consumidor::consumoKWh)
            .min()
            .orElse(0.0);
    }

    public double calcularMediaConsumo() {
        return consumidores.stream()
            .mapToDouble(Consumidor::consumoKWh)
            .average()
            .orElse(0.0);
    }

    public Map<TipoConsumidor, Double> calcularConsumoPorTipo() {
        final Map<TipoConsumidor, Double> map = consumidores.stream()
            .collect(Collectors.groupingBy(
                Consumidor::tipo,
                Collectors.summingDouble(Consumidor::consumoKWh)));
        for (final TipoConsumidor t : TipoConsumidor.values()) {
            map.putIfAbsent(t, 0.0);
        }
        return Map.copyOf(map);
    }

    public void exibirResultados(final PrintStream out) {
        out.println("\n--- Resultados por Consumidor ---");
        for (final Consumidor c : consumidores) {
            out.printf("Consumidor %d: Total a pagar: R$ %.2f%n", c.numero(), c.totalPagar());
        }

        out.println("\n--- Estatísticas Gerais ---");
        out.printf("Maior consumo: %.2f kWh%n", calcularMaiorConsumo());
        out.printf("Menor consumo: %.2f kWh%n", calcularMenorConsumo());

        final Map<TipoConsumidor, Double> consumoPorTipo = calcularConsumoPorTipo();
        for (final TipoConsumidor tipo : TipoConsumidor.values()) {
            out.printf("Total consumo %s: %.2f kWh%n",
                tipo.getValor(), consumoPorTipo.getOrDefault(tipo, 0.0));
        }
        out.printf("Média geral de consumo: %.2f kWh%n", calcularMediaConsumo());
    }

    public static void main(final String[] args) {
        PesquisaConsumoApp.main(args);
    }
}
