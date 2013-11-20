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

		int x1 = actor.getX();
		int y1 = actor.getY();

		// Upper portion of circle sqrt(49-x^2)
		// Bottom portion of circle -sqrt(49-x^2)

		for (int i = 0; i < floor.XMAX; i++) {
			for (int j = 0; j < floor.YMAX; j++) {
				int a = i - x1;
				int b = j - y1;
				if (a * a + b * b <= range * range) {
					Actor thisActor = floor.getActorAt(x1 + a, y1 + b);
					if (thisActor != null) {
						result.add(thisActor);
					}
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
