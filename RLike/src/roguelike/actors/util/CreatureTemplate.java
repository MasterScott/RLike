package roguelike.actors.util;

import java.awt.Image;

import roguelike.actors.AI.BasicAI;
import roguelike.actors.classes.Classes;
import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;

public enum CreatureTemplate {
	GOBLIN("Goblin", 10, 0, 9, 9, 10, true, 4, null, BasicAI.class, Graphic.getImage(GraphicFile.MONSTER1, 7, 0)),
	RAT_WHITE("White Rat", 4, 0, 5, 3, 11, true, 1, null, BasicAI.class, Graphic.getImage(GraphicFile.MONSTER1, 4, 0)),
	;

	public String name;
	public int hp, mp, str, intl, dex;
	public long xp;
	public boolean hostile;
	public Classes c;
	public Class<?> ai;
	public Image img;

	private CreatureTemplate(String name, int hp, int mp, int str, int intl, int dex, boolean hostile, long xp, Classes c, Class<?> ai, Image img) {
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
		

		this.img = img;
	}
}
