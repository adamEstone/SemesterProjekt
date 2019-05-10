package semesterprojekt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

public class MultiMuselytter extends JPanel implements MouseListener, MouseMotionListener {

    public static int mouseX = 0;
    public static int mouseY = 0;
    public static boolean leftButtonDown = false;
    public static boolean rightButtonDown = false;

    @Override
    public void mousePressed(MouseEvent hændelse) // kræves af MouseListener
    {
        
        //backgroundMusic.play(0);

        Point trykpunkt = hændelse.getPoint();
        if (hændelse.getButton() == 1) {
            leftButtonDown = true;
            System.out.println("Venstre Mus trykket ned i " + trykpunkt);
        }
        if (hændelse.getButton() == 3) {
            rightButtonDown = true;
            System.out.println("Højre Mus trykket ned i " + trykpunkt);
        }

    }

    @Override
    public void mouseReleased(MouseEvent hændelse) // kræves af MouseListener
    {
        Point slippunkt = hændelse.getPoint();
        if (hændelse.getButton() == 1) {
            leftButtonDown = false;
            System.out.println("Venstre mus sluppet i" + slippunkt);
        }
        if (hændelse.getButton() == 3) {
            rightButtonDown = false;
            System.out.println("Højre højre sluppet i" + slippunkt);
        }

    }

    //--------------------------------------------------------------------
    //  Ubrugte hændelser (skal defineres for at implementere MouseListener)
    //--------------------------------------------------------------------
    @Override
    public void mouseClicked(MouseEvent hændelse) // kræves af MouseListener
    {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }	// kræves af MouseListener

    @Override
    public void mouseExited(MouseEvent event) {
    }	// kræves af MouseListener

    @Override
    public void mouseDragged(MouseEvent hændelse) {
        int x = AreaCoordinates.AC.getPlayableAreaX();
        if (hændelse.getX() >= x)
        {
            mouseX = x;
        }
        else
        {
            mouseX = hændelse.getX();
        }
        mouseY = hændelse.getY();
    } // kræves af MouseMotionListener

    @Override
    public void mouseMoved(MouseEvent hændelse) {
        //mousePos.x = hændelse.getX();
        int x = AreaCoordinates.AC.getPlayableAreaX();
        if (hændelse.getX() >= x)
        {
            mouseX = x;
        }
        else
        {
            mouseX = hændelse.getX();
        }
        
        mouseY = hændelse.getY();
        System.out.println("mus rykket til: " + hændelse.getX());
    } //kræves af MouseMotionListener

}
