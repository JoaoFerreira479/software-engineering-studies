package programarcomputadoresalternativasdecisao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaAprovacao {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final String nome = EntradaUtil.lerLinha(scanner, "Digite o nome da disciplina: ");
            if (nome.isEmpty()) {
                Console.println("Erro: O nome da disciplina não pode ser vazio.");
                return;
            }
            final double notaFinal = EntradaUtil.lerDouble(scanner, "Digite a nota final (deve ser múltipla de 0,5): ");
            final int aulasMinistradas = EntradaUtil.lerInteiro(scanner, "Digite a quantidade de aulas ministradas: ");
            final int faltas = EntradaUtil.lerInteiro(scanner, "Digite a quantidade de faltas: ");

            final Disciplina disciplina = new Disciplina(nome, notaFinal, aulasMinistradas, faltas);
            final String resultado = Aprovacao.verificarAprovacao(disciplina);

            Console.println("\n=== Resultado ===");
            Console.println("Disciplina: " + disciplina.getNome());
            Console.println("Nota Final: " + disciplina.getNota());
            Console.printf("Frequência: %.2f%%%n", disciplina.getFrequencia());
            Console.println("Resultado: " + resultado);
        } catch (IllegalArgumentException e) {
            Console.println("Erro: " + e.getMessage());
        }
    }
}
