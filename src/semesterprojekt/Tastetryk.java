package semesterprojekt;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tastetryk extends JPanel implements KeyListener {

    String tekst = "tast noget - pil op/ned rykker teksten ";
    Point pos = new Point(20, 20);

    private boolean pauseTast = false;
    private boolean escapeTast = false;

    public Tastetryk() {
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        System.out.println("Keyboard lytter");
    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.print("Der blev trykket: ");
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.print("ESCAPE \n");
            
            
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
                System.out.print("P \n");
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

        } else {
            tekst = tekst + e.getKeyChar();
        }

    }

    public void keyReleased(KeyEvent e) {
        System.out.println("ENTERRR2");
    } // kræves af KeyListener

    public void keyTyped(KeyEvent e) {
        System.out.println("ENTERRR3");
    } // kræves af KeyListener
}
