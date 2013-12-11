package roguelike.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import roguelike.actors.Player;
import roguelike.etc.Session;
import roguelike.ui.MenuLabel.LabelType;
import roguelike.ui.graphics.Graphic;
import roguelike.ui.graphics.Graphic.GraphicFile;

/**
 * Implementation of JPanel for selecting character.
 * 
 * @author Dan
 * 
 */
public class CharacterSelectPanel extends JPanel {

	private static final long serialVersionUID = -1903352894149762144L;
	private JTextField textField;
	int row, col;
	JLabel lblImage;

	/**
	 * Create the panel.
	 */
	public CharacterSelectPanel() {
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setLayout(new MigLayout("", "[15.56%][1.01%][32.56%][1.01%][12.16%][31.98%]",
				"[8.00%][8.00%][6.00%][6.00%][6.00%][6.00%][6.00%][6.00%][6.00%][6.00%][6.00%][6.00%][6.00%][11.28%]"));

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setForeground(Color.WHITE);
		add(lblName, "cell 0 0,alignx center");

		textField = new JTextField();
		add(textField, "cell 2 0,growx");
		textField.setColumns(10);

		JLabel lblRace = new JLabel("Race");
		lblRace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRace.setForeground(Color.WHITE);
		add(lblRace, "flowy,cell 0 1,alignx center");

		JLabel lblNewLabel_8 = new JLabel("Words go here");
		lblNewLabel_8.setForeground(Color.WHITE);
		add(lblNewLabel_8, "cell 2 1 1 9,alignx center,aligny center");

		JLabel lblClass = new JLabel("Class");
		lblClass.setForeground(Color.WHITE);
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblClass, "flowy,cell 4 1,alignx center");

		JLabel lblNewLabel_17 = new JLabel("Words go here");
		lblNewLabel_17.setForeground(Color.WHITE);
		add(lblNewLabel_17, "cell 5 1 1 9,alignx center,aligny center");

		MenuLabel lblRace0 = new MenuLabel("Buff Human");
		lblRace0.setLabelType(LabelType.RACE);
		lblRace0.setForeground(Color.WHITE);
		add(lblRace0, "cell 0 2,alignx center");

		MenuLabel lblClass0 = new MenuLabel("Mage");
		lblClass0.setLabelType(LabelType.CLASS);
		lblClass0.setForeground(Color.WHITE);
		add(lblClass0, "cell 4 2,alignx center");

		MenuLabel lblRace1 = new MenuLabel("Human");
		lblRace1.setLabelType(LabelType.RACE);
		lblRace1.setForeground(Color.WHITE);
		add(lblRace1, "cell 0 3,alignx center");

		MenuLabel lblClass1 = new MenuLabel("Wizard");
		lblClass1.setLabelType(LabelType.CLASS);
		lblClass1.setForeground(Color.WHITE);
		add(lblClass1, "cell 4 3,alignx center");

		MenuLabel lblRace2 = new MenuLabel("Blond Hair Dude");
		lblRace2.setLabelType(LabelType.RACE);
		lblRace2.setForeground(Color.WHITE);
		add(lblRace2, "cell 0 4,alignx center");

		MenuLabel lblClass2 = new MenuLabel("Monk");
		lblClass2.setLabelType(LabelType.CLASS);
		lblClass2.setForeground(Color.WHITE);
		add(lblClass2, "cell 4 4,alignx center");

		MenuLabel lblRace3 = new MenuLabel("Hobbit");
		lblRace3.setLabelType(LabelType.RACE);
		lblRace3.setForeground(Color.WHITE);
		add(lblRace3, "cell 0 5,alignx center");

		MenuLabel lblClass3 = new MenuLabel("Knight");
		lblClass3.setLabelType(LabelType.CLASS);
		lblClass3.setForeground(Color.WHITE);
		add(lblClass3, "cell 4 5,alignx center");

		MenuLabel lblRace4 = new MenuLabel("Ginger");
		lblRace4.setLabelType(LabelType.RACE);
		lblRace4.setForeground(Color.WHITE);
		add(lblRace4, "cell 0 6,alignx center");

		MenuLabel lblClass4 = new MenuLabel("Priest");
		lblClass4.setLabelType(LabelType.CLASS);
		lblClass4.setForeground(Color.WHITE);
		add(lblClass4, "cell 4 6,alignx center");

		MenuLabel lblRace5 = new MenuLabel("Dwarf");
		lblRace5.setLabelType(LabelType.RACE);
		lblRace5.setForeground(Color.WHITE);
		add(lblRace5, "cell 0 7,alignx center");

		MenuLabel lblClass5 = new MenuLabel("Fighter");
		lblClass5.setLabelType(LabelType.CLASS);
		lblClass5.setForeground(Color.WHITE);
		add(lblClass5, "cell 4 7,alignx center");

		MenuLabel lblRace6 = new MenuLabel("Blue Guy");
		lblRace6.setLabelType(LabelType.RACE);
		lblRace6.setForeground(Color.WHITE);
		add(lblRace6, "cell 0 8,alignx center");

		MenuLabel lblClass6 = new MenuLabel("Thief");
		lblClass6.setLabelType(LabelType.CLASS);
		lblClass6.setForeground(Color.WHITE);
		add(lblClass6, "cell 4 8,alignx center");

		MenuLabel lblRace7 = new MenuLabel("Golem");
		lblRace7.setLabelType(LabelType.RACE);
		lblRace7.setForeground(Color.WHITE);
		add(lblRace7, "cell 0 9,alignx center");

		MenuLabel lblClass7 = new MenuLabel("Gladiator");
		lblClass7.setLabelType(LabelType.CLASS);
		lblClass7.setForeground(Color.WHITE);
		add(lblClass7, "cell 4 9,alignx center");

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		MenuLabel lblRace8 = new MenuLabel("Halfling");
		lblRace8.setLabelType(LabelType.RACE);
		lblRace8.setForeground(Color.WHITE);
		add(lblRace8, "cell 0 10,alignx center");

		MenuLabel lblRace9 = new MenuLabel("Dark Skin Mans");
		lblRace9.setLabelType(LabelType.RACE);
		lblRace9.setForeground(Color.WHITE);
		add(lblRace9, "cell 0 11,alignx center");

		MenuLabel lblRace10 = new MenuLabel("Green Guy");
		lblRace10.setLabelType(LabelType.RACE);
		lblRace10.setForeground(Color.WHITE);
		add(lblRace10, "cell 0 12,alignx center");

		lblImage = new JLabel();
		add(lblImage, "cell 4 13,alignx center");
		add(btnCancel, "flowx,cell 5 13,alignx right");

		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (lblImage.getIcon() != null) {
					Session.player = new Player(3, 3);
					
					try{
						Session.player.setName(textField.getText());
					} catch (NullPointerException e) {
						// TODO Do something with the null value.
					}
					
					Session.player.setImage(GraphicFile.CLASSM, row, col);
					Session.window.notifyCharacterSelected();
				}
			}
		});
		add(btnOk, "cell 5 13,alignx right");

		JSeparator separator = new JSeparator();
		add(separator, "cell 0 1,growx");

		JSeparator separator_1 = new JSeparator();
		add(separator_1, "cell 4 1,growx");
	}

	/**
	 * Sets character preview graphic to the specified icon.
	 * 
	 * @param tileset
	 *            Tileset to pull graphic from.
	 * @param row
	 *            Row of graphic.
	 * @param col
	 *            Column of graphic.
	 */
	public void setImage(GraphicFile tileset, int row, int col) {
		this.row = row;
		this.col = col;
		Image img = Graphic.getImage(GraphicFile.CLASSM.fileName, row, col);
		lblImage.setIcon(new ImageIcon(img));
	}
}

/**
 * Implementation of JLabel specific to CharacterSelectPanel.
 * 
 * @author Dan
 * 
 */
class MenuLabel extends JLabel {

	private static final long serialVersionUID = 4825515890917106490L;

	/**
	 * Type of label this is.
	 * 
	 * @author Dan
	 * 
	 */
	static enum LabelType {
		RACE, CLASS
	}

	static ArrayList<MenuLabel> labels;
	static MenuLabel selectedRace, selectedClass;
	LabelType type;
	Font font;
	CharacterSelectPanel parent;

	/**
	 * Creates a MenuLabel with the specified text.
	 * 
	 * @param text
	 *            Text to display.
	 */
	public MenuLabel(String text) {
		setText(text);
		font = getFont();

		if (labels == null)
			labels = new ArrayList<MenuLabel>();
		labels.add(this);
		final MenuLabel label = this;

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshListings(type);

				/*
				 * Have to check if parent is null at this point rather than
				 * MenuLabel initialization. At MenuLabel initialization, the
				 * label has not been added to any container yet.
				 */
				if (parent == null) {
					parent = (CharacterSelectPanel) getParent();
				}

				if (type == LabelType.RACE) {
					selectedRace = label;
				} else {
					selectedClass = label;
				}

				label.setForeground(new Color(255, 220, 200));
				label.setFont(new Font("Tahoma", Font.BOLD, 12));
				Point p = getSelectedCoordinates();
				if (p != null) {
					parent.setImage(GraphicFile.CLASSM, p.x, p.y);
				}

			}
		});
	}

	/**
	 * Sets label type to the specified type.
	 * 
	 * @param type
	 *            Type of label.
	 */
	public void setLabelType(LabelType type) {
		this.type = type;
	}

	/**
	 * Returns the type of this label.
	 * 
	 * @return Type of label.
	 */
	public LabelType getLabelType() {
		return type;
	}

	/**
	 * If a race and a class have both been selected, returns a point
	 * representing the row/column needed to load an image.
	 * 
	 * @return Point representing the row/column needed to load an image.
	 */
	public Point getSelectedCoordinates() {
		if (selectedRace == null || selectedClass == null) {
			return null;
		}

		int y = 0, x = 0, row = 0, col = 0;
		for (MenuLabel label : labels) {
			if (label.getLabelType() == LabelType.RACE) {
				if (label == selectedRace) {
					row = y;
				} else
					y++;
			} else {
				if (label == selectedClass) {
					col = x;
				} else
					x++;
			}
		}

		return new Point(row, col);
	}

	/**
	 * Clear all selections of the specified type.
	 * 
	 * @param type
	 *            Type to clear selections for.
	 */
	public void refreshListings(LabelType type) {
		for (MenuLabel label : labels) {
			if (label.getLabelType() == type) {
				label.setForeground(Color.WHITE);
				label.setFont(label.font);
			}
		}
	}
}