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
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;

public class EditorMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5323835129443241111L;
	private JTextField textFieldWidth;
	private JTextField textFieldHeight;

	/**
	 * Create the panel.
	 */
	public EditorMenu() {
		setLayout(new MigLayout("", "[]", "[18.00][28.00][][][][]"));
		
		ArrayList<String> arr = new ArrayList<String>();
		for (GraphicFile gf : GraphicFile.values()) {
			arr.add(gf.name());
		}
		
		JLabel lblLevelEditor = new JLabel("Level Editor");
		add(lblLevelEditor, "cell 0 0");
		
		JButton btnNew = new JButton("New");
		add(btnNew, "flowx,cell 0 1");
		
		JButton btnOpen = new JButton("Open");
		add(btnOpen, "cell 0 1");
		
		JButton btnSave = new JButton("Save");
		add(btnSave, "cell 0 1");
		
		JButton btnSaveAs = new JButton("Save As");
		add(btnSaveAs, "cell 0 1");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		add(separator, "cell 0 2,growx");
		
		JList list = new JList(arr.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		add(list, "cell 0 3,alignx left");
		
		JLabel lblWidth = new JLabel("Width: ");
		add(lblWidth, "flowx,cell 0 5");
		
		textFieldWidth = new JTextField();
		textFieldWidth.setPreferredSize(new Dimension(24, 24));
		add(textFieldWidth, "cell 0 5");
		textFieldWidth.setColumns(3);
		
		JLabel lblHeight = new JLabel("Height:");
		add(lblHeight, "cell 0 5");
		
		textFieldHeight = new JTextField();
		textFieldHeight.setPreferredSize(new Dimension(24, 24));
		add(textFieldHeight, "cell 0 5");
		textFieldHeight.setColumns(3);

		// WindowBuilder can't parse this.
		// $hide>>$
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(150, 80));
		listScroller.setMinimumSize(new Dimension(150, 80));
		add(listScroller, "cell 0 3,alignx left");
		// $hide<<$
	}

}
