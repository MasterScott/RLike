package roguelike.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import roguelike.actors.Actor;
import roguelike.actors.Creature;
import roguelike.actors.Tile;
import roguelike.etc.ActionKeyListener;
import roguelike.etc.Session;
import roguelike.ui.graphics.Graphic;
import roguelike.world.Floor;

/**
 * Extension of JPanel that displays the world and actors within the world. Set
 * up to display one floor at a time.
 * 
 * @author Dan
 * 
 */
public class ActionPanel extends ActionKeyListener {

	private static final long serialVersionUID = -7640574532979759113L;

	/**
	 * Creates a new instance of an ActionPanel.
	 */
	public ActionPanel() {
		setFocusable(true);
		addKeyListener(this);

		// TODO Make these into variables.
		setBounds(0, 0, 1376, 1024);
		setSize(1376, 1024);

		xScale = 32;
		yScale = 32;
		xPlus = 1;
		yPlus = 11;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (floor == null)
			throw new NullPointerException("A floor was not defined for this ActionPanel.");

		ArrayList<Actor> inSight = Session.player.getLOS().getVisible();
		
		/*
		 * If actor is in sight, display in normal color and set previously seen
		 * to true. If not in sight, display at an obscured color if the actor
		 * is a tile and has been seen before. Otherwise, do nothing.
		 */
		for (Actor actor : floor.actors) {
			if (inSight.contains(actor) && !(actor instanceof Creature)) {

				g.drawImage(actor.getImage(), actor.getX() * xScale + xPlus, actor.getY() * yScale + yPlus, this);
				actor.setPreviouslySeen(true);

			} else if (actor.getPreviouslySeen() && actor instanceof Tile) {

				g.drawImage(Graphic.getAdjustedBrightnessImage(actor.getImage(), 0.5f), actor.getX() * xScale + xPlus,
						actor.getY() * yScale + yPlus, this);
			}
		}

		/*
		 * Draw creature types after drawing non-creature types, as creature
		 * types will generally be drawn on top of non-creatures.
		 */
		for (Actor actor : floor.actors) {
			if (inSight.contains(actor) && actor instanceof Creature) {
				Creature c = (Creature) actor;
				
				g.drawImage(c.getImage(), c.getX() * xScale + xPlus, c.getY() * yScale + yPlus, this);

				// Draw health bars.
				if (c.hp.max > c.hp.current) {
					int v = (int) (((double) c.hp.current / (double) c.hp.max) * 30);
					
					g.setColor(new Color(255, 0, 0));
					g.fillRect(c.getX() * xScale + xPlus + 1, c.getY() * yScale + yPlus + 30, 30, 2);
					g.setColor(new Color(0, 255, 0));
					g.fillRect(c.getX() * xScale + xPlus + 1, c.getY() * yScale + yPlus + 30, v, 2);
				}
			}
		}

	}
	
	

	/**
	 * Sets floor to be displayed.
	 * 
	 * @param floor
	 *            Floor to be displayed.
	 */
	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	/**
	 * Returns floor currently being displayed.
	 * 
	 * @return Floor being displayed.
	 */
	public Floor getFloor() {
		return floor;
	}

	@Override
	public void changeFloor(int index) {
		// TODO Auto-generated method stub

	}

}
