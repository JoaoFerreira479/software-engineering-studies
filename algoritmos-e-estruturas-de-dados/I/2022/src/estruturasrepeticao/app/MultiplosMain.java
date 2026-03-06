package estruturasrepeticao.app;

import estruturasrepeticao.domain.Multiplos;
import estruturasrepeticao.io.Console;
import estruturasrepeticao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class MultiplosMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int x = entrada.lerInteiro("Digite X (número base para os múltiplos): ");
            int y = entrada.lerInteiro("Digite Y (limite superior): ");
            String multiplos = Multiplos.multiplosDeXAteY(x, y);
            Console.printf("Múltiplos de %d de 1 até %d:%n%s%n", x, y, multiplos);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use inteiros.");
        } finally {
            entrada.fechar();
        }
    }
}
