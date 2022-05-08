package game.objects;

import city.cs.engine.World;

public class RockGround extends Ground {

    public RockGround(World world, int width, int height) {

        super(
                world,
                width,
                height,
                new String[]{"rock-1.png", "rock-2.png", "rock-3.png"},
                new String[]{"rock-5.png", "rock-6.png", "rock-7.png", "rock-8.png", "rock-9.png"},
                new String[]{"rock-7.png", "rock-8.png", "rock-9.png", "rock-10.png", "rock-11.png"},
                new String[]{"rock-7.png", "rock-8.png", "rock-9.png"},
                new String[]{"rock-0.png"},
                new String[]{"rock-4.png"}
        );
    }
}
