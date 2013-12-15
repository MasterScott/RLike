package util.leveleditor;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;

public class EditorWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2789411093618047798L;

	public EditorWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(2, 2, 2, 2));
		getContentPane().add(panel);
		EditorMenu m = new EditorMenu();
		panel.add(m);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(2, 2, 2, 2));
		getContentPane().add(panel_1);
		EditorPanel p = new EditorPanel();
		panel_1.add(p);
	}
}
