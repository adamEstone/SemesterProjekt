/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Font;
import java.awt.Point;
import java.awt.Graphics2D;

/**
 *
 * @author FK
 */
public class AreaCoordinates {

    //rykkes ind i AreaCoordinates for at lave en singleton, næst bedst metode
    static AreaCoordinates AC = new AreaCoordinates();

    private int WindowTopOffset = 30;//toppen af vinduet (Title Bar )

    private int playableAreaX = 600;
    private int playableAreaY = 700;
    private int infoAreaX = 230;
    private int infoAreaY = 700;
    
    Graphics2D g2;

    
    //menu
    //300
    private final int menuWidth = 300;
    //400
    private final int menuHeight = 400;

    //150,100
    private final Point menuLocation = new Point(
            (playableAreaX + infoAreaX) / 3,
            playableAreaY / 4);

    
    //200
    private final int menuButtonWidth = (playableAreaX + infoAreaX) / 4;
    //50
    private final int menuButtonHeight = 50;
    
    
    public int getMenuWidth(){
        return menuWidth;
    }
    
    public int getMenuHeight(){
        return menuHeight;
    }
    
    //TODO fix
    public int getStrLocX(String str){
        /*System.out.println("FEJL 0");
        int foo = menuLocation.x + (menuWidth / 2);
        System.out.println("FEJL 1");
        //int bar = g2.getFontMetrics().stringWidth(str) / 2;
        
        //g2.setFont(new Font("Arial", Font.BOLD, 50));
        String bar = g2.getFontMetrics(new Font("Arial", Font.BOLD, 50)).toString();
        
        System.out.println(bar);
        System.out.println("FEJL 2");
        int baz = foo;
        System.out.println("FEJL 3");
        return baz;*/
        
        int foo = menuLocation.x + (menuWidth / 2) - 65;
        return foo;
    }
    
    public int getStrLocY(int offset){
        return menuLocation.y + offset;
    }
    
    public int getA(){
        return menuLocation.x + (menuWidth / 2) - (menuButtonWidth / 2);
    }
    
    public int getB(int offset){
        return menuLocation.y + 100 + offset;
    }
    
    public int getMenuButtonWidth(){
        return menuButtonWidth;
    }
    
    public int getMenuButtonHeight(){
        return menuButtonHeight;
    }
    
    public int getMenuLocX(){
        return menuLocation.x;
    }
    
    public int getMenuLocY(){
        return menuLocation.y;
    }
    
    
    public void setPlayableAreaX(int x) {
        playableAreaX = x;
    }

    public int getPlayableAreaX() {
        return playableAreaX;
    }

    public int getWindowTopOffset() {
        return WindowTopOffset;
    }

    public void setWindowTopOffset(int WindowTopOffset) {
        this.WindowTopOffset = WindowTopOffset;
    }

    public void setPlayableAreaY(int playableAreaY) {
        this.playableAreaY = playableAreaY;
    }

    public int getPlayableAreaY() {
        return playableAreaY;
    }

    public void setInfoAreaX(int x) {
        infoAreaX = x;
    }

    public int getInfoAreaX() {
        return infoAreaX;
    }

    public void setInfoAreaY(int y) {
        infoAreaY = y;
    }

    public int getInfoAreaY() {
        return infoAreaY;
    }
}
