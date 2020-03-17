package first.graphic;

import first.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Font {
    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TITLE_SIZE = 32;
    public int w;
    public int h;
    private int wLetter;
    private int hLetter;

    public Font(String file) {
        w = TITLE_SIZE;
        h = TITLE_SIZE;

        System.out.println("Loading: " + file + " ...");
        FONTSHEET = loadFont(file);
        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadFontArray();
    }

    public Font(String file, int w, int h) {
        this.w = w;
        this.h = h;
        System.out.println("Loading: " + file + " ...");
        FONTSHEET = loadFont(file);

        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadFontArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public void setHeight(int height) {
        h = height;
        hLetter = FONTSHEET.getHeight() / h;
    }

    public void setWidth(int width) {
        w = width;
        wLetter = FONTSHEET.getWidth() / w;
    }

    private BufferedImage loadFont(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));

        } catch (Exception e) {
            e.getMessage();
            System.err.println("ERROR coudl not load the fule " + file);
        }
        return sprite;
    }


    public void loadFontArray() {
        spriteArray = new BufferedImage[wLetter][hLetter];
        for (int i = 0; i < wLetter; i++) {
            for (int y = 0; y < hLetter; y++) {
                spriteArray[i][y] = getLetter(i, y);
            }
        }
    }

    public BufferedImage getFONTSHEET() {
        return FONTSHEET;
    }

    public BufferedImage getLetter(int x, int y) {
        return FONTSHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getFont(char letter) {
        int value = letter;

        int x = value % wLetter;
        int y = value / wLetter;

        return getLetter(x,y);
    }


}
