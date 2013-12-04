package roguelike.actors.classes;

public interface RLClass {

	public int getBonusStr();
	public int getBonusDex();
	public int getBonusInt();
	public int getBonusHP();
	public Skill[] getSkills();
	public String getName();
	
}
