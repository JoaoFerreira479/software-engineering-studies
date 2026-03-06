package conceitosbasicosprogramacao.app;

import conceitosbasicosprogramacao.domain.SomaPA;
import conceitosbasicosprogramacao.io.Console;
import conceitosbasicosprogramacao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class SomaPAMain {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final EntradaUsuario entrada = new EntradaUsuario(scanner);
        try {
            int a1 = entrada.lerInteiro("Digite o primeiro termo da PA (a1): ");
            int r = entrada.lerInteiro("Digite a razão da PA (r): ");
            int n = entrada.lerInteiro("Digite o número de termos (n): ");

            long soma = SomaPA.soma(a1, r, n);
            Console.printf("Soma dos %d primeiros termos (a1=%d, r=%d): %d%n", n, a1, r, soma);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use inteiros.");
        } finally {
            entrada.fechar();
        }
    }
}
