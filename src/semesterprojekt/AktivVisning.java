package semesterprojekt;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AktivVisning extends JFrame {
    
    Graphics2D g2;
    AffineTransform orgTrans;
    GeneralPath fig = new Java2DDemo().fig;      // stjæl 'smiley' fra andet eks.
    double[][] koord = new double[50][6];        // koordinater på figurer

    void init() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIgnoreRepaint(true);      // system skal IKKE kalde paintComponent()
        this.setVisible(true);

        g2 = (Graphics2D) this.getGraphics();     // aktiv visning: gem Graphics...
        orgTrans = g2.getTransform();             // ... og brug det som du lyster!

        for (int i = 0; i < koord.length; i++) // sæt alle stjerne-koordinater
        {
            for (int j = 0; j < koord[i].length; j++) // til noget tilfældigt: 0=tid,
            {
                koord[i][j] = Math.random();          // 1=x, 2=y, 3,4=skala, 5=rotation
            }
        }
    }

    // public void paintComponent() {}           // definer IKKE - aktiv visning!
    void tegnSpil() {                 // venter lidt, tegner og opdaterer koordinater
        
        g2.setTransform(orgTrans);               // genskab orig. transformation
        g2.setColor(Color.WHITE);                  // rens skærmen selv
        Dimension d = getSize();
        g2.fillRect(0, 0, d.width, d.height);
        g2.setColor(Color.BLACK);
        g2.drawOval(MultiMuselytter.mouseX - (50 / 2), MultiMuselytter.mouseY - (50 / 2), 50, 50);
        //g2.drawImage(sprites.get(0), MultiMuselytter.mouseX-(sprites.get(0).getWidth()/2), 600, null);

    }

}
