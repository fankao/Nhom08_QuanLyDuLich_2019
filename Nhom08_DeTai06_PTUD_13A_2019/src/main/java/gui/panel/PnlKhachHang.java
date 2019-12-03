package gui.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import control.IKhachHangControl;
import control.impl.KhachHangControlImpl;
import entities.KhachHang;
import model.DSKhachHangTableModel;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PnlKhachHang extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tblDSKhachHang;
	private JButton btnSua;
	private JButton btnLuu;
	private JButton btnHuy;
	private JTextField txtHoTen;
	private JTextField txtSoCMND;
	private JTextField txtSoDienThoai;
	private JTextField txtDiaChi;
	private IKhachHangControl khachHangControl;
	private List<KhachHang> dsKH;
	private JScrollPane srcDSKhachHang;

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
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		
		JPanel pnlChucNang = new JPanel();
		FlowLayout fl_pnlChucNang = (FlowLayout) pnlChucNang.getLayout();
		fl_pnlChucNang.setAlignment(FlowLayout.RIGHT);
		
		JPanel pnlTTKH = new JPanel();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(319)
					.addComponent(pnlTTKH, GroupLayout.PREFERRED_SIZE, 624, Short.MAX_VALUE)
					.addGap(43)
					.addComponent(pnlChucNang, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
					.addGap(105))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlChucNang, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlTTKH, GroupLayout.PREFERRED_SIZE, 279, Short.MAX_VALUE))
					.addGap(6))
		);
		
		JLabel label = new JLabel("Họ và tên :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtHoTen.setColumns(10);
		
		JLabel label_1 = new JLabel("Gioi tinh :");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JComboBox cmbGioiTinh = new JComboBox();
		cmbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cmbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel label_2 = new JLabel("Ngày sinh :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JDateChooser dtcNgaySinh = new JDateChooser();
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
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		GroupLayout gl_pnlTTKH = new GroupLayout(pnlTTKH);
		gl_pnlTTKH.setHorizontalGroup(
			gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTTKH.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(cmbGioiTinh, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(dtcNgaySinh, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(48)
							.addComponent(txtSoCMND, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtSoDienThoai, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGap(74)
							.addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 467, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		gl_pnlTTKH.setVerticalGroup(
			gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTTKH.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(6)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
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
					.addGap(11)
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(6)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtSoCMND, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(3)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtSoDienThoai, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
							.addGap(6)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
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
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
		flowLayout.setVgap(17);
		panel.add(panel_6, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel_6.add(lblTieuDe);
		
		JPanel pnlDSKhachHang = new JPanel();
		pnlDSKhachHang.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlMain.add(pnlDSKhachHang);
		pnlDSKhachHang.setLayout(new BorderLayout(0, 0));
		
		srcDSKhachHang = new JScrollPane();
		pnlDSKhachHang.add(srcDSKhachHang, BorderLayout.CENTER);
		
		tblDSKhachHang = new JTable();
		srcDSKhachHang.setViewportView(tblDSKhachHang);
		
		tblDSKhachHang = new JTable();
		khachHangControl = new KhachHangControlImpl();
		dsKH = khachHangControl.layDSKhachHang();
		hienBanThongTinKH(tblDSKhachHang, dsKH, srcDSKhachHang);
	}
	private void hienBanThongTinKH(JTable tbl,List<KhachHang> ds,JScrollPane src) {
		DSKhachHangTableModel dsKhachHangTableModel = new DSKhachHangTableModel(ds);
		tbl.setModel(dsKhachHangTableModel);
		src.setViewportView(tbl);
	}
}
