package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

//import bus.IQuanLyPhieuDKBUS;
//import bus.impl.QuanLyPhieuDKBUS;
import entities.PhieuDangKy;
import java.awt.GridLayout;

public class pnlThongKe extends JPanel {
	private JTabbedPane tabThongTinTK;
	private JTextField txtTongDoanhThu;
	private JTextField txtTongPDKDaHuy;
//	private IQuanLyPhieuDKBUS quanLyPhieuDKBUS;
	private List<PhieuDangKy> dsPDK;
	private JPanel pnlThongKeDoanhThuTheoThang;

	/**
	 * Create the panel.
	 */
	public pnlThongKe() {
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

		JPanel pnlLuuVaThoat = new JPanel();
		FlowLayout fl_pnlLuuVaThoat = (FlowLayout) pnlLuuVaThoat.getLayout();
		fl_pnlLuuVaThoat.setAlignment(FlowLayout.RIGHT);
		fl_pnlLuuVaThoat.setHgap(20);
		pnlLuuVaThoat.setPreferredSize(new Dimension(10, 50));
		pnlMain.add(pnlLuuVaThoat, BorderLayout.SOUTH);

		JButton btnLuu = new JButton("Lưu");
		btnLuu.setPreferredSize(new Dimension(120, 35));
		pnlLuuVaThoat.add(btnLuu);

		JButton btnThoat = new JButton("Thoát");
		btnThoat.setPreferredSize(new Dimension(120, 35));
		pnlLuuVaThoat.add(btnThoat);

//		quanLyPhieuDKBUS = new QuanLyPhieuDKBUS();

		tabThongTinTK = new JTabbedPane(JTabbedPane.TOP);
		tabThongTinTK.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
		FlowLayout fl_pnlTongPDKDaHuy = (FlowLayout) pnlTongPDKDaHuy.getLayout();
		fl_pnlTongPDKDaHuy.setAlignment(FlowLayout.LEFT);
		pnlTongPDKDaHuy.setPreferredSize(new Dimension(10, 50));
		pnlTTThongKePDK.add(pnlTongPDKDaHuy, BorderLayout.SOUTH);

		JLabel lblTongPDKDaHuy = new JLabel("Tổng số phiểu đăng ký đã hủy :");
		lblTongPDKDaHuy.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongPDKDaHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlTongPDKDaHuy.add(lblTongPDKDaHuy);

		txtTongPDKDaHuy = new JTextField();
		txtTongPDKDaHuy.setHorizontalAlignment(SwingConstants.LEFT);
		txtTongPDKDaHuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongPDKDaHuy.setEditable(false);
		txtTongPDKDaHuy.setColumns(10);
		pnlTongPDKDaHuy.add(txtTongPDKDaHuy);

//		dsPDK = quanLyPhieuDKBUS.layDSPhieuDK();
		JPanel panel_7 = new JPanel();
		pnlTTThongKePDK.add(panel_7, BorderLayout.CENTER);
		pnlThongKeDoanhThuTheoThang.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel pnlBieuDo = new JPanel();
		pnlBieuDo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Bi\u1EC3u \u0111\u1ED3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongKeDoanhThuTheoThang.add(pnlBieuDo);
		pnlBieuDo.setLayout(new BorderLayout(0, 0));
		
		ChartPanel chartPanel = new ChartPanel((JFreeChart) null);
		pnlBieuDo.add(chartPanel, BorderLayout.CENTER);
		chartPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSoLieu = new JPanel();
		pnlSoLieu.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "S\u1ED1 li\u1EC7u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongKeDoanhThuTheoThang.add(pnlSoLieu);
		pnlSoLieu.setLayout(new BorderLayout(0, 0));
	}
	/*
	 * Vẽ biểu đò thống kê doanh thu.
	 */

	// Tính tổng doanh thu của một tháng
	public double layDoanhThuTheoThang(int thang) {
		double tongTien = 0.0;
		double tongTienTren1phieu = 0.0;
		for (PhieuDangKy phieuDK : dsPDK) {
//			tongTienTren1phieu = phieuDK.tinhTongThanhTien();
			if (phieuDK.getNgayTaoPhieu().toLocalDate().getMonth().getValue() == thang) {
				tongTien += tongTienTren1phieu;
			}
		}
		return tongTien;
	}

//	Tao dữ liệu doanh thu của các tháng
	public DefaultCategoryDataset taoDuLieuDoanhThu() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 1; i <= 12; i++) {
			dataset.addValue((Number) layDoanhThuTheoThang(i), "Tiền", i);
		}
		DecimalFormat df = new DecimalFormat("0,000 VNĐ");
		txtTongDoanhThu.setText(df.format(layDoanhThuTheoThang(LocalDate.now().getMonthValue())));
		return dataset;
	}

	// Tao biểu đò thể hiện doanh thu theo tháng
	public JFreeChart taoBieuDoDoanhThu() {

		JFreeChart lineChart = ChartFactory.createLineChart(
				"\nBIỂU ĐỒ DOANH THU THEO TỪNG THÁNG CỦA NĂM " + LocalDate.now().getYear() + "\n", "\tTháng", "\nTiền",
				taoDuLieuDoanhThu(), PlotOrientation.VERTICAL, false, false, false);
		return lineChart;
	}

	// Lay so tong so phieu dang ky cua cac thang
	public int laySoPhieuDangKyTheoThang(int thang) {
		int soPDK = 0;
		for (PhieuDangKy phieuDangKy : dsPDK) {
			if (phieuDangKy.getNgayTaoPhieu().toLocalDate().getMonth().getValue() == thang) {
				soPDK++;
			}
		}
		return soPDK;
	}
}
