package roguelike.main;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import roguelike.actors.Feature.FeatureType;
import roguelike.actors.Player;
import roguelike.etc.Session;
import roguelike.ui.ActionPanel;
import roguelike.ui.StatsPanel;
import roguelike.ui.Window;
import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;
import roguelike.world.Cave;
import roguelike.world.Floor;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Session.player = new Player('@', Color.WHITE, 2, 2);

		Window w = new Window(100, 10, 1536, 1024);

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
		f.createAccessibleStairs(Session.player, FeatureType.DOWNSTAIRS, GraphicFile.DUNGEON, 7, 4);

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
