package Model;

import Controller.Controller;

public class Model {

    Controller controller; //Controller object
    Chessboard board;

    public Model(Controller controller) {
        this.controller = controller;
        controller.registerModel(this);
    }

    public void startGame(){

        board = new Chessboard();
        board.resetPawn();
        callToDraw();
    }


    public void callToDraw(){
        controller.clearBuffor();
        for (Pawn pawn:board.getBlackPawns() ) {
            if(pawn.isInGame())
            controller.callViewDraw(pawn.getColor(),pawn.getX(),pawn.getY());
        }

        for (Pawn pawn:board.getWhitePawns() ) {
            if(pawn.isInGame())
            controller.callViewDraw(pawn.getColor(),pawn.getX(),pawn.getY());
        }

        for(Touple2 move:board.getPossibleMoves()){
            controller.callViewDraw(-1,move.a,move.b);
            System.out.println("MOVE: "+move.a+" : "+move.b);
        }
        
    }


    public void pickPawn(int xPos, int yPos) {

        board.pickPawn(xPos,yPos);
        callToDraw();
    }
}
