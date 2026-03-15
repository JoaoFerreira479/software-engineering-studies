package aed1.application;

import aed1.domain.condicional.TipoTriangulo;
import aed1.infrastructure.io.Console;
import aed1.infrastructure.io.EntradaUsuario;

import java.util.Scanner;

public final class TipoTrianguloMain {

    public static void main(final String[] args) {
        final EntradaUsuario entrada = new EntradaUsuario(new Scanner(System.in));
        try {
            double l1 = entrada.lerDouble("Lado 1: ");
            double l2 = entrada.lerDouble("Lado 2: ");
            double l3 = entrada.lerDouble("Lado 3: ");
            String classificacao = TipoTriangulo.classificar(l1, l2, l3);
            Console.println(classificacao);
        } catch (IllegalArgumentException e) {
            Console.erro("Erro: " + e.getMessage());
        } finally {
            entrada.fechar();
        }
    }
}
