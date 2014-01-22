package roguelike.actors.util;

import roguelike.actors.AI.BasicAI;
import roguelike.actors.classes.Classes;
import roguelike.ui.graphics.Graphic.GraphicFile;

/**
 * Enumerated type for all regular creatures.
 * 
 * @author Dan
 * 
 */
public enum CreatureTemplate {
	//@formatter:off
	GOBLIN("Goblin", 10, 0, 9, 9, 10, true, 4, null, BasicAI.class, GraphicFile.MONSTER1, 42), 
	RAT_WHITE("White Rat", 4, 0, 5, 3, 11, true, 1, null, BasicAI.class, GraphicFile.MONSTER1, 24), 
	BAT_BROWN("Brown Bat", 5, 0, 4, 3, 14, true, 2, null, BasicAI.class, GraphicFile.MONSTER4, 1),
	SNAKE_GREEN("Green Snake", 7, 0, 5, 4, 12, true, 2, null, BasicAI.class, GraphicFile.MONSTER4, 13);
	//@formatter:on

	public String name;
	public int hp, mp, str, intl, dex, index;
	public long xp;
	public boolean hostile;
	public Classes c;
	public Class<?> ai;
	public GraphicFile gf;

	private CreatureTemplate(String name, int hp, int mp, int str, int intl, int dex, boolean hostile, long xp,
			Classes c, Class<?> ai, GraphicFile gf, int index) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.str = str;
		this.intl = intl;
		this.dex = dex;
		this.hostile = hostile;
		this.xp = xp;
		this.c = c;
		this.ai = ai;
		this.gf = gf;
		this.index = index;
	}
}
