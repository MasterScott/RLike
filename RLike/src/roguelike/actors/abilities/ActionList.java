package roguelike.actors.abilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import roguelike.actors.Actor;
import roguelike.etc.Session;

/**
 * Enumerated type that contains all actions performed by creatures to recipients.
 * 
 * @author Dan
 * 
 */
public enum ActionList implements Action {
	MELEE_ATTACK("meleeAttack", "Melee Attack", "attacks")

	;

	private Method m;
	private String name, action;
	private Actor performer, recipient;
	private int damage;

	private ActionList(String methodName, String name, String action) {
		try {
			// Use reflection to create an instance of the specified method.
			this.m = ActionMethods.class.getMethod(methodName, Actor.class, Actor.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		this.name = name;
		this.action = action;
	}

	@Override
	public void doAction(Actor performer, Actor recipient) {
		this.performer = performer;
		this.recipient = recipient;
		try {
			m.invoke(null, performer, recipient);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		damage = ActionMethods.getDamage();
		
		// Add message text to the stack.
		Session.messageStack.addFirst(getActionText());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getActionText() {
		if (performer == null || recipient == null)
			return null;

		return performer.getName() + " " + action + " " + recipient.getName() + " for " + damage + " damage.";
	}

}
