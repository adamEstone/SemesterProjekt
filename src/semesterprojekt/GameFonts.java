/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.awt.Font;
import java.io.File;
/**
 *
 * @author FK
 */
public class GameFonts
{
    private String fontSti = "src/semesterprojekt/Fonts"; //mappe stien med fonts
    private Font myFont;

    
    public Font LoadFont(String fontName, int size){
        try
        {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File (fontSti + "/" + fontName));
            myFont = font.deriveFont(Font.PLAIN, size); //40, 25
            return myFont;
        } catch (Exception e)
        {
            System.out.println("The font couldn't be loaded.");
            e.printStackTrace();
        }
        Font f = new Font("default", Font.PLAIN, 10);
        return f;
    }
}
