package lista2treino;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GeradorDeSomatoriosNovo {

    public static final String REGEX_INTEIROS = "\\d+(\\+\\d+)+";
    public static final String REGEX_FRACOES = "\\d+/\\d+(\\+\\d+/\\d+)+";
    public static final String REGEX_POTENCIAS = "\\d+\\^\\d+(\\+\\d+\\^\\d+)+";

    static final String COMANDO_FIM = "FIM";

    private GeradorDeSomatoriosNovo() {}

    public static void main(String[] args) {
        SistemaSomatorio sistema = new SistemaSomatorio();
        sistema.iniciar();
    }

    static int[] parseInteiros(String[] partes) {
        if (partes == null) throw new IllegalArgumentException("partes nulo");
        int[] out = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            out[i] = Integer.parseInt(partes[i].trim());
        }
        return out;
    }

    static int somaInteiros(int[] valores) {
        if (valores == null) return 0;
        int s = 0;
        for (int v : valores) s += v;
        return s;
    }
}

final class SistemaSomatorio {

    public void iniciar() {
        System.out.println("=== Sistema de Análise e Gerador de Somatórios ===\n");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Digite o somatório para analisar ou 'FIM' para sair:\n");
                String entrada = scanner.nextLine();
                if (entrada == null) entrada = "";
                entrada = entrada.trim();

                if (entrada.equalsIgnoreCase(GeradorDeSomatoriosNovo.COMANDO_FIM)) {
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                }

                if (entrada.isEmpty()) {
                    continue;
                }

                try {
                    validarEntrada(entrada);
                    analisarSomatorio(entrada);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }
            }
        }
    }

    private void validarEntrada(String entrada) {
        Objects.requireNonNull(entrada);
        if (!entrada.matches("[\\d+\\-/^\\s()]+")) {
            throw new IllegalArgumentException("Expressão contém caracteres inválidos.");
        }
    }

    private void analisarSomatorio(String entrada) {
        String normalizada = entrada.replaceAll("\\s+", "");
        normalizada = avaliarExpressoes(normalizada);

        if (normalizada.matches(GeradorDeSomatoriosNovo.REGEX_INTEIROS)) {
            analisarInteiros(normalizada);
        } else if (normalizada.matches(GeradorDeSomatoriosNovo.REGEX_FRACOES)) {
            analisarFracoes(normalizada);
        } else if (normalizada.matches(GeradorDeSomatoriosNovo.REGEX_POTENCIAS)) {
            analisarPotencias(normalizada);
        } else {
            throw new IllegalArgumentException("Expressão desconhecida ou formato não suportado.");
        }
    }

    private void analisarInteiros(String entrada) {
        String[] partes = entrada.split("\\+");
        int[] valores = GeradorDeSomatoriosNovo.parseInteiros(partes);

        boolean isPA = verificarPA(valores);
        boolean isPG = verificarPG(valores);

        if (isPA) {
            int razao = valores[1] - valores[0];
            String representacao = "∑ (" + razao + "i + " + (valores[0] - razao) + ") (i de 1 até " + valores.length + ")";
            System.out.println("Representação do somatório: " + representacao);
            System.out.println("Propriedade: Progressão Aritmética (PA)");
        } else if (isPG) {
            System.out.println("Representação do somatório: ∑ (a * r^i) (i de 0 até " + (valores.length - 1) + ")");
            System.out.println("Propriedade: Progressão Geométrica (PG)");
        } else {
            System.out.println("Representação do somatório: Não segue PA ou PG");
            System.out.println("Propriedade: Nenhuma");
        }

        int soma = GeradorDeSomatoriosNovo.somaInteiros(valores);
        exibirResultados(entrada, soma, "inteiros", soma + "/1");
    }

    private void analisarFracoes(String entrada) {
        String[] fracoes = entrada.split("\\+");
        int numeradorTotal = 0;
        int denominadorComum = 1;

        for (String fracao : fracoes) {
            String[] partes = fracao.split("/");
            if (partes.length != 2) {
                throw new IllegalArgumentException("Fração inválida: " + fracao);
            }
            int numerador = Integer.parseInt(partes[0].trim());
            int denominador = Integer.parseInt(partes[1].trim());
            if (denominador == 0) {
                throw new ArithmeticException("Denominador zero em: " + fracao);
            }
            int antigoComum = denominadorComum;
            denominadorComum *= denominador;
            numeradorTotal = numeradorTotal * denominador + numerador * antigoComum;
        }

        int gcd = mdc(numeradorTotal, denominadorComum);
        numeradorTotal /= gcd;
        denominadorComum /= gcd;

        double decimal = (double) numeradorTotal / denominadorComum;
        exibirResultados(entrada, decimal, "frações", numeradorTotal + "/" + denominadorComum);
    }

    private void analisarPotencias(String entrada) {
        String[] potencias = entrada.split("\\+");
        int soma = 0;
        int base = -1;
        int expoenteInicial = -1;
        int expoenteFinal = -1;

        for (int i = 0; i < potencias.length; i++) {
            String[] partes = potencias[i].split("\\^");
            if (partes.length != 2) {
                throw new IllegalArgumentException("Potência inválida: " + potencias[i]);
            }
            int currentBase = Integer.parseInt(partes[0].trim());
            int currentExpoente = Integer.parseInt(partes[1].trim());

            if (i == 0) {
                base = currentBase;
                expoenteInicial = currentExpoente;
            }
            expoenteFinal = currentExpoente;

            soma += (int) Math.pow(currentBase, currentExpoente);
        }

        String representacao = "∑ (" + base + "^i) (i de " + expoenteInicial + " até " + expoenteFinal + ")";
        System.out.println("Representação do somatório: " + representacao);
        System.out.println("Propriedade: Progressão Geométrica (PG)");
        exibirResultados(entrada, soma, "potências", soma + "/1");
    }

    private String avaliarExpressoes(String entrada) {
        Pattern pattern = Pattern.compile("\\((\\d+)\\+(\\d+)\\)");
        Matcher matcher = pattern.matcher(entrada);
        StringBuffer resultado = new StringBuffer();

        while (matcher.find()) {
            int a = Integer.parseInt(matcher.group(1));
            int b = Integer.parseInt(matcher.group(2));
            matcher.appendReplacement(resultado, String.valueOf(a + b));
        }
        matcher.appendTail(resultado);
        return resultado.toString();
    }

    private boolean verificarPA(int[] valores) {
        if (valores == null || valores.length < 2) return false;
        int diferenca = valores[1] - valores[0];
        for (int i = 2; i < valores.length; i++) {
            if (valores[i] - valores[i - 1] != diferenca) return false;
        }
        return true;
    }

    private boolean verificarPG(int[] valores) {
        if (valores == null || valores.length < 2 || valores[0] == 0) return false;
        int razao = valores[1] / valores[0];
        for (int i = 2; i < valores.length; i++) {
            if (valores[i - 1] == 0 || valores[i] / valores[i - 1] != razao) return false;
        }
        return true;
    }

    private int mdc(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        return b == 0 ? a : mdc(b, a % b);
    }

    private void exibirResultados(String entrada, Object somaDecimal, String tipo, String somaFracao) {
        System.out.println("\nSomatório analisado: " + entrada);
        System.out.println("Tipo: " + tipo);
        System.out.println("Soma calculada (decimal): " + somaDecimal);
        System.out.println("Soma calculada (fração): " + somaFracao + "\n");
    }
}
