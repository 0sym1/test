package levels;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {
    Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;
    public LevelManager(Game game){
        this.game = game;
        //levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
        levelOne = new Level(LoadSave.getLevelData());
    }

    public void importOutsideSprites(){
        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.TILE_SET_1);
        levelSprite = new BufferedImage[288];

        for(int i=0 ; i<16 ; i++){
            for(int j=0 ; j<18 ; j++){
                int index = i*18 + j;
                levelSprite[index] = image.getSubimage(j*16,i*16, 16, 16);
            }
        }
    }

    public void draw(Graphics g){
        for(int i=0 ; i<Game.TILES_IN_HEIGHT ; i++){
            for(int j=0 ; j<Game.TILES_IN_WIDTH ; j++){
                int index = levelOne.getSpriteIndex(j,i);
                if(index > 0) g.drawImage(levelSprite[index - 1], Game.TILES_SIZE*j, Game.TILES_SIZE*i, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
        }
        g.drawImage(levelSprite[2], 0, 0, null);
    }
    public void update(){

    }
    public Level getCurrentLevel(){
        return levelOne;
    }
}
