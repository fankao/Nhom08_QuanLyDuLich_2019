package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
//github.com/fankao/Nhom08_QuanLyDuLich_2019.git
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
//github.com/fankao/Nhom08_QuanLyDuLich_2019.git
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDocument;
import org.jdesktop.swingx.autocomplete.AutoCompleteStyledDocument;

import com.toedter.calendar.JDateChooser;

import control.ITourControl;
import control.impl.TourControlImpl;
import entities.DiaDanh;
import entities.NgayKhoiHanh;
import entities.NhanVien;
import entities.PhuongTien;
import entities.Tour;
import model.NgayKhoiHanhTableModel;
import model.TourTableModel;
import utils.HintTextFieldUI;
import utils.TienIch;
import utils.address.Province;

public class PnlTaoTour extends JPanel implements ActionListener, PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	private JComboBox<DiaDanh> cmbDiaDanh;
	private JComboBox<Province> cmbDiemXP;
	private JComboBox<Province> cmbDiemDen;
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
	private JButton btnThemNgayKH;
	private JLabel lblNgayDem;
	private JTextPane txpTenTour;
	private JComboBox<String> cmbPhuongTien;
	private JButton btnDong;
	private JButton btnDongThemTour;
	private JPanel pnlNhap;
	private JButton btnThemKH;
	private JButton btnSuaNgayKH;
	private JButton btnLuuNgayKH;
	private JDateChooser dtcNgayKhoiHanh;
	private JSpinner spnSoKhachToiDa;

	private static boolean sua = false, themmoi = false;
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
	private static int soNgay = 0, soDem = 0;
	private JButton btnBoChon;
	private JComboBox<Province> cmbTimKiem;
	private JComboBox cmbLuaChon;
	private JPanel pnlDSNgayKH;

	private JTable tblDSNGKH;

	/**
	 * Giao diện chức năng tạo tour
	 */
	@SuppressWarnings("unchecked")
	public PnlTaoTour(NhanVien nv) {
		this.nhanvien = nv;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

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

		JLabel lblDiemDen = new JLabel("Điểm đến:");
		lblDiemDen.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbDiemDen = new JComboBox<Province>();
		cmbDiemDen.setEditable(true);
		cmbDiemDen.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblDiemXP = new JLabel("Điểm xuất phát:");
		lblDiemXP.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbDiemXP = new JComboBox<Province>();
		cmbDiemXP.setEditable(true);
		cmbDiemXP.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblThoiGian = new JLabel("Thời gian:");
		lblThoiGian.setFont(new Font("Tahoma", Font.PLAIN, 18));

		spnNgay = new JSpinner();
		spnNgay.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
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

		btnDongThemTour = new JButton("");
		btnDongThemTour.setBackground(SystemColor.window);
		btnDongThemTour.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/delete_sign_32px.png")));
		btnDongThemTour.setFocusable(false);
		btnDongThemTour.setBorder(null);

		lblNgayDem = new JLabel("0");
		lblNgayDem.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgayDem.setFont(new Font("Dialog", Font.PLAIN, 18));

		JPanel panel = new JPanel();
		GroupLayout gl_pnlCTTour = new GroupLayout(pnlCTTour);
		gl_pnlCTTour.setHorizontalGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCTTour
				.createSequentialGroup()
				.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlCTTour.createSequentialGroup().addContainerGap()
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_pnlCTTour.createSequentialGroup().addGap(78).addComponent(panel,
												GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(gl_pnlCTTour.createSequentialGroup().addComponent(lblDiaDanh)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(cmbDiaDanh,
														GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING).addComponent(lblCTTour)
										.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblGiNgiLn, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblDiemXP, GroupLayout.DEFAULT_SIZE, 137,
														Short.MAX_VALUE)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnCTTour)
										.addComponent(cmbDiemXP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtGiaNgLon)))
						.addGroup(gl_pnlCTTour.createSequentialGroup().addGap(12).addComponent(lblTenTour)))
				.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlCTTour
						.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.TRAILING).addComponent(label)
								.addGroup(gl_pnlCTTour.createSequentialGroup()
										.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
												.addComponent(lblDiemDen).addComponent(lblGiaTrEm,
														GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
										.addGap(12)
										.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
												.addComponent(txtGiaTrEm)
												.addComponent(cmbDiemDen, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
												.addComponent(lblThoiGian).addComponent(lblPhuongTien))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_pnlCTTour.createSequentialGroup()
														.addComponent(spnNgay, GroupLayout.PREFERRED_SIZE, 47,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblNgy)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(lblNgayDem, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addComponent(cmbPhuongTien, GroupLayout.PREFERRED_SIZE, 147,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblm).addGap(6)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_pnlCTTour.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnDongThemTour, GroupLayout.PREFERRED_SIZE, 33,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))));
		gl_pnlCTTour.setVerticalGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCTTour
				.createSequentialGroup()
				.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCTTour
						.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE).addComponent(lblDiaDanh)
								.addComponent(cmbDiaDanh, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnDongThemTour, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlCTTour.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_pnlCTTour.createSequentialGroup()
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pnlCTTour.createSequentialGroup()
												.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblDiemDen)
														.addComponent(cmbDiemDen, GroupLayout.PREFERRED_SIZE, 30,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(cmbDiemXP, GroupLayout.PREFERRED_SIZE, 30,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblDiemXP))
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(label))
										.addGroup(gl_pnlCTTour.createSequentialGroup().addGroup(gl_pnlCTTour
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
														.addComponent(cmbPhuongTien, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblPhuongTien))
												.addComponent(lblTenTour)).addGap(18).addGroup(
														gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
																.addComponent(txtGiaNgLon, GroupLayout.PREFERRED_SIZE,
																		30, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblGiaTrEm)
																.addComponent(txtGiaTrEm, GroupLayout.PREFERRED_SIZE,
																		30, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblThoiGian)
																.addComponent(spnNgay, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(lblNgy).addComponent(lblNgayDem)
																.addComponent(lblm).addComponent(lblGiNgiLn))))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE).addComponent(btnCTTour)
										.addComponent(lblCTTour))
								.addGap(12)))));
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrTenTour = new JScrollPane();
		panel.add(scrTenTour, BorderLayout.CENTER);

		txpTenTour = new JTextPane();
		txpTenTour.setFont(new Font("Dialog", Font.PLAIN, 18));
		scrTenTour.setViewportView(txpTenTour);
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
		pnlLuaChonTK.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout fl_pnlLuaChonTK = (FlowLayout) pnlLuaChonTK.getLayout();
		fl_pnlLuaChonTK.setAlignment(FlowLayout.LEFT);
		pnlTimKiem.add(pnlLuaChonTK);

		cmbLuaChon = new JComboBox<String>();
		pnlLuaChonTK.add(cmbLuaChon);
		cmbLuaChon.setModel(new DefaultComboBoxModel(new String[] { "-- Lựa chọn tìm kiếm --", "Theo mã tour",
				"Theo tên tour", "Theo địa danh", "Theo phương tiện", "Theo điểm đến" }));
		cmbLuaChon.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JPanel pnlLoaiTK = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlLoaiTK.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlLoaiTK.setPreferredSize(new Dimension(450, 10));
		pnlTimKiem.add(pnlLoaiTK);

		JLabel lblTimKiem = new JLabel("Tìm kiếm:");
		lblTimKiem.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlLoaiTK.add(lblTimKiem);

		txtTimKiem = new JTextField();
		pnlLoaiTK.add(txtTimKiem);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiem.setColumns(20);

		cmbTimKiem = new JComboBox();
		cmbTimKiem.setVisible(false);
		cmbTimKiem.setEditable(true);
		cmbTimKiem.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlLoaiTK.add(cmbTimKiem);

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

		btnBoChon = new JButton("Bỏ chọn");
		btnBoChon.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlButton.add(btnBoChon);
		btnBoChon.setVisible(false);
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
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnlThemNgayKH.add(pnlNhapNgayDi);

		panel_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_1.getLayout();
		flowLayout_3.setVgap(10);
		flowLayout_3.setHgap(10);
		pnlNhapNgayDi.add(panel_1);

		JLabel lblTenTours = new JLabel("Tên tour:");
		panel_1.add(lblTenTours);
		lblTenTours.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblTenTourct = new JLabel();
		panel_1.add(lblTenTourct);
		lblTenTourct.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenTourct.setText("<Tên tour du lịch>");

		pnlNhap = new JPanel();
		pnlNhapNgayDi.add(pnlNhap);

		JLabel lblNgayKhoiHanh = new JLabel("Ngày khởi hành:");
		pnlNhap.add(lblNgayKhoiHanh);
		lblNgayKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 18));

		dtcNgayKhoiHanh = new JDateChooser();

		pnlNhap.add(dtcNgayKhoiHanh);

		dtcNgayKhoiHanh.setFocusable(false);
		dtcNgayKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dtcNgayKhoiHanh.setPreferredSize(new Dimension(150, 30));
		dtcNgayKhoiHanh.setDateFormatString("dd/MM/yyyy");

		JLabel lblSoKhachToiDa = new JLabel("Số người tối đa:");
		pnlNhap.add(lblSoKhachToiDa);
		lblSoKhachToiDa.setFont(new Font("Tahoma", Font.PLAIN, 18));

		spnSoKhachToiDa = new JSpinner();

		spnSoKhachToiDa.setPreferredSize(new Dimension(40, 30));
		pnlNhap.add(spnSoKhachToiDa);

		spnSoKhachToiDa.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnThemKH = new JButton("Thêm");
		btnThemKH.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlNhapNgayDi.add(btnThemKH);

		btnSuaNgayKH = new JButton("Sửa");
		btnSuaNgayKH.setFont(new Font("Dialog", Font.PLAIN, 20));
		pnlNhapNgayDi.add(btnSuaNgayKH);

		btnLuuNgayKH = new JButton("Lưu");
		btnLuuNgayKH.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlNhapNgayDi.add(btnLuuNgayKH);

		JPanel pnlDong = new JPanel();
		FlowLayout fl_pnlDong = (FlowLayout) pnlDong.getLayout();
		fl_pnlDong.setVgap(10);
		fl_pnlDong.setAlignment(FlowLayout.RIGHT);
		pnlDong.setPreferredSize(new Dimension(350, 10));
		pnlThemNgayKH.add(pnlDong);

		btnDong = new JButton("");
		btnDong.setBackground(SystemColor.window);
		btnDong.setBorder(null);
		btnDong.setFocusable(false);
		btnDong.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/delete_sign_32px.png")));
		pnlDong.add(btnDong);

		pnlDSNgayKH = new JPanel();
		pnlNgayKH.add(pnlDSNgayKH, BorderLayout.CENTER);
		pnlDSNgayKH.setLayout(new BorderLayout(0, 0));

		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlNgayKH.setVisible(false);
		pnlButton.add(btnLuu, 1);
		btnLuu.setVisible(false);
		btnSua.setVisible(false);

		btnLuu.setPreferredSize(btnHuy.getPreferredSize());
		btnXoa.setPreferredSize(btnHuy.getPreferredSize());
		btnThem.setPreferredSize(btnHuy.getPreferredSize());

		JPanel pnlButtonNorth = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlButtonNorth.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		pnlDSCTTour.add(pnlButtonNorth, BorderLayout.SOUTH);

		btnThemNgayKH = new JButton("Thêm ngày khởi hành");
		btnThemNgayKH.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlButtonNorth.add(btnThemNgayKH);
		btnThemNgayKH.setVisible(false);

		tourControl = new TourControlImpl();

		/*
		 * Hiển thị danh sách dia danh
		 */
		List<DiaDanh> lstDiaDanh = tourControl.layDSDiaDanh();
		hienThiDSDiaDanh(lstDiaDanh);

		/*
		 * hiển thị danh sách các tour du lịch
		 */
		lstTour = tourControl.layDsTourTheoYeuCau(2, nv.getMaNV());
		tourTableModel = new TourTableModel(lstTour);
		tblDSTour = new JTable(tourTableModel);
		tblDSTour.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		pnlDSCTTour.add(new JScrollPane(tblDSTour));
		tblDSTour.setFont(new Font("Arial", Font.PLAIN, 15));

		TienIch.chinhKichThuocTable(tblDSTour, tblDSTour.getColumnModel().getTotalColumnWidth(), 2, 10, 30, 30, 20, 20,
				20, 15, 15, 15, 15);
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

		/*
		 * Hiện địa điểm xuất phát, điểm đến
		 */
		List<Province> lstdiaDiem = TienIch.layDiaLyHanhChinh();
		hienDiaDiem(lstdiaDiem, cmbDiemDen);
		hienDiaDiem(lstdiaDiem, cmbDiemXP);

		/*
		 * gắn sự kiện cho các control
		 */
		cmbDiaDanh.setEditable(true);
		ganSuKien();

		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlTour, pnlNgayKH }, "Arial", Font.PLAIN, 20);
		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlCTTour, pnlDSCTTour }, "Arial", Font.PLAIN, 18);
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

	private void ganSuKien() {
		btnLuu.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnHuy.addActionListener(this);
		btnThemNgayKH.addActionListener(this);
		btnDong.addActionListener(this);
		btnDongThemTour.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuuNgayKH.addActionListener(this);
		btnThemKH.addActionListener(this);
		btnSuaNgayKH.addActionListener(this);
		btnBoChon.addActionListener(this);

		cmbLuaChon.addActionListener(this);

		txtGiaNgLon.addPropertyChangeListener(this);
		txtGiaTrEm.addPropertyChangeListener(this);

		spnNgay.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				soNgay = (int) spnNgay.getValue();
				soDem = soNgay - 1;
				lblNgayDem.setText(soDem + "");

			}
		});

		tblDSTour.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnThem.setVisible(false);
				btnBoChon.setVisible(true);
				int row = tblDSTour.getSelectedRow();
				if (row == -1)
					return;

				btnSua.setVisible(true);
				btnThemNgayKH.setVisible(true);
				if (sua == true) {
					Tour tour = tourControl.layTourTheoMa(tblDSTour.getValueAt(row, 1).toString());
					hienThongTinTour(tour);
				}

			}

		});

		AutoCompleteDecorator.decorate(cmbDiaDanh);
		AutoCompleteDecorator.decorate(cmbDiemDen);
		AutoCompleteDecorator.decorate(cmbDiemXP);

	}

	private void hienThongTinTour(Tour tourSel) {

		txpTenTour.setText(tourSel.getTenTour());
		cmbDiaDanh.setSelectedItem(tourSel.getDiaDanh());
		cmbDiemXP.setSelectedItem(tourSel.getDiemDen());
		cmbDiemDen.setSelectedItem(tourSel.getDiemKhoiHanh());
		txtGiaNgLon.setValue(tourSel.getDonGiaNguoiLon());
		txtGiaTrEm.setValue(tourSel.getDonGiaTreEm());
		spnNgay.setValue(tourSel.getThoiGian()[0]);
		lblNgayDem.setText(tourSel.getThoiGian()[1] + "");

	}

	private static List<NgayKhoiHanh> lstNgayKH;
	private JPanel panel_1;

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			tblDSTour.clearSelection();
			pnlCTTour.setVisible(true);
			btnThem.setVisible(false);
			btnLuu.setVisible(true);
			themmoi = true;
		} else if (o.equals(btnHuy)) {

		} else if (o.equals(btnLuu)) {
			Tour tour = new Tour();
			tour.setTenTour(txpTenTour.getText());
			tour.setDiaDanh((DiaDanh) cmbDiaDanh.getSelectedItem());
			tour.setDiemKhoiHanh(cmbDiemDen.getSelectedItem().toString());
			tour.setDiemDen(cmbDiemXP.getSelectedItem().toString());
			tour.setNhanVien(nhanvien);
			tour.setPhuongTien((PhuongTien) cmbPhuongTien.getSelectedItem());
			tour.setThoiGian(new int[] { soNgay, soDem });

			tour.setDonGiaNguoiLon(donGiaNguoiLon);
			tour.setDonGiaTreEm(donGiaTreEm);
			if (themmoi == true) {
				Tour tourThem = tourControl.themTour(tour);

				if (tourThem != null) {
					lstTour.add(tourThem);
					tourTableModel.fireTableDataChanged();
					themmoi = false;
					TienIch.xoaTrangCacJTextField(txtGiaNgLon, txtGiaTrEm, txpTenTour);
					cmbDiaDanh.setSelectedIndex(0);
					cmbDiemXP.setSelectedIndex(0);
					cmbDiemDen.setSelectedIndex(0);
					tblDSTour.getSelectionModel().setSelectionInterval(lstTour.size() - 1, lstTour.size() - 1);
				}

			}

			if (sua == true) {
				int row = tblDSTour.getSelectedRow();
				Tour tourChon = tourControl.layTourTheoMa(tblDSTour.getValueAt(row, 1).toString());
				tour.setMaTour(tourChon.getMaTour());
				Tour touSua = tourControl.suaTour(tour);
				if (touSua != null) {
					lstTour.remove(row);
					lstTour.add(row, touSua);
					tourTableModel.fireTableDataChanged();
					sua = false;
				}
			}

		} else if (o.equals(btnThemNgayKH)) {
			pnlNgayKH.setVisible(true);
			String maTour = tblDSTour.getValueAt(tblDSTour.getSelectedRow(), 1).toString();
			Tour tourChon = tourControl.layTourTheoMa(maTour);
			lblTenTourct.setText(tourChon.getTenTour());
			pnlNhap.setVisible(false);

			lstNgayKH = tourControl.layDSNgayKhoiHanhTheoTour(tourChon.getMaTour());
			NgayKhoiHanhTableModel ngkhTableModel = new NgayKhoiHanhTableModel(lstNgayKH);
			tblDSNGKH = new JTable(ngkhTableModel);
			pnlDSNgayKH.add(new JScrollPane(tblDSNGKH));
			TienIch.chinhKichThuocTable(tblDSNGKH, tblDSNGKH.getColumnModel().getTotalColumnWidth(), 5, 50, 20, 20);

		} else if (o.equals(btnThemKH)) {
			pnlNhap.setVisible(true);
		} else if (o.equals(btnDong)) {
			pnlNgayKH.setVisible(false);
			pnlNhap.setVisible(false);
			themmoi = false;
			sua = false;
			tblDSTour.clearSelection();
		} else if (o.equals(btnDongThemTour)) {
			pnlCTTour.setVisible(false);
			btnThem.setVisible(true);
			btnLuu.setVisible(false);
		} else if (o.equals(btnSua)) {
			pnlCTTour.setVisible(true);
			btnThem.setVisible(false);
			btnSua.setVisible(false);
			btnLuu.setVisible(true);
			sua = true;
		} else if (o.equals(btnLuuNgayKH)) {
			int row = tblDSTour.getSelectedRow();
			NgayKhoiHanh ngayKhoiHanh = new NgayKhoiHanh();
			ngayKhoiHanh.setNgayKhoiHanh(new Date(dtcNgayKhoiHanh.getDate().getTime()));
			int soNguoi = (int) spnSoKhachToiDa.getValue();
			ngayKhoiHanh.setSoNguoiThamGia(soNguoi);
			Tour tourChon = tourControl.layTourTheoMa(tblDSTour.getValueAt(row, 1).toString());

			String maNgKH = tourChon.getMaTour() + "-NGKH001";
			if (lstNgayKH.size() != 0) {
				int max = Integer.parseInt(lstNgayKH.get(0).getMaLT().split("-")[1].substring(6));
				for (NgayKhoiHanh x : lstNgayKH) {
					int suffix = Integer.parseInt(x.getMaLT().split("-")[1].substring(6));
					if (suffix > max) {
						max = suffix;
					}

				}
				maNgKH = tourChon.getMaTour() + "-NGKH00" + (max + 1);
			}
			ngayKhoiHanh.setMaLT(maNgKH);
			ngayKhoiHanh.setTour(tourChon);
			tourChon.getNgayKhoiHanh().add(ngayKhoiHanh);

			Tour touSua = tourControl.suaTour(tourChon);

			if (touSua != null) {
				System.out.println(touSua.getNgayKhoiHanh());
				tblDSNGKH.notifyAll();
				((NgayKhoiHanhTableModel) tblDSNGKH.getModel()).fireTableDataChanged();
			}

		} else if (o.equals(btnBoChon)) {

			btnThem.setVisible(true);
			tblDSTour.clearSelection();
		} else if (o.equals(cmbLuaChon)) {
			switch (cmbLuaChon.getSelectedIndex()) {
			case 1:
				txtTimKiem.setUI(new HintTextFieldUI("Nhập mã tour cần tìm ...", true));

				break;
			case 2:
				txtTimKiem.setUI(new HintTextFieldUI("Nhập tên tour cần tìm ...", true));

				break;
			case 3:
				txtTimKiem.setVisible(false);
				cmbTimKiem.setVisible(true);

				break;
			case 4:
				txtTimKiem.setVisible(false);
				cmbTimKiem.setVisible(true);
				break;
			case 5:
				txtTimKiem.setVisible(false);
				cmbTimKiem.setVisible(true);
				break;

			default:
				cmbTimKiem.setVisible(false);
				break;
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

	/**
	 * Kiểm tra dữ liệu đầu vào
	 * 
	 * @return true hoặc falses
	 */
	private boolean kiemTraThongTin() {
		String tenTour = txpTenTour.getText().trim();
		String ngayBatDau = ((JTextField) dtcNgayKhoiHanh.getDateEditor().getUiComponent()).getText();
		if (!(tenTour.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Tên tour không được để trống !");
			txpTenTour.requestFocus();
			return false;
		}
		if (ngayBatDau.equals("")) {
			JOptionPane.showMessageDialog(this, "Ngày khởi hành không được để trống !");
			dtcNgayKhoiHanh.requestFocusInWindow();
			return false;
		}
		if (!ktNgayKhoiHanh()) {
			JOptionPane.showMessageDialog(this, "Ngày khởi hành phải sau ngày hiện tại 10 ngày !");
			dtcNgayKhoiHanh.requestFocusInWindow();
			return false;
		}
		if (!(txtGiaNgLon.getText().trim().length() > 0)) {
			JOptionPane.showMessageDialog(this, "Giá người lớn không được để trống !");
			txtGiaNgLon.requestFocus();
			return false;
		}

		if (!(txtGiaTrEm.getText().trim().length() > 0)) {
			JOptionPane.showMessageDialog(this, "Giá trẻ em không được để trống !");
			txtGiaTrEm.requestFocus();
			return false;
		}

		return true;
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
			setText(value != null ? value.toString() : "");// or something in value, like value.getNote()..
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

	// Kiểm tra ngày khởi hành.Ngày khởi hành phải sau ngày hiện tại 10 ngày.
	public boolean ktNgayKhoiHanh() {
		LocalDate ngayKhoiHanh = new Date(dtcNgayKhoiHanh.getDate().getTime()).toLocalDate();
		Period period = Period.between(LocalDate.now(), ngayKhoiHanh);
		return period.getDays() >= 10 || period.getMonths() >= 1 ? true : false;
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
