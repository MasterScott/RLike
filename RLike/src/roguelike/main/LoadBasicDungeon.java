package roguelike.main;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

import roguelike.actors.Player;
import roguelike.etc.Session;
import roguelike.ui.ActionPanel;
import roguelike.ui.StatsPanel;
import roguelike.ui.Window;
import roguelike.ui.graphics.Graphic.GraphicFile;
import roguelike.world.BasicDungeon;
import roguelike.world.Floor;

public class LoadBasicDungeon {

	public static void main(String[] args) {

		Session.player = new Player(2, 2);

		Window w = new Window(100, 10, 1536, 1024);

		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ActionPanel p = new ActionPanel();
		StatsPanel s = new StatsPanel();

		Floor f = new BasicDungeon();
		f.generateFloor();

		Point point = f.getRandomOpenTile();
		Session.player = new Player(point.x, point.y);
		Session.player.setFloor(f);
		Session.player.setImage(GraphicFile.CLASSM, 2, 5);
		f.actors.add(Session.player);
		//f.createAccessibleStairs(Session.player, FeatureType.DOWNSTAIRS, GraphicFile.DUNGEON, 4, 7);

		p.setFloor(f);

		p.repaint();
		s.repaint();
		w.add(p);
		w.add(s);
		p.setVisible(true);
		p.setBackground(Color.BLACK);

		w.setVisible(true);
	}

}
