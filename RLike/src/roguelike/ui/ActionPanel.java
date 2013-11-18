package roguelike.ui;

import java.awt.Font;
import java.awt.Graphics;

import roguelike.actors.Actor;
import roguelike.etc.ActionKeyListener;
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
			throw new NullPointerException(
					"A floor was not defined for this ActionPanel.");

		/*
		 * Draw each actor currently in this floor to the screen.
		 */
		Actor[][] actorGrid = floor.getCurrentGrid();
		for (int x = 0; x < actorGrid.length; ++x) {
			for (int y = 0; y < actorGrid[0].length; ++y) {
				if (actorGrid[x][y] != null) {
					Actor actor = actorGrid[x][y];
					g.setColor(actor.getColor());
					g.drawString(String.valueOf(actor.getIcon()), actor.getX()
							* xScale + 1, actor.getY() * yScale + 11);
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

}
