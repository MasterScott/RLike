package roguelike.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import roguelike.actors.Creature;
import roguelike.actors.Feature.FeatureType;
import roguelike.actors.util.CreatureTemplate;
import roguelike.actors.util.Stat;
import roguelike.actors.AI.BasicAI;
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
				ActionPanel p = new ActionPanel();
				StatsPanel s = new StatsPanel();
				Floor f = new Cave();
				f.generateFloor();

				Point point = f.getRandomOpenTile();
				Session.player.setFloor(f);
				Session.player.setX(point.x);
				Session.player.setY(point.y);
				f.actors.add(Session.player);
				f.createAccessibleStairs(Session.player, FeatureType.DOWNSTAIRS, GraphicFile.DUNGEON, 4, 7);

				for (int i = 0; i < 4; i++) {
					Point ps = f.getRandomOpenTile();
					Creature c = null;
					if (Math.random() > 0.5) {
						c = Creature.constructCreature(CreatureTemplate.GOBLIN);
					} else {
						c = Creature.constructCreature(CreatureTemplate.RAT_WHITE);
					}
					c.setCoords(ps.x, ps.y);
					c.setFloor(f);
					//c.setAI(new BasicAI());
					f.actors.add(c);
				}
				
				p.setFloor(f);

				p.repaint();
				s.repaint();
				p.setPreferredSize(p.getSize());
				p.setMaximumSize(p.getSize());
				
				getContentPane().removeAll();
				setBounds(100, 10, 1536, 1024);
				
				getContentPane().add(p);
				getContentPane().add(s);
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
		//repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//repaint();
		
	}

}
