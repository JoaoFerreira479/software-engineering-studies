package programarcomputadoresrepeticao;

import java.util.Map;

public final class Autenticador {

    private final Map<String, String> usuarios;

    public Autenticador() {
        this.usuarios = new java.util.HashMap<>();
        usuarios.put("usuario1", "senha123");
        usuarios.put("admin", "admin123");
        usuarios.put("teste", "teste123");
    }

    public boolean validarCredenciais(final String nome, final String senha) {
        if (nome == null || senha == null) return false;
        return senha.equals(usuarios.get(nome));
    }
}
