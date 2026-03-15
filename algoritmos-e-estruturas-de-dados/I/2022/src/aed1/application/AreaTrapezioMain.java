package aed1.application;

import aed1.domain.basico.AreaTrapezio;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class AreaTrapezioMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double baseMaior = entrada.lerDouble("Digite a base maior do trapézio (m): ");
            double baseMenor = entrada.lerDouble("Digite a base menor do trapézio (m): ");
            double altura = entrada.lerDouble("Digite a altura do trapézio (m): ");

            double area = AreaTrapezio.area(baseMaior, baseMenor, altura);
            Console.printf("A área do trapézio é: %.2f m².%n", area);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
