package ui.scenes;

import game.characters.Player;

import city.cs.engine.*;
import game.levels.Level3;
import ui.Application;
import game.levels.Level;
import game.levels.Level1;
import game.levels.Level2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends BaseScene {

    private ArrayList<Level> levels = new ArrayList();
    private int level = -1;
    private UserView view;
    private DebugViewer debug;

    @Override
    protected void scaffold(JPanel panel, Application application) {

        this.levels.add(new Level1(this));
        this.levels.add(new Level2(this));
        this.levels.add(new Level3(this));

        this.view = new UserView(this.levels.get(0), application.getWidth(), application.getHeight());
        this.debug = new DebugViewer(this.levels.get(0), 500, 500);

        // Listeners

        application.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

                Player player = Game.this.levels.get(Game.this.level).getPlayer();

                if (e.getKeyCode() == 68) {
                    player.moveForward();
                    return;
                }

                if (e.getKeyCode() == 65) {
                    player.moveBackwards();
                    return;
                }

                if (e.getKeyCode() == 87) {
                    player.jump();
                    return;
                }

                if (e.getKeyCode() == 32) {
                    player.shoot();
                    return;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                Player player = Game.this.levels.get(Game.this.level).getPlayer();

                if ((e.getKeyCode() == 68 && player.isMovingForward())
                        || (e.getKeyCode() == 65 && player.isMovingBackwards())) {

                    player.stopMoving();
                    return;
                }

                if (e.getKeyCode() == 87) {
                    player.stopJumping();
                    return;
                }

                if (e.getKeyCode() == 32) {
                    player.stopShooting();
                    return;
                }
            }
        });

        // Music

        SoundClip bgMusic;
        try {
            bgMusic = new SoundClip("resources/sounds/background-music.wav");
            bgMusic.loop();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) {
            System.out.println(exception);
        }

        panel.add(this.view);
        this.view.setOpaque(false);
        this.nextLevel();
    }

    public void nextLevel() {

        if (this.level == this.levels.size() - 1) {
            System.out.println("Game over");
            System.exit(0);
            return;
        }

        this.level++;
        Level currentLevel = this.levels.get(this.level);

        this.view.setWorld(currentLevel);
        this.setBackgroundImage(currentLevel.getBackgroundImage());
        this.debug.setWorld(currentLevel);
        currentLevel.start();
    }

    public UserView getView() {

        return this.view;
    }
}
