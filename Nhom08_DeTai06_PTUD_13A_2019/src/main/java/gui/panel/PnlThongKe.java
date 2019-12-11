package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import control.IPhieuDangKyControl;
import control.IPhieuThuChiControl;
import control.impl.PhieuDangKyControlImpl;
import control.impl.PhieuThuChiControlImpl;
import entities.PhieuDangKy;
import entities.PhieuThuChi;
import model.DSPhieuDangKyModel;
import model.DSPhieuThuTableModel;
import utils.TienIch;

import com.toedter.calendar.JMonthChooser;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.ListSelectionModel;

public class PnlThongKe extends JPanel implements ActionListener {
	private JTabbedPane tabThongTinTK;
	private JTextField txtTongDoanhThu;
	private JTextField txtTongPDKDaTao;
	private IPhieuDangKyControl phieuDangKyControl;
	private IPhieuThuChiControl phieuThuChiControl;
	private List<PhieuDangKy> dsPDK;
	private static List<PhieuThuChi> dsPhieuThuChi;
	private JPanel pnlThongKeDoanhThuTheoThang;
	private JScrollPane srcDSPhieuTC;
	private JTable tblDSPHieuTC;
	private JTable tblDSPDK;
	private JButton btnLamMoi;
	private JButton btnLoc;
	private JMonthChooser mthThang;
	private JScrollPane scrDSPDK;
	private JLabel lblTongPDKDaHuy;
	private JTextField txtSoPDKDaHuy;

	/**
	 * Create the panel.
	 */
	public PnlThongKe() {
		setSize(1500, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel pnlMain = new JPanel();
		add(pnlMain, BorderLayout.CENTER);
		pnlMain.setLayout(new BorderLayout(0, 0));

		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setPreferredSize(new Dimension(10, 70));
		pnlMain.add(pnlTieuDe, BorderLayout.NORTH);
		pnlTieuDe.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 17));

		JLabel label = new JLabel("THỐNG KÊ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 30));
		pnlTieuDe.add(label);

		JPanel pnlThongTinTK = new JPanel();
		pnlThongTinTK.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Th\u00F4ng tin th\u1ED1ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlMain.add(pnlThongTinTK, BorderLayout.CENTER);
		pnlThongTinTK.setLayout(new BorderLayout(0, 0));

		tabThongTinTK = new JTabbedPane(JTabbedPane.TOP);
		tabThongTinTK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlThongTinTK.add(tabThongTinTK, BorderLayout.CENTER);

		JPanel pnlTTThongKeDoanhThu = new JPanel();
		tabThongTinTK.addTab("Thống kê doanh thu theo tháng", null, pnlTTThongKeDoanhThu, null);
		pnlTTThongKeDoanhThu.setLayout(new BorderLayout(0, 0));

		JPanel pnlHTTongTour = new JPanel();
		FlowLayout fl_pnlHTTongTour = (FlowLayout) pnlHTTongTour.getLayout();
		fl_pnlHTTongTour.setAlignment(FlowLayout.LEFT);
		pnlHTTongTour.setPreferredSize(new Dimension(10, 50));
		pnlTTThongKeDoanhThu.add(pnlHTTongTour, BorderLayout.SOUTH);

		JLabel lblTongDoanhThu = new JLabel(
				"Tổng doanh thu hiện tại của tháng " + LocalDate.now().getMonthValue() + " :");
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlHTTongTour.add(lblTongDoanhThu);

		txtTongDoanhThu = new JTextField();
		txtTongDoanhThu.setEditable(false);
		txtTongDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlHTTongTour.add(txtTongDoanhThu);
		txtTongDoanhThu.setColumns(10);

		pnlThongKeDoanhThuTheoThang = new JPanel();
		pnlTTThongKeDoanhThu.add(pnlThongKeDoanhThuTheoThang, BorderLayout.CENTER);

		JPanel pnlTTThongKePDK = new JPanel();
		tabThongTinTK.addTab("Thông kê số phiếu đăng ký", null, pnlTTThongKePDK, null);
		pnlTTThongKePDK.setLayout(new BorderLayout(0, 0));

		JPanel pnlTongPDKDaHuy = new JPanel();
		pnlTongPDKDaHuy.setPreferredSize(new Dimension(10, 50));
		pnlTTThongKePDK.add(pnlTongPDKDaHuy, BorderLayout.SOUTH);
		pnlTongPDKDaHuy.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		pnlTongPDKDaHuy.add(panel);
		
				lblTongPDKDaHuy = new JLabel("Tổng số phiểu đăng ký đã tạo hiện tại của tháng "+LocalDate.now().getMonthValue()+":");
				panel.add(lblTongPDKDaHuy);
				lblTongPDKDaHuy.setHorizontalAlignment(SwingConstants.LEFT);
				lblTongPDKDaHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
				
						txtTongPDKDaTao = new JTextField();
						panel.add(txtTongPDKDaTao);
						txtTongPDKDaTao.setHorizontalAlignment(SwingConstants.LEFT);
						txtTongPDKDaTao.setFont(new Font("Tahoma", Font.PLAIN, 20));
						txtTongPDKDaTao.setEditable(false);
						txtTongPDKDaTao.setColumns(10);
						
						JPanel panel_1 = new JPanel();
						FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
						flowLayout_2.setAlignment(FlowLayout.LEFT);
						pnlTongPDKDaHuy.add(panel_1);
						
						JLabel lblTngSPhiu = new JLabel("Tổng số phiểu đăng ký đã hủy hiện tại của tháng 12:");
						lblTngSPhiu.setHorizontalAlignment(SwingConstants.LEFT);
						lblTngSPhiu.setFont(new Font("Tahoma", Font.PLAIN, 20));
						panel_1.add(lblTngSPhiu);
						
						txtSoPDKDaHuy = new JTextField();
						txtSoPDKDaHuy.setHorizontalAlignment(SwingConstants.LEFT);
						txtSoPDKDaHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
						txtSoPDKDaHuy.setEditable(false);
						txtSoPDKDaHuy.setColumns(10);
						panel_1.add(txtSoPDKDaHuy);

//		dsPDK = quanLyPhieuDKBUS.layDSPhieuDK();
		JPanel pnlChiTietTKPhieuDangKy = new JPanel();
		pnlTTThongKePDK.add(pnlChiTietTKPhieuDangKy, BorderLayout.CENTER);
		pnlChiTietTKPhieuDangKy.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlDieuDoPDK = new JPanel();
		pnlDieuDoPDK.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Bi\u1EC3u \u0111\u1ED3",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChiTietTKPhieuDangKy.add(pnlDieuDoPDK);

		JPanel pnlSoLieuPDK = new JPanel();
		pnlSoLieuPDK
				.setBorder(new TitledBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
						"S\u1ED1 li\u00EAu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChiTietTKPhieuDangKy.add(pnlSoLieuPDK);
		pnlSoLieuPDK.setLayout(new BorderLayout(0, 0));
		
		scrDSPDK = new JScrollPane();
		pnlSoLieuPDK.add(scrDSPDK, BorderLayout.CENTER);
		pnlThongKeDoanhThuTheoThang.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlBieuDo = new JPanel();
		pnlBieuDo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Bi\u1EC3u \u0111\u1ED3",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongKeDoanhThuTheoThang.add(pnlBieuDo);
		pnlBieuDo.setLayout(new BorderLayout(0, 0));

		JPanel pnlSoLieu = new JPanel();
		pnlSoLieu.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "S\u1ED1 li\u1EC7u",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongKeDoanhThuTheoThang.add(pnlSoLieu);
		pnlSoLieu.setLayout(new BorderLayout(0, 0));

		srcDSPhieuTC = new JScrollPane();
		pnlSoLieu.add(srcDSPhieuTC, BorderLayout.CENTER);

		tblDSPHieuTC = new JTable();
		tblDSPHieuTC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel pnlChucNang = new JPanel();
		pnlSoLieu.add(pnlChucNang, BorderLayout.NORTH);
		pnlChucNang.setLayout(new BoxLayout(pnlChucNang, BoxLayout.X_AXIS));

		JPanel pnlLoc = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlLoc.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setVgap(15);
		pnlChucNang.add(pnlLoc);

		JLabel lblChonThang = new JLabel("Chọn tháng:");
		pnlLoc.add(lblChonThang);
		lblChonThang.setFont(new Font("Tahoma", Font.BOLD, 20));

		mthThang = new JMonthChooser();
		mthThang.getComboBox().setPreferredSize(new Dimension(200, 20));
		pnlLoc.add(mthThang);
		mthThang.setLocale(new Locale("vi", "VN"));
		mthThang.setPreferredSize(new Dimension(250, 40));
		mthThang.setFont(new Font("Dialog", Font.PLAIN, 18));
		mthThang.getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 18));
		mthThang.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnLoc = new JButton("");
		btnLoc.setPreferredSize(new Dimension(50, 40));
		btnLoc.setIcon(new ImageIcon(PnlThongKe.class.getResource("/images/filter_25px.png")));
		btnLoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlLoc.add(btnLoc);

		JPanel pnlLamMoi = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlLamMoi.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnlChucNang.add(pnlLamMoi);
		tblDSPDK = new JTable();
		tblDSPDK.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setPreferredSize(new Dimension(150, 40));
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLamMoi.setIcon(new ImageIcon(PnlThongKe.class.getResource("/images/lammoi.png")));
		pnlLamMoi.add(btnLamMoi);

		phieuDangKyControl = new PhieuDangKyControlImpl();
		phieuThuChiControl = new PhieuThuChiControlImpl();
		dsPDK = phieuDangKyControl.layDSPhieuDangKy();

		// Tạo biểu đồ doanh thu
		ChartPanel CpnlBDoanhThu = new ChartPanel((taoBieuDoDoanhThu()));
		CpnlBDoanhThu.setLayout(new BorderLayout(0, 0));
		pnlBieuDo.add(CpnlBDoanhThu, BorderLayout.CENTER);
		pnlDieuDoPDK.setLayout(new BorderLayout(0, 0));

		ChartPanel CpnlBDPhieuDangKy = new ChartPanel(taoBieuDoPhieuDangKy());
		pnlDieuDoPDK.add(CpnlBDPhieuDangKy, BorderLayout.CENTER);

		hienDSPhieuThuChi(LocalDate.now().getMonthValue());
		btnLoc.addActionListener(this);
		btnLamMoi.addActionListener(this);

		TienIch.chinhKichThuocTitleTrenBorder(new JPanel[] { pnlBieuDo, pnlSoLieu }, "Arial", Font.PLAIN, 18);
		dsPDK = phieuDangKyControl.layDSPhieuDangKyTheoThang(LocalDate.now().getMonthValue());
		hienBangDSPDK(tblDSPDK, dsPDK, scrDSPDK);
		hienTTSoLuongPDK(dsPDK);
	}
	/*
	 * Vẽ biểu đò thống kê doanh thu.
	 */

	/**
	 * Lấy tổng doanh thu tháng
	 * 
	 * @param thang: tháng cần lấy danh thu
	 * @return doanh thu theo tháng
	 */
	private double layDoanhThuTheoThang(int thang) {
		double tongTien = 0.0;
		double tongTienPT = 0.0;
		double tongTienPC = 0.0;
		dsPDK = phieuDangKyControl.layDSPhieuDangKyTheoThang(thang);
		for (PhieuDangKy pdk : dsPDK) {
			tongTienPT += phieuThuChiControl.tinhTongTienPhieuThuTheoPDK(pdk.getMaPhieuDK());
			tongTienPC += phieuThuChiControl.tinhTongTienPhieuChiTheoPDK(pdk.getMaPhieuDK());
		}
		tongTien = tongTienPT - tongTienPC;
		return tongTien;
	}

	/**
	 * Tạo dữ liệu doanh thu cho tháng
	 * 
	 * @return: dữ liệu doanh thu
	 */
	private DefaultCategoryDataset taoDuLieuDoanhThu() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 1; i <= 12; i++) {
			dataset.addValue((Number) layDoanhThuTheoThang(i), "Tiền", i);
		}
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "vn"));
		txtTongDoanhThu.setText(format.format(layDoanhThuTheoThang(LocalDate.now().getMonthValue())));
		return dataset;
	}

	// Tao biểu đò thể hiện doanh thu theo tháng
	private JFreeChart taoBieuDoDoanhThu() {

		JFreeChart lineChart = ChartFactory.createLineChart(
				"\nBIỂU ĐỒ DOANH THU THEO TỪNG THÁNG CỦA NĂM " + LocalDate.now().getYear() + "\n", "\tTháng", "\nTiền",
				taoDuLieuDoanhThu(), PlotOrientation.VERTICAL, false, false, false);

		return lineChart;
	}

	// Lay so tong so phieu dang ky cua cac thang
	private int laySoPhieuDangKyTheoThang(int thang) {
		int soPDK = 0;
		dsPDK = phieuDangKyControl.layDSPhieuDangKyTheoThang(thang);
		dsPDK.size();
		return dsPDK.size();
	}

	// Lay tong so phieu dang ky da huy theo thang
	private int laySoPhieuDangKyDaHuyTheoThang(int thang) {
		int soPDK = 0;
		dsPDK = phieuDangKyControl.layDSPhieuDangKyTheoThang(thang);
		for (PhieuDangKy phieuDangKy : dsPDK) {
			if (phieuDangKy.isDaHuyPhieu() == true) {
				soPDK++;
			}
		}
		return soPDK;
	}

//	Tao dữ liệu số lương phiếu đăng ký của các tháng
	private DefaultCategoryDataset taoDuLieuSoPhieuDangKy() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 1; i <= 12; i++) {
			dataset.addValue((Number) laySoPhieuDangKyTheoThang(i), "Số phiếu đăng ký đã tạo", i);
			dataset.addValue((Number) laySoPhieuDangKyDaHuyTheoThang(i), "Số phiếu đăng ký đã hủy", i);
		}
		return dataset;
	}

	// Tao biểu đò thể hiện doanh thu theo tháng
	private JFreeChart taoBieuDoPhieuDangKy() {

		JFreeChart barChar = ChartFactory.createBarChart(
				"Biểu đồ thể hiện số lượng phiếu đăng ký đã tạo và đã hủy theo từng tháng của năm ".toUpperCase()
						+ LocalDate.now().getYear(),
				"Tình trạng", "Tháng", taoDuLieuSoPhieuDangKy(), PlotOrientation.VERTICAL, true, true, false);
		return barChar;
	}

	private void hienThiBangTTDSPhieuTC(JTable tbl, List<PhieuThuChi> ds, JScrollPane src) {
		DSPhieuThuTableModel dsPhieuThuTableModel = new DSPhieuThuTableModel(ds);
		tbl.setModel(dsPhieuThuTableModel);
		src.setViewportView(tbl);
	}
	/**
	 * Hiển thị danh sách phiếu thu chi theo tháng
	 * @param thang : tháng
	 */
	private void hienDSPhieuThuChi(int thang) {
		dsPhieuThuChi = phieuThuChiControl.layDSPhieuTheoThang(thang);
		hienThiBangTTDSPhieuTC(tblDSPHieuTC, dsPhieuThuChi, srcDSPhieuTC);
	}

	/**
	 * Xử lý sự kiện
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "vn"));
		if (o.equals(btnLoc)) {
			int thang = mthThang.getMonth() + 1;
			hienDSPhieuThuChi(thang);
			txtTongDoanhThu.setText(format.format(layDoanhThuTheoThang(thang)) + "");
		} else if (o.equals(btnLamMoi)) {
			hienDSPhieuThuChi(LocalDate.now().getMonthValue());
			mthThang.setMonth(LocalDate.now().getMonthValue());
			ChartPanel CpnlBDoanhThu = new ChartPanel((taoBieuDoDoanhThu()));
			txtTongDoanhThu.setText(format.format(layDoanhThuTheoThang(LocalDate.now().getMonthValue())) + "");
		}

	}
	/**
	 * Hiển thị thông tin số lượng phiếu đăng ký đã tạo và đã hủy theo tháng
	 * @param ds : danh sách phiếu đăng ký
	 */
	public void hienTTSoLuongPDK(List<PhieuDangKy> ds) {
		int sopdkDaHuy = 0;
		for (PhieuDangKy phieuDangKy : ds) {
			if(phieuDangKy.isDaHuyPhieu())
				sopdkDaHuy ++;
		}
		txtTongPDKDaTao.setText(ds.size()+"");
		txtSoPDKDaHuy.setText(sopdkDaHuy+"");
	}
	/**
	 * Hiện bảng danh sách phiếu đăng ký tour
	 * 
	 * @param tbl: bảng
	 * @param ds:  danh sách phiếu đăng ký
	 * @param src: thanh cuộn
	 */
	private void hienBangDSPDK(JTable tbl, List<PhieuDangKy> ds, JScrollPane src) {
		DSPhieuDangKyModel dsPhieuDangKyModel = new DSPhieuDangKyModel(ds);
		tbl.setModel(dsPhieuDangKyModel);
		src.setViewportView(tbl);
		TienIch.chinhKichThuocTable(tbl, tbl.getColumnModel().getTotalColumnWidth(), 5, 15, 15, 20, 15);
//		if (ds.size() != 0) {
//			tbl.getColumnModel().getColumn(0).setCellRenderer(new CenterRenderrer());
//			tbl.getColumnModel().getColumn(2).setCellRenderer(new MyDateRenderer());
//			tbl.getColumnModel().getColumn(3).setCellRenderer(new CenterRenderrer());
//			tbl.getColumnModel().getColumn(4).setCellRenderer(new CenterRenderrer());
//		}

	}
}
