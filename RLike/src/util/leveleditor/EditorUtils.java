package util.leveleditor;

import java.util.HashMap;

/**
 * Miscellaneous utilities for the level editor.
 * 
 * @author Dan
 * 
 */
public class EditorUtils {

	/**
	 * Saves the current map to a file.
	 * 
	 * @param tiles
	 *            Set of tile descriptions from the editor.
	 * @param definitions
	 *            HashMap tying descriptions to single characters.
	 */
	public static void saveMap(String[][] tiles, HashMap<String, Character> definitions) {
		for (int y = 0; y < tiles[0].length; y++) {
			for (int x = 0; x < tiles.length; x++) {
				System.out.print(definitions.get(tiles[x][y]));
			}
			System.out.println();
		}
	}
}
