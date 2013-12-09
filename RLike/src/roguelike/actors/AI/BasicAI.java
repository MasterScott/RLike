package roguelike.actors.AI;

import java.util.ArrayList;

import roguelike.actors.Actor;
import roguelike.actors.Creature;
import roguelike.etc.Session;
import roguelike.ui.los.LOS;

/**
 * Basic artificial intelligence. Will move toward and attack hostiles when in
 * range but will otherwise do nothing interesting.
 * 
 * @author Dan
 * 
 */
public class BasicAI extends AI {

	/**
	 * Creates a new basic artificial intelligence for the given creature.
	 * 
	 * @param c
	 *            Creature to give AI to.
	 */
	public BasicAI(Creature c) {
		super(c);
	}

	@Override
	public void doPrioritizedAction() {
		LOS los = c.getLOS();
		ArrayList<Actor> actors = los.getVisible();
		
		for (Actor actor: actors) {
			if (actor == Session.player){
				// TODO Move toward player.
				int i = -1;
				if (Math.random() > 0.5) i = 1;
				if (!c.getFloor().checkCollision(c.getX(), c.getY() + i))
					c.setY(c.getY() + 1);;
			}
		}
		
	}

}
