package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.Format;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import control.ITourControl;
import control.impl.TourControlImpl;
import entities.DiaDanh;
import entities.NhanVien;
import entities.PhuongTien;
import entities.Tour;
import model.TourTableModel;
import utils.TienIch;
import utils.address.Province;

public class PnlTaoTour extends JPanel implements ActionListener, PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtTenTour;
	private JComboBox<DiaDanh> cmbDiaDanh;
	private JComboBox<Province> cmbDiemDen;
	private JComboBox<Province> cmbDiemXP;
	private JSpinner spnNgay;
	private JLabel lblTenTourct;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnHuy;
	private JButton btnLuu;
	private JPanel pnlCTTour;
	private JPanel pnlNgayKH;
	private JPanel pnlChucNang;
	private JButton btnSua;
	private JTextField txtTimKiem;
	private NhanVien nhanvien;
	private JPanel pnlButton;
	private JTable tblDSTour;
	private TourTableModel tourTableModel;
	/*
	 * Khai báo các Lớp control
	 */
	private ITourControl tourControl;
	private static List<Tour> lstTour;
	private JButton btnCTTour;
	private JFormattedTextField txtGiaNgLon;
	private JFormattedTextField txtGiaTrEm;
	private Format current = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

	/*
	 * Khai báo giá trị đơn giá
	 */
	private static double donGiaNguoiLon = 0, donGiaTreEm = 0;
	private JComboBox<String> cmbPhuongTien;
	private JTable tblDSNgayKH;
	private JButton btnDong;
	private JButton btnDongThemTour;

	/**
	 * Giao diện chức năng tạo tour
	 */
	@SuppressWarnings("unchecked")
	public PnlTaoTour(NhanVien nv) {
		this.nhanvien = nv;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		JPanel pnlTieuDe = new JPanel();
		add(pnlTieuDe, BorderLayout.NORTH);
		pnlTieuDe.setLayout(new BorderLayout(0, 0));

		JPanel pnlTaoTour = new JPanel();
		add(pnlTaoTour, BorderLayout.CENTER);
		pnlTaoTour.setLayout(new BorderLayout(0, 0));

		JPanel pnlTour = new JPanel();
		pnlTour.setBorder(
				new TitledBorder(null, "Th\u00F4ng tin tour", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTaoTour.add(pnlTour, BorderLayout.CENTER);
		pnlTour.setLayout(new BorderLayout(0, 0));

		pnlCTTour = new JPanel();
		pnlTour.add(pnlCTTour, BorderLayout.NORTH);
		pnlCTTour.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "C\u1EADp nh\u1EADt th\u00F4ng tin tour",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCTTour.setPreferredSize(new Dimension(10, 200));

		JLabel lblDiaDanh = new JLabel("Chọn địa danh:");
		lblDiaDanh.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbDiaDanh = new JComboBox<DiaDanh>();
		cmbDiaDanh.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblTenTour = new JLabel("Tên tour:");
		lblTenTour.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtTenTour = new JTextField();
		txtTenTour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTenTour.setColumns(10);

		JLabel lblDiemDen = new JLabel("Điểm đến:");
		lblDiemDen.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbDiemXP = new JComboBox<Province>();
		cmbDiemXP.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblDiemXP = new JLabel("Điểm xuất phát:");
		lblDiemXP.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbDiemDen = new JComboBox<Province>();
		cmbDiemDen.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblThoiGian = new JLabel("Thời gian:");
		lblThoiGian.setFont(new Font("Tahoma", Font.PLAIN, 18));

		spnNgay = new JSpinner();
		spnNgay.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblNgy = new JLabel("Ngày");
		lblNgy.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblm = new JLabel("Đêm");
		lblm.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblGiNgiLn = new JLabel("Giá người lớn:");
		lblGiNgiLn.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtGiaNgLon = new JFormattedTextField(current);
		txtGiaNgLon.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblGiaTrEm = new JLabel("Giá trẻ em:");
		lblGiaTrEm.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtGiaTrEm = new JFormattedTextField(current);
		txtGiaTrEm.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblCTTour = new JLabel("Chương trình tour:");
		lblCTTour.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnCTTour = new JButton("...");
		btnCTTour.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblPhuongTien = new JLabel("Phương tiện");
		lblPhuongTien.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbPhuongTien = new JComboBox();
		cmbPhuongTien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cmbPhuongTien.setModel(new DefaultComboBoxModel(PhuongTien.values()));

		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnDongThemTour = new JButton("");
		btnDongThemTour.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/delete_sign_32px.png")));
		btnDongThemTour.setFocusable(false);
		btnDongThemTour.setBorder(null);
		GroupLayout gl_pnlCTTour = new GroupLayout(pnlCTTour);
		gl_pnlCTTour.setHorizontalGroup(
			gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCTTour.createSequentialGroup()
					.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlCTTour.createSequentialGroup()
									.addComponent(lblCTTour)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCTTour)
									.addGap(171)
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDiemXP, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGiNgiLn))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
										.addComponent(txtGiaNgLon, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(cmbDiemDen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_pnlCTTour.createSequentialGroup()
									.addComponent(lblDiaDanh)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmbDiaDanh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addGap(12)
							.addComponent(lblTenTour)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtTenTour, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addGroup(gl_pnlCTTour.createSequentialGroup()
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDiemDen)
										.addComponent(lblGiaTrEm, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
									.addGap(12)
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
										.addComponent(txtGiaTrEm, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
										.addComponent(cmbDiemXP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
										.addComponent(lblThoiGian)
										.addComponent(lblPhuongTien))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pnlCTTour.createSequentialGroup()
											.addComponent(spnNgay, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNgy)
											.addGap(18)
											.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
										.addComponent(cmbPhuongTien, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblm)))
							.addContainerGap(19, Short.MAX_VALUE))
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDongThemTour, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_pnlCTTour.setVerticalGroup(
			gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCTTour.createSequentialGroup()
					.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDiaDanh)
								.addComponent(cmbDiaDanh, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnDongThemTour, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDiemXP)
								.addComponent(lblDiemDen)
								.addComponent(cmbDiemXP, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbDiemDen, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label))
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblTenTour)
									.addComponent(txtTenTour, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
									.addComponent(cmbPhuongTien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblPhuongTien)))
							.addGap(18)
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCTTour)
								.addComponent(btnCTTour)
								.addComponent(lblGiNgiLn)
								.addComponent(txtGiaNgLon, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGiaTrEm)
								.addComponent(txtGiaTrEm, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblThoiGian)
								.addComponent(spnNgay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblm)
								.addComponent(lblNgy))))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		pnlCTTour.setLayout(gl_pnlCTTour);
		pnlCTTour.setVisible(false);

		txtGiaNgLon.setValue(0);
		txtGiaTrEm.setValue(0);

		JPanel pnlDSCTTour = new JPanel();
		pnlDSCTTour.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Danh s\u00E1ch tour",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTour.add(pnlDSCTTour);
		pnlDSCTTour.setPreferredSize(new Dimension(10, 100));
		pnlDSCTTour.setLayout(new BorderLayout(0, 0));

		pnlChucNang = new JPanel();
		pnlDSCTTour.add(pnlChucNang, BorderLayout.NORTH);
		pnlChucNang.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlTimKiem = new JPanel();
		pnlChucNang.add(pnlTimKiem);
		pnlTimKiem.setLayout(new BoxLayout(pnlTimKiem, BoxLayout.X_AXIS));

		JPanel pnlLuaChonTK = new JPanel();
		FlowLayout fl_pnlLuaChonTK = (FlowLayout) pnlLuaChonTK.getLayout();
		fl_pnlLuaChonTK.setAlignment(FlowLayout.LEFT);
		pnlTimKiem.add(pnlLuaChonTK);

		JComboBox cmbLuaChon = new JComboBox<String>();
		pnlLuaChonTK.add(cmbLuaChon);
		cmbLuaChon.setModel(new DefaultComboBoxModel<String>(new String[] { "-- Lựa chọn tìm kiếm --", "Theo mã tour",
				"Theo tên tour", "Theo địa danh", "Theo phương tiện", "Theo điểm đến" }));
		cmbLuaChon.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JPanel pnlLoaiTK = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlLoaiTK.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlLoaiTK.setPreferredSize(new Dimension(400, 10));
		pnlTimKiem.add(pnlLoaiTK);

		txtTimKiem = new JTextField();
		pnlLoaiTK.add(txtTimKiem);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiem.setColumns(20);

		pnlButton = new JPanel();
		FlowLayout fl_pnlButton = (FlowLayout) pnlButton.getLayout();
		fl_pnlButton.setAlignment(FlowLayout.RIGHT);
		pnlChucNang.add(pnlButton);

		btnThem = new JButton("Thêm");
		pnlButton.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnSua = new JButton("Sửa");
		pnlButton.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnXoa = new JButton("Xoá");
		pnlButton.add(btnXoa);
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnHuy = new JButton("Huỷ bỏ");
		pnlButton.add(btnHuy);
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 18));

		pnlNgayKH = new JPanel();
		pnlNgayKH.setBorder(new TitledBorder(
				new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true),
						new EtchedBorder(EtchedBorder.LOWERED, null, null)),
				"Th\u00F4ng tin ng\u00E0y kh\u1EDFi h\u00E0nh", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnlNgayKH.setPreferredSize(new Dimension(10, 300));
		pnlTaoTour.add(pnlNgayKH, BorderLayout.SOUTH);
		pnlNgayKH.setLayout(new BorderLayout(0, 0));

		JPanel pnlThemNgayKH = new JPanel();
		pnlNgayKH.add(pnlThemNgayKH, BorderLayout.NORTH);
		pnlThemNgayKH.setLayout(new BoxLayout(pnlThemNgayKH, BoxLayout.X_AXIS));

		JPanel pnlNhapNgayDi = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlNhapNgayDi.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnlThemNgayKH.add(pnlNhapNgayDi);

		JLabel lblTenTours = new JLabel("Tên tour:");
		pnlNhapNgayDi.add(lblTenTours);
		lblTenTours.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTenTourct = new JLabel();
		pnlNhapNgayDi.add(lblTenTourct);
		lblTenTourct.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenTourct.setText("<Tên tour du lịch>");

		JLabel lblNgayKhoiHanh = new JLabel("Ngày khởi hành:");
		pnlNhapNgayDi.add(lblNgayKhoiHanh);
		lblNgayKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JDateChooser dtcNgayKhoiHanh = new JDateChooser();
		pnlNhapNgayDi.add(dtcNgayKhoiHanh);
		dtcNgayKhoiHanh.setFocusable(false);
		dtcNgayKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dtcNgayKhoiHanh.setPreferredSize(new Dimension(150, 30));
		dtcNgayKhoiHanh.setDateFormatString("dd/MM/yyyy");

		JLabel lblSoKhachToiDa = new JLabel("Số người tối đa:");
		pnlNhapNgayDi.add(lblSoKhachToiDa);
		lblSoKhachToiDa.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JSpinner spnSoKhachToiDa = new JSpinner();
		pnlNhapNgayDi.add(spnSoKhachToiDa);
		spnSoKhachToiDa.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JPanel pnlDong = new JPanel();
		FlowLayout fl_pnlDong = (FlowLayout) pnlDong.getLayout();
		fl_pnlDong.setAlignment(FlowLayout.RIGHT);
		pnlDong.setPreferredSize(new Dimension(350, 10));
		pnlThemNgayKH.add(pnlDong);

		btnDong = new JButton("");
		btnDong.setBorder(null);
		btnDong.setFocusable(false);
		btnDong.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/delete_sign_32px.png")));
		pnlDong.add(btnDong);

		JPanel pnlDSNgayKH = new JPanel();
		pnlNgayKH.add(pnlDSNgayKH, BorderLayout.CENTER);
		pnlDSNgayKH.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSNgayKH = new JScrollPane();
		pnlDSNgayKH.add(scrDSNgayKH, BorderLayout.CENTER);

		tblDSNgayKH = new JTable();
		tblDSNgayKH.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "STT",
				"M\u00E3 ng\u00E0y kh\u1EDFi h\u00E0nh", "Ng\u00E0y kh\u1EDFi h\u00E0nh", "S\u1ED1 ch\u1ED7" }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] columnTypes = new Class[] { Object.class, String.class, Object.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblDSNgayKH.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblDSNgayKH.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrDSNgayKH.setViewportView(tblDSNgayKH);

		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlNgayKH.setVisible(false);

		btnLuu.setPreferredSize(btnHuy.getPreferredSize());
		btnXoa.setPreferredSize(btnHuy.getPreferredSize());
		btnThem.setPreferredSize(btnHuy.getPreferredSize());

		JPanel pnlButtonNorth = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlButtonNorth.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		pnlDSCTTour.add(pnlButtonNorth, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Thêm ngày khởi hành");
		pnlButtonNorth.add(btnNewButton);

		tourControl = new TourControlImpl();

		/*
		 * Hiển thị danh sách dia danh
		 */
		List<DiaDanh> lstDiaDanh = tourControl.layDSDiaDanh();
		hienThiDSDiaDanh(lstDiaDanh);

		/*
		 * hiển thị danh sách các tour du lịch
		 */
		lstTour = tourControl.layDsTourTheoYeuCau(1);
		tourTableModel = new TourTableModel(lstTour);
		tblDSTour = new JTable(tourTableModel);
		tblDSTour.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		pnlDSCTTour.add(new JScrollPane(tblDSTour));

		/*
		 * Hiện địa điểm xuất phát, điểm đến
		 */
		List<Province> lstdiaDiem = TienIch.layDiaLyHanhChinh();
		hienDiaDiem(lstdiaDiem, cmbDiemXP);
		hienDiaDiem(lstdiaDiem, cmbDiemDen);

		/*
		 * gắn sự kiện cho các control
		 */
		ganSuKien();
	}

	/**
	 * Hiện địa điểm xuất phát và địa điểm đến cho tour
	 * 
	 * @param lstdiaDiem: danh sách địa điểm
	 * @param cmbDiaDiem: combobox chứa địa điểm
	 */
	private void hienDiaDiem(List<Province> lstdiaDiem, JComboBox<Province> cmbDiaDiem) {
		DefaultComboBoxModel<Province> cmbModel = (DefaultComboBoxModel<Province>) cmbDiaDiem.getModel();
		cmbModel.removeAllElements();
		lstdiaDiem.forEach(x -> {
			cmbModel.addElement(x);
		});

	}

	/**
	 * Hiện thị danh sách địa danh
	 * 
	 * @param lstDiaDanh: danh sách địa danh
	 */
	private void hienThiDSDiaDanh(List<DiaDanh> lstDiaDanh) {
		DefaultComboBoxModel<DiaDanh> cmbModel = (DefaultComboBoxModel<DiaDanh>) cmbDiaDanh.getModel();
		cmbModel.removeAllElements();
		for (DiaDanh diaDanh : lstDiaDanh) {
			cmbModel.addElement(diaDanh);
		}

	}

	/*
	 * Lấy thông tin tour được chọn
	 */
	Tour tourSel;

	private void ganSuKien() {
		btnLuu.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnHuy.addActionListener(this);

		txtGiaNgLon.addPropertyChangeListener(this);
		txtGiaTrEm.addPropertyChangeListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			pnlCTTour.setVisible(true);
			btnThem.setVisible(false);
			pnlButton.add(btnLuu, 0);
		} else if (o.equals(btnHuy)) {

		} else if (o.equals(btnLuu)) {
			Tour tour = new Tour();
			String index = TienIch.phatSinhMa(1);
			tour.setMaTour("T00" + index);
			tour.setTenTour(txtTenTour.getText());
			tour.setDiaDanh((DiaDanh) cmbDiaDanh.getSelectedItem());
			tour.setNhanVien(nhanvien);

			int soNgay = (int) spnNgay.getValue();
			int soDem = (int) spnNgay.getValue();
			tour.setThoiGian(new int[] { soNgay, soDem });

			Tour tourThem = tourControl.themTour(tour);
			if (tourThem != null) {
				TienIch.capNhatMaPhatSinh(1, index);
				lstTour.add(tourThem);
				tourTableModel.fireTableDataChanged();
			}

		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		Object o = e.getSource();
		if (o.equals(txtGiaNgLon)) {
			donGiaNguoiLon = ((Number) txtGiaNgLon.getValue()).doubleValue();
		} else if (o.equals(txtGiaTrEm)) {
			donGiaTreEm = ((Number) txtGiaTrEm.getValue()).doubleValue();
		}

	}

	@SuppressWarnings("unused")
	private class MyRenderer extends JTextArea implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public MyRenderer() {
			setFont(new Font("Tahoma", Font.PLAIN, 16));
			setLineWrap(true);
			setWrapStyleWord(true);
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			setText(value.toString());// or something in value, like value.getNote()..
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			int height = getPreferredSize().height + 30;
			setSize(table.getColumnModel().getColumn(column).getWidth(), height);
			if (table.getRowHeight(row) != height) {
				table.setRowHeight(row, height);
			}
			return this;
		}
	}

}
