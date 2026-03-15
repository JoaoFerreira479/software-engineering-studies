package aed1.application;

import aed1.domain.basico.OperacoesMatematicas;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class OperacoesMatematicasMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int x = entrada.lerInteiro("Digite o primeiro inteiro: ");
            int y = entrada.lerInteiro("Digite o segundo inteiro: ");
            Console.println("Soma: " + OperacoesMatematicas.soma(x, y));
            Console.println("Subtração: " + OperacoesMatematicas.subtracao(x, y));
            Console.println("Multiplicação: " + OperacoesMatematicas.multiplicacao(x, y));
            Console.printf("Divisão: %.2f%n", OperacoesMatematicas.divisao(x, y));
            Console.println("Resto: " + OperacoesMatematicas.restoDivisao(x, y));
            Console.printf("Potência: %.2f%n", OperacoesMatematicas.potencia(x, y));
            Console.printf("Raiz dos quadrados: %.2f%n", OperacoesMatematicas.raizQuadradaDosQuadrados(x, y));
        } catch (ArithmeticException | IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
