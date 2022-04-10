package objects;

import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Bullet extends DynamicBody {

    public Bullet(World world) {

        super(world, new CircleShape(2));

        this.addImage(new BodyImage("resources/sprites/player-bullet.gif", 4));
        this.setLinearVelocity(new Vec2(30, 0));
    }
}
