package util.leveleditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class EditorPanel extends JPanel {

	private static final long serialVersionUID = -5584676219132420183L;

	EditorWindow parent;
	int width = 0, height = 0;
	JLabel[][] tiles;
	String[][] tileInfo;

	public EditorPanel() {
		initialize();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.BLACK);
		setFocusable(true);
	}

	/**
	 * Sets level dimensions to the specified dimensions.
	 * 
	 * @param width
	 *            Width of level.
	 * @param height
	 *            Height of level.
	 */
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Sets tileset displayed to the specified value.
	 * 
	 * @param name
	 *            Name of GraphicFile.
	 */
	public void setParent(EditorWindow parent) {
		this.parent = parent;
	}

	/**
	 * Method that sets dimensions and places blank icons in every tile. Called
	 * upon instantiation and when modifying the dimensions of a map.
	 */
	public void initialize() {
		removeAll(); // In case there is data in the editor.
		
		StringBuilder wString = new StringBuilder("[32]");
		StringBuilder hString = new StringBuilder("[32]");

		if (width == 0)
			width = 40;
		if (height == 0)
			height = 25;

		for (int i = 1; i < width; i++) {
			wString.append("1[32]");
		}

		for (int i = 1; i < height; i++) {
			hString.append("1[32]");
		}

		setLayout(new MigLayout("", wString.toString(), hString.toString()));

		tiles = new JLabel[width][height];
		tileInfo = new String[width][height];
		
		BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);

		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 32; j++) {
				img.setRGB(i, j, Color.WHITE.getRGB());
			}
		}

		ImageIcon ic = new ImageIcon(img);

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j] = new JLabel();
				tiles[i][j].setIcon(ic);
				add(tiles[i][j], "cell " + i + " " + j);
			}

		}
		
		revalidate();
	}

	/**
	 * Returns the image at the given coordinates.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @return Image at the given coordinates.
	 */
	public BufferedImage getImageAt(int x, int y) {
		ImageIcon icon = (ImageIcon) tiles[x][y].getIcon();
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		// paint the Icon to the BufferedImage.
		icon.paintIcon(null, g, 0, 0);
		g.dispose();

		return bi;
	}

}
