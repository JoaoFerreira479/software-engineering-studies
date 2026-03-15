package aed2.domain.recursao;

import java.util.Objects;

public final class Palindromo {

    private Palindromo() {
    }

    public static boolean ehPalindromo(String s) {
        Objects.requireNonNull(s, "s");
        return ehPalindromoRec(s, 0, s.length() - 1);
    }

    private static boolean ehPalindromoRec(String s, int inicio, int fim) {
        if (inicio >= fim) return true;
        if (s.charAt(inicio) != s.charAt(fim)) return false;
        return ehPalindromoRec(s, inicio + 1, fim - 1);
    }
}
