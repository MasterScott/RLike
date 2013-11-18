package roguelike.etc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import roguelike.actors.Actor;
import roguelike.world.Floor;

/**
 * Abstract superclass for an ActionPanel. Contains implementations of
 * KeyListener and JPanel.
 * 
 * @author Dan
 * 
 */
public abstract class ActionKeyListener extends JPanel implements KeyListener {

	private static final long serialVersionUID = -6196763528616193375L;
	protected Floor floor;
	protected int fontSize;
	protected int xScale, yScale;
	protected int xBound, yBound;

	@Override
	public void keyPressed(KeyEvent e) {

		int xDiff = 0;
		int yDiff = 0;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_NUMPAD1: // down-left
			xDiff--;
			yDiff++;
			break;
		case KeyEvent.VK_NUMPAD2: // down
			yDiff++;
			break;
		case KeyEvent.VK_NUMPAD3: // down-right
			xDiff++;
			yDiff++;
			break;
		case KeyEvent.VK_NUMPAD4: // left
			xDiff--;
			break;
		case KeyEvent.VK_NUMPAD6: // right
			xDiff++;
			break;
		case KeyEvent.VK_NUMPAD7: // up-left
			xDiff--;
			yDiff--;
			break;
		case KeyEvent.VK_NUMPAD8: // up
			yDiff--;
			break;
		case KeyEvent.VK_NUMPAD9: // up-right
			xDiff++;
			yDiff--;
			break;
		default:
			break;
		}

		playerMovement(xDiff, yDiff);

		repaint();
	}

	/**
	 * Attempts to move player to specified coordinate.
	 * 
	 * @param xDiff
	 *            Delta from player's current x-position.
	 * @param yDiff
	 *            Delta from player's current y-position.
	 */
	private void playerMovement(int xDiff, int yDiff) {
		int x = Session.player.getX();
		int y = Session.player.getY();

		/*
		 * Can't walk through an actor unless they are explicitly set to be
		 * traversable.
		 */
		if (!(floor.checkCollision(x + xDiff, y + yDiff))) {
			Session.player.setX(x + xDiff);
			Session.player.setY(y + yDiff);
		} else {
			Actor actor = floor.getActorAt(x + xDiff, y + yDiff);
			if (actor != null && actor.isTraversable()) {
				Session.player.setX(x + xDiff);
				Session.player.setY(y + yDiff);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
