package ui.scenes.levels;

import city.cs.engine.World;
import game.characters.Enemy;
import game.objects.GrassGround;
import game.powerups.Ammo;
import game.powerups.Plant;
import game.props.SkullPost;
import game.props.Tree;
import org.jbox2d.common.Vec2;
import ui.Application;
import utilities.resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Level1 extends BaseLevel {

    private Cloud[] clouds = new Cloud[5];

    public Level1(Application application) {

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

        new Plant(world).setPosition(new Vec2(170, 16));
        new Ammo(world).setPosition(new Vec2(100, -8));

        new SkullPost(world).setPosition(new Vec2(30, -18));
        new SkullPost(world).setPosition(new Vec2(92, 6));

        new GrassGround(world, 100, 20).setPosition(new Vec2(-12, -30));
        new GrassGround(world, 50, 100).setPosition(new Vec2(-35, -30));

        new GrassGround(world, 100, 40).setPosition(new Vec2(80, -30));

        new GrassGround(world, 30, 4).setPosition(new Vec2(105, 2));
        new GrassGround(world, 160, 90).setPosition(new Vec2(190, -30));

        Tree.generate(world).setPosition(new Vec2(4,-13));
        Tree.generate(world).setPosition(new Vec2(12,-13));
        Tree.generate(world).setPosition(new Vec2(60,-3));
        Tree.generate(world).setPosition(new Vec2(70,-3));
        Tree.generate(world).setPosition(new Vec2(130, 22));
        Tree.generate(world).setPosition(new Vec2(170, 22));
        Tree.generate(world).setPosition(new Vec2(220, 22));

        for (int i = 0; i < 5; i++) {
            this.clouds[i] = new Cloud();
        }
    }

    @Override
    protected void draw(Graphics g) {

        g.drawImage(Images.load("/backgrounds/background-sky.png"), 0, 0, this.getWidth(), this.getHeight(), null);
        this.drawClouds(g);
        g.drawImage(Images.load("/backgrounds/bg-forest.png"), 0, 0, this.getWidth(), this.getHeight(), null);

        BufferedImage image = Images.load("/backgrounds/forest.png");
        int targetWidth = this.getWidth() / 3;
        int targetHeight = (targetWidth / image.getWidth()) * image.getHeight();

        g.drawImage(image, 300, this.getHeight() - targetHeight - 200, targetWidth, targetHeight, null);
        g.drawImage(image, 600 + targetWidth, this.getHeight() - targetHeight - 300, targetWidth, targetHeight, null);
    }

    private void drawClouds(Graphics g) {

        for (Cloud cloud: this.clouds) {
            cloud.draw(g);
        }
    }

    private class Cloud {

        private static BufferedImage[] CLOUD_SKINS = new BufferedImage[] {Images.load("/backgrounds/clouds-0.png"), Images.load("/backgrounds/clouds-1.png")};

        private BufferedImage skin;
        private Integer x;
        private int y;
        private int width;
        private int height;

        Cloud() {

            Random rng = new Random();

            this.skin = Cloud.CLOUD_SKINS[rng.nextInt(1)];
            this.y = rng.nextInt(240) + 100;

            int scale = rng.nextInt(6);
            this.width = this.skin.getWidth() * scale;
            this.height = this.skin.getHeight() * scale;
        }

        public void draw(Graphics g) {

            Random rng = new Random();

            if (this.x != null) {
                if (this.x < -this.width) {
                    this.x = Level1.this.getWidth() + this.width;
                }

                this.x -= (rng.nextInt(6) + 10);
            }
            else {
                this.x = rng.nextInt(Level1.this.getWidth());
            }

            g.drawImage(this.skin, this.x, this.y, this.width, this.height, null);
        }
    }
}
