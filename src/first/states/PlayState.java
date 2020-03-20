package first.states;

import first.GamePanel;
import first.entity.Player;
import first.graphic.Font;
import first.graphic.Sprite;
import first.tiles.TileManager;
import first.util.KeyHandler;
import first.util.MouseHandler;
import first.util.Vector2f;

import java.awt.*;

public class PlayState extends GameState {


    private Font font;

    private Player player;

    private TileManager tm;

    public static Vector2f map;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);


        tm = new TileManager("tile/tilemap.tmx");

        font = new Font("font/Font.png", 10, 10);
        player = new Player(new Sprite("entity/linkFormatted.png"), new Vector2f(0 + (GamePanel.width / 2) - 32, 0 + (GamePanel.height / 2) - 32), 64);
    }


    public void update() {
        Vector2f.setWorldVar(map.x,map.y);
        player.update();

    }


    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
    }


    public void render(Graphics2D g) {
        tm.render(g);
        Sprite.drawArray(g, font, String.valueOf(GamePanel.oldFrameCount) + " :FPS", new Vector2f(50, 25), 32, 32, 16, 0);
        player.render(g);
    }
}
