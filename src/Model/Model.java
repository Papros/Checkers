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

        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++){
                Pawn pawn = board.getPawnAt(i,j);
                if(pawn != null)
                    if(pawn.isInGame())
                        controller.callViewDraw(pawn.getColor(),i,j);
            }
        
        for(Touple2 move:board.getPossibleMoves()){
            controller.callViewDraw(-1,move.a,move.b);
            System.out.println("MOVE: "+move.a+" : "+move.b);
        }
        
    }


    public void pickPawn(int xPos, int yPos) {
        if(board.pickPawn(xPos,yPos))
            callToDraw();
    }
}
