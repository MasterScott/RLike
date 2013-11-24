package roguelike.ui;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import roguelike.actors.Actor;
import roguelike.actors.Creature;
import roguelike.actors.Tile;
import roguelike.etc.ActionKeyListener;
import roguelike.etc.Session;
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
		fontSize = 14;
		setFocusable(true);
		addKeyListener(this);

		// TODO Make these into variables.
		setBounds(0, 0, 640, 480);

		xScale = 10;
		yScale = 16;
		xBound = getBounds().width;
		yBound = getBounds().height;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("Courier", Font.PLAIN, fontSize));

		if (floor == null)
			throw new NullPointerException("A floor was not defined for this ActionPanel.");

		ArrayList<Actor> inSight = Session.player.getLOS().getVisible();

		/*
		 * If actor is in sight, display in normal color and set previously seen
		 * to true. If not in sight, display at an obscured color if the actor
		 * is a tile and has been seen before. Otherwise, do nothing.
		 */
		for (Actor actor : floor.actors) {
			if (inSight.contains(actor)) {
				/*
				 * Make sure tiles take lowest precedence - always display other
				 * actors if possible.
				 */
				Creature c = floor.getCreatureAt(actor.getX(), actor.getY());
				if (c != null)
					actor = c;
				g.setColor(actor.getColor());
				g.drawString(String.valueOf(actor.getIcon()), actor.getX() * xScale + 1, actor.getY() * yScale + 11);
				actor.setPreviouslySeen(true);
			} else if (actor.getPreviouslySeen() && actor instanceof Tile) {
				g.setColor(((Tile) actor).getObscuredColor());
				g.drawString(String.valueOf(actor.getIcon()), actor.getX() * xScale + 1, actor.getY() * yScale + 11);
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
