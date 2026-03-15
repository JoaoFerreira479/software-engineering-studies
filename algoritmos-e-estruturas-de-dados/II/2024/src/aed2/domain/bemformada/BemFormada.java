package aed2.domain.bemformada;

import aed2.domain.estruturas.Pilha;

public final class BemFormada {

    private BemFormada() {
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
