package aed2.domain.recursao;

import aed2.domain.estruturas.Celula;
import aed2.domain.estruturas.ListaEncadeada;

public final class AlgebraBooleana {

    private AlgebraBooleana() {
    }

    public static boolean avaliarExpressao(String expr, int[] valores) {
        expr = expr.trim();
        if (expr.startsWith("not(")) {
            return !avaliarExpressao(expr.substring(4, expr.length() - 1), valores);
        }
        if (expr.startsWith("and(")) {
            String[] args = separarArgumentos(expr.substring(4, expr.length() - 1));
            for (String arg : args) {
                if (!avaliarExpressao(arg, valores)) return false;
            }
            return true;
        }
        if (expr.startsWith("or(")) {
            String[] args = separarArgumentos(expr.substring(3, expr.length() - 1));
            for (String arg : args) {
                if (avaliarExpressao(arg, valores)) return true;
            }
            return false;
        }
        char var = expr.charAt(0);
        int index = var - 'A';
        return index >= 0 && index < valores.length && valores[index] == 1;
    }

    public static String[] separarArgumentos(String expr) {
        ListaEncadeada<String> argumentos = new ListaEncadeada<>();
        int nivel = 0;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == ',' && nivel == 0) {
                argumentos.inserirFinal(buffer.toString().trim());
                buffer.setLength(0);
            } else {
                buffer.append(c);
                if (c == '(') nivel++;
                else if (c == ')') nivel--;
            }
        }
        if (buffer.length() > 0) {
            argumentos.inserirFinal(buffer.toString().trim());
        }
        return toArray(argumentos);
    }

    private static String[] toArray(ListaEncadeada<String> lista) {
        Celula<String> cur = lista.getPrimeiro();
        int n = 0;
        while (cur != null) {
            n++;
            cur = cur.getProximo();
        }
        String[] arr = new String[n];
        cur = lista.getPrimeiro();
        for (int i = 0; i < n && cur != null; i++, cur = cur.getProximo()) {
            arr[i] = cur.getItem();
        }
        return arr;
    }
}
