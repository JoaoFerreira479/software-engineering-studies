package programarcomputadoresvariaveisdados;

public final class ExibicaoResultados {

    private ExibicaoResultados() {
    }

    public static void exibirTabela(double despesasMarido, double despesasEsposa, double totalDespesas,
            double rendaMarido, double rendaEsposa, double valorDevidoMarido, double valorDevidoEsposa,
            double saldoMarido, double saldoEsposa) {
        System.out.println("\nITEM                MARIDO      ESPOSA      TOTAL");
        System.out.println("===================================================");
        System.out.printf("Despesas pagas       %.2f     %.2f     %.2f\n", despesasMarido, despesasEsposa,
                totalDespesas);
        System.out.printf("Renda                %.2f     %.2f     %.2f\n", rendaMarido, rendaEsposa,
                rendaMarido + rendaEsposa);
        System.out.printf("Valor devido         %.2f     %.2f     %.2f\n", valorDevidoMarido, valorDevidoEsposa,
                totalDespesas);
        System.out.printf("Saldo                %.2f     %.2f\n", saldoMarido, saldoEsposa);
    }
}
