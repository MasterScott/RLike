package roguelike.actors.abilities;

import roguelike.actors.Actor;
import roguelike.actors.Creature;

/**
 * Class containing logic for basic melee attacks.
 * 
 * @author Dan
 * 
 */
public class MeleeAttack implements Action {

	private final String NAME = "Melee Attack";
	Creature performer, recipient;
	int damage;

	/**
	 * Creates a new MeleeAttack object for the specified creature - this allows
	 * the creature to perform melee attacks.
	 * 
	 * @param performer
	 *            Creature performing the attack.
	 */
	public MeleeAttack(Creature performer, Creature recipient) {
		this.performer = performer;
		this.recipient = recipient;
	}

	@Override
	public void doAction() {
		// TODO Robust damage calculations.
		if (!(recipient instanceof Creature))
			return;

		damage = (int) Math.random() * 2;
		((Creature) recipient).hp.current -= damage;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getActionText() {
		return performer.getName() + " attacks " + ((Creature) recipient).getName() + " for " + damage + ".";
	}

	@Override
	public void setRecipient(Actor recipient) {
		this.recipient = (Creature) recipient;		
	}

}
