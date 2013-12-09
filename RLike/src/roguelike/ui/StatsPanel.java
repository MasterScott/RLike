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
	private JLabel lblX, lblY;

	/**
	 * Create a new instance of a StatsPanel.
	 */
	public StatsPanel() {

		setBackground(Color.BLACK);
		setFocusable(false);
		setBounds(1376, 0, 160, 1024);
		setLayout(null);
		
		JLabel lblCharacterName = new JLabel("Character Name");
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
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		lblX.setText("X: " + Session.player.getX());
		lblY.setText("Y: " + Session.player.getY());
	}
}
