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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import control.ITourControl;
import control.impl.TourControlImpl;
import entities.NgayKhoiHanh;
import entities.Tour;
import model.TourTableModel;
import utils.TableMouseListener;
import utils.TienIch;

public class PnlDuyetTour extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable tblDSTourChuaDuyet, tblDSTourDaDuyet;
	private JScrollPane scrDSTourChuaDuyet;
	private JScrollPane scrDSTourDuyet;
	private JButton btnDuyetTour;

	private ITourControl tourControl;
	private TourTableModel tourTableModel;
	private static List<Tour> dsTourChuaDuyet, dsTourDaDuyet;
	private static List<NgayKhoiHanh> dsNgayKhoiHanh;
	private static Tour tourChon;
	private JButton btnHuyDuyet;
	private JTextField txtTimKiemD;
	private JTextField txtTimKiemCD;
	private JButton btnLamMoiCD;
	private JButton btnLamMoiD;

	/**
	 * Khơi tạo giao diện duyệt mở bán tour
	 */
	public PnlDuyetTour() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel pnlDSTourCanDuyet = new JPanel();
		pnlDSTourCanDuyet.setBackground(Color.WHITE);
		pnlDSTourCanDuyet.setPreferredSize(new Dimension(10, 350));
		pnlDSTourCanDuyet.setBorder(
				new TitledBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(2, 2, 2, 2)),
						"Danh s\u00E1ch tour ch\u01B0a duy\u1EC7t", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 0, 0)));
		add(pnlDSTourCanDuyet);
		pnlDSTourCanDuyet.setLayout(new BorderLayout(0, 0));

		scrDSTourChuaDuyet = new JScrollPane();
		scrDSTourChuaDuyet.setViewportBorder(new LineBorder(null));
		pnlDSTourCanDuyet.add(scrDSTourChuaDuyet, BorderLayout.CENTER);

		JPanel pnlTTTour = new JPanel();
		pnlTTTour.setPreferredSize(new Dimension(10, 20));
		pnlTTTour.setBorder(new EmptyBorder(10, 0, 10, 0));
		add(pnlTTTour);
		pnlTTTour.setLayout(new BorderLayout(0, 0));

		JPanel pnlCenter = new JPanel();
		pnlTTTour.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlCTTour = new JPanel();
		pnlCenter.add(pnlCTTour);
		pnlCTTour.setLayout(new BorderLayout(0, 0));

		JPanel pnlDSNgayKH = new JPanel();
		pnlCenter.add(pnlDSNgayKH);
		pnlDSNgayKH.setLayout(new BorderLayout(0, 0));

		JPanel pnlDSTourDuyet = new JPanel();
		pnlDSTourDuyet.setBackground(Color.WHITE);
		pnlDSTourDuyet.setPreferredSize(new Dimension(10, 400));
		pnlDSTourDuyet.setBorder(
				new TitledBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(2, 2, 2, 2)),
						"Danh s\u00E1ch tour \u0111\u00E3 duy\u1EC7t", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 0, 0)));
		add(pnlDSTourDuyet);
		pnlDSTourDuyet.setLayout(new BorderLayout(0, 0));

		scrDSTourDuyet = new JScrollPane();
		pnlDSTourDuyet.add(scrDSTourDuyet, BorderLayout.CENTER);
		JPanel pnlChucNang = new JPanel();
		pnlDSTourCanDuyet.add(pnlChucNang, BorderLayout.NORTH);
		pnlChucNang.setLayout(new GridLayout(1, 0, 2, 0));

		JPanel pnlTimKiem = new JPanel();
		pnlChucNang.add(pnlTimKiem);

		tblDSTourDaDuyet = new JTable();
		tblDSTourChuaDuyet = new JTable();
		tblDSTourChuaDuyet.setFillsViewportHeight(true);
		tblDSTourDaDuyet.setFillsViewportHeight(true);

		/*
		 * Hiện thị các thông tin truy vấn từ cơ sở dữ liệu
		 */

		tourControl = new TourControlImpl();
		// hiện danh sách tour chưa được duyệt
		dsTourChuaDuyet = tourControl.layDsTourTheoYeuCau(1);
		hienDanhSachTour(tblDSTourChuaDuyet, dsTourChuaDuyet, scrDSTourChuaDuyet);

		JPanel pnlDuyet = new JPanel();
		pnlDSTourCanDuyet.add(pnlDuyet, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) pnlDuyet.getLayout();

		btnDuyetTour = new JButton("Duyệt tour");
		btnDuyetTour.setIcon(new ImageIcon(PnlDuyetTour.class.getResource("/images/task_completed_32px.png")));
		btnDuyetTour.setEnabled(false);
		btnDuyetTour.setFont(new Font("Tahoma", Font.PLAIN, 22));
		pnlDuyet.add(btnDuyetTour);

		JPanel panel = new JPanel();
		pnlDSTourCanDuyet.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JPanel pnlTimKiemCD = new JPanel();
		FlowLayout fl_pnlTimKiemCD = (FlowLayout) pnlTimKiemCD.getLayout();
		fl_pnlTimKiemCD.setAlignment(FlowLayout.LEFT);
		fl_pnlTimKiemCD.setVgap(10);
		panel.add(pnlTimKiemCD);

		JLabel lblTuKhoaCD = new JLabel("Nhập tên tour:");
		lblTuKhoaCD.setFont(new Font("Arial", Font.PLAIN, 20));
		pnlTimKiemCD.add(lblTuKhoaCD);

		txtTimKiemCD = new JTextField();
		txtTimKiemCD.setFont(new Font("Arial", Font.PLAIN, 20));
		pnlTimKiemCD.add(txtTimKiemCD);
		txtTimKiemCD.setColumns(20);

		JPanel pnlLamMoiCD = new JPanel();
		FlowLayout fl_pnlLamMoiCD = (FlowLayout) pnlLamMoiCD.getLayout();
		fl_pnlLamMoiCD.setAlignment(FlowLayout.RIGHT);
		panel.add(pnlLamMoiCD);

		btnLamMoiCD = new JButton("Làm mới");
		btnLamMoiCD.setIcon(new ImageIcon(PnlDuyetTour.class.getResource("/images/filter_25px.png")));
		btnLamMoiCD.setFont(new Font("Dialog", Font.PLAIN, 20));
		pnlLamMoiCD.add(btnLamMoiCD);

		// hiện danh sách tour đã duyệt
		dsTourDaDuyet = tourControl.layDsTourTheoYeuCau(3);
		hienDanhSachTour(tblDSTourDaDuyet, dsTourDaDuyet, scrDSTourDuyet);

		JPanel pnlHuyDuyet = new JPanel();
		pnlHuyDuyet.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout_1 = (FlowLayout) pnlHuyDuyet.getLayout();
		pnlDSTourDuyet.add(pnlHuyDuyet, BorderLayout.SOUTH);

		btnHuyDuyet = new JButton("Huỷ duyệt");
		btnHuyDuyet.setIcon(new ImageIcon(PnlDuyetTour.class.getResource("/images/cancel_32px.png")));
		btnHuyDuyet.setEnabled(false);
		btnHuyDuyet.setFont(new Font("Tahoma", Font.PLAIN, 22));
		pnlHuyDuyet.add(btnHuyDuyet);

		JPanel panel_4 = new JPanel();
		pnlDSTourDuyet.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		JPanel pnlTimKiemD = new JPanel();
		FlowLayout fl_pnlTimKiemD = (FlowLayout) pnlTimKiemD.getLayout();
		fl_pnlTimKiemD.setVgap(10);
		fl_pnlTimKiemD.setAlignment(FlowLayout.LEFT);
		panel_4.add(pnlTimKiemD);

		JLabel lblTimKiemD = new JLabel("Nhập tên tour:");
		lblTimKiemD.setFont(new Font("Arial", Font.PLAIN, 20));
		pnlTimKiemD.add(lblTimKiemD);

		txtTimKiemD = new JTextField();
		txtTimKiemD.setFont(new Font("Arial", Font.PLAIN, 20));
		txtTimKiemD.setColumns(20);
		pnlTimKiemD.add(txtTimKiemD);

		JPanel pnlLamMoiD = new JPanel();
		FlowLayout fl_pnlLamMoiD = (FlowLayout) pnlLamMoiD.getLayout();
		fl_pnlLamMoiD.setAlignment(FlowLayout.RIGHT);
		panel_4.add(pnlLamMoiD);

		btnLamMoiD = new JButton("Làm mới");
		btnLamMoiD.setIcon(new ImageIcon(PnlDuyetTour.class.getResource("/images/filter_25px.png")));
		btnLamMoiD.setFont(new Font("Dialog", Font.PLAIN, 20));
		pnlLamMoiD.add(btnLamMoiD);

		// gán sự kiện
		ganSuKien();
	}

	/*-------------------------------------------
	 * 
	 * Xử lý sự kiện
	 * 
	 * ------------------------------------------
	 */

	/**
	 * Gắn sự kiện cho các control
	 */
	private void ganSuKien() {
		btnDuyetTour.addActionListener(this);
		btnHuyDuyet.addActionListener(this);
		btnLamMoiCD.addActionListener(this);
		btnLamMoiD.addActionListener(this);

		txtTimKiemCD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String tuKhoa = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTimKiemCD.getText().trim())
						.toLowerCase();
				hienDanhSachTour(tblDSTourChuaDuyet, timKiemTour(tuKhoa, dsTourChuaDuyet), scrDSTourChuaDuyet);
			}

		});
		txtTimKiemD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String tuKhoa = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(txtTimKiemD.getText().trim())
						.toLowerCase();
				hienDanhSachTour(tblDSTourChuaDuyet, timKiemTour(tuKhoa, dsTourDaDuyet), scrDSTourDuyet);
			}

		});

		tblDSTourChuaDuyet.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = tblDSTourChuaDuyet.getSelectedRow();
				if (row == -1)
					return;
				tourChon = dsTourChuaDuyet.get(row);
				btnDuyetTour.setEnabled(true);

			}
		});

		tblDSTourDaDuyet.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = tblDSTourDaDuyet.getSelectedRow();
				if (row == -1)
					return;
				System.out.println(row);
				tourChon = dsTourDaDuyet.get(row);
				btnHuyDuyet.setEnabled(true);

			}
		});
		tblDSTourDaDuyet.addMouseListener(new TableMouseListener(tblDSTourDaDuyet));
		tblDSTourChuaDuyet.addMouseListener(new TableMouseListener(tblDSTourChuaDuyet));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnDuyetTour)) {
			int xacNhan = JOptionPane.showConfirmDialog(null, "Xác nhận mở bác tour " + tourChon.getTenTour() + " ?",
					"Xác nhận", JOptionPane.YES_NO_OPTION);
			if (xacNhan == JOptionPane.YES_OPTION) {
				tourChon.setDaDuyet(true);
				Tour tour = tourControl.suaTour(tourChon);
				if (tour != null) {
					// tblDSTourChuaDuyet.clearSelection();
					dsTourDaDuyet = tourControl.layDsTourTheoYeuCau(3);
					hienDanhSachTour(tblDSTourDaDuyet, dsTourDaDuyet, scrDSTourDuyet);

					dsTourChuaDuyet = tourControl.layDsTourTheoYeuCau(1);
					hienDanhSachTour(tblDSTourChuaDuyet, dsTourChuaDuyet, scrDSTourChuaDuyet);
				}
			}

		} else if (o.equals(btnHuyDuyet)) {
			int xacNhan = JOptionPane.showConfirmDialog(null,
					"Xác nhận không mở bán tour " + tourChon.getTenTour() + " ?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (xacNhan == JOptionPane.YES_OPTION) {
				tourChon.setDaDuyet(false);
				Tour tour = tourControl.suaTour(tourChon);
				if (tour != null) {
					dsTourDaDuyet = tourControl.layDsTourTheoYeuCau(3);
					hienDanhSachTour(tblDSTourDaDuyet, dsTourDaDuyet, scrDSTourDuyet);

					dsTourChuaDuyet = tourControl.layDsTourTheoYeuCau(1);
					hienDanhSachTour(tblDSTourChuaDuyet, dsTourChuaDuyet, scrDSTourChuaDuyet);
				}
			}
		} else if (o.equals(btnLamMoiD)) {
			dsTourDaDuyet = tourControl.layDsTourTheoYeuCau(3);
			hienDanhSachTour(tblDSTourDaDuyet, dsTourDaDuyet, scrDSTourDuyet);
			btnHuyDuyet.setEnabled(false);

		} else if (o.equals(btnLamMoiCD)) {
			dsTourChuaDuyet = tourControl.layDsTourTheoYeuCau(1);
			hienDanhSachTour(tblDSTourChuaDuyet, dsTourChuaDuyet, scrDSTourChuaDuyet);
			btnDuyetTour.setEnabled(false);
		}

	}

	/*
	 * ---------------------------------------------
	 */

	/**
	 * Hiện danh sách tour
	 * 
	 * @param dsTour: danh sách tour
	 */
	private void hienDanhSachTour(JTable tblTour, List<Tour> dsTour, JScrollPane scr) {
		if (dsTour == null)
			dsTour = new ArrayList<Tour>();
		tourTableModel = new TourTableModel(dsTour);
		tblTour.setModel(tourTableModel);
		scr.setViewportView(tblTour);

		if (dsTour.size() != 0) {
			tblTour.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(1).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(2).setCellRenderer(new MyRenderer());
			tblTour.getColumnModel().getColumn(3).setCellRenderer(new MyRenderer());
			tblTour.getColumnModel().getColumn(4).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(5).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(6).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(9).setCellRenderer(new CenterRenderrer());
			tblTour.getColumnModel().getColumn(10).setCellRenderer(new CenterRenderrer());
		}
		TienIch.chinhKichThuocTable(tblTour, tblTour.getColumnModel().getTotalColumnWidth(), 2, 10, 30, 30, 20, 20, 20,
				15, 15, 15, 15);

	}

	/**
	 * Tìm kiếm tour theo tên
	 * 
	 * @param tuKhoa: từ khoá tên tour
	 * @param dsTour: danh sách tour cần tìm
	 */
	private List<Tour> timKiemTour(String tuKhoa, List<Tour> dsTour) {
		List<Tour> dsTourCanTim = new ArrayList<Tour>();
		for (Tour tour : dsTour) {
			String name = TienIch.chuyenChuoiTiengVietThanhChuoiKhongDau(tour.getTenTour().toLowerCase());
			if (name.contains(tuKhoa)) {
				dsTourCanTim.add(tour);
			}
		}
		return dsTourCanTim;
	}

	/*
	 * =============================================
	 * 
	 * Inner class
	 * 
	 * ============================================
	 */

	@SuppressWarnings("unused")
	private class MyRenderer extends JTextArea implements TableCellRenderer {

		private static final long serialVersionUID = 1L;

		public MyRenderer() {
			setFont(new Font("Arial", Font.PLAIN, 14));
			setLineWrap(true);
			setWrapStyleWord(true);
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			setText(value != null ? value.toString() : "");
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			int height = getPreferredSize().height + 20;
			setSize(table.getColumnModel().getColumn(column).getWidth(), height);
			if (table.getRowHeight(row) != height) {
				table.setRowHeight(row, height);
			}
			return this;
		}
	}

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
			value = sdf.format(((Date) value).getTime());
			super.setValue(value);
		}

	}

}
