package roguelike.ui.los;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;

import roguelike.actors.Actor;
import roguelike.actors.Player;
import roguelike.etc.LineIterator;
import roguelike.world.Floor;

/**
 * Class used for all line of sight calculations for an actor.
 * 
 * @author Dan
 * 
 */
public class LOS {

	private int range;
	private Actor actor;

	/**
	 * Create a new line of sight object for the specified actor.
	 * 
	 * @param actor
	 *            Actor to create LOS for.
	 */
	public LOS(Actor actor) {
		this.range = 6;
		this.actor = actor;
	}

	/**
	 * Returns a list of things that are visible to this actor.
	 * 
	 * @param floor
	 *            Floor the actor is on.
	 * @return List of things that are visible to this actor.
	 */
	public ArrayList<Actor> getVisible() {
		Floor floor = actor.getFloor();
		ArrayList<Actor> result = new ArrayList<Actor>();

		int x = actor.getX();
		int y = actor.getY();

		int n = 50;
		for (int i = 0; i < n; i++) {
            double t = 2 * Math.PI * i / n;
            int x1 = (int) Math.round(x + range * Math.cos(t));
            int y1 = (int) Math.round(y + range * Math.sin(t));
            Line2D line = new Line2D.Double(x, y, x1, y1);
            
            Point2D current;
            boolean blocked = false;
            for(Iterator<Point2D> iter = new LineIterator(line); iter.hasNext();) {
                current = iter.next();
                Actor thisActor = floor.getActorAt((int) current.getX(), (int) current.getY());
				if (thisActor != null && !blocked) {
					result.add(thisActor);
					if (!thisActor.isTraversable() && thisActor.getClass() != Player.class) blocked = true;
				} 
            }
            
        }

		return result;
	}

	protected int calculateY(int x, boolean isPositive) {
		int sign;
		if (isPositive) {
			sign = 1;
		} else
			sign = -1;

		float y = (float) (sign * Math
				.sqrt(Math.pow(range, 2) - Math.pow(x, 2)));
		System.out.println("Y: " + y);
		int result = Math.round(y);

		System.out.println(result);
		return result;
	}
}
