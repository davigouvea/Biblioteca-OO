package model;

public abstract class Entidade {
    private int id;

    public Entidade(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
