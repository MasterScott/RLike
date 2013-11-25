package util.tileviewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;

public class InfoPanel extends JPanel implements MouseListener {

	int x, y;

	public InfoPanel() {
		setBounds(500, 0, 200, 800);
		setBackground(Color.BLACK);
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("Courier", Font.PLAIN, 14));
		g.setColor(Color.WHITE);

		int drawY = 32;

		for (GraphicFile gf : GraphicFile.values()) {
			g.drawString(gf.name(), 520, drawY);
			drawY += 18;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x = e.getX();
		y = e.getY();

		if (x >= 500) {
			GraphicPanel.TILESET = Graphic.getImage(GraphicFile.values()[(y - 24) / 18].fileName);
			GraphicPanel.TILESET_PATH = GraphicFile.values()[(y - 24) / 18].fileName;
		}
			
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
