/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Rectangle;


import javax.swing.JFrame;

/**
 *
 * @author Adam
 */
public class SemesterProjekt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Rectangle hej = new Rectangle(10,10,10,10);
        Rectangle hej2 = new Rectangle(10,10,10,10);
        
        if(hej.intersects(hej2)){
        
            System.out.println("av!!");
        
        }else{
        
        System.out.println(" Pew godt der ikke skete noget ");
            
        }
        
        // TODO code application logic here
        SpilVindue panel = new SpilVindue();        // opret panelet

        JFrame vindue = new JFrame("Billet Automaten");    // opret et vindue på skærmen
        vindue.add(panel);                          // vis panelet i vinduet
        vindue.setSize(500, 500);
        vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // reagér på luk
        //vindue.pack();                       // sæt vinduets størrelse
        vindue.setResizable(false);
        vindue.setVisible(true);// åbn vinduet*/
        
    }
    
}
