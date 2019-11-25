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
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import javax.swing.JSeparator;

public class PnlDangKyTour extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tblDSTour;
	private JTable tblDSNgayDi;

	public PnlDangKyTour() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel pnlTimKiemKhachHang = new JPanel();
		add(pnlTimKiemKhachHang);
		pnlTimKiemKhachHang.setLayout(new BorderLayout(0, 0));

		pnlTimKiemKhachHang.add(new PnlTimKiemKhachHang());
		

		JPanel pnlTour = new JPanel();
		add(pnlTour);
		pnlTour.setLayout(new BoxLayout(pnlTour, BoxLayout.Y_AXIS));

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
		pnlLichTrinh.setBorder(new TitledBorder(null, "Danh s\u00E1ch ng\u00E0y \u0111i", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlCTTour.add(pnlLichTrinh);
		pnlLichTrinh.setLayout(new BorderLayout(0, 0));

		JPanel pnlChucNangND = new JPanel();
		pnlLichTrinh.add(pnlChucNangND, BorderLayout.SOUTH);
		pnlChucNangND.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel btnButtonHuyChonNgayDi = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) btnButtonHuyChonNgayDi.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnlChucNangND.add(btnButtonHuyChonNgayDi);

		JButton btnHuyChonNgayDi = new JButton("Huỷ chọn");
		btnButtonHuyChonNgayDi.add(btnHuyChonNgayDi);
		btnHuyChonNgayDi.setHorizontalAlignment(SwingConstants.LEFT);
		btnHuyChonNgayDi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHuyChonNgayDi.setFocusable(false);
		btnHuyChonNgayDi.setEnabled(false);

		JPanel pnlButtonDKTour = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlButtonDKTour.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnlChucNangND.add(pnlButtonDKTour);

		JButton btnDangKy = new JButton("Đăng ký tour");
		pnlButtonDKTour.add(btnDangKy);
		btnDangKy.setHorizontalAlignment(SwingConstants.LEFT);
		btnDangKy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDangKy.setFocusable(false);
		btnDangKy.setEnabled(false);

		JScrollPane scrDSNgayDi = new JScrollPane();
		pnlLichTrinh.add(scrDSNgayDi, BorderLayout.CENTER);

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
		

	}
}
