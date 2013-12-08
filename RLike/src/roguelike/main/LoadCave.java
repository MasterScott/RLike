package roguelike.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import roguelike.actors.Creature;
import roguelike.actors.Feature.FeatureType;
import roguelike.actors.Player;
import roguelike.actors.Stat;
import roguelike.etc.Session;
import roguelike.ui.ActionPanel;
import roguelike.ui.StatsPanel;
import roguelike.ui.Window;
import roguelike.ui.graphics.Graphic.GraphicFile;
import roguelike.world.Cave;
import roguelike.world.Floor;

public class LoadCave {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Session.player = new Player('@', Color.WHITE, 2, 2);

		Window w = new Window();

		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ActionPanel p = new ActionPanel();
		StatsPanel s = new StatsPanel();

		Floor f = new Cave();
		f.generateFloor();

		Point point = f.getRandomOpenTile();
		Session.player = new Player('@', Color.WHITE, point.x, point.y);
		Session.player.setFloor(f);
		Session.player.setImage(GraphicFile.CLASSM, 2, 5);
		f.actors.add(Session.player);
		f.createAccessibleStairs(Session.player, FeatureType.DOWNSTAIRS, GraphicFile.DUNGEON, 4, 7);

		for (int i = 0; i < 4; i++) {
			Point ps = f.getRandomOpenTile();
			Creature c = new Creature('g', Color.DARK_GRAY, ps.x, ps.y);
			c.setImage(GraphicFile.MONSTER1, 3, 1);
			c.hp = new Stat(10);
			f.actors.add(c);
		}
		
		p.setFloor(f);

		p.repaint();
		s.repaint();
		p.setPreferredSize(p.getSize());
		p.setMaximumSize(p.getSize());
		
		w.add(p);
		w.add(s);
		p.setVisible(true);
		p.setBackground(Color.BLACK);

		w.setVisible(true);

	}

}
