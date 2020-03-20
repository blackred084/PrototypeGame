package first.entity;

import first.GamePanel;
import first.graphic.Sprite;
import first.states.PlayState;
import first.util.KeyHandler;
import first.util.MouseHandler;
import first.util.Vector2f;

import java.awt.*;

public class Player extends Entity {

    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        acc = 2f;
        maxSpeed = 3f;
    }

    public void move() {
        if (up) {
//            currentDirection = UP;
            dy -= acc;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }

        if (down) {
//            currentDirection = DOWN;
            dy += acc;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }

        if (left) {
//            currentDirection = LEFT;
            dx -= acc;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }

        if (right) {
//            currentDirection = RIGHT;
            dx += acc;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    private void resetPosition() {
        System.out.println("Reseting Player...");
        pos.x = GamePanel.width / 2 - 32;
        PlayState.map.x = 0;

        pos.y = GamePanel.height / 2 - 32;
        PlayState.map.y = 0;

    }

    public void update() {
        super.update();
        if (!fallen) {
            move();
            if (!bounds.collisionTile(dx, 0)) {
                PlayState.map.x += dx;
                pos.x += dx;
            }
            if (!bounds.collisionTile(0, dy)) {
                PlayState.map.y += dy;
                pos.y += dy;
            }
        } else {
            if (ani.hasPlayedOnce()) {
                resetPosition();
                fallen = false;
            }
        }
    }


    @Override
    public void render(Graphics2D g) {
        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);

    }

    public void input(MouseHandler mouse, KeyHandler key) {

        if (mouse.getButton() == 1) {
            System.out.println("Player:  " + pos.x + ", " + pos.y);
        }
        up = key.up.down;
        down = key.down.down;
        left = key.left.down;
        right = key.right.down;
        attack = key.attack.down;

        if (!fallen) {
            if (up) {
                up = true;
            } else up = false;
            if (down) {
                down = true;
            } else {
                down = false;
            }
            if (left) {
                left = true;
            } else {
                left = false;
            }
            if (right) {
                right = true;

            } else right = false;

            if (attack) {
                attack = true;

            } else attack = false;
        }else {
            up=false;
            down=false;
            right=false;
            left=false;
        }

    }
}
