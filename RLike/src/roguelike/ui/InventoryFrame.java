package roguelike.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import net.miginfocom.swing.MigLayout;
import roguelike.etc.Session;

/**
 * Graphical representation of the player's inventory.
 * 
 * @author Dan
 * 
 */
public class InventoryFrame extends JInternalFrame {

	private static final long serialVersionUID = -1745769096143603577L;
	JDesktopPane jdp;
	JLabel[] entries;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryFrame frame = new InventoryFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InventoryFrame() {
		super("Inventory", false, false, false, true);
		getContentPane().setBackground(Color.BLACK);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, Color.WHITE));

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[]", "[][][][][][]"));
		setUI(new BasicInternalFrameUI(this));

		addInternalFrameListener(new InternalFrameAdapter() {

			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				/*
				 * Have to determine the desktop pane this is located in so we
				 * can change focus when closed.
				 */
				jdp = getEnclosingJDesktopPane();
				loadInventory();
			}
			
			@Override
			public void internalFrameDeiconified(InternalFrameEvent e) {
				// Reload inventory when the panel is opened.
				loadInventory();
			}

			@Override
			public void internalFrameIconified(InternalFrameEvent e) {
				// Make sure the game receives focus after closing.
				for (Component c : jdp.getComponents()) {
					if (c.getClass() == ActionPanel.class) {
						c.requestFocusInWindow();
					}
				}
			}
			
		});
	}

	/**
	 * Loads the player's current inventory into memory.
	 */
	private void loadInventory() {
		// Need to clear the list first.
		if (entries != null) {
			for (JLabel j: entries) {
				if (j != null) remove(j);
			}
		}
		revalidate();
		
		final int MAX_LENGTH = 10;
		// Create the list of predefined max length.
		entries = new JLabel[MAX_LENGTH];
		for (int i = 0; i < Math.min(Session.player.getInventory().size(), entries.length); i++) {
			entries[i] = new JLabel(Session.player.getInventory().get(i).getName());
			entries[i].setForeground(Color.WHITE);
			entries[i].setFont(new Font("Tahoma", Font.BOLD, 12));
			add(entries[i], "cell 0 " + i + ",alignx left");
		}
	}
	
	/**
	 * Returns the JDesktopPane that this is enclosed in.
	 * 
	 * @return JDesktopPane that this is enclosed in.
	 */
	private JDesktopPane getEnclosingJDesktopPane() {
		Component c = this;
		while (c != null && c.getClass() != JDesktopPane.class) {
			c = c.getParent();
		}

		if (c.getClass() != JDesktopPane.class) {
			return null;
		} else {
			return (JDesktopPane) c;
		}
	}

}
