package aed2.application;

import aed2.domain.nivelamento.IdentificarSimbolo;
import aed2.infrastructure.io.Console;
import aed2.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class IdentificarSimboloMain {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            EntradaUsuario entrada = new EntradaUsuario(scan);
            String token = entrada.lerToken("Digite um símbolo: ");
            if (token == null || token.length() != 1) {
                Console.erro("A entrada deve ser um único caractere.");
                return;
            }
            char simbolo = token.charAt(0);
            String tipo = IdentificarSimbolo.identificarSimbolo(simbolo);
            Console.println(tipo);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        }
    }
}
