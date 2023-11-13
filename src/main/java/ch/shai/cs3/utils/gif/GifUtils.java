package ch.shai.cs3.utils.gif;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GifUtils {


    public static int[][] readGif(File file) throws IOException {
        ImageInputStream stream = ImageIO.createImageInputStream(file);
        ImageReader reader = ImageIO.getImageReaders(stream).next();
        reader.setInput(stream);
        int numFrames = reader.getNumImages(true);
        int[][] ret = new int[numFrames + 1][];
        BufferedImage frame = reader.read(0);
        ret[0] = new int[]{frame.getHeight(), frame.getWidth(), numFrames};
        for (int i = 1; i < numFrames; i++) {
            ret[i] = frame.getRGB(0, 0, frame.getWidth(), frame.getHeight(), null, 0, frame.getWidth());
            frame = reader.read(i);
            System.out.println(frame + " ");
        }
        stream.close();
        return ret;
    }
}
