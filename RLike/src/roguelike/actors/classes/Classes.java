package roguelike.actors.classes;

public enum Classes implements RLClass {
	MAGE(-1, 1, 2, -1),
	WIZARD(-2, -1, 3, -2),
	MONK(0, 1, 1, 0),
	KNIGHT(2, 0, 0, 2),
	PRIEST(-1, 0, 2, 0),
	FIGHTER(2, 1, -2, 2),
	THIEF(0, 3, 0, 0),
	GLADIATOR(2, 2, -4, 3);

	private int bonusStr, bonusDex, bonusInt, bonusHP;
	
	public int getBonusStr() {
		return bonusStr;
	}

	public int getBonusDex() {
		return bonusDex;
	}

	public int getBonusInt() {
		return bonusInt;
	}

	public int getBonusHP() {
		return bonusHP;
	}

	public Skill[] getSkills() {
		// TODO Implement in the future.
		return null;
	}
	
	public String getName() {
		return this.name();
	}

	private Classes(int bonusStr, int bonusDex, int bonusInt, int bonusHP) {
		this.bonusStr = bonusStr;
		this.bonusDex = bonusDex;
		this.bonusInt = bonusInt;
		this.bonusHP = bonusHP;
	}
}
