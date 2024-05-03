package utilz;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "JoannaD'ArcIII-Sheet#1.png";
    public static final String TILE_SET_2 = "TileSet2.png";
    public static final String TILE_SET_1 = "TileSet1.png";
    public static final String LEVEL_1_DATA = "TileMap1.png";
    public static BufferedImage getSpriteAtlas(String fileName){
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        BufferedImage image = null;
        try {
            image = ImageIO.read(is);
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public static int[][] getLevelData(){
//        int[][] levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage image = getSpriteAtlas(LEVEL_1_DATA);
        System.out.println(image.getHeight() + " " + image.getWidth());
        int[][] levelData = new int[image.getHeight() + 10][image.getWidth() + 10];

        for(int i=0 ; i<image.getHeight() ; i++){
            for(int j=0 ; j<image.getWidth() ; j++){
                Color color = new Color(image.getRGB(j,i));
                int value = color.getRed();
                if(value >= 288) value = 0;
                levelData[i][j] = value;
            }
        }
        return levelData;
    }
}
