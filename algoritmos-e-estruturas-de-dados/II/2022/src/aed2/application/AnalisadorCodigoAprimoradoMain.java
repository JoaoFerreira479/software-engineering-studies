package aed2.application;

import aed2.infrastructure.io.Console;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AnalisadorCodigoAprimoradoMain {

    private static final String COMANDO_FIM = "FIM";

    private AnalisadorCodigoAprimoradoMain() {}

    public static void main(String[] args) {
        Console.println("=== Sistema de Análise de Códigos Java ===\n");
        Console.println(
            "Cole o código Java que deseja analisar e pressione ENTER duas vezes ou escreva FIM para finalizar a entrada:\n");

        try (Scanner scanner = new Scanner(System.in)) {
            String codigoEntrada = capturarCodigo(scanner);
            if (codigoEntrada == null || codigoEntrada.isEmpty()) {
                Console.println("Nenhum código fornecido. Encerrando o programa.");
                return;
            }

            Console.println("\n=== Análise do Código ===\n");
            Console.println("Entrada original:\n" + codigoEntrada);

            analisarCodigo(codigoEntrada);

            Console.println("\nAnálise completa do trecho de código fornecido.");
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
        return codigo.toString().trim();
    }

    private static void analisarCodigo(String codigo) {
        int nivelLoop = contarLoops(codigo);

        if (nivelLoop == 1) {
            analisarLoopSimples(codigo);
        } else if (nivelLoop > 1) {
            analisarLoopsAninhados(codigo, nivelLoop);
        } else {
            Console.println("Nenhuma estrutura de loop ou condicional identificada.");
        }
    }

    private static void analisarLoopSimples(String codigo) {
        Console.println("Tipo de Estrutura:\n- Loop Simples\n");

        Pattern pattern = Pattern.compile("for\\s*\\((.*?)\\)\\s*(\\n|\\{)?", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(codigo);

        if (matcher.find()) {
            String cabecalho = matcher.group(1);
            String expressao = extrairExpressaoDoLoop(codigo);

            Console.println("Detalhes do Loop:");
            Console.println("- Cabeçalho: " + cabecalho);
            Console.println("- Operação detectada: " + (expressao.isEmpty() ? "Não identificada" : expressao));
            calcularSomatorio(expressao);
        }
    }

    private static void analisarLoopsAninhados(String codigo, int niveis) {
        Console.println("Tipo de Estrutura:\n- Loops Aninhados\n");

        Pattern pattern = Pattern.compile("for\\s*\\((.*?)\\)\\s*(\\n|\\{)?", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(codigo);

        int nivel = 1;
        while (matcher.find()) {
            String cabecalho = matcher.group(1);
            Console.println("Detalhes do Loop Nível " + nivel + ":");
            Console.println("- Cabeçalho: " + cabecalho);
            nivel++;
        }

        String expressao = extrairExpressaoDoLoop(codigo);
        Console.println("- Operação detectada: " + (expressao.isEmpty() ? "Não identificada" : expressao));
        calcularSomatorioAninhado(expressao, niveis);
    }

    private static int contarLoops(String codigo) {
        if (codigo == null || codigo.isEmpty()) return 0;
        Pattern pattern = Pattern.compile("for\\s*\\(");
        Matcher matcher = pattern.matcher(codigo);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }

    private static String extrairExpressaoDoLoop(String codigo) {
        Pattern patternComChaves = Pattern.compile("\\{\\s*([^}]*)\\s*\\}", Pattern.DOTALL);
        Matcher matcherComChaves = patternComChaves.matcher(codigo);
        if (matcherComChaves.find()) {
            String corpo = matcherComChaves.group(1);
            return extrairOperacao(corpo);
        }

        Pattern patternSemChaves = Pattern.compile("for\\s*\\(.*?\\)\\s*([^\\{;]+);", Pattern.DOTALL);
        Matcher matcherSemChaves = patternSemChaves.matcher(codigo);
        if (matcherSemChaves.find()) {
            return matcherSemChaves.group(1).trim();
        }

        return "";
    }

    private static String extrairOperacao(String codigo) {
        Pattern operacaoPattern = Pattern.compile("soma\\s*[+=].*?;");
        Matcher operacaoMatcher = operacaoPattern.matcher(codigo);
        return operacaoMatcher.find() ? operacaoMatcher.group(0).trim() : "";
    }

    private static void calcularSomatorio(String expressao) {
        if (expressao.contains("i * i")) {
            Console.println("Este código calcula a soma de uma progressão quadrática.");
            Console.println("Fórmula Fechada: Σ(i^2) = n(n+1)(2n+1)/6");
            Console.println("Propriedade: Soma Quadrática");
            Console.println("Complexidade: O(n)");
            provarPorInducao("n(n+1)(2n+1)/6");
        } else if (!expressao.isEmpty()) {
            Console.println("Este código calcula a soma de uma progressão aritmética.");
            Console.println("Fórmula Fechada: Σ(i) = n(n+1)/2");
            Console.println("Propriedade: Soma Aritmética");
            Console.println("Complexidade: O(n)");
            provarPorInducao("n(n+1)/2");
        } else {
            Console.println("Nenhuma fórmula matemática detectada.");
        }
    }

    private static void calcularSomatorioAninhado(String expressao, int niveis) {
        Console.println("Análise de somatórios e complexidade para " + niveis + " níveis de loops.");
        Console.println("Operação detectada: " + (expressao.isEmpty() ? "Não identificada" : expressao));
        if (!expressao.isEmpty()) {
            Console.println("Fórmula Fechada: derivada com base nos níveis e expressões");
            Console.println("Propriedade: Soma com Loops Aninhados");
            Console.println("Complexidade: O(n^" + niveis + ")");
        }
    }

    private static void provarPorInducao(String formula) {
        Console.println("Prova por indução para a fórmula: " + formula);
        Console.println("Base: Verifique para n = 1.");
        Console.println("Hipótese: Suponha válida para n = k.");
        Console.println("Passo Indutivo: Mostre que é válida para n = k+1.");
        Console.println("Conclusão: A fórmula é verdadeira para qualquer valor de n.");
    }
}
