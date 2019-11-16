package test;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.frmDangNhap;
import gui.panel.pnlThongKe;

public class Testing {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
//			new frmDangNhap().setVisible(true);
				
				  JFrame fr = new JFrame(); fr.getContentPane().add(new pnlThongKe());
				  fr.setSize(1080,720);
				  fr.setVisible(true);
				 
			}
		});

	}

}
