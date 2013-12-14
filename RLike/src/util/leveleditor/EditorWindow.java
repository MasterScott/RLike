package util.leveleditor;

import javax.swing.JFrame;
import javax.swing.BoxLayout;

public class EditorWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2789411093618047798L;

	public EditorWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		EditorMenu m = new EditorMenu();
		EditorPanel p = new EditorPanel();
		add(m);
		add(p);
	}
}
