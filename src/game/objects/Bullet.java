package game.objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Bullet extends DynamicBody {

    protected float baseHSpeed = 80;

    protected Sensor sensor;

    public Bullet(World world, int direction, float damage, String spritePath) {

        super(world);

        AttachedImage image = this.addImage(new BodyImage(spritePath, 2));

        if (direction == -1) {
            image.flipHorizontal();
        }

        this.setLinearVelocity(new Vec2(this.baseHSpeed  * direction, 0));
        this.setGravityScale(0);

        this.sensor = new Sensor(this, new CircleShape(1));
    }
}
