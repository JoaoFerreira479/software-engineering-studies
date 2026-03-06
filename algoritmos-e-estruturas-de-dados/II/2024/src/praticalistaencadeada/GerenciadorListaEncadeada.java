package praticalistaencadeada;

import aeds2.dominio.LinhaMedalhista;
import aeds2.dominio.Medalhista;
import aeds2.estruturas.Conjunto;
import aeds2.estruturas.ListaEncadeada;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class GerenciadorListaEncadeada {

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            System.err.println("Arquivo nao encontrado: " + csv);
            return;
        }
        ListaEncadeada<Medalhista> lista = carregarLista(csv);
        if (lista == null) return;

        System.out.println("LISTA DE MEDALHISTAS SEM REPETICAO");
        Conjunto<Medalhista> vistos = new Conjunto<>();
        lista.paraCada(m -> {
            if (vistos.adicionar(m)) {
                System.out.println(m);
            }
        });

        System.out.println("LISTA INVERTIDA DE MEDALHISTAS");
        lista.paraCadaReverso(System.out::println);
    }

    private static ListaEncadeada<Medalhista> carregarLista(Path csv) {
        ListaEncadeada<Medalhista> lista = new ListaEncadeada<>();
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
