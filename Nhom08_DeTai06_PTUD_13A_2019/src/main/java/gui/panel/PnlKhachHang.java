package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import control.IKhachHangControl;
import control.impl.KhachHangControlImpl;
import entities.DiaChi;
import entities.KhachHang;
import model.DSKhachHangTableModel;
import utils.TienIch;
import utils.address.District;
import utils.address.Province;
import utils.address.Ward;

import javax.swing.ListSelectionModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;

public class PnlKhachHang extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblDSKhachHang;
	private JButton btnSua;
	private JButton btnLuu;
	private JButton btnHuy;
	private JTextField txtHoTen;
	private JTextField txtSoCMND;
	private JTextField txtSoDienThoai;
	private IKhachHangControl khachHangControl;
	private List<KhachHang> dsKH;
	private JScrollPane srcDSKhachHang;
	private JComboBox cmbGioiTinh;
	private JDateChooser dtcNgaySinh;
	private JTextField txtDiaChi;
	private JPanel pnlXemDCKH;
	private JPanel pnlSuaDCKH;
	private JComboBox cmbTinh;
	private JComboBox cmbHuyen;
	private JComboBox cmbXa;
	private List<Province> lstProvices;

	public PnlKhachHang() {
		setLayout(new BorderLayout(0, 0));

		JPanel pnlMain = new JPanel();
		add(pnlMain, BorderLayout.CENTER);
		pnlMain.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(null);
		pnlMain.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);

		JPanel pnk = new JPanel();
		panel.add(pnk, BorderLayout.CENTER);

		JPanel pnlChucNang = new JPanel();
		FlowLayout fl_pnlChucNang = (FlowLayout) pnlChucNang.getLayout();
		fl_pnlChucNang.setAlignment(FlowLayout.RIGHT);

		JPanel pnlTTKH = new JPanel();
		GroupLayout gl_pnk = new GroupLayout(pnk);
		gl_pnk.setHorizontalGroup(
			gl_pnk.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnk.createSequentialGroup()
					.addGap(316)
					.addComponent(pnlTTKH, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
					.addGap(33)
					.addComponent(pnlChucNang, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_pnk.setVerticalGroup(
			gl_pnk.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnk.createSequentialGroup()
					.addGap(6)
					.addComponent(pnlTTKH, GroupLayout.PREFERRED_SIZE, 279, Short.MAX_VALUE)
					.addGap(6))
				.addGroup(Alignment.TRAILING, gl_pnk.createSequentialGroup()
					.addContainerGap(237, Short.MAX_VALUE)
					.addComponent(pnlChucNang, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		JLabel label = new JLabel("Họ và tên :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtHoTen.setColumns(10);

		JLabel label_1 = new JLabel("Gioi tinh :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		cmbGioiTinh = new JComboBox();
		cmbGioiTinh.setEnabled(false);
		cmbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cmbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel label_2 = new JLabel("Ngày sinh :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));

		dtcNgaySinh = new JDateChooser();
		dtcNgaySinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel label_3 = new JLabel("Số CMND :");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSoCMND = new JTextField();
		txtSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoCMND.setColumns(10);

		JLabel label_4 = new JLabel("Số điện thoại :");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoDienThoai.setColumns(10);

		JLabel label_5 = new JLabel("Địa chỉ :");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel panel_1 = new JPanel();
		GroupLayout gl_pnlTTKH = new GroupLayout(pnlTTKH);
		gl_pnlTTKH.setHorizontalGroup(
			gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTTKH.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(txtHoTen))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(cmbGioiTinh, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(dtcNgaySinh, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_pnlTTKH.createSequentialGroup()
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(txtSoCMND))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtSoDienThoai, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_pnlTTKH.setVerticalGroup(
			gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTTKH.createSequentialGroup()
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(13)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(7)
							.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(cmbGioiTinh, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(7)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(dtcNgaySinh, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(17)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(11)
							.addComponent(txtSoCMND, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(21)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(18)
							.addComponent(txtSoDienThoai, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(24)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(new CardLayout(0, 0));

		pnlSuaDCKH = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlSuaDCKH.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_1.add(pnlSuaDCKH, "name_157845119553400");

		cmbTinh = new JComboBox();
		cmbTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlSuaDCKH.add(cmbTinh);

		cmbHuyen = new JComboBox();
		cmbHuyen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlSuaDCKH.add(cmbHuyen);

		cmbXa = new JComboBox();
		cmbXa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlSuaDCKH.add(cmbXa);

		pnlXemDCKH = new JPanel();
		panel_1.add(pnlXemDCKH, "name_157845141604800");
		pnlXemDCKH.setLayout(new BorderLayout(0, 0));

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlXemDCKH.add(txtDiaChi, BorderLayout.CENTER);
		txtDiaChi.setColumns(10);
		pnlTTKH.setLayout(gl_pnlTTKH);

		btnSua = new JButton("Sửa");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChucNang.add(btnSua);

		btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChucNang.add(btnLuu);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChucNang.add(btnHuy);
		pnk.setLayout(gl_pnk);

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setVgap(17);
		panel.add(panel_6, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel_6.add(lblTieuDe);

		JPanel pnlDSKhachHang = new JPanel();
		pnlDSKhachHang.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlMain.add(pnlDSKhachHang);
		pnlDSKhachHang.setLayout(new BorderLayout(0, 0));

		srcDSKhachHang = new JScrollPane();
		pnlDSKhachHang.add(srcDSKhachHang, BorderLayout.CENTER);
		btnSua.setVisible(false);
		btnLuu.setVisible(false);
		btnHuy.setVisible(false);

		tblDSKhachHang = new JTable();
		tblDSKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		khachHangControl = new KhachHangControlImpl();
		dsKH = khachHangControl.layDSKhachHang();
		hienBanThongTinKH(tblDSKhachHang, dsKH, srcDSKhachHang);
		pnlSuaDCKH.setVisible(false);
		pnlXemDCKH.setVisible(true);

		ganSuKien();
	}

	private void hienBanThongTinKH(JTable tbl, List<KhachHang> ds, JScrollPane src) {
		DSKhachHangTableModel dsKhachHangTableModel = new DSKhachHangTableModel(ds);
		tbl.setModel(dsKhachHangTableModel);
		src.setViewportView(tbl);
	}

	private void hienThiThongTinKhachHang(KhachHang kh) {
		txtHoTen.setText(kh.getHoVaTen());
		txtDiaChi.setText(kh.getDiaChi().toString());
		txtSoCMND.setText(kh.getSoCMND());
		txtSoDienThoai.setText(kh.getSoDienThoai());
		if (kh.isGioiTinh() == true)
			cmbGioiTinh.setSelectedIndex(0);
		else
			cmbGioiTinh.setSelectedItem((String) "Nữ");
		dtcNgaySinh.setDate(kh.getNgaySinh());

	}

	private void xoaTrang() {
		txtHoTen.setText("");
		txtSoCMND.setText("");
		txtSoDienThoai.setText("");
		dtcNgaySinh.setDate(null);
		txtDiaChi.setText("");

	}

	/**
	 * Hiện danh sách địa chỉ: Tỉnh/Thành phố; Huyện/Quận/Thị xã; Xã/Phường/Thị trấn
	 * 
	 * @param lstdiaDiem: danh sách dịa chị
	 * @param cmbDiaDiem: combobox chứa tên tương ứng
	 */
	@SuppressWarnings("unchecked")
	private <T> void hienDiaDiem(List<T> lstdiaDiem, JComboBox<T> cmbDiaChi, String tieuDe) {
		DefaultComboBoxModel<T> cmbModel = (DefaultComboBoxModel<T>) cmbDiaChi.getModel();
		cmbModel.removeAllElements();
		Province province = new Province();
		province.setName(tieuDe);
		cmbModel.addElement((T) province);
		for (T x : lstdiaDiem) {
			cmbModel.addElement(x);
		}

	}

	private void ganSuKien() {
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		cmbTinh.addActionListener(this);
		cmbHuyen.addActionListener(this);
		tblDSKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int row = tblDSKhachHang.getSelectedRow();
				if (row == -1)
					return;
				dsKH = khachHangControl.layDSKhachHang();
				KhachHang kh = dsKH.get(row);
				hienThiThongTinKhachHang(kh);
				btnSua.setVisible(true);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLuu)) {
			int row = tblDSKhachHang.getSelectedRow();
			if (row == -1)
				return;
			dsKH = khachHangControl.layDSKhachHang();
			KhachHang khSua = dsKH.get(row);
			khSua.setHoVaTen(txtHoTen.getText().trim());
			khSua.setSoCMND(txtSoCMND.getText().trim());
			khSua.setSoDienThoai(txtSoDienThoai.getText().trim());
			System.out.println(cmbGioiTinh.getSelectedIndex());
			if (cmbGioiTinh.getSelectedIndex() == 0)
				khSua.setGioiTinh(true);
			else if (cmbGioiTinh.getSelectedIndex() == 1)
				khSua.setGioiTinh(false);
			khSua.setNgaySinh(new Date(dtcNgaySinh.getDate().getTime()));
			khSua.setDiaChi(new DiaChi(cmbXa.getSelectedItem().toString(), cmbHuyen.getSelectedItem().toString(), cmbTinh.getSelectedItem().toString()));
			dsKH = khachHangControl.layDSKhachHang();
			hienBanThongTinKH(tblDSKhachHang, dsKH, srcDSKhachHang);
			KhachHang khTest = khachHangControl.suaKhachHang(khSua);
			tblDSKhachHang.setEnabled(true);
			btnSua.setVisible(false);
			btnLuu.setVisible(false);
			btnHuy.setVisible(false);
			pnlSuaDCKH.setVisible(false);
			pnlXemDCKH.setVisible(true);
			
		} else if (o.equals(btnSua)) {

			cmbGioiTinh.setEnabled(true);
			btnSua.setVisible(false);
			btnLuu.setVisible(true);
			btnHuy.setVisible(true);
			tblDSKhachHang.setEnabled(false);
			pnlSuaDCKH.setVisible(true);
			pnlXemDCKH.setVisible(false);
			lstProvices = TienIch.layDiaLyHanhChinh();
			hienDiaDiem(lstProvices, cmbTinh, "Tỉnh/Thành phố");

		} else if (o.equals(btnHuy)) {
			tblDSKhachHang.clearSelection();
			xoaTrang();
			cmbGioiTinh.setEnabled(false);
			tblDSKhachHang.setEnabled(true);
			btnSua.setVisible(false);
			btnLuu.setVisible(false);
			btnHuy.setVisible(false);
			pnlSuaDCKH.setVisible(false);
			pnlXemDCKH.setVisible(true);
		} else if (o.equals(cmbTinh)) {
			if (cmbHuyen.getItemCount() > 1 && cmbXa.getItemCount() > 1) {
				cmbXa.setModel(new DefaultComboBoxModel<Ward>());
				cmbHuyen.setModel(new DefaultComboBoxModel<District>());
			}
			if (cmbTinh.getSelectedIndex() > 0) {
				List<District> districts = lstProvices.get(cmbTinh.getSelectedIndex() - 1).getDistricts();
				hienDiaDiem(districts, cmbHuyen, "Huyện/Quận");
			}

		} else if (o.equals(cmbHuyen)) {
			if (cmbXa.getItemCount() > 1) {
				cmbXa.setModel(new DefaultComboBoxModel<Ward>());
			}
			if (cmbHuyen.getSelectedIndex() > 0) {
				List<Ward> wards = lstProvices.get(cmbTinh.getSelectedIndex() - 1).getDistricts()
						.get(cmbHuyen.getSelectedIndex() - 1).getWards();
				hienDiaDiem(wards, cmbXa, "Xã/Phường");
			}
		}

	}
}
