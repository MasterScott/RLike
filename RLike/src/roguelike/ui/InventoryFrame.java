package roguelike.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import net.miginfocom.swing.MigLayout;

public class InventoryFrame extends JInternalFrame {

	private static final long serialVersionUID = -1745769096143603577L;
	// TODO Change how this works.
	public ActionPanel ap;
	
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
		super("Inventory", false, true);
		getContentPane().setBackground(Color.BLACK);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, Color.WHITE));
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[]", "[]"));
		setUI(new BasicInternalFrameUI(this));
		
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				ap.requestFocusInWindow();
			}
		});
	}
	
	

}
