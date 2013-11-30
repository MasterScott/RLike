package roguelike.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import roguelike.ui.MenuLabel.LabelType;

public class CharacterSelectPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public CharacterSelectPanel() {
		setForeground(Color.WHITE);
		//setBounds(0, 0, getParent().getWidth(), getParent().getHeight());
		setBackground(Color.BLACK);
		setLayout(new MigLayout("", "[15.56%][1.01%][32.56%][1.01%][12.16%][31.98%]", "[][7.44%][][][][][][][][][][][][][][]"));
		
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
		add(btnCancel, "flowx,cell 5 15,alignx center");
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		add(btnOk, "cell 5 15,alignx center");
		
		JSeparator separator = new JSeparator();
		add(separator, "cell 0 1,growx");
		
		JSeparator separator_1 = new JSeparator();
		add(separator_1, "cell 4 1,growx");
		
	}
	
}

class MenuLabel extends JLabel {
	
	static enum LabelType { RACE, CLASS }
	static ArrayList<MenuLabel> labels;
	LabelType type;
	Font font;
	
	public MenuLabel(String text) {
		setText(text);
		font = getFont();
		
		if (labels == null) labels = new ArrayList<MenuLabel>();
		labels.add(this);
		final MenuLabel label = this;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshListings(type);
				label.setForeground(new Color(255, 220, 200));
				label.setFont(new Font("Tahoma",Font.BOLD, 12));
			}
		});
	}
	
	public void setLabelType(LabelType type) {
		this.type = type;
	}
	
	public LabelType getLabelType() {
		return type;
	}
	
	
	
	public static void refreshListings(LabelType type) {
		for (MenuLabel label: labels) {
			if (label.getLabelType() == type) {
				label.setForeground(Color.WHITE);
				label.setFont(label.font);
			}
		}
	}
}