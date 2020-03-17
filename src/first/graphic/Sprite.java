package first.graphic;

import first.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {
    private BufferedImage SPRITESHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TITLE_SIZE = 32;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (Exception e) {
            e.getMessage();
            System.err.println("ERROR coudl not load the fule " + file);
        }
        return sprite;
    }

    public Sprite(String file) {
        w = TITLE_SIZE;
        h = TITLE_SIZE;

        System.out.println("Loading: " + file + " ...");
        SPRITESHEET = loadSprite(file);
        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        loadSpriteArray();
    }
    public Sprite(String file, int w, int h) {
        this.w = w;
        this.h = h;
        System.out.println("Loading: " + file + " ...");
        SPRITESHEET = loadSprite(file);

        wSprite = SPRITESHEET.getWidth() / w;
        hSprite = SPRITESHEET.getHeight() / h;
        loadSpriteArray();
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
        hSprite = SPRITESHEET.getHeight() / h;
    }

    public void setWidth(int width) {
        w = width;
        wSprite = SPRITESHEET.getWidth() / w;
    }


    public void loadSpriteArray() {
        spriteArray = new BufferedImage[hSprite][wSprite];

        for (int y = 0; y < hSprite; y++) {
            for (int i = 0; i < wSprite; i++) {
                spriteArray[y][i] = getSprite(i, y);
            }
        }
    }

    public BufferedImage getSPRITESHEET() {
        return SPRITESHEET;
    }

    public BufferedImage getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    public BufferedImage[][] getSpriteArray2(int i) {
        return spriteArray;
    }

    public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                g.drawImage(img.get(i), (int) x, (int) y, width, height, null);

            }
            x += xOffset;
            y += yOffset;
        }
    }

    public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32) {
                g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
                x += xOffset;
                y += yOffset;

            }

        }

    }

    public int getSpriteSheetWidth() { return SPRITESHEET.getWidth(); }
    public int getSpriteSheetHeight() { return SPRITESHEET.getHeight(); }

}
