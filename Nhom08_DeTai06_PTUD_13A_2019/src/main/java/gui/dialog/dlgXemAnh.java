package gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utils.TienIch;
import java.awt.Toolkit;

public class dlgXemAnh extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblAnh;
	private byte[] duLieuAnh;
 

	/**
	 * Khởi tạo giao diện
	 */
	public dlgXemAnh(byte[] duLieuAnh) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(dlgXemAnh.class.getResource("/images/iconFrm.png")));
		this.duLieuAnh = duLieuAnh;
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		setTitle("Xem ảnh tour");
		setBounds(0, 0, 867, 619);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			lblAnh = new JLabel("");
			lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblAnh, BorderLayout.CENTER);
		}
		hienAnhTour(duLieuAnh);
		
	}
	
	private void hienAnhTour(byte[] duLieuAnh) {
		lblAnh.setSize(867, 619);
		if(duLieuAnh != null) {
		TienIch.chinhKichThuocAnh(lblAnh, new ImageIcon(duLieuAnh));
		}
	}

}
