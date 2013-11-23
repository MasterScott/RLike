package roguelike.ui;

import javax.swing.JFrame;

/**
 * An (emulated) console window.
 * 
 * @author Dan
 * 
 */
public class Window extends JFrame {

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
	}

	// TODO Make it so key listener calls a repaint to all components from here!

}
