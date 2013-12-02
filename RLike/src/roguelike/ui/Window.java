package roguelike.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import roguelike.actors.Player;
import roguelike.actors.Feature.FeatureType;
import roguelike.etc.Session;
import roguelike.ui.graphics.Graphic.GraphicFile;
import roguelike.world.Cave;
import roguelike.world.Floor;

/**
 * An (emulated) console window.
 * 
 * @author Dan
 * 
 */
public class Window extends JFrame implements KeyListener {

	private static final long serialVersionUID = -5680894051148117655L;

	/**
	 * Creates a new console window.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @param width
	 *            Width of window.
	 * @param height
	 *            Height of window.
	 */
	public Window(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Session.window = this;
	}
	
	public void notifyCharacterSelected() {

		Runnable switchContents = new Runnable() {

			@Override
			public void run() {
				getContentPane().removeAll();
				setBounds(100, 10, 1536, 1024);
				ActionPanel p = new ActionPanel();
				StatsPanel s = new StatsPanel();
				Floor f = new Cave();
				f.generateFloor();

				Point point = f.getRandomOpenTile();
				Session.player.setFloor(f);
				f.actors.add(Session.player);
				f.createAccessibleStairs(Session.player, FeatureType.DOWNSTAIRS, GraphicFile.DUNGEON, 4, 7);

				p.setFloor(f);

				p.repaint();
				s.repaint();
				getContentPane().add(p);
				getContentPane().add(s);
				p.setVisible(true);
				p.setBackground(Color.BLACK);

				setVisible(true);
				
				
			}
		};
		
		SwingUtilities.invokeLater(switchContents);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		repaint();
		
	}

	// TODO Make it so key listener calls a repaint to all components from here!

}
