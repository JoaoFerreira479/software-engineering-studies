package usodeflag.app;

import usodeflag.domain.ContarLancamentosMoeda;
import usodeflag.io.Console;
import usodeflag.io.EntradaUsuario;

import java.util.Scanner;

public final class ContarLancamentosMoedaMain {

    private static final int MAX_LANCAMENTOS = 10_000;

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            Console.println("Digite os lançamentos da moeda:");
            Console.println(ContarLancamentosMoeda.CARA + " para Cara, " + ContarLancamentosMoeda.COROA
                    + " para Coroa, " + ContarLancamentosMoeda.ENCERRAR + " para encerrar.");

            int[] buffer = new int[MAX_LANCAMENTOS];
            int n = 0;

            while (n < MAX_LANCAMENTOS) {
                int valor = entrada.lerInteiro("Lançamento: ");
                if (valor == ContarLancamentosMoeda.ENCERRAR) break;
                if (valor != ContarLancamentosMoeda.CARA && valor != ContarLancamentosMoeda.COROA) {
                    Console.println("Entrada inválida! Digite " + ContarLancamentosMoeda.CARA + " (Cara), "
                            + ContarLancamentosMoeda.COROA + " (Coroa) ou " + ContarLancamentosMoeda.ENCERRAR + " para encerrar.");
                    continue;
                }
                buffer[n++] = valor;
            }

            var res = ContarLancamentosMoeda.contar(buffer, n);
            Console.println("Resultados dos lançamentos:");
            Console.println("Cara: " + res.cara() + " ocorrência(s)");
            Console.println("Coroa: " + res.coroa() + " ocorrência(s)");
        } catch (Exception e) {
            Console.erro("Erro inesperado: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
