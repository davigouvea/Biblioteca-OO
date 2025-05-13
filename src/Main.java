//Davi Gouvea Santos | RGM: 35736640
//João Carlos Fava Filho | RGM: 38518709
//Natalli Semokoviski | RGM: 38718901

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
