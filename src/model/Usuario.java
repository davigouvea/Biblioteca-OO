package model;

public class Usuario extends Entidade {
    private String nome;
    private String telefone;
    private String endereco;
    private String email;

    public Usuario(int id, String nome, String telefone, String endereco, String email) {
        super(id);
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " - " + email;
    }
}
