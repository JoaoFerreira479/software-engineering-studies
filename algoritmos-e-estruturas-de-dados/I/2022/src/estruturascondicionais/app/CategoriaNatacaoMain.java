package estruturascondicionais.app;

import estruturascondicionais.domain.CategoriaNatacao;
import estruturascondicionais.io.Console;
import estruturascondicionais.io.EntradaUsuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class CategoriaNatacaoMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int idade = entrada.lerInteiro("Digite a idade: ");
            String categoria = CategoriaNatacao.categoria(idade);
            Console.printf("Categoria para a idade %d: %s%n", idade, categoria);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } catch (InputMismatchException e) {
            Console.erro("Erro: Entrada inválida. Use um inteiro.");
        } finally {
            entrada.fechar();
        }
    }
}
