package programarcomputadoresarquivos;

public final class CriptografiaSenha {

    private static final int DESLOCAMENTO = 10;

    private CriptografiaSenha() {
    }

    
    public static String criptografar(final String senha) {
        if (senha == null) {
            throw new NullPointerException("Senha não pode ser nula");
        }
        final StringBuilder sb = new StringBuilder(senha.length());
        for (final char c : senha.toCharArray()) {
            sb.append((char) (c + DESLOCAMENTO));
        }
        return sb.toString();
    }
}
