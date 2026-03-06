package conceitosbasicosprogramacao.app;

import conceitosbasicosprogramacao.domain.ConversaoTempo;
import conceitosbasicosprogramacao.io.Console;
import conceitosbasicosprogramacao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class ConversaoTempoMain {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final EntradaUsuario entrada = new EntradaUsuario(scanner);
        try {
            int minutos = entrada.lerInteiro("Digite a quantidade de minutos: ");
            String resultado = ConversaoTempo.paraHorasMinutosSegundos(minutos);
            Console.printf("Tempo convertido: %s%n", resultado);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use um inteiro.");
        } finally {
            entrada.fechar();
        }
    }
}
