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
    void tegnSpil(List<BufferedImage> sprites) {                 // venter lidt, tegner og opdaterer koordinater
        
        g2.setTransform(orgTrans);               // genskab orig. transformation
        g2.setColor(Color.WHITE);                  // rens skærmen selv
        Dimension d = getSize();
        g2.fillRect(0, 0, d.width, d.height);
        g2.setColor(Color.BLACK);
        g2.drawOval(MultiMuselytter.mouseX - (50 / 2), MultiMuselytter.mouseY - (50 / 2), 50, 50);
        g2.drawImage(sprites.get(0), MultiMuselytter.mouseX-(sprites.get(0).getWidth()/2), 600, null);

    }

    void tegnMenu() {                 // venter lidt, tegner og opdaterer koordinater
        g2.setTransform(orgTrans);               // genskab orig. transformation
        g2.setColor(Color.WHITE);                  // rens skærmen selv
        Dimension d = getSize();
        g2.fillRect(0, 0, d.width, d.height);
        g2.setColor(Color.BLUE);

        //g2.drawOval( MultiMuselytter.mousePos.x -(100/2) , 100, 100, 100);
        for (int n = 0; n < koord.length; n++) {
            double k[] = koord[n];
            double t = k[0] = (k[0] + 0.005) % 1;    // opdater 'tid' k[0] og put i t
            g2.setTransform(orgTrans);             // genskab orig. transformation
            g2.translate((t * (k[1] - 0.5) + 0.5) * d.width, (t * (k[2] - 0.5) + 0.5) * d.height);
            g2.scale(t * k[3], t * (k[4] + t - 1));          // flyt, skaler og rotér
            g2.rotate(t * 50 * (k[5] - 0.5));
            g2.translate(-fig.getBounds().width / 2, -fig.getBounds().height / 2);
            g2.draw(fig);
        }
    }
    private int imgNumber = 0;

    void tegnLoading() {                 // venter lidt, tegner og opdaterer koordinater
        g2.setTransform(orgTrans);               // genskab orig. transformation
        g2.setColor(Color.WHITE);                  // rens skærmen selv
        Dimension d = getSize();
        g2.fillRect(0, 0, d.width, d.height);
        g2.setColor(Color.BLUE);
        if (imgNumber<100000) {
            //for (int i = 0; i < 100; i++) {//loading test
            try {

                BufferedImage TempImg = ImageIO.read(new File("SpaceShip.png"));
                //LoadedImages.add(TempImg);
                g2.drawString("Image " + imgNumber + "has been loaded", 10, 100);
                System.out.println("Image " + imgNumber + "has been loaded");
                imgNumber++;
            } catch (IOException e) {
                System.out.println("ERROR LOADING IMAGE");
            }
            try {
                Thread.sleep(0);
            } catch (Exception e) {
            }// vent lidt
            //}
        }else{g2.drawString("DONE!", 10, 100);}
    }

}
