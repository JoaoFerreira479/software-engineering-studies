package aed2.application;

import aed2.domain.lista1.CatalogoOperacoes;
import aed2.infrastructure.io.Console;

import java.util.Objects;
import java.util.Scanner;

public final class ContadorDeOperacoesDinamicoNovoMain {

    private static final String COMANDO_FIM = "FIM";

    private ContadorDeOperacoesDinamicoNovoMain() {}

    public static void main(String[] args) {
        Console.println("=== Sistema de Análise de Operações em Códigos Java ===\n");
        Console.println(
            "Cole o código Java que deseja analisar e pressione ENTER duas vezes ou escreva FIM para finalizar a entrada:\n");

        try (Scanner scanner = new Scanner(System.in)) {
            String codigoBruto = capturarCodigo(scanner);
            if (codigoBruto == null || codigoBruto.isBlank()) {
                Console.println("Nenhum código foi fornecido. Finalizando o programa.");
                return;
            }

            String codigoLimpo = removerNumerosDeLinha(codigoBruto);
            if (!validarCodigoJava(codigoLimpo)) {
                Console.println("Erro: O código fornecido não é reconhecido como válido para a linguagem Java.");
                return;
            }

            CatalogoOperacoes catalogo = new CatalogoOperacoes();
            AnalisadorOperacoes analisador = new AnalisadorOperacoes(catalogo);
            analisador.analisar(codigoLimpo);
        } catch (Exception e) {
            Console.erro("Erro ao processar o código: " + e.getMessage());
        }
    }

    static String capturarCodigo(Scanner scanner) {
        Objects.requireNonNull(scanner, "scanner");
        StringBuilder codigo = new StringBuilder();
        boolean linhaVaziaAnterior = false;

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha != null && linha.trim().equalsIgnoreCase(COMANDO_FIM)) break;
            if (linha != null && linha.trim().isEmpty()) {
                if (linhaVaziaAnterior) break;
                linhaVaziaAnterior = true;
            } else {
                linhaVaziaAnterior = false;
            }
            codigo.append(linha != null ? linha : "").append("\n");
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
        Console.println("\n=== Resultado da Análise ===");
        catalogo.paraCada((padrao, descricao) -> {
            int count = ContadorDeOperacoesDinamicoNovoMain.contarOcorrencias(codigo, padrao);
            Console.printf("%s: %d%n", descricao, count);
        });
    }
}
