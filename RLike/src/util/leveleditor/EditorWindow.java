package util.leveleditor;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import roguelike.ui.graphics.Graphic.GraphicFile;

public class EditorWindow extends JFrame implements MouseMotionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2789411093618047798L;

	EditorMenu m;
	EditorPanel p;
	int xStart = 0, yStart = 0;
	Image selectedImage;
	GraphicFile gf;

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
		updateTile(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		updateCoords(e.getX(), e.getY());
	}

	/**
	 * Sets selected image from the menu to the specified image.
	 * 
	 * @param img
	 *            Selected image.
	 */
	public void setSelectedImage(Image img) {
		this.selectedImage = img;
	}

	/**
	 * Returns selected image.
	 * 
	 * @return Selected image.
	 */
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
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		updateTile(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public void setSelectedTileset(GraphicFile gf) {
		this.gf = gf;
	}
	
	/**
	 * Called upon mouse click to update the current tile.
	 */
	private void updateTile(int x, int y) {
		updateCoords(x, y);
		Point point = m.getCoords();
		if (selectedImage != null) {
			if (gf != null && gf.overlay) {
				BufferedImage existing = p.getImageAt(point.x, point.y);
				BufferedImage selImage = convertImage(selectedImage);
				BufferedImage newImage = new BufferedImage(existing.getHeight(), existing.getWidth(),
						BufferedImage.TYPE_INT_ARGB);
				Graphics g = newImage.getGraphics();
				g.drawImage(existing, 0, 0, null);
				g.drawImage(selImage, 0, 0, null);
				g.dispose();
				p.tiles[point.x][point.y].setIcon(new ImageIcon(newImage));
			} else
				p.tiles[point.x][point.y].setIcon(new ImageIcon(selectedImage));
		}
	}

	/**
	 * Converts an image to a BufferedImage.
	 * 
	 * @param img
	 *            Image to be converted.
	 * @return Converted image.
	 */
	private BufferedImage convertImage(Image img) {
		BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		bimg.getGraphics().drawImage(img, 0, 0, null);
		return bimg;
	}
}
