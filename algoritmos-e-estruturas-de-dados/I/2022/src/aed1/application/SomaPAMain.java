package aed1.application;

import aed1.domain.basico.SomaPA;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class SomaPAMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            int a1 = entrada.lerInteiro("Primeiro termo (a1): ");
            int r = entrada.lerInteiro("Razão (r): ");
            int n = entrada.lerInteiro("Número de termos (n): ");
            long soma = SomaPA.soma(a1, r, n);
            Console.println("Soma da PA: " + soma);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
