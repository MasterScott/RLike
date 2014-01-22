package roguelike.tests;

import java.awt.Color;
import java.awt.Point;

import roguelike.actors.Player;
import roguelike.etc.Session;
import roguelike.ui.ActionPanel;
import roguelike.ui.StatsPanel;
import roguelike.ui.Window;
import roguelike.ui.graphics.Graphic.GraphicFile;
import roguelike.world.BaseDungeon;

public class LoadBasicDungeon {

	public static void main(String[] args) {
		Window w = new Window();
		
		Player p = new Player(20, 10);
		Session.player = p;
		p.setImage(GraphicFile.CLASSM, 20);
		
		BaseDungeon dungeon = new BaseDungeon();
		dungeon.generateFloor();
		dungeon.actors.add(p);
		p.setFloor(dungeon);
		Point point = dungeon.getRandomOpenTile();
		p.setCoords(point.x, point.y);
		
		StatsPanel sp = new StatsPanel();
		sp.setPreferredSize(sp.getSize());
		sp.setMaximumSize(sp.getSize());
		ActionPanel ap = new ActionPanel();
		ap.setBackground(Color.BLACK);
		w.add(ap);
		w.add(sp);
		ap.setFloor(dungeon);
		
		w.setVisible(true);
	}
	
}
