package controller;

import model.*;
import java.util.*;
import java.time.LocalDate;

public class BibliotecaController {
    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private int proximoId = 1;

    public void cadastrarLivro(String titulo, String autor, int ano, int exemplares, String categoria) {
        livros.add(new Livro(proximoId++, titulo, autor, ano, exemplares, categoria));
    }

    public void cadastrarUsuario(String nome, String telefone, String endereco, String email) {
        usuarios.add(new Usuario(proximoId++, nome, telefone, endereco, email));
    }

    public List<Livro> pesquisarLivros(String termo) {
        List<Livro> encontrados = new ArrayList<>();
        for (Livro l : livros) {
            if (l.contem(termo)) {
                encontrados.add(l);
            }
        }
        return encontrados;
    }

    public void emprestarLivro(int idLivro, int idUsuario) throws Exception {
        Livro livro = buscarLivro(idLivro);
        Usuario usuario = buscarUsuario(idUsuario);

        if (livro == null || usuario == null) throw new Exception("Livro ou usuário inválido.");

        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getId() == idUsuario && e.estaAtivo())
                throw new Exception("Usuário já possui empréstimo ativo.");
        }

        livro.emprestar();
        emprestimos.add(new Emprestimo(livro, usuario, LocalDate.now(), LocalDate.now().plusDays(7)));
    }

    public void devolverLivro(int idLivro) throws Exception {
        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getId() == idLivro && e.estaAtivo()) {
                e.registrarDevolucao();
                return;
            }
        }
        throw new Exception("Nenhum empréstimo ativo encontrado para o livro.");
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (e.estaAtivo()) ativos.add(e);
        }
        return ativos;
    }

    public List<Emprestimo> getAtrasos() {
        List<Emprestimo> atrasados = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            if (!e.estaAtivo() && e.emAtraso()) {
                atrasados.add(e);
            }
        }
        atrasados.sort(Comparator.comparingLong(Emprestimo::diasAtraso).reversed());
        return atrasados;
    }

    public Livro buscarLivro(int id) {
        for (Livro l : livros) if (l.getId() == id) return l;
        return null;
    }

    public Usuario buscarUsuario(int id) {
        for (Usuario u : usuarios) if (u.getId() == id) return u;
        return null;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Emprestimo> getTodosEmprestimos() {
        return emprestimos;
    }
}
