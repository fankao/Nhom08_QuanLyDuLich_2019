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
import java.sql.Date;
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
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import bus.IDangKyTourBUS;
import bus.impl.DangKyTourBUS;
import entities.DiaChi;
import entities.DiaDanh;
import entities.KhachHang;
import entities.NhanVien;
import entities.PhieuDangKy;
import entities.Tour;
import gui.frmDangNhap;
import gui.FrmMain;
import gui.dialog.dlgLocTour;
import gui.dialog.dlgPhieuThu;
import utils.HintTextFieldUI;
import utils.TienIch;

/**
 * 
 * @author Nguyễn Minh Chiến
 * @version 1.0 Ngày tạo: 30/09/2019
 *
 */
public class pnlPhieuDangKyTour extends JPanel implements ActionListener, ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField txtGNgLon;
	private JFormattedTextField txtGTreEm;
	private JTextField txtDiaDanh;
	private JTextField txtTenTour;
	private JTextField txtThoiGian;
	private JTextField txtTenKH;
	private JTextField txtDC;
	private JTextField txtCM;
	private JTextField txtMaKH;
	private JLabel lblSoNgTG;
	private JTextField txtSoNgTD;
	private JTextField txtMaTour;
	private JButton btnThoat;
	private JButton btnDangKy;
	private JButton btnTimKH;
	private DefaultComboBoxModel<DiaDanh> cmbModel;

	private JButton btnChonTour;
	private JPanel pnTimKiem;

	private double donGiaNguoiLon;
	private double donGiaTreEm;
	private IDangKyTourBUS dangKyTourBUS;

	private List<DiaDanh> diaDanhs;
	private static List<Tour> tours;

	private NhanVien nv;

	private static KhachHang khachHangTim;

	private JButton btnLamMoi;
	private JList<Tour> lstTour;
	private DefaultListModel<Tour> lstModel;
	private JTextPane txpMoTa;
	private JDateChooser dtcNgSinh;
	private JFormattedTextField ftxtSdt;
	private JTextField txtSoNguoiTG;
	private JSpinner spnSoTrEm;
	private JSpinner spnSoNgLon;
	private JLabel lblHinhAnh;
	private JButton btnHuy;
	private JButton btnHuyChonTour;
	private JPanel pnlYeuCau;
	private JButton btnTimNC;
	private JTextField txtTimKiemTour;
	private JCheckBox chkCheck;

	/**
	 * Khởi tạo giao diện đăng ký tour
	 */
	public pnlPhieuDangKyTour() {

		setSize(1500, 886);

		setLayout(new BorderLayout(0, 0));

		JPanel pnTieuDe = new JPanel();
		pnTieuDe.setBackground(new Color(0, 191, 255));
		add(pnTieuDe, BorderLayout.NORTH);

		btnThoat = new JButton("");
		btnThoat.setBackground(new Color(0, 191, 255));
		btnThoat.setBorder(null);
		btnThoat.setFocusable(false);
		btnThoat.setIcon(new ImageIcon(pnlPhieuDangKyTour.class.getResource("/images/turn_left.png")));
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblPhiungK = new JLabel("PHIẾU ĐĂNG KÝ TOUR DU LỊCH");
		lblPhiungK.setIcon(new ImageIcon(pnlPhieuDangKyTour.class.getResource("/images/subsciption.png")));
		lblPhiungK.setForeground(new Color(255, 255, 255));
		lblPhiungK.setFont(new Font("Arial", Font.BOLD, 25));
		GroupLayout gl_pnTieuDe = new GroupLayout(pnTieuDe);
		gl_pnTieuDe.setHorizontalGroup(gl_pnTieuDe.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnTieuDe.createSequentialGroup().addGap(20)
						.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE).addGap(450)
						.addComponent(lblPhiungK).addGap(490)));
		gl_pnTieuDe.setVerticalGroup(gl_pnTieuDe.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnTieuDe.createSequentialGroup().addGap(10)
						.addGroup(gl_pnTieuDe.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhiungK, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGap(10)));
		pnTieuDe.setLayout(gl_pnTieuDe);

		JPanel pnDangKy = new JPanel();
		add(pnDangKy, BorderLayout.CENTER);
		pnDangKy.setLayout(new BoxLayout(pnDangKy, BoxLayout.X_AXIS));

		JPanel pnTTKH = new JPanel();
		pnTTKH.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Th\u00F4ng tin \u0111\u0103ng k\u00FD tour",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnDangKy.add(pnTTKH);

		JLabel lnlTenKH = new JLabel("Họ và tên:");
		lnlTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblCM = new JLabel("Số chứng minh nhân dân(căn cước):");
		lblCM.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel label_8 = new JLabel("Ngày sinh:");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTenKH = new JTextField();
		txtTenKH.setEditable(false);
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenKH.setColumns(10);

		btnDangKy = new JButton("Đăng ký tour");
		btnDangKy.setEnabled(false);
		btnDangKy.setFocusable(false);
		btnDangKy.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblSoNgTG = new JLabel("Số người tham gia:");
		lblSoNgTG.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblDC = new JLabel("Địa chỉ:");
		lblDC.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtDC = new JTextField();
		txtDC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDC.setEditable(false);
		txtDC.setColumns(10);

		JLabel lblSdt = new JLabel("Số điện thoại:");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtCM = new JTextField();
		txtCM.setEditable(false);
		txtCM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCM.setColumns(10);

		dtcNgSinh = new JDateChooser();
		dtcNgSinh.setFocusable(false);
		((JTextField) dtcNgSinh.getDateEditor().getUiComponent()).setEditable(false);
		dtcNgSinh.getCalendarButton().setEnabled(false);
		dtcNgSinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 20));
		dtcNgSinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dtcNgSinh.setDateFormatString("dd/MM/yyyy");

		ftxtSdt = new JFormattedTextField();
		ftxtSdt.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblMaKH = new JLabel("Mã Khách hàng:");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);

		btnTimKH = new JButton("");
		btnTimKH.setFocusable(false);
		btnTimKH.setIcon(new ImageIcon(pnlPhieuDangKyTour.class.getResource("/images/search.png")));
		btnTimKH.setBackground(new Color(240, 240, 240));

		JLabel lblNguoiLon = new JLabel("Số người lớn:");
		lblNguoiLon.setFont(new Font("Tahoma", Font.PLAIN, 20));

		spnSoNgLon = new JSpinner();
		spnSoNgLon.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnSoNgLon.setOpaque(false);
		spnSoNgLon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spnSoNgLon.setEnabled(false);

		txtSoNguoiTG = new JTextField();
		txtSoNguoiTG.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoNguoiTG.setEditable(false);
		txtSoNguoiTG.setColumns(10);

		JLabel lblTreEm = new JLabel("Số trẻ em:");
		lblTreEm.setFont(new Font("Tahoma", Font.PLAIN, 20));

		spnSoTrEm = new JSpinner();
		spnSoTrEm.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spnSoTrEm.setOpaque(false);
		spnSoTrEm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		spnSoTrEm.setEnabled(false);

		btnHuy = new JButton("Huỷ");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHuy.setFocusable(false);
		btnHuy.setEnabled(false);

		chkCheck = new JCheckBox("Tham gia tour đăng ký");
		chkCheck.setSize(new Dimension(30, 30));
		chkCheck.setPreferredSize(new Dimension(157, 30));
		chkCheck.setEnabled(false);
		chkCheck.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_pnTTKH = new GroupLayout(pnTTKH);
		gl_pnTTKH.setHorizontalGroup(gl_pnTTKH.createParallelGroup(Alignment.LEADING).addGroup(gl_pnTTKH
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnTTKH.createSequentialGroup().addComponent(chkCheck).addContainerGap())
						.addGroup(gl_pnTTKH.createParallelGroup(Alignment.LEADING).addGroup(gl_pnTTKH
								.createSequentialGroup()
								.addGroup(gl_pnTTKH.createParallelGroup(Alignment.LEADING).addComponent(lblMaKH)
										.addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, 204,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lnlTenKH).addComponent(lblCM).addComponent(txtCM, 234, 234, 234)
										.addComponent(label_8).addComponent(lblSdt).addComponent(lblDC)
										.addComponent(txtDC, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addComponent(txtTenKH, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
										.addGroup(gl_pnTTKH.createSequentialGroup().addGroup(gl_pnTTKH
												.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(dtcNgSinh, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(ftxtSdt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 234,
														Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnTimKH,
														GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)))
								.addContainerGap())
								.addGroup(gl_pnTTKH.createSequentialGroup().addGroup(gl_pnTTKH
										.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_pnTTKH.createSequentialGroup().addComponent(lblSoNgTG).addGap(14))
										.addGroup(gl_pnTTKH.createSequentialGroup()
												.addGroup(gl_pnTTKH.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblTreEm, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
														.addComponent(lblNguoiLon, GroupLayout.DEFAULT_SIZE, 155,
																Short.MAX_VALUE))
												.addGap(29)))
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(gl_pnTTKH.createParallelGroup(Alignment.LEADING)
												.addComponent(spnSoTrEm, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(spnSoNgLon, GroupLayout.PREFERRED_SIZE, 60,
														GroupLayout.PREFERRED_SIZE))
										.addGap(156))
								.addGroup(gl_pnTTKH.createSequentialGroup().addGap(184)
										.addComponent(txtSoNguoiTG, 0, 64, Short.MAX_VALUE)
										.addContainerGap(152, Short.MAX_VALUE))
								.addGroup(gl_pnTTKH.createSequentialGroup().addComponent(btnDangKy)
										.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
										.addComponent(btnHuy, GroupLayout.PREFERRED_SIZE, 147,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())))));
		gl_pnTTKH.setVerticalGroup(gl_pnTTKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnTTKH.createSequentialGroup().addContainerGap()
						.addComponent(lblMaKH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(15)
						.addComponent(lnlTenKH).addGap(10)
						.addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(15)
						.addComponent(lblCM).addGap(10)
						.addComponent(txtCM, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(15)
						.addComponent(label_8).addGap(10)
						.addComponent(dtcNgSinh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(15)
						.addComponent(lblSdt).addGap(10)
						.addGroup(gl_pnTTKH.createParallelGroup(Alignment.BASELINE)
								.addComponent(ftxtSdt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTimKH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(15).addComponent(lblDC).addGap(10)
						.addComponent(txtDC, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(15)
						.addGroup(gl_pnTTKH.createParallelGroup(Alignment.BASELINE)
								.addComponent(spnSoNgLon, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNguoiLon, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_pnTTKH.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTreEm, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(spnSoTrEm, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(15)
						.addGroup(gl_pnTTKH.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSoNgTG, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSoNguoiTG, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(chkCheck)
						.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
						.addGroup(gl_pnTTKH.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDangKy, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnHuy, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		pnTTKH.setLayout(gl_pnTTKH);

		JPanel pnDSTour = new JPanel();
		pnDSTour.setFocusable(false);
		pnDSTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnDSTour.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Danh s\u00E1ch tour du l\u1ECBch",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnDangKy.add(pnDSTour);
		pnDSTour.setLayout(new BorderLayout(0, 0));

		pnTimKiem = new JPanel();
		pnTimKiem.setBorder(new EmptyBorder(0, 5, 0, 5));
		pnDSTour.add(pnTimKiem, BorderLayout.NORTH);
		pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.Y_AXIS));

		pnlYeuCau = new JPanel();
		pnlYeuCau.setBorder(new EmptyBorder(2, 2, 2, 2));
		pnTimKiem.add(pnlYeuCau);

		txtTimKiemTour = new JTextField();
		txtTimKiemTour.setEditable(false);
		txtTimKiemTour.setPreferredSize(new Dimension(6, 35));
		txtTimKiemTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiemTour.setColumns(25);

		JPanel pnlNoiDungTK = new JPanel();
		pnTimKiem.add(pnlNoiDungTK);
		pnlNoiDungTK.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnlNoiDungTK.add(panel);

		btnTimNC = new JButton("Tìm kiếm nâng cao");
		btnTimNC.setEnabled(false);
		panel.add(btnTimNC);
		btnTimNC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTimNC.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel pnlLamMoi = new JPanel();
		FlowLayout fl_pnlLamMoi = (FlowLayout) pnlLamMoi.getLayout();
		fl_pnlLamMoi.setAlignment(FlowLayout.RIGHT);
		pnlNoiDungTK.add(pnlLamMoi);

		btnLamMoi = new JButton("");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlLamMoi.add(btnLamMoi);
		btnLamMoi.setEnabled(false);
		btnLamMoi.setFocusable(false);
		btnLamMoi.setIcon(new ImageIcon(pnlPhieuDangKyTour.class.getResource("/images/lammoi.png")));

		JPanel pnDS = new JPanel();
		pnDS.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnDSTour.add(pnDS, BorderLayout.CENTER);
		pnDS.setLayout(new BorderLayout(0, 0));

		JPanel pnLstTour = new JPanel();
		pnLstTour.setBorder(new EmptyBorder(2, 2, 2, 2));
		pnDS.add(pnLstTour, BorderLayout.CENTER);
		pnLstTour.setLayout(new BorderLayout(0, 0));

		JScrollPane scrTour = new JScrollPane();
		pnLstTour.add(scrTour, BorderLayout.CENTER);

		lstTour = new JList<Tour>();
		lstTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrTour.setViewportView(lstTour);

		JPanel pnlCN = new JPanel();
		pnDS.add(pnlCN, BorderLayout.SOUTH);
		pnlCN.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel pnlChonTour = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlChonTour.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlCN.add(pnlChonTour);

		btnChonTour = new JButton("Chọn tour");
		pnlChonTour.add(btnChonTour);
		btnChonTour.setEnabled(false);
		btnChonTour.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel pnlHuyChonTour = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlHuyChonTour.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnlCN.add(pnlHuyChonTour);

		btnHuyChonTour = new JButton("Huỷ chọn tour");
		btnHuyChonTour.setEnabled(false);
		btnHuyChonTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlHuyChonTour.add(btnHuyChonTour);

		JPanel pnTTTour = new JPanel();
		pnTTTour.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Th\u00F4ng tin tour du l\u1ECBch",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnDangKy.add(pnTTTour);

		JPanel pnMoTa = new JPanel();
		pnMoTa.setLayout(new BorderLayout(0, 0));

		JPanel pnHinhAnh = new JPanel();
		pnHinhAnh.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(2, 2, 2, 2)));
		pnHinhAnh.setBackground(new Color(240, 240, 240));
		pnHinhAnh.setLayout(new BorderLayout(0, 0));

		JLabel lblGNgLon = new JLabel("Giá người lớn:");
		lblGNgLon.setHorizontalAlignment(SwingConstants.CENTER);
		lblGNgLon.setFont(new Font("Tahoma", Font.PLAIN, 20));

		Locale locale = new Locale("vi", "VN");
		Format currency = NumberFormat.getCurrencyInstance(locale);

		txtGNgLon = new JFormattedTextField(currency);
		txtGNgLon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGNgLon.setValue(donGiaNguoiLon);
		txtGNgLon.setEditable(false);
		txtGNgLon.setColumns(10);

		JLabel lblGTreEm = new JLabel("Giá trẻ em:");
		lblGTreEm.setHorizontalAlignment(SwingConstants.CENTER);
		lblGTreEm.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtGTreEm = new JFormattedTextField(currency);
		txtGTreEm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtGTreEm.setValue(donGiaTreEm);
		txtGTreEm.setEditable(false);
		txtGTreEm.setColumns(10);

		txtDiaDanh = new JTextField();
		txtDiaDanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDiaDanh.setEditable(false);
		txtDiaDanh.setColumns(10);

		JLabel lblDiaDanh = new JLabel("Địa danh:");
		lblDiaDanh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtTenTour = new JTextField();
		txtTenTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenTour.setEditable(false);
		txtTenTour.setColumns(10);

		JLabel lnlTen = new JLabel("Tên tour:");
		lnlTen.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblMoTa = new JLabel("Mô tả:");
		lblMoTa.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtThoiGian = new JTextField();
		txtThoiGian.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtThoiGian.setEditable(false);
		txtThoiGian.setColumns(10);

		JLabel lblThoiGian = new JLabel("Thời gian:");
		lblThoiGian.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblSoNgTD = new JLabel("Tình trạng đăng ký:");
		lblSoNgTD.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSoNgTD = new JTextField();
		txtSoNgTD.setEditable(false);
		txtSoNgTD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoNgTD.setColumns(10);

		JLabel lblMaTour = new JLabel("Mã tour:");
		lblMaTour.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtMaTour = new JTextField();
		txtMaTour.setEditable(false);
		txtMaTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaTour.setColumns(10);
		GroupLayout gl_pnTTTour = new GroupLayout(pnTTTour);
		gl_pnTTTour.setHorizontalGroup(gl_pnTTTour.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnTTTour.createSequentialGroup().addGap(12)
						.addComponent(pnHinhAnh, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(gl_pnTTTour.createSequentialGroup().addContainerGap()
						.addComponent(pnMoTa, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE).addContainerGap())
				.addGroup(gl_pnTTTour.createSequentialGroup().addGroup(gl_pnTTTour
						.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnTTTour.createSequentialGroup().addGap(12).addGroup(gl_pnTTTour
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnTTTour.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtThoiGian, Alignment.LEADING)
										.addComponent(lblGNgLon, Alignment.LEADING).addComponent(txtGNgLon,
												Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
								.addComponent(lblThoiGian)).addGap(124)
								.addGroup(gl_pnTTTour.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblSoNgTD).addComponent(lblGTreEm)
										.addComponent(txtGTreEm, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
										.addComponent(txtSoNgTD)))
						.addGroup(gl_pnTTTour.createSequentialGroup().addContainerGap().addGroup(gl_pnTTTour
								.createParallelGroup(Alignment.LEADING, false).addComponent(txtDiaDanh)
								.addComponent(lblMoTa)
								.addComponent(lblDiaDanh, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lnlTen)
								.addGroup(gl_pnTTTour.createSequentialGroup().addComponent(lblMaTour).addGap(18)
										.addComponent(txtMaTour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(txtTenTour, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))))
						.addGap(18)));
		gl_pnTTTour.setVerticalGroup(gl_pnTTTour.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnTTTour.createSequentialGroup()
						.addComponent(pnHinhAnh, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnTTTour.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMaTour, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMaTour, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(15).addComponent(lnlTen, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtTenTour, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(15)
						.addComponent(lblDiaDanh, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE).addGap(8)
						.addComponent(txtDiaDanh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE).addGap(15)
						.addGroup(gl_pnTTTour.createParallelGroup(Alignment.TRAILING).addComponent(lblGTreEm)
								.addComponent(lblGNgLon))
						.addGap(10)
						.addGroup(gl_pnTTTour.createParallelGroup(Alignment.LEADING)
								.addComponent(txtGNgLon, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtGTreEm, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addGroup(gl_pnTTTour.createParallelGroup(Alignment.BASELINE).addComponent(lblThoiGian)
								.addComponent(lblSoNgTD))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_pnTTTour.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtThoiGian, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSoNgTD, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblMoTa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnMoTa, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JScrollPane scrMoTa = new JScrollPane();
		pnMoTa.add(scrMoTa, BorderLayout.CENTER);

		txpMoTa = new JTextPane();
		txpMoTa.setEditable(false);
		txpMoTa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrMoTa.setViewportView(txpMoTa);

		lblHinhAnh = new JLabel("<Hình ảnh>");
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnHinhAnh.add(lblHinhAnh, BorderLayout.CENTER);
		pnTTTour.setLayout(gl_pnTTTour);

		dangKyTourBUS = new DangKyTourBUS();

		/*
		 * Thông tin nhân viên
		 */
		nv = frmDangNhap.getNv();

		////////////////////////

		diaDanhs = dangKyTourBUS.layDSDiaDanh();

		/*
		 * thiết lập chế độ chọn một cho JList
		 */
		lstTour.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//////////////////////////

		// chỉnh kích thước title border
		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnDSTour, pnTTTour, pnTTKH }, "Tahoma", Font.PLAIN, 20);

		// chỉnh sử thanh tìm kiếm
		txtTimKiemTour.setUI(new HintTextFieldUI("Nhập tên tour cần tìm ...", true));
		GroupLayout gl_pnlYeuCau = new GroupLayout(pnlYeuCau);
		gl_pnlYeuCau.setHorizontalGroup(gl_pnlYeuCau.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlYeuCau.createSequentialGroup().addGap(2)
						.addComponent(txtTimKiemTour, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE).addGap(2)));
		gl_pnlYeuCau.setVerticalGroup(gl_pnlYeuCau.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlYeuCau
						.createSequentialGroup().addGap(2).addComponent(txtTimKiemTour, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(2, Short.MAX_VALUE)));
		pnlYeuCau.setLayout(gl_pnlYeuCau);

		ftxtSdt.setUI(new HintTextFieldUI("Số điện thoại...", true));
		ftxtSdt.requestFocus();
		// gán sự kiện
		ganSuKien();

	}

	/**
	 * Hiện danh sách tour
	 * 
	 * @param lst: danh sách tour cần hiện
	 */
	private void hienDanhSachTour(List<Tour> lst) {
		lstModel = new DefaultListModel<Tour>();
		lstModel.removeAllElements();
		lst.forEach(x -> lstModel.addElement(x));
		lstTour.setModel(lstModel);
	}

	/**
	 * Hàm gán sự kiện cho các nút
	 */
	private void ganSuKien() {
		btnThoat.addActionListener(this);
		btnTimKH.addActionListener(this);
		btnDangKy.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnChonTour.addActionListener(this);

		spnSoNgLon.addChangeListener(this);
		spnSoTrEm.addChangeListener(this);

		btnHuy.addActionListener(this);
		btnHuyChonTour.addActionListener(this);
		btnTimNC.addActionListener(this);

		/*
		 * xử lý sự kiện nhập tên tour
		 */
		txtTimKiemTour.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String content = TienIch
						.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTimKiemTour.getText().trim().toLowerCase());
				List<Tour> lstLoc = new ArrayList<Tour>();
				tours.forEach(x -> {
					String tenTour = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(x.getTenTour());
					if (tenTour.toLowerCase().contains(content)) {
						lstLoc.add(x);
					}

				});
				if (lstLoc.size() == 0) {
					hienDanhSachTour(tours);
				} else {
					hienDanhSachTour(lstLoc);
				}

			}
		});
	}

	/**
	 * Hàm sử lý sự kiện cho các Button
	 */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// Lấy mã Phát sinh
		String maKH = TienIch.phatSinhMa(2);

		// Nếu chọn nút thoát
		if (o.equals(btnThoat)) {

			TienIch.xoaDuongDan(FrmMain.getPnButtonBar(), 2);
			TienIch.chuyenPanelKhiNhan(FrmMain.getPnContent(), new pnlQuanLyTour());

		} else if (o.equals(btnLamMoi)) {
			if (khachHangTim != null) {
				tours = dangKyTourBUS.layDSTourChuaDangKyTheoMaKH("", khachHangTim.getMaKH());
			} else {
				tours = dangKyTourBUS.layDSTourChuaDuyet();
			}
			hienDanhSachTour(tours);
		} else if (o.equals(btnTimKH)) {
			/*
			 * Bước 1: Tìm kiếm thông tin khách hàng bằng số điện thoại, nếu khách hàng mới
			 * thì nhấn button tìm kiếm để hiện các trương nhập thông tin
			 */
			if (!ftxtSdt.getText().trim().equals("") && (ftxtSdt.getText().matches("(\\+84|0)[0-9]{9}"))) {

				khachHangTim = dangKyTourBUS.layTTKhachHangTheoSDT(ftxtSdt.getText());

				if (khachHangTim != null) {
					// hiện danh sách tour mã khách hàng có thể đăng ký
					tours = dangKyTourBUS.layDSTourChuaDangKyTheoMaKH("", khachHangTim.getMaKH());
					hienDanhSachTour(tours);

					// Hiện thông tin khách hàng
					hienThongTinKhachHang(khachHangTim);

					ftxtSdt.setEditable(false);
					TienIch.hienAnCacControl(true, btnLamMoi, btnHuy, btnChonTour, txtTimKiemTour, btnTimNC);
					requestFocus(false);
				} else {
					tours = dangKyTourBUS.layDSTourChuaDuyet();
					hienDanhSachTour(tours);
					TienIch.hienAnCacControl(true, txtTenKH, txtCM, txtDC, ftxtSdt, dtcNgSinh, btnChonTour, btnHuy,
							btnLamMoi, txtTimKiemTour, btnTimNC);
					txtMaKH.setText("KH00" + maKH);
					ftxtSdt.setEditable(false);
					txtTenKH.requestFocus();
					btnTimKH.setEnabled(false);
				}
			} else {
				/*
				 * tours = dangKyTourBUS.layDSTour(); hienDanhSachTour(tours);
				 * TienIch.hienAnCacControl(true, txtTenKH, txtCM, txtDC, ftxtSdt, spnSoNgLon,
				 * spnSoTrEm, dtcNgSinh, btnChonTour, btnHuy, btnLamMoi, txtTimKiemTour,
				 * btnTimNC); txtMaKH.setText("KH00" + maKH); txtTenKH.requestFocus();
				 */
				JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu là 0 hoặc +84 tiếp sau là 9 ký số");
				ftxtSdt.requestFocus();
			}

			//////////////////////////////////////////////////////////////////////
		}

		else if (o.equals(btnChonTour)) {

			/*
			 * Bước 2: Chọn tour cần đăng ký
			 */

			Tour tour = lstTour.getSelectedValue();
			if (tour != null) {
				hienThongTinTour(tour);
				chkCheck.setEnabled(true);
				TienIch.hienAnCacControl(true, spnSoNgLon, spnSoTrEm, btnHuyChonTour, btnDangKy);
			} else {
				JOptionPane.showMessageDialog(this, "Chưa chọn tour đăng ký", "Thông báo", JOptionPane.WARNING_MESSAGE);
			}
			//////////////////////////////////////////////////////

		} else if (o.equals(btnDangKy)) {
			/*
			 * Bước 3: Chọn nút đăng ký tour
			 * 
			 */

			if (kiemTraNhapLieu()) {
				/*
				 * Thông tin khách hàng
				 */

				KhachHang kh = null;

				// Nếu thông tin khách hàng tồn tại

				if (khachHangTim != null) { // thì hiển thị thông tin khách hàng
					kh = khachHangTim;
				} else { // ngược lại thì thêm mới
					kh = themThongTinKhachHang();
				}

				/////////////////////////////////////

				/*
				 * Thông tin phiếu đăng ký
				 */
				PhieuDangKy pdk = new PhieuDangKy();
				String maPDK = TienIch.phatSinhMa(3);
				pdk.setMaPhieuDK("PDK00" + maPDK);
				pdk.setNgayTaoPhieu(new Date(System.currentTimeMillis()));

				int soNguoiLon = ((Number) spnSoNgLon.getValue()).intValue();
				int soTrEm = ((Number) spnSoTrEm.getValue()).intValue();
				pdk.setSoNguoiLon(soNguoiLon);
				pdk.setSoTreEm(soTrEm);
				int soNguoiDK = pdk.tinhSoNguoiThamGia();

				////////////////////////////////////////

				/*
				 * Thông tin tour đăng ký
				 */
				Tour tour = dangKyTourBUS.layTTTourTheoMa(txtMaTour.getText());
				if (soNguoiDK + tour.getSoNguoiDaDangKy() > tour.getSoLuongKhach()) {
					JOptionPane.showMessageDialog(this, "Số khách tham gia vượt quá số lượng quy định");
					return;
				}
				tour.setSoNguoiDaDangKy((tour.getSoNguoiDaDangKy() + soNguoiDK));

				///////////////////////////////////////////

				/*
				 * Tiến hành tạo phiếu đăng ký
				 *
				 */

				// 1. Thêm khách hàng
				pdk.setKh(kh);

				// 2. Thêm tour đăng ký
				pdk.setTour(tour);

				// 3. Thêm nhân viên tạo tour
				pdk.setNv(nv);

				////////////////////////////////////////////////////////////////////

				int sel = JOptionPane.showConfirmDialog(this, "Xác nhận đăng ký tour " + tour.getTenTour() + " ?",
						"Đăng ký tour", JOptionPane.YES_NO_OPTION);

				boolean khachDKMuonThamGiaTour = false;
				if (chkCheck.isSelected()) {
					khachDKMuonThamGiaTour = true;
				}
				if (sel == JOptionPane.YES_OPTION) {
					dlgPhieuThu dlgPhieuThu = new dlgPhieuThu(pdk, khachDKMuonThamGiaTour);
					boolean daThemPhieu = gui.dialog.dlgPhieuThu.isDaThemPhieuDK();

					System.out.println(daThemPhieu);
					if (daThemPhieu) {
						xoaTrangThongTinTour();
						hienDanhSachTour(dangKyTourBUS.layDSTourChuaDangKyTheoMaKH("", pdk.getKh().getMaKH()));
					}

				}
			}

		} else if (o.equals(btnHuy)) {
			lstModel.clear();
			((JTextField) dtcNgSinh.getDateEditor().getUiComponent()).setText("");
			TienIch.xoaTrangCacJTextField(txtCM, txtDC, txtTenKH, txtMaKH, ftxtSdt, txtSoNguoiTG);
			TienIch.hienAnCacControl(false, btnTimNC, txtCM, txtDC, txtTenKH, dtcNgSinh, spnSoNgLon, spnSoTrEm,
					btnDangKy, btnHuy, btnChonTour, btnLamMoi, txtTimKiemTour);
			ftxtSdt.setEditable(true);
			ftxtSdt.requestFocus();
			btnTimKH.setEnabled(true);
			spnSoNgLon.setValue(0);
			spnSoTrEm.setValue(0);
			requestFocus(false);

			xoaTrangThongTinTour();

		} else if (o.equals(btnHuyChonTour)) {
			xoaTrangThongTinTour();
		} else if (o.equals(btnTimNC)) {
			/*
			 * Hiển thị giao diện tìm kiếm nâng cao
			 */
			dlgLocTour dlgLocTour = new dlgLocTour(diaDanhs);

			if (!dlgLocTour.isShowing() && dlgLocTour.isDaChonLoc()) {
				DiaDanh ddLoc = dlgLocTour.getDiaDanhDcChon();

				LocalDate tuNgay = dlgLocTour.getTuNgay() != null ? dlgLocTour.getTuNgay().toLocalDate() : null;
				LocalDate denNgay = dlgLocTour.getDenNgay() != null ? dlgLocTour.getDenNgay().toLocalDate() : null;

				int[] soNguoi = dlgLocTour.getSoNguoi();
				double[] giaTien = dlgLocTour.getGiaTien();

				if (ddLoc != null || tuNgay != null || denNgay != null) {
					List<Tour> dsLoc = new ArrayList<Tour>();

					tours.forEach(x -> {
						// nếu tour có tour thuộc địa danh danh được chọn
						if (x.getDiaDanh().getMaDiaDanh().trim().equals(ddLoc.getMaDiaDanh().trim())) {
							// thì thêm tour đó vào ds lọc
							dsLoc.add(x);

						}
						// nếu ngày khởi hành và kết thúc khác null
						if (tuNgay != null && denNgay != null) {
							LocalDate l1 = x.getNgayKhoiHanh().toLocalDate(), l2 = x.getNgayKetThuc().toLocalDate();

							// nếu ngày khởi hành của tour không trước ngày được chọn và ngày kết thúc không
							// sau ngày kết thúc được chọn
							if (!l1.isBefore(tuNgay) && !l2.isAfter(denNgay)) {
								// thì thêm tour vào danh sách lọc
								dsLoc.add(x);
							}
						}
					});
					hienDanhSachTour(dsLoc);
				}

			}

			///////////////////////////////////////////

		}
	}

	/**
	 * Hàm xoá trắng các trường lưu thông tin tour
	 */
	private void xoaTrangThongTinTour() {
		lstTour.clearSelection();
		TienIch.xoaTrangCacJTextField(txtMaTour, txtTenTour, txtSoNgTD, txtDiaDanh, txtGTreEm, txtGNgLon, txtSoNgTD,
				txtThoiGian);
		txpMoTa.setText("");
		TienIch.hienAnCacControl(false, btnHuyChonTour, spnSoNgLon, spnSoTrEm);
		if (chkCheck.isSelected()) {
			chkCheck.setSelected(false);
		}
		chkCheck.setEnabled(false);
		lblHinhAnh.setIcon(null);
		lblHinhAnh.setText("<Hình ảnh>");
	}

	/**
	 * Hàm thêm thông tin khách hàng
	 * 
	 * @return khách hàng được thêm
	 */
	private KhachHang themThongTinKhachHang() {
		/*
		 * Thêm địa chỉ khách hàng
		 */
		DiaChi dc = new DiaChi();
		dc.setTenDC(txtDC.getText());

		////////////////////////////////

		/*
		 * Thêm khách hàng
		 */
		KhachHang kh = new KhachHang();

		kh.setMaKH(txtMaKH.getText());

		kh.setHoVaTen(txtTenKH.getText());
		kh.setNgaySinh(new Date(dtcNgSinh.getDate().getTime()));

		kh.setSoCMND(txtCM.getText());
		kh.setSoDienThoai(ftxtSdt.getText());

		kh.setDiaChi(dc);
		return kh;

		/////////////////////////////////

	}

	/**
	 * Hàm kiếm tra dữ liệu nhập vào từ bàn phím
	 * 
	 * @return true: nếu dữ liệu đúng / false: nếu không đúng
	 */
	private boolean kiemTraNhapLieu() {
		String maTour = txtMaTour.getText().trim();
		String tenKH = txtTenKH.getText().trim();
		String soCM = txtCM.getText().trim();
		String sdt = ftxtSdt.getText().trim();
		String ngSinh = ((JTextField) dtcNgSinh.getDateEditor().getUiComponent()).getText().trim();
		String dc = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(txtDC.getText().trim());
		int soNgLon = ((Number) spnSoNgLon.getValue()).intValue();
		int soTrEm = ((Number) spnSoTrEm.getValue()).intValue();

		if (maTour.length() == 0 || tenKH.length() == 0 || soCM.length() == 0 || sdt.length() == 0
				|| ngSinh.length() == 0 || dc.length() == 0) {
			if (maTour.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn tour cần đăng ký");
				txtTenKH.requestFocus();
				return false;
			}

			if (tenKH.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập tên Khách hàng");
				txtTenKH.requestFocus();
				return false;
			}
			if (soCM.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập số CMND");
				txtCM.requestFocus();
				return false;
			}
			if (sdt.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập số điện thoại");
				ftxtSdt.requestFocus();
				return false;

			}
			if (ngSinh.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập ngày sinh");
				dtcNgSinh.requestFocus();
				return false;
			}
			if (dc.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập địa chỉ");
				txtDC.requestFocus();
				return false;
			}

			return false;
		}
		/*
		 * Kiểm tra số người tham gia tour
		 */
		if (soNgLon <= 0 && soTrEm <= 0) {

			JOptionPane.showMessageDialog(this, "Số người tham gia phải lớn hơn 0");
			spnSoNgLon.requestFocus();
			return false;
		}
		////////////////////////////////
		/*
		 * Kiểm tra tuổi từ 18 trở lên
		 */
		LocalDate dt = new Date(dtcNgSinh.getDate().getTime()).toLocalDate();
		Period period = Period.between(dt, LocalDate.now());

		if (period.getYears() < 18) {
			JOptionPane.showMessageDialog(this, "Khách hàng phải đủ 18 tuổi mới được đăng ký");
			dtcNgSinh.requestFocus();
			return false;
		}
		///////////////////////////////

		/*
		 * Kiểm tra số điện thoại
		 */
		if (!(sdt.matches("(\\+84|0)[0-9]{9}"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu là 0 hoặc +84 tiếp sau là 10 ký số");
			ftxtSdt.requestFocus();
			return false;
		}

		//////////////////////////////

		/*
		 * Số chứng minh nhân dân (căn cước)
		 */

		if (soCM.matches("[0-9]{9,12}") == false) {
			JOptionPane.showMessageDialog(this, "CMND Gồm 9 hoặc 12 ký tự số");
			txtCM.requestFocus();
			return false;
		}

		//////////////////////////////

		/*
		 * Địa chỉ
		 */
		if (!(dc.matches("[\\w ]{1,30}"))) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không chứa kí tự đặc biệt và không quá 30 kí tự!!!");
			txtDC.requestFocus();
			txtDC.selectAll();
			return false;
		}

		////////////////////////////////////////////////////

		/*
		 * Kiểm tra số người tham gia
		 */

		///////////////////////////////////////////

		return true;

	}

	/**
	 * Hàm hiện thông tin khách hàng
	 * 
	 * @param khTim: khach hàng
	 */
	private void hienThongTinKhachHang(KhachHang khTim) {
		txtMaKH.setText(khTim.getMaKH());
		txtTenKH.setText(khTim.getHoVaTen());
		txtCM.setText(khTim.getSoCMND());

		txtDC.setText(khTim.getDiaChi().getTenDC());

		dtcNgSinh.setDate(khTim.getNgaySinh());
		ftxtSdt.setText(khTim.getSoDienThoai());
	}

	/**
	 * Hiện thông tin chi tiết của tour khi chọn tour trong danh sách
	 * 
	 * @param tour: tour được chọn
	 */
	private void hienThongTinTour(Tour tour) {
		txtMaTour.setText(tour.getMaTour());
		txtTenTour.setText(tour.getTenTour());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		txtThoiGian.setText(sdf.format(tour.getNgayKhoiHanh()) + " - " + sdf.format(tour.getNgayKetThuc()));

		txtTenTour.setText(tour.getTenTour());

		txtGNgLon.setValue(tour.getDonGiaNguoiLon());
		txtGTreEm.setValue(tour.getDonGiaTreEm());

		txpMoTa.setText(tour.getMoTa());

		txtSoNgTD.setText(tour.getSoNguoiDaDangKy() + "/" + tour.getSoLuongKhach());

		DiaDanh diaDanh = new DiaDanh();
		diaDanh.setMaDiaDanh(tour.getDiaDanh().getMaDiaDanh());

		if (diaDanhs.contains(diaDanh)) {
			diaDanh = diaDanhs.get(diaDanhs.indexOf(diaDanh));
			txtDiaDanh.setText(diaDanh.getTenDiaDanh());
		}

		lblHinhAnh.setText("");
		lblHinhAnh.setSize(610, 216);
		if (tour.getHinhAnh() != null) {
			TienIch.chinhKichThuocAnh(lblHinhAnh, new ImageIcon(tour.getHinhAnh()));
		}

	}

	/**
	 * Xử lý sự kiện khi thao tác trên Jspinner
	 * 
	 * @param e
	 */
	public void stateChanged(ChangeEvent e) {
		Object o = e.getSource();
		if (o.equals(spnSoNgLon)) {
			int soNguoiLon = ((Number) spnSoNgLon.getValue()).intValue();
			int soTrEm = ((Number) spnSoTrEm.getValue()).intValue();
			int soNguoiDK = soNguoiLon + soTrEm;

			txtSoNguoiTG.setText(soNguoiDK + "");

		} else if (o.equals(spnSoTrEm)) {
			int soNguoiLon = ((Number) spnSoNgLon.getValue()).intValue();
			int soTrEm = ((Number) spnSoTrEm.getValue()).intValue();
			int soNguoiDK = soNguoiLon + soTrEm;

			txtSoNguoiTG.setText(soNguoiDK + "");
		}

	}
}
