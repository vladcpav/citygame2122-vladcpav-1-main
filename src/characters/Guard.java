package characters;

import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Guard extends Enemy {

    public Guard(World world) {

        super(world, "resources/sprites/guard.gif", 10);
    }
}
