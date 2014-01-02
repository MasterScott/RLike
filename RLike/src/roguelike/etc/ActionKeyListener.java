package roguelike.etc;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import roguelike.actors.Actor;
import roguelike.actors.Creature;
import roguelike.actors.Feature;
import roguelike.actors.Feature.FeatureType;
import roguelike.actors.Player;
import roguelike.actors.Tile;
import roguelike.ui.graphics.Graphic.GraphicFile;
import roguelike.world.BaseDungeon;
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
		case KeyEvent.VK_NUMPAD5: // stay still
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
					changeFloor((Feature) tile);
				}
			}
			break;
		case KeyEvent.VK_COMMA: // For going up stairs. Requires shift+','
			if (e.isShiftDown()) {
				Tile tile = floor.getTileAt(Session.player.getX(), Session.player.getY());
				if (tile.getClass() == Feature.class && ((Feature) tile).getFeatureType() == FeatureType.UPSTAIRS) {
					changeFloor((Feature) tile);
				}
			}
			break;
		default:
			break;
		}

		if (movement) { // If movement key was pressed
			playerMovement(xDiff, yDiff);
			if (Session.player.movement) { // If movement was successful
				doCreatureActions();
				Session.turnCount++;
				Session.player.processTurn();
			}

		}

		// Repaints this panel and all other panels currently attached to the
		// window.
		getParent().repaint();
	}

	/**
	 * Performs actions for all creatures currently eligible to perform an
	 * action.
	 */
	public void doCreatureActions() {
		// FIXME This likely needs to be placed somewhere else. Sometimes
		// StatsPanel refreshes first.
		if (Session.player.movement) {
			for (Actor actor : floor.actors) {
				if (actor.getClass() == Creature.class) {
					((Creature) actor).doPrioritizedAction();
					((Creature) actor).processTurn();
				}
			}
		}

		Session.player.movement = false;
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
		Player p = Session.player;
		int x = p.getX();
		int y = p.getY();

		// TODO Clean this code up.

		if (xDiff == 0 && yDiff == 0) { // Player stayed still
			p.movement = true;
			return;
		}

		Creature c = floor.getCreatureAt(x + xDiff, y + yDiff);
		Tile t = floor.getTileAt(x + xDiff, y + yDiff);

		/*
		 * Can't walk through an actor unless they are explicitly set to be
		 * traversable.
		 */
		if (!(floor.checkCollision(x + xDiff, y + yDiff))) {
			p.setX(x + xDiff);
			p.setY(y + yDiff);
			p.movement = true;
		} else if (c != null) {
			p.meleeAttack(c);
			if (c.hp.current <= 0) {
				floor.actors.remove(c);
				p.exp += c.getExpGiven();
			}

			p.movement = true;
		} else if (t != null && t.getClass() == Feature.class){
			// Open door if one is present.
			if (((Feature) t).getFeatureType() == FeatureType.DOOR){
				floor.actors.remove(t);
				floor.actors.add(floor.getFloorTile(t.getX(), t.getY()));
			}
		}

		// Need to reprocess LOS so creatures function correctly.
		if (p.movement)
			p.getLOS().getVisible();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void changeFloor(Feature stairs) {
		Floor newFloor = null;
		Point p = null;
		
		if (stairs.getConnectingStaircase() == null) {
			newFloor = new BaseDungeon();
			newFloor.generateFloor();
			
			p = newFloor.getRandomOpenTile();
			
			newFloor.createStairs(p.x, p.y, FeatureType.UPSTAIRS, GraphicFile.DUNGEON, 4, 8);
			newFloor.getUpstairs().setConnectingStaircase(stairs);
			stairs.setConnectingStaircase(newFloor.getUpstairs());
			Session.floors.add(newFloor);
		} else {
			newFloor = stairs.getConnectingStaircase().getFloor();
			p = stairs.getConnectingStaircase().getCoords();
		}
		
		newFloor.actors.add(Session.player);
		floor.actors.remove(Session.player);
		Session.player.setFloor(newFloor);
		Session.player.setCoords(p.x, p.y);
		
		floor = newFloor;
	}

}
