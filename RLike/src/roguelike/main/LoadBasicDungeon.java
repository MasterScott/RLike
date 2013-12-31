package roguelike.main;

import java.awt.Color;

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
		p.setImage(GraphicFile.CLASSM, 2, 5);
		
		BaseDungeon dungeon = new BaseDungeon();
		dungeon.generateFloor();
		dungeon.actors.add(p);
		p.setFloor(dungeon);
		
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
