package roguelike.main;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JDesktopPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import roguelike.actors.Player;
import roguelike.etc.Session;
import roguelike.ui.ActionPanel;
import roguelike.ui.InventoryFrame;
import roguelike.ui.StatsPanel;
import roguelike.ui.Window;
import roguelike.ui.graphics.Graphic.GraphicFile;
import roguelike.world.BaseDungeon;

public class LoadInventory {

	public static void main(String[] args) {
		
		Session.initialize();
        
		Window w = new Window();
		JDesktopPane jdp = new JDesktopPane();

		w.add(jdp);
		Player p = new Player(20, 10);
		Session.player = p;
		p.setImage(GraphicFile.CLASSM, 2, 5);

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
		jdp.add(ap);
		jdp.add(sp);
		ap.setFloor(dungeon);

		w.setVisible(true);
		ap.requestFocusInWindow();

		InventoryFrame ip = new InventoryFrame();
		jdp.add(ip);
		ip.setVisible(true);
		
		
	}
}
