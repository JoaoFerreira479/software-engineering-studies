package trabalhopraticometodosordenacao4;

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

public class HeapSort<T> implements IOrdenator<T> {

    private static final DateTimeFormatter FMT_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String ARQUIVO_LOG = "matricula_heapsort.txt";
    private static final String MATRICULA = "1234567";

    private final T[] array;
    private Comparator<T> comparador;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    public HeapSort(T[] array) {
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
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            trocar(0, i);
            movimentacoes++;
            heapify(i, 0);
        }
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return array;
    }

    private void heapify(int n, int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;
        if (esquerda < n) {
            comparacoes++;
            if (comparador.compare(array[esquerda], array[maior]) > 0) {
                maior = esquerda;
            }
        }
        if (direita < n) {
            comparacoes++;
            if (comparador.compare(array[direita], array[maior]) > 0) {
                maior = direita;
            }
        }
        if (maior != i) {
            trocar(i, maior);
            movimentacoes++;
            heapify(n, maior);
        }
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

            HeapSort<Medalhista> heapSort = new HeapSort<>(aOrdenar);
            heapSort.setComparador((m1, m2) -> {
                int cmp = m1.getPais().compareToIgnoreCase(m2.getPais());
                return cmp != 0 ? cmp : m1.getNome().compareToIgnoreCase(m2.getNome());
            });
            heapSort.ordenar();

            for (Medalhista m : aOrdenar) {
                System.out.print(m);
            }

            try (BufferedWriter log = Files.newBufferedWriter(Path.of(ARQUIVO_LOG))) {
                log.write(MATRICULA + "\t" + heapSort.getTempoOrdenacao() + "\t" + heapSort.getComparacoes() + "\t" + heapSort.getMovimentacoes());
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
        String getPais() { return pais; }

        @Override
        public String toString() {
            return nome + ", " + genero + ". Nascimento: " + nascimento.format(FMT_DATA) + ". Pais: " + pais + "\n";
        }
    }
}
