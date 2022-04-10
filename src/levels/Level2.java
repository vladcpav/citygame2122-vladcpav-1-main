package levels;

import characters.Enemy;
import objects.Tree;
import scenes.Game;
import objects.Floor;
import org.jbox2d.common.Vec2;
import powerups.Health;
import powerups.Powerup;

import java.util.Random;

public class Level2 extends Level {

    public Level2(Game game) {

        super(game, "resources/backgrounds/background-cave.png");

        // PLayer positioning

        this.player.setPosition(new Vec2(0, 8));

        // Ground

        Floor ground = new Floor(this, 200, 12);
        ground.setPosition(new Vec2(0, 0));

        // Trees

        Random ran = new Random();

        for (int i = 0; i < 6; i++) {
            Tree tree = new Tree(this);
            tree.setPosition(new Vec2(ran.nextFloat(80) - 20, 12));
        }

        // Enemies

        for (int i = 0; i < 8; i++) {
            Enemy enemy = new Enemy(this);
            enemy.setPosition(new Vec2(ran.nextFloat(60) + 30, 8));
        }

        // Power up

        Powerup health = new Health(this);
        health.setPosition(new Vec2(20, 8));

        // Setup portal

        this.portal.setPosition(new Vec2(100, 8));

    }
}

