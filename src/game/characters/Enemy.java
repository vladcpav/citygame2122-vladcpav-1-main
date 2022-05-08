package game.characters;

import city.cs.engine.*;
import game.objects.EnemyBullet;
import game.objects.Ground;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

public class Enemy extends Character {

    private static final BodyImage IDLE_IMAGE = new BodyImage("resources/sprites/enemy-idle.gif", 6);
    private static final BodyImage RUN_IMAGE = new BodyImage("resources/sprites/enemy-run.gif", 6);
    private static final BodyImage DIE_IMAGE = new BodyImage("resources/sprites/enemy-die.gif", 6);
    private static final BodyImage SHOOT_IMAGE = new BodyImage("resources/sprites/enemy-shoot.gif", 6);

    private float baseHSpeed = 20;
    private int baseCooldown = 15;
    private int maxAllowedRounds = 5;
    private int baseBodyDamage = 15;
    private int baseBulletDamage = 5;
    private int roundsFired = 0;
    private float hitpoint = 100;
    private boolean aware = false;
    private boolean died = false;
    private boolean offensive = false;
    private Integer patrolDistance;
    private Vec2 startPosition;
    private int cooldown = 0;


    private Timer timer;

    public Enemy(World world, int patrolDistance) {

        this(world);

        this.patrolDistance = patrolDistance;
    }

    public Enemy(World world) {

        super(world);

        this.direction = -1;

        this.switchCurrentImage(Enemy.IDLE_IMAGE);

        this.addCollisionListener((e) -> {

            Body otherBody = e.getOtherBody();

            if (otherBody instanceof Player) {
                ((Player) otherBody).hit(this.baseBodyDamage);
            }
        });
    }

    public void hit(float damage, int direction) {

        if (this.died) {
            return;
        }

        if (this.timer != null) {
            this.timer.cancel();
        }

        this.aware = true;
        this.hitpoint -= damage;
        this.direction = direction * -1;

        this.timer = new Timer();

        if (this.hitpoint <= 0) {
            this.died = true;

            try {
                Field imageField = BodyImage.class.getDeclaredField("image");
                imageField.setAccessible(true);
                ((Image) imageField.get(Enemy.DIE_IMAGE)).flush();
            }
            catch (NoSuchFieldException | IllegalAccessException exception) {
                System.out.println(exception);
            }

            this.timer.schedule(new TimerTask() {

                @Override
                public void run() {

                    Enemy.this.destroy();
                }
            }, 1000);

            return;
        }

        this.timer.schedule(new TimerTask() {

            @Override
            public void run() {

                Enemy.this.offensive = true;
            }
        }, 500);
    }

    @Override
    public void destroy() {

        if (this.timer != null) {
            this.timer.cancel();
        }

        super.destroy();
    }

    public void update() {

        if (this.aware) {
            this.setLinearVelocity(new Vec2(0, this.getLinearVelocity().y));
        }

        if (this.died) {
            this.switchCurrentImage(Enemy.DIE_IMAGE);
            return;
        }

        if (this.offensive) {
            this.switchCurrentImage(Enemy.SHOOT_IMAGE);

            if (this.cooldown != 0) {
                this.cooldown--;
            }
            else {
                this.roundsFired++;
                if (this.roundsFired == this.maxAllowedRounds) {
                    this.cooldown = 0;
                    this.roundsFired = 0;
                    this.offensive = false;
                    this.aware = false;
                    this.startPosition = this.getPosition();
                }
                else {
                    this.cooldown = this.baseCooldown;
                }

                EnemyBullet enemyBullet = new EnemyBullet(this.getWorld(), this.direction, this.baseBulletDamage);
                Vec2 currentPosition = this.getPosition();
                enemyBullet.setPosition(new Vec2(currentPosition.x + 7 * this.direction, currentPosition.y));
            }

            return;
        }

        if (this.aware
            || this.patrolDistance == null) {

            this.switchCurrentImage(Enemy.IDLE_IMAGE);
            return;
        }

        if (this.startPosition == null) {
            this.startPosition = this.getPosition();
        }

        Vec2 currentPosition = this.getPosition();
        if (currentPosition.sub(this.startPosition).length() >= this.patrolDistance) {
            this.startPosition = currentPosition;
            this.direction *= -1;
        }

        this.switchCurrentImage(Enemy.RUN_IMAGE);
        this.setLinearVelocity(new Vec2(this.baseHSpeed * this.direction, this.getLinearVelocity().y));
    }
}
