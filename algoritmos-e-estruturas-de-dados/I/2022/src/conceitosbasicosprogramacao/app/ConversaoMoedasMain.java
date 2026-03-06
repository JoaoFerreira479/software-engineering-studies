package conceitosbasicosprogramacao.app;

import conceitosbasicosprogramacao.domain.ConversaoMoedas;
import conceitosbasicosprogramacao.io.Console;
import conceitosbasicosprogramacao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class ConversaoMoedasMain {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final EntradaUsuario entrada = new EntradaUsuario(scanner);
        final ConversaoMoedas conversor = ConversaoMoedas.padrao();
        try {
            double reais = entrada.lerDouble("Digite o valor em reais (R$): ");
            double euros = conversor.reaisParaEuros(reais);
            double dolares = conversor.reaisParaDolares(reais);
            Console.printf("Em euros: € %.2f%n", euros);
            Console.printf("Em dólares: $ %.2f%n", dolares);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use um decimal válido.");
        } finally {
            entrada.fechar();
        }
    }
}
