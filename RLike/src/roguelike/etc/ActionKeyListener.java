package roguelike.etc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import roguelike.actors.Actor;
import roguelike.actors.Creature;
import roguelike.actors.Feature;
import roguelike.actors.Feature.FeatureType;
import roguelike.actors.Tile;
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
	protected int xPlus, yPlus;

	@Override
	public void keyPressed(KeyEvent e) {

		int xDiff = 0;
		int yDiff = 0;
		boolean movement = false;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_NUMPAD1: // down-left
			xDiff--;
			yDiff++;
			movement = true;
			break;
		case KeyEvent.VK_NUMPAD2: // down
			yDiff++;
			movement = true;
			break;
		case KeyEvent.VK_NUMPAD3: // down-right
			xDiff++;
			yDiff++;
			movement = true;
			break;
		case KeyEvent.VK_NUMPAD4: // left
			xDiff--;
			movement = true;
			break;
		case KeyEvent.VK_NUMPAD6: // right
			xDiff++;
			movement = true;
			break;
		case KeyEvent.VK_NUMPAD7: // up-left
			xDiff--;
			yDiff--;
			movement = true;
			break;
		case KeyEvent.VK_NUMPAD8: // up
			yDiff--;
			movement = true;
			break;
		case KeyEvent.VK_NUMPAD9: // up-right
			xDiff++;
			yDiff--;
			movement = true;
			break;
		case KeyEvent.VK_PERIOD: // For going down stairs. Requires shift+'.'
			if (e.isShiftDown()) {
				Tile tile = floor.getTileAt(Session.player.getX(), Session.player.getY());
				if (tile.getClass() == Feature.class && ((Feature) tile).getFeatureType() == FeatureType.DOWNSTAIRS) {
					// TODO Do downstairs stuff.
				}
			}
			break;
		case KeyEvent.VK_COMMA: // For going up stairs. Requires shift+','
			if (e.isShiftDown()) {
				Tile tile = floor.getTileAt(Session.player.getX(), Session.player.getY());
				if (tile.getClass() == Feature.class && ((Feature) tile).getFeatureType() == FeatureType.UPSTAIRS) {
					// TODO Do upstairs stuff.
				}
			}
			break;
		default:
			break;
		}

		if (movement) {
			playerMovement(xDiff, yDiff);
			Floor f = Session.player.getFloor();
			ArrayList<Actor> actors = f.actors;
			for (Actor actor: actors) {
				if (actor.getClass() == Creature.class) {
					((Creature) actor).doPrioritizedAction();
				}
			}
		}
			

		// Repaints this panel and all other panels currently attached to the
		// window.
		getParent().repaint();
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
		} else if (floor.getCreatureAt(x + xDiff, y + yDiff) != null) {
			// TODO Add creature interaction - may want to implement an 'action'
			// class and have all actors have a set of actions that can be
			// performed.
			Creature c = floor.getCreatureAt(x + xDiff, y + yDiff);
			Session.player.meleeAttack(c);
			if (c.hp.current <= 0)
				floor.actors.remove(c);
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

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public abstract void changeFloor(int index);

}
