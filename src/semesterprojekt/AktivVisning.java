package semesterprojekt;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class AktivVisning extends JFrame {

    Graphics2D g2;
    AffineTransform orgTrans;
    //GeneralPath fig = new Java2DDemo().fig;      // stjæl 'smiley' fra andet eks.
    double[][] koord = new double[50][6];        // koordinater på figurer // koordinater på figurer // koordinater på figurer // koordinater på figurer

    
    @Override
    public void setTitle(String title) {
        super.setTitle(title); //To change body of generated methods, choose Tools | Templates.
    }
    
    void init() {
        setTitle("Spil");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIgnoreRepaint(true);      // system skal IKKE kalde paintComponent()
        this.setVisible(true);

        g2 = (Graphics2D) this.getGraphics();     // aktiv visning: gem Graphics...
        orgTrans = g2.getTransform();             // ... og brug det som du lyster!
        g2.setTransform(orgTrans);

    }


}
