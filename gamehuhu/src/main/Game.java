package main;

import entities.Player;
import levels.LevelManager;

import java.awt.*;
import java.io.IOException;

public class Game extends Thread{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    public Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private LevelManager levelManager;


    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 1.5f;
    public static final int TILES_IN_WIDTH = 50;
    public static final int TILES_IN_HEIGHT = 30;
    public static final int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    public static final int GAME_WIDTH = TILES_IN_WIDTH * TILES_IN_WIDTH;


    Player player;
    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        gameLoop();
    }

    public void initClasses(){
        player = new Player(200,200);
        levelManager = new LevelManager(this);
    }
    public void gameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void update(){
        player.update();
        levelManager.update();
    }
    public void render(Graphics g){
        player.render(g);
        levelManager.draw(g);
    }

    @Override
    public void run(){
        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;

        long previousTime = System.nanoTime();

        int frame = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(deltaU >= 1){
                update();
                deltaU--;
                updates++;
            }

            if(deltaF >= 1){
                gamePanel.repaint();
                frame++;
                deltaF--;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frame + " | UPS: " + updates);
                frame = 0;
                updates = 0;
            }
        }
    }
    public void windowFocusLost(){
        player.resetDir();
    }

    public Player getPlayer(){
        return player;
    }
}
