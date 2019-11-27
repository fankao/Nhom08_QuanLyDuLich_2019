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
import javax.swing.ImageIcon;
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

import entities.KhachHangThamGia;
import entities.PhieuDangKy;
import entities.Tour;
import utils.TienIch;

public class pnlQuanLyKhachHang extends JPanel {
	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelPhieuDK;
	private DefaultTableModel modelDsKhachHang;
	private DefaultTableModel modelTour;
	private JTextField txtMaPhieuDK;
	private JTextField txtNgayTao;
	private JTextField txtTrangThai;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSdtKh;
	private JTextField txtDiaChiKH;
	private JTextField txtCMND_KH;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnHuy;
	private List<PhieuDangKy> dsPDK;
	private List<Tour> dsTour;
	private List<KhachHangThamGia> dsKhDiCung;
	private JTable tblDsPDK;
	private JTextField txtTimKiemTour;
	private JTextField txtTimKiemPDK;
	private JTable tblDsTour;
	private JComboBox<String> cmbTimKiemTour;
	private JComboBox<String> cmbTimKiemPDK;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public pnlQuanLyKhachHang() {
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
		pnlTTTour.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		pnlThongTinPhieuDK.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		pnlThongTinPhieuDK.add(panel_2);
				panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		
				JPanel pnlKhachHang = new JPanel();
				panel_2.add(pnlKhachHang);
				pnlKhachHang.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Kh\u00E1ch H\u00E0ng",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				
						JLabel lblMaKH = new JLabel("Mã khách hàng :");
						lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
						
								txtMaKH = new JTextField();
								txtMaKH.setHorizontalAlignment(SwingConstants.LEFT);
								txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
								txtMaKH.setEditable(false);
								txtMaKH.setColumns(10);
								
										JLabel lblTenKH = new JLabel("Tên khách hàng :");
										lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
										
												txtTenKH = new JTextField();
												txtTenKH.setHorizontalAlignment(SwingConstants.LEFT);
												txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
												txtTenKH.setEditable(false);
												txtTenKH.setColumns(10);
												
														JLabel lblSdtKH = new JLabel("Số điện thoại :");
														lblSdtKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
														
																txtSdtKh = new JTextField();
																txtSdtKh.setHorizontalAlignment(SwingConstants.LEFT);
																txtSdtKh.setFont(new Font("Tahoma", Font.PLAIN, 20));
																txtSdtKh.setEditable(false);
																txtSdtKh.setColumns(10);
																
																		JLabel lblCMDN_KH = new JLabel("Số CMND :");
																		lblCMDN_KH.setFont(new Font("Tahoma", Font.PLAIN, 20));
																		
																				JLabel lblDiachiKH = new JLabel("Địa chỉ :");
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
																								gl_pnlKhachHang.setHorizontalGroup(
																									gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
																										.addGroup(gl_pnlKhachHang.createSequentialGroup()
																											.addContainerGap()
																											.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING, false)
																												.addGroup(gl_pnlKhachHang.createSequentialGroup()
																													.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
																														.addComponent(lblMaKH)
																														.addComponent(lblDiachiKH, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
																													.addGap(27)
																													.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING, false)
																														.addComponent(txtDiaChiKH, GroupLayout.PREFERRED_SIZE, 619, GroupLayout.PREFERRED_SIZE)
																														.addGroup(gl_pnlKhachHang.createSequentialGroup()
																															.addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
																															.addGap(54)
																															.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
																																.addComponent(lblCMDN_KH)
																																.addGroup(gl_pnlKhachHang.createSequentialGroup()
																																	.addComponent(lblTenKH, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																																	.addPreferredGap(ComponentPlacement.RELATED)
																																	.addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))))))
																												.addGroup(gl_pnlKhachHang.createSequentialGroup()
																													.addComponent(lblSdtKH)
																													.addGap(44)
																													.addComponent(txtSdtKh, 0, 0, Short.MAX_VALUE)
																													.addGap(213)
																													.addComponent(txtCMND_KH, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)))
																											.addContainerGap(82, Short.MAX_VALUE))
																								);
																								gl_pnlKhachHang.setVerticalGroup(
																									gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
																										.addGroup(gl_pnlKhachHang.createSequentialGroup()
																											.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
																												.addGroup(gl_pnlKhachHang.createSequentialGroup()
																													.addGap(4)
																													.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
																														.addComponent(lblMaKH, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
																														.addComponent(txtMaKH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
																													.addGap(10))
																												.addGroup(gl_pnlKhachHang.createSequentialGroup()
																													.addGap(3)
																													.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
																														.addComponent(lblTenKH, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
																														.addComponent(txtTenKH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
																											.addGap(2)
																											.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
																												.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
																													.addComponent(lblSdtKH, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
																													.addComponent(txtSdtKh, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
																												.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
																													.addComponent(txtCMND_KH, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
																													.addComponent(lblCMDN_KH, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
																											.addPreferredGap(ComponentPlacement.UNRELATED)
																											.addGroup(gl_pnlKhachHang.createParallelGroup(Alignment.BASELINE)
																												.addComponent(lblDiachiKH, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
																												.addComponent(txtDiaChiKH, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
																											.addGap(12))
																								);
																								pnlKhachHang.setLayout(gl_pnlKhachHang);
																								
																										JPanel pnlPDK = new JPanel();
																										panel_2.add(pnlPDK);
																										pnlPDK.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Phi\u1EBFu \u0111\u0103ng k\u00FD",
																												TitledBorder.LEADING, TitledBorder.TOP, null, null));
																										
																												JLabel lblPhieuDK = new JLabel("Mã phiếu đăng ký :");
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
																																		
																																				JLabel lblTrangThai = new JLabel("Trạng thái :");
																																				lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 20));
																																														
																																																txtTrangThai = new JTextField();
																																																txtTrangThai.setHorizontalAlignment(SwingConstants.LEFT);
																																																txtTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 20));
																																																txtTrangThai.setEditable(false);
																																																txtTrangThai.setColumns(10);
																																																		
																																																		JLabel lblTnTour = new JLabel("Tên tour :");
																																																		lblTnTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
																																																		
																																																		textField = new JTextField();
																																																		textField.setHorizontalAlignment(SwingConstants.LEFT);
																																																		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
																																																		textField.setEditable(false);
																																																		textField.setColumns(10);
																																																		
																																																		JLabel lblNgyKhiHnh = new JLabel("Ngày khởi hành :");
																																																		lblNgyKhiHnh.setFont(new Font("Tahoma", Font.PLAIN, 20));
																																																		
																																																		textField_1 = new JTextField();
																																																		textField_1.setText("15\\15\\2222");
																																																		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
																																																		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
																																																		textField_1.setEditable(false);
																																																		textField_1.setColumns(10);
																																																		
																																																		JLabel lblSKhch = new JLabel("Số khách :");
																																																		lblSKhch.setFont(new Font("Tahoma", Font.PLAIN, 20));
																																																		
																																																		textField_2 = new JTextField();
																																																		textField_2.setHorizontalAlignment(SwingConstants.LEFT);
																																																		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
																																																		textField_2.setEditable(false);
																																																		textField_2.setColumns(10);
																																																		
																																																		JLabel lblNgyKtThc = new JLabel("Ngày kết thúc :");
																																																		lblNgyKtThc.setFont(new Font("Tahoma", Font.PLAIN, 20));
																																																		
																																																		textField_3 = new JTextField();
																																																		textField_3.setText("15\\15\\2222");
																																																		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
																																																		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
																																																		textField_3.setEditable(false);
																																																		textField_3.setColumns(10);
																																																		GroupLayout gl_pnlPDK = new GroupLayout(pnlPDK);
																																																		gl_pnlPDK.setHorizontalGroup(
																																																			gl_pnlPDK.createParallelGroup(Alignment.LEADING)
																																																				.addGroup(gl_pnlPDK.createSequentialGroup()
																																																					.addContainerGap()
																																																					.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
																																																						.addComponent(lblPhieuDK, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
																																																						.addComponent(lblTnTour, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
																																																						.addComponent(lblNgyKhiHnh))
																																																					.addGap(7)
																																																					.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
																																																						.addGroup(gl_pnlPDK.createSequentialGroup()
																																																							.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING, false)
																																																								.addComponent(textField)
																																																								.addGroup(gl_pnlPDK.createSequentialGroup()
																																																									.addComponent(txtMaPhieuDK, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
																																																									.addGap(12)
																																																									.addComponent(lblNgayTao, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
																																																									.addGap(5)
																																																									.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
																																																							.addGap(12)
																																																							.addGroup(gl_pnlPDK.createParallelGroup(Alignment.TRAILING)
																																																								.addGroup(gl_pnlPDK.createSequentialGroup()
																																																									.addComponent(lblTrangThai, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
																																																									.addPreferredGap(ComponentPlacement.RELATED))
																																																								.addGroup(Alignment.LEADING, gl_pnlPDK.createSequentialGroup()
																																																									.addComponent(lblSKhch, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
																																																									.addGap(21)))
																																																							.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
																																																								.addComponent(txtTrangThai, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
																																																								.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
																																																						.addGroup(gl_pnlPDK.createSequentialGroup()
																																																							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
																																																							.addGap(35)
																																																							.addComponent(lblNgyKtThc, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
																																																							.addPreferredGap(ComponentPlacement.RELATED)
																																																							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																																																					.addContainerGap())
																																																		);
																																																		gl_pnlPDK.setVerticalGroup(
																																																			gl_pnlPDK.createParallelGroup(Alignment.LEADING)
																																																				.addGroup(gl_pnlPDK.createSequentialGroup()
																																																					.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
																																																						.addGroup(gl_pnlPDK.createSequentialGroup()
																																																							.addGap(16)
																																																							.addComponent(lblTrangThai, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
																																																						.addGroup(gl_pnlPDK.createSequentialGroup()
																																																							.addContainerGap()
																																																							.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
																																																								.addComponent(txtTrangThai, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
																																																								.addGroup(gl_pnlPDK.createSequentialGroup()
																																																									.addGap(3)
																																																									.addComponent(lblPhieuDK, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
																																																								.addComponent(txtMaPhieuDK, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
																																																								.addGroup(gl_pnlPDK.createSequentialGroup()
																																																									.addGap(3)
																																																									.addComponent(lblNgayTao, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
																																																								.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
																																																					.addPreferredGap(ComponentPlacement.RELATED)
																																																					.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
																																																						.addGroup(gl_pnlPDK.createSequentialGroup()
																																																							.addGap(18)
																																																							.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
																																																								.addComponent(lblTnTour)
																																																								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
																																																						.addGroup(gl_pnlPDK.createSequentialGroup()
																																																							.addGap(14)
																																																							.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
																																																								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
																																																								.addComponent(lblSKhch, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
																																																					.addGroup(gl_pnlPDK.createParallelGroup(Alignment.LEADING)
																																																						.addGroup(gl_pnlPDK.createSequentialGroup()
																																																							.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
																																																							.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
																																																								.addComponent(lblNgyKtThc, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
																																																								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
																																																							.addContainerGap())
																																																						.addGroup(gl_pnlPDK.createSequentialGroup()
																																																							.addGap(18)
																																																							.addGroup(gl_pnlPDK.createParallelGroup(Alignment.BASELINE)
																																																								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
																																																								.addComponent(lblNgyKhiHnh, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
																																																							.addContainerGap())))
																																																		);
																																																		pnlPDK.setLayout(gl_pnlPDK);
																																																		
																																																		JPanel panel_3 = new JPanel();
																																																		panel_3.setBorder(new TitledBorder(null, "Danh s\u00E1ch kh\u00E1ch h\u00E0ng \u0111i c\u00F9ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
																																																		pnlThongTinPhieuDK.add(panel_3);
																																																		panel_3.setLayout(new BorderLayout(0, 0));
																																																		
																																																		JScrollPane scrollPane_2 = new JScrollPane();
																																																		panel_3.add(scrollPane_2, BorderLayout.CENTER);
																																																		
																																																		table_2 = new JTable();
																																																		scrollPane_2.setViewportView(table_2);
																																																		
																																																		JPanel panel_4 = new JPanel();
																																																		scrollPane_2.setColumnHeaderView(panel_4);

		JPanel pnlDsKhachHangDiCung = new JPanel();
		pnlDsKhachHangDiCung.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabThongTinPDK_PT.addTab("Danh sách khách hàng", null, pnlDsKhachHangDiCung, null);
		pnlDsKhachHangDiCung.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch phi\u1EBFu thu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDsKhachHangDiCung.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch phi\u1EBFu chi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDsKhachHangDiCung.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel.add(scrollPane_1, BorderLayout.CENTER);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		String[] headerTblDSKH = { "STT", "Mã KH", "Tên khách hàng", "Ngày sinh" };
		modelDsKhachHang = new DefaultTableModel(headerTblDSKH, 0);

		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setPreferredSize(new Dimension(10, 60));
		FlowLayout flowLayout_1 = (FlowLayout) pnlChucNang.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		pnlMain.add(pnlChucNang, BorderLayout.SOUTH);

		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChucNang.add(btnSua);
		btnSua.setEnabled(false);

		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChucNang.add(btnLuu);
		btnLuu.setEnabled(false);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChucNang.add(btnHuy);
		btnHuy.setEnabled(false);
		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] {}, "Tahoma", Font.PLAIN, 18);

	}
}
