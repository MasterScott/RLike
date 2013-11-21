package roguelike.ui.los;

import java.util.ArrayList;

import roguelike.actors.Actor;
import roguelike.world.Floor;

/**
 * Class used for all line of sight calculations for an actor.
 * 
 * @author Dan
 * 
 */
public class LOS {

	private Delta[] deltas = { new Delta(0, -1, -1, -1),
			new Delta(-1, 0, -1, 1), new Delta(-1, 0, -1, -1),
			new Delta(0, 1, -1, 1), new Delta(0, 1, 1, 1),
			new Delta(1, 0, 1, 1), new Delta(1, 0, 1, -1),
			new Delta(0, -1, 1, -1) };
	private int range;
	private Actor actor;
	private ArrayList<Actor> inSight;

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
		inSight = new ArrayList<Actor>();

		for (Delta d : deltas) {
			recursiveShadowCast(actor.getX(), actor.getY(), d, 0);
		}

		inSight.add(actor);

		return inSight;
	}

	/**
	 * Recursive shadow-casting method. Adds all actors in a direction up until
	 * an object obscures view.
	 * 
	 * @param x x-coordinate of actor.
	 * @param y y-coordinate of actor.
	 * @param d Delta for the specified direction.
	 * @param dist Distance from actor.
	 */
	private void recursiveShadowCast(int x, int y, Delta d, int dist) {
		Floor floor = actor.getFloor();
		Actor thisActor = floor.getActorAt(x, y);
		if (dist + 1 <= range) {
			if (thisActor != null && !thisActor.isTraversable()) {
				if (!inSight.contains(thisActor))
					inSight.add(thisActor);
			} else {
				if (thisActor != null && !inSight.contains(thisActor))
					inSight.add(thisActor);

				recursiveShadowCast(x + d.dx1, y + d.dy1, d, dist + 1);
				recursiveShadowCast(x + d.dx2, y + d.dy2, d, dist + 1);
			}
		}
	}

	private class Delta {
		int dx1, dx2, dy1, dy2;

		public Delta(int dx1, int dy1, int dx2, int dy2) {
			this.dx1 = dx1;
			this.dy1 = dy1;
			this.dx2 = dx2;
			this.dy2 = dy2;
		}
	}
}
