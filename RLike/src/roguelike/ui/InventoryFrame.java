package roguelike.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import net.miginfocom.swing.MigLayout;

/**
 * Graphical representation of the player's inventory.
 * 
 * @author Dan
 * 
 */
public class InventoryFrame extends JInternalFrame {

	private static final long serialVersionUID = -1745769096143603577L;
	JDesktopPane jdp;

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
		getContentPane().setLayout(new MigLayout("", "[]", "[]"));
		setUI(new BasicInternalFrameUI(this));

		addInternalFrameListener(new InternalFrameAdapter() {

			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				/*
				 * Have to determine the desktop pane this is located in so we
				 * can change focus when closed.
				 */
				jdp = getEnclosingJDesktopPane();
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
