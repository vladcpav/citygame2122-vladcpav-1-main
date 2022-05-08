package game.objects;

import city.cs.engine.World;

public class GrassGround extends Ground {

    public GrassGround(World world, int width, int height) {

        super(
            world,
            width,
            height,
            new String[]{"ground-1.png", "ground-2.png", "ground-3.png", "ground-4.png"},
            new String[]{"ground-6.png", "ground-7.png", "ground-8.png", "ground-9.png", "ground-10.png"},
            new String[]{"ground-7.png", "ground-8.png", "ground-9.png", "ground-10.png", "ground-11.png"},
            new String[]{"ground-7.png", "ground-8.png", "ground-9.png", "ground-10.png"},
            new String[]{"ground-0.png"},
            new String[]{"ground-5.png"}
        );
    }
}
