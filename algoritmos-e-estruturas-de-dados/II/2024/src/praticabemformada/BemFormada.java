package praticabemformada;

import aeds2.estruturas.Pilha;

import java.util.Scanner;

public final class BemFormada {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String expressao = scanner.nextLine().trim();
                if (expressao.equals("FIM")) break;
                System.out.println(verificarBemFormada(expressao) ? "correto" : "incorreto");
            }
        }
    }

    public static boolean verificarBemFormada(String expressao) {
        if (expressao == null) return true;
        Pilha<Character> pilha = new Pilha<>();
        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            if (c == '(' || c == '[') {
                pilha.empilhar(c);
            } else if (c == ')' || c == ']') {
                if (pilha.vazia()) return false;
                char topo = pilha.desempilhar();
                if ((c == ')' && topo != '(') || (c == ']' && topo != '[')) {
                    return false;
                }
            }
        }
        return pilha.vazia();
    }
}
