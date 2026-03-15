package aed1.application;

import aed1.domain.basico.TrianguloRetangulo;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class TrianguloRetanguloMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double catetoA = entrada.lerDouble("Cateto A: ");
            double catetoB = entrada.lerDouble("Cateto B: ");
            TrianguloRetangulo tri = TrianguloRetangulo.comCatetos(catetoA, catetoB);
            Console.printf("Hipotenusa: %.2f%n", tri.getHipotenusa());
            Console.printf("Seno: %.4f%n", tri.getSeno());
            Console.printf("Cosseno: %.4f%n", tri.getCosseno());
            Console.printf("Tangente: %.4f%n", tri.getTangente());
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
