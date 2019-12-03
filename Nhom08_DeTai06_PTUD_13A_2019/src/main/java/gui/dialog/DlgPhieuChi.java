package gui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import control.IPhieuThuChiControl;
import control.impl.PhieuThuChiControlImpl;
import entities.KhachHang;
import entities.LoaiPhieu;
import entities.PhieuDangKy;

public class DlgPhieuChi extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtMaPC;
	private JTextField txtNgayTao;
	private JLabel lblTenKH;
	private JLabel lblSDTs;
	private JLabel lblDiaChis;
	private JLabel lblTienChis;
	private PhieuDangKy phieuDangKy;
	private JLabel lblTours;
	private JButton btnLuu;
	private JButton btnHuy;

	private IPhieuThuChiControl phieuThuChiControl;

	/**
	 * Create the dialog.
	 */
	public DlgPhieuChi(PhieuDangKy pdk) {
		this.phieuDangKy = pdk;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgPhieuChi.class.getResource("/images/iconFrm.png")));
		setTitle("Phiếu chi tiền");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 1013, 713);
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
			
			JLabel label_1 = new JLabel("Địa chỉ :12 Nguyễn Văn Bão,phường 3,quận Gò Vấp,Tp HCM");
			panel_3.add(label_1);
			
			JLabel label_2 = new JLabel("SĐT:0123456789");
			panel_3.add(label_2);
			GroupLayout gl_pnlTieuDe = new GroupLayout(pnlTieuDe);
			gl_pnlTieuDe.setHorizontalGroup(
				gl_pnlTieuDe.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_pnlTieuDe.createSequentialGroup()
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 356, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addGap(52)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE))
			);
			gl_pnlTieuDe.setVerticalGroup(
				gl_pnlTieuDe.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_pnlTieuDe.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 101, Short.MAX_VALUE)
						.addGap(57))
					.addGroup(gl_pnlTieuDe.createSequentialGroup()
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
					.addGroup(Alignment.TRAILING, gl_pnlTieuDe.createSequentialGroup()
						.addContainerGap(81, Short.MAX_VALUE)
						.addComponent(lblNewLabel)
						.addGap(41))
			);
			panel.setLayout(new GridLayout(2, 0, 0, 0));

			JPanel panel_2 = new JPanel();
			panel.add(panel_2);

			JLabel lblMaSo = new JLabel("Mã phiếu:");
			lblMaSo.setFont(new Font("Tahoma", Font.PLAIN, 20));

			txtMaPC = new JTextField();
			txtMaPC.setEditable(false);
			txtMaPC.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtMaPC.setColumns(10);
			GroupLayout gl_panel_2 = new GroupLayout(panel_2);
			gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
						.addContainerGap(59, Short.MAX_VALUE)
						.addComponent(lblMaSo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(txtMaPC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(31))
			);
			gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_2.createSequentialGroup()
						.addGap(10)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtMaPC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMaSo))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			panel_2.setLayout(gl_panel_2);

			JPanel panel_1 = new JPanel();
			panel.add(panel_1);

			JLabel lblNgay = new JLabel("Ngày tạo:");
			lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));

			txtNgayTao = new JTextField();
			txtNgayTao.setEditable(false);
			txtNgayTao.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtNgayTao.setColumns(10);
			GroupLayout gl_panel_1 = new GroupLayout(panel_1);
			gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
						.addContainerGap(62, Short.MAX_VALUE)
						.addComponent(lblNgay, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(28))
			);
			gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(10)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtNgayTao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNgay, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			panel_1.setLayout(gl_panel_1);
			pnlTieuDe.setLayout(gl_pnlTieuDe);
		}

		JPanel pnlTTPhieuChi = new JPanel();
		contentPanel.add(pnlTTPhieuChi, BorderLayout.CENTER);
		pnlTTPhieuChi.setLayout(new BorderLayout(0, 0));

		JPanel pnlCTTT = new JPanel();
		pnlTTPhieuChi.add(pnlCTTT, BorderLayout.NORTH);
		pnlCTTT.setLayout(new GridLayout(5, 1, 0, 0));

		JPanel pnlKH = new JPanel();
		pnlCTTT.add(pnlKH);

		JLabel lblKH = new JLabel("Họ tên khách hàng:");
		lblKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlKH.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pnlKH.add(lblKH);

		lblTenKH = new JLabel("Nguyễn Văn A");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlKH.add(lblTenKH);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlCTTT.add(panel);

		JLabel lblTour = new JLabel("Tour đăng ký:");
		lblTour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblTour);

		lblTours = new JLabel("Hạ Long");
		lblTours.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblTours);

		JPanel pnlSDT = new JPanel();
		pnlCTTT.add(pnlSDT);

		JLabel lbl = new JLabel("Số điện thoại:");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlSDT.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pnlSDT.add(lbl);

		lblSDTs = new JLabel("09999999");
		lblSDTs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlSDT.add(lblSDTs);

		JPanel pblDiaChi = new JPanel();
		pnlCTTT.add(pblDiaChi);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pblDiaChi.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pblDiaChi.add(lblDiaChi);

		lblDiaChis = new JLabel("TP HCM");
		lblDiaChis.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pblDiaChi.add(lblDiaChis);

		JPanel pnlTienChi = new JPanel();
		pnlCTTT.add(pnlTienChi);

		JLabel txtSoTien = new JLabel("Số tiền chi:");
		txtSoTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTienChi.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		pnlTienChi.add(txtSoTien);

		lblTienChis = new JLabel("20000");
		lblTienChis.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTienChi.add(lblTienChis);

		JPanel pnlLyDoChi = new JPanel();
		pnlTTPhieuChi.add(pnlLyDoChi, BorderLayout.CENTER);

		JLabel lblLyDo = new JLabel("Lý do chi:");
		lblLyDo.setFont(new Font("Tahoma", Font.PLAIN, 20));

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
										.addComponent(pnlLyDo, GroupLayout.PREFERRED_SIZE, 194,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLyDo))
								.addContainerGap(39, Short.MAX_VALUE)));
		pnlLyDo.setLayout(new BorderLayout(0, 0));

		JScrollPane scrLyDoChi = new JScrollPane();
		pnlLyDo.add(scrLyDoChi, BorderLayout.CENTER);

		JTextPane txpLyDoChi = new JTextPane();
		txpLyDoChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrLyDoChi.setViewportView(txpLyDoChi);
		pnlLyDoChi.setLayout(gl_pnlLyDoChi);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				btnLuu = new JButton("Xuất phiếu chi");
				btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnLuu.setActionCommand("OK");
				buttonPane.add(btnLuu);
				getRootPane().setDefaultButton(btnLuu);
			}
			{
				btnHuy = new JButton("Huỷ bỏ");
				btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnHuy.setActionCommand("Cancel");
				buttonPane.add(btnHuy);
			}
		}
		phieuThuChiControl = new PhieuThuChiControlImpl();
		txtMaPC.setText(phieuThuChiControl.phatSinhMaPhieu(LoaiPhieu.PHIEUCHI));
		txtNgayTao.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		hienThongTinKhachHang(pdk);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		setVisible(true);
	}

	/**
	 * Hiên thông tin phiếu đăng ký tour
	 * 
	 * @param pdk
	 */
	private void hienThongTinKhachHang(PhieuDangKy pdk) {
		KhachHang kh = pdk.getKh();
		lblTenKH.setText(kh.getHoVaTen().trim());
		lblTienChis.setText(pdk.tinhTongTienPDK(pdk.getKhachHangThamGias()) + "");
		lblTours.setText(pdk.getNgayKhoiHanh() + "");
		lblSDTs.setText(kh.getSoDienThoai());
		lblDiaChis.setText(kh.getDiaChi().toString());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLuu)) {
			this.dispose();
		} else if (o.equals(btnHuy)) {
			this.dispose();
		}

	}
}
