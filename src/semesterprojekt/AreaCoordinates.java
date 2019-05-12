/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

/**
 *
 * @author FK
 */
public class AreaCoordinates {

    //TODO rykkes ind i AreaCoordinates for at lave en singleton, næst bedst metode
    static AreaCoordinates AC = new AreaCoordinates();

    private int windowTopOffset = 30;//toppen af vinduet (Title Bar )

    private int playableAreaX = 600;
    private int playableAreaY = 700;
    private int infoAreaX = 200;
    private int infoAreaY = 700;

    AreaCoordinates() {
    }

    public void setPlayableAreaX(int x) {
        playableAreaX = x;
    }

    public int getPlayableAreaX() {
        return playableAreaX;
    }

    public int getWindowTopOffset() {
        return windowTopOffset;
    }

    public void setWindowTopOffset(int WindowTopOffset) {
        this.windowTopOffset = WindowTopOffset;
    }

    public void setPlayableAreaY(int playableAreaY) {
        this.playableAreaY = playableAreaY;
    }

    public void setPlayableAreay(int y) {
        playableAreaY = y;
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
