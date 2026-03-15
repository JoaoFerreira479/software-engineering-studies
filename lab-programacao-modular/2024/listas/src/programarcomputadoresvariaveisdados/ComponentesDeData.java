package programarcomputadoresvariaveisdados;
import lab.listas.infrastructure.io.Console;
import lab.listas.infrastructure.io.EntradaUtil;

import java.util.Scanner;

public class ComponentesDeData {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String entrada = EntradaUtil.lerLinha(scanner, "Digite uma data no formato dd/mm/aa: ");

            if (ManipuladorData.validarFormatoData(entrada)) {
                try {
                    Data data = ManipuladorData.converterParaData(entrada);

                    Console.println("Dia: " + data.getDia());
                    Console.println("Mês: " + data.getMes());
                    Console.println("Ano: " + data.getAno());
                } catch (IllegalArgumentException e) {
                    Console.println("Erro: " + e.getMessage());
                }
            } else {
                Console.println("Erro: Formato de data inválido. Use o formato dd/mm/aa.");
            }
        }
    }
}
