package roguelike.etc;

import java.util.concurrent.LinkedBlockingQueue;

import roguelike.actors.abilities.Action;

public class ActionQueue extends LinkedBlockingQueue<Action>{

	private static final long serialVersionUID = 683038145835293839L;

	public ActionQueue() {
		
	}
	
	public void doAllActions() {
		while (!isEmpty()) {
			
			
		}
	}
}
