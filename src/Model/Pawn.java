package Model;

public class Pawn {

    int color;
    boolean promoted;
    boolean inGame;

    public Pawn(int color) {
            this.inGame = true;
            this.color = color;
    }

    public boolean isPromoted(){
        return promoted;
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
