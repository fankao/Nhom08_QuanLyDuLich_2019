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
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
	private JButton btnXacNhan;
	private JLabel lblNgaySinhdb;
	private KhachHang khachHang;
	private JButton btnDong;

	private IKhachHangControl khachHangControl;
	private static List<KhachHang> dsKhachHang;

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

		JLabel lblChon = new JLabel("Chọn loại tìm kiếm");
		lblChon.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbLoaiTK = new JComboBox<String>();
		cmbLoaiTK.setModel(new DefaultComboBoxModel(
				new String[] { "-- Chọn --", "Theo tên", "Theo số điện thoại", "Theo số CMND(Căn cước)" }));
		cmbLoaiTK.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblNhap = new JLabel("Nhập từ khoá:");
		lblNhap.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtTuKhoa = new JTextField();
		txtTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTuKhoa.setColumns(20);

		JPanel pnlDSKhachHang = new JPanel();
		GroupLayout gl_pnlTimKiemTTKH = new GroupLayout(pnlTimKiemTTKH);
		gl_pnlTimKiemTTKH.setHorizontalGroup(gl_pnlTimKiemTTKH.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTimKiemTTKH.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlTimKiemTTKH.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlDSKhachHang, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
								.addGroup(gl_pnlTimKiemTTKH.createSequentialGroup().addComponent(lblChon)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(cmbLoaiTK, 0, 0, Short.MAX_VALUE))
								.addComponent(lblNhap)
								.addComponent(txtTuKhoa, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
						.addContainerGap()));
		gl_pnlTimKiemTTKH.setVerticalGroup(gl_pnlTimKiemTTKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTimKiemTTKH.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnlTimKiemTTKH.createParallelGroup(Alignment.BASELINE).addComponent(lblChon)
								.addComponent(cmbLoaiTK, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNhap)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtTuKhoa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlDSKhachHang, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(68, Short.MAX_VALUE)));
		gl_pnlTimKiemTTKH.linkSize(SwingConstants.VERTICAL, new Component[] { lblChon, cmbLoaiTK });
		pnlDSKhachHang.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSKH = new JScrollPane();
		pnlDSKhachHang.add(scrDSKH, BorderLayout.CENTER);

		lstDSKH = new JList<KhachHang>();
		lstDSKH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrDSKH.setViewportView(lstDSKH);
		pnlTimKiemTTKH.setLayout(gl_pnlTimKiemTTKH);

		JPanel pnlThongTinTK = new JPanel();
		pnlThongTinTK.setPreferredSize(new Dimension(10, 300));
		pnlTimKiemTT.add(pnlThongTinTK, BorderLayout.SOUTH);
		pnlThongTinTK.setLayout(new BorderLayout(0, 0));

		JPanel pnlXacNhan = new JPanel();
		FlowLayout fl_pnlXacNhan = (FlowLayout) pnlXacNhan.getLayout();
		fl_pnlXacNhan.setAlignment(FlowLayout.RIGHT);
		pnlThongTinTK.add(pnlXacNhan, BorderLayout.SOUTH);

		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setVisible(false);
		btnXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXacNhan.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlXacNhan.add(btnXacNhan);

		btnDong = new JButton("Đóng ");
		btnDong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlXacNhan.add(btnDong);

		JPanel pnlTTCTKhachHang = new JPanel();
		pnlTTCTKhachHang.setPreferredSize(new Dimension(10, 150));
		pnlTTCTKhachHang.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongTinTK.add(pnlTTCTKhachHang, BorderLayout.CENTER);
		pnlTTCTKhachHang.setLayout(new GridLayout(5, 0, 0, 0));

		JPanel pnlHoTen = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlHoTen.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		pnlTTCTKhachHang.add(pnlHoTen);

		JLabel lblHoVaTen = new JLabel("Họ và tên:");
		lblHoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlHoTen.add(lblHoVaTen);

		JLabel lblHoVaTendb = new JLabel("New label");
		lblHoVaTendb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlHoTen.add(lblHoVaTendb);

		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setVgap(10);
		flowLayout_1.setHgap(10);
		pnlTTCTKhachHang.add(panel);

		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNgaySinh);

		lblNgaySinhdb = new JLabel("New label");
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

		JLabel lblCMNDdb = new JLabel("New label");
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

		JLabel lblDienThoaidb = new JLabel("New label");
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

		JLabel lblDiaChidb = new JLabel("New label");
		lblDiaChidb.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDC.add(lblDiaChidb);

		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlTimKiemTT, pnlTTCTKhachHang }, "Arial", Font.PLAIN, 20);

		lblDienThoai.setPreferredSize(lblHoVaTen.getPreferredSize());
		lblNgaySinh.setPreferredSize(lblHoVaTen.getPreferredSize());
		lblCMND.setPreferredSize(lblHoVaTen.getPreferredSize());
		lblDiaChi.setPreferredSize(lblHoVaTen.getPreferredSize());

		khachHangControl = new KhachHangControlImpl();
		dsKhachHang = khachHangControl.layDSKhachHang();
		hienDanhSachKhachHang(dsKhachHang, cmbLoaiTK.getSelectedIndex());

		ganSuKien();

	}

	private void hienDanhSachKhachHang(List<KhachHang> dsKH, int selectedIndex) {
		@SuppressWarnings("unchecked")
		DefaultListModel<KhachHang> listModel = new DefaultListModel<KhachHang>();
		listModel.removeAllElements();
		for (KhachHang khachHang : dsKH) {
			listModel.addElement(khachHang);
		}
		lstDSKH.setModel(listModel);

	}

	private void ganSuKien() {
		btnXacNhan.addActionListener(this);
		btnDong.addActionListener(this);
		cmbLoaiTK.addActionListener(this);

		txtTuKhoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTuKhoa.getText().trim()).toLowerCase();
				switch (cmbLoaiTK.getSelectedIndex()) {
				case 1:
					List<KhachHang> khachHangTheoTen = khachHangControl.layDSKhachHangTheoYeuCau(1, key);
					hienDanhSachKhachHang(khachHangTheoTen, 1);
					break;
				case 2:
					List<KhachHang> khachHangTheoSDT = khachHangControl.layDSKhachHangTheoYeuCau(1, key);
					hienDanhSachKhachHang(khachHangTheoSDT, 2);
					break;
				case 3:
					List<KhachHang> khachHangTheoCMND = khachHangControl.layDSKhachHangTheoYeuCau(1, key);
					hienDanhSachKhachHang(khachHangTheoCMND, 3);
					break;

				default:
					break;
				}
			}
		});

	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXacNhan)) {

		} else if (o.equals(btnDong)) {
			this.getParent().setVisible(false);
		} else if (o.equals(cmbLoaiTK)) {
			switch (cmbLoaiTK.getSelectedIndex()) {
			case 1:
				txtTuKhoa.setUI(new HintTextFieldUI("Nhập tên khách hàng", true));
				break;
			case 2:
				txtTuKhoa.setUI(new HintTextFieldUI("Nhập số điện thoại khách hàng", true));
				break;
			case 3:
				txtTuKhoa.setUI(new HintTextFieldUI("Nhập số CMND(thẻ căn cước) khách hàng", true));
				break;
			default:
				break;
			}
		}

	}
}
