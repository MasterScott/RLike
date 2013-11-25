package util.tileviewer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;

/**
 * Ties all elements of the tile viewer together. This class should be all that
 * is needed to get the viewer to work.
 * 
 * @author Dan
 * 
 */
public class TileViewerWindow extends JFrame implements MouseListener {

	private static final long serialVersionUID = 3353795340446696141L;
	private GraphicPanel gp;
	private InfoPanel ip;

	/**
	 * Creates a new default TileViewerWindow.
	 */
	public TileViewerWindow() {
		setTitle("Tile Viewer");
		setBounds(400, 100, 700, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		this.gp = new GraphicPanel();
		this.ip = new InfoPanel();
		add(gp);
		add(ip);
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		/*
		 * Separated into < and >= 500, due to the two panels loaded in upon instantiation.
		 */
		if (x < 500) {
			if (gp.tileset != null && x < gp.tileset.getWidth(null) && y < gp.tileset.getHeight(null))
				;
			gp.tile = Graphic.getImage(gp.tileset_path, (x - 8) / 32, (y - 31) / 32);
			gp.setCoords((y - 31) / 32, (x - 8) / 32);
		} else {

			gp.tileset = Graphic.getImage(GraphicFile.values()[(y - 46) / 18].fileName);
			gp.tileset_path = GraphicFile.values()[(y - 46) / 18].fileName;
		}

		this.repaint();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
