package entities;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Contants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = RUNNING;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;
    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimations();
    }
    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y,98*2, 66*2, null);

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
        if(left && !right){
            x -= playerSpeed;
            moving = true;
        }
        else if(right && !left){
            x += playerSpeed;
            moving = true;
        }

        if(up && !down){
            y-= playerSpeed;
            moving = true;
        }
        else if(down && !up){
            y += playerSpeed;
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
