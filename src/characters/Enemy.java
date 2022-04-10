package characters;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.World;

public class Enemy extends DynamicBody {

    private static final BodyImage IDLE_IMAGE = new BodyImage("resources/sprites/enemy-idle.gif", 4);
    private static final BodyImage DIE_IMAGE = new BodyImage("resources/sprites/enemy-die.gif", 4);

    private int damage = 10;

    public Enemy(World world) {

        super(world, new BoxShape(2, 2));

        this.addImage(Enemy.IDLE_IMAGE).flipHorizontal();
    }

    public int getDamage() {

        return this.damage;
    }

    public void kill() {

        this.removeAllImages();
        this.addImage(Enemy.DIE_IMAGE).flipHorizontal();
        this.destroy();
    }
}
