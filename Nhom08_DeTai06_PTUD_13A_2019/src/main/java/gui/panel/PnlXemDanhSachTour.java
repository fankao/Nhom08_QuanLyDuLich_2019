package gui.panel;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class PnlXemDanhSachTour extends JPanel {

	/**
	 * Create the panel.
	 */
	public PnlXemDanhSachTour() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);

	}

}
