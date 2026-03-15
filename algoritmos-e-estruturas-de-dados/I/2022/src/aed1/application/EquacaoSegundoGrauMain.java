package aed1.application;

import aed1.domain.condicional.EquacaoSegundoGrau;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class EquacaoSegundoGrauMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double a = entrada.lerDouble("Coeficiente a: ");
            double b = entrada.lerDouble("Coeficiente b: ");
            double c = entrada.lerDouble("Coeficiente c: ");
            var res = EquacaoSegundoGrau.resolver(a, b, c);
            switch (res.tipo()) {
                case NENHUMA -> Console.println("Não há raízes reais.");
                case UMA -> Console.printf("Uma raiz real: %.2f%n", res.raiz1());
                case DUAS -> Console.printf("Duas raízes reais: %.2f e %.2f%n", res.raiz1(), res.raiz2());
            }
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
