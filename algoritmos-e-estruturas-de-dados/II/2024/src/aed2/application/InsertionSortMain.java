package aed2.application;

import aed2.domain.model.MedalhistaComContagem;
import aed2.domain.ordenacao.InsertionSort;
import aed2.infrastructure.io.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public final class InsertionSortMain {

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            Console.erro("Arquivo nao encontrado ou nao legivel: " + csv);
            return;
        }
        var porNome = OrdenacaoMainHelper.carregarMedalhistas(csv);
        if (porNome == null) {
            Console.erro("Erro ao ler CSV");
            return;
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(in.readLine());
            MedalhistaComContagem[] aOrdenar = new MedalhistaComContagem[n];
            for (int i = 0; i < n; i++) {
                String nomeBusca = in.readLine();
                MedalhistaComContagem m = porNome.get(nomeBusca);
                if (m == null) {
                    Console.erro("Medalhista nao encontrado: " + nomeBusca);
                    return;
                }
                aOrdenar[i] = m;
            }

            InsertionSort<MedalhistaComContagem> sort = new InsertionSort<>(aOrdenar);
            sort.setComparador(OrdenacaoMainHelper.comparadorMedalhas());
            sort.ordenar();

            for (MedalhistaComContagem m : aOrdenar) {
                Console.print(m.toString());
            }
        } catch (IOException e) {
            Console.erro("Erro de I/O: " + e.getMessage());
        } catch (NumberFormatException e) {
            Console.erro("Entrada invalida (esperado numero): " + e.getMessage());
        }
    }
}
