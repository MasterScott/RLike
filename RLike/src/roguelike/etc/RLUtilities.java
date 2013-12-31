package roguelike.etc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class RLUtilities {

	/**
	 * Return a random integer given a minimum and maximum and percentage
	 * thresholds.
	 * 
	 * @param min
	 *            Minimum number to apply bottom threshold to.
	 * @param max
	 *            Maximum number to apply top threshold to.
	 * @param bottomThreshold
	 *            Lowest possible percentage of the range to return.
	 * @param topThreshold
	 *            Highest possible percentage of the range to return.
	 * @return Random integer within the given thresholds.
	 */
	public static int generateRandom(int min, int max, double bottomThreshold, double topThreshold) {
		int result = (int) (((Math.random()
				* ((min + (max - min) * topThreshold) - (min + (max - min) * bottomThreshold)) + 1) + (min + (max - min)
				* bottomThreshold)));
		return result;
	}
	
	
	/**
	 * Class used to display a dialog with loading time information. Runs
	 * in a separate thread.
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
