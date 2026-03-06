package conceitosbasicosprogramacao.app;

import conceitosbasicosprogramacao.domain.CalculoDistancia;
import conceitosbasicosprogramacao.io.Console;
import conceitosbasicosprogramacao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class CalculoDistanciaMain {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final EntradaUsuario entrada = new EntradaUsuario(scanner);
        try {
            double ab = entrada.lerDouble("Digite a distância do segmento AB (m): ");
            double bc = entrada.lerDouble("Digite a distância do segmento BC (m): ");

            double ac = CalculoDistancia.hipotenusa(ab, bc);
            Console.printf("Distância A a C: %.2f m.%n", ac);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use números válidos.");
        } finally {
            entrada.fechar();
        }
    }
}
