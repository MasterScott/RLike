package roguelike.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import roguelike.etc.Session;
import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;

/**
 * Panel that displays status of player and various information.
 * 
 * @author Dan
 * 
 */
public class StatsPanel extends JPanel {

	private static final long serialVersionUID = 6646494324971390261L;
	private int fontSize;

	/**
	 * Create a new instance of a StatsPanel.
	 */
	public StatsPanel() {
		fontSize = 14;

		setBackground(Color.BLACK);
		setFocusable(false);
		setBounds(1376, 0, 160, 1024);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("Courier", Font.PLAIN, fontSize));
		g.setColor(Color.WHITE);

		g.drawRect(1378, 2, 139, 436);
		
		g.drawString("x: " + Session.player.getX(), 1430, 40);
		g.drawString("y: " + Session.player.getY(), 1430, 58);
		
	}

}
