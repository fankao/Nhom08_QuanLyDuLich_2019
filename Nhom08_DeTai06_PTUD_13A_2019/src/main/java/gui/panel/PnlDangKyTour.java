package gui.panel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.border.EtchedBorder;

public class PnlDangKyTour extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable tblDSTour;
	private JTable tblDSNgayDi;
	private JTextField textHoTenKH;
	private JTextField txtNgaySinhKH;
	private JTextField txtSoCMND;
	private JTextField txtSdtKH;
	private JButton btnTimKiem;
	private JButton btnThemMoi;
	private JButton btnThem;
	private JPanel pnlTimKiemKhachHang;
	private JPanel pnlThongTinKH;
	private JTable tblDSKhachThamGia;
	private JButton btnDangKyTour;
	private JButton btnHuyChon;
	public static void main(String[] args) {
		PnlDangKyTour test = new PnlDangKyTour();
		JFrame main = new JFrame();
		main.getContentPane().add(test);
		main.setSize(1080,720);
		main.setVisible(true);
	}

	public PnlDangKyTour() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		pnlTimKiemKhachHang = new JPanel();
		add(pnlTimKiemKhachHang);
		pnlTimKiemKhachHang.setLayout(new BorderLayout(0, 0));

		pnlTimKiemKhachHang.add(new PnlTimKiemKhachHang());
		pnlTimKiemKhachHang.setVisible(false);

		JPanel pnlTour = new JPanel();
		add(pnlTour);
		pnlTour.setLayout(new BoxLayout(pnlTour, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		pnlTour.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTimKiemVaThemMoi = new JPanel();
		panel.add(pnlTimKiemVaThemMoi, BorderLayout.NORTH);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlTimKiemVaThemMoi.add(btnTimKiem);
		
		btnThemMoi = new JButton("Thêm mới");
		btnThemMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlTimKiemVaThemMoi.add(btnThemMoi);
		
		pnlThongTinKH = new JPanel();
		pnlThongTinKH.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Th\u00F4ng tin kh\u00E1ch h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pnlThongTinKH, BorderLayout.CENTER);
		
		JLabel lblHoTenKH = new JLabel("Họ và tên khách hàng :");
		lblHoTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textHoTenKH = new JTextField();
		textHoTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textHoTenKH.setColumns(10);
		
		JLabel lblDiaChiKH = new JLabel("Địa chỉ :");
		lblDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh :");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtNgaySinhKH = new JTextField();
		txtNgaySinhKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNgaySinhKH.setColumns(10);
		
		JLabel lblSoCmnd = new JLabel("Số CMND :");
		lblSoCmnd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtSoCMND = new JTextField();
		txtSoCMND.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSoCMND.setColumns(10);
		
		JLabel lblSdt = new JLabel("Số điện thoại :");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtSdtKH = new JTextField();
		txtSdtKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSdtKH.setColumns(10);
		
		JPanel pnlDiaChi = new JPanel();
		FlowLayout fl_pnlDiaChi = (FlowLayout) pnlDiaChi.getLayout();
		fl_pnlDiaChi.setAlignment(FlowLayout.LEFT);
		
		JButton btnLuuTTKhachHang = new JButton("Lưu");
		btnLuuTTKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_pnlThongTinKH = new GroupLayout(pnlThongTinKH);
		gl_pnlThongTinKH.setHorizontalGroup(
			gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlThongTinKH.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlThongTinKH.createSequentialGroup()
							.addComponent(lblHoTenKH)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textHoTenKH, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlThongTinKH.createSequentialGroup()
							.addComponent(lblDiaChiKH, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnlDiaChi, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)))
					.addGap(34)
					.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSdt)
						.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtSdtKH)
						.addComponent(txtNgaySinhKH, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
					.addGap(35)
					.addComponent(lblSoCmnd, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
						.addComponent(txtSoCMND, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlThongTinKH.createSequentialGroup()
							.addComponent(btnLuuTTKhachHang, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnHuy, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_pnlThongTinKH.setVerticalGroup(
			gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlThongTinKH.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlThongTinKH.createSequentialGroup()
							.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHoTenKH)
								.addComponent(lblNgaySinh, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(textHoTenKH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtSdtKH, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnLuuTTKhachHang)
									.addComponent(btnHuy, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblSdt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDiaChiKH, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(pnlDiaChi, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlThongTinKH.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtNgaySinhKH, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSoCmnd, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtSoCMND, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		JTextField txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(txtDiaChi);
		txtDiaChi.setColumns(10);
		
		JComboBox cmbTinh = new JComboBox();
		cmbTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(cmbTinh);
		
		JComboBox cmbHuyen = new JComboBox();
		cmbHuyen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(cmbHuyen);
		
		JComboBox cmbXa = new JComboBox();
		cmbXa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(cmbXa);
		
		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(btnThem);
		
		JButton btnLuu = new JButton("Lưu");
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlDiaChi.add(btnLuu);
		pnlThongTinKH.setLayout(gl_pnlThongTinKH);

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBorder(
				new TitledBorder(null, "Danh s\u00E1ch tour", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlTour.add(pnlNorth);
		pnlNorth.setLayout(new BorderLayout(0, 0));

		JPanel pnlChucNang = new JPanel();
		pnlNorth.add(pnlChucNang, BorderLayout.NORTH);

		JPanel pnlDSTour = new JPanel();
		pnlNorth.add(pnlDSTour, BorderLayout.CENTER);
		pnlDSTour.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSTour = new JScrollPane();
		pnlDSTour.add(scrDSTour, BorderLayout.CENTER);

		tblDSTour = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblDSTour.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "STT", "T\u00EAn tour",
				"\u0110\u1ECBa danh", "Th\u1EDDi gian", "Gi\u00E1 ng\u01B0\u1EDDi l\u1EDBn", "Gi\u00E1 tr\u1EBB em",
				"\u0110i\u1EC3m xu\u1EA5t ph\u00E1t", "\u0110i\u1EC3m \u0111\u1EBFn", "Ph\u01B0\u01A1ng ti\u1EC7n" }) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Short.class, String.class, String.class, String.class, Boolean.class,
					Double.class, String.class, String.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrDSTour.setViewportView(tblDSTour);

		JPanel pnlHuyChonTour = new JPanel();
		FlowLayout fl_pnlHuyChonTour = (FlowLayout) pnlHuyChonTour.getLayout();
		fl_pnlHuyChonTour.setAlignment(FlowLayout.LEFT);
		pnlNorth.add(pnlHuyChonTour, BorderLayout.SOUTH);

		JButton btnHuyChonTour = new JButton("Huỷ chọn");
		btnHuyChonTour.setHorizontalAlignment(SwingConstants.LEFT);
		btnHuyChonTour.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHuyChonTour.setFocusable(false);
		btnHuyChonTour.setEnabled(false);
		pnlHuyChonTour.add(btnHuyChonTour);

		JPanel pnlSouth = new JPanel();
		pnlTour.add(pnlSouth);
		pnlSouth.setLayout(new BorderLayout(0, 0));

		JPanel pnlCTTour = new JPanel();
		pnlSouth.add(pnlCTTour, BorderLayout.CENTER);
		pnlCTTour.setLayout(new BoxLayout(pnlCTTour, BoxLayout.X_AXIS));

		JPanel pnlLichTrinh = new JPanel();
		pnlLichTrinh.setBorder(null);
		pnlCTTour.add(pnlLichTrinh);
		pnlLichTrinh.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel pnlDanhSachNgayDi = new JPanel();
		pnlDanhSachNgayDi.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch ng\u00E0y \u0111i", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLichTrinh.add(pnlDanhSachNgayDi);
		pnlDanhSachNgayDi.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSNgayDi = new JScrollPane();
		pnlDanhSachNgayDi.add(scrDSNgayDi, BorderLayout.CENTER);

		tblDSNgayDi = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblDSNgayDi.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M\u00E3 tour", "Ng\u00E0y kh\u1EDFi h\u00E0nh", "S\u1ED1 ch\u1ED7",
						"S\u1ED1 ng\u01B0\u1EDDi \u0111\u0103ng k\u00FD", "T\u00ECnh tr\u1EA1ng" }) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Object.class, Object.class, Object.class, Object.class, Object.class,
					String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrDSNgayDi.setViewportView(tblDSNgayDi);
		
		JPanel pnlDsKhachHangThamGia = new JPanel();
		pnlDsKhachHangThamGia.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh s\u00E1ch kh\u00E1ch h\u00E0ng tham gia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlLichTrinh.add(pnlDsKhachHangThamGia);
		pnlDsKhachHangThamGia.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrDSKhachThamGia = new JScrollPane();
		pnlDsKhachHangThamGia.add(scrDSKhachThamGia, BorderLayout.CENTER);
		
		tblDSKhachThamGia = new JTable();
		scrDSKhachThamGia.setViewportView(tblDSKhachThamGia);
		
		JPanel pnlDieuKhienND = new JPanel();
		pnlSouth.add(pnlDieuKhienND, BorderLayout.SOUTH);
		pnlDieuKhienND.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel pnlHuyChon = new JPanel();
		FlowLayout fl_pnlHuyChon = (FlowLayout) pnlHuyChon.getLayout();
		fl_pnlHuyChon.setAlignment(FlowLayout.LEFT);
		pnlDieuKhienND.add(pnlHuyChon);
		
		btnHuyChon = new JButton("Hủy chọn");
		pnlHuyChon.add(btnHuyChon);
		
		JPanel pnlDangKyTour = new JPanel();
		FlowLayout fl_pnlDangKyTour = (FlowLayout) pnlDangKyTour.getLayout();
		fl_pnlDangKyTour.setAlignment(FlowLayout.RIGHT);
		pnlDieuKhienND.add(pnlDangKyTour);
		
		btnDangKyTour = new JButton("Đăng ký");
		pnlDangKyTour.add(btnDangKyTour);
		txtDiaChi.setVisible(false);
		btnThem.setVisible(false);
		btnLuu.setVisible(false);
		pnlThongTinKH.setVisible(false);
		
		ganSuKien();
		
	}
	
	private void ganSuKien() {
		btnTimKiem.addActionListener(this);
		btnThemMoi.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnTimKiem)) {
			pnlTimKiemKhachHang.setVisible(true);
			pnlThongTinKH.setVisible(false);
		}else if(o.equals(btnThemMoi)) {
			pnlThongTinKH.setVisible(true);
			pnlTimKiemKhachHang.setVisible(false);
		}
		
	}
}
