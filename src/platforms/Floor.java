package platforms;

import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

public class Floor extends StaticBody {

    public Floor(World world) {

        super(world, new BoxShape(30, 0.5f));
    }
}
