package programarcomputadoresvariaveisdados;

import java.util.Scanner;

public class Losangos {

    private static final int DESLOCAMENTO = 15;
    private static final int TAMANHO = 4;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int linhaTopo = EntradaUtil.lerInteiro(scanner, "Digite a linha do topo do primeiro losango: ");
            int colunaTopo = EntradaUtil.lerInteiro(scanner, "Digite a coluna do topo do primeiro losango: ");

            desenharLosangos(linhaTopo, colunaTopo, DESLOCAMENTO, TAMANHO);
        }
    }

    private static void desenharLosangos(int linhaTopo, int colunaTopo, int deslocamento, int tamanho) {
        for (int l = 1; l < linhaTopo; l++) {
            System.out.println();
        }
        for (int i = -tamanho; i <= tamanho; i++) {
            int largura = calcularLargura(tamanho, i);
            desenharLinha(largura, colunaTopo, deslocamento);
        }
    }

    private static int calcularLargura(int tamanho, int linha) {
        return tamanho - Math.abs(linha);
    }

    private static void desenharLinha(int largura, int colunaTopo, int deslocamento) {
        imprimirEspacos(colunaTopo - largura);
        imprimirCaracteres('x', 2 * largura + 1);
        imprimirEspacos(deslocamento);
        imprimirCaracteres('x', 2 * largura + 1);
        System.out.println();
    }

    private static void imprimirCaracteres(char caractere, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            System.out.print(caractere);
        }
    }

    private static void imprimirEspacos(int quantidade) {
        imprimirCaracteres(' ', quantidade);
    }
}
