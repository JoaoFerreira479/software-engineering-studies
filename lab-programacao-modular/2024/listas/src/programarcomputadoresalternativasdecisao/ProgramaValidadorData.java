package programarcomputadoresalternativasdecisao;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public final class ProgramaValidadorData {

    public static void main(final String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final String data = EntradaUtil.lerLinha(scanner, "Digite uma data no formato DD/MM/AAAA: ");
            Console.println(ValidadorData.validarData(data));
        }
    }
}
