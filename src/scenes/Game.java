package scenes;

import characters.Player;

import city.cs.engine.*;
import levels.Level3;
import views.EnhancedView;
import levels.Level;
import levels.Level1;
import levels.Level2;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game {

    private ArrayList<Level> levels = new ArrayList<Level>();
    private int level = 0;
    private EnhancedView view;
    private DebugViewer debug;

    public Game() {

        this.levels.add(new Level1(this));
        this.levels.add(new Level2(this));
        this.levels.add(new Level3(this));

        this.view = new EnhancedView(this.levels.get(0), 1000, 500);
        this.debug = new DebugViewer(this.levels.get(0), 500, 500);

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
                }
            }
        });

        this.levels.get(0).start();
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
        this.debug.setWorld(currentLevel);
        currentLevel.start();
    }

    public EnhancedView getView() {

        return this.view;
    }
}
