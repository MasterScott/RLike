package roguelike.etc.random;

import roguelike.actors.Item.ItemType;
import roguelike.ui.graphics.Graphic.GraphicFile;

/**
 * Static references to enumerated types for use with RandomGenerator.
 * 
 * @author Dan
 * 
 */
public class ObjectLists {

	/**
	 * Enumerated type that represents creatures that can spawn.
	 * 
	 * @author Dan
	 * 
	 */
	public static enum Creatures implements WeightedRandom {
		// @formatter:off
		GOBLIN(0, 8, 10), 
		RAT_WHITE(0, 4, 18), 
		BAT_BROWN(0, 5, 15), 
		SNAKE_GREEN(0, 6, 11), 
		
		;
		// @formatter:on

		private int minLevel;
		private int maxLevel;
		private int weight;

		public int getMinLevel() {
			return minLevel;
		}

		public int getMaxLevel() {
			return maxLevel;
		}

		public int getWeight() {
			return weight;
		}

		public String getName() {
			return name();
		}

		private Creatures(int minLevel, int maxLevel, int weight) {
			this.minLevel = minLevel;
			this.maxLevel = maxLevel;
			this.weight = weight;
		}
	}

	/**
	 * Enumerated type representing probability for each item type to
	 * drop/spawn.
	 * 
	 * @author Dan
	 * 
	 */
	public static enum Items implements WeightedRandom {
		// @formatter:off
		AMULET(1),
		ARMOR(5),
		AXE(4),
		BOOTS(3),
		BOW(3),
		CLOAK(3),
		CROSSBOW(2),
		DAGGER(4),
		FOOD(6),
		GLOVES(3),
		HELMET(3),
		MACE(4),
		MISC(3),
		MONEY(50),
		POLEARM(4),
		POTION(6),
		RING(2),
		SCROLL(6),
		SHIELD(3),
		SLING(1),
		SPELLBOOK(1),
		STAFF(3),
		SWORD(4),
		WAND(2)
				
		;
		// @formatter:on

		private int weight;

		public int getMinLevel() {
			return 0;
		}

		public int getMaxLevel() {
			return 1000;
		}

		public int getWeight() {
			return weight;
		}

		public String getName() {
			return name();
		}

		private Items(int weight) {
			this.weight = weight;
		}

	}
	

	public static enum Swords implements WeightedRandom {
		// @formatter:off
		
				
		;
		// @formatter:on

		private int weight;
		private int minLevel;
		private int maxLevel;
		public GraphicFile gf;
		public int row, col;

		public int getMinLevel() {
			return minLevel;
		}

		public int getMaxLevel() {
			return maxLevel;
		}

		public int getWeight() {
			return weight;
		}

		public String getName() {
			return name();
		}
		
		public ItemType getItemType() {
			return ItemType.SWORD;
		}
	

		private Swords(int minLevel, int maxLevel, int weight, GraphicFile gf, int row, int col) {
			this.minLevel = minLevel;
			this.maxLevel = maxLevel;
			this.weight = weight;
		}

	}
}
