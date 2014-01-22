package roguelike.actors;

import roguelike.ui.graphics.Graphic.GraphicFile;

public class Item extends Actor {

	private ItemType itemtype;
	private int quantity;

	public Item(int x, int y, ItemType itemtype) {
		super(x, y);
		this.itemtype = itemtype;
		this.traversable = true;
		this.quantity = 1;
	}

	public Item(int x, int y, String name, ItemType itemtype, GraphicFile gf, int index) {
		this(x, y, itemtype);
		this.name = name;
		this.gf = gf;
		this.index = index;
		setImage(gf, index);
	}

	/**
	 * Returns type of item this is.
	 * 
	 * @return Type of item.
	 */
	public ItemType getItemType() {
		return itemtype;
	}

	/**
	 * Sets this item to the specified type.
	 * 
	 * @param itemtype
	 *            Type of item.
	 */
	public void setItemType(ItemType itemtype) {
		this.itemtype = itemtype;
	}

	/**
	 * Returns how many instances of this item this represents.
	 * 
	 * @return Number of items.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the number of items this represents.
	 * 
	 * @param quantity
	 *            Number of items.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Adds the specified amount to the number of items this represents.
	 * 
	 * @param quantity
	 *            Number of items.
	 */
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	@Override
	public String toString() {
		return name + " GF: " + gf.name() + " Index: " + index;
	}

	/**
	 * Enumeration of item types. All items must have an item type assigned.
	 * 
	 * @author Dan
	 * 
	 */
	public static enum ItemType {
		// @formatter:off
		AMULET(true),
		ARMOR(true),
		AXE(true),
		BOOTS(true),
		BOW(true),
		CLOAK(true),
		CROSSBOW(true),
		DAGGER(true),
		FOOD(false),
		GLOVES(true),
		HELMET(true),
		MACE(true),
		MISC(false),
		MONEY(false),
		POLEARM(true),
		POTION(false),
		RING(true),
		SCROLL(false),
		SHIELD(true),
		SLING(true),
		SPELLBOOK(false),
		STAFF(true),
		SWORD(true),
		WAND(false);
		// @formatter:on

		public boolean equippable;

		private ItemType(boolean equippable) {
			this.equippable = equippable;
		}
	}
}
