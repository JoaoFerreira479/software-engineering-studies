package programarcomputadoresbasicos;

public record Nota(double valor, int peso) {

    public Nota {
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso deve ser positivo: " + peso);
        }
    }
}
