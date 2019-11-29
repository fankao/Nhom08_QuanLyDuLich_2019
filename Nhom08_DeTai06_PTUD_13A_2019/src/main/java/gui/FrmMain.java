package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import entities.NhanVien;
import gui.panel.PnlDangKyTour;
import gui.panel.PnlDuyetTour;
import gui.panel.PnlHinhNen;
import gui.panel.PnlTaoTour;
import gui.panel.pnlThongKe;
import test.Testing;
import utils.TienIch;

/**
 * frmMain.java
 * 
 * @author Nguyen Minh Chien
 * @version 1.0 Ngay tao: 30/09/2019
 * 
 *
 */
public class FrmMain extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCheckBox chkQuanLyTour;
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
	private JButton btnCapNhatTour;
	private JButton btnDangKyTour;
	private JButton btnHuyTourDK;
	private JPanel pnlButtonBoder;
	private JPanel pnlCardLayout;
	private PnlTaoTour pnlTaoTour;
	private PnlDangKyTour pnlDangKyTour;
	private PnlHinhNen pnlHinhNen;
	private JPanel pnlSubButton;
	private JButton btnDuyetTour;
	private PnlDuyetTour pnlDuyetTour;

	/**
	 * Giao diện màn hình chính
	 */
	public FrmMain(NhanVien nv) {
		setSize(1800, 960);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 * 
		 * Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		 * setMaximumSize(DimMax);
		 * 
		 * 
		 * setExtendedState(JFrame.MAXIMIZED_BOTH); GraphicsEnvironment env =
		 * GraphicsEnvironment.getLocalGraphicsEnvironment();
		 * setSize(env.getMaximumWindowBounds().getSize());
		 */

		this.nhanVien = nv;
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmMain.class.getResource("/images/iconFrm.png")));
		setTitle("Chương trình quản lý thông tin du lịch công ty Phương Nam");
		setBackground(Color.WHITE);

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		JPanel pnlLeft = new JPanel();
		pnlLeft.setPreferredSize(new Dimension(400, 10));
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
		pnlContent.setBorder(new EmptyBorder(2, 2, 2, 2));
		pnlContent.setBackground(Color.WHITE);
		pnlCenter.add(pnlContent);
		contentPane.setLayout(new BorderLayout(0, 0));

		chkQuanLyTour = new JCheckBox("Quản lý tour du lịch");
		chkQuanLyTour.setHorizontalAlignment(SwingConstants.LEFT);
		chkQuanLyTour.setHorizontalTextPosition(SwingConstants.RIGHT);
		chkQuanLyTour.setIcon(new ImageIcon(FrmMain.class.getResource("/images/qltour.png")));
		chkQuanLyTour.setFocusable(false);
		chkQuanLyTour.setBorder(new EmptyBorder(0, 20, 0, 20));
		chkQuanLyTour.setForeground(new Color(255, 255, 255));
		chkQuanLyTour.setFont(new Font("Tahoma", Font.BOLD, 28));

		btnQuanLyPhieuDK = new JButton("Khách hàng");
		btnQuanLyPhieuDK.setHorizontalAlignment(SwingConstants.LEFT);
		btnQuanLyPhieuDK.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnQuanLyPhieuDK.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnQuanLyPhieuDK.setIcon(new ImageIcon(FrmMain.class.getResource("/images/qlpdk.png")));
		btnQuanLyPhieuDK.setFocusable(false);
		btnQuanLyPhieuDK.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnQuanLyPhieuDK.setForeground(new Color(255, 255, 255));
		btnQuanLyPhieuDK.setFont(new Font("Tahoma", Font.BOLD, 28));

		btnThongKe = new JButton("Thống kê");
		btnThongKe.setHorizontalAlignment(SwingConstants.LEFT);
		btnThongKe.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnThongKe.setIcon(new ImageIcon(FrmMain.class.getResource("/images/thongke.png")));
		btnThongKe.setFocusable(false);
		btnThongKe.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnThongKe.setForeground(new Color(255, 255, 255));
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 28));

		btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
		btnDangXuat.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnDangXuat.setIcon(new ImageIcon(FrmMain.class.getResource("/images/dangxuat.png")));
		btnDangXuat.setFocusable(false);
		btnDangXuat.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnDangXuat.setForeground(new Color(255, 255, 255));
		btnDangXuat.setFont(new Font("Tahoma", Font.BOLD, 22));

		JPanel pnlTTNhanVien = new JPanel();
		pnlTTNhanVien.setBackground(new Color(102, 255, 255));

		pnlButtonBoder = new JPanel();
		pnlButtonBoder.setVisible(false);
		pnlButtonBoder.setFocusable(false);
		pnlButtonBoder.setBorder(null);

		GroupLayout gl_pnLeft = new GroupLayout(pnlLeft);
		gl_pnLeft.setHorizontalGroup(
			gl_pnLeft.createParallelGroup(Alignment.LEADING)
				.addComponent(chkQuanLyTour, GroupLayout.PREFERRED_SIZE, 405, Short.MAX_VALUE)
				.addComponent(pnlTTNhanVien, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
				.addComponent(btnQuanLyPhieuDK, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
				.addGroup(gl_pnLeft.createSequentialGroup()
					.addComponent(btnDangXuat, GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(pnlButtonBoder, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
				.addComponent(btnThongKe, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
		);
		gl_pnLeft.setVerticalGroup(
			gl_pnLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnLeft.createSequentialGroup()
					.addComponent(pnlTTNhanVien, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(chkQuanLyTour, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlButtonBoder, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnQuanLyPhieuDK, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnThongKe, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(164)
					.addComponent(btnDangXuat, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlButtonBoder.setLayout(new BorderLayout(0, 0));

		pnlSubButton = new JPanel();
		pnlButtonBoder.add(pnlSubButton);
		pnlSubButton.setLayout(new GridLayout(4, 1, 0, 0));

		btnCapNhatTour = new JButton("Câp nhật tour");
		btnCapNhatTour.setHorizontalAlignment(SwingConstants.LEADING);
		pnlSubButton.add(btnCapNhatTour);
		btnCapNhatTour.setIcon(new ImageIcon(FrmMain.class.getResource("/images/create_new_32px.png")));
		btnCapNhatTour.setForeground(Color.WHITE);
		btnCapNhatTour.setFocusable(false);
		btnCapNhatTour.setBorder(new EmptyBorder(0, 30, 0, 0));
		btnCapNhatTour.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnCapNhatTour.setBackground(new Color(23, 35, 51));

		btnDangKyTour = new JButton("Đăng ký tour");
		btnDangKyTour.setHorizontalAlignment(SwingConstants.LEADING);
		pnlSubButton.add(btnDangKyTour);
		btnDangKyTour.setIcon(new ImageIcon(FrmMain.class.getResource("/images/submit_resume_32px.png")));
		btnDangKyTour.setForeground(Color.WHITE);
		btnDangKyTour.setFocusable(false);
		btnDangKyTour.setBorder(new EmptyBorder(0, 30, 0, 0));
		btnDangKyTour.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnDangKyTour.setBackground(new Color(23, 35, 51));

		btnHuyTourDK = new JButton("Huỷ tour đăng ký");
		btnHuyTourDK.setHorizontalAlignment(SwingConstants.LEADING);
		pnlSubButton.add(btnHuyTourDK);
		btnHuyTourDK.setIcon(new ImageIcon(FrmMain.class.getResource("/images/cancel_subscription_32px.png")));
		btnHuyTourDK.setForeground(Color.WHITE);
		btnHuyTourDK.setFocusable(false);
		btnHuyTourDK.setBorder(new EmptyBorder(0, 30, 0, 0));
		btnHuyTourDK.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnHuyTourDK.setBackground(new Color(23, 35, 51));
		pnlTTNhanVien.setLayout(new BorderLayout(0, 0));

		JPanel pnlHinhNV = new JPanel();
		pnlHinhNV.setBackground(new Color(39, 89, 84));
		pnlTTNhanVien.add(pnlHinhNV, BorderLayout.WEST);
		pnlHinhNV.setLayout(new BorderLayout(0, 0));

		JLabel lblHinhNV = new JLabel("");
		lblHinhNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhNV.setIcon(new ImageIcon(FrmMain.class.getResource("/images/user.png")));
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
		lblChucVu.setIcon(new ImageIcon(FrmMain.class.getResource("/images/job.png")));

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
		pnlContent.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnlLeft, BorderLayout.WEST);
		contentPane.add(pnlCenter);
		pnlCardLayout = new JPanel();
		pnlCardLayout.setLayout(new CardLayout(0, 0));
		pnlContent.add(pnlCardLayout, BorderLayout.CENTER);
		/*
		 * =========================================================================
		 */
		// Khởi tạo panel con
		pnlTaoTour = new PnlTaoTour(nv);
		pnlDangKyTour = new PnlDangKyTour(nv);
		pnlHinhNen = new PnlHinhNen();
		pnlDuyetTour = new PnlDuyetTour();

		// đặt tên cho panel
		pnlTaoTour.setName("pnlTaoTour");
		pnlDangKyTour.setName("pnlDangKyTour");
		pnlHinhNen.setName("pnlHinhNen");
		pnlDuyetTour.setName("pnlDuyetTour");

		pnlCardLayout.add(pnlHinhNen, pnlHinhNen.getName());
		pnlCardLayout.add(pnlTaoTour, pnlTaoTour.getName());
		pnlCardLayout.add(pnlDangKyTour, pnlDangKyTour.getName());
		pnlCardLayout.add(pnlDuyetTour, pnlDuyetTour.getName());

		// thiết lập màu cho button
		chkQuanLyTour.setBackground(new Color(23, 35, 51));
		btnCapNhatTour.setBackground(new Color(23, 35, 51));
		btnDangKyTour.setBackground(new Color(23, 35, 51));

		btnDuyetTour = new JButton("Duyệt tour");
		btnDuyetTour.setIcon(new ImageIcon(FrmMain.class.getResource("/images/check_file_32px.png")));
		btnDuyetTour.setHorizontalAlignment(SwingConstants.LEADING);
		btnDuyetTour.setForeground(Color.WHITE);
		btnDuyetTour.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnDuyetTour.setFocusable(false);
		btnDuyetTour.setBorder(new EmptyBorder(0, 30, 0, 0));
		btnDuyetTour.setBackground(new Color(23, 35, 51));
		pnlSubButton.add(btnDuyetTour);
		btnQuanLyPhieuDK.setBackground(new Color(23, 35, 51));
		btnThongKe.setBackground(new Color(23, 35, 51));
		btnDangXuat.setBackground(new Color(23, 35, 51));
		pnlButtonBoder.setBackground(new Color(23, 35, 51));

		// gán sự kiện
		ganSuKien();

		lblTenNV.setText(nv.getHoVaTen());
		lblTenCV.setText(nv.getQuyen().getGhiChu());

		TienIch.chinhLookAndFeelChoPanel(pnlCardLayout);

	}

	/**
	 * Hàm gán sự kiện cho button
	 */
	private void ganSuKien() {
		btnDangXuat.addActionListener(this);

		btnThongKe.addActionListener(this);
		btnQuanLyPhieuDK.addActionListener(this);

		btnCapNhatTour.addActionListener(this);
		btnHuyTourDK.addActionListener(this);
		btnDangKyTour.addActionListener(this);
		btnDuyetTour.addActionListener(this);

		chkQuanLyTour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (chkQuanLyTour.isSelected()) {
					chkQuanLyTour.setBackground(new Color(41, 57, 80));
					pnlButtonBoder.setVisible(true);

				} else {
					dongSubButton();
				}
			}
		});
		chkQuanLyTour.addActionListener(this);

	}

	private void dongSubButton() {
		chkQuanLyTour.setBackground(new Color(23, 35, 51));
		TienIch.chuyenPanelKhiNhan(pnlCardLayout, pnlHinhNen);
		TienIch.xoaDuongDan(pnlHinhNen, 1);
		btnCapNhatTour.setBackground(new Color(23, 35, 51));
		btnDangKyTour.setBackground(new Color(23, 35, 51));
		btnCapNhatTour.setForeground(Color.WHITE);
		btnHuyTourDK.setForeground(Color.WHITE);
		btnDangKyTour.setForeground(Color.WHITE);
		pnlButtonBoder.setVisible(false);

	}

	/**
	 * Hàm xử lý sự kiện cho button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		/*
		 * Hiện giao diện cập nhật tour
		 */
		if (o.equals(btnCapNhatTour)) {
			TienIch.xoaDuongDan(pnlButtonBar, 1);
			TienIch.themDuongDan(pnlButtonBar, btnCapNhatTour.getText());
			TienIch.doiMauButton(btnCapNhatTour, new JButton[] { btnDangXuat, btnQuanLyPhieuDK, btnDuyetTour,
					btnDangKyTour, btnHuyTourDK, btnThongKe });
			TienIch.chuyenPanelKhiNhan(pnlCardLayout, pnlTaoTour);

		}
		/*
		 * Hiện giao diện đăng ký tour
		 */
		else if (o.equals(btnDangKyTour)) {
			TienIch.xoaDuongDan(pnlButtonBar, 1);
			TienIch.themDuongDan(pnlButtonBar, btnDangKyTour.getText());
			TienIch.doiMauButton(btnDangKyTour,
					new JButton[] { btnDangXuat, btnCapNhatTour, btnDuyetTour, btnHuyTourDK, btnThongKe });
			TienIch.chuyenPanelKhiNhan(pnlCardLayout, pnlDangKyTour);

		}
		/*
		 * 
		 */
		else if (o.equals(btnThongKe)) {
			dongSubButton();
			TienIch.xoaDuongDan(pnlButtonBar, 1);
			TienIch.doiMauButton(btnThongKe,
					new JButton[] { btnDangXuat, btnDuyetTour, btnCapNhatTour, btnQuanLyPhieuDK });
			TienIch.themDuongDan(pnlButtonBar, btnThongKe.getText());
			TienIch.chuyenPanelKhiNhan(pnlContent, new pnlThongKe());

		}
		/*
		 * Hiện giao diện duyệt tour
		 */
		else if (o.equals(btnDuyetTour)) {
			TienIch.xoaDuongDan(pnlButtonBar, 1);
			TienIch.themDuongDan(pnlButtonBar, "Duyệt mở bán tour");
			TienIch.doiMauButton(btnDuyetTour, new JButton[] { btnDangXuat, btnQuanLyPhieuDK, btnDangKyTour,
					btnCapNhatTour, btnHuyTourDK, btnThongKe });
			TienIch.chuyenPanelKhiNhan(pnlCardLayout, pnlDuyetTour);
		}
		/*
		 * Thông báo xác nhận đăng xuất
		 */
		else if (o.equals(btnDangXuat)) {
			dongSubButton();
			TienIch.doiMauButton(btnDangXuat,
					new JButton[] { btnCapNhatTour, btnDuyetTour, btnQuanLyPhieuDK, btnThongKe });
			if (JOptionPane.showConfirmDialog(this, "Bạn muốn đăng xuất khỏi phần mềm?", "Đăng xuất",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				((Window) getRootPane().getParent()).dispose();
				Testing.main(new String[] {});
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
