package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import com.toedter.components.JSpinField;

import bus.IKhachHangThamGiaBUS;
import bus.IQuanLyPhieuDKBUS;
import bus.impl.DangKyTourBUS;
import bus.impl.KhachHangThamGiaBUS;
import bus.impl.QuanLyPhieuDKBUS;
import entities.KhachHangThamGia;
import entities.PhieuDangKy;
import entities.Tour;
import utils.TienIch;

public class pnlQuanLyPhieuDangKy extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelPhieuDK;
	private DefaultTableModel modelDsKhachHang;
	private DefaultTableModel modelTour;
	private JTextField txtMaPhieuDK;
	private JTextField txtNgayTao;
	private JTextField txtSoKhachTG;
	private JTextField txtTrangThai;
	private JTextField txtMaTour;
	private JTextField txtTenTour;
	private JTextField txtTuNgay;
	private JTextField txtDenNgay;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtSdtNV;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSdtKh;
	private JTextField txtDiaChiKH;
	private JTextField txtCMND_KH;
	private JSpinField spnNguoiLon;
	private JSpinField spnTreEm;
	private IQuanLyPhieuDKBUS quanLyPhieuDangKyBUS;
	private IKhachHangThamGiaBUS khachHangThamGia;
	private DangKyTourBUS dangKyTourBUS;
	private List<PhieuDangKy> dsPDK;
	private List<Tour> dsTour;
	private List<KhachHangThamGia> dsKhDiCung;
	private JTable tblDsPDK;
	private JTextField txtTimKiemTour;
	private JTextField txtTimKiemPDK;
	private JTable tblDsTour;
	private JComboBox<String> cmbTimKiemTour;
	private JComboBox<String> cmbTimKiemPDK;
	private JTable tblDsKhachHangDiCung;

	/**
	 * Create the panel.
	 */
	public pnlQuanLyPhieuDangKy() {
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
		pnlTTTour.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch tour",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

		JScrollPane srcDsTour = new JScrollPane();
		pnlDsTour.add(srcDsTour, BorderLayout.CENTER);

		String[] headerTour = { "Mã tour", "Tên tour", "Số khách tham gia", "Số khách tối đa" };
		modelTour = new DefaultTableModel(headerTour, 0);
		tblDsTour = new JTable(modelTour) {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tblDsTour.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsTour.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		/*
		 * tblDsTour.getTableHeader().setFont(new Font("arial", Font.BOLD, 15));
		 * tblDsTour.setRowHeight(30);
		 */
		TienIch.chinhKichThuocTable(tblDsTour, tblDsTour.getColumnModel().getTotalColumnWidth(), 10, 5, 10, 15);

		srcDsTour.setViewportView(tblDsTour);

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

		String[] headerPDK = { "Mã phiếu", "Ngày tạo", "Khách hàng", "SĐT khách hàng" };
		modelPhieuDK = new DefaultTableModel(headerPDK, 0);
		tblDsPDK = new JTable(modelPhieuDK) {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tblDsPDK.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsPDK.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TienIch.chinhKichThuocTable(tblDsPDK, tblDsTour.getColumnModel().getTotalColumnWidth(), 5, 15, 15, 15);
		srcDsPDK.setViewportView(tblDsPDK);

		JPanel pnlThongTinCT_PDK = new JPanel();
		pnlQLPhieuDK.add(pnlThongTinCT_PDK, BorderLayout.CENTER);
		pnlThongTinCT_PDK.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabThongTinPDK_PT = new JTabbedPane(JTabbedPane.TOP);
		tabThongTinPDK_PT.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlThongTinCT_PDK.add(tabThongTinPDK_PT, BorderLayout.CENTER);

		JPanel pnlThongTinPhieuDK = new JPanel();
		tabThongTinPDK_PT.addTab("Thông tin phiếu đăng ký", null, pnlThongTinPhieuDK, null);
		pnlThongTinPhieuDK.setLayout(new GridLayout(4, 0, 0, 0));

		JPanel pnlPDK = new JPanel();
		pnlPDK.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Phi\u1EBFu \u0111\u0103ng k\u00FD",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinPhieuDK.add(pnlPDK);

		JLabel lblPhieuDK = new JLabel("Mã phiếu đăng ký:");
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

		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblSoKhachTG = new JLabel("Số khách tham gia:");
		lblSoKhachTG.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSoKhachTG = new JTextField();
		txtSoKhachTG.setHorizontalAlignment(SwingConstants.LEFT);
		txtSoKhachTG.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoKhachTG.setEditable(false);
		txtSoKhachTG.setColumns(10);

		JLabel lblNguoiLon = new JLabel("Người lớn:");
		lblNguoiLon.setFont(new Font("Tahoma", Font.PLAIN, 20));

		spnNguoiLon = new JSpinField();
		spnNguoiLon.getSpinner().setEnabled(false);

		JLabel lblTreEm = new JLabel("Trẻ em:");
		lblTreEm.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTrangThai = new JTextField();
		txtTrangThai.setHorizontalAlignment(SwingConstants.LEFT);
		txtTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTrangThai.setEditable(false);
		txtTrangThai.setColumns(10);

		spnTreEm = new JSpinField();
		spnTreEm.getSpinner().setEnabled(false);
		GroupLayout gl_pnlPDK = new GroupLayout(pnlPDK);
		gl_pnlPDK.setHorizontalGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlPDK
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlPDK.createSequentialGroup()
						.addComponent(lblPhieuDK, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(txtMaPhieuDK, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
						.addGap(12).addComponent(lblNgayTao, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addGap(5)
						.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlPDK.createSequentialGroup()
								.addComponent(lblSoKhachTG, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(txtSoKhachTG, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addGap(12).addComponent(lblNguoiLon).addGap(6)
								.addComponent(spnNguoiLon, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
				.addGap(12)
				.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlPDK.createSequentialGroup()
								.addComponent(lblTrangThai, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtTrangThai,
										GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlPDK.createSequentialGroup()
								.addComponent(lblTreEm, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(spnTreEm, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_pnlPDK.setVerticalGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlPDK
				.createSequentialGroup()
				.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlPDK.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlPDK.createSequentialGroup().addGap(3).addComponent(lblPhieuDK,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtMaPhieuDK, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlPDK.createSequentialGroup().addGap(3).addComponent(lblNgayTao,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTrangThai, GroupLayout.PREFERRED_SIZE, 35,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlPDK.createSequentialGroup().addGap(16).addComponent(lblTrangThai,
								GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
				.addGap(53)
				.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlPDK.createSequentialGroup().addGap(3).addComponent(lblSoKhachTG,
								GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtSoKhachTG, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNguoiLon, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(spnNguoiLon, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlPDK.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTreEm, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(spnTreEm, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlPDK.setLayout(gl_pnlPDK);

		JPanel pnlTour = new JPanel();
		pnlTour.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tour", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlThongTinPhieuDK.add(pnlTour);

		JLabel lblMaTour = new JLabel("Mã tour:");
		lblMaTour.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtMaTour = new JTextField();
		txtMaTour.setHorizontalAlignment(SwingConstants.LEFT);
		txtMaTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaTour.setEditable(false);
		txtMaTour.setColumns(10);

		JLabel lblTenTour = new JLabel("Tên tour:");
		lblTenTour.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTenTour = new JTextField();
		txtTenTour.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenTour.setEditable(false);
		txtTenTour.setColumns(10);

		JLabel lblTuNgay = new JLabel("Từ ngày:");
		lblTuNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTuNgay = new JTextField();
		txtTuNgay.setHorizontalAlignment(SwingConstants.LEFT);
		txtTuNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTuNgay.setEditable(false);
		txtTuNgay.setColumns(10);

		JLabel lblDenNgay = new JLabel("Đến ngày:");
		lblDenNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtDenNgay = new JTextField();
		txtDenNgay.setHorizontalAlignment(SwingConstants.LEFT);
		txtDenNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDenNgay.setEditable(false);
		txtDenNgay.setColumns(10);
		GroupLayout gl_pnlTour = new GroupLayout(pnlTour);
		gl_pnlTour.setHorizontalGroup(gl_pnlTour.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTour
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnlTour.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_pnlTour.createSequentialGroup()
								.addComponent(lblMaTour, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(txtMaTour, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTour.createSequentialGroup()
								.addComponent(lblTuNgay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtTuNgay, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_pnlTour.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblDenNgay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTenTour, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
				.addGap(11)
				.addGroup(gl_pnlTour.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtTenTour, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDenNgay, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_pnlTour.setVerticalGroup(gl_pnlTour.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTour.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlTour.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlTour.createSequentialGroup().addGap(4).addComponent(lblMaTour,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlTour.createSequentialGroup().addGap(1).addComponent(txtMaTour,
										GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlTour.createSequentialGroup().addGap(3).addComponent(lblTenTour,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtTenTour, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(38)
						.addGroup(gl_pnlTour.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtDenNgay, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDenNgay, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTuNgay, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTuNgay, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(23, Short.MAX_VALUE)));
		pnlTour.setLayout(gl_pnlTour);

		JPanel pnlNhanVien = new JPanel();
		pnlNhanVien.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nh\u00E2n vi\u00EAn",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinPhieuDK.add(pnlNhanVien);

		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtMaNV = new JTextField();
		txtMaNV.setHorizontalAlignment(SwingConstants.LEFT);
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaNV.setEditable(false);
		txtMaNV.setColumns(10);

		JLabel lblTenNV = new JLabel("Tên nhân viên:");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTenNV = new JTextField();
		txtTenNV.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenNV.setEditable(false);
		txtTenNV.setColumns(10);

		JLabel lblSdtNV = new JLabel("Số điện thoại:");
		lblSdtNV.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSdtNV = new JTextField();
		txtSdtNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSdtNV.setEditable(false);
		txtSdtNV.setColumns(10);
		GroupLayout gl_pnlNhanVien = new GroupLayout(pnlNhanVien);
		gl_pnlNhanVien.setHorizontalGroup(gl_pnlNhanVien.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlNhanVien
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnlNhanVien.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlNhanVien.createSequentialGroup()
								.addComponent(lblSdtNV, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(7)
								.addComponent(txtSdtNV, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlNhanVien.createSequentialGroup().addComponent(lblTenNV).addGap(43)
								.addComponent(txtTenNV, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlNhanVien.createSequentialGroup()
								.addComponent(lblMaNV, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(7)
								.addComponent(txtMaNV, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(420, Short.MAX_VALUE)));
		gl_pnlNhanVien.setVerticalGroup(gl_pnlNhanVien.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_pnlNhanVien.createSequentialGroup()
						.addGroup(gl_pnlNhanVien.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlNhanVien.createSequentialGroup().addGap(3).addComponent(lblMaNV,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtMaNV, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_pnlNhanVien.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTenNV, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTenNV, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_pnlNhanVien.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlNhanVien.createSequentialGroup().addGap(3).addComponent(lblSdtNV,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtSdtNV, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlNhanVien.setLayout(gl_pnlNhanVien);

		JPanel pnlKhachHang = new JPanel();
		pnlKhachHang.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Kh\u00E1ch H\u00E0ng",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongTinPhieuDK.add(pnlKhachHang);

		JLabel lblMaKH = new JLabel("Mã khách hàng:");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtMaKH = new JTextField();
		txtMaKH.setHorizontalAlignment(SwingConstants.LEFT);
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);

		JLabel lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTenKH = new JTextField();
		txtTenKH.setHorizontalAlignment(SwingConstants.LEFT);
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);

		JLabel lblSdtKH = new JLabel("Số điện thoại:");
		lblSdtKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSdtKh = new JTextField();
		txtSdtKh.setHorizontalAlignment(SwingConstants.LEFT);
		txtSdtKh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSdtKh.setEditable(false);
		txtSdtKh.setColumns(10);

		JLabel lblCMDN_KH = new JLabel("Số CMND:");
		lblCMDN_KH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblDiachiKH = new JLabel("Địa chỉ");
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
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlKhachHang.createSequentialGroup()
								.addComponent(lblMaKH, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addGap(7)
								.addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lblTenKH).addGap(18)
								.addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlKhachHang.createSequentialGroup()
								.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSdtKH, GroupLayout.PREFERRED_SIZE, 170,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDiachiKH, GroupLayout.PREFERRED_SIZE, 121,
												GroupLayout.PREFERRED_SIZE))
								.addGap(7)
								.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_pnlKhachHang.createSequentialGroup()
												.addComponent(txtSdtKh, GroupLayout.PREFERRED_SIZE, 144,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(lblCMDN_KH, GroupLayout.PREFERRED_SIZE, 141,
														GroupLayout.PREFERRED_SIZE)
												.addGap(26).addComponent(txtCMND_KH, GroupLayout.PREFERRED_SIZE, 276,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(txtDiaChiKH, GroupLayout.PREFERRED_SIZE, 605,
												GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(91, Short.MAX_VALUE)));
		gl_pnlKhachHang.setVerticalGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlKhachHang
				.createSequentialGroup()
				.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlKhachHang.createSequentialGroup().addGap(4)
								.addComponent(lblMaKH, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE).addGap(7))
						.addGroup(gl_pnlKhachHang.createSequentialGroup().addGap(1).addComponent(txtMaKH,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlKhachHang.createSequentialGroup().addGap(3)
								.addComponent(lblTenKH, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE).addGap(11)))
				.addGap(3)
				.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlKhachHang.createSequentialGroup().addGap(3)
								.addComponent(lblSdtKH, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE).addGap(9))
						.addComponent(txtSdtKh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlKhachHang.createSequentialGroup().addGap(2)
								.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCMDN_KH, GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCMND_KH, GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))))
				.addGap(10)
				.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDiachiKH, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(txtDiaChiKH, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
				.addGap(12)));
		pnlKhachHang.setLayout(gl_pnlKhachHang);

		JPanel pnlDsKhachHangDiCung = new JPanel();
		pnlDsKhachHangDiCung.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabThongTinPDK_PT.addTab("Danh sách khách hàng", null, pnlDsKhachHangDiCung, null);
		pnlDsKhachHangDiCung.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pnlDsKhachHangDiCung.add(scrollPane, BorderLayout.CENTER);

		String[] headerTblDSKH = { "STT", "Mã KH", "Tên khách hàng", "Ngày sinh" };
		modelDsKhachHang = new DefaultTableModel(headerTblDSKH, 0);
		tblDsKhachHangDiCung = new JTable(modelDsKhachHang) {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tblDsKhachHangDiCung.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDsKhachHangDiCung.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TienIch.chinhKichThuocTable(tblDsKhachHangDiCung, tblDsKhachHangDiCung.getColumnModel().getTotalColumnWidth(),
				5, 5, 15, 10, 15);
		scrollPane.setViewportView(tblDsKhachHangDiCung);
		
		JPanel panel = new JPanel();
		pnlThongTinCT_PDK.add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Hủy phiếu đăng ký");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnNewButton);
		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] {}, "Tahoma", Font.PLAIN, 18);

		// Khai bao cac danh sach
		quanLyPhieuDangKyBUS = new QuanLyPhieuDKBUS();
		dangKyTourBUS = new DangKyTourBUS();
		khachHangThamGia = new KhachHangThamGiaBUS();
		dsPDK = quanLyPhieuDangKyBUS.layDSPhieuDK();
		dsTour = dangKyTourBUS.layDSTourTonTaiPDK();
		hienBangThiThongTinTour(dsTour);
		ganSuKien();

	}

	public void ganSuKien() {
		// Bang danh sach tour
		tblDsTour.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = tblDsTour.getSelectedRow();
				hienthhiDSPhieuDangKyTheoTour(row);

			}
		});
		// Bang danh sach phieu dang ky
		tblDsPDK.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = tblDsPDK.getSelectedRow();
				if (row != -1) {
					String maPDK = (String) tblDsPDK.getValueAt(row, 0);
					HienThiTTPDK(maPDK);

					dsKhDiCung = khachHangThamGia.layDSKhachHangTGTheoMaPDK(maPDK);
					hienDSKhachHangTheoMaPDK(dsKhDiCung);

					btnSua.setEnabled(true);
				}

			}
		});

	}

////	Hien thi thong tin phieu dang ky tu chon bang
	public void HienThiTTPDK(String maPDK) {
		PhieuDangKy pdkTim = dangKyTourBUS.layTTPhieuDKTheoMa(maPDK);
		// Thông tin chi tiết tab phiếu đăng ký
		txtMaPhieuDK.setText(pdkTim.getMaPhieuDK().trim());
		txtNgayTao.setText(pdkTim.getNgayTaoPhieu() + "");

		txtSoKhachTG.setText(pdkTim.tinhSoNguoiThamGia() + "");
		spnNguoiLon.setValue(pdkTim.getSoNguoiLon());
		spnTreEm.setValue(pdkTim.getSoTreEm());
		txtMaTour.setText(pdkTim.getTour().getMaTour().trim() + "");
		txtTenTour.setText(pdkTim.getTour().getTenTour().trim() + "");
		txtTuNgay.setText(pdkTim.getTour().getNgayKhoiHanh() + "");
		txtDenNgay.setText(pdkTim.getTour().getNgayKetThuc() + "");
		txtMaNV.setText(pdkTim.getNv().getMaNV().trim());
		txtTenNV.setText(pdkTim.getNv().getHoVaTen().trim());
		txtSdtNV.setText(pdkTim.getNv().getSoDienThoai().trim());
		txtMaKH.setText(pdkTim.getKh().getMaKH().trim());
		txtTenKH.setText(pdkTim.getKh().getHoVaTen().trim());
		txtSdtKh.setText(pdkTim.getKh().getSoDienThoai().trim());
		txtCMND_KH.setText(pdkTim.getKh().getSoCMND().trim());
		txtDiaChiKH.setText(pdkTim.getKh().getDiaChi().getTenDC());

	}

	// Hien thi danh sách phiếu đăng ký ttheo ma tour
	public void hienthhiDSPhieuDangKyTheoTour(int row) {
		String maTour = (String) tblDsTour.getValueAt(row, 0);
		dsPDK = quanLyPhieuDangKyBUS.layDSPhieuDKTheoMaTour(maTour);
		hienBangThongTinPhieuDangKy(dsPDK);

	}

	// hien thi ban chua danh sach phieu dang ky
	private void hienBangThongTinPhieuDangKy(List<PhieuDangKy> dsPDK) {
		int i = 1;
		modelPhieuDK.setRowCount(0);
		for (PhieuDangKy x : dsPDK) {
			modelPhieuDK.addRow(new Object[] { x.getMaPhieuDK(), x.getNgayTaoPhieu(), x.getKh().getHoVaTen(),
					x.getKh().getSoDienThoai() });
			i++;
		}
	}

	// Hien thi danh sach khach hang theo ma phieu dang ky
	private void hienDSKhachHangTheoMaPDK(List<KhachHangThamGia> dsKHTG) {
		int i = 1;
		modelDsKhachHang.setRowCount(0);
		for (KhachHangThamGia khachHangThamGia : dsKHTG) {
			modelDsKhachHang.addRow(new Object[] { i, khachHangThamGia.getMaKHTG(), khachHangThamGia.getHoTenKHTG(),
					khachHangThamGia.getNgaySinh() });
			i++;
		}
	}

	// Hiển thị bảng thông tin tour
	private void hienBangThiThongTinTour(List<Tour> dsTour) {
		int j = 1;
		for (Tour tour : dsTour) {
			modelTour.addRow(new Object[] { tour.getMaTour(), tour.getTenTour(), tour.getSoNguoiDaDangKy(),
					tour.getSoLuongKhach() });
			j++;
		}
	}

	// Xóa trắng cac thong tin
	public void xoaTrangTT() {
		txtMaPhieuDK.setText("");
		txtNgayTao.setText("");
		txtTrangThai.setText("");
		txtSoKhachTG.setText("");
		spnNguoiLon.setValue(0);
		spnTreEm.setValue(0);
		txtMaTour.setText("");
		txtMaPhieuDK.setText("");
		txtTenTour.setText("");
		txtTuNgay.setText("");
		txtDenNgay.setText("");
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtSdtNV.setText("");
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtSdtKh.setText("");
		txtCMND_KH.setText("");
		txtDiaChiKH.setText("");

	}
	// Hien thi danh sach khach hang di cung

	// bat su kien
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnSua)) {
			spnNguoiLon.setEnabled(true);
			spnTreEm.setEnabled(true);
			btnSua.setEnabled(false);
			btnLuu.setEnabled(true);
			btnHuy.setEnabled(true);
			tblDsPDK.setEnabled(false);
			TienIch.hienAnCacControl(false, tblDsPDK, btnSua);
		} else if (o.equals(btnLuu)) {
			tblDsPDK.setEnabled(true);
			spnNguoiLon.setEnabled(false);
			spnTreEm.setEnabled(false);
			btnLuu.setEnabled(false);
			btnHuy.setEnabled(false);
			int r = tblDsPDK.getSelectedRow();
			String maPDKSua = (String) tblDsPDK.getValueAt(r, 0);
			PhieuDangKy pdkSua = new PhieuDangKy();
			pdkSua.setMaPhieuDK(maPDKSua);
			for (PhieuDangKy phieuDangKy : dsPDK) {
				if (dsPDK.contains(pdkSua)) {
					pdkSua = dsPDK.get(dsPDK.indexOf(pdkSua));
					pdkSua.setSoNguoiLon(spnNguoiLon.getValue());
					pdkSua.setSoTreEm(spnTreEm.getValue());
					txtSoKhachTG.setText(pdkSua.getSoNguoiLon() + pdkSua.getSoTreEm() + "");
				}
			}
			quanLyPhieuDangKyBUS.suaThongTinPhieuDK(pdkSua);
		} else if (o.equals(btnHuy)) {
			TienIch.hienAnCacControl(false, btnSua, btnLuu, btnHuy);
			spnNguoiLon.setEnabled(false);
			spnTreEm.setEnabled(false);
			tblDsPDK.setEnabled(true);
			xoaTrangTT();
			hienBangThongTinPhieuDangKy(dsPDK);
			txtSoKhachTG.requestFocus();
		}
	}
}
