package game.objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class Ground extends StaticBody {

    Ground(World world, int width, int height, String[] surfacePaths, String[] leftEdgePaths, String[] rightEdgePaths, String[] dirtPaths, String[] leftCornerPaths, String[] rightCornerPaths) {

        super(world, new BoxShape(width / 2, height / 2));

        Random rng = new Random();

        int xOrigin = -width / 2 + 2;
        int yOrigin = height / 2 - 2;

        for (int i = 0; i < width / 4; i++) {
            for (int j = 0; j < height / 4; j++) {
                String fileName;

                if (i == 0) {
                    fileName = j == 0 ? leftCornerPaths[rng.nextInt(leftCornerPaths.length)] : leftEdgePaths[rng.nextInt(leftEdgePaths.length)];
                }
                else if (i == width / 4 - 1) {
                    fileName = j == 0 ? rightCornerPaths[rng.nextInt(rightCornerPaths.length)] : rightEdgePaths[rng.nextInt(rightEdgePaths.length)];
                }
                else {
                    fileName = j == 0 ? surfacePaths[rng.nextInt(surfacePaths.length)] : dirtPaths[rng.nextInt(dirtPaths.length)];
                }

                AttachedImage dirtImage = this.addImage(new BodyImage("resources/tiles/" + fileName, 4));
                dirtImage.setOffset(new Vec2(xOrigin + i * 4, yOrigin - j * 4));
            }
        }
    }
}
