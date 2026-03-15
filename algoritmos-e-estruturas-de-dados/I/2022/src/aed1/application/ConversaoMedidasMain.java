package aed1.application;

import aed1.domain.basico.ConversaoMedidas;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class ConversaoMedidasMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double metros = entrada.lerDouble("Digite a medida em metros: ");
            Console.printf("Decímetros: %.2f%n", ConversaoMedidas.metrosParaDecimetros(metros));
            Console.printf("Centímetros: %.2f%n", ConversaoMedidas.metrosParaCentimetros(metros));
            Console.printf("Milímetros: %.2f%n", ConversaoMedidas.metrosParaMilimetros(metros));
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
