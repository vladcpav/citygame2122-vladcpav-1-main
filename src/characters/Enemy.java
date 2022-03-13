package characters;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Enemy extends DynamicBody {

    private int damage;

    Enemy(World world, String spritePath, int damage) {

        super(world, new BoxShape(4, 4));

        this.addImage(new BodyImage(spritePath, 8));

        this.damage = damage;
    }

    public int getDamage() {

        return this.damage;
    }
}
