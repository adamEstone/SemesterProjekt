package semesterprojekt;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Java2DDemo extends JPanel {

    GeneralPath fig;

    AffineTransform trans;

    BasicStroke stregtype // 2-punktsstreg med kantede ender og runde knæk
            = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);

    public Java2DDemo() {
        setBackground(Color.WHITE);

        // Lav 'smiley' - cirkel, to øjne og glad åben mund
        fig = new GeneralPath(new Ellipse2D.Float(0, 0, 100, 100));
        fig.append(new Ellipse2D.Float(20, 20, 10, 10), false);
        fig.append(new Ellipse2D.Float(70, 20, 10, 10), false);
        fig.append(new Arc2D.Float(10, 10, 80, 80, 330, -120, Arc2D.CHORD), false);

        trans = AffineTransform.getScaleInstance(0.7, 0.7); // formindsk
        trans.rotate(0.3);                                  // roter
        trans.shear(0.3, 0);                                 // vrid
        trans.translate(160, -50);                           // flyt til siden
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;              // brug Java2D

        // koordinattransformation
        AffineTransform orgTrans = g2.getTransform();// husk original transformation
        g2.translate(10, 10);

        g2.draw(fig);					                 // tegn almindelig
        g2.translate(110, 0);

        g2.setStroke(stregtype);
        g2.draw(fig);					                 // 2-pkt linjetykkelse
        g2.translate(110, 0);

        g2.setRenderingHint( // sæt tegnevink til trappeudjævning (antialias)
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.draw(fig);					                 // tegn med trappeudjævning

        g2.transform(trans);
        g2.draw(fig);					                 // tegn med transformation

        g2.transform(trans);
        g2.draw(fig);					                 // tegn med transformation	x2

        g2.transform(trans);
        g2.draw(fig);					                 // tegn med transformation	x3

        g2.setTransform(orgTrans);                 // genskab orig. transformation
    }

    public static void main(String[] arg) {
        JFrame vindue = new JFrame("Java2DDemo");
        vindue.add(new Java2DDemo());
        vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        vindue.setSize(500, 150);
        vindue.setVisible(true);
    }
}
