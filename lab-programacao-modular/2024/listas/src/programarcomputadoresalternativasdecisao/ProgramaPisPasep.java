package programarcomputadoresalternativasdecisao;

import java.util.Scanner;

public final class ProgramaPisPasep {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final String numero = EntradaUtil.lerLinha(scanner, "Digite os 10 primeiros dígitos do número PIS/PASEP (sem traço): ");
            final int digito = PisPasep.calcularDigitoVerificador(numero);
            System.out.println("O dígito verificador é: " + digito);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
