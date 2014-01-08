package roguelike.actors;

import java.util.ArrayList;

import roguelike.actors.Item.ItemType;
import roguelike.actors.util.Stat;


/**
 * Contains all relevant data for the player's avatar in-game.
 * 
 * @author Dan
 * 
 */
public class Player extends Creature {

	public boolean movement;
	public long exp;
	private ArrayList<Item> inventory;
	
	public Player(int x, int y) {
		super(x, y, null);

		exp = 0;
		traversable = false;
		
		// TODO Placeholder values
		hp = new Stat(15);
		mp = new Stat(6);
		strength = new Stat(12);
		intelligence = new Stat(11);
		dexterity = new Stat(14);
		
		// TODO Placeholder values
		inventory = new ArrayList<Item>();
		inventory.add(new Item(0, 0, "Salmon", ItemType.SWORD));
		inventory.add(new Item(0, 0, "Ham", ItemType.HELMET));
		inventory.add(new Item(0, 0, "Tropical Island Song", ItemType.MISC));
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
}
