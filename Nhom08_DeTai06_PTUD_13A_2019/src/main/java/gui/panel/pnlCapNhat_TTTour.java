package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JDateChooser;

import control.ITourControl;
import control.impl.TourControlImpl;
import entities.DiaDanh;
import entities.NhanVien;
import entities.Tour;
import gui.frmMain;
import gui.dialog.dlgXemAnh;
import utils.HintTextFieldUI;
import utils.TienIch;

/**
 * pnlCapNhat_TTTour.java
 * 
 * @author Minh Chien, Thanh Tri <br>
 *         Ngày tạo: 08/11/2019
 *
 */
public class pnlCapNhat_TTTour extends JPanel implements ActionListener, PropertyChangeListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	private JTable tblTour;
	private JPanel pnlContent;
	private JPanel pnlThongTin;
	private JTextField txtTenTour;

	private JFormattedTextField txtGiaNgLon;
	private JFormattedTextField txtGTreEm;

	private JTextField txtMaTour;
	private JTextField txtDgDan;
	private JSpinner spnSoNgTD;
	private JButton btnFile;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnThem;
	private JPanel pnlTblTour;
	private JDateChooser dtcTuNgay;
	private JDateChooser dtcDenNgay;

	private DefaultTableModel tblModel;

	private List<DiaDanh> diaDanhs;
	private JTextPane txpMoTa;
	private static List<Tour> dsTour;
	private List<DiaDanh> lstDiaDanhChon;
	private JComboBox<DiaDanh> cmbDiaDanh;
	private DefaultComboBoxModel<DiaDanh> cmbModel;

	private double donGiaNguoiLon = 0;
	private double donGiaTreEm = 0;
	private static int soNgToiDa = 0;
	private byte[] duLieuAnh;

	private JButton btnXemAnh;
	private JCheckBox chkThemHinhAnh;
	private JButton btnHuy;
	private NhanVien nv;
	private JButton btnThoat;
	private JTextField txtTimKiem;
	private JComboBox<String> cmbLoaiTK;

	private ITourControl tourControl;

	private NhanVien nhanVien;

	/**
	 * Constructor khởi tạo giao diện cập nhật tour
	 */
	public pnlCapNhat_TTTour(NhanVien nv) {
		this.nhanVien = nv;

		setBackground(Color.WHITE);
		setSize(1596, 785);
		setLayout(new BorderLayout(0, 0));

		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(new Color(0, 191, 255));
		add(pnlTieuDe, BorderLayout.NORTH);
		pnlTieuDe.setLayout(new GridLayout(0, 3, 0, 0));

		JPanel pnlThoat = new JPanel();
		pnlThoat.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlThoat.setBackground(new Color(0, 191, 255));
		FlowLayout flowLayout = (FlowLayout) pnlThoat.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlTieuDe.add(pnlThoat);

		btnThoat = new JButton("");
		btnThoat.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnThoat.setFocusable(false);
		btnThoat.setBackground(new Color(0, 191, 255));
		btnThoat.setIcon(new ImageIcon(pnlCapNhat_TTTour.class.getResource("/images/turn_left.png")));
		pnlThoat.add(btnThoat);

		JPanel pnlTenTieuDe = new JPanel();
		pnlTenTieuDe.setBackground(new Color(0, 191, 255));
		pnlTieuDe.add(pnlTenTieuDe);
		pnlTenTieuDe.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("CẬP NHẬT TOUR");
		lblNewLabel.setIcon(new ImageIcon(pnlCapNhat_TTTour.class.getResource("/images/capnhattour.png")));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pnlTenTieuDe.add(lblNewLabel);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBackground(new Color(0, 191, 255));
		pnlTieuDe.add(pnlTimKiem);
		pnlTimKiem.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));

		txtTimKiem = new JTextField();
		txtTimKiem.setBorder(new LineBorder(new Color(171, 173, 179), 2));
		txtTimKiem.setPreferredSize(new Dimension(6, 35));
		txtTimKiem.setHorizontalAlignment(SwingConstants.LEFT);
		txtTimKiem.setEditable(false);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTimKiem.add(txtTimKiem);
		txtTimKiem.setColumns(20);

		cmbLoaiTK = new JComboBox<String>();
		cmbLoaiTK.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Tìm kiếm", "Mã tour", "Tên tour", "Địa danh" }));
		cmbLoaiTK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTimKiem.add(cmbLoaiTK);

		pnlContent = new JPanel();
		pnlContent.setBackground(Color.WHITE);
		add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new BorderLayout(0, 0));

		pnlThongTin = new JPanel();
		pnlThongTin.setBackground(Color.WHITE);
		pnlContent.add(pnlThongTin, BorderLayout.CENTER);
		pnlThongTin.setLayout(new BoxLayout(pnlThongTin, BoxLayout.Y_AXIS));

		pnlTblTour = new JPanel();
		pnlTblTour.setBackground(Color.WHITE);
		pnlTblTour.setBorder(new TitledBorder(new LineBorder(new Color(0, 204, 102), 3),
				"Danh s\u00E1ch tour du l\u1ECBch", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlTblTour.setPreferredSize(new Dimension(10, 400));
		pnlThongTin.add(pnlTblTour);

		JPanel pnlCapNhat = new JPanel();
		pnlThongTin.add(pnlCapNhat);
		pnlCapNhat.setLayout(new BorderLayout(0, 0));

		JPanel pnlChucNang = new JPanel();
		pnlCapNhat.add(pnlChucNang, BorderLayout.SOUTH);
		pnlChucNang.setLayout(new BoxLayout(pnlChucNang, BoxLayout.X_AXIS));

		JPanel pnlPhimCN = new JPanel();
		FlowLayout fl_pnlPhimCN = (FlowLayout) pnlPhimCN.getLayout();
		fl_pnlPhimCN.setVgap(10);
		fl_pnlPhimCN.setHgap(20);
		fl_pnlPhimCN.setAlignment(FlowLayout.RIGHT);
		pnlChucNang.add(pnlPhimCN);

		btnThem = new JButton("Thêm tour");
		btnThem.setPreferredSize(new Dimension(150, 35));
		btnThem.setFocusable(false);
		btnThem.setToolTipText("Ctr + N\r\n");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlPhimCN.add(btnThem);

		btnHuy = new JButton("Hủy");
		btnHuy.setToolTipText("Ctr + N\r\n");
		btnHuy.setPreferredSize(new Dimension(150, 35));
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHuy.setFocusable(false);
		pnlPhimCN.add(btnHuy);
		btnHuy.setEnabled(false);

		btnXoa = new JButton("Xoá tour");
		btnXoa.setEnabled(false);
		btnXoa.setPreferredSize(new Dimension(150, 35));
		btnXoa.setFocusable(false);
		btnXoa.setToolTipText("Ctrl + D\r\n");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlPhimCN.add(btnXoa);

		btnSua = new JButton("Sửa tour");
		btnSua.setEnabled(false);
		btnSua.setPreferredSize(new Dimension(150, 35));
		btnSua.setFocusable(false);
		btnSua.setToolTipText("Ctrl + E");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlPhimCN.add(btnSua);

		btnLuu = new JButton("Lưu ");
		btnLuu.setEnabled(false);
		btnLuu.setPreferredSize(new Dimension(150, 35));
		btnLuu.setFocusable(false);
		btnLuu.setToolTipText("Ctrl + S");
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlPhimCN.add(btnLuu);
		btnLuu.setEnabled(false);

		JPanel pnlNhapLieu = new JPanel();
		pnlNhapLieu.setPreferredSize(new Dimension(10, 350));
		pnlNhapLieu.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 102, 255), 3), "Nh\u1EADp li\u1EC7u th\u00F4ng tin ",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCapNhat.add(pnlNhapLieu, BorderLayout.CENTER);
		pnlNhapLieu.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnLeft = new JPanel();
		pnLeft.setPreferredSize(new Dimension(10, 40));
		pnLeft.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlNhapLieu.add(pnLeft);
		pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));

		JPanel pnMa = new JPanel();
		FlowLayout fl_pnMa = (FlowLayout) pnMa.getLayout();
		fl_pnMa.setAlignment(FlowLayout.LEFT);
		pnLeft.add(pnMa);

		JLabel lblMaTour = new JLabel("Mã tour:");
		lblMaTour.setPreferredSize(new Dimension(100, 35));
		lblMaTour.setFont(new Font("Dialog", Font.PLAIN, 20));
		pnMa.add(lblMaTour);

		txtMaTour = new JTextField();
		txtMaTour.setPreferredSize(new Dimension(100, 35));
		txtMaTour.setEditable(false);
		txtMaTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnMa.add(txtMaTour);
		txtMaTour.setColumns(10);

		JPanel pnMaVaTen = new JPanel();
		FlowLayout fl_pnMaVaTen = (FlowLayout) pnMaVaTen.getLayout();
		fl_pnMaVaTen.setAlignment(FlowLayout.LEFT);
		pnLeft.add(pnMaVaTen);

		JLabel lblTenTour = new JLabel("Tên tour:");
		lblTenTour.setPreferredSize(new Dimension(100, 35));
		lblTenTour.setFont(new Font("Dialog", Font.PLAIN, 20));
		pnMaVaTen.add(lblTenTour);

		txtTenTour = new JTextField();
		txtTenTour.setEditable(false);
		txtTenTour.setPreferredSize(new Dimension(6, 35));
		txtTenTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnMaVaTen.add(txtTenTour);
		txtTenTour.setColumns(25);

		JPanel pnDiaDanh = new JPanel();
		FlowLayout fl_pnDiaDanh = (FlowLayout) pnDiaDanh.getLayout();
		fl_pnDiaDanh.setAlignment(FlowLayout.LEADING);
		pnLeft.add(pnDiaDanh);

		JLabel lblDiaDanh = new JLabel("Địa danh:\r\n");
		lblDiaDanh.setPreferredSize(new Dimension(100, 35));
		lblDiaDanh.setFont(new Font("Dialog", Font.PLAIN, 20));
		pnDiaDanh.add(lblDiaDanh);

		cmbDiaDanh = new JComboBox<DiaDanh>();
		cmbDiaDanh.setPreferredSize(new Dimension(430, 35));
		cmbDiaDanh.setMinimumSize(new Dimension(200, 22));
		cmbDiaDanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnDiaDanh.add(cmbDiaDanh);
		cmbDiaDanh.setEnabled(false);

		JPanel pnThoiGian = new JPanel();
		pnLeft.add(pnThoiGian);
		pnThoiGian.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlTuNg = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) pnlTuNg.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		pnThoiGian.add(pnlTuNg);

		JLabel lblTuNg = new JLabel("Từ ngày:");
		pnlTuNg.add(lblTuNg);
		lblTuNg.setPreferredSize(new Dimension(150, 35));
		lblTuNg.setFont(new Font("Dialog", Font.PLAIN, 20));

		dtcTuNgay = new JDateChooser();
		dtcTuNgay.setLocale(new Locale("vi"));
		pnlTuNg.add(dtcTuNgay);
		dtcTuNgay.setPreferredSize(new Dimension(172, 35));
		dtcTuNgay.setMinimumSize(new Dimension(27, 35));
		dtcTuNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dtcTuNgay.setDateFormatString("dd/MM/yyyy");

		JPanel pnlDenNgay = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnlDenNgay.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pnThoiGian.add(pnlDenNgay);

		JLabel lblNewLabel_1 = new JLabel("Đến ngày:");
		pnlDenNgay.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setPreferredSize(new Dimension(100, 35));
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 20));

		dtcDenNgay = new JDateChooser();
		dtcDenNgay.setLocale(new Locale("vi"));
		pnlDenNgay.add(dtcDenNgay);
		dtcDenNgay.setDateFormatString("dd/MM/yyyy");
		dtcDenNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dtcDenNgay.setPreferredSize(new Dimension(172, 35));

		JPanel pnDonGia = new JPanel();
		pnLeft.add(pnDonGia);
		pnDonGia.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnGNgLon = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) pnGNgLon.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		pnDonGia.add(pnGNgLon);

		JLabel lblGNgLon = new JLabel("Giá người lớn:");
		pnGNgLon.add(lblGNgLon);
		lblGNgLon.setPreferredSize(new Dimension(150, 35));
		lblGNgLon.setHorizontalTextPosition(SwingConstants.LEADING);
		lblGNgLon.setFont(new Font("Dialog", Font.PLAIN, 20));

		Locale locale = new Locale("vi", "VN");
		Format currency = NumberFormat.getCurrencyInstance(locale);

		txtGiaNgLon = new JFormattedTextField(currency);
		txtGiaNgLon.addPropertyChangeListener("value", this);
		txtGiaNgLon.setValue(donGiaNguoiLon);
		txtGiaNgLon.setEditable(false);
		pnGNgLon.add(txtGiaNgLon);
		txtGiaNgLon.setPreferredSize(new Dimension(200, 35));
		txtGiaNgLon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGiaNgLon.setColumns(10);

		JPanel pnlGTReEm = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) pnlGTReEm.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		pnDonGia.add(pnlGTReEm);

		JLabel lblGTreEm = new JLabel("Giá trẻ em:");
		pnlGTReEm.add(lblGTreEm);
		lblGTreEm.setPreferredSize(new Dimension(100, 35));
		lblGTreEm.setFont(new Font("Dialog", Font.PLAIN, 20));

		txtGTreEm = new JFormattedTextField(currency);
		txtGTreEm.addPropertyChangeListener("value", this);
		txtGTreEm.setValue(donGiaTreEm);
		txtGTreEm.setEditable(false);
		pnlGTReEm.add(txtGTreEm);
		txtGTreEm.setPreferredSize(new Dimension(200, 35));
		txtGTreEm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGTreEm.setColumns(10);

		JPanel pnSoNguoiTD = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnSoNguoiTD.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnLeft.add(pnSoNguoiTD);

		JLabel lblSoNgTD = new JLabel("Số người tham gia tối đa:");
		lblSoNgTD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnSoNguoiTD.add(lblSoNgTD);

		spnSoNgTD = new JSpinner();
		spnSoNgTD.setEnabled(false);
		spnSoNgTD.setPreferredSize(new Dimension(50, 35));
		spnSoNgTD.setModel(new SpinnerNumberModel(2, 2, 40, 1));
		spnSoNgTD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnSoNguoiTD.add(spnSoNgTD);

		JPanel pnRight = new JPanel();
		pnRight.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlNhapLieu.add(pnRight);

		JPanel pnMoTa = new JPanel();

		JLabel lblMoTa = new JLabel("Mô tả:");
		lblMoTa.setVerticalAlignment(SwingConstants.TOP);
		lblMoTa.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoTa.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel pnNDMoTa = new JPanel();
		pnNDMoTa.setLayout(new BorderLayout(0, 0));

		JScrollPane scrMoTa = new JScrollPane();
		pnNDMoTa.add(scrMoTa, BorderLayout.CENTER);

		txpMoTa = new JTextPane();

		txpMoTa.setEditable(false);

		txpMoTa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrMoTa.setViewportView(txpMoTa);

		JPanel pnHinhAnh = new JPanel();

		JLabel lblHinhAnh = new JLabel("Hình ảnh:");
		lblHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtDgDan = new JTextField();
		txtDgDan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDgDan.setEditable(false);
		txtDgDan.setMinimumSize(new Dimension(6, 35));
		txtDgDan.setColumns(10);

		btnFile = new JButton("...");
		btnFile.setEnabled(false);
		btnFile.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnXemAnh = new JButton("Xem ảnh");
		btnXemAnh.setEnabled(false);
		btnXemAnh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		chkThemHinhAnh = new JCheckBox("Chọn hình ảnh");
		chkThemHinhAnh.setEnabled(false);
		chkThemHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_pnHinhAnh = new GroupLayout(pnHinhAnh);
		gl_pnHinhAnh.setHorizontalGroup(gl_pnHinhAnh.createParallelGroup(Alignment.LEADING).addGroup(gl_pnHinhAnh
				.createSequentialGroup().addContainerGap().addComponent(lblHinhAnh).addGap(25)
				.addGroup(gl_pnHinhAnh.createParallelGroup(Alignment.LEADING).addComponent(chkThemHinhAnh)
						.addGroup(gl_pnHinhAnh.createSequentialGroup()
								.addComponent(txtDgDan, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnFile)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnXemAnh)))
				.addContainerGap(119, Short.MAX_VALUE)));
		gl_pnHinhAnh.setVerticalGroup(gl_pnHinhAnh.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnHinhAnh
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnHinhAnh.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnHinhAnh.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnFile, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnXemAnh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtDgDan, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHinhAnh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(chkThemHinhAnh)));
		pnHinhAnh.setLayout(gl_pnHinhAnh);
		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));
		pnRight.add(pnMoTa);
		GroupLayout gl_pnMoTa = new GroupLayout(pnMoTa);
		gl_pnMoTa.setHorizontalGroup(gl_pnMoTa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnMoTa.createSequentialGroup().addContainerGap()
						.addComponent(lblMoTa, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE).addGap(25)
						.addComponent(pnNDMoTa, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE).addGap(36)));
		gl_pnMoTa.setVerticalGroup(gl_pnMoTa.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnMoTa.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnMoTa.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMoTa, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnNDMoTa, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
						.addGap(38)));
		pnMoTa.setLayout(gl_pnMoTa);
		pnRight.add(pnHinhAnh);

		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlNhapLieu, pnlTblTour }, "Tahoma", Font.PLAIN, 20);

		pnlTblTour.setLayout(new BorderLayout(0, 0));

		/*
		 * Khởi tạo đối tượng control
		 */
		tourControl = new TourControlImpl();

		// Hiển thị danh sách địa danh
		cmbModel = new DefaultComboBoxModel<DiaDanh>();
		diaDanhs = capNhatTourBUS.layDSDiaDanh();
		hienThiDanhSachDiaDanh();

		// Tạo bảng thông tin tour
		dsTour = capNhatTourBUS.layDSTour(nv.getMaNV());
		tblTour = new JTable() {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tblTour.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTour.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		String[] headers = { "STT", "Mã tour", "Tên tour", "Ngày khởi hành", "Ngày kết thúc", "Giá người lớn",
				"Giá trẻ em", "Số người tối đa", "Địa danh" };

		tblModel = new DefaultTableModel(headers, 0);
		hienBangThongTinTour(dsTour);

		tblTour.getColumnModel().getColumn(3).setCellRenderer(new MyDateRenderer());
		tblTour.getColumnModel().getColumn(4).setCellRenderer(new MyDateRenderer());
		tblTour.getColumnModel().getColumn(5).setCellRenderer(new MoneyRenderer());
		tblTour.getColumnModel().getColumn(6).setCellRenderer(new MoneyRenderer());
		tblTour.getColumnModel().getColumn(0).setCellRenderer(new NumberRenderrer());
		tblTour.getColumnModel().getColumn(7).setCellRenderer(new NumberRenderrer());

		pnlTblTour.add(new JScrollPane(tblTour));
		TienIch.chinhKichThuocTable(tblTour, tblTour.getColumnModel().getTotalColumnWidth(), 0.5, 5, 50, 10, 10, 10, 10,
				10, 50);

		// sự kiện
		ganSuKien();

		DefaultComboBoxModel<DiaDanh> model = new DefaultComboBoxModel<DiaDanh>();
		model.removeAllElements();
		diaDanhs.forEach(x -> {
			model.addElement(x);
		});

		TienIch.hienAnCacControl(false, dtcDenNgay, dtcTuNgay);
		((JTextComponent) dtcDenNgay.getDateEditor().getUiComponent()).setEditable(false);
		((JTextComponent) dtcTuNgay.getDateEditor().getUiComponent()).setEditable(false);

	}

	/**
	 * Hiển thị danh sách đia danh lên combox
	 */
	private void hienThiDanhSachDiaDanh() {
		cmbModel.removeAllElements();
		for (DiaDanh diaDanh : diaDanhs) {
			cmbModel.addElement(diaDanh);
		}
		cmbDiaDanh.setModel(cmbModel);
	}

	/**
	 * Hàm gán sự kiện cho các control
	 */
	private void ganSuKien() {
		btnLuu.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnFile.addActionListener(this);
		btnXemAnh.addActionListener(this);
		chkThemHinhAnh.addActionListener(this);
		btnHuy.addActionListener(this);
		spnSoNgTD.addChangeListener(this);
		btnThoat.addActionListener(this);
		// Sự kiện cho table
		tblTour.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				int row = tblTour.getSelectedRow();
				if (row != -1) {
					hienThiCTTour(row);
					btnLuu.setEnabled(false);
					TienIch.hienAnCacControl(true, btnXoa, btnSua, btnXemAnh);
					TienIch.hienAnCacControl(false, btnLuu, cmbDiaDanh, chkThemHinhAnh);
				}

			}
		});
		spnSoNgTD.addPropertyChangeListener(this);

		/*
		 * Xử lý sự kiện tìm kiếm
		 */
		cmbLoaiTK.addActionListener(this);
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String content = TienIch
						.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTimKiem.getText().trim().toLowerCase());
				List<Tour> lstTourTK = new ArrayList<Tour>();
				dsTour.forEach(x -> {
					switch (cmbLoaiTK.getSelectedIndex()) {
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

					case 3:
						String diaDanh = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(
								x.getDiaDanh().getTenDiaDanh().trim().toLowerCase());
						if (diaDanh.contains(content)) {
							lstTourTK.add(x);
						}
						break;

					default:
						lstTourTK.add(x);
						break;
					}
				});
				hienBangThongTinTour(lstTourTK);

			}
		});

	}

	/**
	 * Hiện thông tin tour khi nhấn chọn một hàng trên bảng
	 * 
	 * @param row: vị trí hàng
	 */
	private void hienThiCTTour(int row) {
		String maTour = (String) tblTour.getValueAt(row, 1);
		Tour tourCanTim = new Tour();
		tourCanTim.setMaTour(maTour);
		dsTour = capNhatTourBUS.layDSTour(nv.getMaNV());
		if (dsTour.contains(tourCanTim)) {
			tourCanTim = dsTour.get(dsTour.indexOf(tourCanTim));
			txtMaTour.setText(tourCanTim.getMaTour());
			txtTenTour.setText(tourCanTim.getTenTour());

			txtGiaNgLon.setValue(tourCanTim.getDonGiaNguoiLon());

			txtGTreEm.setValue(tourCanTim.getDonGiaTreEm());

			dtcTuNgay.setDate(tourCanTim.getNgayKhoiHanh());
			dtcDenNgay.setDate(tourCanTim.getNgayKetThuc());

			spnSoNgTD.setValue(tourCanTim.getSoLuongKhach());

			txpMoTa.setText(tourCanTim.getMoTa());

			// Hiện tên địa danh
			for (int i = 0; i < cmbModel.getSize(); i++) {
				if (cmbDiaDanh.getItemAt(i).equals(tourCanTim.getDiaDanh())) {
					cmbDiaDanh.setSelectedIndex(i);
				}

			}

			duLieuAnh = tourCanTim.getHinhAnh();
		}
	}

	/**
	 * Tạo bảng hiện danh sách tour
	 */
	private void hienBangThongTinTour(List<Tour> ds) {
		int i = 1;
		tblModel.setRowCount(0);
		for (Tour x : ds) {
			tblModel.addRow(new Object[] { i, x.getMaTour(), x.getTenTour(), x.getNgayKhoiHanh(), x.getNgayKetThuc(),
					x.getDonGiaNguoiLon(), x.getDonGiaTreEm(), x.getSoLuongKhach(), x.getDiaDanh().getTenDiaDanh() });
			i++;
		}
		tblTour.setModel(tblModel);
	}

	/**
	 * Hàm xử ký sự kiện nhấn Button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// Lấy mã hiện tại của tour đã có trong csdl
		String maPS = TienIch.phatSinhMa(1);
		// Nếu chọn nút Lưu
		if (o.equals(btnLuu)) {
			if (kiemTraThongTin()) {
				Tour tour = new Tour();

				/*
				 * Nhập thông tin tour cần cập nhật
				 */

				tour.setMaTour(txtMaTour.getText());
				tour.setTenTour(txtTenTour.getText());
				tour.setMoTa(txpMoTa.getText());

				tour.setNgayKhoiHanh(new Date(dtcTuNgay.getDate().getTime()));
				tour.setNgayKetThuc(new Date(dtcDenNgay.getDate().getTime()));

				soNgToiDa = (int) spnSoNgTD.getValue();
				tour.setSoLuongKhach(soNgToiDa);

				tour.setDonGiaNguoiLon(((Number) txtGiaNgLon.getValue()).doubleValue());
				tour.setDonGiaTreEm(((Number) txtGTreEm.getValue()).doubleValue());

				tour.setNhanVien(nv);
				tour.setDiaDanh((DiaDanh) cmbDiaDanh.getSelectedItem());

				///////////////////////////////////////////////////////

				/*
				 * Nếu tour chưa tồn tại thì thêm mới
				 */
				if (!dsTour.contains(tour)) {
					String duongDan = txtDgDan.getText().trim();
					if (duongDan.length() == 0) {
						JOptionPane.showMessageDialog(this, "Chưa chọn đường dẫn chứa ảnh");
						return;
					}
					// chọn ảnh cho tour
					tour.setHinhAnh(chonAnhChoTour(txtDgDan.getText()));

					Tour toutThem = capNhatTourBUS.themTour(tour);
					// Nếu thêm tour thành công
					if (toutThem != null) {
						// thì cập nhật lại bảng tour
						hienBangThongTinTour(capNhatTourBUS.layDSTour(nv.getMaNV()));

						// Cập nhật mã tour
						TienIch.capNhatMaPhatSinh(1, maPS);

						// xoá trắng các control
						xoaTrangControl();

					}
					// Nếu tour đã tồn tại thì sửa
				} else {
					boolean daSua = false;
					if (chkThemHinhAnh.isSelected() && !txtDgDan.getText().trim().equals("")) {
						tour.setHinhAnh(chonAnhChoTour(txtDgDan.getText()));
						daSua = capNhatTourBUS.suaTour(tour, true);
					} else {
						daSua = capNhatTourBUS.suaTour(tour, false);
					}

					if (daSua) {

						hienBangThongTinTour(capNhatTourBUS.layDSTour(nv.getMaNV()));
						// xoá trắng các control
						xoaTrangControl();

					}
				}
			}

		}
		// Ngược lại nếu chọn nút thoát
		else if (o.equals(btnThoat)) {
			TienIch.chuyenPanelKhiNhan(frmMain.getPnContent(), new pnlQuanLyTour());
			TienIch.xoaDuongDan(frmMain.getPnButtonBar(), 2);

			// ngược lại nếu chọn nút chọn file
		} else if (o.equals(btnFile)) {
			// Mở file chọn ảnh

			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}

			JFileChooser chooser = new JFileChooser();
			SwingUtilities.updateComponentTreeUI(chooser);
			chooser.setFileFilter(new FileFilter() {

				@Override
				public String getDescription() {

					return "jpg, png, gif";
				}

				@Override
				public boolean accept(File f) {

					return f.getAbsolutePath().endsWith(".jpg") || f.getAbsolutePath().endsWith(".png")
							|| f.getAbsolutePath().endsWith(".gif");
				}
			});
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				txtDgDan.setText(chooser.getSelectedFile().getAbsolutePath());
			}

		} else if (o.equals(btnThem)) {// Ngược lại nếu chọn nút thêm
			duLieuAnh = null;
			TienIch.hienAnCacControl(false, btnThem, chkThemHinhAnh, btnSua, btnXoa);
			btnHuy.setEnabled(true);

			// Cho phép các control được thao tác
			TienIch.hienAnCacControl(true, txtTenTour, txtGTreEm, txtGiaNgLon, spnSoNgTD, dtcDenNgay, dtcTuNgay,
					cmbDiaDanh, btnFile, btnXemAnh, txpMoTa, btnLuu);

			// Phát sinh mã cho ô mã Tour
			txtMaTour.setText("TO00" + maPS);

			// Xoá trắng nội dung trong các ô nhập
			TienIch.xoaTrangCacJTextField(txtDgDan, txtTenTour);
			dtcTuNgay.setDate(new Date(System.currentTimeMillis()));
			dtcDenNgay.setDate(new Date(System.currentTimeMillis()));
			tblTour.getSelectionModel().clearSelection();
			tblTour.setEnabled(false);

			txpMoTa.setText("");

		} else if (o.equals(btnSua)) {// Ngược lại nếu chọn nút thêm
			// thì hiện các control
			TienIch.hienAnCacControl(true, txtTenTour, txtGTreEm, txtGiaNgLon, spnSoNgTD, dtcTuNgay, dtcDenNgay,
					txpMoTa, chkThemHinhAnh, btnXemAnh, btnLuu, btnHuy, cmbDiaDanh);
			TienIch.hienAnCacControl(false, btnThem, btnXoa, btnFile, btnSua);
			if (chkThemHinhAnh.isSelected()) {
				btnFile.setEnabled(true);
			}

		} else if (o.equals(btnXoa)) {// Ngược lại nếu chọn nút xoá
			btnHuy.setEnabled(false);
			// Thì xoá tour
			Tour tourXoa = new Tour();
			tourXoa.setMaTour(txtMaTour.getText());

			int sel = JOptionPane.showConfirmDialog(this, "Xoá tour " + txtTenTour.getText() + " ?", "Xoá tour",
					JOptionPane.YES_NO_OPTION);
			if (sel == JOptionPane.YES_OPTION) {
				boolean daXoa = capNhatTourBUS.xoaTour(tourXoa);
				if (daXoa) {
					hienBangThongTinTour(capNhatTourBUS.layDSTour(nv.getMaNV()));
				}
			}

		} else if (o.equals(btnXemAnh)) {
			if (txtDgDan.getText().trim().equals("")) {
				if (duLieuAnh != null) {
					new dlgXemAnh(duLieuAnh).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, "Chưa có thông tin hình ảnh !");
				}
			} else if (chonAnhChoTour(txtDgDan.getText()) != null) {
				new dlgXemAnh(chonAnhChoTour(txtDgDan.getText())).setVisible(true);
			}

		} else if (o.equals(chkThemHinhAnh)) {
			if (chkThemHinhAnh.isSelected()) {
				btnFile.setEnabled(true);
			} else {
				btnFile.setEnabled(false);
			}
		} else if (o.equals(btnHuy)) {
			xoaTrangControl();
		} else if (o.equals(cmbLoaiTK)) {
			txtTimKiem.setEditable(true);
			switch (cmbLoaiTK.getSelectedIndex()) {
			case 1:
				txtTimKiem.setUI(new HintTextFieldUI("Nhập mã tour ...", true));
				break;
			case 2:
				txtTimKiem.setUI(new HintTextFieldUI("Nhập tên tour ...", true));
				break;
			case 3:
				txtTimKiem.setUI(new HintTextFieldUI("Nhập địa danh ...", true));
				break;

			default:
				txtTimKiem.setEditable(false);
				break;
			}
		}

	}

	/**
	 * Xoá trắng các control khi nhấn nút lưu, nút huỷ
	 */
	private void xoaTrangControl() {
		tblTour.setEnabled(true);
		tblTour.getSelectionModel().clearSelection();
		TienIch.hienAnCacControl(false, btnHuy, btnLuu, txtTenTour, dtcTuNgay, dtcDenNgay, txtGiaNgLon, txtGTreEm,
				spnSoNgTD, txpMoTa, btnSua, btnXoa, btnXemAnh, btnFile, cmbDiaDanh);
		TienIch.hienAnCacControl(true, btnThem);
		TienIch.xoaTrangCacJTextField(txtMaTour, txtTenTour);
		dtcTuNgay.setDate(new Date(System.currentTimeMillis()));
		dtcDenNgay.setDate(new Date(System.currentTimeMillis()));
		txtGiaNgLon.setValue(0);
		txtGTreEm.setValue(0);
		spnSoNgTD.setValue(0);
		txpMoTa.setText("");
		txtDgDan.setText("");
		chkThemHinhAnh.setSelected(false);

		cmbDiaDanh.setSelectedIndex(0);

	}

	/**
	 * Hàm chuyển ảnh sang byte, dùng để thêm ảnh cho cơ sở dữ liệu
	 * 
	 * @param path: đường dẫn file
	 * @return byte[]
	 */
	private byte[] chonAnhChoTour(String path) {
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int i; (i = fis.read(buf)) != -1;) {
				bos.write(buf, 0, i);
			}
			return bos.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Kiểm tra dữ liệu đầu vào
	 * 
	 * @return
	 */
	private boolean kiemTraThongTin() {
		String tenTour = txtTenTour.getText().trim();
		String ngayBatDau = ((JTextField) dtcTuNgay.getDateEditor().getUiComponent()).getText();
		String ngayKetThuc = ((JTextField) dtcDenNgay.getDateEditor().getUiComponent()).getText();
		if (!(tenTour.length() > 0)) {
			JOptionPane.showMessageDialog(this, "Tên tour không được để trống !");
			txtTenTour.requestFocus();
			return false;
		}
		if (ngayBatDau.equals("")) {
			JOptionPane.showMessageDialog(this, "Ngày khởi hành không được để trống !");
			dtcTuNgay.requestFocusInWindow();
			return false;
		}
		if (!ktNgayKhoiHanh()) {
			JOptionPane.showMessageDialog(this, "Ngày khởi hành phải sau ngày hiện tại 10 ngày !");
			dtcTuNgay.requestFocusInWindow();
			return false;
		}
		if (ngayKetThuc.equals("")) {
			JOptionPane.showMessageDialog(this, "Ngày kết thúc không được để trống !");
			dtcDenNgay.requestFocusInWindow();
			return false;
		}
		if (!ktNgayKetThuc()) {
			JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày khởi hàng từ 2 ngày trở lên !");
			dtcDenNgay.requestFocusInWindow();
			return false;
		}
		if (!(txtGiaNgLon.getText().trim().length() > 0)) {
			JOptionPane.showMessageDialog(this, "Giá người lớn không được để trống !");
			txtGiaNgLon.requestFocus();
			return false;
		}

		if (!(txtGTreEm.getText().trim().length() > 0)) {
			JOptionPane.showMessageDialog(this, "Giá trẻ em không được để trống !");
			txtGiaNgLon.requestFocus();
			return false;
		}

		if (!(txpMoTa.getText().trim().length() > 0)) {
			JOptionPane.showMessageDialog(this, "Mô tả không được để trống !");
		}
		return true;
	}

	// Tinh khoan cach giua hai ngay
	public int tinhThoiGian() {
		LocalDate d1 = new Date(dtcTuNgay.getDate().getTime()).toLocalDate();
		LocalDate d2 = new Date(dtcDenNgay.getDate().getTime()).toLocalDate();
		Period period = Period.between(d1, d2);
		return period.getDays();
	}

	// Kiểm tra ngày khởi hành.Ngày khởi hành phải sau ngày hiện tại 10 ngày.
	public boolean ktNgayKhoiHanh() {
		LocalDate ngayKhoiHanh = new Date(dtcTuNgay.getDate().getTime()).toLocalDate();
		Period period = Period.between(LocalDate.now(), ngayKhoiHanh);
		return period.getDays() >= 10 || period.getMonths() >= 1 ? true : false;
	}

	// Kiểm tra ngày khởi hành.Ngày kết thúc phải sau ngày bắt đầu 2 ngày.
	public boolean ktNgayKetThuc() {
		return tinhThoiGian() >= 2 ? true : false;
	}

	/**
	 * Xử lý sự kiện khi nhập giá trị trên ô đơn giá
	 */
	public void propertyChange(PropertyChangeEvent e) {
		Object o = e.getSource();
		if (o.equals(txtGiaNgLon)) {
			donGiaNguoiLon = ((Number) txtGiaNgLon.getValue()).doubleValue();
		} else if (o.equals(txtGTreEm)) {
			donGiaTreEm = ((Number) txtGTreEm.getValue()).doubleValue();
		}

	}

	/**
	 * Xử lý sự kiện trên thanh JSpinner nhập số người tối đa
	 */
	public void stateChanged(ChangeEvent e) {
		Object o = e.getSource();
		if (o.equals(spnSoNgTD)) {
			soNgToiDa = ((Number) spnSoNgTD.getValue()).intValue();
			spnSoNgTD.setValue(soNgToiDa);
		}

	}

	/*
	 * ///////////////////////////////////////////////////////////
	 * 
	 * Danh sách các lớp định dạng các ô trong bảng thông tin tour
	 * 
	 */////////////////////////////////////////////////////////////

	private class MoneyRenderer extends DefaultTableCellRenderer {
		public MoneyRenderer() {
			this.setHorizontalAlignment(SwingConstants.RIGHT);
		}

		@Override
		protected void setValue(Object o) {

			DecimalFormat df = new DecimalFormat("#,###");
			o = df.format(o);
			super.setValue(o);
		}

	}

	private class NumberRenderrer extends DefaultTableCellRenderer {
		public NumberRenderrer() {
			this.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	private class MyDateRenderer extends DefaultTableCellRenderer {

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
