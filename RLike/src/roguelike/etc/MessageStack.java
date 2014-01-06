package roguelike.etc;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Deque that stores messages to display to the UI.
 * 
 * @author Dan
 * 
 */
public class MessageStack extends ArrayDeque<String> {

	private static final long serialVersionUID = -4705336103574897171L;
	private final int CAPACITY = 200;

	public MessageStack() {

	}

	@Override
	public void addFirst(String e) {
		super.addFirst(e);

		/*
		 * Don't want to store ALL messages that have ever been generated. We're
		 * only concerned with more recent messages as specified by capacity.
		 */
		if (size() > CAPACITY) {
			removeLast();
		}
	}

	/**
	 * Returns the five most recent messages for display.
	 * @return
	 */
	public String[] getRecent() {
		if (isEmpty()) return null;
		
		// We want at most five messages.
		int size = Math.min(size(), 5);
		String[] result = new String[size];
		
		Iterator<String> iter = this.iterator();
		for (int i = 0; i < size; i++) {
			result[i] = iter.next();
		}
		
		return result;
	}
	
}
