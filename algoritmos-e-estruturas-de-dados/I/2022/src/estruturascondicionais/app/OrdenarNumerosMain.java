package estruturascondicionais.app;

import estruturascondicionais.domain.OrdenarNumeros;
import estruturascondicionais.io.Console;
import estruturascondicionais.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class OrdenarNumerosMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int a = entrada.lerInteiro("Digite o primeiro número: ");
            int b = entrada.lerInteiro("Digite o segundo número: ");
            int c = entrada.lerInteiro("Digite o terceiro número: ");
            int[] ord = OrdenarNumeros.ordenarTres(a, b, c);
            Console.printf("Ordem crescente: %d, %d, %d%n", ord[0], ord[1], ord[2]);
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use inteiros.");
        } finally {
            entrada.fechar();
        }
    }
}
