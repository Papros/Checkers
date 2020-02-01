package Model;

public class Pawn {

    public static final int BLACK = 3;
    public static final int B_KING = 4;
    public static final int WHITE = 5;
    public static final int W_KING = 6;

    int color;
    boolean promoted;
    int x,y;
    boolean inGame;

    public Pawn(int color,int x,int y) {
            this.inGame = true;
            this.color = color;
            this.x = x;
            this.y = y;
    }

    public void move(int nx,int ny){
        this.x = nx;
        this.y = ny;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isPromoted(){
        return promoted;
    }

    public boolean isWhite(){
        return color==WHITE;
    }

    public boolean isBlack(){
        return color==BLACK;
    }

    public int getColor() {
        return color;
    }

    public boolean isInGame(){
        return inGame;
    }

    public void Kill(){
        inGame = false;
    }
}
