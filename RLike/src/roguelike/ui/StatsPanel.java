package roguelike.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
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
	private JLabel lblCharacterName;
	private JLabel lblX, lblY;
	private JLabel lblHP;

	/**
	 * Create a new instance of a StatsPanel.
	 */
	public StatsPanel() {

		setBackground(Color.BLACK);
		setFocusable(false);
		setBounds(1376, 0, 160, 1024);
		setLayout(null);

		lblCharacterName = new JLabel("Character Name");
		lblCharacterName.setForeground(Color.WHITE);
		lblCharacterName.setBounds(10, 11, 140, 14);
		add(lblCharacterName);

		lblX = new JLabel("X: ");
		lblX.setForeground(Color.WHITE);
		lblX.setBounds(10, 105, 46, 14);
		add(lblX);

		lblY = new JLabel("Y: ");
		lblY.setForeground(Color.WHITE);
		lblY.setBounds(10, 130, 46, 14);
		add(lblY);

		lblHP = new JLabel("HP: ");
		lblHP.setForeground(Color.WHITE);
		lblHP.setBounds(10, 36, 140, 14);
		add(lblHP);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		lblCharacterName.setText(Session.player.getName());
		lblX.setText("X: " + Session.player.getX());
		lblY.setText("Y: " + Session.player.getY());
		lblHP.setText("HP: " + Session.player.hp.toString());
		
		System.out.println("Repainting");
		final int HP_BAR_WIDTH = 100;
		int v = (int) (((double) Session.player.hp.current / (double) Session.player.hp.max) * HP_BAR_WIDTH);

		g.setColor(new Color(255, 0, 0));
		g.fillRect(lblHP.getBounds().x, lblHP.getBounds().y + lblHP.getBounds().height + 10, HP_BAR_WIDTH, 5);
		g.setColor(new Color(0, 255, 0));
		g.fillRect(lblHP.getBounds().x, lblHP.getBounds().y + lblHP.getBounds().height + 10, v, 5);

	}
}
