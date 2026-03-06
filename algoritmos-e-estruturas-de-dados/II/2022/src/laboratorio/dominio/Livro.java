package laboratorio.dominio;

import java.util.Objects;

public final class Livro {

    private final String titulo;
    private final String autor;
    private final String assunto;
    private final int id;

    public Livro(String titulo, String autor, String assunto, int id) {
        this.titulo = Objects.requireNonNull(titulo, "titulo");
        this.autor = Objects.requireNonNull(autor, "autor");
        this.assunto = Objects.requireNonNull(assunto, "assunto");
        if (id < 0) {
            throw new IllegalArgumentException("ID não pode ser negativo: " + id);
        }
        this.id = id;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getAssunto() { return assunto; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Assunto: " + assunto + ", ID: " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id == livro.id
            && titulo.equals(livro.titulo)
            && autor.equals(livro.autor)
            && assunto.equals(livro.assunto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, assunto, id);
    }
}
