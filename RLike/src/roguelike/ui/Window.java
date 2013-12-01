package roguelike.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import roguelike.etc.Session;

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
		Session.window = this;
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
