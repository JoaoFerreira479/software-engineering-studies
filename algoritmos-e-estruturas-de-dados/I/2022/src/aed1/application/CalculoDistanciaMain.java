package aed1.application;

import aed1.domain.basico.CalculoDistancia;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class CalculoDistanciaMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double cateto1 = entrada.lerDouble("Digite o primeiro cateto: ");
            double cateto2 = entrada.lerDouble("Digite o segundo cateto: ");
            double hip = CalculoDistancia.hipotenusa(cateto1, cateto2);
            Console.printf("Hipotenusa: %.2f%n", hip);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
