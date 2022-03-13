package powerups;

import city.cs.engine.*;

public class Powerup extends StaticBody {

    protected Sensor sensor;

    Powerup(World world, String spritePath) {

        super(world);

        this.addImage(new BodyImage(spritePath, 8));
        this.sensor = new Sensor(this, new BoxShape(4, 4));
    }
}
