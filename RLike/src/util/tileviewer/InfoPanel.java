package util.tileviewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import roguelike.ui.graphics.Graphic.GraphicFile;

/**
 * Displays a list of tilesets to choose from. Will load all tilesets currently
 * loaded into the GraphicFile enum in Graphic.java.
 * 
 * @author Dan
 * 
 */
public class InfoPanel extends JPanel {


	private static final long serialVersionUID = 3536357614153958718L;
	int x, y;

	/**
	 * Create a new default InfoPanel.
	 */
	public InfoPanel() {
		setBounds(500, 0, 200, 800);
		setBackground(Color.BLACK);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("Courier", Font.PLAIN, 14));
		g.setColor(Color.WHITE);

		int drawY = 32;

		for (GraphicFile gf : GraphicFile.values()) {
			g.drawString(gf.name(), 520, drawY);
			drawY += 18;
		}
	}

}
