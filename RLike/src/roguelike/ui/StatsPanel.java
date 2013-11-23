package roguelike.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import roguelike.etc.Session;

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
		setBounds(640, 0, 160, 480);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("Courier", Font.PLAIN, fontSize));
		g.setColor(Color.WHITE);

		g.drawRect(642, 2, 139, 436);
		g.drawString("HP: " + Session.player.hp.toString(), 660, 40);
		g.drawString("MP: " + Session.player.mp.toString(), 660, 58);
		g.drawString("STR: " + Session.player.strength.getCur(), 660, 76);
		g.drawString("INT: " + Session.player.intelligence.getCur(), 660, 94);
		g.drawString("DEX: " + Session.player.dexterity.getCur(), 660, 112);
		
		g.drawString("x: " + Session.player.getX(), 660, 148);
		g.drawString("y: " + Session.player.getY(), 660, 166);
	}

}
