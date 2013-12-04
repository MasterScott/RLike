package roguelike.actors.actions;

import roguelike.actors.Actor;

public class Skill implements Action{

	private String name;
	
	public Skill(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void doAction(Actor recipient) {
		// TODO Auto-generated method stub
		
	}
}
