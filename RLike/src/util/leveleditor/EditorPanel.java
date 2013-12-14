package util.leveleditor;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class EditorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5584676219132420183L;

	public EditorPanel() {
		setLayout(new MigLayout("", "[32]0[32]0[32]0[32]", "[32]0[32]0[32]0[32]"));
		
	}
	
}
