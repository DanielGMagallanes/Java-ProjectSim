package PracticeSim.Assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) {
		try {
			URL imageFile = ImageLoader.class.getResource(path);
			if(imageFile != null) {
			return ImageIO.read(imageFile);
			}
			else {
				System.out.println("Not found");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
