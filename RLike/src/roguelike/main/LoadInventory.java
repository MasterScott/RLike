package roguelike.main;

import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class LoadInventory {

	public static void main(String[] args) {
		
		UIDefaults defaults = UIManager.getDefaults();
        System.out.println(defaults.size()+ " properties deffined !");
        String[ ] colName = {"Key", "Value"};
        String[ ][ ] rowData = new String[ defaults.size() ][ 2 ];
        int i = 0;
        for(Enumeration e = defaults.keys(); e.hasMoreElements(); i++){
            Object key = e.nextElement();
            rowData[ i ] [ 0 ] = key.toString();
            rowData[ i ] [ 1 ] = ""+defaults.get(key);
            System.out.println(rowData[i][0]+" ,, "+rowData[i][1]);
        }
        JFrame f = new JFrame("UIManager properties default values");
        JTable t = new JTable(rowData, colName);
        f.setContentPane(new JScrollPane(t));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
        
//      Window w = new Window();
//      JDesktopPane jdp = new JDesktopPane();
//		
//		w.add(jdp);
//		Player p = new Player(20, 10);
//		Session.player = p;
//		p.setImage(GraphicFile.CLASSM, 2, 5);
//		
//		BaseDungeon dungeon = new BaseDungeon();
//		dungeon.generateFloor();
//		dungeon.actors.add(p);
//		p.setFloor(dungeon);
//		Point point = dungeon.getRandomOpenTile();
//		p.setCoords(point.x, point.y);
//		
//		StatsPanel sp = new StatsPanel();
//		sp.setPreferredSize(sp.getSize());
//		sp.setMaximumSize(sp.getSize());
//		ActionPanel ap = new ActionPanel();
//		ap.setBackground(Color.BLACK);
//		jdp.add(ap);
//		jdp.add(sp);
//		ap.setFloor(dungeon);
//		
//		w.setVisible(true);
//		
//		InventoryFrame ip = new InventoryFrame();
//		jdp.add(ip);
//		ip.setVisible(true);
		
	}
}
