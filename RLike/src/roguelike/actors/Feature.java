package roguelike.actors;

import java.awt.Color;

public class Feature extends Tile {

	private FeatureType featureType;

	/**
	 * Creates a feature with the specified traversability.
	 * 
	 * @param icon
	 *            Icon used to represent this tile.
	 * @param color
	 *            Color of icon used to represent this tile.
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
	public Feature(char icon, Color color, int x, int y, boolean traversable,
			FeatureType featureType) {
		super(icon, color, x, y, traversable);

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
