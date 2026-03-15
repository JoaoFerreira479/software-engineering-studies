package programarcomputadoresrepeticao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaTabuada {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                final int numero = EntradaUtil.lerInteiroNoIntervalo(scanner, "Digite um número entre 1 e 9: ", 1, 9);
                Tabuada.exibirTabuada(numero);
                continuar = EntradaUtil.perguntarSimNao(scanner, "Deseja ver outra tabuada? (S/N): ");
            }
            Console.println("Programa encerrado. Obrigado!");
        }
    }
}
