package conceitosbasicosprogramacao.app;

import conceitosbasicosprogramacao.domain.OperacoesDecimais;
import conceitosbasicosprogramacao.io.Console;
import conceitosbasicosprogramacao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class OperacoesDecimaisMain {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final EntradaUsuario entrada = new EntradaUsuario(scanner);
        try {
            double numero = entrada.lerDouble("Digite um número decimal: ");
            Console.printf("a) Valor absoluto: %.2f%n", OperacoesDecimais.valorAbsoluto(numero));
            Console.printf("b) Teto (arred. para cima): %.0f%n", OperacoesDecimais.teto(numero));
            Console.printf("c) Piso (arred. para baixo): %.0f%n", OperacoesDecimais.piso(numero));
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use um decimal válido.");
        } finally {
            entrada.fechar();
        }
    }
}
