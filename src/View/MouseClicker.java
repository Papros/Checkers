package View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClicker implements MouseListener {
    View view;

    public MouseClicker(View v){
        this.view = v;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        view.registerClick();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
