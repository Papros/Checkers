package View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionController implements MouseMotionListener {

    private View view;

    public MouseMotionController(View v){
        view = v;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        view.registerMove(e.getX(),e.getY());
    }
}
