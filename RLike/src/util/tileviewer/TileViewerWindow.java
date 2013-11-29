package util.tileviewer;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
		this.gp.setBounds(0, 0, getWidth() - 120, getHeight());
		this.ip.setBounds(getWidth() - 120, 0, 120, getHeight());
		add(gp);
		add(ip);
		addMouseListener(this);

		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent arg0) {

			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				gp.setBounds(0, 0, getWidth() - 120, getHeight());
				ip.setBounds(getWidth() - 120, 0, 120, getHeight());
				validate();

			}

			@Override
			public void componentMoved(ComponentEvent arg0) {

			}

			@Override
			public void componentHidden(ComponentEvent arg0) {

			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (gp.tileset != null && x < gp.tileset.getWidth(null) && y < gp.tileset.getHeight(null)) {

			gp.tile = Graphic.getImage(gp.tileset_path, (x - 8) / 32, (y - 31) / 32);
			gp.setCoords((y - 31) / 32, (x - 8) / 32);
		} else if (x >= getWidth() - 120){

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
