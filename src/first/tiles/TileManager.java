package first.tiles;

import first.graphic.Sprite;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class TileManager {

    public static ArrayList<TileMap> tm;
    public TileManager() {
        tm=new ArrayList<TileMap>();
    }

    public TileManager(String path){
        tm=new ArrayList<TileMap>();
        addTileMap(path,50,50);
    }

    private void addTileMap(String path,int blokWidth,int blockHeight){
        String imagePath;

        int width=0;
        int height=0;
        int tileWidth;
        int tileHeight;
        int tileCount;
        int tileColumns;
        int layers=0;
        Sprite sprite;
        String imagePathTSX;
//        System.out.println(path);
        String[] data=new String[10];
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("tileset");
            Node node = list.item(0);
            Element eElement = (Element) node;

            imagePathTSX = eElement.getAttribute("name");
            imagePath = imagePathTSX.substring(0, imagePathTSX.length() - 4);

            tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
            tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));

            sprite = new Sprite("tile/" + imagePath + ".png", tileHeight,tileWidth);

            tileColumns = sprite.getSpriteSheetWidth() / tileWidth;
            tileCount = tileColumns * (sprite.getSpriteSheetHeight() / tileHeight);
//
//            sprite = new Sprite("tile/" + imagePath + ".png", tileWidth, tileHeight);
            list = doc.getElementsByTagName("layer");
            layers = list.getLength();

            for(int i = 0; i < layers; i++) {
                node = list.item(i);

                eElement = (Element) node;
                if (i <= 0) {

                    width = Integer.parseInt(eElement.getAttribute("width"));
                    height = Integer.parseInt(eElement.getAttribute("height"));
                }

                data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();

                if(i>=1){
                    tm.add(new TileMaopNorm(data[i],sprite,width,height,blokWidth,blockHeight,tileColumns));
            System.out.println("Wrrrrrrr");
                }else {
                    tm.add(new TileMapObj(data[i], sprite, width, height, blokWidth, blockHeight, tileColumns));
//                    tm.add(new TileMapObj(data[i],sprite,width,height,blokWidth,blockHeight,tileColumns));
                }
            }
        }catch (Exception e){
            e.getMessage();
            System.err.println("Error in tiles try catch "+e);
        }

    }

    public void render(Graphics2D g){
        for(int i=0;i<tm.size();i++){
            tm.get(i).render(g);
        }
    }

}
