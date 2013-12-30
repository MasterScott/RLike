package roguelike.etc.random;

public class ObjectLists {

	public static enum Creatures implements WeightedRandom {
		GOBLIN(0, 8, 10),
		RAT_WHITE(0, 4, 20),
		BAT_BROWN(0, 5, 15),
		;
		
		private int minLevel;
		private int maxLevel;
		private int weight;
		
		public int getMinLevel() { return minLevel; }
		public int getMaxLevel() { return maxLevel; }
		public int getWeight() { return weight; }

		private Creatures(int minLevel, int maxLevel, int weight) {
			this.minLevel = minLevel;
			this.maxLevel = maxLevel;
			this.weight = weight;
		}

	}
}
