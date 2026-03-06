package lista1treino;

import java.util.Objects;
import java.util.Scanner;

public final class ContadorDeOperacoesDinamicoNovo {

    private static final String COMANDO_FIM = "FIM";

    private ContadorDeOperacoesDinamicoNovo() {}

    public static void main(String[] args) {
        System.out.println("=== Sistema de Análise de Operações em Códigos Java ===\n");
        System.out.println(
            "Cole o código Java que deseja analisar e pressione ENTER duas vezes ou escreva FIM para finalizar a entrada:\n");

        try (Scanner scanner = new Scanner(System.in)) {
            String codigoBruto = capturarCodigo(scanner);
            if (codigoBruto == null || codigoBruto.isBlank()) {
                System.out.println("Nenhum código foi fornecido. Finalizando o programa.");
                return;
            }

            String codigoLimpo = removerNumerosDeLinha(codigoBruto);
            if (!validarCodigoJava(codigoLimpo)) {
                System.out.println("Erro: O código fornecido não é reconhecido como válido para a linguagem Java.");
                return;
            }

            CatalogoOperacoes catalogo = new CatalogoOperacoes();
            AnalisadorOperacoes analisador = new AnalisadorOperacoes(catalogo);
            analisador.analisar(codigoLimpo);
        } catch (Exception e) {
            System.err.println("Erro ao processar o código: " + e.getMessage());
        }
    }

    static String capturarCodigo(Scanner scanner) {
        Objects.requireNonNull(scanner, "scanner");
        StringBuilder codigo = new StringBuilder();
        boolean linhaVaziaAnterior = false;

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.trim().equalsIgnoreCase(COMANDO_FIM)) {
                break;
            }
            if (linha.trim().isEmpty()) {
                if (linhaVaziaAnterior) {
                    break;
                }
                linhaVaziaAnterior = true;
            } else {
                linhaVaziaAnterior = false;
            }
            codigo.append(linha).append("\n");
        }
        return codigo.toString();
    }

    static String removerNumerosDeLinha(String codigo) {
        if (codigo == null) return "";
        StringBuilder sb = new StringBuilder();
        int inicio = 0;
        int len = codigo.length();
        while (inicio < len) {
            int fim = proximaQuebra(codigo, inicio);
            String linha = codigo.substring(inicio, fim);
            String semNumero = linha.replaceFirst("^\\s*\\d+\\s+", "");
            sb.append(semNumero).append("\n");
            inicio = fim >= len ? len : fim + 1;
        }
        return sb.toString();
    }

    private static int proximaQuebra(String s, int from) {
        for (int i = from; i < s.length(); i++) {
            if (s.charAt(i) == '\n') return i;
        }
        return s.length();
    }

    static boolean validarCodigoJava(String codigo) {
        if (codigo == null || codigo.isEmpty()) return false;
        String[] padroes = {
            "class\\s+\\w+",
            "public\\s+(static\\s+)?void\\s+main\\s*\\(",
            "System\\.out\\.println\\(.*\\)\\s*;"
        };
        for (String padrao : padroes) {
            if (codigo.matches("(?s).*" + padrao + ".*")) {
                return true;
            }
        }
        return false;
    }

    static int contarOcorrencias(String texto, String regex) {
        if (texto == null || texto.isEmpty()) return 0;
        if (regex == null || regex.isEmpty()) return 0;
        String[] partes = texto.split(regex);
        return Math.max(0, partes.length - 1);
    }
}

final class AnalisadorOperacoes {

    private final CatalogoOperacoes catalogo;

    AnalisadorOperacoes(CatalogoOperacoes catalogo) {
        this.catalogo = Objects.requireNonNull(catalogo, "catalogo");
    }

    void analisar(String codigo) {
        System.out.println("\n=== Resultado da Análise ===");
        catalogo.paraCada((padrao, descricao) -> {
            int count = ContadorDeOperacoesDinamicoNovo.contarOcorrencias(codigo, padrao);
            System.out.printf("%s: %d%n", descricao, count);
        });
    }
}
