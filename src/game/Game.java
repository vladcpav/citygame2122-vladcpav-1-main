package game;

import characters.Player;

import city.cs.engine.*;
import levels.Level;
import levels.Level1;
import levels.Level2;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {

    private Level currentLevel;
    private EnhancedView view;
    private DebugViewer debug;

    public Game() {

        this.currentLevel = new Level1(this);
        this.view = new EnhancedView(this.currentLevel, 1000, 500);
        this.debug = new DebugViewer(this.currentLevel, 500, 500);

        JFrame frame = new JFrame("My game");
        frame.add(view);

        // Frame settings

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);               // enable the frame to quit the application when the x button is pressed
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();                                                       // size the frame to fit the world view
        frame.setVisible(true);

        // Listeners

        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

                Player player = Game.this.currentLevel.getPlayer();

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
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                Player player = Game.this.currentLevel.getPlayer();

                if ((e.getKeyCode() == 68 && player.isMovingForward())
                        || (e.getKeyCode() == 65 && player.isMovingBackwards())) {

                    player.stopMoving();
                    return;
                }

                if (e.getKeyCode() == 87) {
                    player.stopJumping();
                }
            }
        });

        this.currentLevel.start();
    }

    public void nextLevel() {

        if (this.currentLevel instanceof Level2) {
            System.out.println("Game over");
            System.exit(0);
            return;
        }

        if (this.currentLevel instanceof Level1) {
            this.currentLevel = new Level2(this);
        }

        this.view.setWorld(this.currentLevel);
        this.debug.setWorld(this.currentLevel);
        this.currentLevel.start();
    }

    public EnhancedView getView() {

        return this.view;
    }
}
