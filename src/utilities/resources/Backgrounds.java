package utilities.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Backgrounds {

    public static BufferedImage load(String fileName) {

        String fullPath = String.join(File.separator, "resources", "backgrounds", fileName);

        try {
            return ImageIO.read(new File(fullPath));
        }
        catch (IOException exception) {
            System.out.println("Failed to load image at path: " + fullPath);
            return null;
        }
    }
}
