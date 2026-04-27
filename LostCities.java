import CONTROLLER.Controller;
import MODEL.Table.Board;
import MODEL.Players.Player;
import VIEW.GUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LostCities {
    public static void main(String[] args) {
        //Initialize Players
        List<Player> players = new ArrayList<>();
        players.add(new Player("Παίκτης Α"));
        players.add(new Player("Παίκτης Β"));
        //Initialize the Controller
        Controller controller = new Controller(players);

        //Initialize the View (GUI)
        GUI gui = new GUI(controller);
        controller.setView(gui);
    }
}