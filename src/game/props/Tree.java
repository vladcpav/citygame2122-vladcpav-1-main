package game.props;

import city.cs.engine.*;

import java.util.Random;

public class Tree extends Prop {

    private Tree(World world, int id) {

        super(world, "resources/props/jungle-tree-" + id + ".png", 16);
    }

    public static Tree generate(World world) {

        Random ran = new Random();
        return new Tree(world, ran.nextInt(1));
    }
}
