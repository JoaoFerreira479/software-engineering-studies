package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public final class ProgramaTipoCaractere {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final String entrada = EntradaUtil.lerLinha(scanner, "Digite um caractere: ");
            if (entrada.length() != 1) {
                System.out.println("Erro: Por favor, insira apenas um único caractere.");
                return;
            }
            final char c = entrada.charAt(0);
            final String tipo = TipoCaractere.identificarTipo(c);
            System.out.println("O caractere '" + c + "' é do tipo: " + tipo);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
