package roguelike.etc;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import roguelike.actors.Player;
import roguelike.ui.InventoryFrame;
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
	public static ArrayList<Floor> floors = new ArrayList<Floor>();

	/**
	 * Window the game is running in.
	 */
	public static Window window;

	/**
	 * How many turns have passed in the current session.
	 */
	public static long turnCount = 0;

	/**
	 * Queue that holds all actions.
	 */
	public static ActionQueue actionQueue = new ActionQueue();

	/**
	 * Stack that holds all messages to be delivered to the UI.
	 */
	public static MessageStack messageStack = new MessageStack();

	/**
	 * Inventory window for the player.
	 */
	public static InventoryFrame inventoryFrame;

	/**
	 * Setting of variables. To be called before all other methods in the
	 * application.
	 */
	public static void initialize() {
		UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(new Color(75, 75, 75)));
		UIManager.put("InternalFrame.activeTitleForeground", new ColorUIResource(new Color(225, 225, 225)));
		inventoryFrame = new InventoryFrame();
	}
}
