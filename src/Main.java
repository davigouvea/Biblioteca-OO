import controller.BibliotecaController;
import utils.PreCarga;
import view.Menu;

public class Main {
    public static void main(String[] args) {
        BibliotecaController controller = new BibliotecaController();
        PreCarga.carregarDados(controller);
        Menu menu = new Menu(controller);
        menu.exibir();
    }
}
