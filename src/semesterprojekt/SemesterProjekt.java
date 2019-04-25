/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

/*
TheImage = Toolkit.getDefaultToolkit().getImage(NameOfPicture);
        for (Coin element : allCoins) {

       

        }
 */
/**
 *
 * @author Adam
 */
public class SemesterProjekt {

    int a = 5;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        SpilVindue panel = new SpilVindue();        // opret panelet

        JFrame vindue = new JFrame("Nose wars");    // opret et vindue på skærmen
        vindue.add(panel);                          // vis panelet i vinduet
        vindue.setSize(800, 800);
        vindue.getHeight();
        vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // reagér på luk
        //vindue.pack();                       // sæt vinduets størrelse
        vindue.setResizable(false);
        vindue.setVisible(true);// åbn vinduet*/

        Rectangle hej = new Rectangle(10, 10, 10, 10);
        Rectangle hej2 = new Rectangle(10, 10, 10, 10);
        double x = 0;
        
        while (true) {
            vindue.repaint();
            x+=0.005;
            Math.sin(x);
            System.out.println(x);
            if (hej.intersects(hej2)) {

                System.out.println("av!!");

            } else {

                System.out.println(" Pew godt der ikke skete noget ");

            }
            
                        panel.repaint();                               // gentegn skærm
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }// vent lidt
            
        }

    }

}
