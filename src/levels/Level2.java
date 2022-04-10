package levels;

import characters.Enemy;
import scenes.Game;
import objects.Floor;
import org.jbox2d.common.Vec2;
import powerups.Health;
import powerups.Powerup;

public class Level2 extends Level {

    public Level2(Game game) {

        super(game, "resources/backgrounds/background-cave.png");

        // PLayer positioning

        this.player.setPosition(new Vec2(0, 8));

        // Ground

        Floor ground = new Floor(this, 200, 12);
        ground.setPosition(new Vec2(0, 0));

        // Enemies

        Enemy guard = new Enemy(this);
        guard.setPosition(new Vec2(30, 8));

        // Power up

        Powerup health = new Health(this);
        health.setPosition(new Vec2(20, 8));

        // Setup portal

        this.portal.setPosition(new Vec2(50, 8));

    }
}

