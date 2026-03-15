package aed2.application;

import aed2.domain.recursao.ContarDigitos;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class ContarDigitosMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            int numero = entrada.lerInteiro("Digite um número inteiro: ");
            int quantidadeDigitos = ContarDigitos.contarDigitos(Math.abs(numero));
            Console.printf("O número %d possui %d dígito(s).%n", numero, quantidadeDigitos);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }
}
