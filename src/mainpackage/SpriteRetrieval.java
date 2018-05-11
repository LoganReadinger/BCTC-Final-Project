package mainpackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteRetrieval {
    private static BufferedImage spriteSheet;
    static ClassLoader loader = Presentation.class.getClassLoader();
    

    public static BufferedImage getSprite(int xGrid, int yGrid, String file, int TILE_SIZE) {
        try {
			spriteSheet = ImageIO.read(loader.getResourceAsStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    
    }
}