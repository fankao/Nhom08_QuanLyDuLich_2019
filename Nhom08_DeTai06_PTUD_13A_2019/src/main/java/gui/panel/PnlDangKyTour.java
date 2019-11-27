package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.hibernate.engine.transaction.jta.platform.internal.JOnASJtaPlatform;

import control.IKhachHangControl;
import control.IPhieuDangKyControl;
import control.ITourControl;
import control.impl.KhachHangControlImpl;
import control.impl.PhieuDangKyControlImpl;
import control.impl.TourControlImpl;
import entities.DiaChi;
import entities.KhachHang;
import entities.NgayKhoiHanh;
import entities.Tour;
import model.TourTableModel;
import utils.TienIch;
import utils.address.District;
import utils.address.Province;
import utils.address.Ward;

import java.awt.Dimension;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;

public class PnlDangKyTour extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable tblDSNgayDi;
	private JTextField txtHoTenKH;
	private JTextField txtSoCMND;
	private JTextField txtSdtKH;
	private JButton btnTimKiem;
	private JButton btnThemMoi;
	private JButton btnThemDC;
	private JPanel pnlTimKiemKhachHang;
	private JPanel pnlThongTinKH;
	private JTable tblDSKhachThamGia;
	private JButton btnDangKyTour;
	private JButton btnHuyChon;
	private JTable tblDSTour;
	private TourTableModel tourTableModel;

	private ITourControl tourControl;
	private IPhieuDangKyControl phieuDangKyControl;
	private IKhachHangControl khachHangControl;

	private static List<Tour> lstTour;
	private static List<NgayKhoiHanh> lstNgayKH;
	private JComboBox cmbTinh;
	private JComboBox cmbHuyen;
	private JComboBox cmbXa;
	private List<Province> lstProvices;
	private JButton btnLuuDC;
	private JLabel lblDiaChi;
	private JButton btnLuuTTKhachHang;
	private JButton btnHuy;
	private JDateChooser dtcNgaySinh;
	private JPanel pnlDSTour;

	@SuppressWarnings("unchecked")
	public PnlDangKyTour() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		pnlTimKiemKhachHang = new JPanel();
		pnlTimKiemKhachHang.setVisible(false);
		add(pnlTimKiemKhachHang);
		pnlTimKiemKhachHang.setLayout(new BorderLayout(0, 0));

		JPanel pnlTour = new JPanel();
		add(pnlTour);
		pnlTour.setLayout(new BoxLayout(pnlTour, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		pnlTour.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel pnlTimKiemVaThemMoi = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlTimKiemVaThemMoi.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(pnlTimKiemVaThemMoi, BorderLayout.NORTH);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlTimKiemVaThemMoi.add(btnTimKiem);

		btnThemMoi = new JButton("Thêm mới");
		btnThemMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlTimKiemVaThemMoi.add(btnThemMoi);

		pnlThongTinKH = new JPanel();
		pnlThongTinKH.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pnlThongTinKH, BorderLayout.CENTER);

		JLabel lblHoTenKH = new JLabel("Họ và tên khách hàng :");
		lblHoTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtHoTenKH = new JTextField();
		txtHoTenKH.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtHoTenKH.setColumns(10);

		JLabel lblDiaChiKH = new JLabel("Địa chỉ :");
		lblDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNgaySinh = new JLabel("Ngày sinh :");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblSoCmnd = new JLabel("Số CMND :");
		lblSoCmnd.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSoCMND = new JTextField();
		txtSoCMND.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtSoCMND.setColumns(10);

		JLabel lblSdt = new JLabel("Số điện thoại :");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSdtKH = new JTextField();
		txtSdtKH.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtSdtKH.setColumns(10);

		JPanel pnlDiaChi = new JPanel();
		FlowLayout fl_pnlDiaChi = (FlowLayout) pnlDiaChi.getLayout();
		fl_pnlDiaChi.setVgap(8);
		fl_pnlDiaChi.setAlignment(FlowLayout.LEFT);

		btnLuuTTKhachHang = new JButton("Lưu");
		btnLuuTTKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 18));

		dtcNgaySinh = new JDateChooser();
		dtcNgaySinh.setFont(new Font("Dialog", Font.PLAIN, 18));
		dtcNgaySinh.setLocale(new Locale("vi", "VN"));
		dtcNgaySinh.setDateFormatString("dd/MM/yyyy");
		GroupLayout gl_pnlThongTinKH = new GroupLayout(pnlThongTinKH);
		gl_pnlThongTinKH.setHorizontalGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlThongTinKH.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlThongTinKH.createSequentialGroup().addComponent(lblHoTenKH)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(txtHoTenKH,
												GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlThongTinKH.createSequentialGroup()
										.addComponent(lblDiaChiKH, GroupLayout.PREFERRED_SIZE, 84,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(pnlDiaChi,
												GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)))
						.addGap(34)
						.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlThongTinKH.createSequentialGroup().addComponent(lblSdt)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(txtSdtKH,
												GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlThongTinKH.createSequentialGroup()
										.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(dtcNgaySinh,
												GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
						.addGap(18).addComponent(lblSoCmnd, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
								.addComponent(txtSoCMND, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlThongTinKH.createSequentialGroup()
										.addComponent(btnLuuTTKhachHang, GroupLayout.PREFERRED_SIZE, 82,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnHuy,
												GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
						.addGap(129)));
		gl_pnlThongTinKH.setVerticalGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlThongTinKH.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlThongTinKH.createSequentialGroup()
										.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblHoTenKH)
														.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(txtHoTenKH, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addComponent(
														dtcNgaySinh, GroupLayout.PREFERRED_SIZE, 30,
														GroupLayout.PREFERRED_SIZE))
										.addGap(11)
										.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.BASELINE)
														.addComponent(txtSdtKH, GroupLayout.PREFERRED_SIZE, 31,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnLuuTTKhachHang).addComponent(btnHuy,
																GroupLayout.PREFERRED_SIZE, 27,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblSdt, GroupLayout.PREFERRED_SIZE, 25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblDiaChiKH, GroupLayout.PREFERRED_SIZE, 25,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(pnlDiaChi, GroupLayout.PREFERRED_SIZE, 38,
														GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblSoCmnd, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSoCMND, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(12, Short.MAX_VALUE)));

		lblDiaChi = new JLabel();
		lblDiaChi.setPreferredSize(new Dimension(4, 30));
		lblDiaChi.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlDiaChi.add(lblDiaChi);

		cmbTinh = new JComboBox();
		cmbTinh.setVisible(false);
		cmbTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(cmbTinh);

		cmbHuyen = new JComboBox();
		cmbHuyen.setVisible(false);
		cmbHuyen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(cmbHuyen);

		cmbXa = new JComboBox();
		cmbXa.setVisible(false);
		cmbXa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(cmbXa);

		btnThemDC = new JButton("Thêm");
		btnThemDC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(btnThemDC);

		btnLuuDC = new JButton("Lưu");
		btnLuuDC.setVisible(false);
		btnLuuDC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(btnLuuDC);
		pnlThongTinKH.setLayout(gl_pnlThongTinKH);

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(
				new TitledBorder(null, "Danh s\u00E1ch tour", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTour.add(pnlNorth);
		pnlNorth.setLayout(new BorderLayout(0, 0));

		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBorder(null);
		pnlNorth.add(pnlChucNang, BorderLayout.NORTH);
		pnlChucNang.setLayout(new BoxLayout(pnlChucNang, BoxLayout.X_AXIS));

		JPanel pnlLuaChon = new JPanel();
		pnlChucNang.add(pnlLuaChon);

		JPanel pnlTimKiem = new JPanel();
		pnlChucNang.add(pnlTimKiem);

		JPanel panel_3 = new JPanel();
		pnlChucNang.add(panel_3);

		pnlDSTour = new JPanel();
		pnlNorth.add(pnlDSTour, BorderLayout.CENTER);
		pnlDSTour.setLayout(new BorderLayout(0, 0));

		JPanel pnlHuyChonTour = new JPanel();
		FlowLayout fl_pnlHuyChonTour = (FlowLayout) pnlHuyChonTour.getLayout();
		fl_pnlHuyChonTour.setAlignment(FlowLayout.LEFT);
		pnlNorth.add(pnlHuyChonTour, BorderLayout.SOUTH);

		JButton btnHuyChonTour = new JButton("Huỷ chọn");
		btnHuyChonTour.setHorizontalAlignment(SwingConstants.LEFT);
		btnHuyChonTour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHuyChonTour.setFocusable(false);
		btnHuyChonTour.setEnabled(false);
		pnlHuyChonTour.add(btnHuyChonTour);

		JPanel pnlSouth = new JPanel();
		pnlTour.add(pnlSouth);
		pnlSouth.setLayout(new BorderLayout(0, 0));

		JPanel pnlCTTour = new JPanel();
		pnlSouth.add(pnlCTTour, BorderLayout.CENTER);
		pnlCTTour.setLayout(new BoxLayout(pnlCTTour, BoxLayout.X_AXIS));

		JPanel pnlLichTrinh = new JPanel();
		pnlLichTrinh.setBorder(null);
		pnlCTTour.add(pnlLichTrinh);
		pnlLichTrinh.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlDanhSachNgayDi = new JPanel();
		pnlDanhSachNgayDi.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Danh s\u00E1ch ng\u00E0y \u0111i", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLichTrinh.add(pnlDanhSachNgayDi);
		pnlDanhSachNgayDi.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSNgayDi = new JScrollPane();
		pnlDanhSachNgayDi.add(scrDSNgayDi, BorderLayout.CENTER);

		tblDSNgayDi = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblDSNgayDi.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M\u00E3 tour", "Ng\u00E0y kh\u1EDFi h\u00E0nh", "S\u1ED1 ch\u1ED7",
						"S\u1ED1 ng\u01B0\u1EDDi \u0111\u0103ng k\u00FD", "T\u00ECnh tr\u1EA1ng" }) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrDSNgayDi.setViewportView(tblDSNgayDi);

		JPanel pnlDsKhachHangThamGia = new JPanel();
		pnlDsKhachHangThamGia.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch kh\u00E1ch h\u00E0ng tham gia",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlLichTrinh.add(pnlDsKhachHangThamGia);
		pnlDsKhachHangThamGia.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSKhachThamGia = new JScrollPane();
		pnlDsKhachHangThamGia.add(scrDSKhachThamGia, BorderLayout.CENTER);

		tblDSKhachThamGia = new JTable();
		scrDSKhachThamGia.setViewportView(tblDSKhachThamGia);

		JPanel pnlThemTTKHTG = new JPanel();
		pnlDsKhachHangThamGia.add(pnlThemTTKHTG, BorderLayout.NORTH);

		JPanel pnlDieuKhienND = new JPanel();
		pnlSouth.add(pnlDieuKhienND, BorderLayout.SOUTH);
		pnlDieuKhienND.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlHuyChon = new JPanel();
		FlowLayout fl_pnlHuyChon = (FlowLayout) pnlHuyChon.getLayout();
		fl_pnlHuyChon.setAlignment(FlowLayout.LEFT);
		pnlDieuKhienND.add(pnlHuyChon);

		btnHuyChon = new JButton("Hủy chọn");
		btnHuyChon.setFont(new Font("Dialog", Font.PLAIN, 20));
		pnlHuyChon.add(btnHuyChon);

		JPanel pnlDangKyTour = new JPanel();
		FlowLayout fl_pnlDangKyTour = (FlowLayout) pnlDangKyTour.getLayout();
		fl_pnlDangKyTour.setAlignment(FlowLayout.RIGHT);
		pnlDieuKhienND.add(pnlDangKyTour);

		btnDangKyTour = new JButton("Đăng ký");
		btnDangKyTour.setFont(new Font("Dialog", Font.PLAIN, 20));
		pnlDangKyTour.add(btnDangKyTour);
		pnlThongTinKH.setVisible(false);

		/*
		 * Khởi tạo các control
		 */
		tourControl = new TourControlImpl();
		phieuDangKyControl = new PhieuDangKyControlImpl();
		khachHangControl = new KhachHangControlImpl();

		hienBangDSTourMoBan(new ArrayList<Tour>());

		ganSuKien();

	}

	/**
	 * Hiện danh sách tour mở bán
	 */
	private void hienBangDSTourMoBan(List<Tour> lstTour) {
		tourTableModel = new TourTableModel(lstTour);
		tblDSTour = new JTable(tourTableModel);
		tblDSTour.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		pnlDSTour.add(new JScrollPane(tblDSTour));
		tblDSTour.setFont(new Font("Arial", Font.PLAIN, 15));

		if (lstTour.size() != 0) {
			tblDSTour.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderrer());
			tblDSTour.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderrer());
			tblDSTour.getColumnModel().getColumn(2).setCellRenderer(new MyRenderer());
			tblDSTour.getColumnModel().getColumn(3).setCellRenderer(new MyRenderer());
			tblDSTour.getColumnModel().getColumn(4).setCellRenderer(new CenterRenderrer());
			tblDSTour.getColumnModel().getColumn(5).setCellRenderer(new CenterRenderrer());
			tblDSTour.getColumnModel().getColumn(6).setCellRenderer(new CenterRenderrer());
			tblDSTour.getColumnModel().getColumn(9).setCellRenderer(new CenterRenderrer());
			tblDSTour.getColumnModel().getColumn(10).setCellRenderer(new CenterRenderrer());
		}
		TienIch.chinhKichThuocTable(tblDSTour, tblDSTour.getColumnModel().getTotalColumnWidth(), 2, 10, 30, 30, 20, 20,
				20, 15, 15, 15, 15);

	}

	private void ganSuKien() {
		btnTimKiem.addActionListener(this);
		btnThemMoi.addActionListener(this);
		btnThemDC.addActionListener(this);
		btnLuuDC.addActionListener(this);
		cmbTinh.addActionListener(this);
		cmbHuyen.addActionListener(this);
		cmbXa.addActionListener(this);
		btnLuuTTKhachHang.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			JPanel panel = new PnlTimKiemKhachHang();
			pnlTimKiemKhachHang.add(panel, BorderLayout.CENTER);
			pnlTimKiemKhachHang.setVisible(true);
			pnlThongTinKH.setVisible(false);
		} else if (o.equals(btnThemMoi)) {
			pnlThongTinKH.setVisible(true);
			pnlTimKiemKhachHang.setVisible(false);
		} else if (o.equals(btnThemDC)) {
			btnThemDC.setVisible(false);
			// hiện danh sách tỉnh
			cmbTinh.setVisible(true);
			lstProvices = TienIch.layDiaLyHanhChinh();
			hienDiaDiem(lstProvices, cmbTinh, "Tỉnh/Thành phố");
		} else if (o.equals(cmbTinh)) {
			if (cmbTinh.getSelectedIndex() != 0) {
				List<District> districts = lstProvices.get(cmbTinh.getSelectedIndex() - 1).getDistricts();
				hienDiaDiem(districts, cmbHuyen, "Huyện/Quận");
				cmbHuyen.setVisible(true);
			}

		} else if (o.equals(cmbHuyen)) {
			if (cmbHuyen.getSelectedIndex() != 0) {
				List<Ward> wards = lstProvices.get(cmbTinh.getSelectedIndex() - 1).getDistricts()
						.get(cmbHuyen.getSelectedIndex() - 1).getWards();
				hienDiaDiem(wards, cmbXa, "Xã/Phường");
				cmbXa.setVisible(true);
			}
		} else if (o.equals(cmbXa)) {
			if (cmbXa.getSelectedIndex() != 0) {
				btnLuuDC.setVisible(true);
			}
		} else if (o.equals(btnLuuDC)) {
			Ward ward = ((Ward) cmbXa.getSelectedItem());
			District district = (District) cmbHuyen.getSelectedItem();
			Province province = (Province) cmbTinh.getSelectedItem();

			String xa = ward.getPrefix() + " " + ward.getName();
			String huyen = district.toString();
			String tinh = province.toString();

			lblDiaChi.setText(xa + ", " + huyen + ", " + tinh);
			lblDiaChi.setVisible(true);
			cmbHuyen.setVisible(false);
			cmbTinh.setVisible(false);
			cmbXa.setVisible(false);

		} else if (o.equals(btnLuuTTKhachHang)) {
			KhachHang khachHang = new KhachHang();
			khachHang.setHoVaTen(txtHoTenKH.getText());
			khachHang.setSoCMND(txtSoCMND.getText());
			khachHang.setNgaySinh(new Date(dtcNgaySinh.getDate().getTime()));
			khachHang.setSoDienThoai(txtSdtKH.getText());
			khachHang.setDiaChi(new DiaChi(cmbXa.getSelectedItem().toString(), cmbHuyen.getSelectedItem().toString(),
					cmbTinh.getSelectedItem().toString()));
			int confirm = JOptionPane.showConfirmDialog(null,
					"Lưu thông tin khách hàng " + khachHang.getHoVaTen() + " ?", "Xác nhận lưu khách hàng",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				KhachHang khThem = khachHangControl.themKhachHang(khachHang);
				if (khThem != null) {
					JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
					TienIch.hienAnCacControl(false, txtHoTenKH, txtSdtKH, txtSoCMND, dtcNgaySinh);
					hienBangDSTourMoBan(tourControl.layDsTourTheoYeuCau(3));
				}
			}

		}

	}

	/**
	 * Hiện danh sách địa chỉ: Tỉnh/Thành phố; Huyện/Quận/Thị xã; Xã/Phường/Thị trấn
	 * 
	 * @param lstdiaDiem: danh sách dịa chị
	 * @param cmbDiaDiem: combobox chứa tên tương ứng
	 */
	@SuppressWarnings("unchecked")
	private <T> void hienDiaDiem(List<T> lstdiaDiem, JComboBox<T> cmbDiaDiem, String tieuDe) {
		DefaultComboBoxModel<T> cmbModel = (DefaultComboBoxModel<T>) cmbDiaDiem.getModel();
		cmbModel.removeAllElements();
		Province province = new Province();
		province.setName(tieuDe);
		cmbModel.addElement((T) province);
		for (T x : lstdiaDiem) {
			cmbModel.addElement(x);
		}

	}
	/*
	 * =============================================
	 * 
	 * Inner class
	 * 
	 * ============================================
	 */

	@SuppressWarnings("unused")
	private class MyRenderer extends JTextArea implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public MyRenderer() {
			setFont(new Font("Arial", Font.PLAIN, 14));
			setLineWrap(true);
			setWrapStyleWord(true);
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			setText(value != null ? value.toString() : "");
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			int height = getPreferredSize().height + 20;
			setSize(table.getColumnModel().getColumn(column).getWidth(), height);
			if (table.getRowHeight(row) != height) {
				table.setRowHeight(row, height);
			}
			return this;
		}
	}

	@SuppressWarnings("unused")
	private class CenterRenderrer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public CenterRenderrer() {
			this.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	@SuppressWarnings("unused")
	private class MyDateRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public MyDateRenderer() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}

		@Override
		protected void setValue(Object value) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			value = sdf.format(((Date) value).getTime());
			super.setValue(value);
		}

	}
}
