package Controller;

import Model.Model;
import View.View;

public class Controller {

    View view;
    Model model;

    public Controller(){

    }

    public void registerView(View view) {
        this.view = view;
    }

    public void registerModel(Model model) {
        this.model = model;
    }

    public void callViewDraw(int color,int posX,int posY){
        view.callToDraw(color,posX,posY);
    }

    public void clearBuffor(){
        this.view.clearBuffor();
    }

    public void pickPawn(int xPos, int yPos) {
        this.model.pickPawn(xPos,yPos);
    }
}
