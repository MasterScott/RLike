package roguelike.actors;


public class Feature extends Tile {

	private FeatureType featureType;

	/**
	 * Creates a feature with the specified traversability.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @param traversable
	 *            Whether or not this tile can be walked over.
	 * @param featureType
	 *            What kind of feature this is - limited to the static enum
	 *            FeatureType within this class.
	 */
	public Feature(int x, int y, boolean traversable,
			FeatureType featureType) {
		super(x, y, traversable);

		this.featureType = featureType;
	}

	/**
	 * Returns type of feature this is.
	 * 
	 * @return Type of feature this is.
	 */
	public FeatureType getFeatureType() {
		return featureType;
	}

	/**
	 * Contains static references of all feature types.
	 * 
	 * @author Dan
	 * 
	 */
	public static enum FeatureType {
		UPSTAIRS, DOWNSTAIRS
	}

}
