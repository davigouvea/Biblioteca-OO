package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public void registrarDevolucao() {
        this.dataDevolucao = LocalDate.now();
        livro.devolver();
    }

    public boolean emAtraso() {
        return dataDevolucao != null && dataDevolucao.isAfter(dataPrevistaDevolucao);
    }

    public long diasAtraso() {
        return emAtraso() ? ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataDevolucao) : 0;
    }

    public boolean estaAtivo() {
        return dataDevolucao == null;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString() {
        return "Livro: " + livro + ", Usuário: " + usuario + ", Devolução: " + (dataDevolucao != null ? dataDevolucao : "Pendente");
    }
}
