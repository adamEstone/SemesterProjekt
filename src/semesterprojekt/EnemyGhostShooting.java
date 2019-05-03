/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Adam
 */
public class EnemyGhostShooting extends Enemy {

    int spriteID = 1;

    private int weapon = 1;
    private double lives = 1;

    EnemyGhostShooting(int Xpos_in, int Ypos_in) {
        System.out.println("WOWOWOOWOWOWOWOW");
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;

    }

    EnemyGhostShooting(int Xpos_in, int Ypos_in, double Lives_in, int Weapon_in) {
        this.xPos = Xpos_in;
        this.yPos = Ypos_in;
        this.lives=Lives_in;
        this.weapon = Weapon_in;
    }

    @Override
    public void move() {//ryk fjende

    }

    @Override
    public void shoot() {//ryk fjende

    }

    @Override
    public Graphics2D draw(Graphics2D a) {//ryk fjende
        a.drawImage(ResourceClass.LoadedSprites.get(spriteID), this.xPos, this.yPos, null);
        return a;
    }

}
