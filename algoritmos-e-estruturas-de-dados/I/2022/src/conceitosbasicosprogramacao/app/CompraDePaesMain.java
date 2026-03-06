package conceitosbasicosprogramacao.app;

import conceitosbasicosprogramacao.domain.CompraPaes;
import conceitosbasicosprogramacao.io.Console;
import conceitosbasicosprogramacao.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class CompraDePaesMain {

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final EntradaUsuario entrada = new EntradaUsuario(scanner);
        try {
            double dinheiro = entrada.lerDouble("Digite o dinheiro disponível (R$): ");
            int precoCentavos = entrada.lerInteiro("Digite o preço de cada pão (centavos): ");

            int qtd = CompraPaes.quantidadePaes(dinheiro, precoCentavos);
            double troco = CompraPaes.trocoReais(dinheiro, precoCentavos);

            Console.printf("Você pode comprar %d pão(ões).%n", qtd);
            Console.printf("Troco: R$ %.2f.%n", troco);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use números válidos.");
        } finally {
            entrada.fechar();
        }
    }
}
