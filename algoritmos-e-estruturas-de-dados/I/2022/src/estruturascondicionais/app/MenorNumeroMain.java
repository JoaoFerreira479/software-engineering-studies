package estruturascondicionais.app;

import estruturascondicionais.domain.MenorNumero;
import estruturascondicionais.io.Console;
import estruturascondicionais.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class MenorNumeroMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int a = entrada.lerInteiro("Digite o primeiro número: ");
            int b = entrada.lerInteiro("Digite o segundo número: ");
            int c = entrada.lerInteiro("Digite o terceiro número: ");
            int menor = MenorNumero.menor(a, b, c);
            Console.printf("O menor número é: %d%n", menor);
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use inteiros.");
        } finally {
            entrada.fechar();
        }
    }
}
