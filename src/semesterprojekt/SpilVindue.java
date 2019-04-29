/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.MemoryImageSource;

/**
 *
 * @author Adam
 */
public class SpilVindue extends javax.swing.JPanel {

    @Override
    public Dimension getSize() {
        return super.getSize(); //To change body of generated methods, choose Tools | Templates.
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    int Xpos = 0; //indeholder x for mus
    int Ypos = 0; //indeholder y for mus
 boolean Dragged = false;
    
    public SpilVindue() {
        initComponents();
        //setBackground(Color.BLACK);
        hideCursor();
        		
		this.setIgnoreRepaint(true);      // system skal IKKE kalde paintComponent()
		this.setVisible(true);

		
    }

    boolean inGame = true;

    public void gamelogic() {
        System.out.println("entered logic!!!!!11");
        while (inGame) {
            this.repaint();
            System.out.println("loop");
        }

    }

    @Override
    public void paintComponent(Graphics g) {

        // Herunder referer g til et Graphics-objekt man kan tegne med
        super.paintComponent(g);        // tegn først baggrunden på panelet

        /*for (Object object : col) {//draw rutine for all objects on screen
        }*/
        
        //draw player
        PlayerShip PlayerSpaceship = new PlayerShip(50, 50, "SpaceShip.png");
        
        //g.clearRect(10, 10, 50, 50);
        
        g.drawImage(PlayerSpaceship.getImage(),Xpos, this.getHeight() - 50, PlayerSpaceship.getSize() - 5, PlayerSpaceship.getSize() - 5, this);

        g.setColor(Color.GREEN);
        g.fillRect(10, 10, 50, 50); //enemy

        //g.setColor(Color.RED);
        if(Dragged){ g.fillRect(300, 300, 25, 25);} //player

        g.setColor(Color.WHITE);
        g.drawLine(Xpos, this.getHeight() - 50, 50, 50);//seight

        g.setColor(Color.RED);

        g.drawString(String.valueOf(Xpos), 20, 50);

        //System.out.println("Der blev tegnet!!");

    }

    private void hideCursor() {
        // http://www.java2s.com/Code/Java/2D-Graphics-GUI/HidethemousecursoruseatransparentGIFasthecursor.htm
        int[] pixels = new int[16 * 16];
        Image image = Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(16, 16, pixels, 0, 16));
        Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                image, new Point(0, 0), "invisibleCursor");

        setCursor(transparentCursor);

        repaint();
    }

    private void showCursor() {
        setCursor(Cursor.getDefaultCursor());
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        jProgressBar1.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar1.setValue(69);
        jProgressBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(268, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
       
        
        Xpos = evt.getX();
        Ypos = evt.getY();
        
        repaint();


        evt.consume();
    }//GEN-LAST:event_formMouseMoved

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        
        Xpos = evt.getX();
        Ypos = evt.getY();
        
        repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
       Dragged=true; // TODO add your handling code here:
       repaint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        Dragged=false;        // TODO add your handling code here:
        repaint();
    }//GEN-LAST:event_formMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
