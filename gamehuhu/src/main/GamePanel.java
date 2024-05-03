package main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utilz.Contants.PlayerConstants.*;
import static utilz.Contants.Direction.*;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    Game game;

    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
    }

    private void setPanelSize(){
        Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void updateGame(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){
        return game;
    }
}
