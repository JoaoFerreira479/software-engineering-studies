package conceitosbasicosprogramacao.app;

import conceitosbasicosprogramacao.domain.OperacoesMatematicas;
import conceitosbasicosprogramacao.io.Console;
import conceitosbasicosprogramacao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class OperacoesMatematicasMain {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final EntradaUsuario entrada = new EntradaUsuario(scanner);
        try {
            int x = entrada.lerInteiro("Digite X (inteiro): ");
            int y = entrada.lerInteiro("Digite Y (inteiro): ");

            Console.printf("a) Raiz quadrada da soma dos quadrados: %.2f%n",
                    OperacoesMatematicas.raizQuadradaDosQuadrados(x, y));
            Console.printf("b) Resto de X por Y: %d%n", OperacoesMatematicas.restoDivisao(x, y));
            Console.printf("c) X elevado a Y: %.2f%n", OperacoesMatematicas.potencia(x, y));
            Console.printf("d) Soma: %d%n", OperacoesMatematicas.soma(x, y));
            Console.printf("e) Subtração: %d%n", OperacoesMatematicas.subtracao(x, y));
            Console.printf("f) Multiplicação: %d%n", OperacoesMatematicas.multiplicacao(x, y));
            Console.printf("g) Divisão X/Y: %.2f%n", OperacoesMatematicas.divisao(x, y));
        } catch (ArithmeticException e) {
            Console.erro("Erro matemático: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use inteiros.");
        } finally {
            entrada.fechar();
        }
    }
}
