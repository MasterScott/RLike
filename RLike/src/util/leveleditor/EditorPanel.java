package util.leveleditor;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class EditorPanel extends JPanel {

	private static final long serialVersionUID = -5584676219132420183L;

	int width = 0, height = 0;

	public EditorPanel() {
		initialize();

	}

	/**
	 * Sets level dimensions to the specified dimensions.
	 * 
	 * @param width
	 *            Width of level.
	 * @param height
	 *            Height of level.
	 */
	public void setDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void initialize() {
		StringBuilder wString = new StringBuilder("[32]");
		StringBuilder hString = new StringBuilder("[32]");

		if (width == 0)
			width = 40;
		if (height == 0)
			height = 25;
		
		for (int i = 1; i <= width; i++) {
			wString.append("1[32]");
		}
		
		for (int i = 1; i <= height; i++) {
			hString.append("1[32]");
		}
		
		setLayout(new MigLayout("", wString.toString(), hString.toString()));
	}
}
