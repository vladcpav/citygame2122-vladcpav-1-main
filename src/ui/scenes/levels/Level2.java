package ui.scenes.levels;

import city.cs.engine.World;
import game.characters.Enemy;
import game.objects.RockGround;
import game.props.SkullPost;
import org.jbox2d.common.Vec2;
import ui.Application;
import utilities.resources.Images;

import java.awt.*;

public class Level2 extends BaseLevel {

    public Level2(Application application) {

        super(application);
    }

    @Override
    protected void build() {

        this.player.setPosition(new Vec2(-8, 0));
        this.shelter.setPosition(new Vec2(250, 22));

        World world = this.world;

        this.addEnemy(new Enemy(world, 40)).setPosition(new Vec2(80, -17));
        this.addEnemy(new Enemy(world)).setPosition(new Vec2(100, 6));
        this.addEnemy(new Enemy(world, 30)).setPosition(new Vec2(160, 30));
        this.addEnemy(new Enemy(world, 20)).setPosition(new Vec2(190, 30));
        this.addEnemy(new Enemy(world, 20)).setPosition(new Vec2(240, 30));

        new SkullPost(world).setPosition(new Vec2(30, -18));
        new SkullPost(world).setPosition(new Vec2(92, 6));

        new RockGround(world, 100, 20).setPosition(new Vec2(-12, -30));
        new RockGround(world, 50, 100).setPosition(new Vec2(-35, -30));

        new RockGround(world, 100, 40).setPosition(new Vec2(80, -30));

        new RockGround(world, 30, 4).setPosition(new Vec2(105, 2));
        new RockGround(world, 160, 90).setPosition(new Vec2(190, -30));
    }

    @Override
    protected void draw(Graphics g) {

        g.drawImage(Images.load("/backgrounds/background-cave.png"), 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
