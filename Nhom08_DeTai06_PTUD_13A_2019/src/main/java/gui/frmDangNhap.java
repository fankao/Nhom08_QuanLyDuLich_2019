package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import control.impl.NhanVienControl;
import entities.NhanVien;
import entities.TaiKhoan;

/**
 * frmDangNhap.java
 * 
 * @author Minh Chien =, Ngày tạo: 08/11/2019
 *
 */
public class frmDangNhap extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTaiKhoan;
	private JPasswordField pwdMatKhau;
	private JLabel lblLogo;
	private JButton btnDangNhap;
	private JButton btnThoat;
	private NhanVienControl nhanVienControl;
	private NhanVien nv;

	/**
	 * Constructor khởi tạo giao diện đăng nhập
	 */
	public frmDangNhap() {
		super((JDialog) null);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmDangNhap.class.getResource("/images/iconFrm.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 897, 463);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JPanel pnLogo = new JPanel();
		pnLogo.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnLogo.setBackground(new Color(26, 163, 255));
		JPanel pnDangNhap = new JPanel();
		pnDangNhap.setBackground(Color.WHITE);

		JLabel lblTieuDe = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Calibri", Font.BOLD, 40));

		JLabel lblTaiKhoan = new JLabel("Tài khoản:");
		lblTaiKhoan.setFont(new Font("Arial", Font.BOLD, 20));

		txtTaiKhoan = new JTextField();
		txtTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtTaiKhoan.setColumns(10);

		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setFont(new Font("Arial", Font.BOLD, 20));

		pwdMatKhau = new JPasswordField();
		pwdMatKhau.setFont(new Font("Arial", Font.BOLD, 24));

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBorder(new LineBorder(new Color(0, 255, 0), 1, true));
		btnDangNhap.setMnemonic(KeyEvent.VK_ENTER);
		btnDangNhap.setForeground(new Color(255, 255, 255));
		btnDangNhap.setBackground(Color.GREEN);
		btnDangNhap.setFont(new Font("Arial", Font.BOLD, 20));

		btnThoat = new JButton("Huỷ");
		btnThoat.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		btnThoat.setForeground(new Color(255, 255, 255));
		btnThoat.setBackground(new Color(255, 0, 0));
		btnThoat.setFont(new Font("Arial", Font.BOLD, 20));
		GroupLayout gl_pnDangNhap = new GroupLayout(pnDangNhap);
		gl_pnDangNhap.setHorizontalGroup(gl_pnDangNhap.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnDangNhap.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnDangNhap.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTieuDe, GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
								.addGroup(gl_pnDangNhap.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_pnDangNhap.createSequentialGroup().addComponent(lblTaiKhoan)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtTaiKhoan,
														GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnDangNhap.createSequentialGroup()
												.addComponent(lblMtKhu, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_pnDangNhap.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_pnDangNhap.createSequentialGroup()
																.addComponent(btnDangNhap, GroupLayout.PREFERRED_SIZE,
																		115, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, 67,
																		Short.MAX_VALUE)
																.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 122,
																		GroupLayout.PREFERRED_SIZE))
														.addComponent(pwdMatKhau, GroupLayout.DEFAULT_SIZE, 304,
																Short.MAX_VALUE)))))
						.addContainerGap()));
		gl_pnDangNhap.setVerticalGroup(gl_pnDangNhap.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnDangNhap.createSequentialGroup().addGap(58)
						.addComponent(lblTieuDe, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(41)
						.addGroup(gl_pnDangNhap.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTaiKhoan, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTaiKhoan, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(37)
						.addGroup(gl_pnDangNhap.createParallelGroup(Alignment.BASELINE)
								.addComponent(pwdMatKhau, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMtKhu, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(58)
						.addGroup(gl_pnDangNhap.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDangNhap, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGap(66)));
		pnDangNhap.setLayout(gl_pnDangNhap);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

		lblLogo = new JLabel("");
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblLogo.setBackground(new Color(0, 191, 255));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(frmDangNhap.class.getResource("/images/sail_boat.png")));
		contentPanel.add(pnLogo);

		JLabel lblTenCT = new JLabel("Phương Nam Travel");
		lblTenCT.setForeground(Color.WHITE);
		lblTenCT.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenCT.setFont(new Font("Tahoma", Font.BOLD, 35));

		JLabel lblSlogan = new JLabel("Cuộc đời là những chuyến đi...");
		lblSlogan.setForeground(Color.WHITE);
		lblSlogan.setFont(new Font("Tahoma", Font.ITALIC, 25));
		GroupLayout gl_pnLogo = new GroupLayout(pnLogo);
		gl_pnLogo.setHorizontalGroup(gl_pnLogo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnLogo.createSequentialGroup().addGap(15)
						.addGroup(gl_pnLogo.createParallelGroup(Alignment.LEADING).addComponent(lblSlogan)
								.addGroup(gl_pnLogo.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblLogo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblTenCT, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addContainerGap(15, Short.MAX_VALUE)));
		gl_pnLogo.setVerticalGroup(gl_pnLogo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnLogo.createSequentialGroup().addGap(41)
						.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblTenCT)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblSlogan)
						.addContainerGap(106, Short.MAX_VALUE)));
		pnLogo.setLayout(gl_pnLogo);
		contentPanel.add(pnDangNhap);

		nhanVienControl = new NhanVienControl();

		txtTaiKhoan.setText("NV001");
		pwdMatKhau.setText("000000");

		// Gắn sự kiện
		ganSuKien();
	}

	/**
	 * Hàm gắn sự kiện cho các control
	 */
	private void ganSuKien() {
		this.getRootPane().setDefaultButton(btnDangNhap);
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
	}

	/**
	 * Xử lý sự kiện cho button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDangNhap)) {
			if (txtTaiKhoan.getText().trim().length() != 0 && pwdMatKhau.getText().trim().length() != 0) {
				TaiKhoan taiKhoan = new TaiKhoan(txtTaiKhoan.getText(), pwdMatKhau.getText());
				nv = nhanVienControl.layNhanVienTheoTaiKhoan(taiKhoan);
				if (nv != null) {
					this.dispose();
					new FrmMain(nv).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại", "Lỗi đăng nhập",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(this, "Chưa nhập tên tài khoản hoặc mật khẩu");
			}
		} else if (o.equals(btnThoat)) {
			int sel = JOptionPane.showConfirmDialog(this, "Thoát chương trình?", "Đăng xuất",
					JOptionPane.YES_NO_OPTION);
			if (sel == JOptionPane.YES_OPTION) {
				dispose();
			}
		}

	}

}
