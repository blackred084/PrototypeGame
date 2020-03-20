package first.entity;

import first.graphic.Animation;
import first.graphic.Sprite;
import first.util.AABB;
import first.util.KeyHandler;
import first.util.MouseHandler;
import first.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private final int RIGHT = 0;
    private final int LEFT = 1;
    private final int DOWN = 2;
    private final int UP = 3;
    private final int FALLEN=4;
    protected int currentAnimation;

    protected Animation ani;
    protected Sprite sprite;
    protected Vector2f pos;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected boolean fallen;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    protected float maxSpeed=4f;
    protected float acc=3f;
    protected float deacc=0.5f;

    protected AABB hitBounds;
    protected AABB bounds;


    public Entity(Sprite sprite, Vector2f origin, int size) {
        this.sprite = sprite;
        pos = origin;
        this.size = size;

        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(new Vector2f(origin.x + (size / 2), origin.y), size, size);


        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setSize(int i) {
        size = i;
    }

    public void setMaxSpeed(float i) {
        maxSpeed = i;
    }

    public Animation getAnimation() {
        return ani;
    }

    public void setAcc(float f) {
        acc = f;
    }

    public void setDeacc(float f) {
        deacc = f;
    }

    public AABB getBounds() {
        return bounds;
    }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(frames,0);
        ani.setDelay(delay);
    }

    public void animate() {
        if (up) {
            if (currentAnimation != UP || ani.getDelay() == -1) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        } else if (down) {
            if (currentAnimation != DOWN || ani.getDelay() == -1) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        } else if (left) {
            if (currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        } else if (right) {
            if (currentAnimation != RIGHT || ani.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        }else if(fallen){
            if(currentAnimation!=FALLEN||ani.getDelay()==-1){
                setAnimation(FALLEN,sprite.getSpriteArray(FALLEN),5);
            }

        }
            else{
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    public void update() {
        animate();
        setHitboxDirection();
        ani.update();
    }

    private void setHitboxDirection() {
        if (up) {
            hitBounds.setyOffset(-size / 2);
            hitBounds.setXOffset(-size / 2);
        } else if (down) {
            hitBounds.setyOffset(-size / 2);
            hitBounds.setXOffset(-size / 2);
        } else if (right) {
            hitBounds.setyOffset(0);
            hitBounds.setXOffset(0);
        } else if (left) {
            hitBounds.setyOffset(-size);
            hitBounds.setXOffset(0);
        }
    }

    public abstract void render(Graphics2D g);

    public void input(KeyHandler key, MouseHandler mouse) {
    }

    public int getSize() {
        return size;
    }


}
