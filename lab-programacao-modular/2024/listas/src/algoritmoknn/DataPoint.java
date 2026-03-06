package algoritmoknn;

import java.util.Objects;

public final class DataPoint {

    private final double calorias;
    private final double horasSono;
    private final String estado;

    public DataPoint(final double calorias, final double horasSono, final String estado) {
        DomainConstants.validarEntrada(calorias, horasSono);
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("Estado não pode ser nulo ou em branco");
        }
        this.calorias = calorias;
        this.horasSono = horasSono;
        this.estado = estado;
    }

    public double calorias() {
        return calorias;
    }

    public double horasSono() {
        return horasSono;
    }

    public String estado() {
        return estado;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DataPoint other)) return false;
        return Double.doubleToLongBits(calorias) == Double.doubleToLongBits(other.calorias())
            && Double.doubleToLongBits(horasSono) == Double.doubleToLongBits(other.horasSono())
            && Objects.equals(estado, other.estado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(calorias, horasSono, estado);
    }

    @Override
    public String toString() {
        return "DataPoint[calorias=" + calorias + ", horasSono=" + horasSono + ", estado=" + estado + "]";
    }
}
