package Model;

import java.util.ArrayList;
import java.util.Vector;

public class Chessboard {

    public ArrayList<Pawn> black;
    public ArrayList<Pawn> white;
    public ArrayList<Touple2> possibleMoves;
    public int moveCounter;
    public boolean whiteFirst = true;
    public boolean isPicked;
    int pickedIndex;

    public Chessboard(){

    }

    public void resetPawn(){
        black = new ArrayList<Pawn>();
        white = new ArrayList<Pawn>();
        possibleMoves = new ArrayList<Touple2>();

        black.add(new Pawn(Pawn.BLACK,1,0));
        black.add(new Pawn(Pawn.BLACK,3,0));
        black.add(new Pawn(Pawn.BLACK,5,0));
        black.add(new Pawn(Pawn.BLACK,7,0));

        black.add(new Pawn(Pawn.BLACK,0,1));
        black.add(new Pawn(Pawn.BLACK,2,1));
        black.add(new Pawn(Pawn.BLACK,4,1));
        black.add(new Pawn(Pawn.BLACK,6,1));

        black.add(new Pawn(Pawn.BLACK,1,2));
        black.add(new Pawn(Pawn.BLACK,3,2));
        black.add(new Pawn(Pawn.BLACK,5,2));
        black.add(new Pawn(Pawn.BLACK,7,2));

        white.add(new Pawn(Pawn.WHITE,0,7));
        white.add(new Pawn(Pawn.WHITE,2,7));
        white.add(new Pawn(Pawn.WHITE,4,7));
        white.add(new Pawn(Pawn.WHITE,6,7));

        white.add(new Pawn(Pawn.WHITE,1,6));
        white.add(new Pawn(Pawn.WHITE,3,6));
        white.add(new Pawn(Pawn.WHITE,5,6));
        white.add(new Pawn(Pawn.WHITE,7,6));

        white.add(new Pawn(Pawn.WHITE,0,5));
        white.add(new Pawn(Pawn.WHITE,2,5));
        white.add(new Pawn(Pawn.WHITE,4,5));
        white.add(new Pawn(Pawn.WHITE,6,5));

        this.moveCounter = 0;

    }

    public boolean whiteTurn(){
        return (whiteFirst && moveCounter%2 == 0);
    }

    public boolean blackTurn(){
        return (whiteFirst && moveCounter%2 == 1);
    }

    public Pawn getWhite(int at){
        return white.get(at);
    }

    public Pawn getBlack(int at){
        return black.get(at);
    }

    public ArrayList<Pawn> getBlackPawns(){
        return black;
    }

    public ArrayList<Pawn> getWhitePawns(){
        return white;
    }

    private Pawn getPawnAt(int xPos,int yPos){

        if(whiteTurn()){

            for(int i=0;i<this.white.size();i++){
                if(white.get(i).getX() == xPos && white.get(i).getY() == yPos){
                    pickedIndex = i;
                    return white.get(i);
                }

            }

        }else{

            for(int i=0;i<this.black.size();i++){
                if(black.get(i).getX() == xPos && black.get(i).getY() == yPos){
                    pickedIndex = i;
                    return black.get(i);
                }
            }

        }
        return null;
    }

    public void findPossibleMoves(Pawn pick){
        System.out.println("searching for moves..");
        int[][][] board = new int[8][8][2];
        possibleMoves.clear();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                board[i][j][0] = -1;
                board[i][j][1] = -1;
            }
        }

        for (int i=0;i<12;i++) {
            board[black.get(i).getY()][black.get(i).getX()][0] = black.get(i).color;
            board[black.get(i).getY()][black.get(i).getX()][1] = i;

            board[white.get(i).getY()][white.get(i).getX()][0] = white.get(i).color;
            board[white.get(i).getY()][white.get(i).getX()][1] = i;
        }

        if(!pick.isPromoted()){

            if(pick.isBlack()){
                    if(pick.getY() <= 6){
                        if(pick.getX() <= 6){
                            if(board[pick.getY()+1][pick.getX()+1][0] == -1){
                                possibleMoves.add( new Touple2( pick.getX()+1, pick.getY()+1 ) );
                            }
                        }

                        if(pick.getX() > 0){
                            if(board[pick.getY()+1][pick.getX()-1][0] == -1){
                                possibleMoves.add( new Touple2( pick.getX()-1, pick.getY()+1 ) );
                            }
                        }

                    }
            }else{

                if(pick.getY() > 0){
                    if(pick.getX() <= 6){
                        if(board[pick.getY()-1][pick.getX()+1][0] == -1){
                            possibleMoves.add( new Touple2( pick.getX()+1, pick.getY()-1 ) );
                        }
                    }

                    if(pick.getX() > 0){
                        if(board[pick.getY()-1][pick.getX()-1][0] == -1){
                            possibleMoves.add( new Touple2( pick.getX()-1, pick.getY()-1 ) );
                        }
                    }

                }

            }

        }else{

        }

        System.out.println("Moves found: "+possibleMoves.size());

    }

    public void pickPawn(int xPos, int yPos) {


        System.out.println("Pawn picked");

        Pawn pick = getPawnAt(xPos, yPos);

        if(pick != null && pick.isWhite()==this.whiteTurn() ){
            findPossibleMoves(pick);
            isPicked = true;
        }


        if(isPicked){

            Touple2 move = getMovesAt(xPos,yPos);
            if(move != null){

                if(this.whiteTurn()){
                    white.get(pickedIndex).move(xPos, yPos);
                    isPicked = false;
                    pickedIndex = -1;
                    possibleMoves.clear();
                    moveCounter++;
                }else {
                    moveCounter++;
                    black.get(pickedIndex).move(xPos, yPos);
                    isPicked = false;
                    possibleMoves.clear();
                    pickedIndex = -1;
                }

            }
        }


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
}
