package util.leveleditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;
import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;

public class EditorMenu extends JPanel {

	private static final long serialVersionUID = -5323835129443241111L;
	private JTextField textFieldWidth;
	private JTextField textFieldHeight;
	private JLabel lblX, lblY, lblTileset;
	private EditorWindow parent;

	/**
	 * Create the panel.
	 */
	public EditorMenu() {
		setLayout(new MigLayout("", "[]", "[18.00][28.00][][][][][][][]"));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
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
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		final JList list = new JList(arr.toArray());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selection = (String) list.getSelectedValue();
				if (selection != null) 
					setTileset(selection);
				
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		add(list, "cell 0 4,alignx left");
		
		JLabel lblWidth = new JLabel("Width: ");
		add(lblWidth, "flowx,cell 0 3");
		
		textFieldWidth = new JTextField();
		textFieldWidth.setPreferredSize(new Dimension(24, 24));
		add(textFieldWidth, "cell 0 3");
		textFieldWidth.setColumns(3);
		
		JLabel lblHeight = new JLabel("Height:");
		add(lblHeight, "cell 0 3");
		
		textFieldHeight = new JTextField();
		textFieldHeight.setPreferredSize(new Dimension(24, 24));
		add(textFieldHeight, "cell 0 3");
		textFieldHeight.setColumns(3);
		
		lblTileset = new JLabel();
		Dimension d = new Dimension(250, 250);
		add(lblTileset, "cell 0 5");
		
		JScrollPane tilesetScroller = new JScrollPane(lblTileset);
		tilesetScroller.setPreferredSize(d);
		tilesetScroller.setMinimumSize(d);
		add(tilesetScroller, "cell 0 5");
		
		tilesetScroller.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int x = arg0.getX();
				int y = arg0.getY();
				
				String selection = (String) list.getSelectedValue();
				if (selection != null) {
					Image img = Graphic.getImage(GraphicFile.valueOf(selection), y / 32, x / 32);
					parent.setSelectedImage(img);
				}
					
				
			}
		});
		
		lblX = new JLabel("X: ");
		add(lblX, "flowx,cell 0 8");
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, "cell 0 8");
		
		lblY = new JLabel("Y: ");
		add(lblY, "cell 0 8");

		// WindowBuilder can't parse this.
		// $hide>>$
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(150, 80));
		listScroller.setMinimumSize(new Dimension(150, 80));
		add(listScroller, "cell 0 4,alignx left");
		// $hide<<$
	}
	
	public void updateCoords(int x, int y) {
		lblX.setText("X: " + x);
		lblY.setText("Y: " + y);
	}
	
	public void setParent(EditorWindow parent) {
		this.parent = parent;
	}

	public void setTileset(String name) {
		Image img = Graphic.getImage(GraphicFile.valueOf(name).fileName);
		lblTileset.setIcon(new ImageIcon(img));
	}
}
