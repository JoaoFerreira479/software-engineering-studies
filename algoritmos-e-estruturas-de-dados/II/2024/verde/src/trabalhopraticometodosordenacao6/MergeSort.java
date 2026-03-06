package trabalhopraticometodosordenacao6;

import trabalhopratico.estruturas.Mapa;
import trabalhopratico.ordenacao.IOrdenator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class MergeSort<T> implements IOrdenator<T> {

    private static final String ARQUIVO_LOG = "matricula_mergesort.txt";
    private static final String MATRICULA = "1234567";

    private final T[] array;
    private Comparator<T> comparador;
    private int comparacoes;
    private int movimentacoes;
    private double tempoOrdenacao;

    public MergeSort(T[] array) {
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
        mergesort(0, array.length - 1);
        tempoOrdenacao = System.currentTimeMillis() - inicio;
        return array;
    }

    private void mergesort(int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesort(esq, meio);
            mergesort(meio + 1, dir);
            merge(esq, meio, dir);
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(int esq, int meio, int dir) {
        int n1 = meio - esq + 1;
        int n2 = dir - meio;
        T[] esquerda = (T[]) Array.newInstance(array.getClass().getComponentType(), n1);
        T[] direita = (T[]) Array.newInstance(array.getClass().getComponentType(), n2);
        for (int i = 0; i < n1; i++) esquerda[i] = array[esq + i];
        for (int j = 0; j < n2; j++) direita[j] = array[meio + 1 + j];
        int i = 0, j = 0, k = esq;
        while (i < n1 && j < n2) {
            comparacoes++;
            if (comparador.compare(esquerda[i], direita[j]) <= 0) {
                array[k++] = esquerda[i++];
            } else {
                array[k++] = direita[j++];
            }
            movimentacoes++;
        }
        while (i < n1) array[k++] = esquerda[i++];
        while (j < n2) array[k++] = direita[j++];
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
        Mapa<String, Evento> eventos = carregarEventos(csv);
        if (eventos == null) return;

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(in.readLine());
            Evento[] aOrdenar = new Evento[n];
            for (int i = 0; i < n; i++) {
                String chave = in.readLine();
                Evento e = eventos.get(chave);
                if (e == null) {
                    System.err.println("Evento nao encontrado: " + chave);
                    return;
                }
                aOrdenar[i] = e;
            }

            MergeSort<Evento> mergeSort = new MergeSort<>(aOrdenar);
            mergeSort.setComparador(Comparator.comparing(Evento::getEsporte).thenComparing(Evento::getNome));
            mergeSort.ordenar();

            for (Evento e : aOrdenar) {
                System.out.println(e + "\n");
            }

            try (BufferedWriter log = Files.newBufferedWriter(Path.of(ARQUIVO_LOG))) {
                log.write(MATRICULA + "\t" + mergeSort.getTempoOrdenacao() + "\t" + mergeSort.getComparacoes() + "\t" + mergeSort.getMovimentacoes());
            }
        } catch (IOException e) {
            System.err.println("Erro de I/O: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Entrada invalida: " + e.getMessage());
        }
    }

    private static Mapa<String, Evento> carregarEventos(Path csv) {
        Mapa<String, Evento> eventos = new Mapa<>();
        DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
        try (BufferedReader br = Files.newBufferedReader(csv)) {
            br.readLine();
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.isBlank()) continue;
                String[] p = linha.split(",", -1);
                if (p.length < 8) continue;
                String esporte = p[6].trim();
                String eventoNome = p[7].trim();
                String chave = esporte + " - " + eventoNome;
                Evento e = eventos.putIfAbsent(chave, new Evento(esporte, eventoNome));
                Medalhista m = new Medalhista(p[0].trim(), p[1].trim(), parseData(p[2].trim(), fmt), p[3].trim());
                e.adicionarMedalhista(m);
            }
        } catch (IOException ex) {
            System.err.println("Erro ao ler CSV: " + ex.getMessage());
            return null;
        }
        return eventos;
    }

    private static LocalDate parseData(String s, DateTimeFormatter fmt) {
        if (s == null || s.isEmpty()) return LocalDate.EPOCH;
        try {
            return LocalDate.parse(s, fmt);
        } catch (Exception e) {
            return LocalDate.EPOCH;
        }
    }

    static class Evento {
        private final String esporte;
        private final String nome;
        private final Medalhista[] medalhistas;
        private int totalMedalhistas;

        Evento(String esporte, String nome) {
            this.esporte = Objects.requireNonNull(esporte, "esporte");
            this.nome = Objects.requireNonNull(nome, "nome");
            this.medalhistas = new Medalhista[50];
            this.totalMedalhistas = 0;
        }

        void adicionarMedalhista(Medalhista m) {
            if (totalMedalhistas < medalhistas.length) {
                medalhistas[totalMedalhistas++] = m;
            }
        }

        String getEsporte() { return esporte; }
        String getNome() { return nome; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(esporte).append(" - ").append(nome).append("\n");
            for (int i = 0; i < totalMedalhistas; i++) {
                sb.append(medalhistas[i]).append("\n");
            }
            return sb.toString();
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

        @Override
        public String toString() {
            return nome + ", " + genero + ". Nascimento: " + nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ". Pais: " + pais;
        }
    }
}
