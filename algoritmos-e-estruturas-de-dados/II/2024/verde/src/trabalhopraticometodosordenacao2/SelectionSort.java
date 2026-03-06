package trabalhopraticometodosordenacao2;

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

public class SelectionSort<T> implements IOrdenator<T> {

    private static final DateTimeFormatter FMT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String ARQUIVO_LOG = "matricula_selecao.txt";
    private static final String MATRICULA = "1234567";

    private final T[] array;
    private Comparator<T> comparador;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    public SelectionSort(T[] array) {
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
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparacoes++;
                if (comparador.compare(array[j], array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                trocar(i, minIndex);
                movimentacoes++;
            }
        }
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

            SelectionSort<Medalhista> selectionSort = new SelectionSort<>(aOrdenar);
            selectionSort.setComparador((m1, m2) -> {
                if (!m1.getNascimento().equals(m2.getNascimento())) {
                    return m1.getNascimento().compareTo(m2.getNascimento());
                }
                return m1.getNome().compareToIgnoreCase(m2.getNome());
            });
            selectionSort.ordenar();

            for (Medalhista m : aOrdenar) {
                System.out.print(m);
            }

            try (BufferedWriter log = Files.newBufferedWriter(Path.of(ARQUIVO_LOG))) {
                log.write(MATRICULA + "\t" + selectionSort.getTempoOrdenacao() + "\t" + selectionSort.getComparacoes() + "\t" + selectionSort.getMovimentacoes());
            }
        } catch (IOException e) {
            System.err.println("Erro de I/O: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada invalida: " + e.getMessage());
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
                if (p.length < 4) continue;
                String nome = p[0].trim();
                String genero = p[1].trim();
                LocalDate nascimento = parseData(p[2].trim());
                String pais = p[3].trim();
                porNome.inserir(nome, new Medalhista(nome, genero, nascimento, pais));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler CSV: " + e.getMessage());
            return null;
        }
        return porNome;
    }

    private static LocalDate parseData(String s) {
        if (s == null || s.isEmpty()) return LocalDate.EPOCH;
        try {
            return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            return LocalDate.EPOCH;
        }
    }

    static class Medalhista {
        private final String nome;
        private final String genero;
        private final LocalDate nascimento;
        private final String pais;

        Medalhista(String nome, String genero, LocalDate nascimento, String pais) {
            this.nome = nome;
            this.genero = genero;
            this.nascimento = nascimento != null ? nascimento : LocalDate.EPOCH;
            this.pais = pais;
        }

        String getNome() { return nome; }
        LocalDate getNascimento() { return nascimento; }

        @Override
        public String toString() {
            return nome + ", " + genero + ". Nascimento: " + nascimento.format(FMT_DATA) + ". Pais: " + pais + "\n";
        }
    }
}
