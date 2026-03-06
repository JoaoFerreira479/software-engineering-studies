package estruturascondicionais.app;

import estruturascondicionais.domain.TipoTriangulo;
import estruturascondicionais.io.Console;
import estruturascondicionais.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class TipoTrianguloMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double l1 = entrada.lerDouble("Digite o lado 1 do triângulo: ");
            double l2 = entrada.lerDouble("Digite o lado 2 do triângulo: ");
            double l3 = entrada.lerDouble("Digite o lado 3 do triângulo: ");
            String tipo = TipoTriangulo.classificar(l1, l2, l3);
            Console.printf("O triângulo é: %s%n", tipo);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use números válidos.");
        } finally {
            entrada.fechar();
        }
    }
}
