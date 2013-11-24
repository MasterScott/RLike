package roguelike.ui.graphics;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

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
		ARMOR("AngbandTk\\dg_armor32.gif"), 
		CLASSM("AngbandTk\\dg_classm32.gif"), 
		DRAGON("AngbandTk\\dg_dragon32.gif"), 
		DUNGEON("AngbandTk\\dg_dungeon32.gif"), 
		EDGING1("AngbandTk\\dg_edging132.gif"), 
		EDGING2("AngbandTk\\dg_edging232.gif"), 
		EDGING3("AngbandTk\\dg_edging332.gif"), 
		EFFECTS("AngbandTk\\dg_effects32.gif"), 
		EXTRA("AngbandTk\\dg_extra32.gif"), 
		FEATURES("AngbandTk\\dg_features32.gif"), 
		FOOD("AngbandTk\\dg_food32.gif"), 
		GROUNDS("AngbandTk\\dg_grounds32.gif"), 
		HUMANS("AngbandTk\\dg_humans32.gif"), 
		ISO("AngbandTk\\dg_iso32.gif"), 
		JEWLS("AngbandTk\\dg_jewls32.gif"), 
		MAGIC("AngbandTk\\dg_magic32.gif"), 
		MISC("AngbandTk\\dg_misc32.gif"), 
		MONSTER1("AngbandTk\\dg_monster132.gif"), 
		MONSTER2("AngbandTk\\dg_monster232.gif"), 
		MONSTER3("AngbandTk\\dg_monster332.gif"), 
		MONSTER4("AngbandTk\\dg_monster432.gif"),
		MONSTER5("AngbandTk\\dg_monster532.gif"), 
		MONSTER6("AngbandTk\\dg_monster632.gif"), 
		MONSTER7("AngbandTk\\dg_monster732.gif"), 
		PEOPLE("AngbandTk\\dg_people32.gif"), 
		POTIONS("AngbandTk\\dg_potions32.gif"), 
		TOWN0("AngbandTk\\dg_town032.gif"), 
		TOWN1("AngbandTk\\dg_town132.gif"), 
		TOWN2("AngbandTk\\dg_town232.gif"), 
		TOWN3("AngbandTk\\dg_town332.gif"), 
		TOWN4("AngbandTk\\dg_town432.gif"), 
		TOWN5("AngbandTk\\dg_town532.gif"), 
		TOWN6("AngbandTk\\dg_town632.gif"), 
		TOWN7("AngbandTk\\dg_town732.gif"), 
		TOWN8("AngbandTk\\dg_town832.gif"), 
		TOWN9("AngbandTk\\dg_town932.gif"), 
		UNDEAD("AngbandTk\\dg_undead32.gif"), 
		UNIQUES("AngbandTk\\dg_uniques32.gif"), 
		WANDS("AngbandTk\\dg_wands32.gif"), 
		WEAPONS("AngbandTk\\dg_weapons32.gif");
		// @formatter:on
		private String fileName;

		private GraphicFile(String fileName) {
			this.fileName = fileName;
		}
	}

	/**
	 * Returns a graphic at the row and column from the tileset specified by the
	 * file argument.
	 * 
	 * @param file
	 *            GraphicFile to search through.
	 * @param column
	 *            Column of the graphic wanted, starting at 0.
	 * @param row
	 *            Row of the graphic wanted, starting at 0.
	 * @return Graphic at the row and column specified.
	 */
	public static Image getImage(GraphicFile file, int column, int row) {
		ImageIcon img = new ImageIcon(file.fileName);

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
		filter = new CropImageFilter(column * 32, row * 32, 32, 32);
		filteredImgProd = new FilteredImageSource(transparentImg.getSource(), filter);
		Image finalImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);

		return finalImg;
	}
}
