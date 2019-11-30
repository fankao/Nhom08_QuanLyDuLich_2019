package gui.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PnlHinhNen extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnlHinhNen() {
		setLayout(new BorderLayout(0, 0));

		JPanel pnlHinhNen = new JPanel() {
			private static final long serialVersionUID = 1L;
			ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/hinhnen.jpg"));

			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}

		};
		add(pnlHinhNen, BorderLayout.CENTER);

	}
}
