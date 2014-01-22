package roguelike.actors;

import java.util.ArrayList;

import roguelike.actors.Item.ItemType;
import roguelike.actors.util.Stat;
import roguelike.ui.graphics.Graphic.GraphicFile;

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

	/**
	 * Create the player.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	public Player(int x, int y) {
		super(x, y, null);

		exp = 0;
		traversable = false;
		inventory = new ArrayList<Item>();

		// TODO Placeholder values
		hp = new Stat(15);
		mp = new Stat(6);
		strength = new Stat(12);
		intelligence = new Stat(11);
		dexterity = new Stat(14);

		// TODO Placeholder values
		inventory.add(new Item(0, 0, "Salmon", ItemType.SWORD, GraphicFile.ARMOR, 0));
		inventory.add(new Item(0, 0, "Ham", ItemType.HELMET, GraphicFile.FOOD, 3));
		inventory.add(new Item(0, 0, "Tropical Island Song", ItemType.MISC, GraphicFile.MISC, 15));
	}

	/**
	 * Returns contents of player's inventory.
	 * 
	 * @return
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	/**
	 * Adds an item to the player's inventory. If the player already has a copy
	 * of this item in the inventory, change the quantity of items in the
	 * inventory instead.
	 * 
	 * @param item
	 *            Item to be added to inventory.
	 */
	public void addToInventory(Item item) {
		boolean duplicateItem = false;
		for (Item invItem : inventory) {
			if (item.toString().equals(invItem.toString())) {
				invItem.addQuantity(item.getQuantity());
				duplicateItem = true;
			}
		}

		if (!duplicateItem) {
			inventory.add(item);
		}
	}
}
