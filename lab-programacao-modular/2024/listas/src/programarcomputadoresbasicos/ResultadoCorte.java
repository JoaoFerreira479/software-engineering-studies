package programarcomputadoresbasicos;

public record ResultadoCorte(int tamanhoTabua, int pecasPorTabua, int tabuasNecessarias, int sobraNaUltimaTabua) {

    public ResultadoCorte {
        if (tamanhoTabua <= 0 || pecasPorTabua <= 0 || tabuasNecessarias < 0 || sobraNaUltimaTabua < 0) {
            throw new IllegalArgumentException("Valores do resultado de corte devem ser consistentes");
        }
    }
}
