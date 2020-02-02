package View;

import Controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class View {

    private int HEIGHT = (int)( 550);
    private int WIDTH = HEIGHT;//(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int BOARD_SIZE = HEIGHT;
    private int FIELD_SIZE = BOARD_SIZE/8;
    private int BOARD_PADDING = FIELD_SIZE/2;
    private int PAWN_SIZE = (int) ( FIELD_SIZE );
    private int PAWN_PADDING = (FIELD_SIZE-PAWN_SIZE) / 2;


    private Controller controller;  //Controller object
    private JFrame myFrame;
    private JPanel myPanel;

    private Image bKing;
    private Image wKing;
    private Image bPawn;
    private Image wPawn;
    private Image backgound,bField,wField,pointer,possible,danger;

    int xPos,yPos;

    public View(Controller controller) {
        this.controller = controller;
        controller.registerView(this);

        myFrame = new JFrame("Checkers");
        myPanel = new MyPanel();

        myPanel.addMouseMotionListener(new MouseMotionController(this));
        myPanel.addMouseListener(new MouseClicker(this));
        myFrame.setLayout(new BorderLayout());
        myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myFrame.setResizable(true);
        myFrame.setVisible(true);
      //  myFrame.setPreferredSize(new Dimension((int)(WIDTH),(int)(HEIGHT)));


        myPanel.setPreferredSize(new Dimension( (int)(WIDTH+FIELD_SIZE), (int)(HEIGHT+FIELD_SIZE)));

        myFrame.add(myPanel);
        myFrame.pack();

        loadRes();
        Image[] img = {backgound,bField,wField,bPawn,bKing,wPawn,wKing,pointer,possible,danger};
        ((MyPanel) myPanel).setImages(img);
        ((MyPanel) myPanel).setFieldSize(FIELD_SIZE);
       // update();
    }



    private void update(){


        myPanel.repaint();
       // myPanel.callToDraw(board,0,0);

    }

    public void drawPointer(int x,int y){
        ((MyPanel) myPanel).setPointer(x,y);
    }


    private void loadRes(){

        try {
            bPawn =  ImageIO.read(new File("Res/BPawn.png")).getScaledInstance(PAWN_SIZE, PAWN_SIZE,1);
            wPawn =  ImageIO.read(new File("Res/WPawn.png")).getScaledInstance(PAWN_SIZE, PAWN_SIZE,1);
            bKing =  ImageIO.read(new File("Res/BKing.png")).getScaledInstance(PAWN_SIZE, PAWN_SIZE,1);
            bKing =  ImageIO.read(new File("Res/WKing.png")).getScaledInstance(PAWN_SIZE, PAWN_SIZE,1);
            backgound=  ImageIO.read(new File("Res/background.png")).getScaledInstance(BOARD_SIZE+FIELD_SIZE, BOARD_SIZE+FIELD_SIZE,1);
            bField=  ImageIO.read(new File("Res/bField.png")).getScaledInstance(FIELD_SIZE, FIELD_SIZE,1);
            wField=  ImageIO.read(new File("Res/wField.png")).getScaledInstance(FIELD_SIZE, FIELD_SIZE,1);
            pointer =  ImageIO.read(new File("Res/pointer.png")).getScaledInstance(FIELD_SIZE, FIELD_SIZE,1);
            possible = ImageIO.read(new File("Res/possible.png")).getScaledInstance(FIELD_SIZE, FIELD_SIZE,1);
            danger = ImageIO.read(new File("Res/danger.png")).getScaledInstance(FIELD_SIZE, FIELD_SIZE,1);
            System.out.println("wczytano...");
        } catch (IOException e) {
            msg("Blad odczytu obrazka");
            System.out.println("Blad odczytu obrazka");
            e.printStackTrace();
        }

    }


    public void msg(String string) {
        JOptionPane.showMessageDialog(null,string);

    }

    public void clearBuffor(){
        ( (MyPanel) myPanel ).clear();
    }

    public void callToDraw(int color, int posX, int posY) {
        if(color > 0)
            ( (MyPanel) myPanel ).pushToDraw(new Tuple(color,posY*FIELD_SIZE+PAWN_PADDING+BOARD_PADDING,posX*FIELD_SIZE+PAWN_PADDING+BOARD_PADDING ));
        else
            ( (MyPanel) myPanel ).pushMovesToDraw(new Tuple(color,posY*FIELD_SIZE+BOARD_PADDING,posX*FIELD_SIZE+BOARD_PADDING ));
    }

    public void registerMove(int x,int y){
        int x1 = (x-this.BOARD_PADDING) / this.FIELD_SIZE;
        int y1 = (y-this.BOARD_PADDING) / this.FIELD_SIZE;

        if(x1 < 8 && y1 < 8 ) {
            xPos = x1;
            yPos = y1;
            ((MyPanel) myPanel).setPointer(xPos, yPos);
            myPanel.repaint();
        }
    }

    public void registerClick() {
        this.controller.pickPawn(xPos,yPos);
        myPanel.repaint();
    }
}
