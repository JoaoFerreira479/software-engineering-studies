package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class NotasDoProfessor {

    private static final int NOTA_MIN = 0;
    private static final int NOTA_MAX = 10;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            CalculadoraNotas calculadora = new CalculadoraNotas();

            int notaExercicio1 = EntradaUtil.lerInteiroNoIntervalo(scanner,
                    "Digite a nota do exercício 1 (de 0 a 10): ", NOTA_MIN, NOTA_MAX);
            int notaExercicio2 = EntradaUtil.lerInteiroNoIntervalo(scanner,
                    "Digite a nota do exercício 2 (de 0 a 10): ", NOTA_MIN, NOTA_MAX);
            double notaProva = lerNotaDecimalNoIntervalo(scanner,
                    "Digite a nota da prova (de 0 a 10, com uma casa decimal): ", NOTA_MIN, NOTA_MAX);

            Aluno aluno = new Aluno(notaExercicio1, notaExercicio2, notaProva);

            double mediaExercicios = calculadora.calcularMediaExercicios(aluno.getNotaExercicio1(),
                    aluno.getNotaExercicio2());
            double notaFinal = calculadora.calcularNotaFinal(mediaExercicios, aluno.getNotaProva());

            System.out.printf("\nMédia dos exercícios: %.1f\n", mediaExercicios);
            System.out.printf("Nota final: %.1f\n", notaFinal);
        }
    }

    private static double lerNotaDecimalNoIntervalo(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            double nota = EntradaUtil.lerDouble(scanner, prompt);
            if (nota >= min && nota <= max) {
                return nota;
            }
            System.out.println("Nota inválida! Digite um valor entre " + min + " e " + max + ".");
        }
    }
}
