package trabalhopraticometodosordenacao5;

import trabalhopratico.estruturas.Mapa;
import trabalhopratico.ordenacao.IOrdenator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;

public class QuickSort<T> implements IOrdenator<T> {

    private static final String ARQUIVO_LOG = "matricula_quicksort.txt";
    private static final String MATRICULA = "1234567";

    private final T[] array;
    private Comparator<T> comparador;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    public QuickSort(T[] array) {
        this.array = Objects.requireNonNull(array, "array");
        this.comparacoes = 0;
        this.movimentacoes = 0;
        this.tempoOrdenacao = 0;
    }

    @Override
    public T[] ordenar() {
        if (comparador == null) {
            throw new IllegalStateException("Comparador nao definido. Chame setComparador antes de ordenar.");
        }
        long inicio = System.currentTimeMillis();
        quicksort(0, array.length - 1);
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return array;
    }

    private void quicksort(int esq, int dir) {
        if (esq < dir) {
            int pivo = particionar(esq, dir);
            quicksort(esq, pivo - 1);
            quicksort(pivo + 1, dir);
        }
    }

    private int particionar(int esq, int dir) {
        T pivo = array[dir];
        int i = esq - 1;
        for (int j = esq; j < dir; j++) {
            comparacoes++;
            if (comparador.compare(array[j], pivo) > 0) {
                i++;
                trocar(i, j);
            }
        }
        trocar(i + 1, dir);
        return i + 1;
    }

    private void trocar(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        movimentacoes++;
    }

    @Override
    public void setComparador(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    @Override
    public int getComparacoes() {
        return comparacoes;
    }

    @Override
    public int getMovimentacoes() {
        return movimentacoes;
    }

    @Override
    public double getTempoOrdenacao() {
        return tempoOrdenacao;
    }

    public static void main(String[] args) {
        Path csv = Path.of("medallists.csv");
        if (!Files.isReadable(csv)) {
            System.err.println("Arquivo nao encontrado ou nao legivel: " + csv);
            return;
        }
        Mapa<String, Pais> paises = carregarPaises(csv);
        if (paises == null) return;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(in.readLine());
            Pais[] aOrdenar = new Pais[n];
            for (int i = 0; i < n; i++) {
                String nomePais = in.readLine();
                Pais p = paises.get(nomePais);
                if (p == null) {
                    System.err.println("Pais nao encontrado: " + nomePais);
                    return;
                }
                aOrdenar[i] = p;
            }

            QuickSort<Pais> quickSort = new QuickSort<>(aOrdenar);
            quickSort.setComparador((p1, p2) -> {
                if (p1.getOuro() != p2.getOuro()) return Integer.compare(p2.getOuro(), p1.getOuro());
                if (p1.getPrata() != p2.getPrata()) return Integer.compare(p2.getPrata(), p1.getPrata());
                return Integer.compare(p2.getBronze(), p1.getBronze());
            });
            quickSort.ordenar();

            for (Pais p : aOrdenar) {
                System.out.println(p);
            }

            try (BufferedWriter log = Files.newBufferedWriter(Path.of(ARQUIVO_LOG))) {
                log.write(MATRICULA + "\t" + quickSort.getTempoOrdenacao() + "\t" + quickSort.getComparacoes() + "\t" + quickSort.getMovimentacoes());
            }
        } catch (IOException e) {
            System.err.println("Erro de I/O: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada invalida: " + e.getMessage());
        }
    }

    private static Mapa<String, Pais> carregarPaises(Path csv) {
        Mapa<String, Pais> paises = new Mapa<>();
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                String[] p = linha.split(",", -1);
                if (p.length < 5) continue;
                String nomePais = p[3].trim();
                String tipoMedalha = p[4].trim().toLowerCase();
                Pais pais = paises.putIfAbsent(nomePais, new Pais(nomePais));
                pais.adicionarMedalha(tipoMedalha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler CSV: " + e.getMessage());
            return null;
        }
        return paises;
    }

    static class Pais {
        private final String nome;
        private int ouro;
        private int prata;
        private int bronze;

        Pais(String nome) {
            this.nome = Objects.requireNonNull(nome, "nome");
        }

        void adicionarMedalha(String tipo) {
            switch (tipo) {
                case "gold", "ouro" -> ouro++;
                case "silver", "prata" -> prata++;
                case "bronze" -> bronze++;
                default -> { }
            }
        }

        String getNome() { return nome; }
        int getOuro() { return ouro; }
        int getPrata() { return prata; }
        int getBronze() { return bronze; }

        @Override
        public String toString() {
            return getNome() + ": " + String.format("%02d", ouro) + " ouros " + String.format("%02d", prata) + " pratas "
                    + String.format("%02d", bronze) + " bronzes Total: " + (ouro + prata + bronze) + " medalhas.";
        }
    }
}
