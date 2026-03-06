package programarcomputadoresvariaveisdados;

public final class ManipuladorData {

    private static final String REGEX_FORMATO = "\\d{2}/\\d{2}/\\d{2}";

    private ManipuladorData() {
    }

    public static boolean validarFormatoData(String data) {
        return data != null && data.matches(REGEX_FORMATO);
    }

    public static Data converterParaData(String data) {
        if (data == null || !validarFormatoData(data)) {
            throw new IllegalArgumentException("Formato de data inválido. Use dd/mm/aa.");
        }
        String[] partes = data.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);
        return new Data(dia, mes, ano);
    }
}
