package programarcomputadoresvariaveisdados;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

public final class ExibicaoResultados {

    private ExibicaoResultados() {
    }

    public static void exibirTabela(double despesasMarido, double despesasEsposa, double totalDespesas,
            double rendaMarido, double rendaEsposa, double valorDevidoMarido, double valorDevidoEsposa,
            double saldoMarido, double saldoEsposa) {
        Console.println("\nITEM                MARIDO      ESPOSA      TOTAL");
        Console.println("===================================================");
        Console.printf("Despesas pagas       %.2f     %.2f     %.2f\n", despesasMarido, despesasEsposa,
                totalDespesas);
        Console.printf("Renda                %.2f     %.2f     %.2f\n", rendaMarido, rendaEsposa,
                rendaMarido + rendaEsposa);
        Console.printf("Valor devido         %.2f     %.2f     %.2f\n", valorDevidoMarido, valorDevidoEsposa,
                totalDespesas);
        Console.printf("Saldo                %.2f     %.2f\n", saldoMarido, saldoEsposa);
    }
}
