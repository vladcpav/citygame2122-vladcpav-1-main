package game.objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class Ground extends StaticBody {

    public Ground(World world, int width, int height) {

        super(world, new BoxShape(width / 2, height / 2));

        Random ran = new Random();

        int xOrigin = -width / 2 + 2;
        int yOrigin = height / 2 - 2;

        for (int i = 0; i < width / 4; i++) {
            for (int j = 0; j < height / 4; j++) {
                String fileName;

                if (i == 0) {
                    fileName = j == 0 ? "ground-0.png" : "ground-" + (ran.nextInt(5) + 6) + ".png";
                }
                else if (i == width / 4 - 1) {
                    fileName = j == 0 ? "ground-5.png" : "ground-" + (ran.nextInt(5) + 7) + ".png";
                }
                else {
                    fileName = "ground-" + (ran.nextInt(4) + (j == 0 ? 1 : 7)) + ".png";
                }

                AttachedImage dirtImage = this.addImage(new BodyImage("resources/tiles/" + fileName, 4));
                dirtImage.setOffset(new Vec2(xOrigin + i * 4, yOrigin - j * 4));
            }
        }
    }
}
