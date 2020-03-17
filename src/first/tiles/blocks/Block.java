package first.tiles.blocks;

import first.util.AABB;
import first.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
     protected  int w;
     protected int h;

     protected BufferedImage img;
     protected Vector2f pos;


    public Block( BufferedImage img,Vector2f pos,int w, int h) {
        this.w = w;
        this.h = h;
        this.img = img;
        this.pos = pos;
    }

    public abstract boolean update(AABB p);
    public void  render(Graphics2D g){
        g.drawImage(img,(int)pos.getWorldVar().x,(int)pos.getWorldVar().y,w,h,null);
    }
}
