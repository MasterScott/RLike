package roguelike.etc;

import java.util.ArrayList;

import roguelike.actors.Player;
import roguelike.ui.Window;
import roguelike.world.Floor;

/**
 * Class containing variables that need to be accessed from many different
 * places.
 * 
 * @author Dan
 * 
 */
public class Session {

	/**
	 * Current player of the session.
	 */
	public static Player player;

	/**
	 * List of all floors in the session.
	 */
	public static ArrayList<Floor> floors;
	
	/**
	 * Window the game is running in.
	 */
	public static Window window;
}
