package tileviewer;

import javax.swing.JFrame;

public class TileViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Tile Viewer");
		frame.setBounds(100, 100, 700, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		GraphicPanel gp = new GraphicPanel();
		InfoPanel ip = new InfoPanel();
		frame.add(gp);
		frame.add(ip);
	}

}
