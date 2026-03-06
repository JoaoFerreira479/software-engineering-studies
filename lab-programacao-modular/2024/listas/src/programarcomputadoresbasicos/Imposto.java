package programarcomputadoresbasicos;

import java.util.List;
import java.util.Scanner;

public final class Imposto {

    private static final List<FaixaImposto> FAIXAS = List.of(
        new FaixaImposto(0, 1200, 0.0),
        new FaixaImposto(1201, 5000, 0.10),
        new FaixaImposto(5001, 10000, 0.15),
        new FaixaImposto(10001, Double.MAX_VALUE, 0.20)
    );

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final double valor = EntradaUtil.lerDouble(scanner, "Digite o valor para calcular o imposto: ");
            if (valor < 0) {
                System.out.println("Erro: O valor não pode ser negativo.");
                return;
            }
            final double imposto = calcularImposto(valor, FAIXAS);
            exibirResumo(valor, imposto);
        }
    }

    /**
     * Calcula o imposto total e exibe o detalhamento por faixa no console.
     */
    public static double calcularImposto(final double valor, final List<FaixaImposto> faixas) {
        double impostoTotal = 0.0;
        for (final FaixaImposto faixa : faixas) {
            if (faixa.aplica(valor)) {
                final double valorNaFaixa = faixa.valorNaFaixa(valor);
                final double impostoNaFaixa = faixa.impostoNaFaixa(valor);
                impostoTotal += impostoNaFaixa;
                final double limiteSupExib = faixa.limiteSuperior() == Double.MAX_VALUE ? Double.POSITIVE_INFINITY : faixa.limiteSuperior();
                System.out.printf("Faixa: De R$ %.2f a R$ %.2f - %.0f%% sobre R$ %.2f -> Imposto: R$ %.2f%n",
                    faixa.limiteInferior(), limiteSupExib, faixa.aliquota() * 100, valorNaFaixa, impostoNaFaixa);
            }
        }
        return impostoTotal;
    }

    private static void exibirResumo(final double valor, final double imposto) {
        System.out.println("\nResumo do Cálculo:");
        System.out.printf("Valor informado: R$ %.2f%n", valor);
        System.out.printf("Imposto a pagar: R$ %.2f%n", imposto);
    }
}
