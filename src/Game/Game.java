package Game;

import Controller.Controller;
import Model.Model;
import View.View;

public class Game {

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Controller controller = new Controller();
                Model model = new Model(controller);
                View view = new View(controller);
                model.startGame();
            }
        });


    }
}
