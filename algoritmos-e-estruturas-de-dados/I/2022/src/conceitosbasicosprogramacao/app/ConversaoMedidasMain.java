package conceitosbasicosprogramacao.app;

import conceitosbasicosprogramacao.domain.ConversaoMedidas;
import conceitosbasicosprogramacao.io.Console;
import conceitosbasicosprogramacao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class ConversaoMedidasMain {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final EntradaUsuario entrada = new EntradaUsuario(scanner);
        try {
            double metros = entrada.lerDouble("Digite o valor em metros: ");
            double dm = ConversaoMedidas.metrosParaDecimetros(metros);
            double cm = ConversaoMedidas.metrosParaCentimetros(metros);
            double mm = ConversaoMedidas.metrosParaMilimetros(metros);
            Console.printf("Decímetros: %.1f dm%n", dm);
            Console.printf("Centímetros: %.1f cm%n", cm);
            Console.printf("Milímetros: %.1f mm%n", mm);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use um decimal válido.");
        } finally {
            entrada.fechar();
        }
    }
}
