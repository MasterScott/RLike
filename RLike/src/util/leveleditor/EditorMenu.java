package util.leveleditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;

public class EditorMenu extends JPanel {

	private static final long serialVersionUID = -5323835129443241111L;
	private JTextField textFieldWidth;
	private JTextField textFieldHeight;
	private JLabel lblX, lblY, lblTileset, lblTile;
	private EditorWindow parent;
	private JFileChooser fc = new JFileChooser();
	int x, y;

	/**
	 * Create the panel.
	 */
	public EditorMenu() {
		setLayout(new MigLayout("", "[][]", "[18.00][28.00][][][][][][][]"));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setMaximumSize(new Dimension(350, 900));
		

		ArrayList<String> arr = new ArrayList<String>();
		for (GraphicFile gf : GraphicFile.values()) {
			arr.add(gf.name());
		}

		JLabel lblLevelEditor = new JLabel("Level Editor");
		add(lblLevelEditor, "cell 0 0 2 1");

		JButton btnNew = new JButton("New");
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent.p.initialize();
			}
		});
		
		add(btnNew, "flowx,cell 0 1 2 1");

		final JButton btnOpen = new JButton("Open");
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(btnOpen);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					EditorUtils.loadMap(file, parent);
				}
			}
		});
		add(btnOpen, "cell 0 1 2 1");

		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				parent.saveMap(new File("test.rlmap")); // TODO Change this.
			}
		});
		add(btnSave, "cell 0 1 2 1");

		final JButton btnSaveAs = new JButton("Save As");
		btnSaveAs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int returnVal = fc.showOpenDialog(btnSaveAs);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					parent.saveMap(file);
				}
			}
		});
		add(btnSaveAs, "cell 0 1 2 1");

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		add(separator, "cell 0 2 2 1,growx");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		final JList list = new JList(arr.toArray());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String selection = (String) list.getSelectedValue();
				if (selection != null)
					setTileset(selection);

			}
		});

		JButton btnSetBackgroundTo = new JButton("Set Background to Selected Tile");
		btnSetBackgroundTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (parent.selectedImage != null) {
					final ImageIcon img = new ImageIcon(parent.selectedImage);
					for (int x = 0; x < parent.p.tiles.length; x++) {
						for (int y = 0; y < parent.p.tiles[0].length; y++) {
							parent.p.tiles[x][y].setIcon(img);
							parent.p.tileInfo[x][y] = parent.selectedTileToString();
						}
					}
					
				}
			}
		});
		
		add(btnSetBackgroundTo, "cell 0 3 2 1,alignx center");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		add(list, "flowx,cell 0 4,alignx left");

		JLabel lblWidth = new JLabel("Width: ");
		add(lblWidth, "flowx,cell 0 7 2 1");

		textFieldWidth = new JTextField();
		textFieldWidth.setPreferredSize(new Dimension(24, 24));
		add(textFieldWidth, "cell 0 7 2 1");
		textFieldWidth.setColumns(3);

		JLabel lblHeight = new JLabel("Height:");
		add(lblHeight, "cell 0 7 2 1");

		textFieldHeight = new JTextField();
		textFieldHeight.setPreferredSize(new Dimension(24, 24));
		add(textFieldHeight, "cell 0 7 2 1");
		textFieldHeight.setColumns(3);

		lblTileset = new JLabel();
		Dimension d = new Dimension(300, 250);

		Box verticalBox = Box.createVerticalBox();
		add(verticalBox, "flowx,cell 1 4,alignx center,aligny top");

		JLabel lblSelectedTile = new JLabel("Selected Tile:");
		lblSelectedTile.setHorizontalAlignment(SwingConstants.CENTER);
		verticalBox.add(lblSelectedTile);

		lblTile = new JLabel();
		lblTile.setHorizontalAlignment(SwingConstants.CENTER);
		verticalBox.add(lblTile);
		add(lblTileset, "cell 0 5 2 1");

		// $hide>>$
		final JScrollPane tilesetScroller = new JScrollPane(lblTileset);
		tilesetScroller.setPreferredSize(d);
		tilesetScroller.setMinimumSize(d);
		add(tilesetScroller, "cell 0 5 2 1");

		tilesetScroller.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				int x = arg0.getX();
				int y = arg0.getY();

				String selection = (String) list.getSelectedValue();
				if (selection != null) {
					int dx = tilesetScroller.getHorizontalScrollBar().getValue();
					int dy = tilesetScroller.getVerticalScrollBar().getValue();
					Image img = Graphic.getImage(GraphicFile.valueOf(selection), (y + dy) / 32, (x + dx) / 32);
					lblTile.setIcon(new ImageIcon(img));
					parent.setSelectedImage(img);
					parent.setSelectedTileset(GraphicFile.valueOf(selection));
					parent.setSelectedTilesetCoords((y + dy) / 32, (x + dx) / 32);
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		// $hide<<$

		lblX = new JLabel("X: ");
		add(lblX, "flowx,cell 0 8 2 1");

		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, "cell 0 8 2 1");

		lblY = new JLabel("Y: ");
		add(lblY, "cell 0 8 2 1");

		// WindowBuilder can't parse this.
		// $hide>>$
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(150, 80));
		listScroller.setMinimumSize(new Dimension(150, 80));
		add(listScroller, "cell 0 4,alignx left");
		// $hide<<$
	}

	/**
	 * Sets coordinates to be displayed in the X and Y labels to the specified
	 * values.
	 * 
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
		lblX.setText("X: " + x);
		lblY.setText("Y: " + y);
	}

	/**
	 * Returns coordinates displayed in the X and Y labels.
	 * 
	 * @return Coordinates displayed in the X and Y labels.
	 */
	public Point getCoords() {
		return new Point(x, y);
	}

	/**
	 * Sets the parent (enclosing EditorWindow) of this object.
	 * 
	 * @param parent
	 *            Enclosing EditorWindow.
	 */
	public void setParent(EditorWindow parent) {
		this.parent = parent;
	}

	/**
	 * Sets tileset displayed to the specified value.
	 * 
	 * @param name
	 *            Name of GraphicFile.
	 */
	public void setTileset(String name) {
		Image img = Graphic.getImage(GraphicFile.valueOf(name).fileName);
		lblTileset.setIcon(new ImageIcon(img));
	}
}
