/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

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
    
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //--------- OPRETTELSE AF SPILVINDUE ----------------
//        SpilVindue panel = new SpilVindue();        // opret panelet
        AktivVisning vindue = new AktivVisning();    // opret et vindue på skærmen
        //vindue.add(panel);                          // vis panelet i vinduet
        vindue.setSize(800, 800);
        vindue.setResizable(false);
        vindue.init();
       // vindue.setDefaultCloseOperation(vindue.EXIT_ON_CLOSE);
       
        //-------------- INPUT FRA BRUGER -------------------
        MultiMuselytter lytter = new MultiMuselytter();
        vindue.addMouseListener(lytter);
        vindue.addMouseMotionListener(lytter);

        Tastetryk tast = new Tastetryk();
        vindue.addKeyListener(tast);
        //--------------------------------------------------
        
        //opretter selve spillet i det vindue som er parrameter 
        GameLogic myGame = new GameLogic(vindue); 
        
    }

    public static void initFuldskærm(Frame vindue) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice dev = env.getDefaultScreenDevice();
        vindue.setUndecorated(true); // vis ikke vinduesramme, titel, luk-knap etc.
        dev.setFullScreenWindow(vindue);
    }

}
