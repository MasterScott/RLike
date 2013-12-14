package util.leveleditor;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import roguelike.ui.graphics.Graphic.GraphicFile;
import javax.swing.JLabel;

public class EditorMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5323835129443241111L;

	/**
	 * Create the panel.
	 */
	public EditorMenu() {
		setLayout(new MigLayout("", "[grow][]", "[][][][grow]"));
		
		ArrayList<String> arr = new ArrayList<String>();
		for (GraphicFile gf : GraphicFile.values()) {
			arr.add(gf.name());
		}
		
		JLabel lblLevelEditor = new JLabel("Level Editor");
		add(lblLevelEditor, "cell 0 0");
		
		JList list = new JList(arr.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		add(list, "cell 0 3,grow");

		// WindowBuilder can't parse this.
		// $hide>>$
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		add(listScroller);
		// $hide<<$
	}

}
