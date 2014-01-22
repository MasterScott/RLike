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
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class containing static references to all graphics files as well as a method
 * to extract a single image from a tileset compilation image.
 * 
 * @author Dan
 * 
 */
public class Graphic {

	public static enum GraphicFile {
		// @formatter:off
		ARMOR("tilesets\\dg_armor32.gif", true), 
		CLASSM("tilesets\\dg_classm32.gif", true), 
		CHAR("graphics\\char.png", true),
		DRAGON("tilesets\\dg_dragon32.gif", true), 
		DUNGEON("tilesets\\dg_dungeon32.gif", false), 
		DUNGEON_FEATURES("graphics\\dungeon_features.png", true),
		EDGING1("tilesets\\dg_edging132.gif", false), 
		EDGING2("tilesets\\dg_edging232.gif", false), 
		EDGING3("tilesets\\dg_edging332.gif", true), 
		EFFECTS("tilesets\\dg_effects32.gif", true), 
		ENVIRONMENT("graphics\\environment.png", false),
		EXTRA("tilesets\\dg_extra132.gif", false), 
		FEATURES("tilesets\\dg_features32.gif", false), 
		FOOD("tilesets\\dg_food32.gif", true), 
		GLYPHS("graphics\\glyphs.png", true),
		GROUNDS("tilesets\\dg_grounds32.gif", false), 
		HALLS("graphics\\halls.png", true),
		HUMANS("tilesets\\dg_humans32.gif", true), 
		ISO("tilesets\\dg_iso32.gif", false), 
		JEWLS("tilesets\\dg_jewls32.gif", true), 
		MAGIC("tilesets\\dg_magic32.gif", true), 
		MISC("tilesets\\dg_misc32.gif", true), 
		MONSTER1("tilesets\\dg_monster132.gif", true), 
		MONSTER2("tilesets\\dg_monster232.gif", true), 
		MONSTER3("tilesets\\dg_monster332.gif", true), 
		MONSTER4("tilesets\\dg_monster432.gif", true),
		MONSTER5("tilesets\\dg_monster532.gif", true), 
		MONSTER6("tilesets\\dg_monster632.gif", true), 
		MONSTER7("tilesets\\dg_monster732.gif", true), 
		OBJ("graphics\\obj.png", true),
		PEOPLE("tilesets\\dg_people32.gif", true), 
		POTIONS("tilesets\\dg_potions32.gif", true), 
		TOWN0("tilesets\\dg_town032.gif", false), 
		TOWN1("tilesets\\dg_town132.gif", false), 
		TOWN2("tilesets\\dg_town232.gif", false), 
		TOWN3("tilesets\\dg_town332.gif", false), 
		TOWN4("tilesets\\dg_town432.gif", false), 
		TOWN5("tilesets\\dg_town532.gif", false), 
		TOWN6("tilesets\\dg_town632.gif", false), 
		TOWN7("tilesets\\dg_town732.gif", false), 
		TOWN8("tilesets\\dg_town832.gif", false), 
		TOWN9("tilesets\\dg_town932.gif", false), 
		UNDEAD("tilesets\\dg_undead32.gif", true), 
		UNIQUES("tilesets\\dg_uniques32.gif", true), 
		WANDS("tilesets\\dg_wands32.gif", true), 
		WEAPONS("tilesets\\dg_weapons32.gif", true);
		// @formatter:on
		public String fileName;
		public boolean overlay;

		private GraphicFile(String fileName, boolean overlay) {
			this.fileName = fileName;
			this.overlay = overlay;
		}
	}

	/**
	 * Returns an image specified by the file string.
	 * 
	 * @param file
	 *            Path of the image.
	 * @return Image specified by the file string.
	 */
	public static Image getImage(String file) {
		BufferedImage image = null;

		try {
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	/**
	 * Returns an image cropped from the specified graphics file.
	 * 
	 * @param gf
	 *            Graphics file to crop from.
	 * @param index
	 *            Index of tile to crop.
	 * @return Image cropped from file.
	 */
	public static Image getImage(GraphicFile gf, int index) {
		if (index < 0 || index > 255)
			throw new IndexOutOfBoundsException();

		BufferedImage in = null;
		BufferedImage newImage = null;

		try {
			in = ImageIO.read(new File(gf.fileName));
			newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = newImage.createGraphics();
			g.drawImage(in, 0, 0, null);
			g.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return processImage(newImage, index, 32, 32);
	}

	/**
	 * Returns an image cropped from the specified graphics file.
	 * 
	 * @param file
	 *            Graphics file to crop from.
	 * @param index
	 *            Index of tile to crop.
	 * @return Image cropped from file.
	 */
	public static Image getImage(String file, int index) {
		if (index < 0 || index > 255)
			throw new IndexOutOfBoundsException();

		BufferedImage in = null;
		BufferedImage newImage = null;

		try {
			in = ImageIO.read(new File(file));
			newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = newImage.createGraphics();
			g.drawImage(in, 0, 0, null);
			g.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return processImage(newImage, index, 32, 32);
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
	 * @return Transparent, cropped image.
	 */
	private static Image processImage(Image img, int index, int width, int height) {
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
		int columns = img.getWidth(null) / 32;
		filter = new CropImageFilter((index % columns) * width, (index / columns) * height, width, height);
		filteredImgProd = new FilteredImageSource(transparentImg.getSource(), filter);
		Image finalImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);

		return finalImg;
	}

	/**
	 * Returns a copy of the image passed to the method set to the specified
	 * brightness.
	 * 
	 * @param img
	 *            Image to be modified.
	 * @param brightness
	 *            Float value representing image brightness.
	 * @return Original image set to the specified brightness.
	 */
	public static Image getAdjustedBrightnessImage(Image img, float brightness) {
		BufferedImage image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(img, 0, 0, null);

		RescaleOp op = new RescaleOp(brightness, 0, null);
		image = op.filter(image, image);
		return image;
	}
}