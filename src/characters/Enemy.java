package characters;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Enemy extends DynamicBody {

    private int damage = 10;

    public Enemy(World world) {

        super(world, new BoxShape(2, 2));

        this.addImage(new BodyImage("resources/sprites/enemy-idle.gif", 4));
    }

    public int getDamage() {

        return this.damage;
    }
}
