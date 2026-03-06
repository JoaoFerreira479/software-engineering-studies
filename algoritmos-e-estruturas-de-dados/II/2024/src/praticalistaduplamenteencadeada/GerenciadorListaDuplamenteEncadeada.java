package praticalistaduplamenteencadeada;

import aeds2.dominio.LinhaMedalhista;
import aeds2.dominio.Medalhista;
import aeds2.estruturas.ListaDuplamenteEncadeada;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class GerenciadorListaDuplamenteEncadeada {

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            System.err.println("Arquivo nao encontrado: " + csv);
            return;
        }
        ListaDuplamenteEncadeada<Medalhista> lista = carregarLista(csv);
        if (lista == null) return;
        lista.paraCada(System.out::println);
    }

    private static ListaDuplamenteEncadeada<Medalhista> carregarLista(Path csv) {
        ListaDuplamenteEncadeada<Medalhista> lista = new ListaDuplamenteEncadeada<>();
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                try {
                    LinhaMedalhista lm = LinhaMedalhista.parse(linha);
                    lista.inserirFinal(lm.toMedalhista());
                } catch (IllegalArgumentException ignored) {
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler CSV: " + e.getMessage());
            return null;
        }
        return lista;
    }
}
