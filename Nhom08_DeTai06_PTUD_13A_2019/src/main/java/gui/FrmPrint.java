package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.swing.JRViewer;

public class FrmPrint extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public FrmPrint(JRViewer viewer) {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel pnlPrint = new JPanel();
		pnlPrint.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnlPrint, BorderLayout.CENTER);
		pnlPrint.add(viewer, BorderLayout.CENTER);
		
	}

}
