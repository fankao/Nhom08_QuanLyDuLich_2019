package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entities.NhanVien;
import gui.panel.pnlQuanLyPhieuDangKy;
import gui.panel.pnlQuanLyTour;
import gui.panel.pnlThongKe;
import utils.TienIch;

/**
 * frmMain.java
 * 
 * @author Nguyen Minh Chien
 * @version 1.0 Ngay tao: 30/09/2019
 * 
 *
 */
public class frmMain extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnQuanLyTour;
	private JButton btnQuanLyPhieuDK;
	private JButton btnThongKe;
	private JButton btnDangXuat;
	private JPanel pnlCenter;
	private static JPanel pnlButtonBar;
	private static JPanel pnlContent;
	private JLabel lblWelcome;
	private JLabel lblTenNV;
	private JPanel pnlTieuDe;
	private JPanel pnlTenCT;
	private JLabel lblTenCT;
	private JPanel pnlDangXuat;
	private JLabel lblChucVu;
	private JLabel lblTenCV;
	private NhanVien nhanVien;

	/**
	 * Giao diện màn hình chính
	 */
	public frmMain(NhanVien nv) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		 * setMaximumSize(DimMax);
		 */
		this.nhanVien = nv;
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setSize(env.getMaximumWindowBounds().getSize());

		setIconImage(Toolkit.getDefaultToolkit().getImage(frmMain.class.getResource("/images/iconFrm.png")));
		setTitle("Chương trình quản lý thông tin du lịch công ty Phương Nam");
		setBackground(Color.WHITE);

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		JPanel pnlLeft = new JPanel();
		pnlLeft.setAlignmentY(Component.TOP_ALIGNMENT);
		pnlLeft.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnlLeft.setBackground(new Color(23, 35, 51));
		pnlLeft.setBorder(new EmptyBorder(0, 0, 0, 0));

		pnlCenter = new JPanel();
		pnlCenter.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlCenter.setBackground(new Color(255, 255, 255));
		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));

		pnlTieuDe = new JPanel();
		pnlTieuDe.setPreferredSize(new Dimension(10, 70));
		pnlCenter.add(pnlTieuDe);
		pnlTieuDe.setLayout(new GridLayout(0, 2, 0, 0));

		pnlTenCT = new JPanel();
		pnlTenCT.setBackground(new Color(71, 120, 197));
		FlowLayout flowLayout_1 = (FlowLayout) pnlTenCT.getLayout();
		flowLayout_1.setVgap(20);
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnlTieuDe.add(pnlTenCT);

		lblTenCT = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ THÔNG TIN DU LỊCH");
		lblTenCT.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenCT.setForeground(Color.WHITE);
		lblTenCT.setFont(new Font("Arial", Font.BOLD, 27));
		pnlTenCT.add(lblTenCT);

		pnlDangXuat = new JPanel();
		FlowLayout fl_pnlDangXuat = (FlowLayout) pnlDangXuat.getLayout();
		fl_pnlDangXuat.setVgap(20);
		fl_pnlDangXuat.setAlignment(FlowLayout.RIGHT);
		pnlDangXuat.setBackground(new Color(71, 120, 197));
		pnlTieuDe.add(pnlDangXuat);

		pnlButtonBar = new JPanel();
		pnlButtonBar.setPreferredSize(new Dimension(10, 35));
		pnlButtonBar.setBorder(new EmptyBorder(0, 2, 0, 2));
		pnlButtonBar.setBackground(new Color(242, 247, 247));
		FlowLayout flowLayout = (FlowLayout) pnlButtonBar.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlCenter.add(pnlButtonBar);

		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5, 10, 5, 10));
		pnlContent.setBackground(Color.WHITE);
		pnlCenter.add(pnlContent);
		pnlContent.setLayout(new BorderLayout(0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));

		btnQuanLyTour = new JButton("Quản lý tour du lịch");
		btnQuanLyTour.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuanLyTour.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnQuanLyTour.setIcon(new ImageIcon(frmMain.class.getResource("/images/qltour.png")));
		btnQuanLyTour.setFocusable(false);
		btnQuanLyTour.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnQuanLyTour.setForeground(new Color(255, 255, 255));
		btnQuanLyTour.setFont(new Font("Tahoma", Font.BOLD, 22));

		btnQuanLyPhieuDK = new JButton("Quản lý phiếu đăng ký");
		btnQuanLyPhieuDK.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuanLyPhieuDK.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnQuanLyPhieuDK.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnQuanLyPhieuDK.setIcon(new ImageIcon(frmMain.class.getResource("/images/qlpdk.png")));
		btnQuanLyPhieuDK.setFocusable(false);
		btnQuanLyPhieuDK.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnQuanLyPhieuDK.setForeground(new Color(255, 255, 255));
		btnQuanLyPhieuDK.setFont(new Font("Tahoma", Font.BOLD, 22));

		btnThongKe = new JButton("Thống kê");
		btnThongKe.setHorizontalAlignment(SwingConstants.LEFT);
		btnThongKe.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnThongKe.setIcon(new ImageIcon(frmMain.class.getResource("/images/thongke.png")));
		btnThongKe.setFocusable(false);
		btnThongKe.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnThongKe.setForeground(new Color(255, 255, 255));
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 22));

		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
		btnDangXuat.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnDangXuat.setIcon(new ImageIcon(frmMain.class.getResource("/images/dangxuat.png")));
		btnDangXuat.setFocusable(false);
		btnDangXuat.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnDangXuat.setForeground(new Color(255, 255, 255));
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD, 22));

		JPanel pnlTTNhanVien = new JPanel();
		pnlTTNhanVien.setBackground(new Color(102, 255, 255));

		GroupLayout gl_pnLeft = new GroupLayout(pnlLeft);
		gl_pnLeft.setHorizontalGroup(gl_pnLeft.createParallelGroup(Alignment.TRAILING)
				.addComponent(btnThongKe, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
				.addComponent(btnQuanLyTour, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
				.addComponent(btnQuanLyPhieuDK, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
				.addComponent(btnDangXuat, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
				.addComponent(pnlTTNhanVien, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE));
		gl_pnLeft.setVerticalGroup(gl_pnLeft.createParallelGroup(Alignment.LEADING).addGroup(gl_pnLeft
				.createSequentialGroup()
				.addComponent(pnlTTNhanVien, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE).addGap(31)
				.addComponent(btnQuanLyTour, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE).addGap(30)
				.addComponent(btnQuanLyPhieuDK, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE).addGap(30)
				.addComponent(btnThongKe, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 328, Short.MAX_VALUE)
				.addComponent(btnDangXuat, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE).addGap(36)));
		pnlTTNhanVien.setLayout(new BorderLayout(0, 0));

		JPanel pnlHinhNV = new JPanel();
		pnlHinhNV.setBackground(new Color(39, 89, 84));
		pnlTTNhanVien.add(pnlHinhNV, BorderLayout.WEST);
		pnlHinhNV.setLayout(new BorderLayout(0, 0));

		JLabel lblHinhNV = new JLabel("");
		lblHinhNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhNV.setIcon(new ImageIcon(frmMain.class.getResource("/images/user.png")));
		pnlHinhNV.add(lblHinhNV, BorderLayout.CENTER);

		JPanel pnlLoiChao = new JPanel();
		pnlLoiChao.setBackground(new Color(51, 153, 153));
		pnlTTNhanVien.add(pnlLoiChao, BorderLayout.CENTER);

		lblWelcome = new JLabel("Xin chào,");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTenNV = new JLabel("Nguyễn Văn A");
		lblTenNV.setForeground(Color.WHITE);
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 25));

		JButton btnThoat = new JButton("");
		btnThoat.setBorder(null);
		btnThoat.setFocusable(false);

		lblChucVu = new JLabel("");
		lblChucVu.setIcon(new ImageIcon(frmMain.class.getResource("/images/job.png")));

		lblTenCV = new JLabel("Nhân viên");
		lblTenCV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenCV.setForeground(Color.WHITE);
		GroupLayout gl_pnlLoiChao = new GroupLayout(pnlLoiChao);
		gl_pnlLoiChao
				.setHorizontalGroup(gl_pnlLoiChao.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlLoiChao.createSequentialGroup()
								.addGroup(gl_pnlLoiChao
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pnlLoiChao.createSequentialGroup().addContainerGap()
												.addGroup(gl_pnlLoiChao.createParallelGroup(Alignment.LEADING)
														.addComponent(btnThoat).addComponent(lblWelcome)))
										.addGroup(gl_pnlLoiChao.createSequentialGroup().addComponent(lblChucVu)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblTenCV))
										.addGroup(gl_pnlLoiChao.createSequentialGroup().addContainerGap()
												.addComponent(lblTenNV)))
								.addContainerGap(134, Short.MAX_VALUE)));
		gl_pnlLoiChao.setVerticalGroup(gl_pnlLoiChao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLoiChao.createSequentialGroup().addGap(23).addComponent(lblWelcome).addGap(12)
						.addComponent(lblTenNV).addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_pnlLoiChao.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTenCV, Alignment.TRAILING).addComponent(lblChucVu, Alignment.TRAILING))
						.addGap(26).addComponent(btnThoat).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlLoiChao.setLayout(gl_pnlLoiChao);
		pnlLeft.setLayout(gl_pnLeft);

		// thiết lập màu cho button
		btnQuanLyTour.setBackground(new Color(23, 35, 51));
		btnQuanLyPhieuDK.setBackground(new Color(23, 35, 51));
		btnThongKe.setBackground(new Color(23, 35, 51));
		btnDangXuat.setBackground(new Color(23, 35, 51));
		contentPane.add(pnlLeft, BorderLayout.WEST);
		contentPane.add(pnlCenter);

		// gán sự kiện
		ganSuKien();

		lblTenNV.setText(nv.getHoVaTen());
		lblTenCV.setText(nv.getQuyen().getGhiChu());

	}

	/**
	 * Hàm gán sự kiện cho button
	 */
	private void ganSuKien() {
		btnDangXuat.addActionListener(this);
		btnQuanLyPhieuDK.addActionListener(this);
		btnQuanLyTour.addActionListener(this);
		btnThongKe.addActionListener(this);
		btnQuanLyPhieuDK.addActionListener(this);

	}

	/**
	 * Hàm xử lý sự kiện cho button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnQuanLyTour)) {
			TienIch.xoaDuongDan(pnlButtonBar, 1);
			TienIch.doiMauButton(btnQuanLyTour, new JButton[] { btnDangXuat, btnQuanLyPhieuDK, btnThongKe });
			TienIch.chuyenPanelKhiNhan(pnlContent, new pnlQuanLyTour(nhanVien));
			TienIch.themDuongDan(pnlButtonBar, btnQuanLyTour.getText());

		} else if (o.equals(btnQuanLyPhieuDK)) {
			TienIch.xoaDuongDan(pnlButtonBar, 1);
			TienIch.doiMauButton(btnQuanLyPhieuDK, new JButton[] { btnDangXuat, btnQuanLyTour, btnThongKe });
			TienIch.chuyenPanelKhiNhan(pnlContent, new pnlQuanLyPhieuDangKy());
			TienIch.themDuongDan(pnlButtonBar, btnQuanLyPhieuDK.getText());

		} else if (o.equals(btnThongKe)) {
			TienIch.xoaDuongDan(pnlButtonBar, 1);
			TienIch.doiMauButton(btnThongKe, new JButton[] { btnDangXuat, btnQuanLyTour, btnQuanLyPhieuDK });
			TienIch.chuyenPanelKhiNhan(pnlContent, new pnlThongKe());
			TienIch.themDuongDan(pnlButtonBar, btnThongKe.getText());

		} else if (o.equals(btnDangXuat)) {
			TienIch.doiMauButton(btnDangXuat, new JButton[] { btnQuanLyTour, btnQuanLyPhieuDK, btnThongKe });
			if (JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất khỏi phần mềm?", "Đăng xuất",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				((Window) getRootPane().getParent()).dispose();
				new frmDangNhap().setVisible(true);
			}
		}

	}

	public static JPanel getPnButtonBar() {
		return pnlButtonBar;
	}

	public static JPanel getPnContent() {
		return pnlContent;
	}

}
