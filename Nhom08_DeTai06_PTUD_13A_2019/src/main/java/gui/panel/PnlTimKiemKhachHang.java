package gui.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import utils.TienIch;

import javax.swing.JFormattedTextField;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Component;

public class PnlTimKiemKhachHang extends JPanel {
	private JComboBox cmbLoaiTK;
	private JTextField txtTuKhoa;
	private JList lstDSKH;

	/**
	 * Create the panel.
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
		pnlTimKiemTTKH.setPreferredSize(new Dimension(10, 150));
		pnlTimKiemTT.add(pnlTimKiemTTKH, BorderLayout.CENTER);

		JLabel lblChon = new JLabel("Chọn loại tìm kiếm");
		lblChon.setFont(new Font("Tahoma", Font.PLAIN, 18));

		cmbLoaiTK = new JComboBox();
		cmbLoaiTK.setModel(new DefaultComboBoxModel(
				new String[] { "-- Chọn --", "Theo tên", "Theo số điện thoại", "Theo số CMND(Căn cước)" }));
		cmbLoaiTK.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lblNhap = new JLabel("Nhập từ khoá:");
		lblNhap.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtTuKhoa = new JTextField();
		txtTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTuKhoa.setColumns(20);

		JPanel pnlDSKhachHang = new JPanel();

		JButton btnTaoMoiKH = new JButton("Tạo mới khách hàng");
		btnTaoMoiKH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_pnlTimKiemTTKH = new GroupLayout(pnlTimKiemTTKH);
		gl_pnlTimKiemTTKH.setHorizontalGroup(
			gl_pnlTimKiemTTKH.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTimKiemTTKH.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTimKiemTTKH.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlDSKhachHang, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_pnlTimKiemTTKH.createSequentialGroup()
							.addComponent(lblChon)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cmbLoaiTK, 0, 0, Short.MAX_VALUE))
						.addComponent(lblNhap)
						.addComponent(txtTuKhoa, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(btnTaoMoiKH, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_pnlTimKiemTTKH.setVerticalGroup(
			gl_pnlTimKiemTTKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTimKiemTTKH.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlTimKiemTTKH.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChon)
						.addComponent(cmbLoaiTK, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNhap)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtTuKhoa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlDSKhachHang, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnTaoMoiKH)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_pnlTimKiemTTKH.linkSize(SwingConstants.VERTICAL, new Component[] {lblChon, cmbLoaiTK});
		pnlDSKhachHang.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSKH = new JScrollPane();
		pnlDSKhachHang.add(scrDSKH, BorderLayout.CENTER);

		lstDSKH = new JList();
		lstDSKH.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrDSKH.setViewportView(lstDSKH);
		pnlTimKiemTTKH.setLayout(gl_pnlTimKiemTTKH);

		JPanel pnlThongTinTK = new JPanel();
		pnlThongTinTK.setBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Th\u00F4ng tin kh\u00E1ch h\u00E0ng",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThongTinTK.setPreferredSize(new Dimension(10, 250));
		pnlTimKiemTT.add(pnlThongTinTK, BorderLayout.SOUTH);
		pnlThongTinTK.setLayout(new BorderLayout(0, 0));

		JPanel pnlDangKyTour = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlDangKyTour.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnlThongTinTK.add(pnlDangKyTour, BorderLayout.SOUTH);

		JButton btnDangKy = new JButton("Đăng ký");
		btnDangKy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDangKy.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlDangKyTour.add(btnDangKy);

		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlTimKiemTT, pnlThongTinTK }, "Arial", Font.PLAIN, 20);

	}
}
