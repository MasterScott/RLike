package util.tileviewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import roguelike.ui.graphics.Graphic;

public class GraphicPanel extends JPanel implements MouseListener {

	public static Image TILESET;
	public static String TILESET_PATH;
	private Image tile;

	public GraphicPanel() {
		setBounds(0, 0, 500, 800);
		setBackground(Color.BLACK);
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (TILESET != null) {
			g.drawImage(TILESET, 0, 0, null);
		}

		g.drawString("Current Tile: ", 40, 700);
		if (tile != null) {
			g.drawImage(tile, 100, 700, null);
			System.out.println("drawing tile...");
		}

	}



	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (TILESET != null && x < TILESET.getWidth(null) && y < TILESET.getHeight(null));
			tile = Graphic.getImage(TILESET_PATH, x / 32, y / 32);

		this.getParent().repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
