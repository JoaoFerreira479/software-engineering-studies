package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class DespesasDeCasal {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            double despesasMarido = EntradaUtil.lerDouble(scanner, "Digite o valor das despesas do marido: ");
            double despesasEsposa = EntradaUtil.lerDouble(scanner, "Digite o valor das despesas da esposa: ");

            try {
                CasalDespesas casal = new CasalDespesas(despesasMarido, despesasEsposa);

                double totalDespesas = casal.calcularTotalDespesas();
                double percentualMarido = casal.calcularPercentual(casal.getDespesasMarido());
                double percentualEsposa = casal.calcularPercentual(casal.getDespesasEsposa());
                double valorDevido = casal.calcularValorDevido();
                double saldoMarido = casal.calcularSaldo(casal.getDespesasMarido());
                double saldoEsposa = casal.calcularSaldo(casal.getDespesasEsposa());

                exibirResultados(despesasMarido, despesasEsposa, totalDespesas, percentualMarido, percentualEsposa,
                        valorDevido, saldoMarido, saldoEsposa);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void exibirResultados(double despesasMarido, double despesasEsposa, double despesasTotais,
            double percentualMarido, double percentualEsposa, double valorDevido, double saldoMarido,
            double saldoEsposa) {
        System.out.println("\nITEM            MARIDO      ESPOSA      TOTAL");
        System.out.println("===============================================");
        System.out.printf("Despesas pagas   %.2f     %.2f     %.2f\n", despesasMarido, despesasEsposa, despesasTotais);
        System.out.printf("%% pago           %.2f%%     %.2f%%     %.0f%%\n", percentualMarido, percentualEsposa, 100.0);
        System.out.printf("Valor devido     %.2f     %.2f     %.2f\n", valorDevido, valorDevido, despesasTotais);
        System.out.printf("Saldo            %.2f     %.2f\n", saldoMarido, saldoEsposa);
    }
}
