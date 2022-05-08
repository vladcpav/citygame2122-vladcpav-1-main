package ui.scenes.levels;

import city.cs.engine.World;
import game.props.SkullPost;
import game.props.Tree;
import game.objects.Ground;
import org.jbox2d.common.Vec2;
import ui.Application;

public class Level2 extends BaseLevel {

    public Level2(Application application) {

        super(application);
    }

    @Override
    protected void build() {

        this.player.setPosition(new Vec2(-8, 0));

        World world = this.world;

        SkullPost skullPost = new SkullPost(world);
        skullPost.setPosition(new Vec2(30, -18));

        Ground ground = new Ground(world, 100, 20);
        ground.setPosition(new Vec2(-12, -30));

        Ground boundary = new Ground(world, 50, 100);
        boundary.setPosition(new Vec2(-35, -30));

        Ground ground2 = new Ground(world, 80, 40);
        ground2.setPosition(new Vec2(70, -30));

        Ground ground4 = new Ground(world, 60, 20);
        ground4.setPosition(new Vec2(80, -20));

        Tree tree1 = Tree.generate(world);
        tree1.setPosition(new Vec2(4,-13));

        Tree tree2 = Tree.generate(world);
        tree2.setPosition(new Vec2(12,-13));

        Tree tree3 = Tree.generate(world);
        tree3.setPosition(new Vec2(60,-3));
    }
}
