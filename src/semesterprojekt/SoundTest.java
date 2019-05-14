/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.util.concurrent.TimeUnit;
import java.io.*;
import java.util.*;

/**
 *
 * @author Mads
 */
public class SoundTest {

    private SoundPlayer backgroundMusic;

    public static void main(String[] args) {

        new SoundTest().run();
    }

    public void run() {

        Scanner keyboard = new Scanner(System.in);

        backgroundMusic = new SoundPlayer("LaserGun1.wav");
        char i = 0;

        while (true) {

            i = keyboard.next().charAt(0);

            if (i == 'a') {

                backgroundMusic.play(0);

            }

            if (i == 'b') {

                backgroundMusic.stop();

            }

            if (i == 'p') {

                backgroundMusic.pause();

            }

            if (i == 'r') {

                backgroundMusic.resume();

            }

        }

    }
}
