package util.tileviewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Displays tilesets and access information for the selected tiles.
 * 
 * @author Dan
 * 
 */
public class GraphicPanel extends JPanel {

	private static final long serialVersionUID = -4318207486332631706L;
	Image tileset;
	String tileset_path;
	Image tile;
	int row, col;

	/**
	 * Create a new default GraphicPanel.
	 */
	public GraphicPanel() {
		setBounds(0, 0, 500, 800);
		setBackground(Color.LIGHT_GRAY);
		row = 0;
		col = 0;
	}

	/**
	 * Set row and column to be displayed.
	 * 
	 * @param row
	 *            Row to be displayed.
	 * @param column
	 *            Column to be displayed.
	 */
	public void setCoords(int row, int column) {
		this.row = row;
		this.col = column;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (tileset != null) {
			g.drawImage(tileset, 0, 0, null);
		}

		g.drawString("Current Tile: ", 40, 700);
		g.drawString("Row: " + row + "   Column: " + col, 160, 700);
		g.drawString("Tileset: " + tileset_path, 280, 700);
		if (tile != null) {
			g.drawImage(tile, 120, 680, null);
		}

		repaint();
	}

}
