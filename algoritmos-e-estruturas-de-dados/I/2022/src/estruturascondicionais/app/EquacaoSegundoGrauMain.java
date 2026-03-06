package estruturascondicionais.app;

import estruturascondicionais.domain.EquacaoSegundoGrau;
import estruturascondicionais.domain.EquacaoSegundoGrau.ResultadoEquacao;
import estruturascondicionais.io.Console;
import estruturascondicionais.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class EquacaoSegundoGrauMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double a = entrada.lerDouble("Digite o coeficiente a (x²): ");
            double b = entrada.lerDouble("Digite o coeficiente b (x): ");
            double c = entrada.lerDouble("Digite o termo constante c: ");
            ResultadoEquacao res = EquacaoSegundoGrau.resolver(a, b, c);
            switch (res.tipo()) {
                case NENHUMA -> Console.println("A equação não possui raízes reais.");
                case UMA -> Console.printf("A equação possui uma raiz real: %.2f%n", res.raiz1());
                case DUAS -> Console.printf("A equação possui duas raízes reais: %.2f e %.2f%n", res.raiz1(), res.raiz2());
            }
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use números válidos.");
        } finally {
            entrada.fechar();
        }
    }
}
