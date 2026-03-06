package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class ContaTelefonica {

    private static final double CUSTO_ASSINATURA = 21.40;
    private static final double CUSTO_IMPULSOS_EXCEDENTES = 0.03;
    private static final double CUSTO_INTERURBANOS = 0.40;
    private static final double CUSTO_CHAMADAS_CELULAR = 0.40;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Conta conta = coletarDados(scanner);

            conta.adicionarServico("Impulsos Excedentes", conta.getImpulsosExcedentes(), CUSTO_IMPULSOS_EXCEDENTES);
            conta.adicionarServico("Interurbanos", conta.getImpulsosInterurbanos(), CUSTO_INTERURBANOS);
            conta.adicionarServico("Chamadas para Celular", conta.getImpulsosCelular(), CUSTO_CHAMADAS_CELULAR);

            double total = conta.calcularTotal(CUSTO_ASSINATURA);

            exibirRelatorio(conta, CUSTO_ASSINATURA, total);
        }
    }

    private static Conta coletarDados(Scanner scanner) {
        int impulsosExcedentes = EntradaUtil.lerInteiro(scanner,
                "Digite a quantidade de impulsos excedentes (acima de 90): ");
        int impulsosInterurbanos = EntradaUtil.lerInteiro(scanner,
                "Digite o número de impulsos para interurbanos: ");
        int impulsosCelular = EntradaUtil.lerInteiro(scanner,
                "Digite o número de impulsos para chamadas para celular: ");

        return new Conta(impulsosExcedentes, impulsosInterurbanos, impulsosCelular);
    }

    private static void exibirRelatorio(Conta conta, double assinatura, double total) {
        System.out.println("\n--- Conta Telefônica ---");
        System.out.printf("Custo da assinatura: R$ %.2f\n", assinatura);
        conta.exibirServicos();
        System.out.printf("TOTAL A PAGAR: R$ %.2f\n", total);
    }
}
