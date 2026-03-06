package praticaabb1;

import aeds2.dominio.LinhaMedalhista;
import aeds2.dominio.Medalhista;
import aeds2.estruturas.ABB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ABBTamanho {

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            System.err.println("Arquivo nao encontrado: " + csv);
            return;
        }
        ABB<Medalhista> abb = carregarABB(csv);
        if (abb == null) return;
        processarConsultas(abb);
    }

    private static ABB<Medalhista> carregarABB(Path csv) {
        ABB<Medalhista> abb = new ABB<>();
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                try {
                    LinhaMedalhista lm = LinhaMedalhista.parse(linha);
                    if (!abb.contem(lm.toMedalhista())) {
                        abb.adicionar(lm.toMedalhista());
                    }
                } catch (IllegalArgumentException ignored) {
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler CSV: " + e.getMessage());
            return null;
        }
        return abb;
    }

    private static void processarConsultas(ABB<Medalhista> abb) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String nome;
            while ((nome = in.readLine()) != null && !nome.trim().equals("FIM")) {
                Medalhista chave = new Medalhista(nome.trim(), "", java.time.LocalDate.EPOCH, "");
                if (abb.contem(chave)) {
                    Medalhista m = abb.pesquisar(chave);
                    System.out.println(m);
                    System.out.println("Tamanho: " + tamanho(abb));
                } else {
                    System.out.println("Atleta nao encontrado");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler entrada: " + e.getMessage());
        }
    }

    private static int tamanho(ABB<Medalhista> abb) {
        final int[] n = {0};
        abb.emOrdem(e -> n[0]++);
        return n[0];
    }
}
