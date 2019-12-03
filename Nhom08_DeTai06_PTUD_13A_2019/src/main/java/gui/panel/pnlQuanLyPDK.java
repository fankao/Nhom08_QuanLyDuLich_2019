package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import control.IKhachHangControl;
import control.IPhieuDangKyControl;
import control.impl.KhachHangControlImpl;
import control.impl.PhieuDangKyControlImpl;
import entities.KhachHang;
import entities.KhachHangThamGia;
import entities.PhieuDangKy;
import entities.Tour;
import model.DSKhachHangTGTableModel;
import model.DSKhachHangTableModel;
import model.DSPhieuDangKyModel;
import utils.TienIch;
import javax.swing.ListSelectionModel;

public class pnlQuanLyPDK extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaPhieuDK;
	private JTextField txtNgayTao;
	private JTextField txtTrangThai;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSdtKh;
	private JTextField txtDiaChiKH;
	private JTextField txtCMND_KH;
	private JTextField txtTimKiemTour;
	private JTextField txtTimKiemPDK;
	private JComboBox<String> cmbTimKiemTour;
	private JComboBox<String> cmbTimKiemPDK;
	private JTextField txtTenTour;
	private JTextField txtKhoiHanh;
	private JTextField txtSoKhach;
	private JTextField txtNgayKetThuc;
	private JButton btnThem;
	private JButton btnXoa1;
	private JButton btnluu;
	private JButton btnHuy;
	private JButton BtnBoChon;
	private JTable tblDSKhachHang;
	private JTable tblDSPhieuDangKy;
	private JTable tblDSKhachTG;
	private IPhieuDangKyControl phieuDangKyControl;
	private IKhachHangControl khachHangControl;
	static List<KhachHang> dsKhachHang;
	static List<PhieuDangKy> dsPDK;
	static List<KhachHangThamGia> dsKhachHangTG;
	private JScrollPane srcDsKhachHang;
	private JScrollPane srcDsKhachTG;
	private JButton btnBoChonKhachHang;
	private JButton btnBoChonPDK;

	/**
	 * Create the panel.
	 */
	public pnlQuanLyPDK() {
		setSize(1500, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel pnlMain = new JPanel();
		add(pnlMain, BorderLayout.CENTER);
		pnlMain.setLayout(new BorderLayout(0, 0));

		JPanel pnlQLPhieuDK = new JPanel();
		pnlQLPhieuDK.setBorder(null);
		pnlMain.add(pnlQLPhieuDK, BorderLayout.CENTER);
		pnlQLPhieuDK.setLayout(new BorderLayout(0, 0));

		JPanel pnlPDKvaTour = new JPanel();
		pnlPDKvaTour.setPreferredSize(new Dimension(600, 10));
		pnlPDKvaTour.setBorder(null);
		pnlQLPhieuDK.add(pnlPDKvaTour, BorderLayout.WEST);
		pnlPDKvaTour.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel pnlTTTour = new JPanel();
		pnlTTTour.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch kh\u00E1ch h\u00E0ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlPDKvaTour.add(pnlTTTour);
		pnlTTTour.setLayout(new BorderLayout(0, 0));

		JPanel pnlTimKiemTour = new JPanel();
		FlowLayout fl_pnlTimKiemTour = (FlowLayout) pnlTimKiemTour.getLayout();
		fl_pnlTimKiemTour.setAlignment(FlowLayout.LEFT);
		pnlTTTour.add(pnlTimKiemTour, BorderLayout.NORTH);

		txtTimKiemTour = new JTextField();
		txtTimKiemTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiemTour.setColumns(15);
		pnlTimKiemTour.add(txtTimKiemTour);

		cmbTimKiemTour = new JComboBox();
		cmbTimKiemTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTimKiemTour.add(cmbTimKiemTour);

		JPanel pnlDsTour = new JPanel();
		pnlTTTour.add(pnlDsTour, BorderLayout.CENTER);
		pnlDsTour.setLayout(new BorderLayout(0, 0));

		srcDsKhachHang = new JScrollPane();
		pnlDsTour.add(srcDsKhachHang, BorderLayout.CENTER);

		JPanel pnlTT_PDK = new JPanel();
		pnlTT_PDK.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch phi\u1EBFu \u0111\u0103ng k\u00FD",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPDKvaTour.add(pnlTT_PDK);
		pnlTT_PDK.setLayout(new BorderLayout(0, 0));

		JPanel pnlTimKiemPDK = new JPanel();
		FlowLayout fl_pnlTimKiemPDK = (FlowLayout) pnlTimKiemPDK.getLayout();
		fl_pnlTimKiemPDK.setAlignment(FlowLayout.LEFT);
		pnlTT_PDK.add(pnlTimKiemPDK, BorderLayout.NORTH);

		txtTimKiemPDK = new JTextField();
		txtTimKiemPDK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiemPDK.setColumns(15);
		pnlTimKiemPDK.add(txtTimKiemPDK);

		cmbTimKiemPDK = new JComboBox();
		cmbTimKiemPDK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTimKiemPDK.add(cmbTimKiemPDK);

		JPanel pnlDsPDK = new JPanel();
		pnlTT_PDK.add(pnlDsPDK, BorderLayout.CENTER);
		pnlDsPDK.setLayout(new BorderLayout(0, 0));

		JScrollPane srcDsPDK = new JScrollPane();
		pnlDsPDK.add(srcDsPDK, BorderLayout.CENTER);
		
		JPanel pnlBoChonPDK = new JPanel();
		FlowLayout fl_pnlBoChonPDK = (FlowLayout) pnlBoChonPDK.getLayout();
		fl_pnlBoChonPDK.setAlignment(FlowLayout.RIGHT);
		pnlTT_PDK.add(pnlBoChonPDK, BorderLayout.SOUTH);
		
		btnBoChonPDK = new JButton("Bỏ chọn");
		pnlBoChonPDK.add(btnBoChonPDK);

		JPanel pnlThongTinCT_PDK = new JPanel();
		pnlQLPhieuDK.add(pnlThongTinCT_PDK, BorderLayout.CENTER);
		pnlThongTinCT_PDK.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabThongTinPDK_PT = new JTabbedPane(JTabbedPane.TOP);
		tabThongTinPDK_PT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlThongTinCT_PDK.add(tabThongTinPDK_PT, BorderLayout.CENTER);

		JPanel pnlThongTinPhieuDK = new JPanel();
		tabThongTinPDK_PT.addTab("Thông tin phiếu đăng ký", null, pnlThongTinPhieuDK, null);
		pnlThongTinPhieuDK.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel_2 = new JPanel();
		pnlThongTinPhieuDK.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel pnlKhachHang = new JPanel();
		panel_2.add(pnlKhachHang);
		pnlKhachHang.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Kh\u00E1ch H\u00E0ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblMaKH = new JLabel("Mã khách hàng :");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtMaKH = new JTextField();
		txtMaKH.setHorizontalAlignment(SwingConstants.LEFT);
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);

		JLabel lblTenKH = new JLabel("Tên khách hàng :");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTenKH = new JTextField();
		txtTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);

		JLabel lblSdtKH = new JLabel("Số điện thoại :");
		lblSdtKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSdtKh = new JTextField();
		txtSdtKh.setHorizontalAlignment(SwingConstants.LEFT);
		txtSdtKh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSdtKh.setEditable(false);
		txtSdtKh.setColumns(10);

		JLabel lblCMDN_KH = new JLabel("Số CMND :");
		lblCMDN_KH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblDiachiKH = new JLabel("Địa chỉ :");
		lblDiachiKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtDiaChiKH = new JTextField();
		txtDiaChiKH.setHorizontalAlignment(SwingConstants.LEFT);
		txtDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDiaChiKH.setEditable(false);
		txtDiaChiKH.setColumns(10);

		txtCMND_KH = new JTextField();
		txtCMND_KH.setHorizontalAlignment(SwingConstants.LEFT);
		txtCMND_KH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCMND_KH.setEditable(false);
		txtCMND_KH.setColumns(10);
		GroupLayout gl_pnlKhachHang = new GroupLayout(pnlKhachHang);
		gl_pnlKhachHang.setHorizontalGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlKhachHang.createSequentialGroup().addContainerGap().addGroup(gl_pnlKhachHang
						.createParallelGroup(Alignment.LEADING, false).addGroup(gl_pnlKhachHang.createSequentialGroup()
								.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING).addComponent(lblMaKH)
										.addComponent(lblDiachiKH, GroupLayout.PREFERRED_SIZE, 121,
												GroupLayout.PREFERRED_SIZE))
								.addGap(27)
								.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtDiaChiKH, GroupLayout.PREFERRED_SIZE, 619,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_pnlKhachHang.createSequentialGroup()
												.addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, 173,
														GroupLayout.PREFERRED_SIZE)
												.addGap(54)
												.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
														.addComponent(lblCMDN_KH)
														.addGroup(gl_pnlKhachHang.createSequentialGroup()
																.addComponent(lblTenKH, GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, 233,
																		GroupLayout.PREFERRED_SIZE))))))
						.addGroup(gl_pnlKhachHang.createSequentialGroup().addComponent(lblSdtKH).addGap(44)
								.addComponent(txtSdtKh, 0, 0, Short.MAX_VALUE).addGap(213)
								.addComponent(txtCMND_KH, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(82, Short.MAX_VALUE)));
		gl_pnlKhachHang.setVerticalGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlKhachHang
				.createSequentialGroup()
				.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlKhachHang
						.createSequentialGroup().addGap(4)
						.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMaKH, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
								.addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(10))
						.addGroup(gl_pnlKhachHang.createSequentialGroup().addGap(3)
								.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTenKH, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
										.addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(2)
				.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSdtKH, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSdtKh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCMND_KH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCMDN_KH, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiachiKH, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(txtDiaChiKH, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
				.addGap(12)));
		pnlKhachHang.setLayout(gl_pnlKhachHang);

		JPanel pnlPDK = new JPanel();
		panel_2.add(pnlPDK);
		pnlPDK.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Phi\u1EBFu \u0111\u0103ng k\u00FD",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblPhieuDK = new JLabel("Mã phiếu đăng ký :");
		lblPhieuDK.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtMaPhieuDK = new JTextField();
		txtMaPhieuDK.setHorizontalAlignment(SwingConstants.LEFT);
		txtMaPhieuDK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaPhieuDK.setEditable(false);
		txtMaPhieuDK.setColumns(10);

		JLabel lblNgayTao = new JLabel("Ngày tạo:");
		lblNgayTao.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtNgayTao = new JTextField();
		txtNgayTao.setHorizontalAlignment(SwingConstants.LEFT);
		txtNgayTao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNgayTao.setEditable(false);
		txtNgayTao.setColumns(10);

		JLabel lblTrangThai = new JLabel("Trạng thái :");
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTrangThai = new JTextField();
		txtTrangThai.setHorizontalAlignment(SwingConstants.LEFT);
		txtTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTrangThai.setEditable(false);
		txtTrangThai.setColumns(10);

		JLabel lblTnTour = new JLabel("Tên tour :");
		lblTnTour.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTenTour = new JTextField();
		txtTenTour.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenTour.setEditable(false);
		txtTenTour.setColumns(10);

		JLabel lblNgyKhiHnh = new JLabel("Ngày khởi hành :");
		lblNgyKhiHnh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtKhoiHanh = new JTextField();
		txtKhoiHanh.setText("15\\15\\2222");
		txtKhoiHanh.setHorizontalAlignment(SwingConstants.LEFT);
		txtKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtKhoiHanh.setEditable(false);
		txtKhoiHanh.setColumns(10);

		JLabel lblSKhch = new JLabel("Số khách :");
		lblSKhch.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSoKhach = new JTextField();
		txtSoKhach.setHorizontalAlignment(SwingConstants.LEFT);
		txtSoKhach.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoKhach.setEditable(false);
		txtSoKhach.setColumns(10);

		JLabel lblNgyKtThc = new JLabel("Ngày kết thúc :");
		lblNgyKtThc.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtNgayKetThuc = new JTextField();
		txtNgayKetThuc.setText("15\\15\\2222");
		txtNgayKetThuc.setHorizontalAlignment(SwingConstants.LEFT);
		txtNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNgayKetThuc.setEditable(false);
		txtNgayKetThuc.setColumns(10);
		GroupLayout gl_pnlPDK = new GroupLayout(pnlPDK);
		gl_pnlPDK.setHorizontalGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPDK.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPhieuDK, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTnTour, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgyKhiHnh))
						.addGap(7)
						.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlPDK.createSequentialGroup()
										.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtTenTour)
												.addGroup(gl_pnlPDK.createSequentialGroup()
														.addComponent(txtMaPhieuDK, GroupLayout.PREFERRED_SIZE, 144,
																GroupLayout.PREFERRED_SIZE)
														.addGap(12)
														.addComponent(lblNgayTao, GroupLayout.PREFERRED_SIZE, 90,
																GroupLayout.PREFERRED_SIZE)
														.addGap(5).addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE,
																144, GroupLayout.PREFERRED_SIZE)))
										.addGap(12)
										.addGroup(gl_pnlPDK.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_pnlPDK.createSequentialGroup()
														.addComponent(lblTrangThai, GroupLayout.PREFERRED_SIZE, 116,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED))
												.addGroup(Alignment.LEADING,
														gl_pnlPDK.createSequentialGroup()
																.addComponent(lblSKhch, GroupLayout.PREFERRED_SIZE, 99,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(21)))
										.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
												.addComponent(txtTrangThai, GroupLayout.DEFAULT_SIZE, 161,
														Short.MAX_VALUE)
												.addComponent(txtSoKhach, GroupLayout.DEFAULT_SIZE, 161,
														Short.MAX_VALUE)))
								.addGroup(gl_pnlPDK.createSequentialGroup()
										.addComponent(txtKhoiHanh, GroupLayout.PREFERRED_SIZE, 163,
												GroupLayout.PREFERRED_SIZE)
										.addGap(35)
										.addComponent(lblNgyKtThc, GroupLayout.PREFERRED_SIZE, 151,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtNgayKetThuc,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_pnlPDK.setVerticalGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPDK.createSequentialGroup().addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlPDK.createSequentialGroup().addGap(16).addComponent(lblTrangThai,
								GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlPDK.createSequentialGroup().addContainerGap().addGroup(gl_pnlPDK
								.createParallelGroup(Alignment.LEADING)
								.addComponent(txtTrangThai, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlPDK.createSequentialGroup().addGap(3).addComponent(lblPhieuDK,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtMaPhieuDK, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlPDK.createSequentialGroup().addGap(3).addComponent(lblNgayTao,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlPDK
										.createSequentialGroup().addGap(18)
										.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblTnTour).addComponent(txtTenTour,
														GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_pnlPDK.createSequentialGroup().addGap(14)
										.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtSoKhach, GroupLayout.PREFERRED_SIZE, 35,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSKhch, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlPDK
								.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
								.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNgyKtThc, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtNgayKetThuc, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
								.addGroup(gl_pnlPDK.createSequentialGroup().addGap(18)
										.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtKhoiHanh, GroupLayout.PREFERRED_SIZE, 35,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNgyKhiHnh, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE))
										.addContainerGap()))));
		pnlPDK.setLayout(gl_pnlPDK);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Danh s\u00E1ch kh\u00E1ch h\u00E0ng \u0111i c\u00F9ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinPhieuDK.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel pnlChucNang = new JPanel();
		FlowLayout fl_pnlChucNang = (FlowLayout) pnlChucNang.getLayout();
		fl_pnlChucNang.setAlignment(FlowLayout.RIGHT);
		panel_3.add(pnlChucNang, BorderLayout.NORTH);

		btnThem = new JButton("Thêm");
		pnlChucNang.add(btnThem);

		btnXoa1 = new JButton("Xóa");
		pnlChucNang.add(btnXoa1);

		btnHuy = new JButton("Hủy");
		pnlChucNang.add(btnHuy);

		btnluu = new JButton("Lưu");
		pnlChucNang.add(btnluu);

		BtnBoChon = new JButton("Bỏ chọn");
		pnlChucNang.add(BtnBoChon);

		srcDsKhachTG = new JScrollPane();
		panel_3.add(srcDsKhachTG, BorderLayout.CENTER);

		JPanel pnlDsKhachHangDiCung = new JPanel();
		pnlDsKhachHangDiCung.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabThongTinPDK_PT.addTab("Danh sách phiếu chi và thu", null, pnlDsKhachHangDiCung, null);
		pnlDsKhachHangDiCung.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch phi\u1EBFu thu",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDsKhachHangDiCung.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch phi\u1EBFu chi",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDsKhachHangDiCung.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, BorderLayout.CENTER);

		JPanel pnlHuyPhieu = new JPanel();
		pnlThongTinCT_PDK.add(pnlHuyPhieu, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Hủy phiếu đăng ký");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlHuyPhieu.add(btnNewButton);
		JPanel pnlBoChonKH = new JPanel();
		FlowLayout fl_pnlBoChonKH = (FlowLayout) pnlBoChonKH.getLayout();
		fl_pnlBoChonKH.setAlignment(FlowLayout.RIGHT);
		pnlTTTour.add(pnlBoChonKH, BorderLayout.SOUTH);
		
		btnBoChonKhachHang = new JButton("Bỏ chọn");
		pnlBoChonKH.add(btnBoChonKhachHang);
		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] {}, "Tahoma", Font.PLAIN, 18);
		
		
		
		khachHangControl = new KhachHangControlImpl(); 
		phieuDangKyControl = new PhieuDangKyControlImpl();
		
		tblDSKhachHang = new JTable();
		tblDSKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSPhieuDangKy = new JTable();
		tblDSPhieuDangKy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSKhachTG = new JTable();
		tblDSKhachTG.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dsKhachHang = khachHangControl.layDSKhachHang();
		dsPDK = phieuDangKyControl.layDSPhieuDangKy();
		dsKhachHangTG = new ArrayList<KhachHangThamGia>();
		hienBangDSKhachHang(tblDSKhachHang, dsKhachHang, srcDsKhachHang);
		hienBangDSPDK(tblDSPhieuDangKy, dsPDK, srcDsPDK);
		hienBangDSKhachTG(tblDSKhachTG, dsKhachHangTG, srcDsKhachTG);
	}
	
	private void hienBangDSKhachHang(JTable tbl, List<KhachHang> ds, JScrollPane src) {
		DSKhachHangTableModel dsKhachHangTableModel = new DSKhachHangTableModel(ds);
		tbl.setModel(dsKhachHangTableModel);
		src.setViewportView(tbl);

	}
	private void hienBangDSPDK(JTable tbl, List<PhieuDangKy> ds, JScrollPane src) {
		DSPhieuDangKyModel dsPhieuDangKyModel = new DSPhieuDangKyModel(ds);
		tbl.setModel(dsPhieuDangKyModel);
		src.setViewportView(tbl);

	}
	private void hienBangDSKhachTG(JTable tbl, List<KhachHangThamGia> ds, JScrollPane src) {
		DSKhachHangTGTableModel dsKhachHangTGTableModel = new DSKhachHangTGTableModel(ds);
		tbl.setModel(dsKhachHangTGTableModel);
		src.setViewportView(tbl);

	}

}
