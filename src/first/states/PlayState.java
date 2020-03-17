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

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        tm=new TileManager("tile/tilemap.tmx");

        font=new Font("font/Font.png",10,10);
        player=new Player(new Sprite("entity/linkFormatted.png"),new Vector2f(550,300),128);
    }


    public void update() {
        player.update();
    }


    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse,key);
    }


    public void render(Graphics2D g) {
        Sprite.drawArray(g,font, String.valueOf(GamePanel.oldFrameCount)+" :FPS",new Vector2f(50,25),32,32,16,0);
        player.render(g);
    }
}
