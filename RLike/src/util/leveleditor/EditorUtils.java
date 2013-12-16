package util.leveleditor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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
	 * @param objects
	 *            Set of object descriptions from the editor.
	 * @param tileDef
	 *            HashMap tying descriptions of tiles to single characters.
	 * @param objectDef
	 *            HashMap tying descriptions of objects to single characters.
	 */
	public static void saveMap(String[][] tiles, String[][] objects, HashMap<String, Character> tileDef,
			HashMap<String, Character> objectDef) {
		PrintWriter writer = null;

		try {
			writer = new PrintWriter("test.rlmap", "UTF-8");
			
			/*
			 * Print tile portion of map.
			 */
			writer.println("#START TILES#");
			for (int y = 0; y < tiles[0].length; y++) {
				for (int x = 0; x < tiles.length; x++) {
					writer.print(tileDef.get(tiles[x][y]));
				}
				writer.println();
			}
			writer.println("#END TILES");
			writer.println();
			
			/*
			 * Print any objects that lay on top of tiles.
			 */
			writer.println("#START OBJECTS#");
			for (int y = 0; y < objects[0].length; y++) {
				for (int x = 0; x < objects.length; x++) {
					writer.print(objectDef.get(objects[x][y]));
				}
				writer.println();
			}
			writer.println("#END OBJECTS#");
			writer.println();

			/*
			 * Print key for tiles.
			 */
			writer.println("#START TILE KEY#");
			for (Map.Entry<String, Character> entry : tileDef.entrySet()) {
				String key = entry.getKey();
				Character value = entry.getValue();
				writer.println(value + ": " + key);
			}
			writer.println("#END TILE KEY#");
			writer.println();
			
			/*
			 * Print key for objects.
			 */
			writer.println("#START OBJECT KEY#");
			for (Map.Entry<String, Character> entry : objectDef.entrySet()) {
				String key = entry.getKey();
				Character value = entry.getValue();
				writer.println(value + ": " + key);
			}
			writer.println("#END OBJECT KEY#");
			writer.println();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}

	}
}
