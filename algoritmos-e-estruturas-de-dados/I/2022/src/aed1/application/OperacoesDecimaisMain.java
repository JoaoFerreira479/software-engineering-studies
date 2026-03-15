package aed1.application;

import aed1.domain.basico.OperacoesDecimais;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class OperacoesDecimaisMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double numero = entrada.lerDouble("Digite um número decimal: ");
            Console.printf("Valor absoluto: %.2f%n", OperacoesDecimais.valorAbsoluto(numero));
            Console.printf("Teto: %.2f%n", OperacoesDecimais.teto(numero));
            Console.printf("Piso: %.2f%n", OperacoesDecimais.piso(numero));
        } finally {
            entrada.fechar();
        }
    }
}
