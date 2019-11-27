package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import bus.IDangKyTourBUS;
import bus.IHuyDangKyTourBUS;
import bus.impl.DangKyTourBUS;
import bus.impl.HuyDangKyTourBUS;
import bus.impl.PhieuChiBUS;
import dao.IKhachHangThamGiaDAO;
import dao.impl.KhachHangThamGiaDAO;
import entities.KhachHang;
import entities.KhachHangThamGia;
import entities.PhieuThuChi;
import entities.PhieuDangKy;
import gui.frmMain;
import utils.HintTextFieldUI;
import utils.TienIch;

/**
 * 
 * @author Nguyễn Minh Chiến
 * @version 1.0 Ngày tạo 01/10/2019
 *
 */
public class pnlHuyDangKyTour extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaPDK;
	private JTextField txtNgayTaoPDK;
	private JTextField txtSoKhachThamGia;
	private JTextField txtMaKH;
	private JTextField txtHovaTenKH;
	private JTextField txtSoCMND;
	private JTextField txtNgaySinhKH;
	private JTextField txtSDT;
	private JTextField txtDiaChiKH;
	private JButton btnTimKiemKH;
	private JTextField txtTimKiemKH;
	private JTextField txtTenTour;
	private JTextField txtNgayKhoiHanh;
	private JTextField txtNgayKetThuc;
	private JTextField txtSLkhachNL;
	private JLabel lblSoLuongKhachNL;
	private JTextField txtSLKhachTE;
	private JTextField txtTongTien;
	private JPanel pnlSLKhachNL;

	private JTable tblPDK;

	private DefaultTableModel tblModel;

	private JPanel pnlHienThi_DSPDK;
	private JPanel pnlDSPhieuDK;
	private JButton btnThoat;

	private IDangKyTourBUS dangKyTourBUS;

	private IHuyDangKyTourBUS huyDangKyTourBUS;
	private IKhachHangThamGiaDAO khTGBUS;
	private PhieuChiBUS phieuChiBUS;
	private static List<PhieuDangKy> lstPhieuDK;
	private static List<KhachHangThamGia> lstKHTG;
	private static KhachHang khachHang;
	private JComboBox<String> cmbChonLoaiTK;
	private JButton btnHuyDK;
	private JTextField txtTimKiem;
	private JTable tblKhTG;

	/**
	 * Create the panel.
	 */
	public pnlHuyDangKyTour() {
		setSize(1500, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel pnlMain = new JPanel();
		add(pnlMain, BorderLayout.CENTER);
		pnlMain.setLayout(new BorderLayout(0, 0));

		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setPreferredSize(new Dimension(10, 70));
		pnlTieuDe.setForeground(new Color(0, 0, 0));
		pnlTieuDe.setBackground(new Color(0, 191, 255));
		pnlMain.add(pnlTieuDe, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("HỦY PHIẾU ĐĂNG KÝ");
		lblNewLabel.setIcon(new ImageIcon(pnlHuyDangKyTour.class.getResource("/images/huydangky_48.png")));
		lblNewLabel.setForeground(new Color(255, 255, 224));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));

		btnThoat = new JButton("");
		btnThoat.setIcon(new ImageIcon(pnlHuyDangKyTour.class.getResource("/images/turn_left.png")));
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThoat.setFocusable(false);
		btnThoat.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnThoat.setBackground(new Color(0, 191, 255));
		GroupLayout gl_pnlTieuDe = new GroupLayout(pnlTieuDe);
		gl_pnlTieuDe.setHorizontalGroup(gl_pnlTieuDe.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTieuDe.createSequentialGroup().addContainerGap()
						.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE).addGap(549)
						.addComponent(lblNewLabel).addContainerGap(585, Short.MAX_VALUE)));
		gl_pnlTieuDe.setVerticalGroup(gl_pnlTieuDe.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTieuDe.createSequentialGroup().addGap(10)
						.addGroup(gl_pnlTieuDe.createParallelGroup(Alignment.LEADING)
								.addComponent(btnThoat, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 50,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(10, Short.MAX_VALUE)));
		pnlTieuDe.setLayout(gl_pnlTieuDe);

		JPanel pnlHuyDK = new JPanel();
		pnlMain.add(pnlHuyDK, BorderLayout.CENTER);
		pnlHuyDK.setLayout(new BorderLayout(0, 0));

		JPanel pnlThongTinKH = new JPanel();
		pnlThongTinKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlThongTinKH.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Th\u00F4ng tin kh\u00E1ch h\u00E0ng",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlHuyDK.add(pnlThongTinKH, BorderLayout.WEST);
		pnlThongTinKH.setLayout(new BorderLayout(0, 0));

		JPanel pnlTimKiem_KH = new JPanel();
		pnlThongTinKH.add(pnlTimKiem_KH, BorderLayout.NORTH);
		pnlTimKiem_KH.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		txtTimKiemKH = new JTextField();
		txtTimKiemKH.setPreferredSize(new Dimension(6, 35));
		txtTimKiemKH.setHorizontalAlignment(SwingConstants.LEFT);
		txtTimKiemKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiemKH.setColumns(20);
		pnlTimKiem_KH.add(txtTimKiemKH);

		btnTimKiemKH = new JButton("");
		btnTimKiemKH.setFocusable(false);
		btnTimKiemKH.setIcon(new ImageIcon(pnlHuyDangKyTour.class.getResource("/images/search.png")));
		btnTimKiemKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTimKiem_KH.add(btnTimKiemKH);

		JPanel pnlHienThi_TTKH = new JPanel();
		pnlHienThi_TTKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlThongTinKH.add(pnlHienThi_TTKH, BorderLayout.CENTER);

		JLabel lblMaKH = new JLabel("Mã Khách hàng:");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblHoVaTenKH = new JLabel("Họ và tên:");
		lblHoVaTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblSoCMND = new JLabel("Số chứng minh nhân dân(căn cước):");
		lblSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtMaKH = new JTextField();
		txtMaKH.setPreferredSize(new Dimension(6, 40));
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(15);

		txtHovaTenKH = new JTextField();
		txtHovaTenKH.setPreferredSize(new Dimension(6, 40));
		txtHovaTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtHovaTenKH.setEditable(false);
		txtHovaTenKH.setColumns(15);

		txtSoCMND = new JTextField();
		txtSoCMND.setPreferredSize(new Dimension(6, 40));
		txtSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoCMND.setEditable(false);
		txtSoCMND.setColumns(15);

		txtNgaySinhKH = new JTextField();
		txtNgaySinhKH.setPreferredSize(new Dimension(6, 40));
		txtNgaySinhKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNgaySinhKH.setEditable(false);
		txtNgaySinhKH.setColumns(15);

		txtSDT = new JTextField();
		txtSDT.setPreferredSize(new Dimension(6, 40));
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSDT.setEditable(false);
		txtSDT.setColumns(15);

		txtDiaChiKH = new JTextField();
		txtDiaChiKH.setPreferredSize(new Dimension(6, 40));
		txtDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDiaChiKH.setEditable(false);
		txtDiaChiKH.setColumns(20);
		GroupLayout gl_pnlHienThi_TTKH = new GroupLayout(pnlHienThi_TTKH);
		gl_pnlHienThi_TTKH.setHorizontalGroup(gl_pnlHienThi_TTKH.createParallelGroup(Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_pnlHienThi_TTKH.createSequentialGroup().addContainerGap().addGroup(gl_pnlHienThi_TTKH
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtHovaTenKH, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
						.addComponent(txtDiaChiKH, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_pnlHienThi_TTKH.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblDiaChi, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaKH, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSDT, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblHoVaTenKH, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSoCMND, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSoCMND, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(txtNgaySinhKH, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_pnlHienThi_TTKH.setVerticalGroup(gl_pnlHienThi_TTKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlHienThi_TTKH.createSequentialGroup().addContainerGap()
						.addComponent(lblMaKH, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(25)
						.addComponent(lblHoVaTenKH, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE).addGap(18)
						.addComponent(txtHovaTenKH, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGap(32).addComponent(lblSoCMND, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE).addGap(18)
						.addComponent(txtSoCMND, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(31)
						.addComponent(lblNgaySinh, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE).addGap(18)
						.addComponent(txtNgaySinhKH, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGap(28).addComponent(lblSDT, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(txtSDT, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(20)
						.addComponent(lblDiaChi, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE).addGap(18)
						.addComponent(txtDiaChiKH, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGap(29)));
		pnlHienThi_TTKH.setLayout(gl_pnlHienThi_TTKH);

		JPanel pnlDS_PhieuDK = new JPanel();
		pnlDS_PhieuDK.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Danh s\u00E1ch phi\u1EBFu \u0111ang k\u00FD",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlHuyDK.add(pnlDS_PhieuDK, BorderLayout.CENTER);
		pnlDS_PhieuDK.setLayout(new BorderLayout(0, 0));

		pnlHienThi_DSPDK = new JPanel();
		pnlDS_PhieuDK.add(pnlHienThi_DSPDK, BorderLayout.CENTER);
		pnlHienThi_DSPDK.setLayout(new BorderLayout(0, 0));

		JPanel pnlTT_PhieuDK = new JPanel();
		pnlTT_PhieuDK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTT_PhieuDK.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3),
				"Th\u00F4ng tin phi\u1EBFu \u0111\u0103ng k\u00FD", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnlTT_PhieuDK.setPreferredSize(new Dimension(600, 10));
		pnlHuyDK.add(pnlTT_PhieuDK, BorderLayout.EAST);
		pnlTT_PhieuDK.setLayout(new BorderLayout(0, 0));

		JPanel pnlHienThi_TTPDK = new JPanel();
		pnlTT_PhieuDK.add(pnlHienThi_TTPDK, BorderLayout.NORTH);
		pnlHienThi_TTPDK.setLayout(new BoxLayout(pnlHienThi_TTPDK, BoxLayout.Y_AXIS));

		JPanel pnlMaPDK = new JPanel();
		pnlHienThi_TTPDK.add(pnlMaPDK);
		pnlMaPDK.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));

		JLabel lblMaPDK = new JLabel("Mã phiếu đăng ký :");
		pnlMaPDK.add(lblMaPDK);
		lblMaPDK.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtMaPDK = new JTextField();
		txtMaPDK.setPreferredSize(new Dimension(6, 40));
		txtMaPDK.setEditable(false);
		txtMaPDK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlMaPDK.add(txtMaPDK);
		txtMaPDK.setColumns(15);

		JPanel pnlNgayTaoPDK = new JPanel();
		FlowLayout fl_pnlNgayTaoPDK = (FlowLayout) pnlNgayTaoPDK.getLayout();
		fl_pnlNgayTaoPDK.setVgap(15);
		fl_pnlNgayTaoPDK.setAlignment(FlowLayout.LEFT);
		pnlHienThi_TTPDK.add(pnlNgayTaoPDK);

		JLabel lblNgayTaoPhieu = new JLabel("Ngày tạo phiếu:");
		lblNgayTaoPhieu.setPreferredSize(new Dimension(200, 25));
		pnlNgayTaoPDK.add(lblNgayTaoPhieu);
		lblNgayTaoPhieu.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtNgayTaoPDK = new JTextField();
		txtNgayTaoPDK.setPreferredSize(new Dimension(6, 40));
		txtNgayTaoPDK.setEditable(false);
		txtNgayTaoPDK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNgayTaoPDK.setColumns(15);
		pnlNgayTaoPDK.add(txtNgayTaoPDK);

		JPanel pnlPhimHuy = new JPanel();
		pnlTT_PhieuDK.add(pnlPhimHuy, BorderLayout.SOUTH);

		btnHuyDK = new JButton("Hủy phiếu đăng ký");
		btnHuyDK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlPhimHuy.add(btnHuyDK);

		JPanel pnlDSDK = new JPanel();
		pnlDSDK.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Danh s\u00E1ch phi\u1EBFu \u0111ang k\u00FD",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlHuyDK.add(pnlDSDK, BorderLayout.CENTER);
		pnlDSDK.setLayout(new BoxLayout(pnlDSDK, BoxLayout.Y_AXIS));

		JPanel pnlLocPhieuDK = new JPanel();
		pnlDSDK.add(pnlLocPhieuDK);
		pnlLocPhieuDK.setLayout(new BoxLayout(pnlLocPhieuDK, BoxLayout.Y_AXIS));

		JPanel pnlLoaiTK = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlLoaiTK.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlLocPhieuDK.add(pnlLoaiTK);

		cmbChonLoaiTK = new JComboBox<String>();
		cmbChonLoaiTK.setEnabled(false);
		pnlLoaiTK.add(cmbChonLoaiTK);
		cmbChonLoaiTK.setModel(new DefaultComboBoxModel<String>(
				new String[] { "-- Chọn loại tìm kiếm --", "Theo tên tour", "Theo mã phiếu đăng ký", "" }));
		cmbChonLoaiTK.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel pnlThanhTK = new JPanel();
		pnlLocPhieuDK.add(pnlThanhTK);

		txtTimKiem = new JTextField();
		txtTimKiem.setEditable(false);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiem.setColumns(20);
		GroupLayout gl_pnlThanhTK = new GroupLayout(pnlThanhTK);
		gl_pnlThanhTK.setHorizontalGroup(
				gl_pnlThanhTK.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlThanhTK.createSequentialGroup()
						.addGap(5).addComponent(txtTimKiem, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE).addGap(5)));
		gl_pnlThanhTK.setVerticalGroup(gl_pnlThanhTK.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				gl_pnlThanhTK.createSequentialGroup().addGap(5)
						.addComponent(txtTimKiem, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlThanhTK.setLayout(gl_pnlThanhTK);

		pnlDSPhieuDK = new JPanel();
		pnlDSPhieuDK.setPreferredSize(new Dimension(10, 400));
		pnlDSPhieuDK.setBorder(
				new TitledBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(2, 2, 2, 2)),
						"Danh s\u00E1ch phi\u1EBFu \u0111\u0103ng k\u00FD", TitledBorder.LEADING, TitledBorder.TOP,
						null, null));
		pnlDSDK.add(pnlDSPhieuDK);
		pnlDSPhieuDK.setLayout(new BorderLayout(0, 0));

		// Khởi tạo bảng phiếu đăng ký
		tblPDK = new JTable() {
			/**
			 * Không chỉnh sửa các ô trong bảng
			 */
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		TienIch.chinhKichThuocTable(tblPDK, tblPDK.getColumnModel().getTotalColumnWidth(), 1, 14, 85);
		tblPDK.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblPDK.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrDSPhieuDK = new JScrollPane(tblPDK);
		scrDSPhieuDK.setPreferredSize(new Dimension(500, 402));
		pnlDSPhieuDK.add(scrDSPhieuDK, BorderLayout.CENTER);

		JPanel pnlDSKHTG = new JPanel();
		pnlDSKHTG.setBorder(
				new TitledBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(2, 2, 2, 0)),
						"Danh s\u00E1ch kh\u00E1ch h\u00E0ng tham gia", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 0, 0)));
		pnlDSDK.add(pnlDSKHTG);
		pnlDSKHTG.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSKHTG = new JScrollPane();
		pnlDSKHTG.add(scrDSKHTG, BorderLayout.CENTER);

		tblKhTG = new JTable();
		scrDSKHTG.setViewportView(tblKhTG);

		JPanel pnlTenTour = new JPanel();
		pnlHienThi_TTPDK.add(pnlTenTour);
		pnlTenTour.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));

		JLabel lblTenTour = new JLabel("Tên tour :");
		lblTenTour.setPreferredSize(new Dimension(178, 25));
		lblTenTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTenTour.add(lblTenTour);

		txtTenTour = new JTextField();
		txtTenTour.setPreferredSize(new Dimension(6, 40));
		txtTenTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenTour.setEditable(false);
		txtTenTour.setColumns(22);
		pnlTenTour.add(txtTenTour);

		JPanel pnlNgayKhoiHanh = new JPanel();
		pnlHienThi_TTPDK.add(pnlNgayKhoiHanh);
		pnlNgayKhoiHanh.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));

		JLabel lblNgayKhoiHanh = new JLabel("Ngày khởi hành :");
		lblNgayKhoiHanh.setPreferredSize(new Dimension(178, 25));
		lblNgayKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlNgayKhoiHanh.add(lblNgayKhoiHanh);

		txtNgayKhoiHanh = new JTextField();
		txtNgayKhoiHanh.setPreferredSize(new Dimension(6, 40));
		txtNgayKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNgayKhoiHanh.setEditable(false);
		txtNgayKhoiHanh.setColumns(15);
		pnlNgayKhoiHanh.add(txtNgayKhoiHanh);

		JPanel pnlNgayKetThuc = new JPanel();
		pnlHienThi_TTPDK.add(pnlNgayKetThuc);
		pnlNgayKetThuc.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));

		JLabel lblNgayKetThuc = new JLabel("Ngày kết thúc :");
		lblNgayKetThuc.setPreferredSize(new Dimension(178, 25));
		lblNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlNgayKetThuc.add(lblNgayKetThuc);

		txtNgayKetThuc = new JTextField();
		txtNgayKetThuc.setPreferredSize(new Dimension(6, 40));
		txtNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNgayKetThuc.setEditable(false);
		txtNgayKetThuc.setColumns(15);
		pnlNgayKetThuc.add(txtNgayKetThuc);

		JPanel pnlSoKhachTG = new JPanel();
		FlowLayout fl_pnlSoKhachTG = (FlowLayout) pnlSoKhachTG.getLayout();
		fl_pnlSoKhachTG.setVgap(15);
		fl_pnlSoKhachTG.setAlignment(FlowLayout.LEFT);
		pnlHienThi_TTPDK.add(pnlSoKhachTG);

		JLabel lblSoKhachTG = new JLabel("Số khách tham gia :");
		pnlSoKhachTG.add(lblSoKhachTG);
		lblSoKhachTG.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSoKhachThamGia = new JTextField();
		txtSoKhachThamGia.setPreferredSize(new Dimension(6, 40));
		txtSoKhachThamGia.setEditable(false);
		txtSoKhachThamGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoKhachThamGia.setColumns(5);
		pnlSoKhachTG.add(txtSoKhachThamGia);

		lblMaPDK.setPreferredSize(lblSoKhachTG.getPreferredSize());
		lblNgayTaoPhieu.setPreferredSize(lblSoKhachTG.getPreferredSize());

		pnlSLKhachNL = new JPanel();
		pnlHienThi_TTPDK.add(pnlSLKhachNL);
		pnlSLKhachNL.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));

		lblSoLuongKhachNL = new JLabel("Người lớn :");
		lblSoLuongKhachNL.setPreferredSize(new Dimension(178, 25));
		lblSoLuongKhachNL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlSLKhachNL.add(lblSoLuongKhachNL);

		txtSLkhachNL = new JTextField();
		txtSLkhachNL.setPreferredSize(new Dimension(6, 40));
		txtSLkhachNL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSLkhachNL.setEditable(false);
		txtSLkhachNL.setColumns(5);
		pnlSLKhachNL.add(txtSLkhachNL);

		JPanel pnlSLKhachTE = new JPanel();
		pnlHienThi_TTPDK.add(pnlSLKhachTE);
		pnlSLKhachTE.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));

		JLabel lblSoLuongKhachTE = new JLabel("Trẻ em :");
		lblSoLuongKhachTE.setPreferredSize(new Dimension(178, 25));
		lblSoLuongKhachTE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlSLKhachTE.add(lblSoLuongKhachTE);

		txtSLKhachTE = new JTextField();
		txtSLKhachTE.setPreferredSize(new Dimension(6, 40));
		txtSLKhachTE.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSLKhachTE.setEditable(false);
		txtSLKhachTE.setColumns(5);
		pnlSLKhachTE.add(txtSLKhachTE);

		JPanel pnlTongTien = new JPanel();
		pnlHienThi_TTPDK.add(pnlTongTien);
		pnlTongTien.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));

		JLabel lblTongTien = new JLabel("Tổng tiền :");
		lblTongTien.setPreferredSize(new Dimension(178, 25));
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTongTien.add(lblTongTien);

		txtTongTien = new JTextField();
		txtTongTien.setPreferredSize(new Dimension(6, 40));
		txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongTien.setEditable(false);
		txtTongTien.setColumns(15);
		pnlTongTien.add(txtTongTien);

		txtTimKiemKH.setUI(new HintTextFieldUI("Số điện thoại khách hàng...", true));

		huyDangKyTourBUS = new HuyDangKyTourBUS();
		String[] tieuDe = { "Mã phiếu DK", "Ngày tạo phiếu", "Tên tour" };
		tblModel = new DefaultTableModel(tieuDe, 0);

		khTGBUS = new KhachHangThamGiaDAO();
		String[] tieuDeKHTG = { "Mã KHTG", "Họ và tên", "Ngày sinh" };
		tblKhTG.setModel(new DefaultTableModel(tieuDeKHTG, 0));
		TienIch.chinhKichThuocTable(tblKhTG, tblKhTG.getColumnModel().getTotalColumnWidth(), 1, 20, 10);

		phieuChiBUS = new PhieuChiBUS();
		dangKyTourBUS = new DangKyTourBUS();

		// chỉnh kích thước Title Border
		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlThongTinKH, pnlDSDK, pnlTT_PhieuDK }, "Tahoma",
				Font.PLAIN, 20);

		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlDS_PhieuDK, pnlDSKHTG }, "Tahoma", Font.PLAIN, 15);
		// Gán sự kiện
		ganSuKien();
	}

	private void ganSuKien() {
		btnThoat.addActionListener(this);
		btnTimKiemKH.addActionListener(this);
		btnHuyDK.addActionListener(this);
		cmbChonLoaiTK.addActionListener(this);

		tblPDK.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int row = tblPDK.getSelectedRow();
				if (row == -1)
					return;

				PhieuDangKy pdk = dangKyTourBUS.layTTPhieuDKTheoMa(tblPDK.getValueAt(row, 0).toString());
				lstKHTG = khTGBUS.layDSKhachHangTGTheoMaPDK(pdk.getMaPhieuDK().trim());

				hienThiTTDangKyTour(pdk);
				hienDSKhachHangTG(lstKHTG);

			}
		});

		/*
		 * Xử lý sự kiện khi nhập kí tự trên thanh tìm kiếm
		 */
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String content = TienIch
						.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTimKiem.getText().trim().toLowerCase());
				List<PhieuDangKy> lstLoc = new ArrayList<PhieuDangKy>();

				lstPhieuDK.forEach(x -> {
					switch (cmbChonLoaiTK.getSelectedIndex()) {
					case 1:
						String tenTour = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(x.getTour().getTenTour());
						if (tenTour.toLowerCase().contains(content)) {
							lstLoc.add(x);
						}
						break;
					case 2:
						if (x.getMaPhieuDK().toLowerCase().contains(content)) {
							lstLoc.add(x);
						}
						break;
					default:
						break;
					}
				});
				if (lstLoc.size() == 0) {
					hienThiDSPhieuDK(lstPhieuDK);
				} else {
					hienThiDSPhieuDK(lstLoc);
				}

			}
		});
	}

	/**
	 * Hiện danh sách khách hàng tham gia tour
	 * 
	 * @param maPhieuDK: mã phiếu đăng ký tour
	 */
	private void hienDSKhachHangTG(List<KhachHangThamGia> khtgs) {
		DefaultTableModel model = (DefaultTableModel) tblKhTG.getModel();
		model.setRowCount(0);
		for (KhachHangThamGia khtg : khtgs) {
			model.addRow(new Object[] { khtg.getMaKHTG(), khtg.getHoTenKHTG(), khtg.getNgaySinh() });
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			TienIch.xoaDuongDan(frmMain.getPnButtonBar(), 2);
			TienIch.chuyenPanelKhiNhan(frmMain.getPnContent(), new pnlQuanLyTour());
		} else if (o.equals(btnTimKiemKH)) {
			if (!txtTimKiemKH.getText().trim().equals("") && (txtTimKiemKH.getText().matches("(\\+84|0)[0-9]{9}"))) {
				khachHang = huyDangKyTourBUS.layTTKhachHang(2, txtTimKiemKH.getText());
				if (khachHang != null) {
					lstPhieuDK = huyDangKyTourBUS.layDSPhieuDKTheoMaKH(khachHang.getMaKH());
					hienThiTTKhachHang(khachHang);
					hienThiDSPhieuDK(lstPhieuDK);
					cmbChonLoaiTK.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this, "Số điện thoại không tồn tại", "Thông báo",
							JOptionPane.WARNING_MESSAGE);
					txtTimKiemKH.selectAll();
				}

			} else {
				JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu là 0 hoặc +84 tiếp sau là 9 ký số",
						"Thông báo lỗi", JOptionPane.ERROR_MESSAGE);
				txtTimKiemKH.requestFocus();
				txtTimKiemKH.selectAll();
			}

		} else if (o.equals(btnHuyDK)) {
			PhieuDangKy pdk = lstPhieuDK.get(tblPDK.getSelectedRow());
			int sel = JOptionPane.showConfirmDialog(this, "Xác nhận huỷ đăng ký tour "
					+ pdk.getTour().getMaTour().trim() + " - " + pdk.getTour().getTenTour() + " ?", "Huỷ đăng ký tour",
					JOptionPane.YES_NO_OPTION);
			if (sel == JOptionPane.YES_OPTION) {
				boolean huyDKTour = huyDangKyTourBUS.huyPhieuDangKy(pdk);
				if (huyDKTour) {
					lstPhieuDK = huyDangKyTourBUS.layDSPhieuDKTheoMaKH(khachHang.getMaKH());
					hienThiDSPhieuDK(lstPhieuDK);
					lstKHTG = khTGBUS.layDSKhachHangTGTheoMaPDK(pdk.getMaPhieuDK());
					hienDSKhachHangTG(lstKHTG);

					// Tạo phiếu chi trả tiền cho khách
					String mapc = TienIch.phatSinhMa(5);
					String maPC = "PC00" + mapc;
					PhieuThuChi phieuChi = phieuChiBUS.taoPhieuChi(new PhieuThuChi(maPC.trim(),
							new Date(System.currentTimeMillis()), pdk.tinhTongThanhTien(), "Khách hàng huỷ tour", pdk));
					if (phieuChi != null) {

						((DefaultTableModel) tblKhTG.getModel()).setRowCount(0);
					} else {
						JOptionPane.showMessageDialog(this, "Không tạo được Phiếu chi", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					LocalDate ngayDK = pdk.getTour().getNgayKhoiHanh().toLocalDate();
					if (ngayDK.plusDays(5).isEqual(LocalDate.now())) {
						JOptionPane.showMessageDialog(this, "Không thể huỷ tour do còn 5 ngày nữa là tour khởi hành",
								"Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		} else if (o.equals(cmbChonLoaiTK)) {
			switch (cmbChonLoaiTK.getSelectedIndex()) {
			case 1:
				txtTimKiem.setEditable(true);
				txtTimKiem.setUI(new HintTextFieldUI("Nhập tên tour cần tìm ...", true));
				break;
			case 2:
				txtTimKiem.setEditable(true);
				txtTimKiem.setUI(new HintTextFieldUI("Nhập mã phiếu đăng ký cần tìm ...", true));
				break;

			default:
				txtTimKiem.removeAll();
				txtTimKiem.setEditable(false);
				break;
			}
		}

	}

	/**
	 * Hiển thị thông tin khách hàng
	 * 
	 * @param kh: Khách hàng
	 */
	private void hienThiTTKhachHang(KhachHang kh) {
		txtMaKH.setText(kh.getMaKH());
		txtHovaTenKH.setText(kh.getHoVaTen());
		txtNgaySinhKH.setText(kh.getNgaySinh().toString());
		txtSoCMND.setText(kh.getSoCMND());
		txtDiaChiKH.setText(kh.getDiaChi().toString());
		txtSDT.setText(kh.getSoDienThoai());

	}

	/**
	 * Hàm hiển thị bảng danh sách phiêu đăng ký tour
	 * 
	 * @param lstPDk: danh sách phiếu đăng ký
	 */
	private void hienThiDSPhieuDK(List<PhieuDangKy> lstPDK) {
		tblModel.setRowCount(0);
		lstPDK.forEach(x -> {
			tblModel.addRow(new Object[] { x.getMaPhieuDK(), x.getNgayTaoPhieu(), x.getTour() });
		});
		tblPDK.setModel(tblModel);
	}

	/**
	 * Hiển thị thông tin phiếu đăng ký tour muốn huỷ
	 * 
	 * @param pdk: phiếu đăng ký muốn huỷ
	 */
	private void hienThiTTDangKyTour(PhieuDangKy pdk) {
		// Thông tin phiếu đăng ký
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		txtMaPDK.setText(pdk.getMaPhieuDK());
		txtNgayTaoPDK.setText(sdf.format(pdk.getNgayTaoPhieu()));
		txtSoKhachThamGia.setText(pdk.tinhSoNguoiThamGia() + "");
		txtSLkhachNL.setText(pdk.getSoNguoiLon() + "");
		txtSLKhachTE.setText(pdk.getSoTreEm() + "");

		// Thông tin tour đăng ký
		txtTenTour.setText(pdk.getTour().toString());

		SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");

		txtNgayKhoiHanh.setText(dfm.format(pdk.getTour().getNgayKhoiHanh()));
		txtNgayKetThuc.setText(dfm.format(pdk.getTour().getNgayKetThuc()));

		// Thông tin thanh toán

		txtTongTien.setText(pdk.tinhTongThanhTien() + "");

	}
}
