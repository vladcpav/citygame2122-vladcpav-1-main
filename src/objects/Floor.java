package objects;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

public class Floor extends StaticBody {

    public Floor(World world, float width, float height) {

        super(world, new BoxShape(width / 2, height / 2));

        this.addImage(new BodyImage("resources/backgrounds/ground.png", height));
    }
}
