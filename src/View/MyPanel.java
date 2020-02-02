package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPanel extends JPanel {

    Graphics g2d;

    private ArrayList<Tuple> toDraw;
    private ArrayList<Tuple> movesDraw;
    private ArrayList<Image> images;
    int fieldSize;
    int x,y;

    public MyPanel(){
        super();
        images = new ArrayList<Image>();
        toDraw = new ArrayList<Tuple>();
        movesDraw= new ArrayList<Tuple>();
    }

    @Override
    protected void paintComponent(Graphics g2d) {
        super.paintComponent(g2d);
        this.g2d = g2d;

        g2d.drawImage(images.get(0),0,0,this);

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(j%2==1){
                        g2d.drawImage(images.get(i%2+1),i*fieldSize+fieldSize/2,j*fieldSize+fieldSize/2,this);
                }else{
                        g2d.drawImage(images.get(2-i%2),i*fieldSize+fieldSize/2,j*fieldSize+fieldSize/2,this);
                }
            }
        }


        for (Tuple tuple: toDraw) {

            if(images.size() > tuple.a)
            g2d.drawImage(images.get(tuple.a),tuple.b,tuple.c,this);

        }

        for (Tuple tuple: movesDraw) {
            if(tuple.a == -1)
            g2d.drawImage(images.get(8),tuple.b,tuple.c,this);
        }


        g2d.drawImage(images.get(7),x*fieldSize+fieldSize/2,y*fieldSize+fieldSize/2,this);


    }

    public void clear(){
        toDraw.clear();
        movesDraw.clear();
    }

    public void update(ArrayList<Tuple> newToDraw){
        this.toDraw = newToDraw;
    }

    public void pushToDraw(Tuple t){
        this.toDraw.add(t);
    }

    public void pushMovesToDraw(Tuple t){
        this.movesDraw.add(t);
    }

    public void setImages(Image[] newImages){
        this.images.addAll(Arrays.asList(newImages));
    }

    public void setFieldSize(int size) {
        this.fieldSize = size;
    }

    public void setPointer(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
