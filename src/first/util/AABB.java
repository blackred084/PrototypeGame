package first.util;

import first.entity.Entity;
import first.tiles.TileMapObj;
import first.tiles.blocks.Block;
import first.tiles.blocks.HoleBlock;

public class AABB {

    private Vector2f pos;
    private float xOffset;
    private float yOffset;
    private float w;
    private float h;
    private  float r;
    private int size;
    private Entity e;
    public AABB(Vector2f pos, int w, int h) {
        this.pos = pos;
        this.w = w;
        this.h = h;

        size = Math.max(w,h);
    }

    public AABB(Vector2f pos, int r, Entity e) {
        this.pos = pos;
        this.r=r;
        this.e=e;

        size=r;
    }
    public Vector2f getPos(){
        return pos;
    }
    public float getRadius(){
        return r;
    }
    public float getW(){
        return w;
    }
    public float getH(){
        return h;
    }

    public void setBox(Vector2f pos,int w,int h){
        this.pos=pos;
        this.w=w;
        this.h=h;

        size=Math.max(w,h);

    }

    public void setCircle(Vector2f pos,int r){
        this.pos=pos;
        this.r=r;
        size=r;
    }

    public void setWidth(float f){
        w=f;
    }
    public void setHeight(float f){
        h=f;
    }

    public void setXOffset(float f){xOffset=f;}

    public void setyOffset(float f) {
        yOffset =f;
    }
    public boolean collides(AABB bBox){
        float ax=((pos.getWorldVar().x+(xOffset))+(w/2));
        float ay=((pos.getWorldVar().y+(yOffset))+(h/2));
        float bx=((bBox.pos.getWorldVar().x+(bBox.xOffset/2))+(w/2));
        float by=((bBox.pos.getWorldVar().y+(bBox.yOffset/2))+(h/2));

        if(Math.abs(ax-bx)<(this.w/2)+(bBox.w/2)){
            if(Math.abs(ay-by)<(this.h/2)+(bBox.h/2)){
                return true;
            }
        }
        return false;
    }

    public boolean colCirclesBox(AABB aBox){
        float cx=(float)(pos.getWorldVar().x+r/Math.sqrt(2)-e.getSize()/Math.sqrt(2));
        float cy=(float)(pos.getWorldVar().y+r/Math.sqrt(2)-e.getSize()/Math.sqrt(2));

        float xDelta=cx-Math.max(aBox.pos.getWorldVar().x+(aBox.getW()/2),Math.min(cx,aBox.pos.getWorldVar().x));
        float yDelta=cy-Math.max(aBox.pos.getWorldVar().y+(aBox.getW()/2),Math.min(cy,aBox.pos.getWorldVar().y));

        if(((xDelta*xDelta)+(yDelta*yDelta)<((this.r/Math.sqrt(2))*(this.r/Math.sqrt(2)))))
        {
            return true;
        }
        return false;
    }

    public boolean collisionTile(float ax, float ay) {
        if (TileMapObj.twoBlocks != null) {
            int xt;
            int yt;

            for (int c = 0; c < 4; c++) {

                xt = (int) ((e.getPos().x + ax) + (c % 2) * e.getBounds().getWidth() + e.getBounds().getXOffset()) / 64;
                yt = (int) ((e.getPos().y + ay) + (c / 2) * e.getBounds().getHeight() + e.getBounds().getYOffset()) / 64;

                if (xt <= 0 || yt <= 0 || xt + (yt * TileMapObj.height) < 0 || xt + (yt * TileMapObj.height) > (TileMapObj.height * TileMapObj.width) - 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private float getYOffset() {
        return yOffset;
    }

    private float getXOffset() {
        return xOffset;
    }

    private float getHeight() {
        return h;
    }

    private float getWidth() {
        return w;
    }

}
