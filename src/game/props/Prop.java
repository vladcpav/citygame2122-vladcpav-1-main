package game.props;

import city.cs.engine.*;

class Prop extends StaticBody {

    Prop(World world, String spritePath, int size) {

        super(world);

        new GhostlyFixture(this, new BoxShape(size / 2, size / 2));
        this.addImage(new BodyImage(spritePath, size));
    }
}
