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
import javax.swing.JLabel;
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

import control.IKhachHangControl;
import control.IPhieuDangKyControl;
import control.impl.KhachHangControlImpl;
import control.impl.PhieuDangKyControlImpl;
import entities.KhachHang;
import entities.KhachHangThamGia;
import entities.PhieuDangKy;
import model.DSKhachHangTGTableModel;
import model.DSKhachHangTableModel;
import model.DSPhieuDangKyModel;
import utils.TienIch;
import javax.swing.JTextPane;
import java.awt.TextArea;

public class pnlQuanLyPDK extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtMaPhieuDK;
	private JTextField txtNgayTao;
	private JTextField txtTrangThai;
	private JTextField txtTimKiemTour;
	private JTextField txtTimKiemPDK;
	private JComboBox<String> cmbTimKiemTour;
	private JComboBox<String> cmbTimKiemPDK;
	private JTextField txtKhoiHanh;
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
		
		btnLamMoiBangDSKH = new JButton("Lam moi");
		btnLamMoiBangDSKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTimKiemTour.add(btnLamMoiBangDSKH);

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
		
		btnLamMoiBangDSPDK = new JButton("Lam moi");
		btnLamMoiBangDSPDK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTimKiemPDK.add(btnLamMoiBangDSPDK);

		JPanel pnlDsPDK = new JPanel();
		pnlTT_PDK.add(pnlDsPDK, BorderLayout.CENTER);
		pnlDsPDK.setLayout(new BorderLayout(0, 0));

		srcDsPDK = new JScrollPane();
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
		pnlKhachHang.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_6 = new JPanel();
		pnlKhachHang.add(panel_6);
		
		JLabel lblNewLabel = new JLabel("Mã khách hàng :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Họ và tên :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label = new JLabel("");
		
		lblMaKH = new JLabel("New label");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblTenKH = new JLabel("New label");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(727)
							.addComponent(label))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(11)
							.addComponent(lblMaKH, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTenKH, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMaKH)
							.addComponent(lblNewLabel_1)
							.addComponent(lblTenKH, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		
		JPanel panel_7 = new JPanel();
		pnlKhachHang.add(panel_7);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày sinh :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblNgaySinh = new JLabel("New label");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_7 = new JLabel("Số CMND :");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblSoCMND = new JLabel("0123456789");
		lblSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_4 = new JLabel("Giới tính :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblGioiTinh = new JLabel("Nam");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addGap(58)
					.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSoCMND, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4)
					.addGap(10)
					.addComponent(lblGioiTinh, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addComponent(lblGioiTinh)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(lblNgaySinh)
							.addComponent(lblSoCMND)
							.addComponent(lblNewLabel_7))))
		);
		panel_7.setLayout(gl_panel_7);
		
		JPanel panel_5 = new JPanel();
		pnlKhachHang.add(panel_5);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại :");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblSoDienThoai = new JLabel("New label");
		lblSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblDiaChi = new JLabel("New label");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label_2 = new JLabel("Đia chỉ :");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSinThoi)
					.addGap(26)
					.addComponent(lblSoDienThoai, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDiaChi, GroupLayout.PREFERRED_SIZE, 339, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDiaChi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSoDienThoai)))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);

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

		JLabel lblNgyKhiHnh = new JLabel("Ngày khởi hành :");
		lblNgyKhiHnh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtKhoiHanh = new JTextField();
		txtKhoiHanh.setText("15\\15\\2222");
		txtKhoiHanh.setHorizontalAlignment(SwingConstants.LEFT);
		txtKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtKhoiHanh.setEditable(false);
		txtKhoiHanh.setColumns(10);

		JLabel lblNgyKtThc = new JLabel("Thời gian :");
		lblNgyKtThc.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtNgayKetThuc = new JTextField();
		txtNgayKetThuc.setHorizontalAlignment(SwingConstants.LEFT);
		txtNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNgayKetThuc.setEditable(false);
		txtNgayKetThuc.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		GroupLayout gl_pnlPDK = new GroupLayout(pnlPDK);
		gl_pnlPDK.setHorizontalGroup(
			gl_pnlPDK.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPDK.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPhieuDK, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTnTour, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNgyKhiHnh))
					.addGap(7)
					.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
						.addGroup(gl_pnlPDK.createSequentialGroup()
							.addComponent(txtKhoiHanh, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
							.addGap(77)
							.addComponent(lblNgyKtThc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNgayKetThuc, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
						.addGroup(gl_pnlPDK.createSequentialGroup()
							.addComponent(txtMaPhieuDK, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(lblNgayTao, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(lblTrangThai, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTrangThai, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_pnlPDK.setVerticalGroup(
			gl_pnlPDK.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlPDK.createSequentialGroup()
					.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlPDK.createSequentialGroup()
							.addGap(16)
							.addComponent(lblTrangThai, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlPDK.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
								.addComponent(txtTrangThai, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlPDK.createSequentialGroup()
									.addGap(3)
									.addComponent(lblPhieuDK, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtMaPhieuDK, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlPDK.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNgayTao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlPDK.createSequentialGroup()
							.addGap(18)
							.addComponent(lblTnTour))
						.addGroup(gl_pnlPDK.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNgyKtThc, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtNgayKetThuc, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNgyKhiHnh, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtKhoiHanh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		TextArea textArea = new TextArea();
		panel_4.add(textArea, BorderLayout.CENTER);
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
		ganSuKien();
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
	private void ganSuKien() {
		tblDSKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = tblDSKhachHang.getSelectedRow();
					if (row == -1)
						return ;
				String maKH = (String) tblDSKhachHang.getValueAt(row, 1);
				dsPDK = phieuDangKyControl.layDSPhieuDangKyTheoKH(maKH);
				hienBangDSPDK(tblDSPhieuDangKy, dsPDK,srcDsPDK );
			}
		});
		tblDSPhieuDangKy.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int row = tblDSKhachHang.getSelectedRow();
				if (row == -1 )
					return;
				PhieuDangKy pdk = new PhieuDangKy();
				String maKH = (String) tblDSKhachHang.getValueAt(row, 1);
				dsPDK = phieuDangKyControl.layDSPhieuDangKyTheoKH(maKH);
				pdk = dsPDK.get(row);
				hienThongTinPDK(pdk);
			}
		});
	}
	private void hienThongTinPDK(PhieuDangKy pdk) {
		lblMaKH.setText(pdk.getKh().getMaKH());
		lblTenKH.setText(pdk.getKh().getHoVaTen());
		lblSoDienThoai.setText(pdk.getKh().getSoDienThoai());
		lblNgaySinh.setText(pdk.getKh().getNgaySinh().toString());
		lblSoCMND.setText(pdk.getKh().getSoCMND());
		lblDiaChi.setText(pdk.getKh().getDiaChi().toString());
		if(pdk.getKh().isGioiTinh())
			lblGioiTinh.setText("Nam");
		lblGioiTinh.setText("Nữ");
		
		
	}
}
