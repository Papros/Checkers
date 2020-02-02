package Model;

import java.util.ArrayList;
import java.util.Vector;

public class Chessboard {

    public static final int BLACK = 3;
    public static final int B_KING = 4;
    public static final int WHITE = 5;
    public static final int W_KING = 6;

    private Pawn [][] board;
    private Touple2 picked;

    Pawn[][] new_board = {
            { null,new Pawn(BLACK),null,new Pawn(BLACK),null,new Pawn(BLACK),null,new Pawn(BLACK)},
            { new Pawn(BLACK),null,new Pawn(BLACK),null,new Pawn(BLACK),null,new Pawn(BLACK),null},
            { null,new Pawn(BLACK),null,new Pawn(BLACK),null,new Pawn(BLACK),null,new Pawn(BLACK)},
            {null,null,null,null,null,null,null,null},
            {null,null,null,null,null,null,null,null},
            { new Pawn(WHITE),null,new Pawn(WHITE),null,new Pawn(WHITE),null,new Pawn(WHITE),null},
            { null,new Pawn(WHITE),null,new Pawn(WHITE),null,new Pawn(WHITE),null,new Pawn(WHITE)},
            { new Pawn(WHITE),null,new Pawn(WHITE),null,new Pawn(WHITE),null,new Pawn(WHITE),null}
    };

    public ArrayList<Touple2> possibleMoves;
    public int moveCounter;
    public boolean whiteFirst = true;
    public boolean isPicked;
    int pickedIndex;

    public Chessboard(){

    }

    public void resetPawn(){

        possibleMoves = new ArrayList<Touple2>();

        board = new_board;

        this.moveCounter = 0;

    }

    public boolean whiteTurn(){
        return (whiteFirst && moveCounter%2 == 0);
    }

    public boolean blackTurn(){
        return (whiteFirst && moveCounter%2 == 1);
    }


    public Pawn getPawnAt(int xPos,int yPos){

       if(xPos >= 0 && xPos <=7 && yPos >=0 && yPos <=7)
           return board[xPos][yPos];
       else
           return null;
    }



    private Touple2 getMovesAt(int xPos, int yPos) {

        for(Touple2 move:this.possibleMoves){
            if(move.a == xPos && move.b == yPos)
                return move;
        }
        return null;
    }

    public ArrayList<Touple2> getPossibleMoves() {
        System.out.println("return "+possibleMoves.size()+" moves..");
        return possibleMoves;
    }

    public boolean pickPawn(int xPos, int yPos) {

        if(whiteTurn()){

            if(getPawnAt(xPos,yPos) != null) {
                if (getPawnAt(xPos, yPos).color == WHITE || getPawnAt(xPos, yPos).color == W_KING) {
                    picked = new Touple2(xPos, yPos);
                    return true;
                }
            }

        }else{

            if(getPawnAt(xPos,yPos) != null) {
                if (getPawnAt(xPos, yPos).color == BLACK || getPawnAt(xPos, yPos).color == B_KING) {
                    picked = new Touple2(xPos, yPos);
                    return true;
                }
            }
        }


        return false;

    }
}
