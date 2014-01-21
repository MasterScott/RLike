package roguelike.ui.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import roguelike.ui.graphics.Graphic.GraphicFile;

public class RLGraphic {

	public static Image getEnvironmentImage(int index) {
		if (index < 0 || index > 255)
			throw new IndexOutOfBoundsException();

		BufferedImage in = null;
		BufferedImage newImage = null;

		try {
			in = ImageIO.read(new File(GraphicFile.ENVIRONMENT.fileName));
			newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = newImage.createGraphics();
			g.drawImage(in, 0, 0, null);
			g.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return processImage(newImage, index, 32, 32, 16, 16);
	}

	/**
	 * Creates a transparent, cropped image from the specified parameters.
	 * 
	 * @param img
	 *            Image to modify.
	 * @param index
	 *            Index of tile wanted.
	 * @param width
	 *            Width of tiles in the specified image.
	 * @param height
	 *            Height of tiles in the specified image.
	 * @param rows
	 *            Number of rows in the specified image.
	 * @param columns
	 *            Number of columns in the specified image.
	 * @return Transparent, cropped image.
	 */
	private static Image processImage(Image img, int index, int width, int height, int rows, int columns) {
		/*
		 * Make image transparent.
		 */
		ImageFilter filter = new RGBImageFilter() {
			int transparentColor = Color.white.getRGB() | 0xFF000000;

			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == transparentColor) {
					return 0x00FFFFFF & rgb;
				} else {
					return rgb;
				}
			}
		};

		ImageProducer filteredImgProd = new FilteredImageSource(img.getSource(), filter);
		Image transparentImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);

		/*
		 * Crop image.
		 */

		filter = new CropImageFilter((index % columns) * width, (index / rows) * height, width, height);
		filteredImgProd = new FilteredImageSource(transparentImg.getSource(), filter);
		Image finalImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);

		return finalImg;
	}

}
