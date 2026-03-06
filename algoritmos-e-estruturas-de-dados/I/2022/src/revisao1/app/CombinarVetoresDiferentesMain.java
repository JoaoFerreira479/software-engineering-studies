package revisao1.app;

import revisao1.domain.CombinarVetores;
import revisao1.io.Console;
import revisao1.io.EntradaUsuario;

import java.util.Scanner;

public final class CombinarVetoresDiferentesMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int m = entrada.lerInteiro("Digite o tamanho do primeiro vetor (M): ");
            int n = entrada.lerInteiro("Digite o tamanho do segundo vetor (N): ");
            CombinarVetores.validarTamanhos(m, n);
            int[] v1 = LeituraDados.lerVetor(entrada, m, "primeiro");
            int[] v2 = LeituraDados.lerVetor(entrada, n, "segundo");
            int[] combinado = CombinarVetores.combinar(v1, v2);
            Console.println("Vetor combinado:");
            exibirVetor(combinado);
        } catch (Exception e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }

    private static void exibirVetor(final int[] v) {
        for (int x : v) Console.print(x + " ");
        Console.println("");
    }
}
