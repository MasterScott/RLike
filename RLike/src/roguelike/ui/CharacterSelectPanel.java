package roguelike.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;

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
		
		JLabel lblRace0 = new JLabel("Buff Human");
		lblRace0.setForeground(Color.WHITE);
		add(lblRace0, "cell 0 2,alignx center");
		
		JLabel lblClass0 = new JLabel("Mage");
		lblClass0.setForeground(Color.WHITE);
		add(lblClass0, "cell 4 2,alignx center");
		
		JLabel lblRace1 = new JLabel("Human");
		lblRace1.setForeground(Color.WHITE);
		add(lblRace1, "cell 0 3,alignx center");
		
		JLabel lblClass2 = new JLabel("Wizard");
		lblClass2.setForeground(Color.WHITE);
		add(lblClass2, "cell 4 3,alignx center");
		
		JLabel lblRace2 = new JLabel("Blond Hair Dude");
		lblRace2.setForeground(Color.WHITE);
		add(lblRace2, "cell 0 4,alignx center");
		
		JLabel lblClass3 = new JLabel("Monk");
		lblClass3.setForeground(Color.WHITE);
		add(lblClass3, "cell 4 4,alignx center");
		
		JLabel lblRace3 = new JLabel("Hobbit");
		lblRace3.setForeground(Color.WHITE);
		add(lblRace3, "cell 0 5,alignx center");
		
		JLabel lblClass4 = new JLabel("Knight");
		lblClass4.setForeground(Color.WHITE);
		add(lblClass4, "cell 4 5,alignx center");
		
		JLabel lblRace4 = new JLabel("Ginger");
		lblRace4.setForeground(Color.WHITE);
		add(lblRace4, "cell 0 6,alignx center");
		
		JLabel lblClass5 = new JLabel("Priest");
		lblClass5.setForeground(Color.WHITE);
		add(lblClass5, "cell 4 6,alignx center");
		
		JLabel lblRace5 = new JLabel("Dwarf");
		lblRace5.setForeground(Color.WHITE);
		add(lblRace5, "cell 0 7,alignx center");
		
		JLabel lblClass6 = new JLabel("Fighter");
		lblClass6.setForeground(Color.WHITE);
		add(lblClass6, "cell 4 7,alignx center");
		
		JLabel lblRace6 = new JLabel("Blue Guy");
		lblRace6.setHorizontalAlignment(SwingConstants.CENTER);
		lblRace6.setForeground(Color.WHITE);
		add(lblRace6, "cell 0 8,alignx center");
		
		JLabel lblClass7 = new JLabel("Thief");
		lblClass7.setForeground(Color.WHITE);
		add(lblClass7, "cell 4 8,alignx center");
		
		JLabel lblRace7 = new JLabel("Golem");
		lblRace7.setForeground(Color.WHITE);
		add(lblRace7, "cell 0 9,alignx center");
		
		JLabel lblClass8 = new JLabel("Gladiator");
		lblClass8.setForeground(Color.WHITE);
		add(lblClass8, "cell 4 9,alignx center");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		JLabel lblRace8 = new JLabel("Halfling");
		lblRace8.setForeground(Color.WHITE);
		add(lblRace8, "cell 0 10,alignx center");
		
		JLabel lblRace9 = new JLabel("Dark Skin Mans");
		lblRace9.setForeground(Color.WHITE);
		add(lblRace9, "cell 0 11,alignx center");
		
		JLabel lblRace10 = new JLabel("Green Guy");
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
