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
import java.io.File;
import java.sql.Date;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//github.com/fankao/Nhom08_QuanLyDuLich_2019.git
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

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
import utils.TableMouseListener;
import utils.TienIch;
import utils.address.Province;

public class PnlTaoTour extends JPanel implements ActionListener, PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	private JComboBox<DiaDanh> cmbDiaDanh;
	private JComboBox<Province> cmbDiemXP;
	private JComboBox<Province> cmbDiemDen;
	private JSpinner spnNgay;
	private JLabel lblMaTourdb;
	private JButton btnThem;
	private JButton btnXoaTrang;

	private JPanel pnlCTTour;
	private JPanel pnlNgayKH;
	private JPanel pnlChucNang;
	private JButton btnSua;
	private JTextField txtTimKiem;
	private NhanVien nhanvien;
	private JPanel pnlButton;
	private TourTableModel tourTableModel;
	private JButton btnThemNgayKH;
	private JLabel lblNgayDem;
	private JTextArea txaTenTour;
	private JComboBox<String> cmbPhuongTien;
	private JButton btnDong;
	private JButton btnDongThemTour;
	private JPanel pnlNhap;
	private JButton btnThemKH;
	private JButton btnSuaNgayKH;
	private JButton btnLuuNgayKH;
	private JDateChooser dtcNgayKhoiHanh;
	private JSpinner spnSoKhachToiDa;

	private static List<NgayKhoiHanh> lstNgayKH;
	private JPanel pnl;

	private NgayKhoiHanhTableModel ngkhTableModel;
	private JTable tblDSTour;

	private JScrollPane scrDSTour;
	private List<DiaDanh> lstDiaDanh;
	private JLabel lblTimKiem;

	private JButton btnBoChon;
	private JComboBox cmbTimKiem;
	private JComboBox cmbLuaChon;
	private JPanel pnlDSNgayKH;

	private JTable tblDSNGKH;
	private JButton btnLoc;
	private JPanel pnlButtonNorth;
	private JButton btnLuu;
	private JButton btnXemCTTour;
	private JButton btnLamMoi;
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

	private static String duongDanCTTour;
	private JPanel pnlIconDong;
	private JPanel pnlTinhTrangTour;
	private JLabel lblTinhTrangTour;
	private JScrollPane scrDSNgayKH;

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

		JLabel lblDiaDanh = new JLabel("Chọn địa danh:");
		lblDiaDanh.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbDiaDanh = new JComboBox<DiaDanh>();
		cmbDiaDanh.setToolTipText("Chọn địa danh cho tour.");
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
		btnCTTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
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
		btnXoaTrang = new JButton("Xoá trắng");
		btnXoaTrang.setToolTipText("Xóa trắng thông tin tour đang hiển thị.");
		btnXoaTrang.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/broom_25px.png")));
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnLuu = new JButton("Lưu");
		btnLuu.setToolTipText("Lưu thông tin tour đang xử lý.");
		btnLuu.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/save_25px.png")));
		btnLuu.setFont(new Font("Dialog", Font.PLAIN, 18));

		btnXemCTTour = new JButton("Xem chi tiết");
		btnXemCTTour.setEnabled(false);
		btnXemCTTour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_pnlCTTour = new GroupLayout(pnlCTTour);
		gl_pnlCTTour.setHorizontalGroup(
			gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCTTour.createSequentialGroup()
					.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_pnlCTTour.createSequentialGroup()
									.addGap(78)
									.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_pnlCTTour.createSequentialGroup()
									.addComponent(lblDiaDanh)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmbDiaDanh, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCTTour)
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblGiNgiLn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblDiemXP, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlCTTour.createSequentialGroup()
									.addComponent(btnXemCTTour, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCTTour))
								.addComponent(cmbDiemXP, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtGiaNgLon)))
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addGap(12)
							.addComponent(lblTenTour)))
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
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtGiaTrEm)
										.addComponent(cmbDiemDen, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
										.addComponent(lblThoiGian)
										.addComponent(lblPhuongTien)
										.addComponent(btnLuu, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
										.addComponent(cmbPhuongTien, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_pnlCTTour.createSequentialGroup()
											.addComponent(spnNgay, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNgy)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblNgayDem)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblm))
										.addComponent(btnXoaTrang))
									.addGap(38)))
							.addContainerGap(83, Short.MAX_VALUE))
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDongThemTour, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(6))))
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
					.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlCTTour.createSequentialGroup()
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlCTTour.createSequentialGroup()
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDiemDen)
										.addComponent(cmbDiemDen, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addComponent(cmbDiemXP, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDiemXP))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label))
								.addGroup(gl_pnlCTTour.createSequentialGroup()
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
											.addComponent(cmbPhuongTien, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblPhuongTien))
										.addComponent(lblTenTour))
									.addGap(18)
									.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtGiaNgLon, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGiaTrEm)
										.addComponent(txtGiaTrEm, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblThoiGian)
										.addComponent(lblGiNgiLn)
										.addComponent(spnNgay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNgy)
										.addComponent(lblNgayDem)
										.addComponent(lblm))))
							.addGap(18)
							.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
									.addComponent(btnXemCTTour, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCTTour, Alignment.TRAILING)
									.addComponent(btnCTTour, Alignment.TRAILING))
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
									.addComponent(btnXoaTrang, Alignment.TRAILING)
									.addComponent(btnLuu, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_pnlCTTour.linkSize(SwingConstants.VERTICAL, new Component[] {lblCTTour, btnCTTour, btnXemCTTour});
		gl_pnlCTTour.linkSize(SwingConstants.VERTICAL, new Component[] {btnXoaTrang, btnLuu});
		gl_pnlCTTour.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnXoaTrang, btnLuu});
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrTenTour = new JScrollPane();
		panel.add(scrTenTour, BorderLayout.CENTER);

		txaTenTour = new JTextArea();
		txaTenTour.setToolTipText("Nhập tên cho tour.");
		txaTenTour.setLineWrap(true);
		txaTenTour.setFont(new Font("Dialog", Font.PLAIN, 18));
		scrTenTour.setViewportView(txaTenTour);
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
		pnlLuaChonTK.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout fl_pnlLuaChonTK = (FlowLayout) pnlLuaChonTK.getLayout();
		fl_pnlLuaChonTK.setVgap(10);
		fl_pnlLuaChonTK.setAlignment(FlowLayout.LEFT);
		pnlTimKiem.add(pnlLuaChonTK);

		cmbLuaChon = new JComboBox<String>();
		cmbLuaChon.setToolTipText("Chọn tiêu chí để tìm kiếm thông tin tour.");
		pnlLuaChonTK.add(cmbLuaChon);
		cmbLuaChon.setModel(new DefaultComboBoxModel(new String[] { "-- Lựa chọn tìm kiếm --", "Theo mã tour",
				"Theo tên tour", "Theo địa danh", "Theo phương tiện", "Theo điểm đến" }));
		cmbLuaChon.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JPanel pnlLoaiTK = new JPanel();
		pnlLoaiTK.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) pnlLoaiTK.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlLoaiTK.setPreferredSize(new Dimension(450, 10));
		pnlTimKiem.add(pnlLoaiTK);

		lblTimKiem = new JLabel("Tìm kiếm:");
		lblTimKiem.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlLoaiTK.add(lblTimKiem);

		txtTimKiem = new JTextField();
		txtTimKiem.setToolTipText("Nhập vào thông tin tour cần tìm.");
		txtTimKiem.setEditable(false);
		pnlLoaiTK.add(txtTimKiem);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiem.setColumns(20);

		cmbTimKiem = new JComboBox();
		cmbTimKiem.setVisible(false);
		cmbTimKiem.setEditable(true);
		cmbTimKiem.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlLoaiTK.add(cmbTimKiem);

		btnLoc = new JButton("Lọc");
		btnLoc.setVisible(false);
		pnlLoaiTK.add(btnLoc);

		pnlButton = new JPanel();
		pnlButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout fl_pnlButton = (FlowLayout) pnlButton.getLayout();
		fl_pnlButton.setAlignment(FlowLayout.RIGHT);
		pnlChucNang.add(pnlButton);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setToolTipText("Làm mới thông tin bảng danh sách tour.");
		btnLamMoi.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/lammoi.png")));
		btnLamMoi.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlButton.add(btnLamMoi);

		btnThem = new JButton("Thêm");
		btnThem.setToolTipText("Thêm tour.");
		btnThem.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/plus_32px.png")));
		pnlButton.add(btnThem);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnSua = new JButton("Sửa");
		btnSua.setToolTipText("Sửa thông tin tour đang chọn.");
		btnSua.setVisible(false);
		btnSua.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/edit_property_32px.png")));
		pnlButton.add(btnSua);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnBoChon = new JButton("Bỏ chọn");
		btnBoChon.setToolTipText("Bỏ chọn tour đang chọn.");
		btnBoChon.setVisible(false);
		btnBoChon.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/exit.png")));
		btnBoChon.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlButton.add(btnBoChon);

		pnlNgayKH = new JPanel();
		pnlNgayKH.setVisible(false);
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
		pnlNhapNgayDi.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout_1 = (FlowLayout) pnlNhapNgayDi.getLayout();
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnlThemNgayKH.add(pnlNhapNgayDi);

		pnl = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnl.getLayout();
		flowLayout_3.setVgap(10);
		flowLayout_3.setHgap(10);
		pnlNhapNgayDi.add(pnl);

		JLabel lblMaTour = new JLabel("Mã tour:");
		pnl.add(lblMaTour);
		lblMaTour.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblMaTourdb = new JLabel();
		pnl.add(lblMaTourdb);
		lblMaTourdb.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaTourdb.setText("<Mã tour du lịch>");

		pnlNhap = new JPanel();
		pnlNhapNgayDi.add(pnlNhap);

		JLabel lblNgayKhoiHanh = new JLabel("Ngày khởi hành:");
		pnlNhap.add(lblNgayKhoiHanh);
		lblNgayKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 18));

		dtcNgayKhoiHanh = new JDateChooser();
		pnlNhap.add(dtcNgayKhoiHanh);
		dtcNgayKhoiHanh.setEnabled(false);

		dtcNgayKhoiHanh.setFocusable(false);
		dtcNgayKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dtcNgayKhoiHanh.setPreferredSize(new Dimension(150, 30));
		dtcNgayKhoiHanh.setDateFormatString("dd/MM/yyyy");

		JLabel lblSoKhachToiDa = new JLabel("Số người tối đa:");
		pnlNhap.add(lblSoKhachToiDa);
		lblSoKhachToiDa.setFont(new Font("Tahoma", Font.PLAIN, 18));

		spnSoKhachToiDa = new JSpinner();
		spnSoKhachToiDa.setToolTipText("Số người ít nhất là 10.");
		spnSoKhachToiDa.setEnabled(false);
		spnSoKhachToiDa.setModel(new SpinnerNumberModel(10, 10, 40, 0));

		spnSoKhachToiDa.setPreferredSize(new Dimension(40, 30));
		pnlNhap.add(spnSoKhachToiDa);

		spnSoKhachToiDa.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnThemKH = new JButton("Thêm");
		btnThemKH.setToolTipText("Thêm ngày khởi hành");
		btnThemKH.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlNhapNgayDi.add(btnThemKH);

		btnSuaNgayKH = new JButton("Sửa");
		btnSuaNgayKH.setToolTipText("Sửa thông tin ngày khởi hành.");
		btnSuaNgayKH.setFont(new Font("Dialog", Font.PLAIN, 20));
		pnlNhapNgayDi.add(btnSuaNgayKH);

		btnLuuNgayKH = new JButton("Lưu");
		btnLuuNgayKH.setToolTipText("Lưu thông tin ngày khởi hành.");
		btnLuuNgayKH.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlNhapNgayDi.add(btnLuuNgayKH);

		JPanel pnlDong = new JPanel();
		pnlDong.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlDong.setPreferredSize(new Dimension(350, 10));
		pnlThemNgayKH.add(pnlDong);
		pnlDong.setLayout(new GridLayout(0, 2, 0, 0));

		pnlTinhTrangTour = new JPanel();
		pnlDong.add(pnlTinhTrangTour);

		lblTinhTrangTour = new JLabel("<html>Tour đã được <br> duyệt mở bán</html>");
		lblTinhTrangTour.setVisible(false);
		pnlTinhTrangTour.add(lblTinhTrangTour);
		lblTinhTrangTour.setForeground(new Color(0, 100, 0));
		lblTinhTrangTour.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));

		pnlIconDong = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlIconDong.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		pnlDong.add(pnlIconDong);

		btnDong = new JButton("");
		btnDong.setToolTipText("Hủy thêm ngày khởi hành.");
		pnlIconDong.add(btnDong);
		btnDong.setBackground(SystemColor.window);
		btnDong.setBorder(null);
		btnDong.setFocusable(false);
		btnDong.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/delete_sign_32px.png")));

		pnlDSNgayKH = new JPanel();
		pnlNgayKH.add(pnlDSNgayKH, BorderLayout.CENTER);
		pnlDSNgayKH.setLayout(new BorderLayout(0, 0));

		scrDSNgayKH = new JScrollPane();
		pnlDSNgayKH.add(scrDSNgayKH, BorderLayout.NORTH);

		pnlButtonNorth = new JPanel();
		pnlButtonNorth.setVisible(false);
		pnlButtonNorth.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout fl_pnlButtonNorth = (FlowLayout) pnlButtonNorth.getLayout();
		fl_pnlButtonNorth.setAlignment(FlowLayout.RIGHT);
		pnlDSCTTour.add(pnlButtonNorth, BorderLayout.SOUTH);

		btnThemNgayKH = new JButton("Thêm ngày khởi hành");
		btnThemNgayKH.setToolTipText("Thêm ngày khởi hàng cho tour đang chon.");
		btnThemNgayKH.setIcon(new ImageIcon(PnlTaoTour.class.getResource("/images/plus_32px.png")));
		btnThemNgayKH.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlButtonNorth.add(btnThemNgayKH);

		scrDSTour = new JScrollPane();
		pnlDSCTTour.add(scrDSTour, BorderLayout.CENTER);
		btnThemNgayKH.setVisible(false);

		tblDSNGKH = new JTable();

		btnLuu.setPreferredSize(btnXoaTrang.getPreferredSize());
		btnThem.setPreferredSize(btnXoaTrang.getPreferredSize());
		btnXoaTrang.setVisible(false);

		/*
		 * ===========================================
		 * ===========================================
		 * 
		 */

		tourControl = new TourControlImpl();

		// Hiển thị danh sách dia danh

		lstDiaDanh = tourControl.layDSDiaDanh();
		hienThiDSDiaDanh(lstDiaDanh, cmbDiaDanh);

		// hiển thị danh sách các tour du lịch
		tblDSTour = new JTable();
		tblDSTour.setToolTipText("Bảng hiển thị danh sách tour.");
		tblDSTour.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstTour = tourControl.layDsTourTheoYeuCau(2, nv.getMaNV());
		hienDanhSachTour(tblDSTour, lstTour, scrDSTour);

		// Hiện địa điểm xuất phát, điểm đến

		List<Province> lstdiaDiem = TienIch.layDiaLyHanhChinh();
		hienDiaDiem(lstdiaDiem, cmbDiemDen);
		hienDiaDiem(lstdiaDiem, cmbDiemXP);
		// gắn sự kiện cho các control

		cmbDiaDanh.setEditable(true);
		ganSuKien();

		btnThem.setPreferredSize(btnBoChon.getPreferredSize());
		btnLamMoi.setPreferredSize(btnBoChon.getPreferredSize());
		btnLuu.setPreferredSize(btnBoChon.getPreferredSize());
		btnSua.setPreferredSize(btnBoChon.getPreferredSize());
		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlTour, pnlNgayKH }, "Arial", Font.PLAIN, 20);
		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlCTTour, pnlDSCTTour }, "Arial", Font.PLAIN, 18);
		((JTextField) dtcNgayKhoiHanh.getDateEditor().getUiComponent()).setEditable(false);

	}

	/**
	 * Hiện danh sách tour
	 * 
	 * @param dsTour: danh sách tour
	 */
	private void hienDanhSachTour(JTable tblTour, List<Tour> dsTour, JScrollPane scr) {
		if (dsTour == null)
			dsTour = new ArrayList<Tour>();
		tourTableModel = new TourTableModel(dsTour);
		tblTour.setModel(tourTableModel);
		scr.setViewportView(tblTour);

		if (dsTour.size() != 0) {
			tblTour.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(2).setCellRenderer(new MyRenderer());
			tblTour.getColumnModel().getColumn(3).setCellRenderer(new MyRenderer());
			tblTour.getColumnModel().getColumn(4).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(5).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(6).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(9).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(10).setCellRenderer(new CenterRenderrer());
		}
		TienIch.chinhKichThuocTable(tblTour, tblTour.getColumnModel().getTotalColumnWidth(), 2, 10, 30, 30, 20, 20, 20,
				15, 15, 15, 15);

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
	private void hienThiDSDiaDanh(List<DiaDanh> lstDiaDanh, JComboBox<DiaDanh> cmbDiaDanh) {
		DefaultComboBoxModel<DiaDanh> cmbModel = (DefaultComboBoxModel<DiaDanh>) cmbDiaDanh.getModel();
		cmbModel.removeAllElements();
		for (DiaDanh diaDanh : lstDiaDanh) {
			cmbModel.addElement(diaDanh);
		}

	}

	/*-----------------------------------------------
	 * 
	 * Xử lý sự kiện
	 * 
	 * ----------------------------------------------
	 */

	/**
	 * Gán sự kiện cho các component
	 */
	private void ganSuKien() {
		btnLuu.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnThemNgayKH.addActionListener(this);
		btnDong.addActionListener(this);
		btnDongThemTour.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuuNgayKH.addActionListener(this);
		btnThemKH.addActionListener(this);
		btnSuaNgayKH.addActionListener(this);
		btnBoChon.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnXemCTTour.addActionListener(this);
		btnCTTour.addActionListener(this);
		btnLoc.addActionListener(this);

		cmbLuaChon.addActionListener(this);

		cmbTimKiem.addActionListener(this);

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
				btnSua.setSelected(true);
				pnlButtonNorth.setVisible(true);
				btnThemNgayKH.setVisible(true);
				if (btnSua.isSelected() == true) {
					Tour tour = tourControl.layTourTheoMa(tblDSTour.getValueAt(row, 1).toString());
					hienThongTinTour(tour);
				}

			}

		});
		tblDSNGKH.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				btnSuaNgayKH.setVisible(true);
				int row = tblDSNGKH.getSelectedRow();
				if (row == -1)
					return;
				NgayKhoiHanh nkh = lstNgayKH.get(row);
				hienTTNgayKhoiHanh(nkh);

			}
		});
		tblDSTour.addMouseListener(new TableMouseListener(tblDSTour));

		AutoCompleteDecorator.decorate(cmbDiaDanh);
		AutoCompleteDecorator.decorate(cmbDiemDen);
		AutoCompleteDecorator.decorate(cmbDiemXP);

		AutoCompleteDecorator.decorate(cmbTimKiem);
		timKiemTheoTuKhoa();
	}

	/**
	 * Xử lý sự kiện
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		/*
		 * Chọn nút thêm tour
		 */
		if (o.equals(btnThem)) {
			tblDSTour.clearSelection();
			tblDSTour.setEnabled(false);
			pnlCTTour.setVisible(true);
			btnThem.setVisible(false);
			btnLuu.setVisible(true);
			btnXoaTrang.setVisible(true);
			btnBoChon.setVisible(false);
			btnSua.setVisible(false);
			btnThem.setSelected(true);
			btnXemCTTour.setVisible(true);

			btnLamMoi.setVisible(false);
			xoaTrang();

		}
		/*
		 * Nút sửa ngày khởi hành
		 */
		else if (o.equals(btnSua)) {
			pnlCTTour.setVisible(true);
			btnThem.setVisible(false);
			btnSua.setVisible(false);
			btnLuu.setVisible(true);
			tblDSTour.setEnabled(false);
			btnBoChon.setVisible(false);
			btnXoaTrang.setVisible(true);

			btnLamMoi.setVisible(false);

			if (duongDanCTTour != null) {
				btnXemCTTour.setEnabled(false);
			}

		}
		/*
		 * Nút thêm chương trình tour
		 */
		else if (o.equals(btnCTTour)) {
			JFileChooser fileChooser = new JFileChooser(new File("./chuongtrinhtour"));
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				duongDanCTTour = fileChooser.getSelectedFile().getName();
				if (duongDanCTTour != null) {
					btnXemCTTour.setEnabled(true);
				}
			}
		}
		/*
		 * Nút xem chương trình tour
		 */
		else if (o.equals(btnXemCTTour)) {
			TienIch.hienFilePDF(duongDanCTTour);
		}
		/*
		 * Nút làm mới danh sách tour
		 */
		else if (o.equals(btnLamMoi)) {
			
			btnBoChon.setVisible(false);
			tblDSTour.clearSelection();
			pnlButtonNorth.setVisible(false);
			btnSua.setVisible(false);
			hienDanhSachTour(tblDSTour, lstTour, scrDSTour);
		}
		/*
		 * Nút đóng giao diện cập nhật tour
		 */
		else if (o.equals(btnDongThemTour)) {
			tblDSTour.clearSelection();
			btnThem.setVisible(true);
			btnBoChon.setVisible(false);
			pnlCTTour.setVisible(false);
			btnLuu.setVisible(false);
			tblDSTour.setEnabled(true);
			btnSua.setSelected(false);
			btnThem.setSelected(false);

			btnLamMoi.setVisible(true);

			pnlButtonNorth.setVisible(false);

		}
		/*
		 * Nút đóng giao diện cập nhật ngày khởi hành
		 */
		else if (o.equals(btnDong)) {
			xoaTrangTTKH();
			tblDSTour.clearSelection();
			pnlNgayKH.setVisible(false);
			pnlNhap.setVisible(false);
			btnThem.setSelected(false);
			btnSua.setVisible(false);
			tblDSTour.setEnabled(true);
			btnThemNgayKH.setVisible(false);
			pnlButtonNorth.setVisible(true);
			btnBoChon.setVisible(false);
			btnThem.setVisible(true);
			dtcNgayKhoiHanh.setEnabled(false);
			spnSoKhachToiDa.setEnabled(false);

			btnLamMoi.setVisible(true);

		}
		/*
		 * Nút xoá trắng
		 */
		else if (o.equals(btnXoaTrang)) {
			xoaTrang();

		}
		/*
		 * Lưu thông tin tour
		 */
		else if (o.equals(btnLuu)) {
			if (kiemTraThongTin() == true) {
				btnBoChon.setVisible(false);
				Tour tour = new Tour();
				tour.setTenTour(txaTenTour.getText());
				tour.setDiaDanh((DiaDanh) cmbDiaDanh.getSelectedItem());
				tour.setDiemKhoiHanh(cmbDiemXP.getSelectedItem().toString());
				tour.setDiemDen(cmbDiemDen.getSelectedItem().toString());
				tour.setNhanVien(nhanvien);
				tour.setPhuongTien((PhuongTien) cmbPhuongTien.getSelectedItem());
				tour.setThoiGian(new int[] { soNgay, soDem });

				tour.setDonGiaNguoiLon(donGiaNguoiLon);
				tour.setDonGiaTreEm(donGiaTreEm);
				if (btnThem.isSelected() == true) {
					Tour tourThem = tourControl.themTour(tour);
					if (tourThem != null) {
						btnThem.setSelected(false);
						TienIch.xoaTrangCacJTextField(txtGiaNgLon, txtGiaTrEm, txaTenTour);
						cmbDiaDanh.setSelectedIndex(0);
						cmbDiemXP.setSelectedIndex(0);
						cmbDiemDen.setSelectedIndex(0);
						lstTour = tourControl.layDsTourTheoYeuCau(2, nhanvien.getMaNV());
						hienDanhSachTour(tblDSTour, lstTour, scrDSTour);
						tblDSTour.clearSelection();
						tblDSTour.getSelectionModel().setSelectionInterval(lstTour.size() - 1, lstTour.size() - 1);
					}
					tblDSTour.setEnabled(true);
				}

				if (btnSua.isSelected() == true) {
					int row = tblDSTour.getSelectedRow();
					Tour tourChon = tourControl.layTourTheoMa(tblDSTour.getValueAt(row, 1).toString());
					tourChon.setTenTour(tour.getTenTour());
					tourChon.setDiaDanh(tour.getDiaDanh());
					tourChon.setDiemKhoiHanh(tour.getDiemKhoiHanh());
					tourChon.setDiemDen(tour.getDiemDen());
					tourChon.setNhanVien(nhanvien);
					tourChon.setPhuongTien(tour.getPhuongTien());
					tourChon.setThoiGian(tour.getThoiGian());
					tourChon.setDonGiaNguoiLon(tour.getDonGiaNguoiLon());
					tourChon.setDonGiaTreEm(tour.getDonGiaTreEm());
					tourChon.setMoTa(duongDanCTTour);
					Tour tourSua = tourControl.suaTour(tourChon);
					if (tourSua != null) {
						lstTour = tourControl.layDsTourTheoYeuCau(2, nhanvien.getMaNV());
						hienDanhSachTour(tblDSTour, lstTour, scrDSTour);
						tblDSTour.clearSelection();
						tblDSTour.getSelectionModel().setSelectionInterval(row, row);
						btnSua.setSelected(false);
					}
					pnlCTTour.setVisible(false);
					tblDSTour.setEnabled(true);
					btnLuu.setVisible(false);
					btnThem.setVisible(true);

				}

			}
		}
		/*
		 * Nhấn nút bỏ chọn
		 */
		else if (o.equals(btnBoChon)) {
			tblDSTour.clearSelection();

			btnBoChon.setVisible(false);
			btnThem.setVisible(true);
			btnSua.setVisible(false);
			btnLuuNgayKH.setEnabled(false);
			btnLamMoi.setVisible(true);

			tblDSTour.setEnabled(true);
			btnThemNgayKH.setVisible(false);
			pnlNgayKH.setVisible(false);
			pnlButtonNorth.setVisible(false);
		}
		/*
		 * Chọn nút thêm danh sách ngày khởi hành
		 */
		else if (o.equals(btnThemNgayKH)) {
			pnlNgayKH.setVisible(true);
			pnlNhap.setVisible(true);
			xoaTrangTTKH();
			btnThemKH.setVisible(true);
			btnSuaNgayKH.setVisible(false);
			btnLuuNgayKH.setVisible(false);
			tblDSNGKH.setEnabled(true);
			tblDSTour.setEnabled(false);
			pnlButtonNorth.setVisible(false);
			String maTour = tblDSTour.getValueAt(tblDSTour.getSelectedRow(), 1).toString();
			Tour tourChon = tourControl.layTourTheoMa(maTour);
			lblMaTourdb.setText(tourChon.getMaTour());

			if (tourChon.isDaDuyet()) {
				lblTinhTrangTour.setVisible(true);
				btnSuaNgayKH.setEnabled(false);
			} else {
				lblTinhTrangTour.setVisible(false);
				btnSuaNgayKH.setEnabled(true);
			}
			btnLamMoi.setVisible(false);
			lstNgayKH = tourControl.layDSNgayKhoiHanhTheoTour(maTour);
			hienDanhSachNgayKhoiHanh(tblDSNGKH, lstNgayKH, scrDSNgayKH);

		}
		/*
		 * Chọn nút thêm ngày khởi hành
		 */
		else if (o.equals(btnThemKH)) {
			xoaTrangTTKH();
			btnThem.setSelected(true);
			pnlNhap.setVisible(true);
			btnThemKH.setSelected(true);
			btnThemKH.setVisible(false);
			btnLuuNgayKH.setVisible(true);
			btnLuuNgayKH.setEnabled(true);
			tblDSNGKH.setEnabled(false);
			dtcNgayKhoiHanh.setEnabled(true);
			spnSoKhachToiDa.setEnabled(true);

			btnSua.setSelected(false);
		}

		/*
		 * 
		 */
		else if (o.equals(btnSuaNgayKH)) {
			btnSuaNgayKH.setVisible(false);
			tblDSNGKH.setEnabled(false);
			btnThemKH.setVisible(false);
			btnSua.setVisible(false);
			btnLuuNgayKH.setVisible(true);
			btnLuuNgayKH.setEnabled(true);
			dtcNgayKhoiHanh.setEnabled(true);
			spnSoKhachToiDa.setEnabled(true);

			btnThemKH.setSelected(false);
		}

		/*
		 * 
		 */
		else if (o.equals(btnLuuNgayKH)) {
			if (kiemTraNgayKhoiHanh() == true) {
				btnLuuNgayKH.setVisible(false);
				btnSuaNgayKH.setVisible(false);
				btnThemKH.setVisible(true);
				tblDSNGKH.setEnabled(true);
				dtcNgayKhoiHanh.setEnabled(false);
				spnSoKhachToiDa.setEnabled(true);
				int row = tblDSTour.getSelectedRow();

				if (row == -1)
					return;

				int rowNgayKH = tblDSNGKH.getSelectedRow();

				NgayKhoiHanh ngayKhoiHanh = null;
				if (rowNgayKH > -1 && rowNgayKH <= tblDSNGKH.getRowCount() - 1) {
					ngayKhoiHanh = lstNgayKH.get(rowNgayKH);
				}

				Tour tourChon = tourControl.layTourTheoMa(tblDSTour.getValueAt(row, 1).toString());
				if (btnThemKH.isSelected() && ngayKhoiHanh == null) {
					ngayKhoiHanh = new NgayKhoiHanh();
					ngayKhoiHanh.setMaLT(tourControl.phatSinhNgayKhoiHanh(tourChon.getMaTour()));
					btnThem.setVisible(false);

				}
				ngayKhoiHanh.setNgayKhoiHanh(dtcNgayKhoiHanh.getDate());
				int soNguoi = (int) spnSoKhachToiDa.getValue();
				ngayKhoiHanh.setSoKhachToiDa(soNguoi);
				ngayKhoiHanh.setTour(tourChon);
				if (btnSua.isSelected()) {
					tourChon.getNgayKhoiHanh().remove(rowNgayKH);
					tourChon.getNgayKhoiHanh().add(rowNgayKH, ngayKhoiHanh);
				} else {
					tourChon.getNgayKhoiHanh().add(ngayKhoiHanh);
				}

				Tour touSua = tourControl.suaTour(tourChon);

				if (touSua != null) {
					lstTour = tourControl.layDsTourTheoYeuCau(2, nhanvien.getMaNV());
					lstNgayKH = tourControl.layDSNgayKhoiHanhTheoTour(tourChon.getMaTour());
					hienDanhSachTour(tblDSTour, lstTour, scrDSTour);
					tblDSTour.setRowSelectionInterval(row, row);
					btnBoChon.setVisible(true);
					hienDanhSachNgayKhoiHanh(tblDSNGKH, lstNgayKH, scrDSNgayKH);
					pnlButtonNorth.setVisible(false);
					dtcNgayKhoiHanh.setEnabled(false);
					spnSoKhachToiDa.setEnabled(false);

				}
			}

		}
		/*
		 * Lựa chọn loại tìm kiếm
		 */
		else if (o.equals(cmbLuaChon)) {
			switch (cmbLuaChon.getSelectedIndex()) {
			case 1:
				txtTimKiem.setEditable(true);
				cmbTimKiem.setVisible(false);
				btnLoc.setVisible(false);
				txtTimKiem.setUI(new HintTextFieldUI("Nhập mã tour cần tìm ...", true));

				break;
			case 2:
				txtTimKiem.setEditable(true);
				cmbTimKiem.setVisible(false);
				btnLoc.setVisible(false);
				txtTimKiem.setUI(new HintTextFieldUI("Nhập tên tour cần tìm ...", true));

				break;
			case 3:
				txtTimKiem.setVisible(false);
				cmbTimKiem.setSelectedItem(null);
				cmbTimKiem.setVisible(true);
				btnLoc.setVisible(true);
				hienThiDSDiaDanh(lstDiaDanh, cmbTimKiem);

				break;
			case 4:
				txtTimKiem.setVisible(false);
				cmbTimKiem.setSelectedItem(null);
				cmbTimKiem.setVisible(true);
				btnLoc.setVisible(true);
				cmbTimKiem.setModel(new DefaultComboBoxModel(PhuongTien.values()));
				break;
			case 5:
				txtTimKiem.setVisible(false);
				cmbTimKiem.setSelectedItem(null);
				cmbTimKiem.setVisible(true);
				btnLoc.setVisible(true);
				hienDiaDiem(TienIch.layDiaLyHanhChinh(), cmbTimKiem);
				break;

			default:
				txtTimKiem.setVisible(true);
				txtTimKiem.setUI(new HintTextFieldUI(""));
				txtTimKiem.setEditable(false);
				cmbTimKiem.setVisible(false);
				btnLoc.setVisible(false);
				break;

			}

		}
		/*
		 * Lọc tour theo combox
		 */
		else if (o.equals(btnLoc)) {
			List<Tour> dsTourCanTim = new ArrayList<Tour>();
			switch (cmbLuaChon.getSelectedIndex()) {
			case 3:
				// hiện danh sách tour theo địa danh
				try {
					DiaDanh key = (DiaDanh) cmbTimKiem.getEditor().getItem();
					for (Tour tour : lstTour) {

						if (key != null) {
							if (tour.getDiaDanh().getTenDiaDanh().equalsIgnoreCase(key.getTenDiaDanh())) {
								dsTourCanTim.add(tour);
							}
						} else {
							dsTourCanTim.add(tour);
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Đia danh tìm kiếm không hợp lệ", "Lỗi",
							JOptionPane.ERROR_MESSAGE);

				}

				break;
			case 4:
				// hiện danh sách tour theo phương tiện
				try {
					PhuongTien phuongtien = (PhuongTien) cmbTimKiem.getEditor().getItem();
					for (Tour tour : lstTour) {
						if (tour.getPhuongTien() == phuongtien) {
							dsTourCanTim.add(tour);
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Phương tiện tìm kiếm không hợp lệ", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					dsTourCanTim = lstTour;
				}
				break;
			case 5:
				// hiện danh sách tour theo điểm đến
				try {
					String diemden = (String) cmbTimKiem.getSelectedItem();
					for (Tour tour : lstTour) {
						if (tour.getDiemDen().equalsIgnoreCase(diemden)) {
							dsTourCanTim.add(tour);
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Điểm đến tìm kiếm không hợp lệ", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					dsTourCanTim = lstTour;
				}
				break;

			default:
				dsTourCanTim = lstTour;
				dsTourCanTim = lstTour;
				break;
			}
			if (dsTourCanTim.size() == 0) {
				dsTourCanTim = lstTour;
			}
			hienDanhSachTour(tblDSTour, dsTourCanTim, scrDSTour);

		}
	}

	/**
	 * hiện danh sách ngày khởi hành theo tour
	 * 
	 * @param tbl: bảng
	 * @param ds:  danh sách ngày khởi hành
	 * @param scr: thanh cuộn
	 */
	private void hienDanhSachNgayKhoiHanh(JTable tbl, List<NgayKhoiHanh> ds, JScrollPane scr) {
		ngkhTableModel = new NgayKhoiHanhTableModel(ds);
		tbl.setModel(ngkhTableModel);
		TienIch.chinhKichThuocTable(tbl, tbl.getColumnModel().getTotalColumnWidth(), 5, 50, 20, 20);
		if (ds.size() != 0) {
			tbl.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderrer());
			tbl.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderrer());
			tbl.getColumnModel().getColumn(2).setCellRenderer(new CenterRenderrer());
			tbl.getColumnModel().getColumn(3).setCellRenderer(new CenterRenderrer());
		}
		scr.setViewportView(tbl);

	}

	/**
	 * Tìm kiếm tour du lịch
	 */
	private void timKiemTheoTuKhoa() {
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String content = TienIch
						.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTimKiem.getText().trim().toLowerCase());
				List<Tour> lstTourTK = new ArrayList<Tour>();
				lstTour.forEach(x -> {
					switch (cmbLuaChon.getSelectedIndex()) {
					case 1:
						String maTour = TienIch
								.chuyenChuoiTiengVietThanhChuoiKhongDau(x.getMaTour().trim().toLowerCase());
						if (maTour.contains(content)) {
							lstTourTK.add(x);
						}
						break;
					case 2:
						String tenTour = TienIch
								.chuyenChuoiTiengVietThanhChuoiKhongDau(x.getTenTour().trim().toLowerCase());
						if (tenTour.contains(content)) {
							lstTourTK.add(x);
						}
						break;
					default:
						lstTourTK.add(x);
						break;
					}
				});
				hienDanhSachTour(tblDSTour, lstTourTK, scrDSTour);

			}
		});

	}

	/**
	 * Hiên thông tin tour du lịch
	 * 
	 * @param tourSel
	 */
	private void hienThongTinTour(Tour tourSel) {
		txaTenTour.setText(tourSel.getTenTour());
		cmbDiaDanh.setSelectedItem(tourSel.getDiaDanh());
		cmbDiemXP.setSelectedItem(tourSel.getDiemKhoiHanh());
		cmbDiemDen.setSelectedItem(tourSel.getDiemDen());
		txtGiaNgLon.setValue(tourSel.getDonGiaNguoiLon());
		txtGiaTrEm.setValue(tourSel.getDonGiaTreEm());
		duongDanCTTour = tourSel.getMoTa();
		if (tourSel.getThoiGian() != null) {
			spnNgay.setValue(tourSel.getThoiGian()[0]);
			lblNgayDem.setText(tourSel.getThoiGian()[1] + "");
		}
		if (tourSel.getPhuongTien() == PhuongTien.XE)
			cmbPhuongTien.setSelectedIndex(0);
		else if (tourSel.getPhuongTien() == PhuongTien.HANGKHONG)
			cmbPhuongTien.setSelectedIndex(1);
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
		String tenTour = txaTenTour.getText().trim();
		double donGiaNL = Double.parseDouble(txtGiaNgLon.getValue().toString());
		double dongiaTE = Double.parseDouble(txtGiaTrEm.getValue().toString());
		try {
			DiaDanh diaDanh = (DiaDanh) cmbDiaDanh.getEditor().getItem();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Địa danh không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			cmbDiaDanh.requestFocusInWindow();
			return false;
		}

		if (!(tenTour.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Tên tour không được để trống !");
			txaTenTour.requestFocus();
			return false;
		}
		if (donGiaNL < 1000000 && donGiaNL > 10000000) {
			JOptionPane.showMessageDialog(this, "Đơn giá người lớn phải lớn hơn hoặc bằng một triệu !");
			txtGiaNgLon.requestFocusInWindow();
			return false;
		}
		if (dongiaTE < 1000000 && dongiaTE > 10000000) {
			JOptionPane.showMessageDialog(this, "Đơn giá trẻ em phải lớn hơn hoặc bằng một triệu !");
			txtGiaTrEm.requestFocusInWindow();
			return false;
		}
		try {
			String diemKH = cmbDiemXP.getEditor().getItem().toString();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Điểm xuất phát không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			cmbDiemXP.requestFocusInWindow();
			return false;
		}

		try {
			String diemDen = cmbDiemDen.getEditor().getItem().toString();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Điểm đến không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			cmbDiemDen.requestFocusInWindow();
			return false;
		}
		if (Double.parseDouble(txtGiaNgLon.getValue().toString()) > 20000000) {
			JOptionPane.showMessageDialog(this, "Đơn giá người lớn không được lớn hơn hai mươi triệu");
			txtGiaNgLon.requestFocusInWindow();
			return false;
		}
		if (Double.parseDouble(txtGiaNgLon.getValue().toString()) < 1000000) {
			JOptionPane.showMessageDialog(this, "Đơn giá người lớn không được bé hơn một triệu");
			txtGiaNgLon.requestFocusInWindow();
			return false;
		}
		if (Double.parseDouble(txtGiaTrEm.getValue().toString()) > 20000000) {
			JOptionPane.showMessageDialog(this, "Đơn giá trẻ em không được lớn hơn hai mươi triệu");
			txtGiaTrEm.requestFocusInWindow();
			return false;
		}
		if (Double.parseDouble(txtGiaTrEm.getValue().toString()) > 20000000) {
			JOptionPane.showMessageDialog(this, "Đơn giá trẻ em không được bé hơn một triệu");
			txtGiaTrEm.requestFocusInWindow();
			return false;
		}

		return true;
	}

	/**
	 * Kiểm tra thông tin ngày khởi hành
	 * 
	 * @return
	 */
	private boolean kiemTraNgayKhoiHanh() {
		String ngayBatDau = ((JTextField) dtcNgayKhoiHanh.getDateEditor().getUiComponent()).getText();
		int soKhachToiDa = Integer.parseInt(spnSoKhachToiDa.getValue().toString());
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
		if (soKhachToiDa < 10 || soKhachToiDa > 50) {
			JOptionPane.showMessageDialog(this, "Số khách tối đa không được bé hơn 10 người hoặc vượt quá 50 người !");
			spnSoKhachToiDa.requestFocusInWindow();
			return false;
		}
		return true;
	}

	/**
	 * Kiểm tra ngày khởi hành có hợp lê
	 * 
	 * @return true: nếu ngày khởi hành cách ngày hiện tại 10 ngày/ false: ngược lại
	 */
	public boolean ktNgayKhoiHanh() {
		LocalDate ngayKhoiHanh = new Date(dtcNgayKhoiHanh.getDate().getTime()).toLocalDate();
		Period period = Period.between(LocalDate.now(), ngayKhoiHanh);
		return period.getDays() >= 10 || period.getMonths() >= 1 ? true : false;
	}

	/**
	 * Hiện thị thông tin ngày khởi hành
	 * 
	 * @param nkh: ngày khởi hành
	 */
	private void hienTTNgayKhoiHanh(NgayKhoiHanh nkh) {
		dtcNgayKhoiHanh.setDate(nkh.getNgayKhoiHanh());
		spnSoKhachToiDa.setValue(nkh.getSoKhachToiDa());
	}

	/**
	 * Xoá trằng thông tin tour
	 */
	private void xoaTrang() {
		cmbDiaDanh.setSelectedItem(null);
		cmbDiemDen.setSelectedItem(null);
		cmbDiemXP.setSelectedItem(null);
		txaTenTour.setText("");
		cmbPhuongTien.setSelectedIndex(0);
		txtGiaNgLon.setValue((Double) 0.0);
		txtGiaTrEm.setValue((Double) 0.0);
	}

	/**
	 * Xoá trắng thông tin ngày khởi hành
	 */
	private void xoaTrangTTKH() {
		dtcNgayKhoiHanh.setDate(null);
		spnSoKhachToiDa.setValue(2);
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
			int height = getPreferredSize().height + 30;
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
