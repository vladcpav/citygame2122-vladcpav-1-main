package objects;

import city.cs.engine.*;

import java.util.Random;

public class Tree extends StaticBody {

    public Tree(World world) {

        super(world);

        Random ran = new Random();
        int treeId = ran.nextInt(4) + 1;

        new GhostlyFixture(this, new BoxShape(6, 6));
        this.addImage(new BodyImage("resources/sprites/jungle-tree" + treeId + ".png", 12));
    }
}
