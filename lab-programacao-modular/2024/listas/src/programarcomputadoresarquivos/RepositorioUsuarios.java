package programarcomputadoresarquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class RepositorioUsuarios {

    private static final String SEPARADOR = ";";

    private RepositorioUsuarios() {
    }

    /**
     * Carrega o mapa nome -> senha do arquivo. Retorna mapa vazio se o arquivo não existir.
     */
    public static Map<String, String> carregar(final Path caminho) throws IOException {
        final Map<String, String> usuarios = new HashMap<>();
        if (!Files.isRegularFile(caminho)) {
            return usuarios;
        }
        try (BufferedReader reader = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                final String t = linha.trim();
                if (t.isEmpty()) continue;
                final int idx = t.indexOf(SEPARADOR);
                if (idx > 0 && idx < t.length() - 1) {
                    usuarios.put(t.substring(0, idx).trim(), t.substring(idx + 1).trim());
                }
            }
        }
        return usuarios;
    }

    /**
     * Salva o mapa no arquivo (sobrescreve).
     */
    public static void salvar(final Path caminho, final Map<String, String> usuarios) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardCharsets.UTF_8)) {
            for (final Map.Entry<String, String> e : usuarios.entrySet()) {
                writer.write(e.getKey() + SEPARADOR + e.getValue());
                writer.newLine();
            }
        }
    }
}
