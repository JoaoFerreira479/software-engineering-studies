package aed1.application;

import aed1.domain.basico.CompraPaes;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class CompraDePaesMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double dinheiro = entrada.lerDouble("Digite o valor em reais: ");
            int precoCentavos = entrada.lerInteiro("Digite o preço do pão em centavos: ");
            int qtd = CompraPaes.quantidadePaes(dinheiro, precoCentavos);
            double troco = CompraPaes.trocoReais(dinheiro, precoCentavos);
            Console.println("Quantidade de pães: " + qtd);
            Console.printf("Troco: R$ %.2f%n", troco);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
