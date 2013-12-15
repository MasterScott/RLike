package util.leveleditor;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditorWindow extends JFrame implements MouseMotionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2789411093618047798L;

	EditorMenu m;
	EditorPanel p;
	int xStart = 0, yStart = 0;
	Image selectedImage;

	public EditorWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(2, 2, 2, 2));
		getContentPane().add(panel);
		m = new EditorMenu();
		panel.add(m);
		
		m.setParent(this);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(2, 2, 2, 2));
		getContentPane().add(panel_1);
		p = new EditorPanel();
		panel_1.add(p);
		
		p.setParent(this);

		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	private void updateCoords(int x, int y) {
		updateXStart();
		updateYStart();

		if (x >= xStart && y >= yStart) {
			/*
			 * Simplified equations. 31 * (x - xStart) / 1024 is the same as (x
			 * - xStart - (x - xStart) / 32) / 32, which just gets the current
			 * position minus the position the grid starts at and accounts for a
			 * one pixel space between each tile.
			 */
			m.setCoords(31 * (x - xStart) / 1024, 31 * (y - yStart) / 1024 - 1);
		} else {
			m.setCoords(0, 0);
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		updateCoords(e.getX(), e.getY());
		Point point = m.getCoords();
		if (selectedImage != null) {
			p.tiles[point.x][point.y].setIcon(new ImageIcon(selectedImage));
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		updateCoords(e.getX(), e.getY());
	}
	
	public void setSelectedImage(Image img) {
		this.selectedImage = img;
	}
	
	public Image getSelectedImage() {
		return selectedImage;
	}

	/**
	 * Updates the position where X should start tracking.
	 */
	private void updateXStart() {
		if (xStart == 0)
			xStart = m.getBounds().x + m.getBounds().width + 30;
	}

	/**
	 * Updates the position where Y should start tracking.
	 */
	private void updateYStart() {
		if (yStart == 0)
			yStart = p.getBounds().y + 5;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		Point point = m.getCoords();
		if (selectedImage != null) {
			p.tiles[point.x][point.y].setIcon(new ImageIcon(selectedImage));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
