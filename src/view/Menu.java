package view;

import controller.BibliotecaController;
import model.*;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private BibliotecaController controller;
    private Scanner scanner;

    public Menu(BibliotecaController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibir() {
        int opcao;

        do {
            System.out.println("\n===== MENU BIBLIOTECA =====");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("3. Pesquisar Livro");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Devolver Livro");
            System.out.println("6. Listar Empréstimos Ativos");
            System.out.println("7. Listar Atrasos");
            System.out.println("8. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            try {
                switch (opcao) {
                    case 1 -> cadastrarLivro();
                    case 2 -> cadastrarUsuario();
                    case 3 -> pesquisarLivro();
                    case 4 -> emprestarLivro();
                    case 5 -> devolverLivro();
                    case 6 -> listarEmprestimos();
                    case 7 -> listarAtrasos();
                    case 8 -> relatorios();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    private void cadastrarLivro() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Exemplares: ");
        int exemplares = Integer.parseInt(scanner.nextLine());
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        controller.cadastrarLivro(titulo, autor, ano, exemplares, categoria);
        System.out.println("Livro cadastrado!");
    }

    private void cadastrarUsuario() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        controller.cadastrarUsuario(nome, telefone, endereco, email);
        System.out.println("Usuário cadastrado!");
    }

    private void pesquisarLivro() {
        System.out.print("Digite termo de busca: ");
        String termo = scanner.nextLine();
        List<Livro> resultados = controller.pesquisarLivros(termo);

        if (resultados.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            resultados.forEach(System.out::println);
        }
    }

    private void emprestarLivro() throws Exception {
        System.out.print("ID do Livro: ");
        int idLivro = Integer.parseInt(scanner.nextLine());
        System.out.print("ID do Usuário: ");
        int idUsuario = Integer.parseInt(scanner.nextLine());

        controller.emprestarLivro(idLivro, idUsuario);
        System.out.println("Empréstimo realizado!");
    }

    private void devolverLivro() throws Exception {
        System.out.print("ID do Livro a ser devolvido: ");
        int idLivro = Integer.parseInt(scanner.nextLine());

        controller.devolverLivro(idLivro);
        System.out.println("Livro devolvido!");
    }

    private void listarEmprestimos() {
        List<Emprestimo> emprestimos = controller.getEmprestimosAtivos();
        emprestimos.forEach(System.out::println);
    }

    private void listarAtrasos() {
        List<Emprestimo> atrasos = controller.getAtrasos();
        atrasos.forEach(e -> System.out.println(e + " | Dias de atraso: " + e.diasAtraso()));
    }

    private void relatorios() {
        System.out.println("\nLivros cadastrados:");
        controller.getLivros().forEach(System.out::println);

        System.out.println("\nUsuários cadastrados:");
        controller.getUsuarios().forEach(System.out::println);

        System.out.println("\nTodos os empréstimos:");
        controller.getTodosEmprestimos().forEach(System.out::println);
    }
}
