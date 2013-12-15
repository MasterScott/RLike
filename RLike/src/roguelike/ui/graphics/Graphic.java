package roguelike.ui.graphics;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.awt.image.RescaleOp;

import javax.swing.ImageIcon;

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
		DRAGON("tilesets\\dg_dragon32.gif", true), 
		DUNGEON("tilesets\\dg_dungeon32.gif", false), 
		EDGING1("tilesets\\dg_edging132.gif", false), 
		EDGING2("tilesets\\dg_edging232.gif", false), 
		EDGING3("tilesets\\dg_edging332.gif", false), 
		EFFECTS("tilesets\\dg_effects32.gif", true), 
		EXTRA("tilesets\\dg_extra132.gif", false), 
		FEATURES("tilesets\\dg_features32.gif", false), 
		FOOD("tilesets\\dg_food32.gif", true), 
		GROUNDS("tilesets\\dg_grounds32.gif", false), 
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

	public static Image TILESET;

	/**
	 * Returns a graphic at the row and column from the tileset specified by the
	 * file argument.
	 * 
	 * @param file
	 *            GraphicFile to search through.
	 * @param row
	 *            Row of the graphic wanted, starting at 0.
	 * @param col
	 *            Column of the graphic wanted, starting at 0.
	 * @return Graphic at the row and column specified.
	 */
	public static Image getImage(GraphicFile file, int row, int col) {
		ImageIcon img = new ImageIcon(file.fileName);

		return processImage(img, row, col);
	}

	/**
	 * Returns a graphic at the row and column from the tileset specified by the
	 * filepath given.
	 * 
	 * @param file
	 *            Filepath of the graphic file to search through.
	 * @param row
	 *            Row of the graphic wanted, starting at 0.
	 * @param col
	 *            Column of the graphic wanted, starting at 0.
	 * @return Graphic at the row and column specified.
	 */
	public static Image getImage(String fileName, int row, int col) {
		ImageIcon img = new ImageIcon(fileName);

		return processImage(img, row, col);
	}

	/**
	 * Returns an image specified by the file string.
	 * 
	 * @param file
	 *            Path of the image.
	 * @return Image specified by the file string.
	 */
	public static Image getImage(String file) {
		ImageIcon img = new ImageIcon(file);

		Image image = img.getImage();
		return image;
	}

	private static Image processImage(ImageIcon img, int row, int col) {
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

		ImageProducer filteredImgProd = new FilteredImageSource(img.getImage().getSource(), filter);
		Image transparentImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);

		/*
		 * Crop image.
		 */

		filter = new CropImageFilter(col * 32, row * 32, 32, 32);
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