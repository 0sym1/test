package entities;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Contants.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = RUNNING;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;
    private int[][] levelData;
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    public void update(){
        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimations();
    }
    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y,98*2, 66*2, null);
        drawHitbox(g);
    }
    private void loadAnimations() {

        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][37];
        for(int i=0 ; i<animations.length ; i++){
            for(int j=0 ; j<animations[i].length ; j++){
                animations[i][j] = image.getSubimage(j*98, i*66, 98, 66);
            }
        }
    }
    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;
    }
    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }
    public void setAnimations(){
        int startAnimation = playerAction;

        if(moving) playerAction = WALKING;
        else playerAction = IDLE;

        if(attacking) playerAction = JUMPING;

        if(startAnimation != playerAction){
            resetAnimationTick();
        }
    }

    public void resetAnimationTick(){
        aniTick = 0;
        aniIndex = 0;
    }

    public void updatePos(){
        moving = false;
        if(!left && !right && !up && !down) return;

        float xSpeed = 0, ySpeed = 0;
        if(left && !right){
            xSpeed = -playerSpeed;
        }
        else if(right && !left){
            xSpeed = playerSpeed;
        }
        if(up && !down){
            ySpeed = -playerSpeed;
        }
        else if(down && !up){
            ySpeed = playerSpeed;
        }

        if(CanMoveHere(x+xSpeed, y+ySpeed, width, height, levelData)){
            x += xSpeed;
            y += ySpeed;
            moving = true;
        }
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }
    private void updateAnimationTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
            }
        }
    }
    public void resetDir(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
