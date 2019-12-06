package gui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import control.IPhieuThuChiControl;
import control.impl.PhieuThuChiControlImpl;
import entities.KhachHang;
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
import net.sf.jasperreports.swing.JRViewer;

public class DlgPhieuChi extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtMaPC;
	private JTextField txtNgayTao;
	private JLabel lblTenKH;
	private JLabel lblSDTs;
	private JLabel lblDiaChis;
	private JLabel lblTienChis;

	private JLabel lblTours;
	private JButton btnLuu;

	private IPhieuThuChiControl phieuThuChiControl;
	private PhieuDangKy phieuDangKy;
	private PhieuThuChi phieuThuChi;
	private JTextArea txaLyDo;

	/**
	 * Create the dialog.
	 */
	public DlgPhieuChi(PhieuThuChi p, double tienChiTra) {
		this.phieuDangKy = p.getPdk();
		this.phieuThuChi = p;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgPhieuChi.class.getResource("/images/iconFrm.png")));
		setTitle("Phiếu chi tiền");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 1013, 634);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlTieuDe = new JPanel();
			contentPanel.add(pnlTieuDe, BorderLayout.NORTH);

			JLabel lblNewLabel = new JLabel("PHIẾU CHI");
			lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));

			JPanel panel = new JPanel();

			JPanel panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(3, 0, 0, 0));

			JLabel label = new JLabel("Công ty du lịch Phương Nam");
			label.setFont(new Font("Tahoma", Font.BOLD, 18));
			panel_3.add(label);

			JLabel lblaCh = new JLabel("Địa chỉ :12 Nguyễn Văn Bảo,phường 3,quận Gò Vấp,Tp HCM");
			panel_3.add(lblaCh);

			JLabel label_2 = new JLabel("SĐT:0123456789");
			panel_3.add(label_2);
			GroupLayout gl_pnlTieuDe = new GroupLayout(pnlTieuDe);
			gl_pnlTieuDe
					.setHorizontalGroup(gl_pnlTieuDe.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
							gl_pnlTieuDe.createSequentialGroup()
									.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
									.addComponent(lblNewLabel).addGap(52)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)));
			gl_pnlTieuDe
					.setVerticalGroup(gl_pnlTieuDe.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_pnlTieuDe.createSequentialGroup()
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 101, Short.MAX_VALUE).addGap(57))
							.addGroup(
									gl_pnlTieuDe.createSequentialGroup()
											.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 87,
													GroupLayout.PREFERRED_SIZE)
											.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_pnlTieuDe.createSequentialGroup()
									.addContainerGap(81, Short.MAX_VALUE).addComponent(lblNewLabel).addGap(41)));
			panel.setLayout(new GridLayout(2, 0, 0, 0));

			JPanel pnlMaPC = new JPanel();
			panel.add(pnlMaPC);

			JLabel lblMaSo = new JLabel("Mã phiếu:");
			lblMaSo.setFont(new Font("Tahoma", Font.PLAIN, 20));

			txtMaPC = new JTextField();
			txtMaPC.setEditable(false);
			txtMaPC.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtMaPC.setColumns(10);
			GroupLayout gl_pnlMaPC = new GroupLayout(pnlMaPC);
			gl_pnlMaPC.setHorizontalGroup(gl_pnlMaPC.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
					gl_pnlMaPC.createSequentialGroup().addContainerGap(59, Short.MAX_VALUE)
							.addComponent(lblMaSo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(txtMaPC,
									GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(31)));
			gl_pnlMaPC.setVerticalGroup(gl_pnlMaPC.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlMaPC.createSequentialGroup().addGap(10)
							.addGroup(gl_pnlMaPC.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtMaPC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(lblMaSo))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			pnlMaPC.setLayout(gl_pnlMaPC);

			JPanel pnlNgayTaoPC = new JPanel();
			panel.add(pnlNgayTaoPC);

			JLabel lblNgay = new JLabel("Ngày tạo:");
			lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));

			txtNgayTao = new JTextField();
			txtNgayTao.setEditable(false);
			txtNgayTao.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtNgayTao.setColumns(10);
			GroupLayout gl_pnlNgayTaoPC = new GroupLayout(pnlNgayTaoPC);
			gl_pnlNgayTaoPC.setHorizontalGroup(gl_pnlNgayTaoPC.createParallelGroup(Alignment.LEADING).addGroup(
					Alignment.TRAILING,
					gl_pnlNgayTaoPC.createSequentialGroup().addContainerGap(62, Short.MAX_VALUE)
							.addComponent(lblNgay, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(txtNgayTao,
									GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(28)));
			gl_pnlNgayTaoPC.setVerticalGroup(gl_pnlNgayTaoPC.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pnlNgayTaoPC.createSequentialGroup().addGap(10)
							.addGroup(gl_pnlNgayTaoPC.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNgay, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			pnlNgayTaoPC.setLayout(gl_pnlNgayTaoPC);
			pnlTieuDe.setLayout(gl_pnlTieuDe);
		}

		JPanel pnlTTPhieuChi = new JPanel();
		pnlTTPhieuChi.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.add(pnlTTPhieuChi, BorderLayout.CENTER);
		pnlTTPhieuChi.setLayout(new BorderLayout(0, 0));

		JPanel pnlCTTT = new JPanel();
		pnlTTPhieuChi.add(pnlCTTT, BorderLayout.NORTH);
		pnlCTTT.setLayout(new GridLayout(5, 1, 0, 0));

		JPanel pnlKH = new JPanel();
		pnlCTTT.add(pnlKH);

		JLabel lblKH = new JLabel("Họ tên khách hàng:");
		lblKH.setFont(new Font("Arial", Font.BOLD, 20));
		pnlKH.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pnlKH.add(lblKH);

		lblTenKH = new JLabel("Nguyễn Văn A");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlKH.add(lblTenKH);

		JPanel pnlTour = new JPanel();
		FlowLayout fl_pnlTour = (FlowLayout) pnlTour.getLayout();
		fl_pnlTour.setVgap(10);
		fl_pnlTour.setHgap(10);
		fl_pnlTour.setAlignment(FlowLayout.LEFT);
		pnlCTTT.add(pnlTour);

		JLabel lblTour = new JLabel("Tour đăng ký:");
		lblTour.setFont(new Font("Arial", Font.BOLD, 20));
		pnlTour.add(lblTour);

		lblTours = new JLabel("Hạ Long");
		lblTours.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTour.add(lblTours);

		JPanel pnlSDT = new JPanel();
		pnlCTTT.add(pnlSDT);

		JLabel lbl = new JLabel("Số điện thoại:");
		lbl.setFont(new Font("Arial", Font.BOLD, 20));
		pnlSDT.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pnlSDT.add(lbl);

		lblSDTs = new JLabel("09999999");
		lblSDTs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlSDT.add(lblSDTs);

		JPanel pblDiaChi = new JPanel();
		pnlCTTT.add(pblDiaChi);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Arial", Font.BOLD, 20));
		pblDiaChi.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pblDiaChi.add(lblDiaChi);

		lblDiaChis = new JLabel("TP HCM");
		lblDiaChis.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pblDiaChi.add(lblDiaChis);

		JPanel pnlTienChi = new JPanel();
		pnlCTTT.add(pnlTienChi);

		JLabel txtSoTien = new JLabel("Số tiền chi:");
		txtSoTien.setFont(new Font("Arial", Font.BOLD, 20));
		pnlTienChi.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pnlTienChi.add(txtSoTien);

		lblTienChis = new JLabel("20000");
		lblTienChis.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTienChi.add(lblTienChis);

		JPanel pnlLyDoChi = new JPanel();
		pnlTTPhieuChi.add(pnlLyDoChi, BorderLayout.CENTER);

		JLabel lblLyDo = new JLabel("Lý do chi:");
		lblLyDo.setFont(new Font("Arial", Font.BOLD, 20));

		JPanel pnlLyDo = new JPanel();
		GroupLayout gl_pnlLyDoChi = new GroupLayout(pnlLyDoChi);
		gl_pnlLyDoChi
				.setHorizontalGroup(
						gl_pnlLyDoChi.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlLyDoChi.createSequentialGroup().addContainerGap().addComponent(lblLyDo)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(pnlLyDo,
												GroupLayout.PREFERRED_SIZE, 701, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(174, Short.MAX_VALUE)));
		gl_pnlLyDoChi
				.setVerticalGroup(gl_pnlLyDoChi.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlLyDoChi.createSequentialGroup().addContainerGap()
								.addGroup(gl_pnlLyDoChi.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlLyDo, GroupLayout.PREFERRED_SIZE, 118,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLyDo))
								.addContainerGap(99, Short.MAX_VALUE)));
		pnlLyDo.setLayout(new BorderLayout(0, 0));

		JScrollPane scrLyDoChi = new JScrollPane();
		pnlLyDo.add(scrLyDoChi, BorderLayout.CENTER);

		txaLyDo = new JTextArea();
		txaLyDo.setFont(new Font("Arial", Font.PLAIN, 18));
		scrLyDoChi.setViewportView(txaLyDo);
		pnlLyDoChi.setLayout(gl_pnlLyDoChi);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				btnLuu = new JButton("In phiếu chi");
				btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnLuu.setActionCommand("OK");
				buttonPane.add(btnLuu);
				getRootPane().setDefaultButton(btnLuu);
			}
		}
		phieuThuChiControl = new PhieuThuChiControlImpl();
		p.setMaPhieuChi(phieuThuChiControl.phatSinhMaPhieu(LoaiPhieu.PHIEUCHI));
		p.setNgayTaoPhieuChi(new Date(System.currentTimeMillis()));
		p.setSoTien(tienChiTra);
		p.setLoaiPhieu(LoaiPhieu.PHIEUCHI);
		PhieuThuChi phieuThuChiThem = phieuThuChiControl.themPhieu(p);
		if (phieuThuChiThem != null) {
			txtMaPC.setText(p.getMaPhieuChi() + "");
			txtNgayTao.setText(new SimpleDateFormat("dd/MM/yyyy").format(p.getNgayTaoPhieuChi()));
			hienThongTinPhieuChi(phieuThuChi);
		}

		btnLuu.addActionListener(this);
	}

	/**
	 * Hiên thông tin phiếu đăng ký tour
	 * 
	 * @param ptc: phiếu chi
	 */
	private void hienThongTinPhieuChi(PhieuThuChi ptc) {
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "vn"));
		KhachHang kh = ptc.getPdk().getKh();
		lblTenKH.setText(kh.getHoVaTen().trim());
		lblTienChis.setText(format.format(ptc.getSoTien()));
		lblTours.setText(ptc.getPdk().getNgayKhoiHanh() + "");
		lblSDTs.setText(kh.getSoDienThoai());
		lblDiaChis.setText(kh.getDiaChi().toString());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLuu)) {
			if (txaLyDo.getText().trim().length() != 0) {
				String noiDung = txaLyDo.getText();
				phieuThuChi.setLyDo(noiDung);
				PhieuThuChi ptc = phieuThuChiControl.suaPhieu(phieuThuChi);
				if (ptc != null) {
					List<Map<String, ?>> dataSource = new ArrayList<Map<String, ?>>();

					Map<String, Object> m = new HashMap<String, Object>();
					m.put("soPhieuChi", txtMaPC.getText());
					m.put("ngayTaoPhieuThu", txtNgayTao.getText());
					m.put("tenTour", lblTours.getText());
					m.put("tenKhachHang", lblTenKH.getText());
					m.put("diaChi", lblDiaChis.getText());
					m.put("soDienThoai", lblSDTs.getText());
					m.put("soTienChi", lblTienChis.getText());
					m.put("liDoChi", txaLyDo.getText());

					dataSource.add(m);

					JRDataSource Datasour = new JRBeanCollectionDataSource(dataSource);
					String file = "src/main/resources/PhieuChi.jrxml";
					try {
						JasperReport report = JasperCompileManager.compileReport(file);
						JasperPrint filledRedport = JasperFillManager.fillReport(report, null, Datasour);
						this.dispose();
						JRViewer viewer = new JRViewer(filledRedport);
						JFrame frmPrint = new FrmPrint(viewer);
						frmPrint.setVisible(true);
					} catch (JRException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(this, "Lỗi không mở được phiếu chi", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		} else {
			JOptionPane.showMessageDialog(null, "Chưa nhập lý do tạo phiếu chi", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

	}
}
