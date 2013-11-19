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
		this.range = 7;
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

		for (int i = x - range; i < x + range; ++i) {
			for (int j = y - range; j < y + range; ++j) {
				if (i >= 0 && i < floor.XMAX && j >= 0 && j < floor.YMAX) {
					Actor thisActor = floor.getActorAt(i, j);
					if (thisActor != null)
						result.add(thisActor);
				}
			}
		}

		return result;
	}
}
