package aed2.application;

import aed2.domain.estruturas.ListaDuplamenteEncadeada;
import aed2.domain.model.LinhaMedalhista;
import aed2.domain.model.Medalhista;
import aed2.infrastructure.io.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class GerenciadorListaDuplamenteEncadeadaMain {

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            Console.erro("Arquivo nao encontrado: " + csv);
            return;
        }
        ListaDuplamenteEncadeada<Medalhista> lista = carregarLista(csv);
        if (lista == null) return;
        lista.paraCada(m -> Console.println(m.toString()));
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
            Console.erro("Erro ao ler CSV: " + e.getMessage());
            return null;
        }
        return lista;
    }
}
