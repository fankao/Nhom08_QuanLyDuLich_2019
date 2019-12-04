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
import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import constant.HangSo;
import control.IPhieuThuChiControl;
import control.impl.PhieuThuChiControlImpl;
import entities.KhachHangThamGia;
import entities.LoaiPhieu;
import entities.PhieuDangKy;
import entities.PhieuThuChi;
import gui.FrmPrint;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import utils.TienIch;

/**
 * 
 * @author Gia Hưng, Minh Chiến
 * @version 1.0 Ngày tạo 5/10/2019
 */
public class dlgPhieuThu extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final JPanel pnlNoiDung = new JPanel();
	private JTextField txtMaDK;
	private JTextField txtKH;
	private JTextField txtDC;
	private JTextField txtSoPT;
	private JPanel pnlThanhTien;
	private JPanel pnlNguoiLon;
	private JFormattedTextField txtSoNguoiLon;
	private JFormattedTextField txtDonGiaNL;
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
	private JFormattedTextField txtTienThue;
	private JPanel pnlTongTien;
	private JLabel lblMaPT;
	private JLabel lblSoPT;
	private JLabel lblKH;
	private JLabel lblDC;
	private JLabel lblTieuDe;

	private final Format donVi = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
	private JPanel pnlTour;
	private JLabel lblTour;
	private JTextField txtTour;
	private JLabel lblDienThoai;
	private JTextField txtSDT;
	private JButton btnInPhieu;

	private JPanel pnlLiDo;
	private JPanel pnlCongTien;
	private JPanel panel_3;
	private JFormattedTextField txtCongTien;
	private JLabel lblThanhTienTE;
	private JLabel lblThanhTienNL;
	private JLabel lblCongTien;
	private JTextArea txaNoiDung;

	private IPhieuThuChiControl phieuThuChiControl;
	private JFormattedTextField txtTongTienThanhToan;
	private double thanhTienNguoiLon;
	private double thanhTienTremEm;
	private double tongThanhTien;
	private int soNguoiLon;
	private int soTreEm;
	private double congTien;
	private double tienThue;

	private PhieuDangKy phieuDangKy;
	private PhieuThuChi phieuThuChi;

	/**
	 * Hiện giao diện phiếu thu
	 */
	public dlgPhieuThu(PhieuThuChi ptc, List<KhachHangThamGia> khachThamGias) {
		setModalityType(ModalityType.TOOLKIT_MODAL);
		setIconImage(Toolkit.getDefaultToolkit().getImage(dlgPhieuThu.class.getResource("/images/iconFrm.png")));
		setTitle("Phiếu thu");
		this.phieuThuChi = ptc;
		this.phieuDangKy = phieuThuChi.getPdk();
		setBounds(100, 100, 1244, 856);
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
							.addComponent(pnlTTCongTy, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTieuDe, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE).addGap(82)
							.addGroup(gl_pnlTieuDe.createParallelGroup(Alignment.TRAILING).addComponent(lblSoPT)
									.addComponent(lblNgayTao))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_pnlTieuDe.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtNgayTao).addComponent(txtSoPT))
							.addContainerGap()));
			gl_pnlTieuDe.setVerticalGroup(gl_pnlTieuDe.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTieuDe
					.createSequentialGroup()
					.addGroup(gl_pnlTieuDe.createParallelGroup(Alignment.TRAILING)
							.addComponent(pnlTTCongTy, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, gl_pnlTieuDe.createSequentialGroup().addContainerGap()
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
													GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGroup(Alignment.TRAILING,
							gl_pnlTieuDe.createSequentialGroup().addContainerGap(30, Short.MAX_VALUE)
									.addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGap(10)));
			pnlTTCongTy.setLayout(new GridLayout(3, 0, 0, 0));
			{
				lblTenCty = new JLabel("Công ty du lịch Phương Nam");
				lblTenCty.setFont(new Font("Tahoma", Font.BOLD, 18));
				pnlTTCongTy.add(lblTenCty);
			}
			{
				lblDiaChiCty = new JLabel("Địa chỉ :12 Nguyễn Văn Bão,phường 3,quận Gò Vấp,Tp HCM");
				lblDiaChiCty.setFont(new Font("Tahoma", Font.PLAIN, 14));
				pnlTTCongTy.add(lblDiaChiCty);
			}
			{
				JLabel lblSdtCty = new JLabel("SĐT: 0123456789");
				lblSdtCty.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
				pnlNhapLieu.setBorder(
						new TitledBorder(new LineBorder(new Color(192, 192, 192), 3), "Th\u00F4ng tin phi\u1EBFu thu",
								TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				pnlTTPhieuThu.add(pnlNhapLieu, BorderLayout.CENTER);
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
						txtTour.setColumns(40);
						pnlTour.add(txtTour);
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
				pnlThanhTien = new JPanel();
				pnlThanhTien.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 3),
						"Th\u00F4ng tin thanh to\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				pnlTTPhieuThu.add(pnlThanhTien, BorderLayout.SOUTH);
				pnlThanhTien.setLayout(new GridLayout(4, 1, 0, 0));
				{
					pnlNguoiLon = new JPanel();
					pnlThanhTien.add(pnlNguoiLon);
					pnlNguoiLon.setLayout(new GridLayout(0, 3, 0, 0));

					JPanel pnlSoNguoiLon = new JPanel();
					pnlNguoiLon.add(pnlSoNguoiLon);
					pnlSoNguoiLon.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

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
							lblThanhTienNL = new JLabel("Thành tiền :");
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
					pnlCongTien = new JPanel();
					pnlThanhTien.add(pnlCongTien);
					pnlCongTien.setLayout(new GridLayout(0, 3, 0, 0));
					{
						JPanel panel = new JPanel();
						pnlCongTien.add(panel);
					}
					{
						JPanel panel = new JPanel();
						pnlCongTien.add(panel);
					}
					{
						panel_3 = new JPanel();
						pnlCongTien.add(panel_3);
						{
							lblCongTien = new JLabel("Cộng tiền :");
							lblCongTien.setPreferredSize(new Dimension(100, 30));
							lblCongTien.setHorizontalAlignment(SwingConstants.LEFT);
							lblCongTien.setFont(new Font("Tahoma", Font.PLAIN, 17));
						}
						{
							txtCongTien = new JFormattedTextField(donVi);
							txtCongTien.setFont(new Font("Tahoma", Font.PLAIN, 17));
							txtCongTien.setEditable(false);
							txtCongTien.setColumns(10);
						}
						panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
						panel_3.add(lblCongTien);
						panel_3.add(txtCongTien);
					}
				}
				{
					pnlThueVaTTien = new JPanel();
					pnlThanhTien.add(pnlThueVaTTien);
					{
						pnlTongTien = new JPanel();
						FlowLayout fl_pnlTongTien = (FlowLayout) pnlTongTien.getLayout();
						fl_pnlTongTien.setAlignment(FlowLayout.LEFT);
					}

					JLabel lblThue = new JLabel("Thuế GTGT (VAT): 10%");
					lblThue.setHorizontalAlignment(SwingConstants.TRAILING);
					lblThue.setFont(new Font("Tahoma", Font.PLAIN, 20));

					JLabel lblTngTienf = new JLabel("Tổng tiền thanh toán :");
					lblTngTienf.setHorizontalAlignment(SwingConstants.TRAILING);
					lblTngTienf.setFont(new Font("Tahoma", Font.PLAIN, 20));

					txtTongTienThanhToan = new JFormattedTextField((Format) null);
					txtTongTienThanhToan.setLocale(new Locale("vi", "VN"));
					txtTongTienThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 20));
					txtTongTienThanhToan.setEditable(false);
					txtTongTienThanhToan.setColumns(10);
					GroupLayout gl_pnlThueVaTTien = new GroupLayout(pnlThueVaTTien);
					gl_pnlThueVaTTien
							.setHorizontalGroup(
									gl_pnlThueVaTTien.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_pnlThueVaTTien.createSequentialGroup().addContainerGap()
													.addComponent(lblThue).addGap(29)
													.addComponent(pnlTongTien, GroupLayout.PREFERRED_SIZE, 331,
															GroupLayout.PREFERRED_SIZE)
													.addGap(36)
													.addComponent(lblTngTienf, GroupLayout.PREFERRED_SIZE, 214,
															GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(txtTongTienThanhToan, GroupLayout.PREFERRED_SIZE, 299,
															GroupLayout.PREFERRED_SIZE)
													.addContainerGap(23, Short.MAX_VALUE)));
					gl_pnlThueVaTTien.setVerticalGroup(gl_pnlThueVaTTien.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnlThueVaTTien.createSequentialGroup().addGroup(gl_pnlThueVaTTien
									.createParallelGroup(Alignment.LEADING)
									.addGroup(
											gl_pnlThueVaTTien.createSequentialGroup().addGap(19).addComponent(lblThue))
									.addGroup(gl_pnlThueVaTTien.createSequentialGroup().addContainerGap().addComponent(
											pnlTongTien, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
											Short.MAX_VALUE))
									.addGroup(gl_pnlThueVaTTien.createSequentialGroup().addGap(13)
											.addGroup(gl_pnlThueVaTTien.createParallelGroup(Alignment.TRAILING)
													.addComponent(txtTongTienThanhToan, GroupLayout.PREFERRED_SIZE, 31,
															GroupLayout.PREFERRED_SIZE)
													.addComponent(lblTngTienf, GroupLayout.PREFERRED_SIZE, 25,
															GroupLayout.PREFERRED_SIZE))))
									.addContainerGap()));
					{
						JLabel lblTongTien = new JLabel("Tiền thuế :");
						lblTongTien.setHorizontalAlignment(SwingConstants.TRAILING);
						lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
						pnlTongTien.add(lblTongTien);
						lblTongTien.setPreferredSize(new Dimension(100, 21));
					}
					{
						txtTienThue = new JFormattedTextField(donVi);
						txtTienThue.setLocale(new Locale("vi", "VN"));
						txtTienThue.setFont(new Font("Tahoma", Font.PLAIN, 20));
						txtTienThue.setEditable(false);
						txtTienThue.setColumns(10);
						pnlTongTien.add(txtTienThue);
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
				btnInPhieu = new JButton("In phiếu");
				btnInPhieu.setFont(new Font("Tahoma", Font.PLAIN, 20));
				pnlButton.add(btnInPhieu);
			}
		}
		lblKH.setPreferredSize(lblMaPT.getPreferredSize());
		lblDC.setPreferredSize(lblMaPT.getPreferredSize());
		lblSoTreEm.setPreferredSize(lblSoNguoiLon.getPreferredSize());
		lblTour.setPreferredSize(lblMaPT.getPreferredSize());
		{
			pnlLiDo = new JPanel();
			pnlLiDo.setBorder(new EmptyBorder(0, 2, 0, 0));
			pnlLiDo.setPreferredSize(new Dimension(10, 150));
			pnlNhapLieu.add(pnlLiDo);

			JLabel lblNoiDung = new JLabel("Nội dung:");
			lblNoiDung.setFont(new Font("Tahoma", Font.PLAIN, 17));

			JPanel pnlLyDoNop = new JPanel();
			GroupLayout gl_pnlLiDo = new GroupLayout(pnlLiDo);
			gl_pnlLiDo
					.setHorizontalGroup(gl_pnlLiDo.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnlLiDo.createSequentialGroup().addComponent(lblNoiDung)
									.addPreferredGap(ComponentPlacement.RELATED).addComponent(pnlLyDoNop,
											GroupLayout.PREFERRED_SIZE, 592, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(488, Short.MAX_VALUE)));
			gl_pnlLiDo
					.setVerticalGroup(
							gl_pnlLiDo.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_pnlLiDo.createSequentialGroup().addContainerGap()
											.addGroup(gl_pnlLiDo.createParallelGroup(Alignment.LEADING)
													.addComponent(pnlLyDoNop, GroupLayout.DEFAULT_SIZE, 143,
															Short.MAX_VALUE)
													.addComponent(lblNoiDung))
											.addContainerGap()));
			pnlLyDoNop.setLayout(new BorderLayout(0, 0));

			JScrollPane scrNoiDung = new JScrollPane();
			pnlLyDoNop.add(scrNoiDung, BorderLayout.CENTER);

			txaNoiDung = new JTextArea();
			txaNoiDung.setWrapStyleWord(true);
			txaNoiDung.setFont(new Font("Arial", Font.PLAIN, 15));
			scrNoiDung.setViewportView(txaNoiDung);
			pnlLiDo.setLayout(gl_pnlLiDo);
		}

		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlNhapLieu, pnlThanhTien

		}, "Tahoma", Font.BOLD, 18);
		lblThanhTienNL.setPreferredSize(new Dimension(100, 30));
		lblThanhTienTE.setPreferredSize(new Dimension(100, 30));

		/*
		 * Tạo phiếu thu
		 */
		phieuThuChiControl = new PhieuThuChiControlImpl();
		ptc.setMaPhieuChi(phieuThuChiControl.phatSinhMaPhieu(LoaiPhieu.PHIEUTHU));
		ptc.setNgayTaoPhieuChi(new Date(System.currentTimeMillis()));
		ptc.setLoaiPhieu(LoaiPhieu.PHIEUTHU);

		soNguoiLon = phieuDangKy.tinhSoNguoiTheoDoTuoi(khachThamGias)[0];
		soTreEm = phieuDangKy.tinhSoNguoiTheoDoTuoi(khachThamGias)[1];

		thanhTienNguoiLon = phieuDangKy.tinhThanhTien(khachThamGias)[0];
		thanhTienTremEm = phieuDangKy.tinhThanhTien(khachThamGias)[1];

		congTien = (thanhTienNguoiLon + thanhTienTremEm);
		tienThue = (thanhTienNguoiLon + thanhTienTremEm) * HangSo.THUE;

		tongThanhTien = congTien + tienThue;

		phieuThuChi.setSoTien(tongThanhTien);

		PhieuThuChi phieuThuChiNew = phieuThuChiControl.themPhieu(ptc);
		if (phieuThuChiNew != null) {
			hienThongTinPhieuThu(phieuThuChiNew);
		}

		btnInPhieu.addActionListener(this);

	}

	/**
	 * Hiện thông tin phiếu thu
	 * 
	 * @param phieuThuChi: phiếu thu
	 */
	private void hienThongTinPhieuThu(PhieuThuChi pt) {
		txtSoPT.setText(pt.getMaPhieuChi());
		txtNgayTao.setText(new SimpleDateFormat("dd/MM/yyyy").format(pt.getNgayTaoPhieuChi()));
		txtMaDK.setText(pt.getPdk().getMaPhieuDK());
		txtKH.setText(pt.getPdk().getKh().getHoVaTen());
		txtTour.setText(pt.getPdk().getNgayKhoiHanh().getTour().toString());
		txtDC.setText(pt.getPdk().getKh().getDiaChi().toString());
		txtSDT.setText(pt.getPdk().getKh().getSoDienThoai());

		txtSoNguoiLon.setText(soNguoiLon + "");
		txtSoTreEm.setText(soTreEm + "");

		txtThanhTienNL.setText(thanhTienNguoiLon + "");
		txtThanhTienTE.setText(thanhTienTremEm + "");

		txtDonGiaNL.setText(pt.getPdk().getNgayKhoiHanh().getTour().getDonGiaNguoiLon() + "");
		txtDonGiaTE.setText(pt.getPdk().getNgayKhoiHanh().getTour().getDonGiaTreEm() + "");

		txtCongTien.setText(congTien + "");
		txtTienThue.setText(tienThue + "");
		txtTongTienThanhToan.setText(tongThanhTien + "");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnInPhieu)) {
			if (txaNoiDung.getText().trim().length() != 0) {
				String noiDung = txaNoiDung.getText();
				phieuThuChi.setLyDo(noiDung);
				PhieuThuChi ptc = phieuThuChiControl.suaPhieu(phieuThuChi);
				if (ptc != null) {
					List<Map<String, ?>> dataSource = new ArrayList<Map<String, ?>>();

					Map<String, Object> m = new HashMap<String, Object>();
					m.put("soPhieuThu", txtSoPT.getText());
					m.put("ngayTaoPhieu", txtNgayTao.getText());
					m.put("maPDK", txtMaDK.getText());
					m.put("tenTour", txtTour.getText());
					m.put("tenKhachHang", txtKH.getText());
					m.put("diaChiKH", txtDC.getText());
					m.put("soDienThoai", txtSDT.getText());
					m.put("noiDung", txaNoiDung.getText());
					m.put("soKhachNL", txtSoNguoiLon.getText());
					m.put("soKhachTE", txtSoTreEm.getText());
					m.put("donGiaNL", txtDonGiaNL.getText());
					m.put("donGiaTE", txtDonGiaTE.getText());
					m.put("thanhTienNL", txtThanhTienNL.getText());
					m.put("thanhTienTE", txtThanhTienTE.getText());
					m.put("congTien", txtCongTien.getText());
					m.put("tienThue", txtTienThue.getText());
					m.put("tongTienThanhToan", txtTongTienThanhToan.getText());
					dataSource.add(m);

					JRDataSource Datasour = new JRBeanCollectionDataSource(dataSource);
					String file = "src/main/resources/PhieuThu.jrxml";
					try {
						JasperReport report = JasperCompileManager.compileReport(file);
						JasperPrint filledRedport = JasperFillManager.fillReport(report, null, Datasour);
						this.dispose();
						JRViewer viewer = new JRViewer(filledRedport);
						JFrame frmPrint = new FrmPrint(viewer);
						frmPrint.setVisible(true);
					} catch (JRException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(this, "Lỗi không mở được phiếu thu", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		}

	}

	/*
	 * private boolean kiemTraNhapLieu() { String tenKH =
	 * txtHoTenKHTG.getText().trim();
	 * 
	 * String ngSinh = ((JTextField)
	 * dtcNgaySinh.getDateEditor().getUiComponent()).getText().trim();
	 * 
	 * if (tenKH.length() == 0 || ngSinh.length() == 0) { if (tenKH.length() == 0) {
	 * JOptionPane.showMessageDialog(this, "Chưa nhập tên Khách hàng");
	 * txtHoTenKHTG.requestFocus(); return false; }
	 * 
	 * if (ngSinh.length() == 0) { JOptionPane.showMessageDialog(this,
	 * "Chưa nhập ngày sinh"); dtcNgaySinh.requestFocus(); return false; } return
	 * false; } return true;
	 * 
	 * }
	 */
}
