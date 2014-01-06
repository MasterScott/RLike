package roguelike.etc;

import java.util.ArrayDeque;

/**
 * Deque that stores messages to display to the UI.
 * 
 * @author Dan
 * 
 */
public class MessageQueue extends ArrayDeque<String> {

	private static final long serialVersionUID = -4705336103574897171L;
	private final int CAPACITY = 200;

	public MessageQueue() {

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

	// Implement a method to retrieve the last 5 messages.
}
