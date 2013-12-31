package roguelike.etc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class RLUtilities {

	/**
	 * Returns a random integer between the specified minimum and maximum
	 * values.
	 * 
	 * @param min
	 *            Minimum value.
	 * @param max
	 *            Maximum value.
	 * @return Random integer between the minimum and maximum values.
	 */
	public static int getRandom(int min, int max) {
		Random random = new Random();
		int n = max - min + 1;
		if (n < 0)
			n = -n;
		return min + random.nextInt(n);
	}

	/**
	 * Class used to display a dialog with loading time information. Runs in a
	 * separate thread.
	 * 
	 * @author Dan
	 * 
	 */
	public class LoadingDialog extends SwingWorker<Void, Void> {

		private JProgressBar pb;
		private JDialog d;
		private JLabel label;
		public int i;

		/**
		 * Create a new LoadingDialog.
		 */
		public LoadingDialog() {
			i = 0;

			/*
			 * Every time progress is made, update the dialog.
			 */
			addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					if ("progress".equalsIgnoreCase(evt.getPropertyName())) {
						if (d == null) {

							// Create dialog.
							d = new JDialog();
							d.setTitle("Processing");
							d.setLayout(new GridBagLayout());
							d.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
							GridBagConstraints gbc = new GridBagConstraints();
							gbc.insets = new Insets(2, 2, 2, 2); // Add padding.
							gbc.weightx = 1;
							gbc.gridy = 0;

							// Add label and progress bar to dialog.
							label = new JLabel("Loading...");
							d.add(label, gbc);
							pb = new JProgressBar();
							gbc.gridy = 1;
							d.add(pb, gbc);
							d.pack();
							d.setLocationRelativeTo(null);
							d.setVisible(true);
						}
						pb.setValue(getProgress());
					}
				}

			});
		}

		/**
		 * Change currently displayed text.
		 * 
		 * @param text
		 *            Text to display.
		 */
		public void setLabelText(String text) {
			label.setText(text);
		}

		@Override
		protected void done() {
			if (d != null) {
				d.dispose();
			}
		}

		@Override
		protected Void doInBackground() throws Exception {
			while (i < 100) { // Once i = 100, loading is complete.
				setProgress(i);
			}
			return null;
		}

	}
}
