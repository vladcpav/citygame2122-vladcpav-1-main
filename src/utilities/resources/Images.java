package utilities.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {

    public static BufferedImage load(String path) {

        String fullPath = "resources" + path;

        try {
            return ImageIO.read(new File(fullPath));
        }
        catch (IOException exception) {
            System.out.println("Failed to load image at path: " + fullPath);
            return null;
        }
    }
}
