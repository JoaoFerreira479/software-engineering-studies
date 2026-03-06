import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public final class ContadorParesImpares {

    private static final String MSG_ERRO_ENTRADA = "Erro: entrada inválida. Informe um número inteiro.";
    private static final String MSG_ERRO_QUANTIDADE = "Erro: a quantidade deve ser maior que zero.";

    private ContadorParesImpares() {}

    public record ResultadoContagem(int pares, int impares) {
        public ResultadoContagem {
            if (pares < 0 || impares < 0) {
                throw new IllegalArgumentException("pares e impares devem ser não negativos");
            }
        }
    }

    public static ResultadoContagem contar(final List<Integer> numeros) {
        Objects.requireNonNull(numeros, "numeros");
        int pares = 0, impares = 0;
        for (int n : numeros) {
            if (n % 2 == 0) {
                pares++;
            } else {
                impares++;
            }
        }
        return new ResultadoContagem(pares, impares);
    }

    public static String formatarResultado(final ResultadoContagem r) {
        Objects.requireNonNull(r, "resultado");
        return """
                ===== Resultado =====
                Quantidade de números pares: %d
                Quantidade de números ímpares: %d""".formatted(r.pares(), r.impares());
    }

    public static void main(String[] args) {
        int code = run(System.in, System.out, System.err);
        if (code != 0) {
            System.exit(code);
        }
    }

    static int run(java.io.InputStream in, java.io.PrintStream out, java.io.PrintStream err) {
        try (Scanner scanner = new Scanner(in)) {
            int quantidade = lerQuantidade(scanner, out);
            if (quantidade <= 0) {
                err.println(MSG_ERRO_QUANTIDADE);
                return 1;
            }
            List<Integer> numeros = lerNumeros(scanner, quantidade, out);
            if (numeros == null) {
                err.println(MSG_ERRO_ENTRADA);
                return 1;
            }
            ResultadoContagem resultado = contar(numeros);
            out.println(formatarResultado(resultado));
            return 0;
        }
    }

    private static int lerQuantidade(Scanner scanner, java.io.PrintStream out) {
        out.print("Quantos números deseja inserir? ");
        if (!scanner.hasNextInt()) {
            if (scanner.hasNext()) scanner.next();
            return -1;
        }
        return scanner.nextInt();
    }

    private static List<Integer> lerNumeros(Scanner scanner, int quantidade, java.io.PrintStream out) {
        List<Integer> numeros = new ArrayList<>(quantidade);
        for (int i = 0; i < quantidade; i++) {
            out.print("Digite o " + (i + 1) + "º número: ");
            if (!scanner.hasNextInt()) {
                if (scanner.hasNext()) scanner.next();
                return null;
            }
            numeros.add(scanner.nextInt());
        }
        return numeros;
    }
}
