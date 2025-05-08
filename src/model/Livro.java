package model;

public class Livro extends Entidade implements Pesquisavel {
    private String titulo;
    private String autor;
    private int ano;
    private int exemplares;
    private String categoria;

    public Livro(int id, String titulo, String autor, int ano, int exemplares, String categoria) {
        super(id);
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.exemplares = exemplares;
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getExemplares() {
        return exemplares;
    }

    public void emprestar() throws Exception {
        if (exemplares <= 0) throw new Exception("Sem exemplares disponíveis.");
        exemplares--;
    }

    public void devolver() {
        exemplares++;
    }

    @Override
    public boolean contem(String termo) {
        return titulo.contains(termo) || autor.contains(termo) || categoria.contains(termo);
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + ano + ")";
    }
}
