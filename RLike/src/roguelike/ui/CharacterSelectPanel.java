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

public class CharacterSelectPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CharacterSelectPanel() {
		setForeground(Color.WHITE);
		//setBounds(0, 0, getParent().getWidth(), getParent().getHeight());
		setBackground(Color.BLACK);
		setLayout(new MigLayout("", "[15.56%][1.01%][30.10%][1.01%][12.16%][30.82%]", "[7.44%][][][][][][][][][][][][][][]"));
		
		JLabel lblRace = new JLabel("Race");
		lblRace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRace.setForeground(Color.WHITE);
		add(lblRace, "cell 0 0,alignx center");
		
		JLabel lblNewLabel_8 = new JLabel("Words go here");
		lblNewLabel_8.setForeground(Color.WHITE);
		add(lblNewLabel_8, "cell 2 0 1 9,alignx center,aligny center");
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setForeground(Color.WHITE);
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblClass, "cell 4 0,alignx center");
		
		JLabel lblNewLabel_17 = new JLabel("Words go here");
		lblNewLabel_17.setForeground(Color.WHITE);
		add(lblNewLabel_17, "cell 5 0 1 9,alignx center,aligny center");
		
		JLabel lblNewLabel = new JLabel("Buff Human");
		lblNewLabel.setForeground(Color.WHITE);
		add(lblNewLabel, "cell 0 1,alignx center");
		
		JLabel lblNewLabel_9 = new JLabel("Mage");
		lblNewLabel_9.setForeground(Color.WHITE);
		add(lblNewLabel_9, "cell 4 1,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("Human");
		lblNewLabel_1.setForeground(Color.WHITE);
		add(lblNewLabel_1, "cell 0 2,alignx center");
		
		JLabel lblNewLabel_10 = new JLabel("Wizard");
		lblNewLabel_10.setForeground(Color.WHITE);
		add(lblNewLabel_10, "cell 4 2,alignx center");
		
		JLabel lblNewLabel_2 = new JLabel("Blond Hair Dude");
		lblNewLabel_2.setForeground(Color.WHITE);
		add(lblNewLabel_2, "cell 0 3,alignx center");
		
		JLabel lblNewLabel_11 = new JLabel("Monk");
		lblNewLabel_11.setForeground(Color.WHITE);
		add(lblNewLabel_11, "cell 4 3,alignx center");
		
		JLabel lblNewLabel_3 = new JLabel("Hobbit");
		lblNewLabel_3.setForeground(Color.WHITE);
		add(lblNewLabel_3, "cell 0 4,alignx center");
		
		JLabel lblNewLabel_12 = new JLabel("Knight");
		lblNewLabel_12.setForeground(Color.WHITE);
		add(lblNewLabel_12, "cell 4 4,alignx center");
		
		JLabel lblNewLabel_4 = new JLabel("Dwarf");
		lblNewLabel_4.setForeground(Color.WHITE);
		add(lblNewLabel_4, "cell 0 5,alignx center");
		
		JLabel lblNewLabel_13 = new JLabel("Priest");
		lblNewLabel_13.setForeground(Color.WHITE);
		add(lblNewLabel_13, "cell 4 5,alignx center");
		
		JLabel lblNewLabel_5 = new JLabel("Blue Guy");
		lblNewLabel_5.setForeground(Color.WHITE);
		add(lblNewLabel_5, "cell 0 6,alignx center");
		
		JLabel lblNewLabel_14 = new JLabel("Fighter");
		lblNewLabel_14.setForeground(Color.WHITE);
		add(lblNewLabel_14, "cell 4 6,alignx center");
		
		JLabel lblNewLabel_6 = new JLabel("Dark Skin Mans");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setForeground(Color.WHITE);
		add(lblNewLabel_6, "cell 0 7,alignx center");
		
		JLabel lblNewLabel_15 = new JLabel("Thief");
		lblNewLabel_15.setForeground(Color.WHITE);
		add(lblNewLabel_15, "cell 4 7,alignx center");
		
		JLabel lblNewLabel_7 = new JLabel("Green Guy");
		lblNewLabel_7.setForeground(Color.WHITE);
		add(lblNewLabel_7, "cell 0 8,alignx center");
		
		JLabel lblNewLabel_16 = new JLabel("Gladiator");
		lblNewLabel_16.setForeground(Color.WHITE);
		add(lblNewLabel_16, "cell 4 8,alignx center");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		add(btnCancel, "flowx,cell 5 14,alignx center");
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		add(btnOk, "cell 5 14,alignx center");
		
		
	}
}
