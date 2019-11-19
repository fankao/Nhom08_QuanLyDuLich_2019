package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import entities.DiaDanh;

public class PnlTaoTour extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField txtTenTour;
	private JComboBox<DiaDanh> cmbDiaDanh;
	private JComboBox<String> cmbDiemDen;
	private JComboBox<String> cmbDiemXP;
	private JSpinner spnNgay;
	private JSpinner spnDem;
	private JTable tblDSCTTour;
	private JTextField ttxTenTour;
	private JTable tblDSTour;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnHuy;
	private JButton btnLuu;
	private JButton btnTaoTour;

	/**
	 * Giao diện chức năng tạo tour
	 */
	public PnlTaoTour() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		JPanel pnlTieuDe = new JPanel();
		add(pnlTieuDe, BorderLayout.NORTH);
		pnlTieuDe.setLayout(new BorderLayout(0, 0));

		JPanel pnlTaoTour = new JPanel();
		add(pnlTaoTour, BorderLayout.CENTER);
		pnlTaoTour.setLayout(new BorderLayout(0, 0));

		JPanel pnlCTTour = new JPanel();
		pnlCTTour.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Ch\u01B0\u01A1ng tr\u00ECnh tour",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCTTour.setPreferredSize(new Dimension(10, 150));
		pnlTaoTour.add(pnlCTTour, BorderLayout.NORTH);

		JLabel lblDiaDanh = new JLabel("Chọn địa danh:");

		cmbDiaDanh = new JComboBox<DiaDanh>();

		JLabel lblTenTour = new JLabel("Tên tour:");

		txtTenTour = new JTextField();
		txtTenTour.setColumns(10);

		JLabel lblDiemXP = new JLabel("Điểm xuất phát:");

		cmbDiemXP = new JComboBox<String>();

		JLabel lblDiemDen = new JLabel("Điểm xuất phát:");

		cmbDiemDen = new JComboBox<String>();

		JLabel lblThoiGian = new JLabel("Thời gian:");

		spnNgay = new JSpinner();

		JLabel lblNgy = new JLabel("Ngày");

		spnDem = new JSpinner();

		JLabel lblm = new JLabel("Đêm");

		JLabel lblGiNgiLn = new JLabel("Giá người lớn:");

		JLabel label = new JLabel("");

		JFormattedTextField txtGiaNgLon = new JFormattedTextField();

		JLabel lblGiaTrEm = new JLabel("Giá trẻ em:");

		JFormattedTextField txtGiaTrEm = new JFormattedTextField();

		JLabel lblCTTour = new JLabel("Chương trình tour:");

		JButton btnCTTour = new JButton("...");
		GroupLayout gl_pnlCTTour = new GroupLayout(pnlCTTour);
		gl_pnlCTTour
				.setHorizontalGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCTTour.createSequentialGroup()
								.addGroup(gl_pnlCTTour
										.createParallelGroup(
												Alignment.LEADING)
										.addGroup(gl_pnlCTTour.createSequentialGroup().addContainerGap().addGroup(
												gl_pnlCTTour
														.createParallelGroup(Alignment.LEADING).addComponent(lblDiaDanh)
														.addComponent(lblCTTour))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
														.addComponent(btnCTTour).addComponent(
																cmbDiaDanh, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(171).addGroup(gl_pnlCTTour
														.createParallelGroup(Alignment.LEADING).addComponent(lblDiemDen,
																GroupLayout.PREFERRED_SIZE, 92,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblGiNgiLn))
												.addGap(18)
												.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
														.addComponent(label)
														.addGroup(gl_pnlCTTour.createSequentialGroup()
																.addGroup(gl_pnlCTTour
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(txtGiaNgLon,
																				GroupLayout.PREFERRED_SIZE, 117,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(cmbDiemDen,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addGroup(gl_pnlCTTour
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(
																				lblDiemXP)
																		.addComponent(lblGiaTrEm,
																				GroupLayout.PREFERRED_SIZE, 80,
																				GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addGroup(gl_pnlCTTour
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(
																				cmbDiemXP, GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGroup(gl_pnlCTTour.createSequentialGroup()
																				.addComponent(txtGiaTrEm,
																						GroupLayout.PREFERRED_SIZE, 117,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.UNRELATED)
																				.addComponent(lblThoiGian)
																				.addPreferredGap(
																						ComponentPlacement.UNRELATED)
																				.addComponent(spnNgay,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.UNRELATED)
																				.addComponent(lblNgy)
																				.addPreferredGap(
																						ComponentPlacement.UNRELATED)
																				.addComponent(spnDem,
																						GroupLayout.PREFERRED_SIZE,
																						GroupLayout.DEFAULT_SIZE,
																						GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						ComponentPlacement.UNRELATED)
																				.addComponent(lblm))))))
										.addGroup(gl_pnlCTTour.createSequentialGroup().addGap(12)
												.addComponent(lblTenTour).addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(txtTenTour, GroupLayout.PREFERRED_SIZE, 214,
														GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(71, Short.MAX_VALUE)));
		gl_pnlCTTour.setVerticalGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCTTour
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE).addComponent(lblDiaDanh).addComponent(
						cmbDiaDanh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCTTour.createSequentialGroup()
								.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE).addComponent(lblDiemDen)
										.addComponent(cmbDiemXP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDiemXP).addComponent(cmbDiemDen, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED).addGroup(
										gl_pnlCTTour.createParallelGroup(Alignment.BASELINE).addComponent(label)
												.addComponent(txtGiaTrEm, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblGiaTrEm)
												.addComponent(txtGiaNgLon, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblGiNgiLn).addComponent(lblCTTour)
												.addComponent(btnCTTour).addComponent(lblThoiGian)
												.addComponent(spnNgay, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNgy)
												.addComponent(spnDem, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblm)))
						.addGroup(gl_pnlCTTour.createParallelGroup(Alignment.BASELINE).addComponent(lblTenTour)
								.addComponent(txtTenTour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(155, Short.MAX_VALUE)));
		pnlCTTour.setLayout(gl_pnlCTTour);

		JPanel pnlDSCTTour = new JPanel();
		pnlDSCTTour.setPreferredSize(new Dimension(10, 100));
		pnlTaoTour.add(pnlDSCTTour, BorderLayout.CENTER);
		pnlDSCTTour.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSCTTour = new JScrollPane();
		pnlDSCTTour.add(scrDSCTTour, BorderLayout.CENTER);

		tblDSCTTour = new JTable();
		tblDSCTTour.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "T\u00EAn tour", "\u0110i\u1EC3m xu\u1EA5t ph\u00E1t",
						"\u0110i\u1EC3m \u0111\u1EBFn", "Th\u1EDDi gian", "Gi\u00E1 tr\u1EBB em",
						"Gi\u00E1 ng\u01B0\u1EDDi l\u1EDBn" }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] columnTypes = new Class[] { Object.class, String.class, String.class, Object.class, Object.class,
					Object.class, Double.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrDSCTTour.setViewportView(tblDSCTTour);

		JPanel pnlButtonSouth = new JPanel();
		pnlDSCTTour.add(pnlButtonSouth, BorderLayout.SOUTH);
		pnlButtonSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		btnTaoTour = new JButton("Tạo tour");
		pnlButtonSouth.add(btnTaoTour);
		
				JPanel pnlButtonNorth = new JPanel();
				pnlDSCTTour.add(pnlButtonNorth, BorderLayout.NORTH);
				FlowLayout fl_pnlButtonNorth = (FlowLayout) pnlButtonNorth.getLayout();
				fl_pnlButtonNorth.setAlignment(FlowLayout.RIGHT);
				
						btnThem = new JButton("Thêm");
						pnlButtonNorth.add(btnThem);
						
								btnXoa = new JButton("Xoá");
								pnlButtonNorth.add(btnXoa);
								
										btnHuy = new JButton("Huỷ bỏ");
										pnlButtonNorth.add(btnHuy);

		JPanel pnlThemTour = new JPanel();
		pnlThemTour.setBorder(new TitledBorder(
				new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true),
						new EtchedBorder(EtchedBorder.LOWERED, null, null)),
				"Chi ti\u1EBFt tour du l\u1ECBch", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlThemTour.setPreferredSize(new Dimension(10, 300));
		pnlTaoTour.add(pnlThemTour, BorderLayout.SOUTH);
		pnlThemTour.setLayout(new BorderLayout(0, 0));

		JPanel pnlTTTour = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlTTTour.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		pnlThemTour.add(pnlTTTour, BorderLayout.NORTH);

		JLabel lblTenTours = new JLabel("Tên tour:");
		pnlTTTour.add(lblTenTours);

		ttxTenTour = new JTextField();
		pnlTTTour.add(ttxTenTour);
		ttxTenTour.setColumns(10);

		JLabel lblNgayKhoiHanh = new JLabel("Ngày khởi hành:");
		pnlTTTour.add(lblNgayKhoiHanh);

		JDateChooser dateChooser = new JDateChooser();
		pnlTTTour.add(dateChooser);

		JLabel lblSoKhachToiDa = new JLabel("Số người tối đa:");
		pnlTTTour.add(lblSoKhachToiDa);

		JSpinner spnSoKhachToiDa = new JSpinner();
		pnlTTTour.add(spnSoKhachToiDa);

		JPanel pnlDSTour = new JPanel();
		pnlThemTour.add(pnlDSTour, BorderLayout.CENTER);
		pnlDSTour.setLayout(new BorderLayout(0, 0));

		JScrollPane scrDSTour = new JScrollPane();
		pnlDSTour.add(scrDSTour, BorderLayout.CENTER);

		tblDSTour = new JTable();
		tblDSTour.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "STT", "M\u00E3 tour",
				"Ng\u00E0y xu\u1EA5t ph\u00E1t", "S\u1ED1 ng\u01B0\u1EDDi t\u1ED1i \u0111a" }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] columnTypes = new Class[] { Integer.class, String.class, Object.class, Integer.class };

			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblDSTour.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblDSTour.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrDSTour.setViewportView(tblDSTour);
		
		btnLuu = new JButton("Lưu");
		
		btnThem.setPreferredSize(btnHuy.getPreferredSize());
		btnLuu.setPreferredSize(btnHuy.getPreferredSize());
		btnXoa.setPreferredSize(btnHuy.getPreferredSize());
		
		btnThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnThem.setVisible(false);
				pnlButtonNorth.add(btnLuu, 0);
				
			}
		});
	}
}
