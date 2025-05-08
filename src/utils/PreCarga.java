package utils;

import controller.BibliotecaController;

public class PreCarga {
    public static void carregarDados(BibliotecaController controller) {
        controller.cadastrarLivro("Java Básico", "João Silva", 2019, 5, "Programação");
        controller.cadastrarLivro("Estrutura de Dados", "Maria Santos", 2020, 3, "Computação");
        controller.cadastrarLivro("POO em Java", "Carlos Lima", 2022, 4, "Programação");

        controller.cadastrarUsuario("Ana Paula", "123456789", "Rua A", "ana@email.com");
        controller.cadastrarUsuario("Bruno Souza", "987654321", "Rua B", "bruno@email.com");
    }
}
