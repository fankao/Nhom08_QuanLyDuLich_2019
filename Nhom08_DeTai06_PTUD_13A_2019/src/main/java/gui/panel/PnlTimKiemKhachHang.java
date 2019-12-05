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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.IKhachHangControl;
import control.impl.KhachHangControlImpl;
import entities.KhachHang;
import utils.HintTextFieldUI;
import utils.TienIch;

public class PnlTimKiemKhachHang extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cmbLoaiTK;
	private JTextField txtTuKhoa;
	private JList<KhachHang> lstDSKH;
	private JLabel lblNgaySinhdb;
	private static KhachHang khachHang;

	private IKhachHangControl khachHangControl;
	private static List<KhachHang> dsKhachHang;
	private JLabel lblHoVaTendb;
	private JLabel lblCMNDdb;
	private JLabel lblDienThoaidb;
	private JLabel lblDiaChidb;
	private JLabel lblGioiTinhdb;
	private JButton btnBoChon;

	/**
	 * Khơi tạo giao diện tìm kiếm thông tin khách hàng
	 */
	public PnlTimKiemKhachHang() {
		setLayout(new BorderLayout(0, 0));

		JPanel pnlTimKiemTT = new JPanel();
		pnlTimKiemTT.setBorder(new TitledBorder(
				new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true), new EmptyBorder(2, 2, 2, 2)),
				"T\u00ECm ki\u1EBFm th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		add(pnlTimKiemTT, BorderLayout.CENTER);
		pnlTimKiemTT.setLayout(new BorderLayout(0, 0));

		JPanel pnlTimKiemTTKH = new JPanel();
		pnlTimKiemTT.add(pnlTimKiemTTKH, BorderLayout.CENTER);

		JLabel lblLoaiTK = new JLabel("Tìm kiếm theo:");
		lblLoaiTK.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbLoaiTK = new JComboBox<String>();
		cmbLoaiTK.setModel(new DefaultComboBoxModel(
				new String[] { "-- Chọn --", "Theo tên", "Theo số điện thoại", "Theo số CMND(Căn cước)" }));
		cmbLoaiTK.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblNhap = new JLabel("Nhập từ khoá:");
		lblNhap.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtTuKhoa = new JTextField();
		txtTuKhoa.setEditable(false);
		txtTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTuKhoa.setColumns(20);

		JPanel pnlDSKhachHang = new JPanel();

		btnBoChon = new JButton("Huỷ chọn");
		btnBoChon.setIcon(new ImageIcon(PnlTimKiemKhachHang.class.getResource("/images/cancel_32px.png")));
		btnBoChon.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GroupLayout gl_pnlTimKiemTTKH = new GroupLayout(pnlTimKiemTTKH);
		gl_pnlTimKiemTTKH.setHorizontalGroup(gl_pnlTimKiemTTKH.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTimKiemTTKH.createSequentialGroup().addContainerGap().addGroup(gl_pnlTimKiemTTKH
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlDSKhachHang, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING,
								gl_pnlTimKiemTTKH.createSequentialGroup().addComponent(lblLoaiTK)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(cmbLoaiTK, 0, 0, Short.MAX_VALUE))
						.addComponent(lblNhap, Alignment.LEADING)
						.addComponent(txtTuKhoa, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(btnBoChon, Alignment.LEADING)).addContainerGap()));
		gl_pnlTimKiemTTKH.setVerticalGroup(gl_pnlTimKiemTTKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTimKiemTTKH.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlTimKiemTTKH.createParallelGroup(Alignment.BASELINE).addComponent(lblLoaiTK)
								.addComponent(cmbLoaiTK, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNhap)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtTuKhoa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlDSKhachHang, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBoChon)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_pnlTimKiemTTKH.linkSize(SwingConstants.VERTICAL, new Component[] { lblLoaiTK, cmbLoaiTK });
		pnlDSKhachHang.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSKH = new JScrollPane();
		pnlDSKhachHang.add(scrDSKH, BorderLayout.CENTER);

		lstDSKH = new JList<KhachHang>();
		lstDSKH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstDSKH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrDSKH.setViewportView(lstDSKH);
		pnlTimKiemTTKH.setLayout(gl_pnlTimKiemTTKH);

		JPanel pnlThongTinTK = new JPanel();
		pnlThongTinTK.setPreferredSize(new Dimension(10, 300));
		pnlTimKiemTT.add(pnlThongTinTK, BorderLayout.SOUTH);
		pnlThongTinTK.setLayout(new BorderLayout(0, 0));

		JPanel pnlTTCTKhachHang = new JPanel();
		pnlTTCTKhachHang.setPreferredSize(new Dimension(10, 350));
		pnlTTCTKhachHang.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinTK.add(pnlTTCTKhachHang, BorderLayout.CENTER);
		pnlTTCTKhachHang.setLayout(new GridLayout(6, 0, 0, 0));

		JPanel pnlHoTen = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlHoTen.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		pnlTTCTKhachHang.add(pnlHoTen);

		JLabel lblHoVaTen = new JLabel("Họ và tên:");
		lblHoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlHoTen.add(lblHoVaTen);

		lblHoVaTendb = new JLabel("");
		lblHoVaTendb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlHoTen.add(lblHoVaTendb);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_1.getLayout();
		flowLayout_4.setHgap(10);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		flowLayout_4.setVgap(10);
		pnlTTCTKhachHang.add(panel_1);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(lblGioiTinh);

		lblGioiTinhdb = new JLabel("");
		lblGioiTinhdb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(lblGioiTinhdb);

		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(10);
		pnlTTCTKhachHang.add(panel);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNgaySinh);

		lblNgaySinhdb = new JLabel("");
		lblNgaySinhdb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNgaySinhdb);

		JPanel pnlCMND = new JPanel();
		FlowLayout fl_pnlCMND = (FlowLayout) pnlCMND.getLayout();
		fl_pnlCMND.setAlignment(FlowLayout.LEFT);
		fl_pnlCMND.setVgap(10);
		fl_pnlCMND.setHgap(10);
		pnlTTCTKhachHang.add(pnlCMND);

		JLabel lblCMND = new JLabel("Số CMND:");
		lblCMND.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlCMND.add(lblCMND);

		lblCMNDdb = new JLabel("");
		lblCMNDdb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlCMND.add(lblCMNDdb);

		JPanel pnlSDT = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlSDT.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setVgap(10);
		flowLayout_2.setHgap(10);
		pnlTTCTKhachHang.add(pnlSDT);

		JLabel lblDienThoai = new JLabel("Điện thoại:");
		lblDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlSDT.add(lblDienThoai);

		lblDienThoaidb = new JLabel("");
		lblDienThoaidb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlSDT.add(lblDienThoaidb);

		JPanel pnlDC = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) pnlDC.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setVgap(10);
		flowLayout_3.setHgap(10);
		pnlTTCTKhachHang.add(pnlDC);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDC.add(lblDiaChi);

		lblDiaChidb = new JLabel("");
		lblDiaChidb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDC.add(lblDiaChidb);

		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlTimKiemTT, pnlTTCTKhachHang }, "Arial", Font.PLAIN, 20);

		lblDienThoai.setPreferredSize(lblHoVaTen.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblHoVaTen.getPreferredSize());
		lblCMND.setPreferredSize(lblHoVaTen.getPreferredSize());
		lblDiaChi.setPreferredSize(lblHoVaTen.getPreferredSize());

		khachHangControl = new KhachHangControlImpl();
		dsKhachHang = khachHangControl.layDSKhachHang();
		hienDanhSachKhachHang(dsKhachHang);

		this.setLblHoVaTendb(lblHoVaTendb);
		this.setLblCMNDdb(lblCMNDdb);
		this.setLblNgaySinhdb(lblNgaySinhdb);
		this.setLblDiaChidb(lblDiaChidb);
		this.setLblDienThoaidb(lblDienThoaidb);
		this.setLstDSKH(lstDSKH);

		this.setCmbLoaiTK(cmbLoaiTK);
		this.setTxtTuKhoa(txtTuKhoa);

		ganSuKien();

	}

	private void hienDanhSachKhachHang(List<KhachHang> dsKH) {
		@SuppressWarnings("unchecked")
		DefaultListModel<KhachHang> listModel = new DefaultListModel<KhachHang>();
		listModel.removeAllElements();
		for (KhachHang khachHang : dsKH) {
			listModel.addElement(khachHang);
		}
		this.getLstDSKH().setModel(listModel);

	}

	private void ganSuKien() {
		cmbLoaiTK.addActionListener(this);
		btnBoChon.addActionListener(this);
		lstDSKH.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				PnlDangKyTour.btnXacNhan.setVisible(true);
				khachHang = lstDSKH.getSelectedValue();
				if (khachHang != null) {
					lblHoVaTendb.setText(khachHang.getHoVaTen());
					lblNgaySinhdb.setText(new SimpleDateFormat("dd/MM/yyyy").format(khachHang.getNgaySinh()));
					lblCMNDdb.setText(khachHang.getSoCMND());
					lblDienThoaidb.setText(khachHang.getSoDienThoai());
					lblDiaChidb.setText(khachHang.getDiaChi().toString());
					if (khachHang.isGioiTinh()) {
						lblGioiTinhdb.setText("Nam");
					} else {
						lblGioiTinhdb.setText("Nữ");
					}
				}

			}
		});

		txtTuKhoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTuKhoa.getText().trim()).toLowerCase();
				List<KhachHang> dsKhachHangDB = khachHangControl.layDSKhachHang();
				List<KhachHang> dsKhachHangTim = new ArrayList<KhachHang>();
				for (KhachHang khachHang : dsKhachHangDB) {
					switch (cmbLoaiTK.getSelectedIndex()) {
					case 1:
						String ten = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(khachHang.getHoVaTen().trim())
								.toLowerCase();
						if (ten.contains(key)) {
							dsKhachHangTim.add(khachHang);
						}
						break;
					case 2:
						String sdt = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(khachHang.getSoDienThoai().trim())
								.toLowerCase();
						if (sdt.contains(key)) {
							dsKhachHangTim.add(khachHang);
						}
						break;
					case 3:
						String cmnd = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(khachHang.getSoCMND().trim())
								.toLowerCase();
						if (cmnd.contains(key)) {
							dsKhachHangTim.add(khachHang);
						}
						break;

					default:
						dsKhachHangTim = dsKhachHang;
					}
				}
				hienDanhSachKhachHang(dsKhachHangTim);
			}
		});

	}

	public static KhachHang getKhachHang() {
		return khachHang;
	}

	public static void setKhachHang(KhachHang khachHang) {
		PnlTimKiemKhachHang.khachHang = khachHang;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(cmbLoaiTK)) {
			this.getLstDSKH().clearSelection();
			switch (cmbLoaiTK.getSelectedIndex()) {
			case 1:
				txtTuKhoa.setEditable(true);
				txtTuKhoa.setUI(new HintTextFieldUI("Nhập tên khách hàng", true));
				break;
			case 2:
				txtTuKhoa.setEditable(true);
				txtTuKhoa.setUI(new HintTextFieldUI("Nhập số điện thoại khách hàng", true));
				break;
			case 3:
				txtTuKhoa.setEditable(true);
				txtTuKhoa.setUI(new HintTextFieldUI("Nhập số CMND(thẻ căn cước) khách hàng", true));
				break;
			default:
				txtTuKhoa.setEditable(false);
				txtTuKhoa.setUI(new HintTextFieldUI(""));
				break;
			}
		} else if (o.equals(btnBoChon)) {
			xoaTrangThongTinKH();
		}

	}

	/**
	 * Xoá trăng thông tin khách hàng
	 */
	public void xoaTrangThongTinKH() {
		this.getLstDSKH().clearSelection();
		hienDanhSachKhachHang(dsKhachHang);
		this.getLblHoVaTendb().setText("");
		this.getLblCMNDdb().setText("");
		this.getLblNgaySinhdb().setText("");
		this.getLblGioiTinhdb().setText("");
		this.getLblDiaChidb().setText("");
		this.getLblDienThoaidb().setText("");

		this.getCmbLoaiTK().setSelectedIndex(0);
		this.getTxtTuKhoa().setUI(new HintTextFieldUI(""));

	}

	public JList<KhachHang> getLstDSKH() {
		return lstDSKH;
	}

	public void setLstDSKH(JList<KhachHang> lstDSKH) {
		this.lstDSKH = lstDSKH;
	}

	public JLabel getLblNgaySinhdb() {
		return lblNgaySinhdb;
	}

	public void setLblNgaySinhdb(JLabel lblNgaySinhdb) {
		this.lblNgaySinhdb = lblNgaySinhdb;
	}

	public JLabel getLblHoVaTendb() {
		return lblHoVaTendb;
	}

	public void setLblHoVaTendb(JLabel lblHoVaTendb) {
		this.lblHoVaTendb = lblHoVaTendb;
	}

	public JLabel getLblCMNDdb() {
		return lblCMNDdb;
	}

	public void setLblCMNDdb(JLabel lblCMNDdb) {
		this.lblCMNDdb = lblCMNDdb;
	}

	public JLabel getLblDienThoaidb() {
		return lblDienThoaidb;
	}

	public void setLblDienThoaidb(JLabel lblDienThoaidb) {
		this.lblDienThoaidb = lblDienThoaidb;
	}

	public JLabel getLblDiaChidb() {
		return lblDiaChidb;
	}

	public void setLblDiaChidb(JLabel lblDiaChidb) {
		this.lblDiaChidb = lblDiaChidb;
	}

	public JLabel getLblGioiTinhdb() {
		return lblGioiTinhdb;
	}

	public void setLblGioiTinhdb(JLabel lblGioiTinhdb) {
		this.lblGioiTinhdb = lblGioiTinhdb;
	}

	public JComboBox<String> getCmbLoaiTK() {
		return cmbLoaiTK;
	}

	public void setCmbLoaiTK(JComboBox<String> cmbLoaiTK) {
		this.cmbLoaiTK = cmbLoaiTK;
	}

	public JTextField getTxtTuKhoa() {
		return txtTuKhoa;
	}

	public void setTxtTuKhoa(JTextField txtTuKhoa) {
		this.txtTuKhoa = txtTuKhoa;
	}

}
