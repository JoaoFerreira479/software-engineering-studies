package aed1.application;

import aed1.domain.condicional.CategoriaNatacao;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class CategoriaNatacaoMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int idade = entrada.lerInteiro("Digite a idade do nadador: ");
            String categoria = CategoriaNatacao.categoria(idade);
            Console.println("Categoria: " + categoria);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
