import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedImageLoader {

    private BufferedImage image;
    public BufferedImage loadImage(String path) throws IOException {
        image = ImageIO.read(new FileInputStream(path));
        return image;
    }
}
