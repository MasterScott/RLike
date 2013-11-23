package roguelike.main;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

import roguelike.actors.Feature.FeatureType;
import roguelike.actors.Player;
import roguelike.etc.Session;
import roguelike.ui.ActionPanel;
import roguelike.ui.StatsPanel;
import roguelike.world.Cave;
import roguelike.world.Floor;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Session.player = new Player('@', Color.WHITE, 2, 2);
		
		JFrame j = new JFrame();
		j.setBounds(100, 100, 800, 480);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ActionPanel p = new ActionPanel();
		StatsPanel s = new StatsPanel();
		
		Floor f = new Cave();
		f.generateFloor();
		
		Point point = f.getRandomOpenTile();
		Session.player = new Player('@', Color.WHITE, point.x, point.y);
		Session.player.setFloor(f);
		f.actors.add(Session.player);
		f.createAccessibleStairs(Session.player, FeatureType.DOWNSTAIRS);

		p.setFloor(f);
		
		p.repaint();
		s.repaint();
		j.add(p);
		j.add(s);
		p.setVisible(true);
		p.setBackground(Color.BLACK);
		
		j.setVisible(true);

		
	}

}
