package roguelike.actors.abilities;

import roguelike.actors.Actor;
import roguelike.actors.Creature;

public class ActionMethods {
	
	private static int damage;
	
	public static void meleeAttack(Actor performer, Actor recipient) {
		damage = (int) (Math.random() * 2 + 1);
		((Creature) recipient).hp.current -= (int) (Math.random() * 2 + 1);
	}
	
	public static int getDamage() {
		return damage;
	}
}
