package programarcomputadoresalternativasdecisao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaDiaDaSemana {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final int numeroDia = EntradaUtil.lerInteiroNoIntervalo(scanner, "Digite um número de 1 a 7 para o dia da semana: ", 1, 7);
            final String nomeDia = DiaSemana.obterNomeDia(numeroDia).orElseThrow();
            Console.println("O dia correspondente é: " + nomeDia);
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }
}
