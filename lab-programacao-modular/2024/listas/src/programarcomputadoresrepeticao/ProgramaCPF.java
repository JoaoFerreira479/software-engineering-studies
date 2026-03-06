package programarcomputadoresrepeticao;

import java.util.Scanner;

public final class ProgramaCPF {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final String entradaCpf = EntradaUtil.lerLinha(scanner, "Digite os 9 primeiros dígitos do CPF (apenas números): ");
            CPF.validarEntrada(entradaCpf);

            final int[] cpfNumeros = new int[9];
            for (int i = 0; i < 9; i++) {
                cpfNumeros[i] = Character.getNumericValue(entradaCpf.charAt(i));
            }
            final int dv1 = CPF.calcularDV1(cpfNumeros);
            final int dv2 = CPF.calcularDV2(cpfNumeros, dv1);
            final int numeroDeControle = CPF.calcularNumeroDeControle(dv1, dv2);

            System.out.println("Primeiro dígito verificador (DV1): " + dv1);
            System.out.println("Segundo dígito verificador (DV2): " + dv2);
            System.out.println("Número de controle do CPF: " + numeroDeControle);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
