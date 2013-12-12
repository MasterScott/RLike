package roguelike.actors.abilities;

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
	public void doAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRecipient(Actor recipient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getActionText() {
		// TODO Auto-generated method stub
		return null;
	}
}
