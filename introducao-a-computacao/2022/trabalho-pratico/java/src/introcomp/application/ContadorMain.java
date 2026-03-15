package introcomp.application;

import introcomp.domain.ContadorParesImpares;
import introcomp.domain.ResultadoContagem;
import introcomp.infrastructure.io.Console;
import introcomp.infrastructure.io.EntradaUsuario;

import java.util.Scanner;


public final class ContadorMain {

    private static final String MSG_ERRO_ENTRADA = "Erro: entrada inválida. Informe um número inteiro.";
    private static final String MSG_ERRO_QUANTIDADE = "Erro: a quantidade deve ser maior que zero.";

    public static void main(String[] args) {
        int code = run(System.in);
        if (code != 0) {
            System.exit(code);
        }
    }

    static int run(java.io.InputStream in) {
        try (Scanner scanner = new Scanner(in)) {
            EntradaUsuario entrada = new EntradaUsuario(scanner);
            Integer q = entrada.lerInteiro("Quantos números deseja inserir? ");
            if (q == null || q <= 0) {
                Console.erro(q == null ? MSG_ERRO_ENTRADA : MSG_ERRO_QUANTIDADE);
                return 1;
            }
            int[] numeros = lerNumeros(entrada, q);
            if (numeros == null) {
                Console.erro(MSG_ERRO_ENTRADA);
                return 1;
            }
            ResultadoContagem resultado = ContadorParesImpares.contar(numeros);
            Console.println(ContadorParesImpares.formatarResultado(resultado));
            return 0;
        }
    }

    private static int[] lerNumeros(EntradaUsuario entrada, int quantidade) {
        int[] numeros = new int[quantidade];
        for (int i = 0; i < quantidade; i++) {
            Integer valor = entrada.lerInteiro("Digite o " + (i + 1) + "º número: ");
            if (valor == null) {
                return null;
            }
            numeros[i] = valor;
        }
        return numeros;
    }
}
