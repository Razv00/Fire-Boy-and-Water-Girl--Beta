package PaooGame.Input;

import PaooGame.Items.Map_level;
import PaooGame.Items.Scor;
import PaooGame.RefLinks;
import PaooGame.States.MenuState;
import PaooGame.States.NextLevelState;
import PaooGame.States.PlayState;
import PaooGame.States.State;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener , MouseMotionListener {
    private int x , y;
    private RefLinks refLink;

    public MouseManager(RefLinks refLink)
    {
        this.refLink = refLink;

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if(x >= refLink.GetWidth()/2 - 70 && x <= refLink.GetWidth()/2 - 70 + 130 &&
                y >= 285 && y <= 285 + 80) {
            State.SetState(new PlayState(refLink));
            Scor.GetInstance().setScor(0);
            Map_level.GetInstance().SetLevel(1);
            System.out.println("mouse apasat1");
        }
        if(x>= refLink.GetWidth()/2 - 70 && x<=refLink.GetWidth()/2 - 70 + 130 &&
                y>=400 && y <= 400+50){
            State.SetState(new MenuState(refLink));

        }

        if(x>= refLink.GetWidth()/2 - 70 && x<=refLink.GetWidth()/2 - 70 + 130 &&
                y>=100 && y <= 100+50){
            State.SetState(new NextLevelState(refLink));
            State.SetState(new PlayState(refLink));

            //Map_level.GetInstance().SetLevel(2);


        }




        System.out.println("mouse apasat");
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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    public int GetX(){
        return x;
    }

    public int GetY(){
        return y;
    }

    public Rectangle GetRectangle(){
        return new Rectangle((int)GetX(),(int)GetY(),1,1);
    }
}
