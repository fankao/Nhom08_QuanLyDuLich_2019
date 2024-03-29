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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.toedter.calendar.JDateChooser;

import constant.HangSo;
import control.IKhachHangControl;
import control.IPhieuDangKyControl;
import control.ITourControl;
import control.impl.KhachHangControlImpl;
import control.impl.PhieuDangKyControlImpl;
import control.impl.TourControlImpl;
import entities.DiaChi;
import entities.DiaDanh;
import entities.DoTuoi;
import entities.KhachHang;
import entities.KhachHangThamGia;
import entities.NgayKhoiHanh;
import entities.NhanVien;
import entities.PhieuDangKy;
import entities.PhieuThuChi;
import entities.Tour;
import gui.dialog.DlgPhieuThu;
import model.DSKhachHangTGTableModel;
import model.TourTableModel;
import utils.TienIch;
import utils.address.District;
import utils.address.Province;
import utils.address.Ward;

/**
 * PnlDangKyTour.java
 * 
 * @author Minh Chiến, Thanh Trí<br>
 *         Ngày tạo 25/11/2019
 *
 */
public class PnlDangKyTour extends JPanel implements ActionListener, ListSelectionListener {

	/**
	 * Khai báo các thuộc tính
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblDSNgayDi;
	private JTextField txtHoTenKH;
	private JTextField txtSoCMND;
	private JTextField txtSdtKH;
	private JButton btnTimKiem;
	private JButton btnThemMoiKH;
	private JButton btnThemDC;
	private JPanel pnlTimKiemKhachHang;
	private JPanel pnlThongTinKH;
	private JTable tblDSKhachThamGia;
	private JButton btnDangKyTour;
	private JButton btnHuyChonNgayKH;
	private JTable tblDSTour;
	private TourTableModel tourTableModel;
	private JComboBox<Province> cmbTinh;
	private JComboBox<District> cmbHuyen;
	private JComboBox<Ward> cmbXa;
	private List<Province> lstProvices;
	private JButton btnLuuDC;
	private JButton btnLuuTTKhachHang;
	private JDateChooser dtcNgaySinh;
	private JPanel pnlDSTour;

	private ITourControl tourControl;
	private IPhieuDangKyControl phieuDangKyControl;
	private IKhachHangControl khachHangControl;
	private NhanVien nhanvien;
	private static List<NgayKhoiHanh> dsNgayKhoiHanh;

	private static List<Tour> dsTourDaDuyet;
	private JScrollPane scrDSTour;
	private static PnlTimKiemKhachHang pnlTimKiemKH;
	public static JButton btnXacNhan;
	private JButton btnDongTimKiem;
	private static KhachHang khachHang;
	private JLabel lblDiaChi;
	private JTextField txtHoVaTenKHTG;
	private JButton btnThemKHTG;
	private JPanel pnlDSKhachHangTG;
	private static List<KhachHangThamGia> dsKhachHangThamGia;
	private JDateChooser dtcNgaySinhKHTG;
	private static NgayKhoiHanh ngayKhoiHanh;
	private JTextField txtTimKiemTheoTen;
	private JDateChooser dtcTuNgay;
	private JButton btnLamMoi;
	private JButton btnTimKiemTheoNgay;
	private JButton btnLocDiaDanh;
	private JLabel lblGioiTinh;
	private JRadioButton rdbNam;
	private JRadioButton rdbNu;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JDateChooser dtcDenNgay;
	private JPanel pnlHuyChonNgayKH;
	private JPanel pnlChonNgayKH;
	private JButton btnThemKhachHangTG;
	private JLabel lblMaNgayKH;
	private JLabel lblMaNgayKHdb;
	private JLabel lblNgayKH;
	private JLabel lblNgayKHdb;
	private JPanel pnlTTNgayKH;
	private JCheckBox chkThamGiaTourDK;
	private JButton btnXoaKHTG;
	private JButton btnHuyChonTour;
	private JPanel pnlThemTTKHTG;
	private JScrollPane scrDSKhachThamGia;
	private JComboBox cmbDiaDanh;
	private JPanel pnlHuyChonTour;
	private JPanel pnlThongTinNgayKH;
	private JPanel pnlButtonNgayKH;
	private JPanel pnlDangKyTour;
	private JPopupMenu popupMenu;
	private JMenuItem mnuXemTTTour;
	private JButton btnLamMoiNgKH;
	private JPanel panel;
	private JPanel pnlLamMoiNgKH;
	private JLabel lblTinhTrangKHTG;
	private JPanel pnlTinhTrangTour;
	private JPanel pnlButtonTimKiemVaThem;
	private JPanel pnlDongThemKH;
	private JButton btnDongThemKH;

	/**
	 * Khởi tạo giao diện đăng ký tour
	 * 
	 * @param nv: nhân viên thực hiện đăng ký tour
	 */
	public PnlDangKyTour(NhanVien nv) {
		this.nhanvien = nv;
		pnlTimKiemKhachHang = new JPanel();
		pnlTimKiemKhachHang.setPreferredSize(new Dimension(400, 10));
		pnlTimKiemKhachHang.setVisible(false);
		setLayout(new BorderLayout(0, 0));
		add(pnlTimKiemKhachHang, BorderLayout.WEST);
		pnlTimKiemKhachHang.setLayout(new BorderLayout(0, 0));

		JPanel pnlButton = new JPanel();
		pnlButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout_1 = (FlowLayout) pnlButton.getLayout();
		flowLayout_1.setVgap(10);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnlTimKiemKhachHang.add(pnlButton, BorderLayout.SOUTH);

		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setToolTipText("Xác nhận chọn khách hàng");
		btnXacNhan.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/checked_25px.png")));
		btnXacNhan.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlButton.add(btnXacNhan);

		btnDongTimKiem = new JButton("Đóng");
		btnDongTimKiem.setToolTipText("Đóng giao diện tìm kiếm");
		btnDongTimKiem.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/close_window_25px.png")));
		btnDongTimKiem.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlButton.add(btnDongTimKiem);

		JPanel pnlContent = new JPanel();
		add(pnlContent);
		pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));

		JPanel pnlKhachHang = new JPanel();
		pnlKhachHang.setBackground(Color.WHITE);
		pnlKhachHang.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2),
				"Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlContent.add(pnlKhachHang);
		pnlKhachHang.setLayout(new BorderLayout(0, 0));

		JPanel pnlTimKiemVaThemMoi = new JPanel();
		pnlKhachHang.add(pnlTimKiemVaThemMoi, BorderLayout.NORTH);
		pnlTimKiemVaThemMoi.setLayout(new GridLayout(0, 2, 0, 0));

		pnlButtonTimKiemVaThem = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlButtonTimKiemVaThem.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlTimKiemVaThemMoi.add(pnlButtonTimKiemVaThem);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setToolTipText("Tìm kiếm khách hàng");
		pnlButtonTimKiemVaThem.add(btnTimKiem);
		btnTimKiem.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/search_32px.png")));
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnThemMoiKH = new JButton("Thêm khách hàng");
		btnThemMoiKH.setToolTipText("Thêm mới khách hàng");
		pnlButtonTimKiemVaThem.add(btnThemMoiKH);
		btnThemMoiKH.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/plus_32px.png")));
		btnThemMoiKH.setFont(new Font("Tahoma", Font.PLAIN, 18));

		pnlDongThemKH = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pnlDongThemKH.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		pnlTimKiemVaThemMoi.add(pnlDongThemKH);

		btnDongThemKH = new JButton("");
		btnDongThemKH.setToolTipText("Đóng giao diện thêm khách hàng");
		btnDongThemKH.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/cancel_32px.png")));
		btnDongThemKH.setFont(new Font("Arial", Font.PLAIN, 12));
		pnlDongThemKH.add(btnDongThemKH);

		pnlThongTinKH = new JPanel();
		pnlThongTinKH.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlKhachHang.add(pnlThongTinKH, BorderLayout.CENTER);

		JLabel lblHoTenKH = new JLabel("Họ và tên :");
		lblHoTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtHoTenKH = new JTextField();
		txtHoTenKH.setToolTipText("Nhập họ và tên");
		txtHoTenKH.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtHoTenKH.setColumns(10);

		JLabel lblDiaChiKH = new JLabel("Địa chỉ :");
		lblDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNgaySinh = new JLabel("Ngày sinh :");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblSoCmnd = new JLabel("Số CMND :");
		lblSoCmnd.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSoCMND = new JTextField();
		txtSoCMND.setToolTipText("Nhập số chứng minh nhân dân");
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
		btnLuuTTKhachHang.setToolTipText("Lưu thông tin khách hàng");
		btnLuuTTKhachHang.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/save_35px.png")));
		btnLuuTTKhachHang.setFont(new Font("Arial", Font.PLAIN, 23));

		dtcNgaySinh = new JDateChooser();
		dtcNgaySinh.setToolTipText("Chọn ngày sinh");
		dtcNgaySinh.getCalendarButton().setFont(new Font("Dialog", Font.PLAIN, 17));
		dtcNgaySinh.setFont(new Font("Arial", Font.PLAIN, 18));
		dtcNgaySinh.setLocale(new Locale("vi", "VN"));
		dtcNgaySinh.setDateFormatString("dd/MM/yyyy");

		lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Dialog", Font.PLAIN, 18));

		rdbNam = new JRadioButton("Nam");
		rdbNam.setSelected(true);
		buttonGroup.add(rdbNam);
		rdbNam.setFont(new Font("Dialog", Font.PLAIN, 18));

		rdbNu = new JRadioButton("Nữ");
		buttonGroup.add(rdbNu);
		rdbNu.setFont(new Font("Dialog", Font.PLAIN, 18));
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
												GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlThongTinKH.createSequentialGroup().addComponent(lblSdt)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(txtSdtKH,
												GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlThongTinKH.createSequentialGroup()
										.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(dtcNgaySinh,
												GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(
								gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
										.addComponent(lblSoCmnd, GroupLayout.PREFERRED_SIZE, 108,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGioiTinh))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlThongTinKH.createSequentialGroup().addComponent(rdbNam).addGap(49)
										.addComponent(rdbNu, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(txtSoCMND, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
						.addGap(22)
						.addComponent(btnLuuTTKhachHang, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addGap(22)));
		gl_pnlThongTinKH.setVerticalGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlThongTinKH.createSequentialGroup().addContainerGap().addGroup(gl_pnlThongTinKH
						.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLuuTTKhachHang, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlThongTinKH.createSequentialGroup()
								.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblHoTenKH)
												.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtHoTenKH, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(dtcNgaySinh, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE))
								.addGap(11)
								.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtSdtKH, GroupLayout.PREFERRED_SIZE, 31,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblGioiTinh).addComponent(rdbNam).addComponent(rdbNu,
														GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
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
		gl_pnlThongTinKH.linkSize(SwingConstants.VERTICAL, new Component[] { lblDiaChiKH, pnlDiaChi });
		gl_pnlThongTinKH.linkSize(SwingConstants.VERTICAL, new Component[] { lblHoTenKH, txtHoTenKH });
		gl_pnlThongTinKH.linkSize(SwingConstants.VERTICAL, new Component[] { lblSdt, txtSdtKH });
		gl_pnlThongTinKH.linkSize(SwingConstants.VERTICAL, new Component[] { lblNgaySinh, dtcNgaySinh });
		gl_pnlThongTinKH.linkSize(SwingConstants.VERTICAL, new Component[] { lblSoCmnd, txtSoCMND });

		cmbTinh = new JComboBox();
		cmbTinh.setVisible(false);

		lblDiaChi = new JLabel("New label");
		lblDiaChi.setVisible(false);
		lblDiaChi.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlDiaChi.add(lblDiaChi);
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

		btnThemDC = new JButton("...");
		btnThemDC.setToolTipText("Thêm địa chỉ");
		btnThemDC.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDiaChi.add(btnThemDC);

		btnLuuDC = new JButton("Lưu");
		btnLuuDC.setToolTipText("Lưu địa chỉ");
		btnLuuDC.setVisible(false);
		btnLuuDC.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pnlDiaChi.add(btnLuuDC);
		pnlThongTinKH.setLayout(gl_pnlThongTinKH);

		JPanel pnlTour = new JPanel();
		pnlTour.setPreferredSize(new Dimension(10, 800));
		pnlTour.setBackground(Color.WHITE);
		pnlTour.setBorder(
				new TitledBorder(null, "Danh s\u00E1ch tour", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlContent.add(pnlTour);
		pnlTour.setLayout(new BorderLayout(0, 0));

		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBorder(null);
		pnlTour.add(pnlChucNang, BorderLayout.NORTH);
		pnlChucNang.setLayout(new BoxLayout(pnlChucNang, BoxLayout.X_AXIS));

		JPanel pnlLuaChon = new JPanel();
		pnlLuaChon.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlChucNang.add(pnlLuaChon);

		txtTimKiemTheoTen = new JTextField();
		txtTimKiemTheoTen.setToolTipText("Nhập từ khoá tìm kiếm");
		txtTimKiemTheoTen.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtTimKiemTheoTen.setColumns(10);

		JLabel lblTimKiem = new JLabel("Tìm kiếm theo tên tour:");
		lblTimKiem.setFont(new Font("Dialog", Font.PLAIN, 18));

		cmbDiaDanh = new JComboBox();
		cmbDiaDanh.setToolTipText("Chọn địa danh có tour cần tìm");
		cmbDiaDanh.setEditable(true);
		cmbDiaDanh.setFont(new Font("Dialog", Font.PLAIN, 18));

		btnLocDiaDanh = new JButton("Lọc");
		btnLocDiaDanh.setToolTipText("Lọc tour theo địa danh");
		btnLocDiaDanh.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/filter_25px.png")));
		btnLocDiaDanh.setFont(new Font("Arial", Font.PLAIN, 18));

		JLabel lblDiaDanh = new JLabel("Chọn địa danh:");
		lblDiaDanh.setFont(new Font("Dialog", Font.PLAIN, 18));
		GroupLayout gl_pnlLuaChon = new GroupLayout(pnlLuaChon);
		gl_pnlLuaChon.setHorizontalGroup(gl_pnlLuaChon.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_pnlLuaChon.createSequentialGroup().addContainerGap()
						.addComponent(lblTimKiem).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(txtTimKiemTheoTen, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE).addComponent(lblDiaDanh)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(cmbDiaDanh, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
						.addGap(20).addComponent(btnLocDiaDanh).addContainerGap()));
		gl_pnlLuaChon.setVerticalGroup(gl_pnlLuaChon.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlLuaChon
				.createSequentialGroup().addContainerGap(14, Short.MAX_VALUE)
				.addGroup(gl_pnlLuaChon.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlLuaChon
						.createSequentialGroup()
						.addGroup(gl_pnlLuaChon.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDiaDanh, Alignment.TRAILING).addComponent(txtTimKiemTheoTen,
										Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGap(5))
						.addGroup(
								gl_pnlLuaChon.createSequentialGroup()
										.addComponent(cmbDiaDanh, GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addGap(5))
						.addComponent(btnLocDiaDanh, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlLuaChon.createSequentialGroup().addComponent(lblTimKiem).addGap(5)))
				.addGap(9)));
		gl_pnlLuaChon.linkSize(SwingConstants.VERTICAL,
				new Component[] { txtTimKiemTheoTen, lblTimKiem, cmbDiaDanh, lblDiaDanh });
		pnlLuaChon.setLayout(gl_pnlLuaChon);

		JPanel pnlLamMoi = new JPanel();
		pnlLamMoi.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlLamMoi.setPreferredSize(new Dimension(300, 10));
		pnlChucNang.add(pnlLamMoi);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setToolTipText("Làm mới bảng danh sách tour");
		btnLamMoi.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/lammoi.png")));
		btnLamMoi.setFont(new Font("Dialog", Font.PLAIN, 17));
		GroupLayout gl_pnlLamMoi = new GroupLayout(pnlLamMoi);
		gl_pnlLamMoi.setHorizontalGroup(gl_pnlLamMoi.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlLamMoi
				.createSequentialGroup().addContainerGap(39, Short.MAX_VALUE).addComponent(btnLamMoi).addGap(10)));
		gl_pnlLamMoi.setVerticalGroup(gl_pnlLamMoi.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlLamMoi.createSequentialGroup().addGap(15).addComponent(btnLamMoi)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnlLamMoi.setLayout(gl_pnlLamMoi);

		pnlDSTour = new JPanel();
		pnlTour.add(pnlDSTour, BorderLayout.CENTER);
		pnlDSTour.setLayout(new BorderLayout(0, 0));

		scrDSTour = new JScrollPane();
		scrDSTour.setToolTipText("Bảng danh sách tour");
		pnlDSTour.add(scrDSTour, BorderLayout.CENTER);

		pnlHuyChonTour = new JPanel();
		pnlHuyChonTour.setVisible(false);
		pnlHuyChonTour.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout fl_pnlHuyChonTour = (FlowLayout) pnlHuyChonTour.getLayout();
		fl_pnlHuyChonTour.setAlignment(FlowLayout.LEFT);
		pnlTour.add(pnlHuyChonTour, BorderLayout.SOUTH);

		btnHuyChonTour = new JButton("Huỷ chọn tour");
		btnHuyChonTour.setToolTipText("Huỷ chọn tour");
		btnHuyChonTour.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/close_window_25px.png")));
		btnHuyChonTour.setHorizontalAlignment(SwingConstants.LEFT);
		btnHuyChonTour.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnlHuyChonTour.add(btnHuyChonTour);

		JPanel pnlSouth = new JPanel();
		pnlContent.add(pnlSouth);
		pnlSouth.setLayout(new BorderLayout(0, 0));

		JPanel pnlCTTour = new JPanel();
		pnlSouth.add(pnlCTTour, BorderLayout.CENTER);
		pnlCTTour.setLayout(new BoxLayout(pnlCTTour, BoxLayout.X_AXIS));

		JPanel pnlNgayKhoiHanh = new JPanel();
		pnlNgayKhoiHanh.setBorder(null);
		pnlCTTour.add(pnlNgayKhoiHanh);
		pnlNgayKhoiHanh.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlDSNgayKhoiHanh = new JPanel();
		pnlDSNgayKhoiHanh.setBackground(new Color(236, 242, 242));
		pnlDSNgayKhoiHanh.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Danh s\u00E1ch ng\u00E0y \u0111i", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlNgayKhoiHanh.add(pnlDSNgayKhoiHanh);
		pnlDSNgayKhoiHanh.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSNgayDi = new JScrollPane();
		scrDSNgayDi.setToolTipText("Bảng danh sách ngày khởi hành theo tour");
		pnlDSNgayKhoiHanh.add(scrDSNgayDi, BorderLayout.CENTER);

		tblDSNgayDi = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblDSNgayDi.setFont(new Font("Arial", Font.PLAIN, 15));
		tblDSNgayDi.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M\u00E3 tour", "Ng\u00E0y kh\u1EDFi h\u00E0nh", "Số khách tối đa",
						"S\u1ED1 ng\u01B0\u1EDDi \u0111\u0103ng k\u00FD", "T\u00ECnh tr\u1EA1ng" }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] columnTypes = new Class[] { Object.class, Object.class, String.class, Object.class, Object.class,
					String.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblDSNgayDi.setRowHeight(25);
		tblDSNgayDi.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		scrDSNgayDi.setViewportView(tblDSNgayDi);
		tblDSNgayDi.setRowHeight(25);

		JPanel pnlKhachHangThamGia = new JPanel();
		pnlKhachHangThamGia.setBackground(new Color(236, 242, 242));
		pnlKhachHangThamGia.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch kh\u00E1ch h\u00E0ng tham gia",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlNgayKhoiHanh.add(pnlKhachHangThamGia);
		pnlKhachHangThamGia.setLayout(new BorderLayout(0, 0));

		pnlDSKhachHangTG = new JPanel();
		pnlKhachHangThamGia.add(pnlDSKhachHangTG, BorderLayout.CENTER);
		pnlDSKhachHangTG.setLayout(new BorderLayout(0, 0));

		scrDSKhachThamGia = new JScrollPane();
		scrDSKhachThamGia.setToolTipText("Danh sách khách hàng tham gia");
		pnlDSKhachHangTG.add(scrDSKhachThamGia, BorderLayout.CENTER);

		tblDSKhachThamGia = new JTable();

		pnlThemTTKHTG = new JPanel();
		pnlThemTTKHTG.setVisible(false);
		pnlDSKhachHangTG.add(pnlThemTTKHTG, BorderLayout.NORTH);
		FlowLayout fl_pnlThemTTKHTG = (FlowLayout) pnlThemTTKHTG.getLayout();
		fl_pnlThemTTKHTG.setAlignment(FlowLayout.LEFT);

		JLabel lblTen = new JLabel("Họ và tên:");
		lblTen.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlThemTTKHTG.add(lblTen);

		txtHoVaTenKHTG = new JTextField();
		txtHoVaTenKHTG.setPreferredSize(new Dimension(4, 30));
		txtHoVaTenKHTG.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlThemTTKHTG.add(txtHoVaTenKHTG);
		txtHoVaTenKHTG.setColumns(15);

		JLabel lblNgaySinhKHTG = new JLabel("Ngày sinh:");
		lblNgaySinhKHTG.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlThemTTKHTG.add(lblNgaySinhKHTG);

		dtcNgaySinhKHTG = new JDateChooser();
		dtcNgaySinhKHTG.setLocale(new Locale("vi", "VN"));
		dtcNgaySinhKHTG.setPreferredSize(new Dimension(150, 30));
		dtcNgaySinhKHTG.setDateFormatString("dd/MM/yyyy");
		pnlThemTTKHTG.add(dtcNgaySinhKHTG);

		btnThemKHTG = new JButton("Thêm");
		btnThemKHTG.setToolTipText("Thêm khách hàng tham gia");
		btnThemKHTG.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/add_25px.png")));
		btnThemKHTG.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlThemTTKHTG.add(btnThemKHTG);

		btnXoaKHTG = new JButton("Xoá");
		btnXoaKHTG.setVisible(false);
		btnXoaKHTG.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/delete_25px.png")));
		btnXoaKHTG.setFont(new Font("Dialog", Font.PLAIN, 18));
		pnlThemTTKHTG.add(btnXoaKHTG);

		JPanel pnlNorth = new JPanel();
		pnlKhachHangThamGia.add(pnlNorth, BorderLayout.SOUTH);
		pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.X_AXIS));

		chkThamGiaTourDK = new JCheckBox("Tham gia tour đăng ký");
		chkThamGiaTourDK.setToolTipText("Tích khi khách hàng đăng ký tour muốn tham gia");
		chkThamGiaTourDK.setVisible(false);
		pnlNorth.add(chkThamGiaTourDK);
		chkThamGiaTourDK.setHorizontalAlignment(SwingConstants.CENTER);
		chkThamGiaTourDK.setFont(new Font("Dialog", Font.PLAIN, 18));

		pnlTinhTrangTour = new JPanel();
		pnlNorth.add(pnlTinhTrangTour);

		lblTinhTrangKHTG = new JLabel("Chưa chọn ngày khởi hành");
		lblTinhTrangKHTG.setForeground(Color.BLACK);
		lblTinhTrangKHTG.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		pnlTinhTrangTour.add(lblTinhTrangKHTG);

		JPanel pnlDieuKhienND = new JPanel();
		pnlSouth.add(pnlDieuKhienND, BorderLayout.SOUTH);
		pnlDieuKhienND.setLayout(new GridLayout(0, 2, 0, 0));

		pnlButtonNgayKH = new JPanel();
		pnlButtonNgayKH.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlDieuKhienND.add(pnlButtonNgayKH);
		pnlButtonNgayKH.setLayout(new GridLayout(0, 2, 0, 0));

		pnlHuyChonNgayKH = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) pnlHuyChonNgayKH.getLayout();
		flowLayout_5.setVgap(10);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		pnlButtonNgayKH.add(pnlHuyChonNgayKH);

		btnHuyChonNgayKH = new JButton("Hủy chọn ngày khởi hành");
		btnHuyChonNgayKH.setToolTipText("Huỷ chọn ngày khởi hành");
		btnHuyChonNgayKH.setEnabled(false);
		btnHuyChonNgayKH.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/close_window_25px.png")));
		pnlHuyChonNgayKH.add(btnHuyChonNgayKH);
		btnHuyChonNgayKH.setFont(new Font("Arial", Font.BOLD, 15));

		pnlChonNgayKH = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) pnlChonNgayKH.getLayout();
		flowLayout_6.setVgap(10);
		flowLayout_6.setAlignment(FlowLayout.RIGHT);
		pnlButtonNgayKH.add(pnlChonNgayKH);

		btnThemKhachHangTG = new JButton("Thêm khách tham gia");
		btnThemKhachHangTG.setToolTipText("Thêm khách hàng tham gia");
		btnThemKhachHangTG.setEnabled(false);
		btnThemKhachHangTG.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/checked_25px.png")));
		btnThemKhachHangTG.setFont(new Font("Arial", Font.BOLD, 15));
		pnlChonNgayKH.add(btnThemKhachHangTG);

		pnlDangKyTour = new JPanel();
		pnlDangKyTour.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout fl_pnlDangKyTour = (FlowLayout) pnlDangKyTour.getLayout();
		fl_pnlDangKyTour.setVgap(10);
		pnlDieuKhienND.add(pnlDangKyTour);

		btnDangKyTour = new JButton("Đăng ký");
		btnDangKyTour.setToolTipText("Đăng ký tour");
		btnDangKyTour.setEnabled(false);
		btnDangKyTour.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/enter_25px.png")));
		btnDangKyTour.setFont(new Font("Dialog", Font.PLAIN, 20));
		pnlDangKyTour.add(btnDangKyTour);
		pnlThongTinKH.setVisible(false);

		tblDSTour = new JTable();
		tblDSTour.setEnabled(false);
		tblDSTour.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSNgayDi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel pnlTimKiemNGKH = new JPanel();
		pnlDSNgayKhoiHanh.add(pnlTimKiemNGKH, BorderLayout.NORTH);
		pnlTimKiemNGKH.setLayout(new BoxLayout(pnlTimKiemNGKH, BoxLayout.X_AXIS));

		panel = new JPanel();
		pnlTimKiemNGKH.add(panel);

		JLabel lblNgay = new JLabel("Từ ngày:");
		panel.add(lblNgay);
		lblNgay.setFont(new Font("Dialog", Font.PLAIN, 18));

		dtcTuNgay = new JDateChooser();
		panel.add(dtcTuNgay);
		dtcTuNgay.setLocale(new Locale("vi", "VN"));
		dtcTuNgay.setPreferredSize(new Dimension(130, 25));
		dtcTuNgay.setMinimumSize(new Dimension(200, 21));
		dtcTuNgay.setDateFormatString("dd/MM/yyyy");

		JLabel lblDenNgay = new JLabel("Đến ngày");
		panel.add(lblDenNgay);
		lblDenNgay.setFont(new Font("Dialog", Font.PLAIN, 18));

		dtcDenNgay = new JDateChooser();
		panel.add(dtcDenNgay);
		dtcDenNgay.getCalendarButton().setLocale(new Locale("vi", "VN"));
		dtcDenNgay.setPreferredSize(new Dimension(130, 25));
		dtcDenNgay.setDateFormatString("dd/MM/yyyy");
		dtcDenNgay.setMinimumSize(new Dimension(150, 21));

		btnTimKiemTheoNgay = new JButton("Tìm kiếm");
		btnTimKiemTheoNgay.setToolTipText("Tìm kiếm ngày khởi hành theo ngày");
		panel.add(btnTimKiemTheoNgay);
		btnTimKiemTheoNgay.setFont(new Font("Dialog", Font.PLAIN, 18));
		((JTextField) dtcDenNgay.getDateEditor().getUiComponent()).setEditable(false);
		((JTextField) dtcTuNgay.getDateEditor().getUiComponent()).setEditable(false);

		pnlLamMoiNgKH = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlLamMoiNgKH.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		pnlTimKiemNGKH.add(pnlLamMoiNgKH);

		btnLamMoiNgKH = new JButton("");
		btnLamMoiNgKH.setToolTipText("Làm mới bảng ngày khởi hành");
		pnlLamMoiNgKH.add(btnLamMoiNgKH);
		btnLamMoiNgKH.setIcon(new ImageIcon(PnlDangKyTour.class.getResource("/images/lammoi.png")));

		pnlThongTinNgayKH = new JPanel();
		pnlThongTinNgayKH.setVisible(false);
		pnlDSNgayKhoiHanh.add(pnlThongTinNgayKH, BorderLayout.SOUTH);
		pnlThongTinNgayKH.setLayout(new BoxLayout(pnlThongTinNgayKH, BoxLayout.X_AXIS));

		pnlTTNgayKH = new JPanel();
		pnlTTNgayKH.setBorder(new LineBorder(new Color(0, 153, 51), 2));
		FlowLayout flowLayout_3 = (FlowLayout) pnlTTNgayKH.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pnlThongTinNgayKH.add(pnlTTNgayKH);

		lblMaNgayKH = new JLabel("Mã:");
		pnlTTNgayKH.add(lblMaNgayKH);
		lblMaNgayKH.setFont(new Font("Arial", Font.BOLD, 18));

		lblMaNgayKHdb = new JLabel("<...>");
		pnlTTNgayKH.add(lblMaNgayKHdb);
		lblMaNgayKHdb.setFont(new Font("Dialog", Font.PLAIN, 18));

		lblNgayKH = new JLabel("Ngày khởi hành:");
		pnlTTNgayKH.add(lblNgayKH);
		lblNgayKH.setFont(new Font("Arial", Font.BOLD, 18));

		lblNgayKHdb = new JLabel("<...>");
		pnlTTNgayKH.add(lblNgayKHdb);
		lblNgayKHdb.setFont(new Font("Dialog", Font.PLAIN, 18));
		/*
		 * Khởi tạo các control
		 */
		dsKhachHangThamGia = new ArrayList<KhachHangThamGia>();
		tourControl = new TourControlImpl();
		phieuDangKyControl = new PhieuDangKyControlImpl();
		khachHangControl = new KhachHangControlImpl();

		dsTourDaDuyet = tourControl.layDsTourTheoYeuCau(3);

		// hiện danh sách tour
		hienDanhSachTour(tblDSTour, dsTourDaDuyet, scrDSTour);

		// hiên danh sách đia danh
		DefaultComboBoxModel<DiaDanh> cmbModel = (DefaultComboBoxModel<DiaDanh>) cmbDiaDanh.getModel();
		cmbModel.removeAllElements();

		List<DiaDanh> dsDiaDanh = tourControl.layDSDiaDanh();
		for (DiaDanh x : dsDiaDanh) {
			cmbModel.addElement(x);
		}
		cmbDiaDanh.setSelectedItem("");
		ganSuKien();

		// chỉnh sửa kích thước border
		TienIch.chinhKichThuocTitleTrenBorder(
				new JPanel[] { pnlKhachHang, pnlTour, pnlDSNgayKhoiHanh, pnlKhachHangThamGia }, "Arial", Font.PLAIN,
				18);
		TienIch.chinhKichThuocTable(tblDSNgayDi, tblDSNgayDi.getColumnModel().getTotalColumnWidth(), 2, 10, 15, 20, 20,
				20);

		((JTextField) dtcNgaySinh.getDateEditor().getUiComponent()).setEditable(false);
		((JTextField) dtcNgaySinhKHTG.getDateEditor().getUiComponent()).setEditable(false);

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
	 * Xử lý sự kiện cho control
	 */
	private void ganSuKien() {
		btnTimKiem.addActionListener(this);
		btnThemMoiKH.addActionListener(this);
		btnThemDC.addActionListener(this);
		btnLuuDC.addActionListener(this);
		btnHuyChonNgayKH.addActionListener(this);
		btnThemKhachHangTG.addActionListener(this);
		btnHuyChonTour.addActionListener(this);
		btnLamMoiNgKH.addActionListener(this);
		btnDongThemKH.addActionListener(this);

		btnLuuTTKhachHang.addActionListener(this);
		btnXacNhan.addActionListener(this);
		btnDongTimKiem.addActionListener(this);
		btnThemKHTG.addActionListener(this);
		btnDangKyTour.addActionListener(this);
		btnLamMoi.addActionListener(this);

		btnTimKiemTheoNgay.addActionListener(this);
		btnLocDiaDanh.addActionListener(this);
		btnXoaKHTG.addActionListener(this);

		cmbTinh.addActionListener(this);
		cmbHuyen.addActionListener(this);
		cmbXa.addActionListener(this);

		tblDSTour.getSelectionModel().addListSelectionListener(this);
		tblDSNgayDi.getSelectionModel().addListSelectionListener(this);
		tblDSKhachThamGia.getSelectionModel().addListSelectionListener(this);

		/*
		 * click check box
		 */
		chkThamGiaTourDK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (chkThamGiaTourDK.isSelected()) {
					int confirm = JOptionPane.showConfirmDialog(null, "Xác nhận khách hàng đăng ký tham gia tour ?",
							"Thông báo xác nhận", JOptionPane.YES_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						// nếu danh sách khách hàng tham gia chưa khởi tạo
						if (dsKhachHangThamGia == null) {
							dsKhachHangThamGia = new ArrayList<KhachHangThamGia>();
						}
						KhachHangThamGia khtg = new KhachHangThamGia();
						khtg.setHoTenKHTG(khachHang.getHoVaTen());
						khtg.setNgaySinh(khachHang.getNgaySinh());
						khtg.setDoTuoi(DoTuoi.NGUOILON);
						dsKhachHangThamGia.add(khtg);
						hienBangDSKhachTG(tblDSKhachThamGia, dsKhachHangThamGia, scrDSKhachThamGia);
						chkThamGiaTourDK.setEnabled(false);
					}

				}
			}
		});

		AutoCompleteDecorator.decorate(cmbDiaDanh);

		txtTimKiemTheoTen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String key = TienIch
						.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTimKiemTheoTen.getText().trim().toLowerCase());
				List<Tour> dsTourCanTim = new ArrayList<Tour>();
				for (Tour tour : dsTourDaDuyet) {
					String name = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(tour.getTenTour().toLowerCase());
					if (name.contains(key)) {
						dsTourCanTim.add(tour);
					}
				}
				hienDanhSachTour(tblDSTour, dsTourCanTim, scrDSTour);
			}
		});
	}

	/**
	 * Sự kiện click bảng
	 */
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel o = (ListSelectionModel) e.getSource();
		/*
		 * Chọn một hàng của danh sách tour
		 */
		if (o.equals(tblDSTour.getSelectionModel())) {
			int row = tblDSTour.getSelectedRow();
			if (row == -1)
				return;
			Tour tourChon = dsTourDaDuyet.get(row);
			dsNgayKhoiHanh = tourChon.getNgayKhoiHanh();
			hienDanhSachNgayKhoiHanh(dsNgayKhoiHanh);
			pnlHuyChonTour.setVisible(true);

			tblDSTour.setEnabled(false);

			TienIch.hienAnCacControl(false, btnTimKiem, btnThemMoiKH, btnLamMoi);

		}
		/*
		 * Chọn một hàng của ngày khởi hành
		 */
		else if (o.equals(tblDSNgayDi.getSelectionModel())) {
			int row = tblDSNgayDi.getSelectedRow();
			if (row == -1)
				return;
			ngayKhoiHanh = dsNgayKhoiHanh.get(row);
			TienIch.hienAnCacControl(true, btnThemKhachHangTG, btnHuyChonNgayKH);

			hienThongTinNgayKH(ngayKhoiHanh);
			String tinhTrang = tblDSNgayDi.getValueAt(row, 5).toString();
			chkThamGiaTourDK.setVisible(false);
			hienBangDSKhachTG(tblDSKhachThamGia, new ArrayList<KhachHangThamGia>(), scrDSKhachThamGia);
			pnlDSKhachHangTG.setVisible(false);
			pnlTinhTrangTour.setVisible(true);

			if (tinhTrang.equalsIgnoreCase(HangSo.DADANGKY)) {
				PhieuDangKy phieuDangKy = phieuDangKyControl.layPhieuDangKyTheoKHVaNgayKH(khachHang.getMaKH(),
						ngayKhoiHanh.getMaLT());
				btnThemKhachHangTG.setEnabled(false);

				// nếu khách hàng đăng ký tham gia tour
				if (phieuDangKy.isThamGiaTourDangKy()) {
					chkThamGiaTourDK.setSelected(true);
				}
				chkThamGiaTourDK.setEnabled(false);
				chkThamGiaTourDK.setVisible(true);
				pnlTinhTrangTour.setVisible(false);
				pnlDSKhachHangTG.setVisible(true);

				hienBangDSKhachTG(tblDSKhachThamGia, phieuDangKy.getKhachHangThamGias(), scrDSKhachThamGia);
			} else if (tinhTrang.equalsIgnoreCase(HangSo.CHUADANGKY)) {
				btnThemKhachHangTG.setEnabled(true);
				chkThamGiaTourDK.setSelected(false);
				chkThamGiaTourDK.setEnabled(true);
			} else if (tinhTrang.equalsIgnoreCase(HangSo.DADUSOLUONG)) {
				btnThemKhachHangTG.setEnabled(false);
				chkThamGiaTourDK.setSelected(false);
				chkThamGiaTourDK.setEnabled(false);
				pnlTinhTrangTour.setVisible(false);
				JOptionPane.showConfirmDialog(null, "Ngày khởi hành đã đủ số lượng người tham gia");
			}

		}
		/*
		 * click chọn bảng khách hàng tham gia
		 */
		else if (o.equals(tblDSKhachThamGia.getSelectionModel())) {
			btnXoaKHTG.setVisible(true);
			int row = tblDSKhachThamGia.getSelectedRow();
			if (row == -1)
				return;
			KhachHangThamGia khtg = dsKhachHangThamGia.get(row);
			txtHoVaTenKHTG.setText(khtg.getHoTenKHTG());
			dtcNgaySinh.setDate(khtg.getNgaySinh());
			btnXoaKHTG.setVisible(true);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			tblDSTour.setEnabled(false);
			if (pnlTimKiemKH == null) {
				pnlTimKiemKH = new PnlTimKiemKhachHang();
			}
			pnlTimKiemKhachHang.add(pnlTimKiemKH, BorderLayout.CENTER);
			pnlTimKiemKhachHang.setVisible(true);
			pnlThongTinKH.setVisible(false);
			btnThemMoiKH.setVisible(false);
			btnTimKiem.setEnabled(false);
		}
		/*
		 * Huỷ chọn tour
		 */
		else if (o.equals(btnHuyChonTour)) {
			huyChonTour();
			pnlHuyChonTour.setVisible(false);
		}
		/*
		 * Xác nhận sử dụng thông tin khách hàng
		 */
		else if (o.equals(btnXacNhan)) {
			khachHang = pnlTimKiemKH.getKhachHang();
			if (khachHang != null) {
				hienThongTinKhachHang(khachHang);
				tblDSTour.setEnabled(true);
				TienIch.hienAnCacControl(false, txtHoTenKH, txtSdtKH, txtSoCMND, dtcNgaySinh);
				pnlTimKiemKhachHang.setVisible(false);
				btnThemMoiKH.setVisible(true);
				btnLuuTTKhachHang.setVisible(false);
				pnlTimKiemKH.xoaTrangThongTinKH();
				btnTimKiem.setEnabled(true);

			} else {
				JOptionPane.showConfirmDialog(pnlTimKiemKH, "Chưa tìm thấy thông tin khách hàng");
			}
		}

		/*
		 * Đóng màn hình tìm kiếm khách hàng
		 */
		else if (o.equals(btnDongTimKiem)) {
			btnThemMoiKH.setVisible(true);
			btnLuuTTKhachHang.setVisible(true);
			pnlTimKiemKhachHang.setVisible(false);
			tblDSTour.setEnabled(false);

			pnlTimKiemKH.xoaTrangThongTinKH();

			btnTimKiem.setEnabled(true);

		}
		/*
		 * Đóng màn hình thêm khách hàng
		 */
		else if (o.equals(btnDongThemKH)) {
			if (pnlThongTinKH.isVisible() == true) {
				int confirm = JOptionPane.showConfirmDialog(null,
						"<html>Đăng tiến hành nhập thông tin khách hàng.<br>Muốn kết thúc thêm ?<html>", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					xoaTrangThongTinKhachHang();
					pnlThongTinKH.setVisible(false);
				}
			}
		}
		/*
		 * Nút thêm mới khách hàng
		 */
		else if (o.equals(btnThemMoiKH)) {
			xoaTrangThongTinKhachHang();
		}
		/*
		 * Thêm địa chỉ cho khách hàng
		 */
		else if (o.equals(btnThemDC)) {
			anThanhNhapDiaChi();
			btnThemDC.setVisible(false);
			lblDiaChi.setText("");
			cmbTinh.setVisible(true);
			lstProvices = TienIch.layDiaLyHanhChinh();
			hienDiaDiem(lstProvices, cmbTinh, "Tỉnh/Thành phố");
		} else if (o.equals(cmbTinh)) {
			if (cmbHuyen.getItemCount() > 1 && cmbXa.getItemCount() > 1) {
				cmbXa.setModel(new DefaultComboBoxModel<Ward>());
				cmbHuyen.setModel(new DefaultComboBoxModel<District>());
			}
			if (cmbTinh.getSelectedIndex() > 0) {
				List<District> districts = lstProvices.get(cmbTinh.getSelectedIndex() - 1).getDistricts();
				hienDiaDiem(districts, cmbHuyen, "Huyện/Quận");
				cmbHuyen.setVisible(true);
			}

		} else if (o.equals(cmbHuyen)) {
			if (cmbXa.getItemCount() > 1) {
				cmbXa.setModel(new DefaultComboBoxModel<Ward>());
			}
			if (cmbHuyen.getSelectedIndex() > 0) {
				List<Ward> wards = lstProvices.get(cmbTinh.getSelectedIndex() - 1).getDistricts()
						.get(cmbHuyen.getSelectedIndex() - 1).getWards();
				hienDiaDiem(wards, cmbXa, "Xã/Phường");
				cmbXa.setVisible(true);
			}
		} else if (o.equals(cmbXa)) {
			if (cmbXa.getSelectedIndex() > 0) {
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
			btnLuuDC.setVisible(false);
			btnThemDC.setVisible(true);

		}
		/*
		 * Lưu thông tin khách hàng
		 */
		else if (o.equals(btnLuuTTKhachHang)) {

			// nếu nhập liệu đúng cú pháp
			if (kiemTraNhapLieu()) {
				// khởi tạo đối tượng khách hàng
				KhachHang kh = new KhachHang();
				kh.setHoVaTen(txtHoTenKH.getText());
				kh.setSoCMND(txtSoCMND.getText());
				kh.setNgaySinh(new Date(dtcNgaySinh.getDate().getTime()));
				kh.setSoDienThoai(txtSdtKH.getText());
				kh.setDiaChi(new DiaChi(cmbXa.getSelectedItem().toString(), cmbHuyen.getSelectedItem().toString(),
						cmbTinh.getSelectedItem().toString()));
				kh.setGioiTinh(rdbNam.isSelected() ? true : false);

				// thông báo xác nhận lưu thông tin khách hàng
				int confirm = JOptionPane.showConfirmDialog(null, "Lưu thông tin khách hàng " + kh.getHoVaTen() + " ?",
						"Xác nhận lưu khách hàng", JOptionPane.YES_NO_OPTION);

				// chọn yes
				if (confirm == JOptionPane.YES_OPTION) {
					KhachHang khThem = khachHangControl.themKhachHang(kh);
					if (khThem != null) {
						JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
						khachHang = khThem;
						TienIch.hienAnCacControl(false, txtHoTenKH, txtSdtKH, txtSoCMND, dtcNgaySinh);
						btnLuuDC.setVisible(false);
						btnThemDC.setVisible(false);
						tblDSTour.setEnabled(true);
						btnLuuTTKhachHang.setVisible(false);

					}
				}
			}

		}
		/*
		 * Chọn ngày khởi hành để đăng ký
		 */
		else if (o.equals(btnThemKhachHangTG)) {
			btnThemKHTG.setEnabled(true);
			btnDangKyTour.setEnabled(true);
			if (dsKhachHangThamGia == null) {
				dsKhachHangThamGia = new ArrayList<KhachHangThamGia>();
			}
			hienBangDSKhachTG(tblDSKhachThamGia, dsKhachHangThamGia, scrDSKhachThamGia);
			lblTinhTrangKHTG.setVisible(false);
			pnlDSKhachHangTG.setVisible(true);
			pnlThemTTKHTG.setVisible(true);

			chkThamGiaTourDK.setEnabled(true);

			chkThamGiaTourDK.setVisible(true);

			tblDSNgayDi.setEnabled(false);
			
			

		}
		/*
		 * Xoá khách hàng tham gia trong danh sách
		 */
		else if (o.equals(btnXoaKHTG)) {
			int row = tblDSKhachThamGia.getSelectedRow();
			if (row == -1)
				return;
			KhachHangThamGia khtg = dsKhachHangThamGia.get(row);

			// Thông báo xác nhận xoá khách hàng tham gia
			int confirm = JOptionPane.showConfirmDialog(null, "Xoá khách hàng tham gia vừa chọn khỏi danh sách",
					"Xác nhận", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				dsKhachHangThamGia.remove(khtg);
				hienBangDSKhachTG(tblDSKhachThamGia, dsKhachHangThamGia, scrDSKhachThamGia);
				btnXoaKHTG.setVisible(false);
				tblDSKhachThamGia.clearSelection();
				xoaTrangThongTinKHTG();

			}
		}

		/*
		 * Huỷ chọn ngày khởi hành
		 */
		else if (o.equals(btnHuyChonNgayKH)) {
			dsKhachHangThamGia = new ArrayList<KhachHangThamGia>();
			huyThemKhachHangThamGia(dsKhachHangThamGia);
			huyChonNgayKhoiHanh();

		}

		/*
		 * Nút thêm khách hàng tham gia vào danh sách
		 */
		else if (o.equals(btnThemKHTG)) {
			if (ktTTNhapKhachHangTG()) {
				if (dsKhachHangThamGia == null) {
					dsKhachHangThamGia = new ArrayList<KhachHangThamGia>();
				}
				KhachHangThamGia khachHangThamGia = new KhachHangThamGia();
				khachHangThamGia.setHoTenKHTG(txtHoVaTenKHTG.getText());
				khachHangThamGia.setNgaySinh(new Date(dtcNgaySinhKHTG.getDate().getTime()));
				if (khachHangThamGia.tinhTuoiKhachHang() > HangSo.NGUOILON) {
					khachHangThamGia.setDoTuoi(DoTuoi.NGUOILON);
				} else {
					khachHangThamGia.setDoTuoi(DoTuoi.TREEM);
				}
				dsKhachHangThamGia.add(khachHangThamGia);
				hienBangDSKhachTG(tblDSKhachThamGia, dsKhachHangThamGia, scrDSKhachThamGia);
				xoaTrangThongTinKHTG();
			}
		}
		/*
		 * Nút đăng ký tour
		 */
		else if (o.equals(btnDangKyTour)) {
			// lấy vị trí hàng được chọn trong bảng danh sách ngày khởi hành
			int row = tblDSNgayDi.getSelectedRow();

			// Khởi tạo phiếu đăng ký
			PhieuDangKy phieuDangKy = new PhieuDangKy();
			phieuDangKy.setKh(khachHang);
			phieuDangKy.setNgayKhoiHanh(ngayKhoiHanh);
			phieuDangKy.setNv(nhanvien);
			phieuDangKy.setNgayTaoPhieu(new Date(System.currentTimeMillis()));

			// nếu khách hàng đăng ký muốn tham gia
			if (chkThamGiaTourDK.isSelected()) {
				phieuDangKy.setThamGiaTourDangKy(true);
			}

			// Thêm khách hàng tham gia vào danh sách
			phieuDangKy.setKhachHangThamGias(dsKhachHangThamGia);

			int soLuongKhachToiDa = ngayKhoiHanh.getSoKhachToiDa();
			int soLuongKhachTang = dsKhachHangThamGia.size();
			int soLuongKhachHangDaThamGia = soLuongKhachTang + ngayKhoiHanh.getSoKhachDaDangKy();

			// Nếu sô lượng khách tham gia > số lượng khách tối đa
			if (soLuongKhachHangDaThamGia > soLuongKhachToiDa) {
				JOptionPane.showMessageDialog(null, "Số lượng khách tham gia vượt quá Số lượng khách tối đa",
						"Thông báo", JOptionPane.ERROR_MESSAGE);

			} else {
				if (dsKhachHangThamGia.size() == 0) {
					JOptionPane.showMessageDialog(null, "Chưa có thông tin khách hành tham gia", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// nếu số lượng khách tham gia đã đến tối đa
					if (soLuongKhachHangDaThamGia == soLuongKhachToiDa) {
						// cập nhật trạng thái đủ số lượng cho ngày khởi hành
						phieuDangKy.getNgayKhoiHanh().setDaDuSoLuong(true);
					}

					// cập nhật số lượng khách tham gia tour cho ngày khởi hành
					phieuDangKy.getNgayKhoiHanh().setSoKhachDaDangKy(soLuongKhachHangDaThamGia);

					// Thông báo xác nhận đăng ký tour
					int confirm = JOptionPane.showConfirmDialog(null,
							"Xác nhận đăng ký tour " + phieuDangKy.getNgayKhoiHanh().getTour().getTenTour() + " ?",
							"Thông báo xác nhận", JOptionPane.YES_NO_OPTION);

					// chọn yes
					if (confirm == JOptionPane.YES_OPTION) {
						PhieuDangKy phieuDangKyTour = phieuDangKyControl.themPhieuDangKy(phieuDangKy);
						if (phieuDangKyTour != null) {
							xoaTrangThongTinKHTG();
							pnlThemTTKHTG.setVisible(false);
							pnlDSKhachHangTG.setEnabled(false);
							chkThamGiaTourDK.setEnabled(false);
							btnThemKHTG.setEnabled(false);

							// hiện lại danh sách ngày khởi hành
							dsNgayKhoiHanh = tourControl
									.layDSNgayKhoiHanhTheoTour(phieuDangKy.getNgayKhoiHanh().getTour().getMaTour());
							hienDanhSachNgayKhoiHanh(dsNgayKhoiHanh);

							// Khởi tạo phiếu thu
							PhieuThuChi phieuThuChi = new PhieuThuChi();
							phieuThuChi.setPdk(phieuDangKy);

							// Hiện giao diện phiếu thu
							DlgPhieuThu dlgPhieuThu = new DlgPhieuThu(phieuThuChi, phieuDangKy.getKhachHangThamGias());
							dlgPhieuThu.setVisible(true);
							if (dlgPhieuThu.isDisplayable() == false) {
								tblDSNgayDi.setRowSelectionInterval(row, row);
								btnDangKyTour.setEnabled(false);
							}

						}
					}
				}
			}

		}
		/*
		 * Nút Lọc tour theo địa danh
		 */
		else if (o.equals(btnLocDiaDanh)) {
			try {
				DiaDanh diaDanh = (DiaDanh) cmbDiaDanh.getEditor().getItem();
				List<Tour> dsTourCanTim = new ArrayList<Tour>();
				for (Tour tour : dsTourDaDuyet) {
					if (tour.getDiaDanh().getTenDiaDanh().equalsIgnoreCase(diaDanh.getTenDiaDanh())) {
						dsTourCanTim.add(tour);
					}
				}
				if (dsTourCanTim.size() == 0) {
					dsTourCanTim = dsTourDaDuyet;
				}
				hienDanhSachTour(tblDSTour, dsTourCanTim, scrDSTour);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Địa danh vừa chọn không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
		/*
		 * Tìm kiếm ngày khởi hành theo ngày
		 */
		else if (o.equals(btnTimKiemTheoNgay)) {
			if (ktNgayKhoiHanh()) {
				String maTour = tblDSTour.getValueAt(tblDSTour.getSelectedRow(), 1).toString();
				List<NgayKhoiHanh> dsNgayKhoiHanhCanTim = tourControl.layDSNgayKhoiHanhTheoNgayKhoiHanh(maTour,
						dtcTuNgay.getDate(), dtcDenNgay.getDate());
				if (dsNgayKhoiHanhCanTim.size() != 0) {
					hienDanhSachNgayKhoiHanh(dsNgayKhoiHanhCanTim);
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy ngày khởi hành cần tìm", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					dsNgayKhoiHanhCanTim = dsNgayKhoiHanh;
					hienDanhSachNgayKhoiHanh(dsNgayKhoiHanhCanTim);
					//
				}
			}

		}

		/*
		 * Nút làm mới danh sách tour
		 */
		else if (o.equals(btnLamMoi)) {
			txtTimKiemTheoTen.setText("");
			cmbDiaDanh.setSelectedItem(null);
			dsTourDaDuyet = tourControl.layDsTourTheoYeuCau(3);
			hienDanhSachTour(tblDSTour, dsTourDaDuyet, scrDSTour);
		}

		/*
		 * Nút làm mới danh sách ngày khởi hành
		 */
		else if (o.equals(btnLamMoiNgKH)) {
			dtcTuNgay.setDate(null);
			dtcDenNgay.setDate(null);
			hienDanhSachNgayKhoiHanh(dsNgayKhoiHanh);
		}

	}

	/**
	 * Xoá trắng thông tin khách hàng
	 */
	private void xoaTrangThongTinKHTG() {
		txtHoVaTenKHTG.setText("");
		dtcNgaySinhKHTG.setDate(null);

	}

	/**
	 * Hiện thông tin ngày khởi hành
	 */
	private void hienThongTinNgayKH(NgayKhoiHanh ngayKhoiHanh) {
		pnlThongTinNgayKH.setVisible(true);
		lblMaNgayKHdb.setText(ngayKhoiHanh.getMaLT());
		lblNgayKHdb.setText(new SimpleDateFormat("dd/MM/yyyy").format(ngayKhoiHanh.getNgayKhoiHanh()));
	}

	/**
	 * Huỷ chọn tour
	 */
	private void huyChonTour() {
		TienIch.hienAnCacControl(true, btnTimKiem, btnThemMoiKH, btnLamMoi);
		tblDSTour.setEnabled(true);
		tblDSTour.clearSelection();

		huyChonNgayKhoiHanh();
		pnlHuyChonTour.setVisible(false);
		dsNgayKhoiHanh = new ArrayList<NgayKhoiHanh>();
		hienDanhSachNgayKhoiHanh(dsNgayKhoiHanh);
	}

	/**
	 * Huỷ chọn ngày khởi hành
	 */
	private void huyChonNgayKhoiHanh() {
		pnlHuyChonTour.setVisible(true);
		tblDSNgayDi.clearSelection();

		tblDSNgayDi.setEnabled(true);
		chkThamGiaTourDK.setSelected(false);

		lblNgayKHdb.setText("<...>");
		lblMaNgayKHdb.setText("<...>");
		pnlThongTinNgayKH.setVisible(false);

		xoaTrangThongTinKHTG();
		chkThamGiaTourDK.setSelected(false);
		chkThamGiaTourDK.setVisible(false);
		pnlTinhTrangTour.setVisible(false);

		pnlDSKhachHangTG.setVisible(false);
		TienIch.hienAnCacControl(false, btnHuyChonNgayKH, btnThemKhachHangTG);

	}

	/**
	 * Làm mới bảng thông tin khách hàng tham gia tour
	 * 
	 * @param khtgs: danh sách khách hàng tham gia tour
	 */
	private void huyThemKhachHangThamGia(List<KhachHangThamGia> khtgs) {

		pnlThemTTKHTG.setVisible(false);
		lblTinhTrangKHTG.setVisible(true);
		lblTinhTrangKHTG.setText("Chưa chọn ngày khởi hành");
		pnlDSKhachHangTG.setVisible(false);
		hienBangDSKhachTG(tblDSKhachThamGia, khtgs, scrDSKhachThamGia);
	}

	/**
	 * Hiện danh sách ngày khởi hành theo tour
	 * 
	 * @param lstNgayKH: danh sách ngày khởi hành
	 */
	private void hienDanhSachNgayKhoiHanh(List<NgayKhoiHanh> lstNgayKH) {
		DefaultTableModel tblModel = (DefaultTableModel) tblDSNgayDi.getModel();
		int i = 1;
		tblModel.setRowCount(0);
		String tinhTrang = HangSo.CHUADANGKY;
		for (NgayKhoiHanh x : lstNgayKH) {
			PhieuDangKy phieuDangKy = phieuDangKyControl.layPhieuDangKyTheoKHVaNgayKH(khachHang.getMaKH(), x.getMaLT());
			if (phieuDangKy != null) {
				tinhTrang = HangSo.DADANGKY;
			} else if (x.isDaDuSoLuong()) {
				tinhTrang = HangSo.DADUSOLUONG;
			} else {
				tinhTrang = HangSo.CHUADANGKY;
			}
			tblModel.addRow(new Object[] { i, x.getMaLT(), x.getNgayKhoiHanh(), x.getSoKhachToiDa(),
					x.getSoKhachDaDangKy(), tinhTrang });
			i++;

		}

		if (lstNgayKH.size() != 0) {
			tblDSNgayDi.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderrer());
			tblDSNgayDi.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderrer());
			tblDSNgayDi.getColumnModel().getColumn(2).setCellRenderer(new MyDateRenderer());
			tblDSNgayDi.getColumnModel().getColumn(3).setCellRenderer(new CenterRenderrer());
			tblDSNgayDi.getColumnModel().getColumn(4).setCellRenderer(new CenterRenderrer());
			tblDSNgayDi.getColumnModel().getColumn(5).setCellRenderer(new CenterRenderrer());
		}

	}

	/**
	 * Hiện danh sách khách hàng tham gia tour
	 * 
	 * 
	 * @param tbl: bảng chứa danh sách khách hàng tham gia
	 * @param ds:  danh sách khách hàng tham gia
	 * @param src: Thanh cuộn
	 */
	private void hienBangDSKhachTG(JTable tbl, List<KhachHangThamGia> ds, JScrollPane src) {
		DSKhachHangTGTableModel dsKhachHangTGTableModel = new DSKhachHangTGTableModel(ds);
		tbl.setModel(dsKhachHangTGTableModel);
		src.setViewportView(tbl);
		tblDSKhachThamGia.setRowHeight(25);
		TienIch.chinhKichThuocTable(tblDSKhachThamGia, tblDSKhachThamGia.getColumnModel().getTotalColumnWidth(), 2, 20,
				15, 10, 20);

		if (ds.size() != 0) {
			tblDSNgayDi.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderrer());
			tblDSNgayDi.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderrer());
			tblDSNgayDi.getColumnModel().getColumn(2).setCellRenderer(new MyDateRenderer());
			tblDSNgayDi.getColumnModel().getColumn(3).setCellRenderer(new CenterRenderrer());
			tblDSNgayDi.getColumnModel().getColumn(4).setCellRenderer(new CenterRenderrer());
		}

	}

	/**
	 * Xoá trắng các trường thông tin khách hàng
	 */
	private void xoaTrangThongTinKhachHang() {
		pnlThongTinKH.setVisible(true);
		pnlTimKiemKhachHang.setVisible(false);
		btnThemDC.setVisible(true);
		btnLuuTTKhachHang.setVisible(true);
		TienIch.xoaTrangCacJTextField(txtHoTenKH, txtSdtKH, txtSoCMND);

		lblDiaChi.setText("");

		dtcNgaySinh.setDate(null);
		tblDSTour.setEnabled(false);
		TienIch.hienAnCacControl(true, txtHoTenKH, txtSdtKH, txtSoCMND, dtcNgaySinh);
		txtHoTenKH.requestFocus();

		anThanhNhapDiaChi();

	}

	/**
	 * Ẩn thanh nhập địa chỉ
	 */
	private void anThanhNhapDiaChi() {
		cmbTinh.setVisible(false);
		cmbXa.setVisible(false);
		cmbHuyen.setVisible(false);

	}

	/**
	 * Hiện thông tin khách hàng
	 */
	private void hienThongTinKhachHang(KhachHang kh) {
		pnlThongTinKH.setVisible(true);
		txtHoTenKH.setText(kh.getHoVaTen());
		txtSdtKH.setText(kh.getSoDienThoai());
		txtSoCMND.setText(kh.getSoCMND());
		dtcNgaySinh.setDate(kh.getNgaySinh());
		lblDiaChi.setVisible(true);
		lblDiaChi.setText(kh.getDiaChi().toString());
		btnThemDC.setVisible(false);

		if (kh.isGioiTinh()) {
			rdbNam.setSelected(true);
		} else {
			rdbNu.setSelected(true);
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

	/**
	 * Hàm kiếm tra dữ liệu nhập vào từ bàn phím
	 * 
	 * @return true: nếu dữ liệu đúng / false: nếu không đúng
	 */
	private boolean kiemTraNhapLieu() {

		String tenKH = txtHoTenKH.getText().trim();
		String soCM = txtSoCMND.getText().trim();
		String sdt = txtSdtKH.getText().trim();
		String ngSinh = ((JTextField) dtcNgaySinh.getDateEditor().getUiComponent()).getText().trim();
		String dc = lblDiaChi.getText();

		if (tenKH.length() == 0 || soCM.length() == 0 || sdt.length() == 0 || ngSinh.length() == 0
				|| dc.length() == 0) {
			if (tenKH.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập tên Khách hàng", "Lỗi nhập liệu",
						JOptionPane.ERROR_MESSAGE);
				txtHoTenKH.requestFocus();
				return false;
			}
			if (sdt.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại", "Lỗi nhập liệu",
						JOptionPane.ERROR_MESSAGE);
				txtSdtKH.requestFocus();
				return false;

			}
			if (soCM.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập số CMND", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
				txtSoCMND.requestFocus();
				return false;
			}

			if (ngSinh.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập ngày sinh", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
				dtcNgaySinh.requestFocus();
				return false;
			}
			if (dc.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return false;
		}
		/*
		 * Kiểm tra số điện thoại khách hàng
		 */
		if (khachHangControl.layTTKhachHangTheoYeuCau(2, sdt) != null) {
			JOptionPane.showMessageDialog(this, "Số điện thoại vừa nhập đã tồn tại", "Lỗi nhập liệu",
					JOptionPane.ERROR_MESSAGE);
			txtSdtKH.requestFocus();
			return false;
		}

		/*
		 * Kiểm tra số chứng minh nhân dân khách hàng
		 */
		if (khachHangControl.layTTKhachHangTheoYeuCau(3, soCM) != null) {
			JOptionPane.showMessageDialog(this, "Số CMND(căn cước) vừa nhập đã tồn tại", "Lỗi nhập liệu",
					JOptionPane.ERROR_MESSAGE);
			txtSoCMND.requestFocus();
			return false;
		}

		////////////////////////////////
		/*
		 * Kiểm tra tuổi từ 18 trở lên
		 */
		LocalDate dt = new Date(dtcNgaySinh.getDate().getTime()).toLocalDate();
		Period period = Period.between(dt, LocalDate.now());

		if (period.getYears() < 18) {
			JOptionPane.showMessageDialog(this, "Khách hàng phải đủ 18 tuổi mới được đăng ký");
			dtcNgaySinh.requestFocus();
			return false;
		}
		///////////////////////////////

		/*
		 * Kiểm tra số điện thoại
		 */
		if (!(sdt.matches("(\\+84|0)[0-9]{9}"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu là 0 hoặc +84 tiếp sau là 10 ký số");
			txtSdtKH.requestFocus();
			return false;
		}

		//////////////////////////////

		/*
		 * Số chứng minh nhân dân (căn cước)
		 */

		if (soCM.matches("[0-9]{9}|{12}") == false) {
			JOptionPane.showMessageDialog(this, "CMND Gồm 9 hoặc 12 ký tự số");
			txtSoCMND.requestFocus();
			return false;
		}

		return true;

	}

	/**
	 * Kiem tra tim kiem ngay khoi hanh
	 * 
	 * @return true hoặc false
	 */
	private boolean ktNgayKhoiHanh() {
		if (dtcTuNgay.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Chưa chọn từ ngày !");
			return false;
		}
		if (dtcDenNgay.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Chưa chọn đến ngày !");
			return false;
		}
		LocalDate tuNgay = new Date(dtcTuNgay.getDate().getTime()).toLocalDate();
		LocalDate denNgay = new Date(dtcDenNgay.getDate().getTime()).toLocalDate();
		if (denNgay.isBefore(tuNgay)) {
			JOptionPane.showMessageDialog(this, "Đến ngày không thể trước từ ngày");
			dtcDenNgay.requestFocusInWindow();
			return false;
		}
		return true;

	}

	private boolean ktTTNhapKhachHangTG() {
		if (txtHoVaTenKHTG.getText().length() <= 0) {
			JOptionPane.showMessageDialog(this, "Tên khách hàng tham gia không được để trống !");
			return false;
		}
		if (dtcNgaySinhKHTG.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Ngày sinh khách hàng tham gia không được để trống");
			return false;
		}
		if (dtcNgaySinhKHTG.getDate().equals(LocalDate.now())) {
			JOptionPane.showMessageDialog(this, "Ngày sinh khách hàng tham gia không thể là ngày hiện tại");
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
			value = sdf.format(((java.util.Date) value).getTime());
			super.setValue(value);
		}

	}
}
