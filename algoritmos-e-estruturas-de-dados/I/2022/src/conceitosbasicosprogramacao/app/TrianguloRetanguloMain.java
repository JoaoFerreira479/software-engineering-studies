package conceitosbasicosprogramacao.app;

import conceitosbasicosprogramacao.domain.TrianguloRetangulo;
import conceitosbasicosprogramacao.io.Console;
import conceitosbasicosprogramacao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class TrianguloRetanguloMain {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final EntradaUsuario entrada = new EntradaUsuario(scanner);
        try {
            double catetoA = entrada.lerDouble("Digite o cateto a (m): ");
            double catetoB = entrada.lerDouble("Digite o cateto b (m): ");

            TrianguloRetangulo tri = TrianguloRetangulo.comCatetos(catetoA, catetoB);

            Console.printf("a) Hipotenusa (c): %.2f m%n", tri.getHipotenusa());
            Console.printf("b) Seno de θ: %.4f%n", tri.getSeno());
            Console.printf("c) Cosseno de θ: %.4f%n", tri.getCosseno());
            Console.printf("d) Tangente de θ: %.4f%n", tri.getTangente());
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use números válidos.");
        } finally {
            entrada.fechar();
        }
    }
}
