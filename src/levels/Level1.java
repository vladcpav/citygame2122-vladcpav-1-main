package levels;

import characters.Enemy;
import characters.Guard;
import game.Game;
import objects.Floor;
import org.jbox2d.common.Vec2;
import powerups.Health;
import powerups.Powerup;

public class Level1 extends Level {

    public Level1(Game game) {

        super(game);

        // Ground

        Floor ground = new Floor(this, 60, 1);
        ground.setPosition(new Vec2(0f, -11.5F));

        // Power up

        Powerup health = new Health(this);
        health.setPosition(new Vec2(-20, -10));

        // Setup portal

        this.portal.setPosition(new Vec2(30, -10));
    }
}
