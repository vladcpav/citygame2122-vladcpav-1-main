package game.objects;

import city.cs.engine.*;

public class Portal extends StaticBody {

    private Sensor sensor;

    public Portal(World level) {

        super(level);

        this.addImage(new BodyImage("resources/sprites/grunt-portal.png", 4));
        this.sensor = new Sensor(this, new BoxShape(2, 2));
    }

    public Sensor getSensor() {

        return this.getSensor();
    }
}
