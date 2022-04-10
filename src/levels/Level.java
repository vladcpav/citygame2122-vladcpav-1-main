package levels;

import characters.Player;
import city.cs.engine.*;
import scenes.Game;
import objects.Portal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Level extends World {

    private Game game;
    protected Portal portal;
    protected Player player;
    private StepListener listener;
    private BufferedImage backgroundImage;

    Level(Game game, String backgroundPath) {

        super();

        try {
            this.backgroundImage = ImageIO.read(new File(backgroundPath));
        }
        catch (IOException exception) {
            System.out.println(exception);
            this.backgroundImage = null;
        }

        this.game = game;
        this.portal = new Portal(this);
        this.player = new Player(this);
        this.listener = new Level.WorldStepListener();

        this.addStepListener(this.listener);
    }

    public void finish() {

        this.removeStepListener(this.listener);

        for (StaticBody body: this.getStaticBodies()) {
            body.destroy();
        }

        for (DynamicBody body: this.getDynamicBodies()) {
            body.destroy();
        }

        this.game.nextLevel();
    }

    public Player getPlayer() {

        return this.player;
    }

    public BufferedImage getBackgroundImage() {

        return this.backgroundImage;
    }

    private class WorldStepListener implements StepListener {

        @Override
        public void preStep(StepEvent stepEvent) {
        }

        @Override
        public void postStep(StepEvent stepEvent) {

            // Move player

            Level.this.player.update();

            // Follow player position

            Level.this.game.getView().setCentre(player.getPosition());
        }
    }
}
