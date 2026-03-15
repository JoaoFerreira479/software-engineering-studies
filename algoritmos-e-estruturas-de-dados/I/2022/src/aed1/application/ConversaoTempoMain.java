package aed1.application;

import aed1.domain.basico.ConversaoTempo;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class ConversaoTempoMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int totalMinutos = entrada.lerInteiro("Digite o total de minutos: ");
            String resultado = ConversaoTempo.paraHorasMinutosSegundos(totalMinutos);
            Console.println("Formato HH:MM:SS = " + resultado);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
