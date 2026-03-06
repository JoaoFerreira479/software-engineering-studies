package conceitosbasicosprogramacao.app;

import conceitosbasicosprogramacao.domain.AreaTrapezio;
import conceitosbasicosprogramacao.io.Console;
import conceitosbasicosprogramacao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class AreaTrapezioMain {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final EntradaUsuario entrada = new EntradaUsuario(scanner);
        try {
            double baseMaior = entrada.lerDouble("Digite a base maior do trapézio (m): ");
            double baseMenor = entrada.lerDouble("Digite a base menor do trapézio (m): ");
            double altura = entrada.lerDouble("Digite a altura do trapézio (m): ");

            double area = AreaTrapezio.area(baseMaior, baseMenor, altura);
            Console.printf("A área do trapézio é: %.2f m².%n", area);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use números válidos.");
        } finally {
            entrada.fechar();
        }
    }
}
