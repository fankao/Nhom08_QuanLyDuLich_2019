package test;

import javax.swing.SwingUtilities;

import gui.frmDangNhap;

public class Testing {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new frmDangNhap().setVisible(true);
			}
		});

	}

}
