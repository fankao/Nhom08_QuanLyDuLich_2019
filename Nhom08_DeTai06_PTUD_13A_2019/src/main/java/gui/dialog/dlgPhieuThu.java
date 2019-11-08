package gui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import entities.KhachHang;
import entities.KhachHangThamGia;
import entities.NhanVien;
import entities.PhieuDangKy;
import entities.Tour;
import utils.TienIch;
import com.toedter.calendar.JDateChooser;

import bus.IDangKyTourBUS;
import bus.IKhachHangThamGiaBUS;
import bus.impl.DangKyTourBUS;
import bus.impl.KhachHangThamGiaBUS;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalityType;

/**
 * 
 * @author Gia Hưng, Minh Chiến
 * @version 1.0 Ngày tạo 5/10/2019
 */
public class dlgPhieuThu extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel pnlNoiDung = new JPanel();
	private JTextField txtMaDK;
	private JTextField txtNV;
	private JTextField txtKH;
	private JTextField txtDC;
	private JTextField txtSoPT;
	private JPanel pnlThanhTien;
	private JPanel pnlNguoiLon;
	private JFormattedTextField txtSoNguoiLon;
	private JFormattedTextField txtDonGiaNL;
	private JLabel lblThanhTienTE;
	private JFormattedTextField txtThanhTienNL;
	private JPanel pnlTreEm;
	private JLabel lblSoTreEm;
	private JTextField txtSoTreEm;
	private JFormattedTextField txtDonGiaTE;
	private JFormattedTextField txtThanhTienTE;
	private JPanel pnlThueVaTTien;
	private JLabel lblSoNguoiLon;
	private JPanel pnlTTCongTy;
	private JLabel lblTenCty;
	private JLabel lblDiaChiCty;
	private JPanel pnlNhapLieu;
	private JTextField txtNgayTao;
	private JFormattedTextField txtTongTien;
	private JPanel pnlThue;
	private JPanel pnlTongTien;
	private JLabel lblMaPT;
	private JLabel lblSoPT;
	private JLabel lblKH;
	private JLabel lblDC;
	private JLabel lblNV;
	private JLabel lblTieuDe;

	private final Format donVi = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	private JButton btnDong;
	private JPanel pnlTour;
	private JLabel lblTour;
	private JTextField txtTour;
	private JLabel lblDienThoai;
	private JTextField txtSDT;
	private JPanel pnlNhapTT;
	private JPanel pnlBangKH;
	private JLabel lblMaKHTG;
	private JLabel lblNewLabel_1;
	private JTextField txtHoTenKHTG;
	private JLabel lblNgaySinh;
	private JDateChooser dtcNgaySinh;
	private JScrollPane scrDSKH;
	private JTable tblKHTG;
	private JButton btnThemKH;
	private JButton btnLuu;
	private JPanel pnlTTKHTG;
	private IDangKyTourBUS dangKyTourBUS;
	private IKhachHangThamGiaBUS khachHangThamGiaBUS;
	private static List<KhachHangThamGia> dsKHThamGia;
	private static String maKH;

	private static boolean daThemPhieuDK;
	private PhieuDangKy phieuDangKy;
	private boolean khachDKMuonThamGiaTour;

	/**
	 * Hiện giao diện phiếu thu
	 */
	public dlgPhieuThu(PhieuDangKy phieuDangKy, boolean kq) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setIconImage(Toolkit.getDefaultToolkit().getImage(dlgPhieuThu.class.getResource("/images/iconFrm.png")));
		setTitle("Phiếu thu");
		this.phieuDangKy = phieuDangKy;
		this.khachDKMuonThamGiaTour = kq;

		setBounds(100, 100, 1200, 900);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		pnlNoiDung.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnlNoiDung, BorderLayout.CENTER);
		pnlNoiDung.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlTieuDe = new JPanel();
			pnlTieuDe.setPreferredSize(new Dimension(10, 100));
			pnlNoiDung.add(pnlTieuDe, BorderLayout.NORTH);
			{
				lblTieuDe = new JLabel("PHIẾU THU");
				lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
				lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 25));
			}
			{
				lblSoPT = new JLabel("Số phiếu thu:");
				lblSoPT.setFont(new Font("Tahoma", Font.PLAIN, 20));
			}
			{
				txtSoPT = new JTextField();
				txtSoPT.setEditable(false);
				txtSoPT.setFont(new Font("Tahoma", Font.PLAIN, 20));
				txtSoPT.setColumns(10);
			}

			JLabel lblNgayTao = new JLabel("Ngày tạo:");
			lblNgayTao.setFont(new Font("Tahoma", Font.PLAIN, 20));
			pnlTTCongTy = new JPanel();

			txtNgayTao = new JTextField();
			txtNgayTao.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtNgayTao.setEditable(false);
			txtNgayTao.setColumns(10);
			GroupLayout gl_pnlTieuDe = new GroupLayout(pnlTieuDe);
			gl_pnlTieuDe.setHorizontalGroup(gl_pnlTieuDe.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_pnlTieuDe.createSequentialGroup()
							.addComponent(pnlTTCongTy, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
							.addGap(41).addComponent(lblTieuDe, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
							.addGap(67)
							.addGroup(gl_pnlTieuDe.createParallelGroup(Alignment.TRAILING).addComponent(lblSoPT)
									.addComponent(lblNgayTao))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlTieuDe.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtNgayTao).addComponent(txtSoPT))
							.addContainerGap()));
			gl_pnlTieuDe.setVerticalGroup(gl_pnlTieuDe.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTieuDe
					.createSequentialGroup()
					.addGroup(gl_pnlTieuDe.createParallelGroup(Alignment.LEADING)
							.addComponent(pnlTTCongTy, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
							.addGroup(gl_pnlTieuDe.createSequentialGroup().addContainerGap().addGroup(gl_pnlTieuDe
									.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_pnlTieuDe.createSequentialGroup()
											.addGroup(gl_pnlTieuDe.createParallelGroup(Alignment.BASELINE)
													.addComponent(lblSoPT, GroupLayout.PREFERRED_SIZE, 35,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(txtSoPT, GroupLayout.PREFERRED_SIZE, 35,
															GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_pnlTieuDe.createParallelGroup(Alignment.BASELINE)
													.addComponent(lblNgayTao, GroupLayout.PREFERRED_SIZE, 35,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, 35,
															GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(39, Short.MAX_VALUE)));
			pnlTTCongTy.setLayout(new GridLayout(3, 0, 0, 0));
			{
				lblTenCty = new JLabel("Công ty du lịch Phương Nam");
				lblTenCty.setFont(new Font("Tahoma", Font.BOLD, 18));
				pnlTTCongTy.add(lblTenCty);
			}
			{
				lblDiaChiCty = new JLabel("Địa chỉ :12 Nguyễn Văn Bão,phường 3,quận Gò Vấp,Tp HCM");
				pnlTTCongTy.add(lblDiaChiCty);
			}
			{
				JLabel lblSdtCty = new JLabel("SĐT:0123456789");
				pnlTTCongTy.add(lblSdtCty);
			}
			pnlTieuDe.setLayout(gl_pnlTieuDe);
		}
		{
			JPanel pnlTTPhieuThu = new JPanel();
			pnlNoiDung.add(pnlTTPhieuThu, BorderLayout.CENTER);
			pnlTTPhieuThu.setLayout(new BorderLayout(0, 0));
			{
				pnlNhapLieu = new JPanel();
				pnlNhapLieu.setPreferredSize(new Dimension(10, 250));
				pnlNhapLieu.setBorder(
						new TitledBorder(new LineBorder(new Color(192, 192, 192), 3), "Th\u00F4ng tin phi\u1EBFu thu",
								TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				pnlTTPhieuThu.add(pnlNhapLieu, BorderLayout.NORTH);
				pnlNhapLieu.setLayout(new BoxLayout(pnlNhapLieu, BoxLayout.Y_AXIS));
				{
					JPanel pnlTTPT = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnlTTPT.getLayout();
					flowLayout.setAlignment(FlowLayout.LEFT);
					pnlNhapLieu.add(pnlTTPT);
					{
						lblMaPT = new JLabel("Mã phiếu đăng kí:");
						lblMaPT.setFont(new Font("Tahoma", Font.PLAIN, 17));
						pnlTTPT.add(lblMaPT);
					}
					{
						txtMaDK = new JTextField();
						txtMaDK.setPreferredSize(new Dimension(6, 35));
						txtMaDK.setEditable(false);
						txtMaDK.setFont(new Font("Tahoma", Font.PLAIN, 17));
						pnlTTPT.add(txtMaDK);
						txtMaDK.setColumns(20);
					}
				}
				{
					pnlTour = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnlTour.getLayout();
					flowLayout.setAlignment(FlowLayout.LEFT);
					pnlNhapLieu.add(pnlTour);
					{
						lblTour = new JLabel("Tour:");
						lblTour.setFont(new Font("Tahoma", Font.PLAIN, 17));
						pnlTour.add(lblTour);
					}
					{
						txtTour = new JTextField();
						txtTour.setPreferredSize(new Dimension(6, 35));
						txtTour.setFont(new Font("Tahoma", Font.PLAIN, 17));
						txtTour.setEditable(false);
						txtTour.setColumns(20);
						pnlTour.add(txtTour);
					}
				}
				{
					JPanel pnlTTNV = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnlTTNV.getLayout();
					flowLayout.setAlignment(FlowLayout.LEFT);
					pnlNhapLieu.add(pnlTTNV);
					{
						lblNV = new JLabel("Nhân viên:");
						lblNV.setFont(new Font("Tahoma", Font.PLAIN, 17));
						pnlTTNV.add(lblNV);
					}
					{
						txtNV = new JTextField();
						txtNV.setPreferredSize(new Dimension(6, 35));
						txtNV.setEditable(false);
						txtNV.setFont(new Font("Tahoma", Font.PLAIN, 17));
						txtNV.setColumns(20);
						pnlTTNV.add(txtNV);
					}
				}
				{
					JPanel pnlTTKH = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnlTTKH.getLayout();
					flowLayout.setAlignment(FlowLayout.LEFT);
					pnlNhapLieu.add(pnlTTKH);
					{
						lblKH = new JLabel("Khách hàng:");
						lblKH.setFont(new Font("Tahoma", Font.PLAIN, 17));
						pnlTTKH.add(lblKH);
					}
					{
						txtKH = new JTextField();
						txtKH.setPreferredSize(new Dimension(6, 35));
						txtKH.setEditable(false);
						txtKH.setFont(new Font("Tahoma", Font.PLAIN, 17));
						txtKH.setColumns(20);
						pnlTTKH.add(txtKH);
					}
				}
				{
					JPanel pnlTTDC = new JPanel();
					FlowLayout fl_pnlTTDC = (FlowLayout) pnlTTDC.getLayout();
					fl_pnlTTDC.setAlignment(FlowLayout.LEFT);
					pnlNhapLieu.add(pnlTTDC);
					{
						lblDC = new JLabel("Địa chỉ:");
						lblDC.setFont(new Font("Tahoma", Font.PLAIN, 17));
						pnlTTDC.add(lblDC);
					}
					{
						txtDC = new JTextField();
						txtDC.setPreferredSize(new Dimension(6, 35));
						txtDC.setEditable(false);
						txtDC.setFont(new Font("Tahoma", Font.PLAIN, 17));
						pnlTTDC.add(txtDC);
						txtDC.setColumns(20);
					}
					{
						lblDienThoai = new JLabel("Số điện thoại:");
						lblDienThoai.setPreferredSize(new Dimension(132, 21));
						lblDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 17));
						pnlTTDC.add(lblDienThoai);
					}
					{
						txtSDT = new JTextField();
						txtSDT.setText((String) null);
						txtSDT.setPreferredSize(new Dimension(6, 35));
						txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 17));
						txtSDT.setEditable(false);
						txtSDT.setColumns(20);
						pnlTTDC.add(txtSDT);
					}
				}
			}
			{
				pnlTTKHTG = new JPanel();
				pnlTTKHTG.setBorder(new TitledBorder(null, "Th\u00F4ng tin kh\u00E1ch h\u00E0ng tham gia",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				pnlTTPhieuThu.add(pnlTTKHTG, BorderLayout.CENTER);
				pnlTTKHTG.setLayout(new BorderLayout(0, 0));
				{
					pnlNhapTT = new JPanel();
					FlowLayout flowLayout = (FlowLayout) pnlNhapTT.getLayout();
					flowLayout.setHgap(15);
					pnlTTKHTG.add(pnlNhapTT, BorderLayout.NORTH);
					{
						lblMaKHTG = new JLabel("Mã khách hàng:\r\n");
						lblMaKHTG.setFont(new Font("Tahoma", Font.PLAIN, 20));
						pnlNhapTT.add(lblMaKHTG);
					}
					{
						txtMaKHTG = new JTextField();
						txtMaKHTG.setEditable(false);
						txtMaKHTG.setFont(new Font("Tahoma", Font.PLAIN, 20));
						pnlNhapTT.add(txtMaKHTG);
						txtMaKHTG.setColumns(10);
					}
					{
						lblNewLabel_1 = new JLabel("Họ và tên:");
						lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
						pnlNhapTT.add(lblNewLabel_1);
					}
					{
						txtHoTenKHTG = new JTextField();
						txtHoTenKHTG.setFont(new Font("Tahoma", Font.PLAIN, 20));
						pnlNhapTT.add(txtHoTenKHTG);
						txtHoTenKHTG.setColumns(15);
					}
					{
						lblNgaySinh = new JLabel("Ngày sinh:");
						lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
						pnlNhapTT.add(lblNgaySinh);
					}
					{
						dtcNgaySinh = new JDateChooser();
						dtcNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
						dtcNgaySinh.setPreferredSize(new Dimension(150, 30));
						dtcNgaySinh.setDateFormatString("dd/MM/yyyy");
						pnlNhapTT.add(dtcNgaySinh);
					}
					{
						btnThemKH = new JButton("");
						btnThemKH.setBackground(Color.LIGHT_GRAY);
						btnThemKH.setBorder(new EmptyBorder(2, 2, 2, 2));
						btnThemKH.setFocusable(false);
						btnThemKH.setIcon(new ImageIcon(dlgPhieuThu.class.getResource("/images/plus_32px.png")));
						btnThemKH.setFont(new Font("Tahoma", Font.PLAIN, 17));
						btnThemKH.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
							}
						});
						pnlNhapTT.add(btnThemKH);
					}
				}
				{
					pnlBangKH = new JPanel();
					pnlTTKHTG.add(pnlBangKH, BorderLayout.CENTER);
					pnlBangKH.setLayout(new BorderLayout(0, 0));
					{
						scrDSKH = new JScrollPane();
						pnlBangKH.add(scrDSKH, BorderLayout.CENTER);
						{
							tblKHTG = new JTable();
							tblKHTG.setRowHeight(25);
							tblKHTG.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							tblKHTG.setFont(new Font("Tahoma", Font.PLAIN, 18));
							tblKHTG.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
							tblKHTG.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "STT",
									"M\u00E3 KHTG", "H\u1ECD v\u00E0 t\u00EAn", "Ng\u00E0y sinh", "Ghi ch\u00FA" }) {

								private static final long serialVersionUID = 1L;
								Class<?>[] columnTypes = new Class[] { Integer.class, String.class, String.class,
										Object.class, String.class };

								public Class getColumnClass(int columnIndex) {
									return columnTypes[columnIndex];
								}

								boolean[] columnEditables = new boolean[] { true, false, false, false, false };

								public boolean isCellEditable(int row, int column) {
									return columnEditables[column];
								}
							});
							tblKHTG.getColumnModel().getColumn(0).setPreferredWidth(15);
							tblKHTG.getColumnModel().getColumn(1).setResizable(false);
							tblKHTG.getColumnModel().getColumn(2).setResizable(false);
							tblKHTG.getColumnModel().getColumn(2).setPreferredWidth(120);
							tblKHTG.getColumnModel().getColumn(3).setResizable(false);
							tblKHTG.getColumnModel().getColumn(4).setResizable(false);
							tblKHTG.getColumnModel().getColumn(4).setPreferredWidth(125);
							scrDSKH.setViewportView(tblKHTG);
						}
					}
				}
			}
			{
				pnlThanhTien = new JPanel();
				pnlThanhTien.setPreferredSize(new Dimension(10, 200));
				pnlThanhTien.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 3),
						"Th\u00F4ng tin thanh to\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				pnlTTPhieuThu.add(pnlThanhTien, BorderLayout.SOUTH);
				pnlThanhTien.setLayout(new GridLayout(3, 1, 0, 0));
				{
					pnlNguoiLon = new JPanel();
					pnlThanhTien.add(pnlNguoiLon);
					pnlNguoiLon.setLayout(new GridLayout(0, 3, 0, 0));

					JPanel pnlSoNguoiLon = new JPanel();
					pnlNguoiLon.add(pnlSoNguoiLon);
					pnlSoNguoiLon.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

					lblSoNguoiLon = new JLabel("Người lớn :");
					lblSoNguoiLon.setFont(new Font("Tahoma", Font.PLAIN, 17));
					lblSoNguoiLon.setHorizontalAlignment(SwingConstants.LEFT);
					pnlSoNguoiLon.add(lblSoNguoiLon);

					txtSoNguoiLon = new JFormattedTextField();
					txtSoNguoiLon.setEditable(false);
					txtSoNguoiLon.setFont(new Font("Tahoma", Font.PLAIN, 17));
					pnlSoNguoiLon.add(txtSoNguoiLon);
					txtSoNguoiLon.setColumns(10);
					{
						JPanel pnlDonGiaNL = new JPanel();
						FlowLayout fl_pnlDonGiaNL = (FlowLayout) pnlDonGiaNL.getLayout();
						fl_pnlDonGiaNL.setAlignment(FlowLayout.LEFT);
						fl_pnlDonGiaNL.setVgap(0);
						pnlNguoiLon.add(pnlDonGiaNL);
						{
							JLabel lblDonGiaNL = new JLabel("Đơn giá :");
							lblDonGiaNL.setHorizontalAlignment(SwingConstants.LEFT);
							lblDonGiaNL.setFont(new Font("Tahoma", Font.PLAIN, 17));
							pnlDonGiaNL.add(lblDonGiaNL);
						}
						{
							txtDonGiaNL = new JFormattedTextField(donVi);
							txtDonGiaNL.setEditable(false);
							txtDonGiaNL.setFont(new Font("Tahoma", Font.PLAIN, 17));
							txtDonGiaNL.setColumns(10);
							pnlDonGiaNL.add(txtDonGiaNL);
						}
					}

					{

						JPanel pnlThanhTienNL = new JPanel();
						FlowLayout fl_pnlThanhTienNL = (FlowLayout) pnlThanhTienNL.getLayout();
						fl_pnlThanhTienNL.setAlignment(FlowLayout.LEFT);
						fl_pnlThanhTienNL.setVgap(0);
						pnlNguoiLon.add(pnlThanhTienNL);
						{
							lblThanhTienTE = new JLabel("Thành tiền :");
							lblThanhTienTE.setHorizontalAlignment(SwingConstants.LEFT);
							lblThanhTienTE.setFont(new Font("Tahoma", Font.PLAIN, 17));
							pnlThanhTienNL.add(lblThanhTienTE);
						}
						{
							txtThanhTienNL = new JFormattedTextField(donVi);
							txtThanhTienNL.setEditable(false);
							txtThanhTienNL.setFont(new Font("Tahoma", Font.PLAIN, 17));
							txtThanhTienNL.setColumns(10);
							pnlThanhTienNL.add(txtThanhTienNL);
						}
					}
				}
				{
					pnlTreEm = new JPanel();
					pnlThanhTien.add(pnlTreEm);
					pnlTreEm.setLayout(new GridLayout(0, 3, 0, 0));
					{
						JPanel pnlSoTreEm = new JPanel();
						FlowLayout fl_pnlSoTreEm = (FlowLayout) pnlSoTreEm.getLayout();
						fl_pnlSoTreEm.setAlignment(FlowLayout.LEFT);
						pnlTreEm.add(pnlSoTreEm);
						{
							lblSoTreEm = new JLabel("Trẻ em :");
							lblSoTreEm.setHorizontalAlignment(SwingConstants.LEFT);
							lblSoTreEm.setFont(new Font("Tahoma", Font.PLAIN, 17));
							pnlSoTreEm.add(lblSoTreEm);
						}
						{
							txtSoTreEm = new JTextField();
							txtSoTreEm.setFont(new Font("Tahoma", Font.PLAIN, 17));
							txtSoTreEm.setEditable(false);
							txtSoTreEm.setColumns(10);
							pnlSoTreEm.add(txtSoTreEm);
						}
					}
					{
						JPanel pnlDonGiaTE = new JPanel();
						FlowLayout fl_pnlDonGiaTE = (FlowLayout) pnlDonGiaTE.getLayout();
						fl_pnlDonGiaTE.setAlignment(FlowLayout.LEFT);
						pnlTreEm.add(pnlDonGiaTE);
						{
							JLabel lblDonGiaTE = new JLabel("Đơn giá :");
							lblDonGiaTE.setHorizontalAlignment(SwingConstants.LEFT);
							lblDonGiaTE.setFont(new Font("Tahoma", Font.PLAIN, 17));
							pnlDonGiaTE.add(lblDonGiaTE);
						}
						{
							txtDonGiaTE = new JFormattedTextField(donVi);
							txtDonGiaTE.setFont(new Font("Tahoma", Font.PLAIN, 17));
							txtDonGiaTE.setEditable(false);
							txtDonGiaTE.setColumns(10);
							pnlDonGiaTE.add(txtDonGiaTE);
						}
					}
					{
						JPanel pnlThanhTienTE = new JPanel();
						FlowLayout fl_pnlThanhTienTE = (FlowLayout) pnlThanhTienTE.getLayout();
						fl_pnlThanhTienTE.setAlignment(FlowLayout.LEFT);
						pnlTreEm.add(pnlThanhTienTE);
						{
							JLabel lblThanhTienNL = new JLabel("Thành tiền :");
							lblThanhTienNL.setHorizontalAlignment(SwingConstants.LEFT);
							lblThanhTienNL.setFont(new Font("Tahoma", Font.PLAIN, 17));
							pnlThanhTienTE.add(lblThanhTienNL);
						}
						{
							txtThanhTienTE = new JFormattedTextField(donVi);
							txtThanhTienTE.setFont(new Font("Tahoma", Font.PLAIN, 17));
							txtThanhTienTE.setEditable(false);
							txtThanhTienTE.setColumns(10);
							pnlThanhTienTE.add(txtThanhTienTE);
						}
					}
				}
				{
					pnlThueVaTTien = new JPanel();
					pnlThanhTien.add(pnlThueVaTTien);
					{
						pnlThue = new JPanel();
						pnlThue.setBorder(new EmptyBorder(5, 0, 5, 50));
					}
					{
						pnlTongTien = new JPanel();
						FlowLayout fl_pnlTongTien = (FlowLayout) pnlTongTien.getLayout();
						fl_pnlTongTien.setAlignment(FlowLayout.LEFT);
					}
					GroupLayout gl_pnlThueVaTTien = new GroupLayout(pnlThueVaTTien);
					gl_pnlThueVaTTien.setHorizontalGroup(gl_pnlThueVaTTien.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnlThueVaTTien.createSequentialGroup()
									.addComponent(pnlThue, GroupLayout.PREFERRED_SIZE, 658, GroupLayout.PREFERRED_SIZE)
									.addGap(111)
									.addComponent(pnlTongTien, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
									.addGap(60)));
					gl_pnlThueVaTTien
							.setVerticalGroup(gl_pnlThueVaTTien.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_pnlThueVaTTien.createSequentialGroup()
											.addComponent(pnlThue, GroupLayout.PREFERRED_SIZE, 47,
													GroupLayout.PREFERRED_SIZE)
											.addGap(0))
									.addGroup(gl_pnlThueVaTTien
											.createSequentialGroup().addGap(7).addComponent(pnlTongTien,
													GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(6)));
					pnlThue.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));

					JLabel lblThue = new JLabel("Thuế GTGT (VAT): 10%");
					lblThue.setHorizontalAlignment(SwingConstants.TRAILING);
					lblThue.setFont(new Font("Tahoma", Font.PLAIN, 20));
					pnlThue.add(lblThue);
					{
						JLabel lblTongTien = new JLabel("Tổng tiền :");
						lblTongTien.setHorizontalAlignment(SwingConstants.TRAILING);
						lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
						pnlTongTien.add(lblTongTien);
						lblTongTien.setPreferredSize(new Dimension(100, 21));
					}
					{
						txtTongTien = new JFormattedTextField(donVi);
						txtTongTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
						txtTongTien.setEditable(false);
						txtTongTien.setColumns(10);
						pnlTongTien.add(txtTongTien);
					}
					pnlThueVaTTien.setLayout(gl_pnlThueVaTTien);
				}
			}
		}
		{
			JPanel pnlButton = new JPanel();
			pnlButton.setLayout(new FlowLayout(FlowLayout.RIGHT));

			getContentPane().add(pnlButton, BorderLayout.SOUTH);
			{
				btnLuu = new JButton("Lưu phiếu đăng ký");
				btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 20));
				pnlButton.add(btnLuu);
			}
			{
				btnDong = new JButton("Đóng");
				btnDong.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnDong.setActionCommand("OK");
				pnlButton.add(btnDong);
				getRootPane().setDefaultButton(btnDong);
			}
		}
		lblNV.setPreferredSize(lblMaPT.getPreferredSize());
		lblKH.setPreferredSize(lblMaPT.getPreferredSize());
		lblDC.setPreferredSize(lblMaPT.getPreferredSize());
		lblSoTreEm.setPreferredSize(lblSoNguoiLon.getPreferredSize());
		lblTour.setPreferredSize(lblMaPT.getPreferredSize());

		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlNhapLieu, pnlThanhTien, pnlTTKHTG

		}, "Tahoma", Font.BOLD, 18);
		TienIch.chinhKichThuocTable(tblKHTG, tblKHTG.getColumnModel().getTotalColumnWidth(), 0.1, 10, 20, 10, 30);

		dangKyTourBUS = new DangKyTourBUS();
		khachHangThamGiaBUS = new KhachHangThamGiaBUS();

		dsKHThamGia = new ArrayList<KhachHangThamGia>();
		((JTextField) dtcNgaySinh.getDateEditor().getUiComponent()).setEditable(false);
		maKH = TienIch.phatSinhMa(4);
		txtMaKHTG.setText("KHTG00" + maKH);

		// hiện thông tin phiếu thu
		hienThongTinPhieuThu();

		btnDong.addActionListener(this);
		btnThemKH.addActionListener(this);
		btnLuu.addActionListener(this);

		/*
		 * Nếu khách hàng đăng ký muốn tham gia tour
		 */
		if (khachDKMuonThamGiaTour) {
			String ma = TienIch.phatSinhMa(4);
			KhachHangThamGia khtg = new KhachHangThamGia("KHTG00" + ma, phieuDangKy.getKh().getHoVaTen(),
					phieuDangKy.getKh().getNgaySinh());
			dsKHThamGia.add(khtg);
			TienIch.capNhatMaPhatSinh(4, ma);
			txtMaKHTG.setText("KHTG00" + TienIch.phatSinhMa(4));
			hienBangDSKHThamGiaTour(dsKHThamGia);
		}
		setVisible(true);

	}

	/**
	 * Hàm hiện thông tin phiếu đăng ký
	 */
	private void hienThongTinPhieuThu() {
		txtSoPT.setText(phieuDangKy.getMaPhieuDK().trim());
		txtMaDK.setText(phieuDangKy.getMaPhieuDK().trim());
		txtTour.setText(phieuDangKy.getTour().getMaTour().trim() + "-" + phieuDangKy.getTour().getTenTour().trim());

		txtNgayTao.setText(new SimpleDateFormat("dd/MM/yyyy").format(phieuDangKy.getNgayTaoPhieu()));
		txtNV.setText(phieuDangKy.getNv().getMaNV().trim() + " - " + phieuDangKy.getNv().getHoVaTen());
		txtKH.setText(phieuDangKy.getKh().getMaKH().trim() + " - " + phieuDangKy.getKh().getHoVaTen());
		txtSDT.setText(phieuDangKy.getKh().getSoDienThoai());

		txtSoNguoiLon.setText(phieuDangKy.getSoNguoiLon() + "");
		txtDonGiaNL.setValue(phieuDangKy.getTour().getDonGiaNguoiLon());
		txtThanhTienNL.setValue(phieuDangKy.getTour().getDonGiaNguoiLon() * phieuDangKy.getSoNguoiLon());

		txtSoTreEm.setText(phieuDangKy.getSoTreEm() + "");
		txtDonGiaTE.setValue(phieuDangKy.getTour().getDonGiaTreEm());
		txtThanhTienTE.setValue(phieuDangKy.getTour().getDonGiaTreEm() * phieuDangKy.getSoTreEm());

		txtTongTien.setValue(phieuDangKy.tinhTongThanhTien());

		txtDC.setText(phieuDangKy.getKh().getDiaChi().getTenDC());

	}

	private JTextField txtMaKHTG;

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnDong)) {
			this.dispose();
		} else if (o.equals(btnThemKH)) {
			if (kiemTraNhapLieu()) {
				KhachHangThamGia kh = new KhachHangThamGia();
				kh.setMaKHTG(txtMaKHTG.getText());
				kh.setHoTenKHTG(txtHoTenKHTG.getText());
				kh.setNgaySinh(new Date(System.currentTimeMillis()));
				dsKHThamGia.add(kh);
				if (dsKHThamGia.size() < phieuDangKy.tinhSoNguoiThamGia()) {
					TienIch.capNhatMaPhatSinh(4, maKH);
					maKH = TienIch.phatSinhMa(4);
					txtMaKHTG.setText("KHTG00" + maKH);

					txtHoTenKHTG.requestFocus();

				} else {
					txtHoTenKHTG.setRequestFocusEnabled(false);
					btnThemKH.setEnabled(false);
					txtHoTenKHTG.setText("");
					txtHoTenKHTG.setEditable(false);
				}
				
				txtHoTenKHTG.setText("");
				txtMaKHTG.setText("");
				hienBangDSKHThamGiaTour(dsKHThamGia);

			}

		} else if (o.equals(btnLuu)) {
			if (dsKHThamGia.size() != 0) {
				int confim = JOptionPane.showConfirmDialog(this, "Xác nhận lưu phiếu đăng ký ?", "Xác nhận",
						JOptionPane.YES_NO_OPTION);
				if (confim == JOptionPane.YES_OPTION) {
					PhieuDangKy pdk = dangKyTourBUS.taoPhieuDangKy(phieuDangKy);
					if (pdk != null) {
						dsKHThamGia.forEach(x -> {
							x.setPhieuDangKy(pdk);
							KhachHangThamGia khtg = khachHangThamGiaBUS.themKhachHangTG(x);
						});
						String maPDK = TienIch.phatSinhMa(3);
						TienIch.capNhatMaPhatSinh(3, maPDK);
						TienIch.capNhatMaPhatSinh(2, pdk.getKh().getMaKH().trim().substring(4));
						setDaThemPhieuDK(true);
						this.dispose();
					}

				}
			} else {
				JOptionPane.showMessageDialog(this, "Chưa nhập thông tin khách tham gia", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	/**
	 * Hiển danh sách khách hàng đăng ký tour
	 * 
	 * @param ds
	 */
	private void hienBangDSKHThamGiaTour(List<KhachHangThamGia> ds) {
		DefaultTableModel tblModel = (DefaultTableModel) tblKHTG.getModel();
		tblModel.setRowCount(0);
		int i = 1;
		for (KhachHangThamGia khtg : ds) {
			tblModel.addRow(new Object[] { i++, khtg.getMaKHTG(), khtg.getHoTenKHTG(), khtg.getNgaySinh() });
		}

	}

	private boolean kiemTraNhapLieu() {
		String tenKH = txtHoTenKHTG.getText().trim();

		String ngSinh = ((JTextField) dtcNgaySinh.getDateEditor().getUiComponent()).getText().trim();

		if (tenKH.length() == 0 || ngSinh.length() == 0) {
			if (tenKH.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập tên Khách hàng");
				txtHoTenKHTG.requestFocus();
				return false;
			}

			if (ngSinh.length() == 0) {
				JOptionPane.showMessageDialog(this, "Chưa nhập ngày sinh");
				dtcNgaySinh.requestFocus();
				return false;
			}
			return false;
		}
		return true;

	}

	public static boolean isDaThemPhieuDK() {
		return daThemPhieuDK;
	}

	public static void setDaThemPhieuDK(boolean daThemPhieuDKs) {

		daThemPhieuDK = daThemPhieuDKs;

	}

}
