package gui.panel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import com.toedter.calendar.JDateChooser;

import control.IKhachHangControl;
import control.impl.KhachHangControlImpl;
import entities.DiaChi;
import entities.KhachHang;
import model.DSKhachHangTableModel;
import utils.HintTextFieldUI;
import utils.TienIch;
import utils.address.District;
import utils.address.Province;
import utils.address.Ward;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;

/**
 * PnlKhachHang.java
 * 
 * @author Thanh Trí <br>
 *         Ngày tạo: 01/12/2019
 *
 */
public class PnlKhachHang extends JPanel implements ActionListener {
	/**
	 * Khởi tạo các thuộc tính
	 */
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
	private JTextField txtTimKiem;
	private JComboBox cmbLoaiTimKiem;
	private JButton btnLamMoi;

	/**
	 * Hiển thị giao diện khách hàng
	 */
	public PnlKhachHang() {
		setLayout(new BorderLayout(0, 0));

		JPanel pnlMain = new JPanel();
		add(pnlMain, BorderLayout.CENTER);
		pnlMain.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pnlTTKhachHang = new JPanel();
		pnlTTKhachHang.setBorder(null);
		pnlMain.add(pnlTTKhachHang);
		pnlTTKhachHang.setLayout(new BorderLayout(0, 0));

		JPanel pnlKhachHang = new JPanel();
		pnlTTKhachHang.add(pnlKhachHang, BorderLayout.CENTER);

		JPanel pnlChucNang = new JPanel();
		FlowLayout fl_pnlChucNang = (FlowLayout) pnlChucNang.getLayout();
		fl_pnlChucNang.setAlignment(FlowLayout.LEFT);

		JPanel pnlTTKH = new JPanel();
		GroupLayout gl_pnlKhachHang = new GroupLayout(pnlKhachHang);
		gl_pnlKhachHang.setHorizontalGroup(gl_pnlKhachHang.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlKhachHang.createSequentialGroup().addGap(316)
						.addComponent(pnlTTKH, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE).addGap(33)
						.addComponent(pnlChucNang, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_pnlKhachHang.setVerticalGroup(gl_pnlKhachHang.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlKhachHang.createSequentialGroup().addGap(6)
						.addComponent(pnlTTKH, GroupLayout.PREFERRED_SIZE, 279, Short.MAX_VALUE).addGap(6))
				.addGroup(Alignment.TRAILING, gl_pnlKhachHang
						.createSequentialGroup().addContainerGap(237, Short.MAX_VALUE).addComponent(pnlChucNang,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

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
		dtcNgaySinh.setEnabled(false);
		JLabel label_3 = new JLabel("Số CMND :");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSoCMND = new JTextField();
		txtSoCMND.setEditable(false);
		txtSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoCMND.setColumns(10);

		JLabel label_4 = new JLabel("Số điện thoại :");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 20));

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setEditable(false);
		txtSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoDienThoai.setColumns(10);

		JLabel label_5 = new JLabel("Địa chỉ :");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel pnlDiaChi = new JPanel();
		GroupLayout gl_pnlTTKH = new GroupLayout(pnlTTKH);
		gl_pnlTTKH.setHorizontalGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTTKH
				.createSequentialGroup().addGap(5)
				.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlTTKH.createSequentialGroup()
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addGap(48).addComponent(txtHoTen))
						.addGroup(gl_pnlTTKH.createSequentialGroup()
								.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 74,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
										.addComponent(pnlDiaChi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(txtSoDienThoai, GroupLayout.PREFERRED_SIZE, 458,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.LEADING, gl_pnlTTKH.createSequentialGroup().addGroup(gl_pnlTTKH
								.createParallelGroup(Alignment.LEADING)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
								.addGap(48)
								.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
										.addComponent(cmbGioiTinh, GroupLayout.PREFERRED_SIZE, 78,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(dtcNgaySinh, GroupLayout.PREFERRED_SIZE, 233,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtSoCMND))))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_pnlTTKH.setVerticalGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlTTKH
				.createSequentialGroup()
				.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup().addGap(13).addComponent(label,
								GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup().addGap(7)
								.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.TRAILING).addGroup(gl_pnlTTKH
						.createSequentialGroup()
						.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbGioiTinh, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGap(19).addComponent(label_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(dtcNgaySinh, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup().addGap(17).addComponent(label_3,
								GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup().addGap(11).addComponent(txtSoCMND,
								GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup().addGap(21).addComponent(label_4,
								GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup().addGap(18).addComponent(txtSoDienThoai,
								GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_pnlTTKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlTTKH.createSequentialGroup().addGap(24).addComponent(label_5,
								GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTTKH.createSequentialGroup().addGap(18).addComponent(pnlDiaChi,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(25, Short.MAX_VALUE)));
		pnlDiaChi.setLayout(new CardLayout(0, 0));

		pnlSuaDCKH = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlSuaDCKH.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnlDiaChi.add(pnlSuaDCKH, "name_157845119553400");

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
		pnlDiaChi.add(pnlXemDCKH, "name_157845141604800");
		pnlXemDCKH.setLayout(new BorderLayout(0, 0));

		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlXemDCKH.add(txtDiaChi, BorderLayout.CENTER);
		txtDiaChi.setColumns(10);
		pnlTTKH.setLayout(gl_pnlTTKH);

		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon(PnlKhachHang.class.getResource("/images/edit_property_32px.png")));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChucNang.add(btnSua);

		btnLuu = new JButton("Lưu");
		btnLuu.setIcon(new ImageIcon(PnlKhachHang.class.getResource("/images/save_35px.png")));
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChucNang.add(btnLuu);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlChucNang.add(btnHuy);
		pnlKhachHang.setLayout(gl_pnlKhachHang);

		JPanel pnl = new JPanel();
		FlowLayout fl_pnl = (FlowLayout) pnl.getLayout();
		fl_pnl.setVgap(17);
		pnlTTKhachHang.add(pnl, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 26));
		pnl.add(lblTieuDe);

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
		hienBangDSKhachHang(tblDSKhachHang, dsKH, srcDSKhachHang);

		JPanel pnlTimKiemKhachHang = new JPanel();
		pnlDSKhachHang.add(pnlTimKiemKhachHang, BorderLayout.NORTH);
		pnlTimKiemKhachHang.setLayout(new BoxLayout(pnlTimKiemKhachHang, BoxLayout.Y_AXIS));

		JPanel pnlLoaiTimKiem = new JPanel();
		pnlLoaiTimKiem.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) pnlLoaiTimKiem.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlTimKiemKhachHang.add(pnlLoaiTimKiem);

		JLabel lblLoaiTimKiem = new JLabel("Loại tìm kiếm:");
		lblLoaiTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlLoaiTimKiem.add(lblLoaiTimKiem);

		cmbLoaiTimKiem = new JComboBox();
		cmbLoaiTimKiem.setPreferredSize(new Dimension(150, 30));
		cmbLoaiTimKiem.setModel(new DefaultComboBoxModel(
				new String[] { "", "Theo tên", "Theo số điện thoại", "Theo số CMND (căn cước)" }));
		cmbLoaiTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlLoaiTimKiem.add(cmbLoaiTimKiem);

		JPanel pnlThanhTiemK = new JPanel();
		pnlTimKiemKhachHang.add(pnlThanhTiemK);
		pnlThanhTiemK.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout_3 = (FlowLayout) pnlTimKiem.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setVgap(10);
		pnlThanhTiemK.add(pnlTimKiem);

		JLabel lblTuKhoa = new JLabel("Từ khoá:");
		pnlTimKiem.add(lblTuKhoa);
		lblTuKhoa.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtTimKiem = new JTextField();
		txtTimKiem.setPreferredSize(new Dimension(6, 30));
		txtTimKiem.setEditable(false);
		pnlTimKiem.add(txtTimKiem);
		txtTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTimKiem.setColumns(25);

		JPanel pnlLamMoi = new JPanel();
		pnlLamMoi.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout_2 = (FlowLayout) pnlLamMoi.getLayout();
		flowLayout_2.setVgap(10);
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		pnlThanhTiemK.add(pnlLamMoi);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon(PnlKhachHang.class.getResource("/images/lammoi.png")));
		btnLamMoi.setHorizontalAlignment(SwingConstants.RIGHT);
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlLamMoi.add(btnLamMoi);
		pnlSuaDCKH.setVisible(false);
		pnlXemDCKH.setVisible(true);

		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlDSKhachHang }, "Arial", Font.PLAIN, 20);

		ganSuKien();
	}

	/**
	 * Hiện danh sách khách hàng
	 * 
	 * @param tbl: bảng
	 * @param ds:  danh sách khách hàng
	 * @param src: thanh cuộn
	 */
	private void hienBangDSKhachHang(JTable tbl, List<KhachHang> ds, JScrollPane src) {
		DSKhachHangTableModel dsKhachHangTableModel = new DSKhachHangTableModel(ds);
		tbl.setModel(dsKhachHangTableModel);
		src.setViewportView(tbl);
		if (ds.size() != 0) {
			tbl.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderrer());
			tbl.getColumnModel().getColumn(3).setCellRenderer(new MyDateRenderer());
			tbl.getColumnModel().getColumn(4).setCellRenderer(new CenterRenderrer());
			tbl.getColumnModel().getColumn(5).setCellRenderer(new CenterRenderrer());
		}
		TienIch.chinhKichThuocTable(tbl, tbl.getColumnModel().getTotalColumnWidth(), 5, 15, 30, 15, 15, 20);
	}

	/**
	 * Hiện thị thông tin chi tiết khách hàng
	 * 
	 * @param kh: khách hàng
	 */
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

		cmbHuyen.setSelectedItem(kh.getDiaChi().getHuyen());
		cmbTinh.setSelectedItem(kh.getDiaChi().getTinh());
		cmbXa.setSelectedItem(kh.getDiaChi().getXa());

	}

	/**
	 * Xoá trắng thông tin khách hàng
	 */
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

	/**
	 * Gắn sự kiện cho các component
	 */
	private void ganSuKien() {
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		cmbTinh.addActionListener(this);
		cmbHuyen.addActionListener(this);
		btnLamMoi.addActionListener(this);
		cmbLoaiTimKiem.addActionListener(this);
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
		txtTimKiem.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String key = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTimKiem.getText().trim()).toLowerCase();
				List<KhachHang> dsKhachHangTim = new ArrayList<KhachHang>();
				for (KhachHang khachHang : dsKH) {
					switch (cmbLoaiTimKiem.getSelectedIndex()) {
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
						dsKhachHangTim.add(khachHang);
					}
				}
				if (dsKhachHangTim.size() != 0) {
					dsKH = dsKhachHangTim;
				}
				// hiện danh sách khách hàng
				hienBangDSKhachHang(tblDSKhachHang, dsKH, srcDSKhachHang);
			}
		});
	}
	public boolean ktSuaTTKhachHang() {
		if(txtHoTen.getText().length() < 1) {
			JOptionPane.showMessageDialog(this,"Tên khách hàng không được để trống !");
			txtHoTen.requestFocus();
			return false;
		}
		if(txtSoCMND.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "Số chứng minh nhân dân không được để trống !");
			txtSoCMND.requestFocus();
			return false;
		}
		if(txtSoDienThoai.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
			txtSoDienThoai.requestFocus();
			return false;
		}
		if (cmbTinh.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Chưa chọn tỉnh cho địa chỉ");
			cmbTinh.requestFocusInWindow();
			return false;
		}
		if (cmbHuyen.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Chưa chọn huyện cho địa chỉ");
			cmbHuyen.requestFocusInWindow();
			return false;
		}
		if (cmbXa.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Chưa chọn huyện cho địa chỉ");
			cmbXa.requestFocusInWindow();
			return false;
		}
		return true;
	}

	/**
	 * Xử lý sự kiện cho các component
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnLuu)) {
			if(ktSuaTTKhachHang()) {
				txtHoTen.setEditable(false);
				cmbGioiTinh.setEnabled(false);
				dtcNgaySinh.setEnabled(false);
				txtSoCMND.setEditable(false);
				txtSoDienThoai.setEditable(false);
				
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
				khSua.setDiaChi(new DiaChi(cmbXa.getSelectedItem().toString(), cmbHuyen.getSelectedItem().toString(),
						cmbTinh.getSelectedItem().toString()));
				dsKH = khachHangControl.layDSKhachHang();
				hienBangDSKhachHang(tblDSKhachHang, dsKH, srcDSKhachHang);
				KhachHang khTest = khachHangControl.suaKhachHang(khSua);
				tblDSKhachHang.setEnabled(true);
				btnSua.setVisible(false);
				btnLuu.setVisible(false);
				btnHuy.setVisible(false);
				pnlSuaDCKH.setVisible(false);
				pnlXemDCKH.setVisible(true);
			}

		} else if (o.equals(btnSua)) {
			txtSoDienThoai.setEditable(true);
			cmbGioiTinh.setEditable(true);
			txtHoTen.setEditable(true);
			cmbGioiTinh.setEnabled(true);
			dtcNgaySinh.setEnabled(true);
			txtSoCMND.setEditable(true);
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
			txtHoTen.setEditable(false);
			cmbGioiTinh.setEnabled(false);
			dtcNgaySinh.setEnabled(false);
			txtSoCMND.setEditable(false);
			txtSoDienThoai.setEditable(false);
			cmbGioiTinh.setEditable(false);
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
		} else if (o.equals(btnLamMoi)) {
			txtHoTen.setEditable(false);
			cmbGioiTinh.setEnabled(false);
			dtcNgaySinh.setEnabled(false);
			txtSoCMND.setEditable(false);
			txtSoDienThoai.setEditable(false);
			
			dsKH = khachHangControl.layDSKhachHang();
			hienBangDSKhachHang(tblDSKhachHang, dsKH, srcDSKhachHang);
		} else if (o.equals(cmbLoaiTimKiem)) {
			switch (cmbLoaiTimKiem.getSelectedIndex()) {
			case 1:
				txtTimKiem.setEditable(true);
				txtTimKiem.setUI(new HintTextFieldUI("Nhập tên khách hàng", true));
				break;
			case 2:
				txtTimKiem.setEditable(true);
				txtTimKiem.setUI(new HintTextFieldUI("Nhập số điện thoại khách hàng", true));
				break;
			case 3:
				txtTimKiem.setEditable(true);
				txtTimKiem.setUI(new HintTextFieldUI("Nhập số CMND(thẻ căn cước) khách hàng", true));
				break;
			default:
				txtTimKiem.setEditable(false);
				break;
			}
		}

	}

	/*
	 * ============================ ============================
	 *
	 */
	@SuppressWarnings("unused")
	private class CenterRenderrer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public CenterRenderrer() {
			this.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	@SuppressWarnings("unused")
	private class MyDateRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public MyDateRenderer() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}

		@Override
		protected void setValue(Object value) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			value = sdf.format(((java.util.Date) value).getTime());
			super.setValue(value);
		}

	}
}
