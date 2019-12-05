package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import constant.HangSo;
import control.IKhachHangControl;
import control.IPhieuDangKyControl;
import control.IPhieuThuChiControl;
import control.impl.KhachHangControlImpl;
import control.impl.PhieuDangKyControlImpl;
import control.impl.PhieuThuChiControlImpl;
import entities.DoTuoi;
import entities.KhachHang;
import entities.KhachHangThamGia;
import entities.LoaiPhieu;
import entities.NhanVien;
import entities.PhieuDangKy;
import entities.PhieuThuChi;
import entities.Tour;
import gui.dialog.DlgPhieuChi;
import gui.dialog.DlgPhieuThu;
import model.DSKhachHangTGTableModel;
import model.DSKhachHangTableModel;
import model.DSPhieuDangKyModel;
import utils.HintTextFieldUI;
import utils.TienIch;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Component;

public class pnlQuanLyPDK extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private JComboBox<String> cmbTimKiemKH;
	private JComboBox<String> cmbTimKiemPDK;
	private JTable tblDSKhachHang;
	private JTable tblDSPhieuDangKy;
	private JTable tblDSKhachTG;
	private JScrollPane srcDsKhachHang;
	private JScrollPane srcDsKhachTG;
	private JButton btnBoChonKhachHang;
	private JButton btnBoChonPDK;
	private JButton btnLamMoiBangDSPDK;
	private JButton btnLamMoiBangDSKH;
	private JScrollPane srcDsPDK;
	private JLabel lblMaKH;
	private JLabel lblTenKH;
	private JLabel lblSoDienThoai;
	private JLabel lblSoCMND;
	private JLabel lblNgaySinh;
	private JLabel lblGioiTinh;
	private JLabel lblDiaChi;
	private JLabel lblTrangThai;
	private JLabel lblNgayTao;
	private JLabel lblMaPDK;
	private JLabel lblNgayKhoiHanh;
	private TextArea txaTenTuor;
	private JLabel lblThoiGian;
	private JButton btnHuyDangKyTour;
	private JButton btnLoc;

	private IPhieuDangKyControl phieuDangKyControl;
	private IKhachHangControl khachHangControl;

	private static List<KhachHang> dsKhachHang;
	private static List<PhieuDangKy> dsPDK;
	private static List<KhachHangThamGia> dsKhachHangTGTrongCSDL;

	private static PhieuDangKy pdkDuocChon;
	private JLabel lblSoKhachTG;
	private IPhieuThuChiControl phieuThuChiControl;
	private JLabel lblTongTiendb;
	private JPanel pnlKhachHangThamGia;
	private JPanel pnlTTKhachHang;
	private JPanel pnlTTPDK;
	private JPanel pnlKhachHangDK;;

	/**
	 * Create the panel.
	 */
	public pnlQuanLyPDK(NhanVien nv) {
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

		pnlKhachHangDK = new JPanel();
		pnlKhachHangDK.setBorder(
				new TitledBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(2, 2, 2, 2)),
						"Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 0, 0)));
		pnlPDKvaTour.add(pnlKhachHangDK);
		pnlKhachHangDK.setLayout(new BorderLayout(0, 0));

		JPanel pnlTimKiemTour = new JPanel();
		pnlKhachHangDK.add(pnlTimKiemTour, BorderLayout.NORTH);
		pnlTimKiemTour.setLayout(new BoxLayout(pnlTimKiemTour, BoxLayout.X_AXIS));

		JPanel pnlTimKiemKH = new JPanel();
		pnlTimKiemKH.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlTimKiemTour.add(pnlTimKiemKH);
		pnlTimKiemKH.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel pnlLoaiTK = new JPanel();
		pnlLoaiTK.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) pnlLoaiTK.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlTimKiemKH.add(pnlLoaiTK);

		cmbTimKiemKH = new JComboBox();
		pnlLoaiTK.add(cmbTimKiemKH);
		cmbTimKiemKH.setModel(new DefaultComboBoxModel(
				new String[] { "-- Loại tìm kiếm --", "Theo tên", "Theo số điện thoại", "Theo số CMND(căn cước)" }));
		cmbTimKiemKH.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JPanel pnlThanhTimKiem = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pnlThanhTimKiem.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		pnlTimKiemKH.add(pnlThanhTimKiem);
		
		JLabel lblTuKhoa = new JLabel("Từ khoá:");
		lblTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlThanhTimKiem.add(lblTuKhoa);

		txtTimKiem = new JTextField();
		txtTimKiem.setEditable(false);
		pnlThanhTimKiem.add(txtTimKiem);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiem.setColumns(20);

		JPanel pnlLamMoiDSKH = new JPanel();
		pnlTimKiemTour.add(pnlLamMoiDSKH);
		pnlLamMoiDSKH.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel pnlSpace = new JPanel();
		pnlLamMoiDSKH.add(pnlSpace);

		JPanel pnlLamMoiKH = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlLamMoiKH.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnlLamMoiDSKH.add(pnlLamMoiKH);

		btnLamMoiBangDSKH = new JButton("");
		pnlLamMoiKH.add(btnLamMoiBangDSKH);
		btnLamMoiBangDSKH.setIcon(new ImageIcon(pnlQuanLyPDK.class.getResource("/images/lammoi.png")));
		btnLamMoiBangDSKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel pnlDsTour = new JPanel();
		pnlKhachHangDK.add(pnlDsTour, BorderLayout.CENTER);
		pnlDsTour.setLayout(new BorderLayout(0, 0));

		srcDsKhachHang = new JScrollPane();
		srcDsKhachHang.setBorder(new LineBorder(Color.BLACK, 2));
		pnlDsTour.add(srcDsKhachHang, BorderLayout.CENTER);

		JPanel pnlPhieuDK = new JPanel();
		pnlPhieuDK.setBorder(
				new TitledBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(2, 2, 2, 2)),
						"Danh s\u00E1ch phi\u1EBFu \u0111\u0103ng k\u00FD", TitledBorder.LEADING, TitledBorder.TOP,
						null, new Color(0, 0, 0)));
		pnlPDKvaTour.add(pnlPhieuDK);
		pnlPhieuDK.setLayout(new BorderLayout(0, 0));

		JPanel pnlTimKiemPDK = new JPanel();
		pnlPhieuDK.add(pnlTimKiemPDK, BorderLayout.NORTH);
		pnlTimKiemPDK.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlLocDSPDK = new JPanel();
		FlowLayout fl_pnlLocDSPDK = (FlowLayout) pnlLocDSPDK.getLayout();
		fl_pnlLocDSPDK.setAlignment(FlowLayout.LEFT);
		pnlTimKiemPDK.add(pnlLocDSPDK);

		cmbTimKiemPDK = new JComboBox();
		cmbTimKiemPDK.setModel(new DefaultComboBoxModel(new String[] { "-- Chọn trạng thái --", "Đang chờ xử lý",
				"Chờ huỷ ", "Đã huỷ đăng ký", "Đã hoàn thành tour" }));
		pnlLocDSPDK.add(cmbTimKiemPDK);
		cmbTimKiemPDK.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnLoc = new JButton("");
		btnLoc.setIcon(new ImageIcon(pnlQuanLyPDK.class.getResource("/images/filter_25px.png")));
		pnlLocDSPDK.add(btnLoc);
		btnLoc.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JPanel pnlLamMoiDSPDK = new JPanel();
		FlowLayout fl_pnlLamMoiDSPDK = (FlowLayout) pnlLamMoiDSPDK.getLayout();
		fl_pnlLamMoiDSPDK.setAlignment(FlowLayout.RIGHT);
		pnlTimKiemPDK.add(pnlLamMoiDSPDK);

		btnLamMoiBangDSPDK = new JButton("");
		btnLamMoiBangDSPDK.setIcon(new ImageIcon(pnlQuanLyPDK.class.getResource("/images/lammoi.png")));
		pnlLamMoiDSPDK.add(btnLamMoiBangDSPDK);
		btnLamMoiBangDSPDK.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel pnlDsPDK = new JPanel();
		pnlPhieuDK.add(pnlDsPDK, BorderLayout.CENTER);
		pnlDsPDK.setLayout(new BorderLayout(0, 0));

		srcDsPDK = new JScrollPane();
		srcDsPDK.setBorder(new LineBorder(Color.BLACK));
		pnlDsPDK.add(srcDsPDK, BorderLayout.CENTER);

		JPanel pnlBoChonPDK = new JPanel();
		pnlBoChonPDK.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout fl_pnlBoChonPDK = (FlowLayout) pnlBoChonPDK.getLayout();
		fl_pnlBoChonPDK.setAlignment(FlowLayout.RIGHT);
		pnlPhieuDK.add(pnlBoChonPDK, BorderLayout.SOUTH);

		btnBoChonPDK = new JButton("Bỏ chọn");
		btnBoChonPDK.setIcon(new ImageIcon(pnlQuanLyPDK.class.getResource("/images/close_window_25px.png")));
		btnBoChonPDK.setFont(new Font("Tahoma", Font.PLAIN, 18));
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

		pnlTTKhachHang = new JPanel();
		panel_2.add(pnlTTKhachHang);
		pnlTTKhachHang.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Kh\u00E1ch H\u00E0ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTTKhachHang.setLayout(new GridLayout(3, 0, 0, 0));

		JPanel panel_6 = new JPanel();
		pnlTTKhachHang.add(panel_6);

		JLabel lblNewLabel = new JLabel("Mã khách hàng :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblTen = new JLabel("Họ và tên :");
		lblTen.setHorizontalAlignment(SwingConstants.LEFT);
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel label = new JLabel("");

		lblMaKH = new JLabel("");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblTenKH = new JLabel("");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_6
				.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
				.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup().addGap(727).addComponent(label))
						.addGroup(gl_panel_6.createSequentialGroup().addGap(11)
								.addComponent(lblMaKH, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblTen, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblTenKH, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_6.setVerticalGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_6
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING).addComponent(label)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMaKH).addComponent(lblTen)
								.addComponent(lblTenKH, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_6.setLayout(gl_panel_6);

		JPanel panel_7 = new JPanel();
		pnlTTKhachHang.add(panel_7);

		JLabel lblNewLabel_2 = new JLabel("Ngày sinh :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblNgaySinh = new JLabel("");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_7 = new JLabel("Số CMND :");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblSoCMND = new JLabel("");
		lblSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_4 = new JLabel("Giới tính :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblGioiTinh = new JLabel("");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_7
				.createSequentialGroup()
				.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_7.createSequentialGroup().addGap(170).addComponent(lblNgaySinh,
								GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_7.createSequentialGroup().addContainerGap().addComponent(lblNewLabel_2,
								GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
				.addGap(32).addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblSoCMND, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_4).addGap(10)
				.addComponent(lblGioiTinh, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(35, Short.MAX_VALUE)));
		gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_4)
								.addComponent(lblGioiTinh)
								.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_2)
										.addComponent(lblNgaySinh).addComponent(lblSoCMND)
										.addComponent(lblNewLabel_7)))));
		panel_7.setLayout(gl_panel_7);

		JPanel panel_5 = new JPanel();
		pnlTTKhachHang.add(panel_5);

		JLabel lblSinThoi = new JLabel("Số điện thoại :");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblSoDienThoai = new JLabel("");
		lblSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblDiaChi = new JLabel("");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel label_2 = new JLabel("Đia chỉ :");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_5
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSoDienThoai, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
				.addGap(170).addComponent(label_2, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(lblDiaChi, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		gl_panel_5.setVerticalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup().addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup().addContainerGap().addComponent(lblSoDienThoai))
						.addGroup(gl_panel_5.createSequentialGroup().addGap(14).addGroup(gl_panel_5
								.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDiaChi, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_5.linkSize(SwingConstants.VERTICAL, new Component[] { lblDiaChi, label_2 });
		panel_5.setLayout(gl_panel_5);

		pnlTTPDK = new JPanel();
		panel_2.add(pnlTTPDK);
		pnlTTPDK.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Phi\u1EBFu \u0111\u0103ng k\u00FD",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblPhieuDK = new JLabel("Mã phiếu đăng ký :");
		lblPhieuDK.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNgayTao_N = new JLabel("Ngày tạo:");
		lblNgayTao_N.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblTrangThai_N = new JLabel("Trạng thái :");
		lblTrangThai_N.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblTnTour = new JLabel("Tên tour :");
		lblTnTour.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNgyKhiHnh = new JLabel("Ngày khởi hành :");
		lblNgyKhiHnh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNgyKtThc = new JLabel("Thời gian :");
		lblNgyKtThc.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel panel_4 = new JPanel();

		lblMaPDK = new JLabel("");
		lblMaPDK.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblNgayTao = new JLabel("");
		lblNgayTao.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblTrangThai = new JLabel("");
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblNgayKhoiHanh = new JLabel("");
		lblNgayKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblThoiGian = new JLabel("");
		lblThoiGian.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_pnlTTPDK = new GroupLayout(pnlTTPDK);
		gl_pnlTTPDK.setHorizontalGroup(gl_pnlTTPDK.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTTPDK
				.createSequentialGroup().addContainerGap().addGroup(gl_pnlTTPDK.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPhieuDK, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTnTour, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNgyKhiHnh))
				.addGap(7)
				.addGroup(gl_pnlTTPDK.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTTPDK
						.createSequentialGroup()
						.addComponent(lblNgayKhoiHanh, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addGap(81).addComponent(lblNgyKtThc).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblThoiGian, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
						.addGroup(gl_pnlTTPDK.createSequentialGroup().addGap(3)
								.addComponent(lblMaPDK, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addGap(32)
								.addComponent(lblNgayTao_N, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNgayTao, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
								.addGap(14).addComponent(lblTrangThai_N).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblTrangThai, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
				.addContainerGap()));
		gl_pnlTTPDK.setVerticalGroup(gl_pnlTTPDK.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTTPDK
				.createSequentialGroup()
				.addGroup(gl_pnlTTPDK.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTTPDK
						.createSequentialGroup().addGap(3)
						.addGroup(gl_pnlTTPDK.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlTTPDK.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPhieuDK, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMaPDK))
								.addComponent(lblNgayTao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgayTao_N, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlTTPDK.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblTrangThai, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTrangThai_N, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25,
										Short.MAX_VALUE)))
				.addGap(7)
				.addGroup(gl_pnlTTPDK.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTPDK.createSequentialGroup().addGap(18).addComponent(lblTnTour))
						.addGroup(gl_pnlTTPDK.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_pnlTTPDK.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTTPDK
						.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
						.addGroup(gl_pnlTTPDK.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNgyKhiHnh, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgayKhoiHanh)
								.addComponent(lblNgyKtThc, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlTTPDK.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
								.addComponent(lblThoiGian, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
				.addGap(21)));
		panel_4.setLayout(new BorderLayout(0, 0));

		txaTenTuor = new TextArea();
		txaTenTuor.setEditable(false);
		txaTenTuor.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel_4.add(txaTenTuor, BorderLayout.CENTER);
		pnlTTPDK.setLayout(gl_pnlTTPDK);

		pnlKhachHangThamGia = new JPanel();
		pnlKhachHangThamGia.setBorder(new TitledBorder(null, "Danh s\u00E1ch kh\u00E1ch h\u00E0ng \u0111i c\u00F9ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinPhieuDK.add(pnlKhachHangThamGia);
		pnlKhachHangThamGia.setLayout(new BorderLayout(0, 0));

		JPanel pnlThongTinKHTG = new JPanel();
		pnlKhachHangThamGia.add(pnlThongTinKHTG, BorderLayout.NORTH);
		pnlThongTinKHTG.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblSoKhachHangThamGia = new JLabel("Số khách hàng tham gia:");
		lblSoKhachHangThamGia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlThongTinKHTG.add(lblSoKhachHangThamGia);

		lblSoKhachTG = new JLabel("");
		lblSoKhachTG.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlThongTinKHTG.add(lblSoKhachTG);

		srcDsKhachTG = new JScrollPane();
		pnlKhachHangThamGia.add(srcDsKhachTG, BorderLayout.CENTER);

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

		JPanel pnlHuyPhieuVaXacNhanPhieu = new JPanel();
		pnlThongTinCT_PDK.add(pnlHuyPhieuVaXacNhanPhieu, BorderLayout.SOUTH);
		pnlHuyPhieuVaXacNhanPhieu.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlHuyPhieuDK = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlHuyPhieuDK.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnlHuyPhieuVaXacNhanPhieu.add(pnlHuyPhieuDK);

		btnHuyDangKyTour = new JButton("Hủy phiếu đăng ký");
		btnHuyDangKyTour.setIcon(new ImageIcon(pnlQuanLyPDK.class.getResource("/images/cancel_subscription_32px.png")));
		btnHuyDangKyTour.setEnabled(false);
		pnlHuyPhieuDK.add(btnHuyDangKyTour);
		btnHuyDangKyTour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JPanel pnlBoChonKH = new JPanel();
		pnlBoChonKH.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout fl_pnlBoChonKH = (FlowLayout) pnlBoChonKH.getLayout();
		fl_pnlBoChonKH.setAlignment(FlowLayout.RIGHT);
		pnlKhachHangDK.add(pnlBoChonKH, BorderLayout.SOUTH);

		btnBoChonKhachHang = new JButton("Bỏ chọn");
		btnBoChonKhachHang.setIcon(new ImageIcon(pnlQuanLyPDK.class.getResource("/images/close_window_25px.png")));
		btnBoChonKhachHang.setFont(new Font("Arial", Font.PLAIN, 17));
		pnlBoChonKH.add(btnBoChonKhachHang);
		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] {}, "Tahoma", Font.PLAIN, 18);
		JPanel pnlTongTien = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnlTongTien.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pnlKhachHangThamGia.add(pnlTongTien, BorderLayout.SOUTH);

		JLabel lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTongTien.add(lblTongTien);

		lblTongTiendb = new JLabel("0.0");
		lblTongTiendb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTongTien.add(lblTongTiendb);
		TienIch.chinhKichThuocTitleTrenBorder(
				new JPanel[] { pnlKhachHangDK, pnlPhieuDK, pnlTTKhachHang, pnlTTPDK, pnlKhachHangThamGia }, "Arial",
				Font.PLAIN, 20);

		/*
		 * ===========================================================
		 */

		khachHangControl = new KhachHangControlImpl();
		phieuDangKyControl = new PhieuDangKyControlImpl();
		phieuThuChiControl = new PhieuThuChiControlImpl();

		tblDSKhachHang = new JTable();
		tblDSKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSPhieuDangKy = new JTable();
		tblDSPhieuDangKy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSKhachTG = new JTable();
		tblDSKhachTG.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dsKhachHang = khachHangControl.layDSKhachHang();
		dsPDK = phieuDangKyControl.layDSPhieuDangKy();
		dsKhachHangTGTrongCSDL = new ArrayList<KhachHangThamGia>();
		hienBangDSKhachHang(tblDSKhachHang, dsKhachHang, srcDsKhachHang);
		hienBangDSPDK(tblDSPhieuDangKy, dsPDK, srcDsPDK);
		hienBangDSKhachTG(tblDSKhachTG, dsKhachHangTGTrongCSDL, srcDsKhachTG);

		if (nv.getQuyen().getQuyen() == 2) {
			btnHuyDangKyTour.setVisible(false);
		}
		ganSuKien();
	}

	/**
	 * Hiên
	 * 
	 * @param tbl
	 * @param ds
	 * @param src
	 */
	private void hienBangDSKhachHang(JTable tbl, List<KhachHang> ds, JScrollPane src) {
		DSKhachHangTableModel dsKhachHangTableModel = new DSKhachHangTableModel(ds);
		tbl.setModel(dsKhachHangTableModel);
		src.setViewportView(tbl);

	}

	/**
	 * 
	 * @param tbl
	 * @param ds
	 * @param src
	 */
	private void hienBangDSPDK(JTable tbl, List<PhieuDangKy> ds, JScrollPane src) {
		DSPhieuDangKyModel dsPhieuDangKyModel = new DSPhieuDangKyModel(ds);
		tbl.setModel(dsPhieuDangKyModel);
		src.setViewportView(tbl);

	}

	/**
	 * 
	 * @param tbl
	 * @param ds
	 * @param src
	 */
	private void hienBangDSKhachTG(JTable tbl, List<KhachHangThamGia> ds, JScrollPane src) {
		DSKhachHangTGTableModel dsKhachHangTGTableModel = new DSKhachHangTGTableModel(ds);
		tbl.setModel(dsKhachHangTGTableModel);
		src.setViewportView(tbl);

	}

	/**
	 * Gán sự kiện cho các control
	 */
	private void ganSuKien() {
		btnBoChonKhachHang.addActionListener(this);
		btnBoChonPDK.addActionListener(this);
		btnLamMoiBangDSKH.addActionListener(this);
		btnLamMoiBangDSPDK.addActionListener(this);
		btnLoc.addActionListener(this);
		btnHuyDangKyTour.addActionListener(this);
		cmbTimKiemPDK.addActionListener(this);
		cmbTimKiemKH.addActionListener(this);

		tblDSKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = tblDSKhachHang.getSelectedRow();
				if (row == -1)
					return;
				String maKH = (String) tblDSKhachHang.getValueAt(row, 1);
				dsPDK = phieuDangKyControl.layDSPhieuDangKyTheoKH(maKH);
				hienBangDSPDK(tblDSPhieuDangKy, dsPDK, srcDsPDK);
			}
		});
		tblDSPhieuDangKy.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = tblDSPhieuDangKy.getSelectedRow();
				if (row == -1)
					return;
				String maPDK = (String) tblDSPhieuDangKy.getValueAt(row, 1);
				System.out.println(maPDK);
				pdkDuocChon = phieuDangKyControl.layTTPhieuDangKyTheoMa(maPDK);
				hienThongTinPDK(pdkDuocChon);
				String tinhTrang = (String) tblDSPhieuDangKy.getValueAt(row, 4);
				if (!tinhTrang.equalsIgnoreCase(HangSo.DAHOANTHANHTOUR)) {
					btnHuyDangKyTour.setEnabled(true);
				}

			}
		});
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTimKiem.getText().trim()).toLowerCase();
				List<KhachHang> dsKhachHangTim = new ArrayList<KhachHang>();
				for (KhachHang khachHang : dsKhachHang) {
					switch (cmbTimKiemKH.getSelectedIndex()) {
					case 1:
						String ten = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(khachHang.getHoVaTen().trim())
								.toLowerCase();
						if (ten.contains(key)) {
							dsKhachHangTim.add(khachHang);
						}
						break;
					case 2:
						String sdt = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(khachHang.getSoDienThoai().trim())
								.toLowerCase();
						if (sdt.contains(key)) {
							dsKhachHangTim.add(khachHang);
						}
						break;
					case 3:
						String cmnd = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(khachHang.getSoCMND().trim())
								.toLowerCase();
						if (cmnd.contains(key)) {
							dsKhachHangTim.add(khachHang);
						}
						break;

					default:
						dsKhachHangTim.add(khachHang);
					}
				}
				if (dsKhachHangTim.size() != 0) {
					dsKhachHang = dsKhachHangTim;
				}
				hienBangDSKhachHang(tblDSKhachHang, dsKhachHang, srcDsKhachHang);
			}
		});
	}

	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		/*
		 * Huỷ tour đăng ký
		 */
		if (o.equals(btnHuyDangKyTour)) {
			int confirm = JOptionPane.showConfirmDialog(null,
					"Xác nhận huỷ đăng ký tour " + pdkDuocChon.getNgayKhoiHanh().getTour() + " ?", "Thông báo xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				pdkDuocChon.setDaHuyPhieu(true);
				PhieuDangKy phieuDangKySua = phieuDangKyControl.suaPhieuDangKy(pdkDuocChon);
				if (phieuDangKySua != null) {
					taoPhieuChi(pdkDuocChon);
				}

			}
		} else if (o.equals(btnLamMoiBangDSKH)) {
			dsKhachHang = khachHangControl.layDSKhachHang();
			hienBangDSKhachHang(tblDSKhachHang, dsKhachHang, srcDsKhachHang);
		} else if (o.equals(btnLamMoiBangDSPDK)) {
			dsPDK = phieuDangKyControl.layDSPhieuDangKy();
			hienBangDSPDK(tblDSPhieuDangKy, dsPDK, srcDsPDK);
		}

		else if (o.equals(cmbTimKiemKH)) {
			switch (cmbTimKiemKH.getSelectedIndex()) {
			case 1:
				txtTimKiem.setEditable(true);
				txtTimKiem.setUI(new HintTextFieldUI("Nhập tên khách hàng", true));
				break;
			case 2:
				txtTimKiem.setEditable(true);
				txtTimKiem.setUI(new HintTextFieldUI("Nhập số điện thoại khách hàng", true));
				break;
			case 3:
				txtTimKiem.setEditable(true);
				txtTimKiem.setUI(new HintTextFieldUI("Nhập số CMND(thẻ căn cước) khách hàng", true));
				break;
			default:
				txtTimKiem.setEditable(false);
				break;
			}
		} else if (o.equals(btnLoc)) {
			List<PhieuDangKy> dsPhieuDKTim = new ArrayList<PhieuDangKy>();
			for (PhieuDangKy p : dsPDK) {
				switch (cmbTimKiemPDK.getSelectedIndex()) {
				case 1:
					if (!p.isDaHoanThanhTour() || !p.isDaHuyPhieu() || !p.getNgayKhoiHanh().isDaXoaDoKhongDuSoLuong()) {
						dsPhieuDKTim.add(p);
					}
					break;
				case 2:
					if (p.getNgayKhoiHanh().isDaXoaDoKhongDuSoLuong()) {
						dsPhieuDKTim.add(p);
					}
					break;
				case 3:
					if (p.isDaHuyPhieu()) {
						dsPhieuDKTim.add(p);
					}
					break;
				case 4:
					if (p.isDaHoanThanhTour()) {
						dsPhieuDKTim.add(p);
					}
					break;

				default:
					dsPhieuDKTim.add(p);
					break;
				}
			}
			if (dsPhieuDKTim.size() != 0) {
				dsPDK = dsPhieuDKTim;
			}
			hienBangDSPDK(tblDSPhieuDangKy, dsPDK, srcDsPDK);

		}
	}

	/**
	 * Hiện thông tin phiếu đăng ký tour
	 * 
	 * @param pdk
	 */
	private void hienThongTinPDK(PhieuDangKy pdk) {
		lblMaKH.setText(pdk.getKh().getMaKH());
		lblTenKH.setText(pdk.getKh().getHoVaTen());
		lblSoDienThoai.setText(pdk.getKh().getSoDienThoai());
		lblNgaySinh.setText(pdk.getKh().getNgaySinh().toString());
		lblSoCMND.setText(pdk.getKh().getSoCMND());
		lblDiaChi.setText(pdk.getKh().getDiaChi().toString());
		if (pdk.getKh().isGioiTinh())
			lblGioiTinh.setText("Nam");
		lblGioiTinh.setText("Nữ");
		lblMaPDK.setText(pdk.getMaPhieuDK());
		lblNgayTao.setText(pdk.getNgayTaoPhieu().toString());

		lblTrangThai.setText(HangSo.DANGXULY);
		if (pdk.getNgayKhoiHanh().isDaXoaDoKhongDuSoLuong())
			lblTrangThai.setText(HangSo.CHOHUYPHIEUDK);
		if (pdk.isDaHoanThanhTour())
			lblTrangThai.setText(HangSo.DAHOANTHANHTOUR);
		if (pdk.isDaHuyPhieu())
			lblTrangThai.setText(HangSo.DAHUYPHIEUDK);

		txaTenTuor.setText(pdk.getNgayKhoiHanh().getTour().getTenTour());
		lblNgayKhoiHanh.setText(pdk.getNgayKhoiHanh().getNgayKhoiHanh().toString());
		lblThoiGian.setText(pdk.getNgayKhoiHanh().getTour().getThoiGian()[0] + " Ngày "
				+ pdk.getNgayKhoiHanh().getTour().getThoiGian()[1] + " Đêm");

		lblSoKhachTG.setText(pdk.getKhachHangThamGias().size() + "");
		double tongTien = phieuThuChiControl.tinhTongTienPhieuThuTheoPDK(pdk.getMaPhieuDK());
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "vn"));
		lblTongTiendb.setText(format.format(tongTien));
		dsKhachHangTGTrongCSDL = pdk.getKhachHangThamGias();
		hienBangDSKhachTG(tblDSKhachTG, dsKhachHangTGTrongCSDL, srcDsKhachTG);

	}

	/**
	 * Tạo phiếu chi
	 * 
	 * @param pdk
	 */
	private void taoPhieuChi(PhieuDangKy pdk) {
		PhieuThuChi phieuThuChi = new PhieuThuChi();
		phieuThuChi.setPdk(pdkDuocChon);

		// nếu huỷ tour theo yêu cầu khách hàng
		int row = tblDSPhieuDangKy.getSelectedRow();
		String tinhTrangTour = tblDSPhieuDangKy.getValueAt(row, 4).toString();
		double tongTienChiTra = phieuThuChiControl.tinhTongTienPhieuThuTheoPDK(pdk.getMaPhieuDK());
		DlgPhieuChi dlgPhieuChi = null;
		if (tinhTrangTour.equalsIgnoreCase(HangSo.CHOHUYPHIEUDK)) {
			// chi trả 100% chi phí do
			dlgPhieuChi = new DlgPhieuChi(phieuThuChi, tongTienChiTra);
		} else {
			// chi trả 80% tiền thanh toán do tự ý huỷ tour
			tongTienChiTra = tongTienChiTra * HangSo.PHAT;
			dlgPhieuChi = new DlgPhieuChi(phieuThuChi, tongTienChiTra);
		}

		dlgPhieuChi.setVisible(true);
	}
}
