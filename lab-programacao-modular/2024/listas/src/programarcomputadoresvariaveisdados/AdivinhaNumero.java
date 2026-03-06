package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class AdivinhaNumero {

    private static final int RESULTADO_MINIMO = 3;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Adivinha adivinha = new Adivinha();

            adivinha.exibirInstrucoes();

            int resultado = lerResultado(scanner);
            int numeroPensado = adivinha.calcularNumeroPensado(resultado);

            System.out.printf("Você pensou no número: %d\n", numeroPensado);
        }
    }

    private static int lerResultado(Scanner scanner) {
        while (true) {
            int valor = EntradaUtil.lerInteiro(scanner, "Digite o número final que você obteve: ");
            if (valor >= RESULTADO_MINIMO) {
                return valor;
            }
            System.out.println("O número final deve ser maior ou igual a " + RESULTADO_MINIMO + ". Tente novamente.");
        }
    }
}
