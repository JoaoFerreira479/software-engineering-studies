package trabalhopraticometodosordenacao1;

import trabalhopratico.estruturas.Mapa;
import trabalhopratico.ordenacao.IOrdenator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class BubbleSort<T> implements IOrdenator<T> {

    private static final DateTimeFormatter FMT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String ARQUIVO_LOG = "matricula_bubblesort.txt";
    private static final String MATRICULA = "1234567";

    private final T[] array;
    private Comparator<T> comparador;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    public BubbleSort(T[] array) {
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
        int n = array.length;
        boolean trocado;
        do {
            trocado = false;
            for (int i = 0; i < n - 1; i++) {
                comparacoes++;
                if (comparador.compare(array[i], array[i + 1]) > 0) {
                    trocar(i, i + 1);
                    movimentacoes++;
                    trocado = true;
                }
            }
            n--;
        } while (trocado);
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return array;
    }

    private void trocar(int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
        Mapa<String, Medalhista> porNome = carregarMedalhistas(csv);
        if (porNome == null) return;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(in.readLine());
            Medalhista[] aOrdenar = new Medalhista[n];
            for (int i = 0; i < n; i++) {
                String nomeBusca = in.readLine();
                Medalhista m = porNome.get(nomeBusca);
                if (m == null) {
                    System.err.println("Medalhista nao encontrado: " + nomeBusca);
                    return;
                }
                aOrdenar[i] = m;
            }

            BubbleSort<Medalhista> bubbleSort = new BubbleSort<>(aOrdenar);
            bubbleSort.setComparador(comparadorMedalhas());
            bubbleSort.ordenar();

            for (Medalhista m : aOrdenar) {
                System.out.print(m);
            }

            escreverLog(ARQUIVO_LOG, MATRICULA, bubbleSort);
        } catch (IOException e) {
            System.err.println("Erro de I/O: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada invalida (esperado numero): " + e.getMessage());
        }
    }

    private static Mapa<String, Medalhista> carregarMedalhistas(Path csv) {
        Mapa<String, Medalhista> porNome = new Mapa<>();
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                String[] p = linha.split(",", -1);
                if (p.length < 7) continue;
                String nome = p[0].trim();
                String genero = p[1].trim();
                LocalDate nascimento = parseData(p[2].trim());
                String pais = p[3].trim();
                int ouro = parseInt(p[4].trim(), 0);
                int prata = parseInt(p[5].trim(), 0);
                int bronze = parseInt(p[6].trim(), 0);
                porNome.inserir(nome, new Medalhista(nome, genero, nascimento, pais, ouro, prata, bronze));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler CSV: " + e.getMessage());
            return null;
        }
        return porNome;
    }

    private static Comparator<Medalhista> comparadorMedalhas() {
        return (m1, m2) -> {
            if (m1.getOuro() != m2.getOuro()) return Integer.compare(m2.getOuro(), m1.getOuro());
            if (m1.getPrata() != m2.getPrata()) return Integer.compare(m2.getPrata(), m1.getPrata());
            if (m1.getBronze() != m2.getBronze()) return Integer.compare(m2.getBronze(), m1.getBronze());
            return m1.getNome().compareToIgnoreCase(m2.getNome());
        };
    }

    private static void escreverLog(String arquivo, String matricula, BubbleSort<Medalhista> sort) throws IOException {
        try (BufferedWriter log = Files.newBufferedWriter(Path.of(arquivo))) {
            log.write(matricula + "\t" + sort.getTempoOrdenacao() + "\t" + sort.getComparacoes() + "\t" + sort.getMovimentacoes());
        }
    }

    private static LocalDate parseData(String s) {
        if (s == null || s.isEmpty()) return LocalDate.EPOCH;
        try {
            return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            return LocalDate.EPOCH;
        }
    }

    private static int parseInt(String s, int padrao) {
        if (s == null || s.isEmpty()) return padrao;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return padrao;
        }
    }

    static class Medalhista {
        private final String nome;
        private final String genero;
        private final LocalDate nascimento;
        private final String pais;
        private final int ouro;
        private final int prata;
        private final int bronze;

        Medalhista(String nome, String genero, LocalDate nascimento, String pais, int ouro, int prata, int bronze) {
            this.nome = nome;
            this.genero = genero;
            this.nascimento = nascimento != null ? nascimento : LocalDate.EPOCH;
            this.pais = pais;
            this.ouro = ouro;
            this.prata = prata;
            this.bronze = bronze;
        }

        String getNome() { return nome; }
        int getOuro() { return ouro; }
        int getPrata() { return prata; }
        int getBronze() { return bronze; }

        @Override
        public String toString() {
            return nome + ", " + genero + ". Nascimento: " + nascimento.format(FMT_DATA) + ". Pais: " + pais
                    + "\nQuantidade de medalhas de ouro: " + ouro
                    + (prata > 0 ? "\nQuantidade de medalhas de prata: " + prata : "")
                    + (bronze > 0 ? "\nQuantidade de medalhas de bronze: " + bronze : "") + "\n";
        }
    }
}
