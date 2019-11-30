package gui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import control.impl.NhanVienControl;
import gui.frmDangNhap;

public class DlgSplashScreen extends JWindow {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JWindow window;
	private long startTime;
	private int minimumMilliseconds;

	private static JProgressBar progressbar = new JProgressBar();
	private static int count;
	private static DlgSplashScreen execute;
	private static Timer timer1;

	public DlgSplashScreen() {
		window = new JWindow();
		JPanel pnl = new JPanel() {
			ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/logoLogin.png"));

			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		window.getContentPane().add(pnl, BorderLayout.CENTER);
		progressbar.setMaximum(50);
		progressbar.setBounds(0, 335, 450, 15);
		progressbar.setForeground(Color.green);
		window.getContentPane().add(progressbar, BorderLayout.SOUTH);
		window.setSize(600, 400);
		window.setLocationRelativeTo(null);

	}

	public void show(int minimumMilliseconds) {
		this.minimumMilliseconds = minimumMilliseconds;
		window.setVisible(true);
		startTime = System.currentTimeMillis();
		loadProgressBar();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		NhanVienControl nhanVienControl = new NhanVienControl();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

	}

	public void hide() {
		long elapsedTime = System.currentTimeMillis() - startTime;
		try {
			Thread.sleep(Math.max(minimumMilliseconds - elapsedTime, 0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		window.setVisible(false);
	}

	private void loadProgressBar() {
		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				progressbar.setValue(count);
				if (count == 51) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							new frmDangNhap().setVisible(true);
						}
					});

					timer1.stop();
				}
			}

			@SuppressWarnings("unused")
			private void createFrame() throws HeadlessException {
				JFrame frame = new JFrame();
				frame.setSize(600, 400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				NhanVienControl nhanVienControl = new NhanVienControl();
			}

		};
		timer1 = new Timer(76, al);
		timer1.start();

	}

	public static void main(String[] args) {
		execute = new DlgSplashScreen();
		execute.show(4000);

		execute.hide();
	}
}
