package roguelike.actors;

import roguelike.ui.graphics.Graphic.GraphicFile;

public class Feature extends Tile {

	private FeatureType featureType;
	private Feature connectingStaircase;

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
	public Feature(int x, int y, boolean traversable, GraphicFile graphicFile, int row, int col, FeatureType featureType) {
		super(x, y, traversable, graphicFile, row, col);

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
	 * Used with the stairway feature type. Sets the staircase this stairway is
	 * connected to.
	 * 
	 * @param floor
	 *            Floor this stairway is connected to.
	 */
	public void setConnectingStaircase(Feature staircase) {
		this.connectingStaircase = staircase;
	}

	/**
	 * Used with the stairway feature type. Returns the staircase this stairway
	 * is connected to.
	 * 
	 * @return Staircase this stairway is connected to.
	 */
	public Feature getConnectingStaircase() {
		return connectingStaircase;
	}

	/**
	 * Contains static references of all feature types.
	 * 
	 * @author Dan
	 * 
	 */
	public static enum FeatureType {
		UPSTAIRS, DOWNSTAIRS, DOOR
	}

}
