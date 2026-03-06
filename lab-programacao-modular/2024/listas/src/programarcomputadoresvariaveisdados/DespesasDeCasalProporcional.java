package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class DespesasDeCasalProporcional {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            double despesasMarido = EntradaUtil.lerDouble(scanner, "Digite o valor das despesas do marido: ");
            double despesasEsposa = EntradaUtil.lerDouble(scanner, "Digite o valor das despesas da esposa: ");
            double rendaMarido = EntradaUtil.lerDouble(scanner, "Digite a renda do marido: ");
            double rendaEsposa = EntradaUtil.lerDouble(scanner, "Digite a renda da esposa: ");

            try {
                CasalDespesasProporcional casal = new CasalDespesasProporcional(despesasMarido, despesasEsposa,
                        rendaMarido, rendaEsposa);

                double totalDespesas = casal.calcularTotalDespesas();
                double valorDevidoMarido = casal.calcularValorDevidoProporcional(casal.getRendaMarido());
                double valorDevidoEsposa = casal.calcularValorDevidoProporcional(casal.getRendaEsposa());
                double saldoMarido = casal.calcularSaldo(casal.getDespesasMarido(), valorDevidoMarido);
                double saldoEsposa = casal.calcularSaldo(casal.getDespesasEsposa(), valorDevidoEsposa);

                ExibicaoResultados.exibirTabela(despesasMarido, despesasEsposa, totalDespesas, rendaMarido, rendaEsposa,
                        valorDevidoMarido, valorDevidoEsposa, saldoMarido, saldoEsposa);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
