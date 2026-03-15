package aed1.application;

import aed1.domain.basico.ConversaoMoedas;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class ConversaoMoedasMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            ConversaoMoedas conv = ConversaoMoedas.padrao();
            double reais = entrada.lerDouble("Digite o valor em reais: ");
            Console.printf("Em euros: %.2f%n", conv.reaisParaEuros(reais));
            Console.printf("Em dólares: %.2f%n", conv.reaisParaDolares(reais));
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
