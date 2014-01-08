package roguelike.actors;

public class Item extends Actor {

	private ItemType itemtype;

	public Item(int x, int y, ItemType itemtype) {
		super(x, y);
		this.itemtype = itemtype;
		this.traversable = true;
	}
	
	public Item(int x, int y, String name, ItemType itemtype) {
		this(x, y, itemtype);
		this.name = name;
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
