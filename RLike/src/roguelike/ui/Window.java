package roguelike.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import roguelike.etc.Session;
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
	private ActionPanel p;
	private StatsPanel s;
	private JDesktopPane jdp;

	/**
	 * Creates a new console window with default parameters.
	 */
	public Window() {
		setBounds(100, 10, 1536, 1024);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Session.window = this;
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
	
	}
	
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
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
	}
	
	public void notifyCharacterSelected() {

		Runnable switchContents = new Runnable() {

			@Override
			public void run() {
				// TODO Need to rework all of this.
				Session.initialize();
				
				p = new ActionPanel();
				s = new StatsPanel();
				Floor f = new Cave();
				f.generateFloor();

				Point point = f.getRandomAccessibleTile(f.getDownstairs().getCoords());
				Session.player.setFloor(f);
				Session.player.setX(point.x);
				Session.player.setY(point.y);
				f.actors.add(Session.player);
				f.setDepth(0);
				
				p.setFloor(f);

				p.repaint();
				s.repaint();
				p.setPreferredSize(p.getSize());
				p.setMaximumSize(p.getSize());
				
				getContentPane().removeAll();
				setBounds(100, 10, 1536, 1024);
				
				jdp = new JDesktopPane();
				getContentPane().add(jdp);
				jdp.add(p);
				jdp.add(s);
				jdp.add(Session.inventoryFrame);

				p.setVisible(true);
				p.setBackground(Color.BLACK);

				setVisible(true);
				p.requestFocus();
				
			}
		};
		
		SwingUtilities.invokeLater(switchContents);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
